package org.dawnoftime.gardentrails.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.dawnoftime.gardentrails.GTCommon;
import org.dawnoftime.gardentrails.datagen.worldgen.GTBiomeModifiers;
import org.dawnoftime.gardentrails.datagen.worldgen.GTConfiguredFeatures;
import org.dawnoftime.gardentrails.datagen.worldgen.GTPlacedFeatures;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GTWorldGenProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, GTConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, GTPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, GTBiomeModifiers::bootstrap);

    public GTWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, BUILDER, Set.of(GTCommon.MOD_ID));
    }
}