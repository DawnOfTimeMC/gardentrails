package org.dawnoftime.gardentrails.registry;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.dawnoftime.gardentrails.recipe.DryerRecipe;

import java.util.function.Supplier;

public abstract class GTRecipeTypesRegistry {
    public static GTRecipeTypesRegistry INSTANCE;

    public final Supplier<RecipeType<DryerRecipe>> DRYING = register("drying");

    public abstract <T extends Recipe<?>> Supplier<RecipeType<T>> register(String name);
}
