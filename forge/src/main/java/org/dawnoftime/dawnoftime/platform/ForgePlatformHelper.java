package org.dawnoftime.dawnoftime.platform;

import org.dawnoftime.dawnoftime.DoTBConfig;
import org.dawnoftime.dawnoftime.DoTBForge;
import org.dawnoftime.dawnoftime.platform.services.IPlatformHelper;
import net.minecraftforge.fml.ModList;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public DoTBConfig getConfig() {
        return DoTBForge.HANDLER.instance();
    }
}