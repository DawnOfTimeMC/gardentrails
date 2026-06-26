package org.dawnoftime.gardentrails;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.dawnoftime.gardentrails.registry.GTFeaturesRegistry;

import java.util.List;

public class BiomeModifiers {

    public static void init() {
        GTConfig cfg = GTConfigLoader.INSTANCE;
        registerFromConfig(GTFeaturesRegistry.BOXWOOD_BUSH_PLACED_KEY, cfg.boxwoodBushBiomes);
        registerFromConfig(GTFeaturesRegistry.CAMELLIA_PLACED_KEY,     cfg.camelliaBiomes);
        registerFromConfig(GTFeaturesRegistry.COMMELINA_PLACED_KEY,    cfg.commelinaBiomes);
        registerFromConfig(GTFeaturesRegistry.CYPRESS_PLACED_KEY,      cfg.cypressBiomes);
        registerFromConfig(GTFeaturesRegistry.GERANIUM_PINK_PLACED_KEY,cfg.geraniumPinkBiomes);
        registerFromConfig(GTFeaturesRegistry.IVY_PLACED_KEY,          cfg.ivyBiomes);
        registerFromConfig(GTFeaturesRegistry.MULBERRY_PLACED_KEY,     cfg.mulberryBiomes);
        registerFromConfig(GTFeaturesRegistry.RED_MAPLE_PLACED_KEY,    cfg.redMapleBiomes);
        registerFromConfig(GTFeaturesRegistry.RICE_PLACED_KEY,         cfg.riceBiomes);
        registerFromConfig(GTFeaturesRegistry.WILD_GRAPE_PLACED_KEY,   cfg.wildGrapeBiomes);
        registerFromConfig(GTFeaturesRegistry.WILD_MAIZE_PLACED_KEY,   cfg.wildMaizeBiomes);
    }

    private static void registerFromConfig(ResourceKey<PlacedFeature> featureKey, List<String> biomeIds) {
        for (String id : biomeIds) {
            ResourceLocation loc = ResourceLocation.tryParse(id);
            if (loc == null) {
                System.err.println("[GardenTrails] Invalid biome ID in config, skipping: " + id);
                continue;
            }
            ResourceKey<Biome> biomeKey = ResourceKey.create(Registries.BIOME, loc);
            BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(biomeKey),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                featureKey
            );
        }
    }
}
