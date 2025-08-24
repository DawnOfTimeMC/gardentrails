package org.dawnoftime.gardentrails.platform.services;

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
}