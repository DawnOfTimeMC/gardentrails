package org.dawnoftime.gardentrails;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import org.dawnoftime.gardentrails.datagen.DataGenerators;
import org.dawnoftime.gardentrails.loot.LootModifiers;

@Mod(GTCommon.MOD_ID)
public class GTNeoForge {
    public static final ConfigClassHandler<GTConfig> HANDLER = ConfigClassHandler.createBuilder(GTConfig.class)
            .id(ResourceLocation.fromNamespaceAndPath(GTCommon.MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("gardentrails-config.json5"))
                    .setJson5(true)
                    .build())
            .build();

    public GTNeoForge(IEventBus eventBus, ModContainer container) {
        HANDLER.load();
        GTCommon.init();

        RegistryImpls.init(eventBus);
        LootModifiers.register(eventBus);

        if (FMLEnvironment.dist.isClient()) {
            eventBus.register(GTNeoForgeClient.class);
            eventBus.register(BiomeColorHandlers.class);
        }

        eventBus.register(DataGenerators.class);
    }
}
