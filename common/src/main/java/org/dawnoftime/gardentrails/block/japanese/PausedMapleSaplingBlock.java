package org.dawnoftime.gardentrails.block.japanese;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.FlintAndSteelItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.dawnoftime.gardentrails.block.templates.BushBlockGT;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;

import static org.dawnoftime.gardentrails.util.VoxelShapes.SAPLING_SHAPES;

public class PausedMapleSaplingBlock extends BushBlockGT {

    public PausedMapleSaplingBlock(final Properties properties) {
        super(properties, SAPLING_SHAPES);
    }

//    @Override
//    public ItemStack getCloneItemStack(final BlockState stateIn, final HitResult targetIn, final BlockGetter worldIn, final BlockPos posIn, final Player playerIn) {
//        return new ItemStack(DoTBBlocksRegistry.INSTANCE.MAPLE_RED_SAPLING.get().asItem());
//    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (player.getMainHandItem().getItem() instanceof FlintAndSteelItem) {
            level.setBlock(pos, GTBlocksRegistry.INSTANCE.MAPLE_RED_SAPLING.get().defaultBlockState(), 35);
            level.levelEvent(player, 2001, pos, Block.getId(state));

            return ItemInteractionResult.SUCCESS;
        }

        return ItemInteractionResult.FAIL;
    }

    /**
     * Lights methods
     */
    @Override
    public boolean useShapeForLightOcclusion(final BlockState p_220074_1_In) {
        return false;
    }

    @Override
    public float getShadeBrightness(final BlockState p_220080_1_, final BlockGetter p_220080_2_, final BlockPos p_220080_3_) {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(final BlockState p_200123_1_, final BlockGetter p_200123_2_, final BlockPos p_200123_3_) {
        return true;
    }
}
