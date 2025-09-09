package org.dawnoftime.gardentrails.block.templates;

import org.dawnoftime.gardentrails.block.IBiomeColoredBlock;

public class BiomeColoredEdgeBlock extends EdgeBlock implements IBiomeColoredBlock {
    private final ColorType colorType;
    
    public BiomeColoredEdgeBlock(Properties properties, ColorType colorType) {
        super(properties);
        this.colorType = colorType;
    }
    
    @Override
    public ColorType getColorType() {
        return colorType;
    }
}