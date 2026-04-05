package org.dawnoftime.gardentrails.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.dawnoftime.gardentrails.GTCommon;

import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class GTCreativeModeTabsRegistry {
    public static GTCreativeModeTabsRegistry INSTANCE;
    public Supplier<CreativeModeTab> GT_TAB = register("gt_tab", () -> new ItemStack(GTItemsRegistry.INSTANCE.GARDEN_TRAILS.get()), Component.translatable("itemGroup." + GTCommon.MOD_ID + ".gttab"));
    public abstract <T extends CreativeModeTab> Supplier<CreativeModeTab> register(final String name, final Supplier<ItemStack> iconSupplier, final Component title);

    public static void addOrderedItems(Consumer<ItemLike> output) {
        // ── Drying Trays ──────────────────────────────────────────────────────
        output.accept(GTBlocksRegistry.INSTANCE.BAMBOO_DRYING_TRAY.get());
        output.accept(GTBlocksRegistry.INSTANCE.OAK_DRYING_TRAY.get());
        output.accept(GTBlocksRegistry.INSTANCE.BIRCH_DRYING_TRAY.get());
        output.accept(GTBlocksRegistry.INSTANCE.SPRUCE_DRYING_TRAY.get());
        output.accept(GTBlocksRegistry.INSTANCE.JUNGLE_DRYING_TRAY.get());
        output.accept(GTBlocksRegistry.INSTANCE.ACACIA_DRYING_TRAY.get());
        output.accept(GTBlocksRegistry.INSTANCE.DARK_OAK_DRYING_TRAY.get());
        output.accept(GTBlocksRegistry.INSTANCE.MANGROVE_DRYING_TRAY.get());
        output.accept(GTBlocksRegistry.INSTANCE.CHERRY_DRYING_TRAY.get());

        // ── Pergola ───────────────────────────────────────────────────────────
        output.accept(GTBlocksRegistry.INSTANCE.IRON_PERGOLA.get());
        output.accept(GTBlocksRegistry.INSTANCE.COPPER_PERGOLA.get());
        output.accept(GTBlocksRegistry.INSTANCE.OXIDIZED_COPPER_PERGOLA.get());

        // ── Plantes + items associés ──────────────────────────────────────────
        output.accept(GTBlocksRegistry.INSTANCE.CAMELLIA.get());
        output.accept(GTItemsRegistry.INSTANCE.CAMELLIA_LEAVES.get());
        output.accept(GTItemsRegistry.INSTANCE.TEA_LEAVES.get());

        output.accept(GTBlocksRegistry.INSTANCE.MULBERRY.get());
        output.accept(GTItemsRegistry.INSTANCE.MULBERRY_LEAVES.get());
        output.accept(GTItemsRegistry.INSTANCE.MULBERRY_JUICE.get());
        output.accept(GTItemsRegistry.INSTANCE.FERMENTED_MULBERRY_JUICE.get());

        output.accept(GTBlocksRegistry.INSTANCE.RICE.get());
        output.accept(GTItemsRegistry.INSTANCE.COOKED_RICE.get());
        output.accept(GTItemsRegistry.INSTANCE.FERMENTED_RICE.get());

        output.accept(GTBlocksRegistry.INSTANCE.WILD_MAIZE.get());
        output.accept(GTBlocksRegistry.INSTANCE.MAIZE.get());
        output.accept(GTItemsRegistry.INSTANCE.DRIED_MAIZE.get());
        output.accept(GTItemsRegistry.INSTANCE.CRUSHED_MAIZE.get());

        output.accept(GTBlocksRegistry.INSTANCE.WILD_GRAPE.get());
        output.accept(GTItemsRegistry.INSTANCE.GRAPE_SEEDS.get());
        output.accept(GTItemsRegistry.INSTANCE.GRAPE.get());
        output.accept(GTItemsRegistry.INSTANCE.DRIED_GRAPE.get());
        output.accept(GTItemsRegistry.INSTANCE.CRUSHED_GRAPE.get());

        output.accept(GTBlocksRegistry.INSTANCE.BOXWOOD_BUSH.get());
        output.accept(GTBlocksRegistry.INSTANCE.BOXWOOD_SMALL_HEDGE.get());
        output.accept(GTBlocksRegistry.INSTANCE.BOXWOOD_TALL_HEDGE.get());

        output.accept(GTBlocksRegistry.INSTANCE.COMMELINA.get());
        output.accept(GTBlocksRegistry.INSTANCE.CYPRESS.get());
        output.accept(GTBlocksRegistry.INSTANCE.GERANIUM_PINK.get());
        output.accept(GTBlocksRegistry.INSTANCE.IVY.get());
        output.accept(GTBlocksRegistry.INSTANCE.MAPLE_RED_SAPLING.get());

        // ── Sake ──────────────────────────────────────────────────────────────
        output.accept(GTItemsRegistry.INSTANCE.SAKE.get());
        output.accept(GTItemsRegistry.INSTANCE.SAKE_GRAPE.get());
        output.accept(GTItemsRegistry.INSTANCE.SAKE_MAIZE.get());
        output.accept(GTItemsRegistry.INSTANCE.SAKE_MULBERRY.get());

        // ── Soie ──────────────────────────────────────────────────────────────
        output.accept(GTItemsRegistry.INSTANCE.SILK_WORM_EGGS.get());
        output.accept(GTItemsRegistry.INSTANCE.SILK_WORMS_HATCHERY.get());
        output.accept(GTItemsRegistry.INSTANCE.SILK_WORMS.get());
        output.accept(GTItemsRegistry.INSTANCE.SILK_COCOONS.get());
        output.accept(GTItemsRegistry.INSTANCE.SILK.get());
        output.accept(GTBlocksRegistry.INSTANCE.STICK_BUNDLE.get());

        // ── Pots ──────────────────────────────────────────────────────────────
        output.accept(GTBlocksRegistry.INSTANCE.PLANTER_GERANIUM_PINK.get());
        output.accept(GTBlocksRegistry.POT_BLOCKS.get("geranium_pink_flower_pot"));
        output.accept(GTBlocksRegistry.POT_BLOCKS.get("camellia_flower_pot"));
        output.accept(GTBlocksRegistry.POT_BLOCKS.get("mulberry_flower_pot"));
        output.accept(GTBlocksRegistry.POT_BLOCKS.get("commelina_flower_pot"));
        output.accept(GTBlocksRegistry.POT_BLOCKS.get("cypress_flower_pot"));
        output.accept(GTBlocksRegistry.POT_BLOCKS.get("ivy_flower_pot"));
        output.accept(GTBlocksRegistry.POT_BLOCKS.get("grape_seeds_flower_pot"));
        output.accept(GTBlocksRegistry.POT_BLOCKS.get("maize_flower_pot"));
        output.accept(GTBlocksRegistry.POT_BLOCKS.get("maple_red_sapling_flower_pot"));
    }
}
