package org.dawnoftime.gardentrails.block.japanese;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Half;
import java.util.List;
import org.dawnoftime.gardentrails.GTConstants;
import org.dawnoftime.gardentrails.block.templates.DoubleGrowingBushBlock;
import org.dawnoftime.gardentrails.registry.GTEntitiesRegistry;

public class MulberryBlock extends DoubleGrowingBushBlock {
    public MulberryBlock(PlantType plantType, int cutAge, int growingAge) {
        super(plantType, cutAge, growingAge);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("lore.gardentrails.silk_process")
            .withStyle(ChatFormatting.AQUA));
        Component yellowLeaves = Component.translatable("item.gardentrails.mulberry_leaves")
            .withStyle(ChatFormatting.YELLOW);
        tooltipComponents.add(Component.translatable("lore.gardentrails.mulberry", yellowLeaves)
            .withStyle(ChatFormatting.WHITE));
        Component yellowEggs = Component.translatable("item.gardentrails.silk_worm_eggs")
            .withStyle(ChatFormatting.YELLOW);
        tooltipComponents.add(Component.translatable("lore.gardentrails.mulberry.silkmoths", yellowEggs)
            .withStyle(ChatFormatting.WHITE));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        super.randomTick(state, worldIn, pos, random);
        if(state.getValue(HALF) == Half.TOP) {
            if(random.nextInt(GTConstants.SILKMOTH_SPAWN_CHANCE) == 0) {
                GTEntitiesRegistry.INSTANCE.SILKMOTH_ENTITY.get().spawn(worldIn, (ItemStack) null, null, pos, MobSpawnType.SPAWNER, false, true);
            }
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState blockState) {
        return true;
    }
}
