package org.dawnoftime.dawnoftime.loot;

import com.google.common.collect.ImmutableMap;
import net.minecraft.world.item.Item;
import org.dawnoftime.dawnoftime.DoTBConfig;
import org.dawnoftime.dawnoftime.platform.Services;
import org.dawnoftime.dawnoftime.registry.DoTBBlocksRegistry;
import org.dawnoftime.dawnoftime.registry.DoTBItemsRegistry;

import java.util.Map;

public final class LootTablesToModify {
    public static final String SHIPWRECK_TREASURE = "minecraft:chests/shipwreck_treasure";
    public static final String SHIPWRECK_SUPPLY = "minecraft:chests/shipwreck_supply";
    public static final String VILLAGE_PLAINS_HOUSE = "minecraft:chests/village/village_plains_house";
    public static final String VILLAGE_SAVANNA_HOUSE = "minecraft:chests/village/village_savanna_house";
    public static final String VILLAGE_TAIGA_HOUSE = "minecraft:chests/village/village_taiga_house";

    private static final DoTBConfig config = Services.PLATFORM.getConfig();

    public static final Map<Item, Boolean> SHOULD_ADD_MAP = ImmutableMap.ofEntries(
            Map.entry(DoTBItemsRegistry.INSTANCE.SILK.get(),             config.generateSilk),
            Map.entry(DoTBItemsRegistry.INSTANCE.GRAPE.get(),            config.generateGrapes),
            Map.entry(DoTBBlocksRegistry.INSTANCE.MAIZE.get().asItem(), config.generateMaize),
            Map.entry(DoTBBlocksRegistry.INSTANCE.RICE.get().asItem(),  config.generateRice),
            Map.entry(DoTBBlocksRegistry.INSTANCE.MULBERRY.get().asItem(), config.generateMulberry)
    );

    private LootTablesToModify() {}
}
