package org.dawnoftime.gardentrails.block.templates;

import net.minecraft.world.phys.shapes.VoxelShape;
import org.dawnoftime.gardentrails.block.IBiomeColoredBlock;

public class BiomeColoredBushBlock extends BushBlockGT implements IBiomeColoredBlock {
    private final ColorType colorType;
    
    public BiomeColoredBushBlock(Properties properties, VoxelShape[] shapes, ColorType colorType) {
        super(properties, shapes);
        this.colorType = colorType;
    }
    
    @Override
    public ColorType getColorType() {
        return colorType;
    }
}