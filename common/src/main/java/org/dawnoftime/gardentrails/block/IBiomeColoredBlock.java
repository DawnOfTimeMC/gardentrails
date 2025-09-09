package org.dawnoftime.gardentrails.block;

import net.minecraft.world.level.block.Block;

public interface IBiomeColoredBlock {
    enum ColorType {
        FOLIAGE,
        GRASS
    }
    
    ColorType getColorType();
    
    static boolean isBiomeColored(Block block) {
        return block instanceof IBiomeColoredBlock;
    }
}