package org.dawnoftime.gardentrails.platform.services;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.dawnoftime.gardentrails.GTConfig;

public interface IPlatformHelper {


    /**
     * Checks if a mod with the given id is loaded.
     *
     * @param modId The mod to check if it is loaded.
     * @return True if the mod is loaded, false otherwise.
     */
    boolean isModLoaded(String modId);

    GTConfig getConfig();

    // technically doesn't belong here (i think?) but needs to be because for whatever reason neoforge patches the method signature of this method
    float getGrowthSpeed(BlockState state, BlockGetter level, BlockPos pos);
}