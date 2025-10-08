package org.dawnoftime.gardentrails.block.templates;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.dawnoftime.gardentrails.platform.Services;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;
import org.dawnoftime.gardentrails.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static org.dawnoftime.gardentrails.util.VoxelShapes.PERGOLA_COLLISION_SHAPES;

public class PergolaPlantBlock extends PergolaBlock{
    private static final IntegerProperty AGE_2 = BlockStateProperties.AGE_2;

    public PergolaPlantBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS_Y, false).setValue(AXIS_X, false).setValue(AXIS_Z, false).setValue(AGE_2, 0));
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if (state.getValue(AXIS_X) || state.getValue(AXIS_Z)) {
            return state.getValue(AXIS_Y) ? PERGOLA_COLLISION_SHAPES[2] : PERGOLA_COLLISION_SHAPES[1];
        }
        return PERGOLA_COLLISION_SHAPES[0];
    }

    @Override
    public @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return super.getShape(state, level, pos, context);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE_2);
    }

    @Override
    public boolean isRandomlyTicking(@NotNull BlockState state) {
        return true;
    }

    @Override
    public void randomTick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if(!level.isClientSide()) {
            if(!level.isLoaded(pos))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light

            if(level.getRawBrightness(pos, 0) >= 8) {
                int age = state.getValue(AGE_2);
                if(age < 2){
                    if (random.nextInt(Services.PLATFORM.getConfig().climbingPlantGrowthChance) == 0) { //Probability "can grow"
                        level.setBlock(pos, state.setValue(AGE_2, age + 1), 2);
                    }
                } else if (random.nextInt(Services.PLATFORM.getConfig().climbingPlantSpreadChance) == 0) { //Probability "can spread"
                    BlockPos adjacentPos = randomSpread(pos, random);
                    BlockState adjacentState = level.getBlockState(adjacentPos);
                    if(adjacentState.getBlock() == GTBlocksRegistry.INSTANCE.IRON_PERGOLA.get()) {
                        level.setBlock(adjacentPos, this.copyShapeToPergola(adjacentState, this), 2);
                    }
                }
            }
        }
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if(player.isCrouching() && level instanceof ServerLevel serverLevel) {
            ItemStack heldItemStack = player.getItemInHand(hand);
            ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(state.getBlock());
            List<ItemStack> drops = Utils.getLootList(serverLevel, state, heldItemStack, blockId.getPath() + "_" + state.getValue(AGE_2));
            Utils.dropLootFromList(level, pos, drops, 1.0F);
            level.setBlock(pos, this.copyShapeToPergola(state, GTBlocksRegistry.INSTANCE.IRON_PERGOLA.get()), 2);
            level.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
