package org.dawnoftime.gardentrails.item;

import org.dawnoftime.gardentrails.block.templates.FlowerPotBlockGT;

import javax.annotation.Nullable;

public interface IHasFlowerPot {
    /**
     * @return the instance of the PotBlock. Null if there is no PotBlock associated.
     */
    @Nullable
    FlowerPotBlockGT getPotBlock();

    /**
     * Used in registration to bind this item to its PotBlock.
     *
     * @param pot to be associated with this item.
     */
    void setPotBlock(@Nullable FlowerPotBlockGT pot);
}
