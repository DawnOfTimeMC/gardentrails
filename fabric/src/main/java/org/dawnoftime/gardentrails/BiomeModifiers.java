package org.dawnoftime.gardentrails;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.dawnoftime.gardentrails.registry.GTFeaturesRegistry;

import java.util.List;

public class BiomeModifiers {
    public static void init() {
        addFromConfig("boxwood_bush",       GTFeaturesRegistry.BOXWOOD_BUSH_PLACED_KEY);
        addFromConfig("camellia",           GTFeaturesRegistry.CAMELLIA_PLACED_KEY);
        addFromConfig("commelina",          GTFeaturesRegistry.COMMELINA_PLACED_KEY);
        addFromConfig("cypress",            GTFeaturesRegistry.CYPRESS_PLACED_KEY);
        addFromConfig("geranium_pink",      GTFeaturesRegistry.GERANIUM_PINK_PLACED_KEY);
        addFromConfig("geranium_orange",    GTFeaturesRegistry.GERANIUM_ORANGE_PLACED_KEY);
        addFromConfig("geranium_purple",    GTFeaturesRegistry.GERANIUM_PURPLE_PLACED_KEY);
        addFromConfig("geranium_purpleish", GTFeaturesRegistry.GERANIUM_PURPLEISH_PLACED_KEY);
        addFromConfig("geranium_red",       GTFeaturesRegistry.GERANIUM_RED_PLACED_KEY);
        addFromConfig("geranium_white",     GTFeaturesRegistry.GERANIUM_WHITE_PLACED_KEY);
        addFromConfig("ivy",                GTFeaturesRegistry.IVY_PLACED_KEY);
        addFromConfig("mulberry",           GTFeaturesRegistry.MULBERRY_PLACED_KEY);
        addFromConfig("red_maple",          GTFeaturesRegistry.RED_MAPLE_PLACED_KEY);
        addFromConfig("rice",               GTFeaturesRegistry.RICE_PLACED_KEY);
        addFromConfig("wild_grape",         GTFeaturesRegistry.WILD_GRAPE_PLACED_KEY);
        addFromConfig("wild_maize",         GTFeaturesRegistry.WILD_MAIZE_PLACED_KEY);
    }

    private static void addFromConfig(String plantKey, ResourceKey<PlacedFeature> featureKey) {
        List<String> biomes = GTConfigLoader.INSTANCE.getBiomesForPlant(plantKey);
        for (String biomeId : biomes) {
            ResourceLocation loc = ResourceLocation.tryParse(biomeId);
            if (loc == null) {
                System.err.println("[GardenTrails] Invalid biome ID '" + biomeId + "' for plant " + plantKey);
                continue;
            }
            ResourceKey<Biome> biomeKey = ResourceKey.create(
                net.minecraft.core.registries.Registries.BIOME, loc
            );
            BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(biomeKey),
                GenerationStep.Decoration.VEGETAL_DECORATION,
                featureKey
            );
        }
    }
}
