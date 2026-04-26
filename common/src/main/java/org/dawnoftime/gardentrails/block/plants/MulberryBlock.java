package org.dawnoftime.gardentrails.block.plants;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import org.dawnoftime.gardentrails.block.templates.DoubleGrowingBushBlock;
import org.dawnoftime.gardentrails.platform.Services;
import org.dawnoftime.gardentrails.registry.GTEntitiesRegistry;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class MulberryBlock extends DoubleGrowingBushBlock {
    public MulberryBlock(PlantType plantType, int cutAge, int growingAge) {
        super(plantType, cutAge, growingAge);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable BlockGetter world, @NotNull List<Component> tooltip, @NotNull TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);
        tooltip.add(Component.translatable("lore.gardentrails.silk_process").withStyle(ChatFormatting.AQUA));
        Component yellowArg = Component.translatable("item.gardentrails.mulberry_leaves").withStyle(ChatFormatting.YELLOW);
        tooltip.add(Component.translatable("lore.gardentrails.mulberry", yellowArg).withStyle(ChatFormatting.WHITE));
        Component yellowEggs = Component.translatable("item.gardentrails.silk_worm_eggs").withStyle(ChatFormatting.YELLOW);
        tooltip.add(Component.translatable("lore.gardentrails.mulberry.silkmoths", yellowEggs).withStyle(ChatFormatting.WHITE));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        super.randomTick(state, worldIn, pos, random);
        if(state.getValue(HALF) == Half.TOP) {
            if(random.nextInt(400) == 0) {
                GTEntitiesRegistry.INSTANCE.SILKMOTH_ENTITY.get().spawn(worldIn, (ItemStack) null, null, pos, MobSpawnType.SPAWNER, false, true);
            }
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }
}
