package org.dawnoftime.gardentrails.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import org.dawnoftime.gardentrails.GTCommon;
import org.dawnoftime.gardentrails.loot.GTLootModifiersNeoForge;
import org.dawnoftime.gardentrails.loot.LootTablesToModify;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;
import org.dawnoftime.gardentrails.registry.GTItemsRegistry;

import java.util.concurrent.CompletableFuture;

public class GTLootModifierProvider extends GlobalLootModifierProvider {
    public GTLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, GTCommon.MOD_ID);
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

    private GTLootModifiersNeoForge buildLootTable(String lootTableName, Item item, float probability) {
        return new GTLootModifiersNeoForge(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(ResourceLocation.tryParse(lootTableName)).build(),
                        LootItemRandomChanceCondition.randomChance(probability).build()
                },
                item
        );
    }
}
