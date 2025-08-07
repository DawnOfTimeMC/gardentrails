package org.dawnoftime.dawnoftime.block;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

public interface IBlockGeneration {

    boolean generateOnPos(WorldGenLevel world, BlockPos pos, BlockState state, RandomSource random);
}
