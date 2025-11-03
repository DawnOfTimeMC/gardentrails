package org.dawnoftime.gardentrails.recipe;

import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeInput;

public record SimpleContainerRecipeInput(SimpleContainer container) implements RecipeInput {
    @Override
    public ItemStack getItem(int i) {
        return container.getItem(i);
    }

    @Override
    public int size() {
        return container.getContainerSize();
    }
}
