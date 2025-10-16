package org.dawnoftime.gardentrails.block.templates;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.dawnoftime.gardentrails.block.IBiomeColoredBlock;
import org.dawnoftime.gardentrails.block.IBlockSpecialDisplay;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;

import javax.annotation.Nullable;

import static net.minecraft.world.level.block.Blocks.FLOWER_POT;
import static org.dawnoftime.gardentrails.util.VoxelShapes.FLOWER_POT_SHAPE;

public class FlowerPotBlockGT extends BlockGT implements IBlockSpecialDisplay, IBiomeColoredBlock {
    private Item itemInPot;

    public FlowerPotBlockGT(@Nullable Item itemInPot) {
        super(Properties.ofFullCopy(FLOWER_POT), FLOWER_POT_SHAPE);
        this.itemInPot = itemInPot;
    }

    public void setItemInPot(@Nullable Item itemInPot) {
        this.itemInPot = itemInPot;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(this.itemInPot != null && !level.isClientSide()) {
            Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(this.itemInPot));
            level.setBlock(pos, Blocks.FLOWER_POT.defaultBlockState(), 2);
        }

        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    public BlockState getRandomState() {
        return this.defaultBlockState();
    }

    @Override
    public float getDisplayScale() {
        return 0.667F;
    }
    
    @Override
    public ColorType getColorType() {
        if (this.itemInPot != null && this.itemInPot == GTBlocksRegistry.INSTANCE.CYPRESS.get().asItem()) {
            return ColorType.FOLIAGE;
        }
        return null;
    }
}
