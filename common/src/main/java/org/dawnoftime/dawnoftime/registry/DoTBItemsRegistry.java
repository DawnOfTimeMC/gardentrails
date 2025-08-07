package org.dawnoftime.dawnoftime.registry;

import net.minecraft.world.item.Item;
import org.dawnoftime.dawnoftime.item.IHasFlowerPot;
import org.dawnoftime.dawnoftime.item.templates.ItemDoTB;
import org.dawnoftime.dawnoftime.item.templates.PotItem;
import org.dawnoftime.dawnoftime.util.Foods;

import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public abstract class DoTBItemsRegistry {
    public static DoTBItemsRegistry INSTANCE;

    // General
    public final Supplier<Item> ANCIENTARCHI = register("ancientarchi", () -> new ItemDoTB(false));
    public final Supplier<Item> SILK_WORMS = register("silk_worms", () -> new ItemDoTB(true));
    public final Supplier<Item> SILK_WORMS_HATCHERY = register("silk_worm_hatchery", () -> new ItemDoTB(true));
    public final Supplier<Item> SILK_WORM_EGGS = register("silk_worm_eggs", () -> new ItemDoTB(true));
    public final Supplier<Item> SILK_COCOONS = register("silk_cocoons", () -> new ItemDoTB(true));
    public final Supplier<Item> SILK = register("silk", ItemDoTB::new);
    public final Supplier<Item> TEA_LEAVES = register("tea_leaves", ItemDoTB::new);
    public final Supplier<Item> CAMELLIA_LEAVES = register("camellia_leaves", ItemDoTB::new);
    public final Supplier<Item> MULBERRY_LEAVES = register("mulberry_leaves", ItemDoTB::new);
    public final Supplier<Item> GRAPE = register("grape", () -> new ItemDoTB(new Item.Properties().food(Foods.GRAPE)));
    public Supplier<Item> GRAPE_SEEDS;

    public void postRegister() {
        GRAPE_SEEDS = registerWithFlowerPot("grape_seeds", PotItem::new);
    }

    public abstract <T extends Item> Supplier<Item> register(final String name, final Supplier<T> itemSupplier);
    public abstract <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(final String name, final Supplier<T> itemSupplier);
    public abstract <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(final String plantName, final String seedName, final Supplier<T> itemSupplier);

}
