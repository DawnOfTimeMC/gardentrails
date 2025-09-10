package org.dawnoftime.gardentrails;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.GrassColor;
import net.minecraft.world.level.block.Block;
import org.dawnoftime.gardentrails.block.IBiomeColoredBlock;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;

import java.util.function.Supplier;

public class BiomeColorHandlers {
    private static final float BRIGHTNESS_FACTOR = 1.5f;
    
    private static int brightenColor(int color) {
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = color & 0xFF;
        
        r = Math.min(255, (int)(r * BRIGHTNESS_FACTOR));
        g = Math.min(255, (int)(g * BRIGHTNESS_FACTOR));
        b = Math.min(255, (int)(b * BRIGHTNESS_FACTOR));
        
        return (r << 16) | (g << 8) | b;
    }
    
    public static void init() {
        registerBiomeColoredBlock(GTBlocksRegistry.INSTANCE.CYPRESS);
        registerBiomeColoredBlock(GTBlocksRegistry.INSTANCE.BOXWOOD_BUSH);
        registerBiomeColoredBlock(GTBlocksRegistry.INSTANCE.BOXWOOD_SMALL_HEDGE);
        registerBiomeColoredBlock(GTBlocksRegistry.INSTANCE.BOXWOOD_TALL_HEDGE);
        registerBiomeColoredBlock(() -> GTBlocksRegistry.POT_BLOCKS.get("cypress_flower_pot"));
    }
    
    private static void registerBiomeColoredBlock(Supplier<Block> blockSupplier) {
        Block block = blockSupplier.get();
        if (IBiomeColoredBlock.isBiomeColored(block)) {
            IBiomeColoredBlock coloredBlock = (IBiomeColoredBlock) block;
            
            BlockColor blockColor = (state, world, pos, tintIndex) -> {
                if (world != null && pos != null && tintIndex == 0) {
                    int color = coloredBlock.getColorType() == IBiomeColoredBlock.ColorType.FOLIAGE
                        ? BiomeColors.getAverageFoliageColor(world, pos)
                        : BiomeColors.getAverageGrassColor(world, pos);
                    return brightenColor(color);
                }
                int defaultColor = coloredBlock.getColorType() == IBiomeColoredBlock.ColorType.FOLIAGE
                    ? FoliageColor.getDefaultColor()
                    : GrassColor.getDefaultColor();
                return brightenColor(defaultColor);
            };
            
            ItemColor itemColor = (stack, tintIndex) -> {
                if (tintIndex == 0) {
                    int color = coloredBlock.getColorType() == IBiomeColoredBlock.ColorType.FOLIAGE
                        ? FoliageColor.getDefaultColor()
                        : GrassColor.getDefaultColor();
                    return brightenColor(color);
                }
                return 0xFFFFFF;
            };
            
            ColorProviderRegistry.BLOCK.register(blockColor, block);
            ColorProviderRegistry.ITEM.register(itemColor, block.asItem());
        }
    }
}