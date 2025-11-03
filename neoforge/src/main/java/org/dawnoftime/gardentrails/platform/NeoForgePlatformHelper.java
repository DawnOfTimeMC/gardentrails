package org.dawnoftime.gardentrails.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModList;
import org.dawnoftime.gardentrails.GTConfig;
import org.dawnoftime.gardentrails.GTNeoForge;
import org.dawnoftime.gardentrails.mixin.accessor.CropBlockAccessor;
import org.dawnoftime.gardentrails.platform.services.IPlatformHelper;

public class NeoForgePlatformHelper implements IPlatformHelper {
    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public GTConfig getConfig() {
        return GTNeoForge.HANDLER.instance();
    }

    @Override
    public float getGrowthSpeed(BlockState state, BlockGetter level, BlockPos pos) {
        return CropBlockAccessor.callGetGrowthSpeed(state, level, pos);
    }
}
