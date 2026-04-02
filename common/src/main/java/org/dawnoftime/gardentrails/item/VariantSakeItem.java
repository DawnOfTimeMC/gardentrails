package org.dawnoftime.gardentrails.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.dawnoftime.gardentrails.item.templates.ItemGT;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class VariantSakeItem extends ItemGT {

    private final MobEffect variantEffect;
    private final String loreKey;

    public VariantSakeItem(Properties properties, MobEffect variantEffect, String loreKey) {
        super(properties);
        this.variantEffect = variantEffect;
        this.loreKey = loreKey;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(Component.translatable(loreKey).withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide && entity instanceof Player player) {
            boolean hasSlowness   = player.hasEffect(MobEffects.MOVEMENT_SLOWDOWN);
            boolean hasPoison     = player.hasEffect(MobEffects.POISON);
            boolean hasNausea     = player.hasEffect(MobEffects.CONFUSION);
            boolean hasResistance = player.hasEffect(MobEffects.DAMAGE_RESISTANCE);
            boolean hasHunger     = player.hasEffect(MobEffects.HUNGER);

            if (hasPoison && hasNausea && hasHunger) {
                // 6th drink — variant buff II 200s + Resistance I 60s
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER,            900, 0)); // Hunger I 45s
                player.addEffect(new MobEffectInstance(MobEffects.HARM,                1, 2)); // Instant Damage III
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0)); // Resistance I 60s
                player.addEffect(new MobEffectInstance(variantEffect,                4000, 1)); // Buff II 200s

            } else if (hasPoison && hasNausea && hasResistance) {
                // 5th drink
                player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0)); // Hunger I 10s
                player.addEffect(new MobEffectInstance(MobEffects.HARM,     1, 1)); // Instant Damage II

            } else if (hasPoison && hasNausea) {
                // 4th drink — variant buff I 120s + Resistance I 40s
                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 800, 0));  // Resistance I 40s
                player.addEffect(new MobEffectInstance(variantEffect,                2400, 0)); // Buff I 120s

            } else if (hasSlowness && hasPoison) {
                // 3rd drink
                player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0)); // Nausea I 15s

            } else if (hasSlowness) {
                // 2nd drink
                player.addEffect(new MobEffectInstance(MobEffects.POISON, 900, 0)); // Poison I 45s

            } else {
                // 1st drink — variant buff I 50s
                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 500, 0)); // Slowness I 25s
                player.addEffect(new MobEffectInstance(variantEffect,                1000, 0)); // Buff I 50s
            }
        }

        return result;
    }
}
