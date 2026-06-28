package org.dawnoftime.gardentrails;

import java.util.List;

public class GTConfig {
    public boolean generateChestLoot = true;
    public boolean generateSilk = true;
    public boolean generateGrapes = true;
    public boolean generateMaize = true;
    public boolean generateRice = true;
    public boolean generateMulberry = true;

    // Plant biome lists - empty list = plant does not spawn
    public List<String> boxwoodBushBiomes = List.of(
        "minecraft:forest", "minecraft:birch_forest",
        "minecraft:old_growth_birch_forest", "minecraft:windswept_forest"
    );
    public List<String> camelliaBiomes = List.of(
        "minecraft:jungle", "minecraft:sparse_jungle",
        "minecraft:bamboo_jungle", "minecraft:savanna", "minecraft:taiga"
    );
    public List<String> commelinaBiomes = List.of(
        "minecraft:swamp", "minecraft:mangrove_swamp", "minecraft:flower_forest"
    );
    public List<String> cypressBiomes = List.of(
        "minecraft:savanna", "minecraft:savanna_plateau", "minecraft:windswept_savanna"
    );
    public List<String> geraniumPinkBiomes = List.of(
        "minecraft:flower_forest", "minecraft:meadow",
        "minecraft:birch_forest", "minecraft:old_growth_birch_forest"
    );
    public List<String> geraniumOrangeBiomes = List.of(
        "minecraft:flower_forest", "minecraft:meadow",
        "minecraft:birch_forest", "minecraft:old_growth_birch_forest"
    );
    public List<String> geraniumPurpleBiomes = List.of(
        "minecraft:flower_forest", "minecraft:meadow",
        "minecraft:birch_forest", "minecraft:old_growth_birch_forest"
    );
    public List<String> geraniumPurpleishBiomes = List.of(
        "minecraft:flower_forest", "minecraft:meadow",
        "minecraft:birch_forest", "minecraft:old_growth_birch_forest"
    );
    public List<String> geraniumRedBiomes = List.of(
        "minecraft:flower_forest", "minecraft:meadow",
        "minecraft:birch_forest", "minecraft:old_growth_birch_forest"
    );
    public List<String> geraniumWhiteBiomes = List.of(
        "minecraft:flower_forest", "minecraft:meadow",
        "minecraft:birch_forest", "minecraft:old_growth_birch_forest"
    );
    public List<String> ivyBiomes = List.of(
        "minecraft:forest", "minecraft:birch_forest",
        "minecraft:old_growth_birch_forest", "minecraft:dark_forest"
    );
    public List<String> mulberryBiomes = List.of(
        "minecraft:taiga", "minecraft:old_growth_pine_taiga", "minecraft:old_growth_spruce_taiga"
    );
    public List<String> redMapleBiomes = List.of(
        "minecraft:birch_forest", "minecraft:old_growth_birch_forest", "minecraft:dark_forest"
    );
    public List<String> riceBiomes = List.of(
        "minecraft:swamp", "minecraft:mangrove_swamp"
    );
    public List<String> wildGrapeBiomes = List.of(
        "minecraft:forest", "minecraft:birch_forest", "minecraft:dark_forest"
    );
    public List<String> wildMaizeBiomes = List.of(
        "minecraft:savanna", "minecraft:savanna_plateau",
        "minecraft:windswept_savanna", "minecraft:plains"
    );

    public List<String> getBiomesForPlant(String plant) {
        return switch (plant) {
            case "boxwood_bush"       -> boxwoodBushBiomes;
            case "camellia"           -> camelliaBiomes;
            case "commelina"          -> commelinaBiomes;
            case "cypress"            -> cypressBiomes;
            case "geranium_pink"      -> geraniumPinkBiomes;
            case "geranium_orange"    -> geraniumOrangeBiomes;
            case "geranium_purple"    -> geraniumPurpleBiomes;
            case "geranium_purpleish" -> geraniumPurpleishBiomes;
            case "geranium_red"       -> geraniumRedBiomes;
            case "geranium_white"     -> geraniumWhiteBiomes;
            case "ivy"                -> ivyBiomes;
            case "mulberry"           -> mulberryBiomes;
            case "red_maple"          -> redMapleBiomes;
            case "rice"               -> riceBiomes;
            case "wild_grape"         -> wildGrapeBiomes;
            case "wild_maize"         -> wildMaizeBiomes;
            default -> {
                System.err.println("[GardenTrails] Unknown plant key in biome modifier: " + plant);
                yield List.of();
            }
        };
    }
}
