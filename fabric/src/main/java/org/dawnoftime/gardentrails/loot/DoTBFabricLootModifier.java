package org.dawnoftime.gardentrails.loot;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import org.dawnoftime.gardentrails.GTConfig;
import org.dawnoftime.gardentrails.platform.Services;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;
import org.dawnoftime.gardentrails.registry.GTItemsRegistry;

public final class DoTBFabricLootModifier {
    private static final GTConfig config = Services.PLATFORM.getConfig();

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            switch (id.toString()) {
                case LootTablesToModify.SHIPWRECK_TREASURE -> buildLootTable(GTItemsRegistry.INSTANCE.SILK.get(), 1.0f, tableBuilder);
                case LootTablesToModify.VILLAGE_PLAINS_HOUSE -> buildLootTable(GTItemsRegistry.INSTANCE.GRAPE.get(), 0.5f, tableBuilder);
                case LootTablesToModify.VILLAGE_SAVANNA_HOUSE -> buildLootTable(GTBlocksRegistry.INSTANCE.MAIZE.get().asItem(), 0.5f, tableBuilder);
                case LootTablesToModify.VILLAGE_TAIGA_HOUSE -> {
                    buildLootTable(GTBlocksRegistry.INSTANCE.RICE.get().asItem(), 0.5f, tableBuilder);
                    buildLootTable(GTBlocksRegistry.INSTANCE.MULBERRY.get().asItem(), 0.5f, tableBuilder);
                }
                case LootTablesToModify.SHIPWRECK_SUPPLY -> {
                    buildLootTable(GTItemsRegistry.INSTANCE.GRAPE.get(),0.2f, tableBuilder);
                    buildLootTable(GTBlocksRegistry.INSTANCE.MAIZE.get().asItem(), 0.2f, tableBuilder);
                    buildLootTable(GTBlocksRegistry.INSTANCE.RICE.get().asItem(), 0.2f, tableBuilder);
                    buildLootTable(GTBlocksRegistry.INSTANCE.MULBERRY.get().asItem(), 0.2f, tableBuilder);
                }
            }
        });
    }

    private static void buildLootTable(Item item, float probability, LootTable.Builder tableBuilder) {
        boolean shouldAdd = LootTablesToModify.SHOULD_ADD_MAP.getOrDefault(item, false);

        if (config.generateChestLoot && shouldAdd) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .conditionally(LootItemRandomChanceCondition.randomChance(probability).build());

            poolBuilder.with(LootItem.lootTableItem(item)
                            .setWeight(1)
                            .build())
                    .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1f)));

            tableBuilder.pool(poolBuilder.build());
        }
    }
}
