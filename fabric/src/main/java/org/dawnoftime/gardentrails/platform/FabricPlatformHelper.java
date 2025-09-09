package org.dawnoftime.gardentrails.platform;

import org.dawnoftime.gardentrails.GTConfig;
import org.dawnoftime.gardentrails.GTFabric;
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
}
