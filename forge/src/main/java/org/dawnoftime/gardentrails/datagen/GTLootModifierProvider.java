package org.dawnoftime.gardentrails.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import org.dawnoftime.gardentrails.GTCommon;
import org.dawnoftime.gardentrails.GTConfig;
import org.dawnoftime.gardentrails.loot.GTLootModifiersForge;
import org.dawnoftime.gardentrails.loot.LootTablesToModify;
import org.dawnoftime.gardentrails.platform.Services;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;
import org.dawnoftime.gardentrails.registry.GTItemsRegistry;

public class GTLootModifierProvider extends GlobalLootModifierProvider {
    private static final GTConfig config = Services.PLATFORM.getConfig();

    public GTLootModifierProvider(PackOutput output) {
        super(output, GTCommon.MOD_ID);
    }

    @Override
    protected void start() {
        add("silk_in_shipwreck_treasure",
                buildLootTable(LootTablesToModify.SHIPWRECK_TREASURE,
                        GTItemsRegistry.INSTANCE.SILK.get(),
                        1.0f));

        add("grape_in_village_plains_house",
                buildLootTable(LootTablesToModify.VILLAGE_PLAINS_HOUSE,
                        GTItemsRegistry.INSTANCE.GRAPE.get(),
                        0.5f));

        add("maize_in_village_savanna_house",
                buildLootTable(LootTablesToModify.VILLAGE_SAVANNA_HOUSE,
                        GTBlocksRegistry.INSTANCE.MAIZE.get().asItem(),
                        0.5f));

        add("rice_in_village_taiga_house",
                buildLootTable(LootTablesToModify.VILLAGE_TAIGA_HOUSE,
                        GTBlocksRegistry.INSTANCE.RICE.get().asItem(),
                        0.5f));

        add("mulberry_in_village_taiga_house",
                buildLootTable(LootTablesToModify.VILLAGE_TAIGA_HOUSE,
                        GTBlocksRegistry.INSTANCE.MULBERRY.get().asItem(),
                        0.5f));

        add("grape_in_shipwreck_supply",
                buildLootTable(LootTablesToModify.SHIPWRECK_SUPPLY,
                        GTItemsRegistry.INSTANCE.GRAPE.get(),
                        0.2f));

        add("maize_in_shipwreck_supply",
                buildLootTable(LootTablesToModify.SHIPWRECK_SUPPLY,
                        GTBlocksRegistry.INSTANCE.MAIZE.get().asItem(),
                        0.2f));

        add("rice_in_shipwreck_supply",
                buildLootTable(LootTablesToModify.SHIPWRECK_SUPPLY,
                        GTBlocksRegistry.INSTANCE.RICE.get().asItem(),
                        0.2f));

        add("mulberry_in_shipwreck_supply",
                buildLootTable(LootTablesToModify.SHIPWRECK_SUPPLY,
                        GTBlocksRegistry.INSTANCE.MULBERRY.get().asItem(),
                        0.2f));
    }

    private GTLootModifiersForge buildLootTable(String lootTableName, Item item, float probability) {
        return new GTLootModifiersForge(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation(lootTableName)).build(),
                        LootItemRandomChanceCondition.randomChance(probability).build()
                },
                item
        );
    }
}
