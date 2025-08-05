package org.dawnoftime.dawnoftime.client.gui.creative;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import org.dawnoftime.dawnoftime.registry.DoTBBlocksRegistry;
import org.dawnoftime.dawnoftime.registry.DoTBItemsRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.dawnoftime.dawnoftime.DoTBCommon.MOD_ID;

public enum CreativeInventoryCategories {
    GENERAL("general",
            DoTBBlocksRegistry.INSTANCE.BOXWOOD_BUSH.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.BOXWOOD_SMALL_HEDGE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.BOXWOOD_TALL_HEDGE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.GERANIUM_PINK.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.PLANTER_GERANIUM_PINK.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.IVY.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.MAPLE_RED_SAPLING.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.CAMELLIA.get().asItem(),
            DoTBItemsRegistry.INSTANCE.CAMELLIA_LEAVES.get(),
            DoTBItemsRegistry.INSTANCE.TEA_LEAVES.get(),
            DoTBBlocksRegistry.INSTANCE.BAMBOO_DRYING_TRAY.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.STICK_BUNDLE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.MULBERRY.get().asItem(),
            DoTBItemsRegistry.INSTANCE.MULBERRY_LEAVES.get(),
            DoTBItemsRegistry.INSTANCE.SILK_WORM_EGGS.get(),
            DoTBItemsRegistry.INSTANCE.SILK_WORMS_HATCHERY.get(),
            DoTBItemsRegistry.INSTANCE.SILK_WORMS.get(),
            DoTBItemsRegistry.INSTANCE.SILK_COCOONS.get(),
            DoTBItemsRegistry.INSTANCE.SILK.get(),
            DoTBBlocksRegistry.INSTANCE.RICE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.CYPRESS.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.WILD_GRAPE.get().asItem(),
            DoTBItemsRegistry.INSTANCE.GRAPE.get(),
            DoTBItemsRegistry.INSTANCE.GRAPE_SEEDS.get(),
            DoTBBlocksRegistry.INSTANCE.COMMELINA.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.WILD_MAIZE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.MAIZE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.LATTICE_GLASS_PANE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.WAXED_OAK_TABLE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.WAXED_OAK_CHAIR.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.STONE_BRICKS_POOL.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.STONE_BRICKS_SMALL_POOL.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.STONE_BRICKS_FAUCET.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.STONE_BRICKS_WATER_JET.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.WATER_SOURCE_TRICKLE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.SPRUCE_LOW_TABLE.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.SPRUCE_LEGLESS_CHAIR.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.IKEBANA_FLOWER_POT.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.BIG_FLOWER_POT.get().asItem(),
            DoTBBlocksRegistry.INSTANCE.MARBLE_BIG_FLOWER_POT.get().asItem()
    );

    private final String name;
    private final Component translation;
    private final ArrayList<Item> items = new ArrayList<>();

    CreativeInventoryCategories(String name, Item... items) {
        this.name = name;
        this.translation = Component.translatable("gui." + MOD_ID + "." + name);
        this.items.addAll(Arrays.asList(items));
    }

    public String getName() {
        return this.name;
    }

    public Component getTranslation() {
        return this.translation;
    }

    public List<Item> getItems() {
        return this.items;
    }
}