package org.dawnoftime.gardentrails.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public abstract class GTTags {
    public static GTTags INSTANCE;
    //Block tags
    public final TagKey<Block> GRAVEL = registerBlock(ResourceLocation.fromNamespaceAndPath("c", "gravel"));

    public abstract TagKey<Block> registerBlock(ResourceLocation id);
    public abstract TagKey<Item> registerItem(ResourceLocation id);
}
