package org.dawnoftime.gardentrails.loot;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.Item;
import org.dawnoftime.gardentrails.GTConfig;
import org.dawnoftime.gardentrails.platform.Services;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;
import org.dawnoftime.gardentrails.registry.GTItemsRegistry;

import java.util.Map;

public final class LootTablesToModify {
    public static final String SHIPWRECK_TREASURE = "minecraft:chests/shipwreck_treasure";
    public static final String SHIPWRECK_SUPPLY = "minecraft:chests/shipwreck_supply";
    public static final String VILLAGE_PLAINS_HOUSE = "minecraft:chests/village/village_plains_house";
    public static final String VILLAGE_SAVANNA_HOUSE = "minecraft:chests/village/village_savanna_house";
    public static final String VILLAGE_TAIGA_HOUSE = "minecraft:chests/village/village_taiga_house";

    private static final GTConfig config = Services.PLATFORM.getConfig();

    public static final Map<Item, Boolean> SHOULD_ADD_MAP = ImmutableMap.ofEntries(
            Map.entry(GTItemsRegistry.INSTANCE.SILK.get(),             config.generateSilk),
            Map.entry(GTItemsRegistry.INSTANCE.GRAPE.get(),            config.generateGrapes),
            Map.entry(GTBlocksRegistry.INSTANCE.MAIZE.get().asItem(), config.generateMaize),
            Map.entry(GTBlocksRegistry.INSTANCE.RICE.get().asItem(),  config.generateRice),
            Map.entry(GTBlocksRegistry.INSTANCE.MULBERRY.get().asItem(), config.generateMulberry)
    );

    private LootTablesToModify() {}
}
