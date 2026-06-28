package org.dawnoftime.gardentrails;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.dawnoftime.gardentrails.loot.GTFabricLootModifier;

public class GTFabric implements ModInitializer, ClientModInitializer {

    @Override
    public void onInitialize() {
        GTConfigLoader.load(FabricLoader.getInstance().getConfigDir());

        GTCommon.init();
        RegistryImpls.init();
        BiomeModifiers.init();

        GTFabricLootModifier.modifyLootTables();
    }

    @Override
    public void onInitializeClient() {
        RegistryImpls.initClient();
        RenderLayers.init();
        BiomeColorHandlers.init();
    }
}
