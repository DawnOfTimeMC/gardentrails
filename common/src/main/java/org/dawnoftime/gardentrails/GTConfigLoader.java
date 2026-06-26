package org.dawnoftime.gardentrails;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class GTConfigLoader {

    public static GTConfig INSTANCE = new GTConfig();

    public static void load(Path configDir) {
        Path file = configDir.resolve("gardentrails-config.txt");
        if (!Files.exists(file)) {
            writeDefaults(file);
            return;
        }
        try {
            List<String> lines = Files.readAllLines(file);
            for (String raw : lines) {
                String line = raw.strip();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int eq = line.indexOf('=');
                if (eq < 0) continue;
                String key = line.substring(0, eq).strip();
                String rawValue = line.substring(eq + 1).strip();
                boolean boolValue = Boolean.parseBoolean(rawValue);
                switch (key) {
                    case "generateChestLoot"      -> INSTANCE.generateChestLoot     = boolValue;
                    case "generateSilk"           -> INSTANCE.generateSilk          = boolValue;
                    case "generateGrapes"         -> INSTANCE.generateGrapes        = boolValue;
                    case "generateMaize"          -> INSTANCE.generateMaize         = boolValue;
                    case "generateRice"           -> INSTANCE.generateRice          = boolValue;
                    case "generateMulberry"       -> INSTANCE.generateMulberry      = boolValue;
                    case "dawnOfTimeUseSilk"      -> INSTANCE.dawnOfTimeUseSilk     = boolValue;
                    case "armorOfTheAgesUseSilk"  -> INSTANCE.armorOfTheAgesUseSilk = boolValue;
                    case "boxwood_bush_biomes"    -> INSTANCE.boxwoodBushBiomes  = parseList(rawValue);
                    case "camellia_biomes"        -> INSTANCE.camelliaBiomes     = parseList(rawValue);
                    case "commelina_biomes"       -> INSTANCE.commelinaBiomes    = parseList(rawValue);
                    case "cypress_biomes"         -> INSTANCE.cypressBiomes      = parseList(rawValue);
                    case "geranium_pink_biomes"   -> INSTANCE.geraniumPinkBiomes = parseList(rawValue);
                    case "ivy_biomes"             -> INSTANCE.ivyBiomes          = parseList(rawValue);
                    case "mulberry_biomes"        -> INSTANCE.mulberryBiomes     = parseList(rawValue);
                    case "red_maple_biomes"       -> INSTANCE.redMapleBiomes     = parseList(rawValue);
                    case "rice_biomes"            -> INSTANCE.riceBiomes         = parseList(rawValue);
                    case "wild_grape_biomes"      -> INSTANCE.wildGrapeBiomes    = parseList(rawValue);
                    case "wild_maize_biomes"      -> INSTANCE.wildMaizeBiomes    = parseList(rawValue);
                }
            }
        } catch (IOException e) {
            System.err.println("[GardenTrails] Failed to read config, using defaults: " + e.getMessage());
        }
    }

    private static List<String> parseList(String raw) {
        if (raw == null || raw.isBlank()) return List.of();
        return Arrays.stream(raw.split(","))
            .map(String::strip)
            .filter(s -> !s.isEmpty())
            .toList();
    }

    private static void writeDefaults(Path file) {
        String content = """
# Garden Trails configuration
# Restart required after changes

# Master toggle for chest loot injection
generateChestLoot=true

# Individual items (only if generateChestLoot=true)
generateSilk=true
generateGrapes=true
generateMaize=true
generateRice=true
generateMulberry=true

# Requires Dawn of Time - replaces white wool with silk in DoT recipes
dawnOfTimeUseSilk=true

# Requires Armor of the Ages - replaces string with silk in AotA recipes
armorOfTheAgesUseSilk=true

# Plant biome lists
# Comma-separated biome IDs. Empty = plant does not spawn.
# Use full biome IDs: namespace:biome_name (e.g., minecraft:forest, byg:jacaranda_forest)
# IDs for biomes from mods that are not installed are ignored automatically.

boxwood_bush_biomes=minecraft:forest,minecraft:birch_forest,minecraft:old_growth_birch_forest,minecraft:windswept_forest
camellia_biomes=minecraft:jungle,minecraft:sparse_jungle,minecraft:bamboo_jungle,minecraft:savanna,minecraft:taiga
commelina_biomes=minecraft:swamp,minecraft:mangrove_swamp,minecraft:flower_forest
cypress_biomes=minecraft:savanna,minecraft:savanna_plateau,minecraft:windswept_savanna
geranium_pink_biomes=minecraft:flower_forest,minecraft:meadow,minecraft:birch_forest,minecraft:old_growth_birch_forest
ivy_biomes=minecraft:forest,minecraft:birch_forest,minecraft:old_growth_birch_forest,minecraft:dark_forest
mulberry_biomes=minecraft:taiga,minecraft:old_growth_pine_taiga,minecraft:old_growth_spruce_taiga
red_maple_biomes=minecraft:birch_forest,minecraft:old_growth_birch_forest,minecraft:dark_forest
rice_biomes=minecraft:swamp,minecraft:mangrove_swamp
wild_grape_biomes=minecraft:forest,minecraft:birch_forest,minecraft:dark_forest
wild_maize_biomes=minecraft:savanna,minecraft:savanna_plateau,minecraft:windswept_savanna,minecraft:plains
""";
        try {
            Files.createDirectories(file.getParent());
            Files.writeString(file, content);
        } catch (IOException e) {
            System.err.println("[GardenTrails] Failed to write default config: " + e.getMessage());
        }
    }
}
