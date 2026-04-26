package org.dawnoftime.gardentrails.block.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import org.dawnoftime.gardentrails.block.IBiomeColoredBlock;
import org.dawnoftime.gardentrails.block.templates.BlockGT;
import javax.annotation.Nullable;
import static org.dawnoftime.gardentrails.util.VoxelShapes.GERANIUM_SHAPE;

public class GeraniumBlock extends BlockGT implements BonemealableBlock, IBiomeColoredBlock {

    public GeraniumBlock(Properties properties) {
        super(properties.pushReaction(PushReaction.DESTROY), GERANIUM_SHAPE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return canSurvive(this.defaultBlockState(), context.getLevel(), context.getClickedPos()) ? super.getStateForPlacement(context) : null;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        BlockState blockDown = worldIn.getBlockState(pos.below());
        return blockDown.getBlock() == Blocks.GRASS_BLOCK || blockDown.is(BlockTags.DIRT) || blockDown.getBlock() == Blocks.FARMLAND;
    }

    @Override
    public boolean isValidBonemealTarget(@NotNull LevelReader level, @NotNull BlockPos pos, @NotNull BlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(@NotNull Level level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        return random.nextInt(10) == 0;
    }

    @Override
    public void performBonemeal(@NotNull ServerLevel level, @NotNull RandomSource random, @NotNull BlockPos pos, @NotNull BlockState state) {
        Block.popResource(level, pos, new ItemStack(this.asItem()));
    }

    @Override
    public ColorType getColorType() {
        return ColorType.FOLIAGE;
    }
}
