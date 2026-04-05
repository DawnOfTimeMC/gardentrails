package org.dawnoftime.gardentrails.registry;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.effect.MobEffects;
import org.dawnoftime.gardentrails.item.DrinkItem;
import org.dawnoftime.gardentrails.item.IHasFlowerPot;
import org.dawnoftime.gardentrails.item.SakeItem;
import org.dawnoftime.gardentrails.item.VariantSakeItem;
import org.dawnoftime.gardentrails.item.templates.ItemGT;
import org.dawnoftime.gardentrails.item.templates.PotItem;
import org.dawnoftime.gardentrails.util.Foods;

import java.util.function.Supplier;

@SuppressWarnings({"unused"})
public abstract class GTItemsRegistry {
    public static GTItemsRegistry INSTANCE;

    // General
    public final Supplier<Item> GARDEN_TRAILS = register("garden_trails", ItemGT::new);
    public final Supplier<Item> SILK_WORMS = register("silk_worms", () -> new ItemGT(new Item.Properties(), "lore.gardentrails.silk_worms", "block.gardentrails.stick_bundle"));
    public final Supplier<Item> SILK_WORMS_HATCHERY = register("silk_worm_hatchery", () -> new ItemGT(new Item.Properties(), "lore.gardentrails.silk_worm_hatchery", "lore.gardentrails.name.drying_tray"));
    public final Supplier<Item> SILK_WORM_EGGS = register("silk_worm_eggs", () -> new ItemGT(new Item.Properties(), "lore.gardentrails.silk_worm_eggs", "item.gardentrails.mulberry_leaves"));
    public final Supplier<Item> SILK_COCOONS = register("silk_cocoons", () -> new ItemGT(new Item.Properties(), "lore.gardentrails.silk_cocoons", "item.gardentrails.silk", ChatFormatting.LIGHT_PURPLE));
    public final Supplier<Item> SILK = register("silk", () -> new ItemGT(new Item.Properties().rarity(Rarity.EPIC), "lore.gardentrails.silk", null));
    public final Supplier<Item> TEA_LEAVES = register("tea_leaves", ItemGT::new);
    public final Supplier<Item> CAMELLIA_LEAVES = register("camellia_leaves", ItemGT::new);
    public final Supplier<Item> MULBERRY_LEAVES = register("mulberry_leaves", () -> new ItemGT(new Item.Properties(), "lore.gardentrails.mulberry_leaves", "item.gardentrails.silk_worm_eggs"));
    public final Supplier<Item> GRAPE = register("grape", () -> new ItemGT(new Item.Properties().food(Foods.GRAPE)));
    public final Supplier<Item> DRIED_MAIZE = register("dried_maize", () -> new ItemGT(new Item.Properties().food(Foods.DRIED_MAIZE)));
    public final Supplier<Item> DRIED_GRAPE = register("dried_grape", () -> new ItemGT(new Item.Properties().food(Foods.DRIED_GRAPE)));
    public final Supplier<Item> COOKED_RICE = register("cooked_rice", () -> new ItemGT(new Item.Properties().food(Foods.COOKED_RICE)));
    public final Supplier<Item> FERMENTED_RICE = register("fermented_rice", () -> new ItemGT(new Item.Properties().food(Foods.FERMENTED_RICE)));
    public final Supplier<Item> CRUSHED_MAIZE = register("crushed_maize", ItemGT::new);
    public final Supplier<Item> CRUSHED_GRAPE = register("crushed_grape", ItemGT::new);
    public final Supplier<Item> MULBERRY_JUICE = register("mulberry_juice", () -> new DrinkItem(new Item.Properties().food(Foods.MULBERRY_JUICE)));
    public final Supplier<Item> FERMENTED_MULBERRY_JUICE = register("fermented_mulberry_juice", () -> new DrinkItem(new Item.Properties().food(Foods.FERMENTED_MULBERRY_JUICE)));
    public final Supplier<Item> SAKE = register("sake_bottle", () -> new SakeItem(new Item.Properties().food(Foods.SAKE)));
    public final Supplier<Item> SAKE_GRAPE    = register("sake_bottle_grape",    () -> new VariantSakeItem(new Item.Properties().food(Foods.SAKE).rarity(Rarity.RARE), MobEffects.MOVEMENT_SPEED,  "lore.gardentrails.sake_bottle_grape"));
    public final Supplier<Item> SAKE_MAIZE    = register("sake_bottle_maize",    () -> new VariantSakeItem(new Item.Properties().food(Foods.SAKE).rarity(Rarity.RARE), MobEffects.DAMAGE_BOOST,     "lore.gardentrails.sake_bottle_maize"));
    public final Supplier<Item> SAKE_MULBERRY = register("sake_bottle_mulberry", () -> new VariantSakeItem(new Item.Properties().food(Foods.SAKE).rarity(Rarity.RARE), MobEffects.REGENERATION,    "lore.gardentrails.sake_bottle_mulberry"));
    public Supplier<Item> GRAPE_SEEDS;

    public void postRegister() {
        GRAPE_SEEDS = registerWithFlowerPot("grape_seeds", () -> new PotItem(new Item.Properties().food(Foods.GRAPE_SEEDS), "lore.gardentrails.grape_seeds"));
    }

    public abstract <T extends Item> Supplier<Item> register(final String name, final Supplier<T> itemSupplier);
    public abstract <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(final String name, final Supplier<T> itemSupplier);
    public abstract <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(final String plantName, final String seedName, final Supplier<T> itemSupplier);

}
