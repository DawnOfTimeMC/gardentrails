package org.dawnoftime.gardentrails.block.templates;

import org.dawnoftime.gardentrails.block.IBiomeColoredBlock;

public class BiomeColoredPlateBlock extends PlateBlock implements IBiomeColoredBlock {
    private final ColorType colorType;
    
    public BiomeColoredPlateBlock(Properties properties, ColorType colorType) {
        super(properties);
        this.colorType = colorType;
    }
    
    @Override
    public ColorType getColorType() {
        return colorType;
    }
}