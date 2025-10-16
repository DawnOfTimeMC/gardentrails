package org.dawnoftime.gardentrails.platform;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.dawnoftime.gardentrails.GTConfig;
import org.dawnoftime.gardentrails.GTFabric;
import org.dawnoftime.gardentrails.mixin.accessor.CropBlockAccessor;
import org.dawnoftime.gardentrails.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public GTConfig getConfig() {
        return GTFabric.HANDLER.instance();
    }

    @Override
    public float getGrowthSpeed(BlockState state, BlockGetter level, BlockPos pos) {
        return CropBlockAccessor.callGetGrowthSpeed(state.getBlock(), level, pos);
    }
}
