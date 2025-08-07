package org.dawnoftime.dawnoftime.util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.dawnoftime.dawnoftime.DoTBCommon;
import org.dawnoftime.dawnoftime.registry.DoTBTags;

import javax.annotation.Nonnull;
import java.util.List;

public class Utils {
    //General
    public static final int HIGHEST_Y = 255;
    //Tooltip translation text
    public static final Component TOOLTIP_HOLD_SHIFT = Component.translatable("tooltip." +
            DoTBCommon.MOD_ID + ".hold_key").withStyle(ChatFormatting.GRAY).append(Component.translatable("tooltip." +
            DoTBCommon.MOD_ID + ".shift").withStyle(ChatFormatting.AQUA));
    public static final String TOOLTIP_COLUMN = "column";
    public static final String TOOLTIP_CROP = "crop";

    public static VoxelShape[] generateHorizontalShapes(final VoxelShape[] shapes, VoxelShape... nonRotatedShapes) {
        final VoxelShape[] newShape = {Shapes.empty()};
        final VoxelShape[] newShapes = new VoxelShape[shapes.length * 4 + nonRotatedShapes.length];
        int i = 0;
        // First we copy the provided array at the start of the new one.
        for (final VoxelShape shape : shapes) {
            newShapes[i] = shape;
            i++;
        }
        // Then rotate the provided array in each direction, and add it the new array.
        for (int rotation = 1; rotation < 4; rotation++) {
            int j = 0;
            for (final VoxelShape shape : shapes) {
                shape.forAllBoxes((minX, minY, minZ, maxX, maxY, maxZ) -> newShape[0] = Shapes.or(newShape[0], Shapes.box(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
                shapes[j] = newShape[0];
                newShapes[i] = newShape[0];
                newShape[0] = Shapes.empty();
                i++;
                j++;
            }
        }
        // Lastly, we add the non-rotated shapes at the end of the array.
        for (final VoxelShape shape : nonRotatedShapes) {
            newShapes[i] = shape;
            i++;
        }
        return newShapes;
    }

    public static List<ItemStack> getLootList(final ServerLevel serverWorld, final BlockState stateIn, final ItemStack itemStackHand, final String name) {
        final LootTable table = serverWorld.getServer().getLootData().getLootTable(new ResourceLocation(DoTBCommon.MOD_ID + ":blocks/" + name));
        final LootParams.Builder builder = new LootParams.Builder(serverWorld).withParameter(LootContextParams.BLOCK_STATE, stateIn).withParameter(LootContextParams.TOOL, itemStackHand).withParameter(LootContextParams.ORIGIN, new Vec3(0, 0, 0));
        final LootParams lootParams = builder.create(LootContextParamSets.BLOCK);
        return table.getRandomItems(lootParams);
    }

    public static boolean dropLootFromList(final LevelAccessor worldIn, final BlockPos pos, final List<ItemStack> drops, final float multiplier) {
        if (drops.isEmpty() || !(worldIn instanceof Level)) {
            return false;
        }
        for (final ItemStack drop : drops) {
            final int quantity = (int) Math.floor(drop.getCount() * multiplier);
            for (int i = 0; i < quantity; i++) {
                Block.popResource((Level) worldIn, pos, new ItemStack(drop.getItem(), 1));
            }
        }
        return true;
    }

    public static boolean useLighter(final Level worldIn, final BlockPos pos, final Player player, final InteractionHand handIn) {
        final ItemStack itemInHand = player.getItemInHand(handIn);
        if (!itemInHand.isEmpty() && itemInHand.is(DoTBTags.INSTANCE.LIGHTERS)) {
            worldIn.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
            itemInHand.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(handIn));
            return true;
        }
        return false;
    }

    public static void addTooltip(final List<Component> tooltip, @Nonnull final Item item, final String... tooltipNames) {
        final ResourceLocation itemName = item.builtInRegistryHolder().key().location();
        if (itemName != null) {
            String[] tts = new String[tooltipNames.length + 1];
            System.arraycopy(tooltipNames, 0, tts, 0, tooltipNames.length);
            tts[tooltipNames.length] = itemName.getPath();
            Utils.addTooltip(tooltip, tts);
        }
    }

    public static void addTooltip(final List<Component> tooltip, final String... tooltipNames) {
        if (Screen.hasShiftDown()) {
            for (final String tooltipName : tooltipNames) {
                tooltip.add(Component.translatable("tooltip." + DoTBCommon.MOD_ID + "." + tooltipName).withStyle(ChatFormatting.GRAY));
            }
        } else {
            tooltip.add(Utils.TOOLTIP_HOLD_SHIFT);
        }
    }
}