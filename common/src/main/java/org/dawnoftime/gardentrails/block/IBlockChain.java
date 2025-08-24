package org.dawnoftime.gardentrails.block;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public interface IBlockChain {

    static boolean canBeChained(BlockState state, boolean tryConnectUnder) {
        Block block = state.getBlock();
        if(block == Blocks.CHAIN) {
            return state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y;
        }
        if(block instanceof IBlockChain)
            return tryConnectUnder ? ((IBlockChain) block).canConnectToChainUnder(state) : ((IBlockChain) block).canConnectToChainAbove(state);
        return false;
    }

    default boolean canConnectToChainAbove(BlockState state) {
        return true;
    }

    default boolean canConnectToChainUnder(BlockState state) {
        return true;
    }

}
