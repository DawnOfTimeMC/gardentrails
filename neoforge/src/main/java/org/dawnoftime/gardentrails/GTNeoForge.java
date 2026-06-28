package org.dawnoftime.gardentrails;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.dawnoftime.gardentrails.datagen.DataGenerators;
import org.dawnoftime.gardentrails.loot.LootModifiers;
import org.dawnoftime.gardentrails.worldgen.GTConfigBiomeModifier;

@Mod(GTCommon.MOD_ID)
public class GTNeoForge {

    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
        DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, GTCommon.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends BiomeModifier>, MapCodec<GTConfigBiomeModifier>> CONFIG_BIOME_MODIFIER =
        BIOME_MODIFIER_SERIALIZERS.register("config_driven", () -> GTConfigBiomeModifier.CODEC);

    public GTNeoForge(IEventBus eventBus, ModContainer container) {
        GTConfigLoader.load(FMLPaths.CONFIGDIR.get());
        GTCommon.init();

        RegistryImpls.init(eventBus);
        BIOME_MODIFIER_SERIALIZERS.register(eventBus);
        LootModifiers.register(eventBus);

        if (FMLEnvironment.dist.isClient()) {
            eventBus.register(GTNeoForgeClient.class);
            eventBus.register(BiomeColorHandlers.class);
        }

        eventBus.register(DataGenerators.class);
    }
}
