package org.dawnoftime.gardentrails;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.dawnoftime.gardentrails.datagen.DataGenerators;
import org.dawnoftime.gardentrails.loot.LootModifiers;
import org.dawnoftime.gardentrails.worldgen.GTConfigBiomeModifier;

@Mod(GTCommon.MOD_ID)
public class GTForge {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
        DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, GTCommon.MOD_ID);

    public static final RegistryObject<Codec<GTConfigBiomeModifier>> CONFIG_BIOME_MODIFIER =
        BIOME_MODIFIER_SERIALIZERS.register("config_driven", () -> GTConfigBiomeModifier.CODEC);

    public GTForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        GTConfigLoader.load(FMLPaths.CONFIGDIR.get());
        GTCommon.init();

        RegistryImpls.init(modEventBus);
        LootModifiers.register(modEventBus);
        BIOME_MODIFIER_SERIALIZERS.register(modEventBus);

        if (FMLEnvironment.dist.isClient()) {
            modEventBus.register(GTForgeClient.class);
        }

        modEventBus.register(DataGenerators.class);
    }
}
