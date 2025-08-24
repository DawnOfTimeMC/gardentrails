package org.dawnoftime.gardentrails.platform;

import org.dawnoftime.gardentrails.GTConfig;
import org.dawnoftime.gardentrails.GTForge;
import org.dawnoftime.gardentrails.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public GTConfig getConfig() {
        return GTForge.HANDLER.instance();
    }
}