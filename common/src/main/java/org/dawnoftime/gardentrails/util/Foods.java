package org.dawnoftime.gardentrails.util;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class Foods {
    public static final FoodProperties GRAPE = (new FoodProperties.Builder())
            .nutrition(3)
            .saturationMod(0.6f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0), 1.0f)
            .build();
    public static final FoodProperties MAIZE = (new FoodProperties.Builder())
            .nutrition(3)
            .saturationMod(0.6f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 100, 0), 1.0f)
            .build();
    public static final FoodProperties MULBERRY = (new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.5f)
            .fast()
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 100, 0), 1.0f)
            .build();
    public static final FoodProperties MULBERRY_JUICE = (new FoodProperties.Builder())
            .nutrition(2)
            .saturationMod(0.4f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 500, 0), 1.0f)
            .build();
    public static final FoodProperties RICE = (new FoodProperties.Builder())
            .nutrition(4)
            .saturationMod(0.6f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0), 1.0f)
            .build();
    public static final FoodProperties GRAPE_SEEDS = (new FoodProperties.Builder())
            .nutrition(1)
            .saturationMod(0.5f)
            .fast()
            .build();
    public static final FoodProperties DRIED_MAIZE = (new FoodProperties.Builder())
            .nutrition(6)
            .saturationMod(0.8f)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 900, 0), 1.0f)
            .build();
    public static final FoodProperties DRIED_GRAPE = (new FoodProperties.Builder())
            .nutrition(3)
            .saturationMod(0.6f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 900, 0), 1.0f)
            .build();
    public static final FoodProperties COOKED_RICE = (new FoodProperties.Builder())
            .nutrition(7)
            .saturationMod(1.0f)
            .build();
    public static final FoodProperties FERMENTED_RICE = (new FoodProperties.Builder())
            .nutrition(9)
            .saturationMod(1.5f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 500, 0), 1.0f)
            .build();
    public static final FoodProperties SAKE = (new FoodProperties.Builder())
            .nutrition(0)
            .saturationMod(0.0f)
            .alwaysEat()
            .build();
    public static final FoodProperties FERMENTED_MULBERRY_JUICE = (new FoodProperties.Builder())
            .nutrition(3)
            .saturationMod(0.6f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 800, 0), 1.0f)
            .build();
}
