package org.dawnoftime.gardentrails.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.dawnoftime.gardentrails.GTFabric;

public class ModMenuEntrypoint implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (parent) -> GTFabric.HANDLER.generateGui().generateScreen(parent);
    }
}
