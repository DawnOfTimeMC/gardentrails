package org.dawnoftime.gardentrails.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.dawnoftime.gardentrails.item.IHasFlowerPot;
import org.dawnoftime.gardentrails.item.templates.ItemGT;
import org.dawnoftime.gardentrails.item.templates.PotItem;
import org.dawnoftime.gardentrails.util.Foods;

import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public abstract class GTItemsRegistry {
    public static GTItemsRegistry INSTANCE;

    // General
    public final Supplier<Item> GARDEN_TRAILS = register("garden_trails", ItemGT::new);

    // Silk chain
    public final Supplier<Item> SILK_WORMS = register("silk_worms",
        () -> new ItemGT(new Item.Properties(),
            "lore.gardentrails.silk_worms",
            "block.gardentrails.stick_bundle"));
    public final Supplier<Item> SILK_WORMS_HATCHERY = register("silk_worm_hatchery",
        () -> new ItemGT(new Item.Properties(),
            "lore.gardentrails.silk_worm_hatchery",
            "lore.gardentrails.name.drying_tray"));
    public final Supplier<Item> SILK_WORM_EGGS = register("silk_worm_eggs",
        () -> new ItemGT(new Item.Properties(),
            "lore.gardentrails.silk_worm_eggs",
            "item.gardentrails.mulberry_leaves"));
    public final Supplier<Item> SILK_COCOONS = register("silk_cocoons",
        () -> new ItemGT(new Item.Properties(),
            "lore.gardentrails.silk_cocoons",
            "item.gardentrails.silk",
            ChatFormatting.LIGHT_PURPLE));
    public final Supplier<Item> SILK = register("silk",
        () -> new ItemGT(new Item.Properties().rarity(Rarity.EPIC),
            "lore.gardentrails.silk",
            null));

    public final Supplier<Item> MULBERRY_LEAVES = register("mulberry_leaves",
        () -> new ItemGT(new Item.Properties(),
            "lore.gardentrails.mulberry_leaves",
            "item.gardentrails.silk_worm_eggs"));
    public final Supplier<Item> TEA_LEAVES = register("tea_leaves", ItemGT::new);
    public final Supplier<Item> CAMELLIA_LEAVES = register("camellia_leaves", ItemGT::new);
    public final Supplier<Item> GRAPE = register("grape", () -> new ItemGT(new Item.Properties().food(Foods.GRAPE)));
    public Supplier<Item> GRAPE_SEEDS;

    public void postRegister() {
        GRAPE_SEEDS = registerWithFlowerPot("grape_seeds",
            () -> new PotItem(new Item.Properties(), "lore.gardentrails.grape_seeds"));
    }

    public abstract <T extends Item> Supplier<Item> register(final String name, final Supplier<T> itemSupplier);
    public abstract <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(final String name, final Supplier<T> itemSupplier);
    public abstract <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(final String plantName, final String seedName, final Supplier<T> itemSupplier);

}
