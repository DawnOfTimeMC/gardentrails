package org.dawnoftime.gardentrails.datagen.worldgen;

import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.dawnoftime.gardentrails.GTCommon;
import org.dawnoftime.gardentrails.registry.GTFeaturesRegistry;

public class GTBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_CAMELLIA = registerKey("add_camellia");
    public static final ResourceKey<BiomeModifier> ADD_COMMELINA = registerKey("add_commelina");
    public static final ResourceKey<BiomeModifier> ADD_CYPRESS = registerKey("add_cypress");
    public static final ResourceKey<BiomeModifier> ADD_RED_MAPLE = registerKey("add_red_maple");
    public static final ResourceKey<BiomeModifier> ADD_BOXWOOD_BUSH = registerKey("add_boxwood_bush");
    public static final ResourceKey<BiomeModifier> ADD_MULBERRY = registerKey("add_mulberry");
    public static final ResourceKey<BiomeModifier> ADD_RICE = registerKey("add_rice");
    public static final ResourceKey<BiomeModifier> ADD_WILD_GRAPE = registerKey("add_wild_grape");
    public static final ResourceKey<BiomeModifier> ADD_WILD_MAIZE = registerKey("add_wild_maize");
    public static final ResourceKey<BiomeModifier> ADD_GERANIUM_PINK = registerKey("add_geranium_pink");
    public static final ResourceKey<BiomeModifier> ADD_IVY = registerKey("add_ivy");

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_CAMELLIA,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.FLOWER_FOREST).get(),
                                biomes.get(Biomes.JUNGLE).get(),
                                biomes.get(Biomes.SPARSE_JUNGLE).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.CAMELLIA_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_COMMELINA,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.SWAMP).get(),
                                biomes.get(Biomes.MANGROVE_SWAMP).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.COMMELINA_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_CYPRESS,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.TAIGA).get(),
                                biomes.get(Biomes.SNOWY_TAIGA).get(),
                                biomes.get(Biomes.OLD_GROWTH_PINE_TAIGA).get(),
                                biomes.get(Biomes.OLD_GROWTH_SPRUCE_TAIGA).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.CYPRESS_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_RED_MAPLE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.FLOWER_FOREST).get(),
                                biomes.get(Biomes.TAIGA).get(),
                                biomes.get(Biomes.SNOWY_TAIGA).get(),
                                biomes.get(Biomes.OLD_GROWTH_PINE_TAIGA).get(),
                                biomes.get(Biomes.OLD_GROWTH_SPRUCE_TAIGA).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.RED_MAPLE_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_BOXWOOD_BUSH,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.FLOWER_FOREST).get(),
                                biomes.get(Biomes.FOREST).get(),
                                biomes.get(Biomes.WINDSWEPT_FOREST).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.BOXWOOD_BUSH_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_MULBERRY,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.FLOWER_FOREST).get(),
                                biomes.get(Biomes.TAIGA).get(),
                                biomes.get(Biomes.SNOWY_TAIGA).get(),
                                biomes.get(Biomes.OLD_GROWTH_PINE_TAIGA).get(),
                                biomes.get(Biomes.OLD_GROWTH_SPRUCE_TAIGA).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.MULBERRY_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_RICE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.SWAMP).get(),
                                biomes.get(Biomes.MANGROVE_SWAMP).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.RICE_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_WILD_GRAPE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.FLOWER_FOREST).get(),
                                biomes.get(Biomes.BIRCH_FOREST).get(),
                                biomes.get(Biomes.OLD_GROWTH_BIRCH_FOREST).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.WILD_GRAPE_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_WILD_MAIZE,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.SAVANNA).get(),
                                biomes.get(Biomes.SAVANNA_PLATEAU).get(),
                                biomes.get(Biomes.WINDSWEPT_SAVANNA).get(),
                                biomes.get(Biomes.FLOWER_FOREST).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.WILD_MAIZE_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_GERANIUM_PINK,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.FLOWER_FOREST).get(),
                                biomes.get(Biomes.BIRCH_FOREST).get(),
                                biomes.get(Biomes.OLD_GROWTH_BIRCH_FOREST).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.GERANIUM_PINK_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );

        context.register(ADD_IVY,
                new BiomeModifiers.AddFeaturesBiomeModifier(
                        HolderSet.direct(
                                biomes.get(Biomes.FLOWER_FOREST).get(),
                                biomes.get(Biomes.BIRCH_FOREST).get(),
                                biomes.get(Biomes.OLD_GROWTH_BIRCH_FOREST).get()),
                        HolderSet.direct(placedFeatures.getOrThrow(GTFeaturesRegistry.IVY_PLACED_KEY)),
                        GenerationStep.Decoration.VEGETAL_DECORATION
                )
        );
    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(GTCommon.MOD_ID, name));
    }
}