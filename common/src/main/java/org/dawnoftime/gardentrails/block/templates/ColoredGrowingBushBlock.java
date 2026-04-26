package org.dawnoftime.gardentrails.block.templates;

import org.dawnoftime.gardentrails.block.IBiomeColoredBlock;

public class ColoredGrowingBushBlock extends GrowingBushBlock implements IBiomeColoredBlock {

    public ColoredGrowingBushBlock(PlantType plantType, int cutAge) {
        super(plantType, cutAge);
    }

    @Override
    public ColorType getColorType() {
        return ColorType.FOLIAGE;
    }
}
