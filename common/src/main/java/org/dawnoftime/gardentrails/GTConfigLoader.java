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
            for (String raw : Files.readAllLines(file)) {
                String line = raw.strip();
                if (line.isEmpty() || line.startsWith("#")) continue;
                int eq = line.indexOf('=');
                if (eq < 0) continue;
                String key = line.substring(0, eq).strip();
                String val = line.substring(eq + 1).strip();
                switch (key) {
                    case "generateChestLoot"         -> INSTANCE.generateChestLoot         = Boolean.parseBoolean(val);
                    case "generateSilk"              -> INSTANCE.generateSilk              = Boolean.parseBoolean(val);
                    case "generateGrapes"            -> INSTANCE.generateGrapes            = Boolean.parseBoolean(val);
                    case "generateMaize"             -> INSTANCE.generateMaize             = Boolean.parseBoolean(val);
                    case "generateRice"              -> INSTANCE.generateRice              = Boolean.parseBoolean(val);
                    case "generateMulberry"          -> INSTANCE.generateMulberry          = Boolean.parseBoolean(val);
                    case "boxwood_bush_biomes"       -> INSTANCE.boxwoodBushBiomes       = parseList(val);
                    case "camellia_biomes"           -> INSTANCE.camelliaBiomes           = parseList(val);
                    case "commelina_biomes"          -> INSTANCE.commelinaBiomes          = parseList(val);
                    case "cypress_biomes"            -> INSTANCE.cypressBiomes            = parseList(val);
                    case "geranium_pink_biomes"      -> INSTANCE.geraniumPinkBiomes       = parseList(val);
                    case "geranium_orange_biomes"    -> INSTANCE.geraniumOrangeBiomes     = parseList(val);
                    case "geranium_purple_biomes"    -> INSTANCE.geraniumPurpleBiomes     = parseList(val);
                    case "geranium_purpleish_biomes" -> INSTANCE.geraniumPurpleishBiomes  = parseList(val);
                    case "geranium_red_biomes"       -> INSTANCE.geraniumRedBiomes        = parseList(val);
                    case "geranium_white_biomes"     -> INSTANCE.geraniumWhiteBiomes      = parseList(val);
                    case "ivy_biomes"                -> INSTANCE.ivyBiomes                = parseList(val);
                    case "mulberry_biomes"           -> INSTANCE.mulberryBiomes           = parseList(val);
                    case "red_maple_biomes"          -> INSTANCE.redMapleBiomes           = parseList(val);
                    case "rice_biomes"               -> INSTANCE.riceBiomes               = parseList(val);
                    case "wild_grape_biomes"         -> INSTANCE.wildGrapeBiomes          = parseList(val);
                    case "wild_maize_biomes"         -> INSTANCE.wildMaizeBiomes          = parseList(val);
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

# Loot settings
generateChestLoot=true
generateSilk=true
generateGrapes=true
generateMaize=true
generateRice=true
generateMulberry=true

# Plant biome lists
# Comma-separated biome IDs. Empty value = plant does not spawn.
# Full namespace required: minecraft:forest, byg:jacaranda_forest, etc.
# Biome IDs from uninstalled mods are silently ignored.

boxwood_bush_biomes=minecraft:forest,minecraft:birch_forest,minecraft:old_growth_birch_forest,minecraft:windswept_forest
camellia_biomes=minecraft:jungle,minecraft:sparse_jungle,minecraft:bamboo_jungle,minecraft:savanna,minecraft:taiga
commelina_biomes=minecraft:swamp,minecraft:mangrove_swamp,minecraft:flower_forest
cypress_biomes=minecraft:savanna,minecraft:savanna_plateau,minecraft:windswept_savanna
geranium_pink_biomes=minecraft:flower_forest,minecraft:meadow,minecraft:birch_forest,minecraft:old_growth_birch_forest
geranium_orange_biomes=minecraft:flower_forest,minecraft:meadow,minecraft:birch_forest,minecraft:old_growth_birch_forest
geranium_purple_biomes=minecraft:flower_forest,minecraft:meadow,minecraft:birch_forest,minecraft:old_growth_birch_forest
geranium_purpleish_biomes=minecraft:flower_forest,minecraft:meadow,minecraft:birch_forest,minecraft:old_growth_birch_forest
geranium_red_biomes=minecraft:flower_forest,minecraft:meadow,minecraft:birch_forest,minecraft:old_growth_birch_forest
geranium_white_biomes=minecraft:flower_forest,minecraft:meadow,minecraft:birch_forest,minecraft:old_growth_birch_forest
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
