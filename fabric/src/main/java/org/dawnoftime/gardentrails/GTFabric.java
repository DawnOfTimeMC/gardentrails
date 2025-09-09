package org.dawnoftime.gardentrails;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.ResourceLocation;
import org.dawnoftime.gardentrails.loot.GTFabricLootModifier;

public class GTFabric implements ModInitializer, ClientModInitializer {
    public static final ConfigClassHandler<GTConfig> HANDLER = ConfigClassHandler.createBuilder(GTConfig.class)
            .id(new ResourceLocation(GTCommon.MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("gardentrails-config.json5"))
                    .setJson5(true)
                    .build())
            .build();

    @Override
    public void onInitialize() {
        HANDLER.load();

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
