package org.dawnoftime.gardentrails.datagen.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;
import org.dawnoftime.gardentrails.registry.GTFeaturesRegistry;

import java.util.List;

public class GTPlacedFeatures {
    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, GTFeaturesRegistry.CAMELLIA_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.CAMELLIA_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.COMMELINA_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.COMMELINA_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.CYPRESS_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.CYPRESS_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.RED_MAPLE_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.RED_MAPLE_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.BOXWOOD_BUSH_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.BOXWOOD_BUSH_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(4),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.MULBERRY_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.MULBERRY_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.RICE_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.RICE_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(1),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.WILD_GRAPE_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.WILD_GRAPE_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.WILD_MAIZE_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.WILD_MAIZE_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.GERANIUM_PINK_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.GERANIUM_PINK_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );

        register(context, GTFeaturesRegistry.IVY_PLACED_KEY,
                configuredFeatures.getOrThrow(GTConfiguredFeatures.IVY_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(2),
                        InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()
                )
        );
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}