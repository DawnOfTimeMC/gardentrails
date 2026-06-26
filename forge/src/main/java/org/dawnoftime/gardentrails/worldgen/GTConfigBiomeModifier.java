package org.dawnoftime.gardentrails.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import org.dawnoftime.gardentrails.GTConfigLoader;

import java.util.List;

public record GTConfigBiomeModifier(
    String plantKey,
    Holder<PlacedFeature> feature
) implements BiomeModifier {

    public static final Codec<GTConfigBiomeModifier> CODEC = RecordCodecBuilder.create(inst -> inst.group(
        Codec.STRING.fieldOf("plant").forGetter(GTConfigBiomeModifier::plantKey),
        PlacedFeature.CODEC.fieldOf("feature").forGetter(GTConfigBiomeModifier::feature)
    ).apply(inst, GTConfigBiomeModifier::new));

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.ADD) return;
        List<String> allowed = GTConfigLoader.INSTANCE.getBiomesForPlant(plantKey);
        String biomeId = biome.unwrapKey()
            .map(k -> k.location().toString())
            .orElse("");
        if (allowed.contains(biomeId)) {
            BiomeGenerationSettingsBuilder generation = builder.getGenerationSettings();
            generation.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, feature);
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return CODEC;
    }
}
