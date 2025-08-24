package org.dawnoftime.gardentrails.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.dawnoftime.gardentrails.GTCommon;

import java.util.function.Supplier;

public abstract class GTCreativeModeTabsRegistry {
    public static GTCreativeModeTabsRegistry INSTANCE;
    public Supplier<CreativeModeTab> GT_TAB = register("gt_tab", () -> new ItemStack(GTItemsRegistry.INSTANCE.GARDEN_TRAILS.get()), Component.translatable("itemGroup." + GTCommon.MOD_ID + ".gttab"));
    public abstract <T extends CreativeModeTab> Supplier<CreativeModeTab> register(final String name, final Supplier<ItemStack> iconSupplier, final Component title);
}
