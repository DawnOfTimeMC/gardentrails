package org.dawnoftime.dawnoftime.platform;

import org.dawnoftime.dawnoftime.DoTBConfig;
import org.dawnoftime.dawnoftime.DoTBFabric;
import org.dawnoftime.dawnoftime.platform.services.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public DoTBConfig getConfig() {
        return DoTBFabric.HANDLER.instance();
    }
}
