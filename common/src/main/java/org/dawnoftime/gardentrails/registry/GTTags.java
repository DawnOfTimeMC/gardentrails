package org.dawnoftime.gardentrails.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.dawnoftime.gardentrails.GTCommon;

public abstract class GTTags {
    public static GTTags INSTANCE;
    //Item tags
    public final TagKey<Item> LIGHTERS = registerItem(ResourceLocation.fromNamespaceAndPath(GTCommon.MOD_ID, "lighters"));
    //Block tags
    public final TagKey<Block> GRAVEL = registerBlock(ResourceLocation.fromNamespaceAndPath("c", "gravel"));


    public abstract TagKey<Block> registerBlock(ResourceLocation id);
    public abstract TagKey<Item> registerItem(ResourceLocation id);
}
