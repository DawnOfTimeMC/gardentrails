package org.dawnoftime.gardentrails.compat;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import org.dawnoftime.gardentrails.DoTBFabric;

public class ModMenuEntrypoint implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (parent) -> DoTBFabric.HANDLER.generateGui().generateScreen(parent);
    }
}
