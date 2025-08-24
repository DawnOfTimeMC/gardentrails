package org.dawnoftime.gardentrails;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.platform.YACLPlatform;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.dawnoftime.gardentrails.datagen.DataGenerators;
import org.dawnoftime.gardentrails.loot.LootModifiers;

@Mod(GTCommon.MOD_ID)
public class GTForge {
    public static final ConfigClassHandler<GTConfig> HANDLER = ConfigClassHandler.createBuilder(GTConfig.class)
            .id(new ResourceLocation(GTCommon.MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(YACLPlatform.getConfigDir().resolve("gardentrails-config.json5"))
                    .setJson5(true)
                    .build())
            .build();

    public GTForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        HANDLER.load();
        GTCommon.init();

        RegistryImpls.init(modEventBus);
        LootModifiers.register(modEventBus);

        if (FMLEnvironment.dist.isClient()) {
            modEventBus.register(GTForgeClient.class);
        }

        modEventBus.register(DataGenerators.class);
    }

}