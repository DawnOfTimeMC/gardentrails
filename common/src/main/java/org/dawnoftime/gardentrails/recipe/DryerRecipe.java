package org.dawnoftime.gardentrails.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;
import org.dawnoftime.gardentrails.registry.GTRecipeSerializersRegistry;
import org.dawnoftime.gardentrails.registry.GTRecipeTypesRegistry;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public record DryerRecipe(String group, Ingredient ingredient, ItemStack result, float experience,
                          int dryingTime) implements Recipe<SimpleContainerRecipeInput> {

    @Override
    public @NotNull String getGroup() {
        return this.group;
    }

    @Override
    public boolean matches(SimpleContainerRecipeInput inv, @NotNull Level worldIn) {
        return this.ingredient.test(inv.getItem(0)) && inv.getItem(0).getCount() >= this.ingredient.getItems()[0].getCount();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    @Override
    public ItemStack assemble(SimpleContainerRecipeInput simpleContainer, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return GTRecipeSerializersRegistry.INSTANCE.DRYER.get();
    }

    @Override
    @Nonnull
    public RecipeType<?> getType() {
        return GTRecipeTypesRegistry.INSTANCE.DRYING.get();
    }

    @Override
    @Nonnull
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(this.ingredient);
        return list;
    }

    @Override
    public @NotNull ItemStack getToastSymbol() {
        return new ItemStack(GTBlocksRegistry.INSTANCE.BAMBOO_DRYING_TRAY.get());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
