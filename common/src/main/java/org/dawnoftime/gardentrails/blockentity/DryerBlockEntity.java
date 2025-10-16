package org.dawnoftime.gardentrails.blockentity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.dawnoftime.gardentrails.platform.Services;
import org.dawnoftime.gardentrails.recipe.DryerRecipe;
import org.dawnoftime.gardentrails.recipe.SimpleContainerRecipeInput;
import org.dawnoftime.gardentrails.registry.GTBlockEntitiesRegistry;
import org.dawnoftime.gardentrails.registry.GTRecipeTypesRegistry;

import javax.annotation.Nullable;
import java.util.Random;

public class DryerBlockEntity extends BlockEntity {
    public final SimpleContainer itemHandler = new SimpleContainer(2);
    private final int[] remainingTicks = new int[2];
    private boolean isInOperation;

    public DryerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(GTBlockEntitiesRegistry.INSTANCE.DRYER.get(), pPos, pBlockState);
    }

    public void tick() {
        if (this.getLevel() != null && this.isInOperation) {
            int finish = 0;
            boolean success = false;
            for (int slotIndex = 0; slotIndex < this.remainingTicks.length; slotIndex++) {
                this.remainingTicks[slotIndex]--;

                if (this.remainingTicks[slotIndex] <= 0) {
                    this.remainingTicks[slotIndex] = 0;
                    // Item dried, we replace it with the recipe result, and clear the recipe cached.
                    final DryerRecipe recipe = this.getDryerRecipe(new SimpleContainer(this.itemHandler.getItem(slotIndex)));

                    if (recipe != null) {
                        this.itemHandler.setItem(slotIndex, recipe.getResultItem(this.getLevel().registryAccess()).copy());
                        success = true;
                    }
                    finish++;
                }
            }

            if (success) {
                this.setChanged();
                BlockState state = level.getBlockState(worldPosition);
                this.getLevel().sendBlockUpdated(this.worldPosition, state, state, Block.UPDATE_ALL);
            }

            if (finish >= 2) {
                this.isInOperation = false;
            }
        }
    }

    public ItemInteractionResult tryInsertItemStack(final ItemStack itemStack, final boolean simple, final Level worldIn, final BlockPos pos, final Player player) {

        //Try to put the itemStack in an empty dryer
        if (this.putItemStackInFreeSpace(itemStack, simple, player)) {
            return ItemInteractionResult.SUCCESS;
        }
        //No empty dryer, let's see if we could replace a dried item with ours
        if (simple) {
            if (this.itemIsDried(0)) {
                this.dropItemIndex(0, worldIn, pos);
                this.putItemStackInIndex(0, itemStack, player);
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        final int index = this.dropOneDriedItem(worldIn, pos);
        if (index < 0) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        this.putItemStackInIndex(index, itemStack, player);
        return ItemInteractionResult.SUCCESS;
    }

    public ItemInteractionResult dropOneItem(final Level worldIn, final BlockPos pos) {
        if (this.dropOneDriedItem(worldIn, pos) > -1) {
            return ItemInteractionResult.SUCCESS;
        }
        if (!this.itemHandler.getItem(0).isEmpty()) {
            this.dropItemIndex(0, worldIn, pos);
            return ItemInteractionResult.SUCCESS;
        }
        if (!this.itemHandler.getItem(1).isEmpty()) {
            this.dropItemIndex(1, worldIn, pos);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    public int dropOneDriedItem(final Level worldIn, final BlockPos pos) {
        if (this.itemIsDried(0)) {
            this.dropItemIndex(0, worldIn, pos);
            return 0;
        }
        if (this.itemIsDried(1)) {
            this.dropItemIndex(1, worldIn, pos);
            return 1;
        }
        return -1;
    }

    private boolean itemIsDried(final int index) {
        if (this.itemHandler.getItem(index).isEmpty()) {
            return false;
        }
        return this.remainingTicks[index] <= 0;
    }

    private boolean putItemStackInFreeSpace(final ItemStack itemStack, final boolean simple, final Player player) {

        if (this.itemHandler.getItem(0).isEmpty() && this.putItemStackInIndex(0, itemStack, player)) {
            this.isInOperation = true;
            return true;
        }
        if (!simple && this.itemHandler.getItem(1).isEmpty() && this.putItemStackInIndex(1, itemStack, player)) {
            this.isInOperation = true;
            return true;
        }
        return false;
    }

    @Nullable
    private DryerRecipe getDryerRecipe(final SimpleContainer ingredientInventory) {
        if (this.getLevel() != null && !this.getLevel().isClientSide) {
            return this.getLevel().getRecipeManager().getRecipeFor(GTRecipeTypesRegistry.INSTANCE.DRYING.get(), new SimpleContainerRecipeInput(ingredientInventory), this.getLevel()).map(RecipeHolder::value).orElse(null);
        }
        return null;
    }

    private boolean putItemStackInIndex(final int index, final ItemStack itemStack, final Player player) {
        //Tries to put the itemStack in a dryer : first we check if there is a corresponding recipe, then we set the variables.
        if (this.getLevel() != null) {
            final SimpleContainer invInHand = new SimpleContainer(itemStack);
            final DryerRecipe recipe = this.getDryerRecipe(invInHand);
            if (recipe != null && recipe.matches(new SimpleContainerRecipeInput(invInHand), this.getLevel())) {
                this.itemHandler.setItem(index, recipe.getIngredients().get(0).getItems()[0].copy());
                if (!player.isCreative()) {
                    itemStack.shrink(recipe.getIngredients().get(0).getItems()[0].getCount());
                }
                final float timeVariation = new Random().nextFloat() * 2.0F - 1.0F;
                final int range = timeVariation >= 0 ? Services.PLATFORM.getConfig().dryingTimeVariation : 10000 / (100 + Services.PLATFORM.getConfig().dryingTimeVariation);
                this.remainingTicks[index] = (int) (recipe.dryingTime() * (100 + timeVariation * range) / 100);

                this.setChanged();
                BlockState state = level.getBlockState(worldPosition);
                this.getLevel().sendBlockUpdated(this.worldPosition, state, state, Block.UPDATE_ALL);

                return true;
            }
        }
        return false;
    }

    private void dropItemIndex(final int index, final Level worldIn, final BlockPos pos) {
        Block.popResource(worldIn, pos, this.itemHandler.removeItem(index, 64));
        this.remainingTicks[index] = 0;
        if (this.getLevel() != null) {
            final BlockState state = this.getLevel().getBlockState(pos);
            this.setChanged();
            this.getLevel().sendBlockUpdated(this.worldPosition, state, state, Block.UPDATE_CLIENTS);
        }
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = super.getUpdateTag(registries);
        if (!itemHandler.getItem(0).isEmpty()) {
            tag.put("slot_0", itemHandler.getItem(0).save(registries));
        }
        if (!itemHandler.getItem(1).isEmpty()) {
            tag.put("slot_1", itemHandler.getItem(1).save(registries));
        }
        tag.putBoolean("isInOperation", this.isInOperation);
        return tag;
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        if (!itemHandler.getItem(0).isEmpty()) {
            tag.put("slot_0", itemHandler.getItem(0).save(registries));
        }
        if (!itemHandler.getItem(1).isEmpty()) {
            tag.put("slot_1", itemHandler.getItem(1).save(registries));
        }
        for (int index = 0; index < 2; index++) {
            tag.putInt("remainingTime" + index, this.remainingTicks[index]);
        }
        tag.putBoolean("isInOperation", this.isInOperation);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        itemHandler.clearContent();
        ItemStack.parse(registries, tag.getCompound("slot_0")).ifPresent(itemStack -> itemHandler.setItem(0, itemStack));
        ItemStack.parse(registries, tag.getCompound("slot_1")).ifPresent(itemStack -> itemHandler.setItem(1, itemStack));
        for (int index = 0; index < 2; index++) {
            this.remainingTicks[index] = tag.getInt("remainingTime" + index);
        }
        this.isInOperation = tag.getBoolean("isInOperation");

        super.loadAdditional(tag, registries);
    }
}