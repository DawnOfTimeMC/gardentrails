package org.dawnoftime.gardentrails.registry;

import net.minecraft.world.item.Item;
import org.dawnoftime.gardentrails.item.IHasFlowerPot;
import org.dawnoftime.gardentrails.item.templates.ItemGT;
import org.dawnoftime.gardentrails.item.templates.PotItem;
import org.dawnoftime.gardentrails.util.Foods;

import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public abstract class GTItemsRegistry {
    public static GTItemsRegistry INSTANCE;

    // General
    public final Supplier<Item> GARDEN_TRAILS = register("garden_trails", () -> new ItemGT(false));
    public final Supplier<Item> SILK_WORMS = register("silk_worms", () -> new ItemGT(true));
    public final Supplier<Item> SILK_WORMS_HATCHERY = register("silk_worm_hatchery", () -> new ItemGT(true));
    public final Supplier<Item> SILK_WORM_EGGS = register("silk_worm_eggs", () -> new ItemGT(true));
    public final Supplier<Item> SILK_COCOONS = register("silk_cocoons", () -> new ItemGT(true));
    public final Supplier<Item> SILK = register("silk", ItemGT::new);
    public final Supplier<Item> TEA_LEAVES = register("tea_leaves", ItemGT::new);
    public final Supplier<Item> CAMELLIA_LEAVES = register("camellia_leaves", ItemGT::new);
    public final Supplier<Item> MULBERRY_LEAVES = register("mulberry_leaves", ItemGT::new);
    public final Supplier<Item> GRAPE = register("grape", () -> new ItemGT(new Item.Properties().food(Foods.GRAPE)));
    public Supplier<Item> GRAPE_SEEDS;

    public void postRegister() {
        GRAPE_SEEDS = registerWithFlowerPot("grape_seeds", PotItem::new);
    }

    public abstract <T extends Item> Supplier<Item> register(final String name, final Supplier<T> itemSupplier);
    public abstract <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(final String name, final Supplier<T> itemSupplier);
    public abstract <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(final String plantName, final String seedName, final Supplier<T> itemSupplier);

}
