package org.dawnoftime.gardentrails.block.templates;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;
import org.dawnoftime.gardentrails.registry.GTItemsRegistry;
import org.dawnoftime.gardentrails.util.GTBlockStateProperties;
import org.dawnoftime.gardentrails.util.Utils;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

import static org.dawnoftime.gardentrails.util.VoxelShapes.PERGOLA_SHAPES;


public class PergolaBlock extends BlockGT {
    public static final BooleanProperty AXIS_X = GTBlockStateProperties.AXIS_X;
    public static final BooleanProperty AXIS_Y = GTBlockStateProperties.AXIS_Y;
    public static final BooleanProperty AXIS_Z = GTBlockStateProperties.AXIS_Z;

    public PergolaBlock(Properties properties) {
        super(properties, PERGOLA_SHAPES);
        this.registerDefaultState(this.defaultBlockState().setValue(AXIS_Y, false).setValue(AXIS_X, false).setValue(AXIS_Z, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AXIS_Y, AXIS_X, AXIS_Z);
    }

    @Override
    public int getShapeIndex(@NotNull BlockState state, @NotNull BlockGetter worldIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        if(state.getValue(AXIS_Y)) {
            int index = 3;
            if(state.getValue(AXIS_X))
                index += 1;
            if(state.getValue(AXIS_Z))
                index += 2;
            return index;
        } else {
            int index = state.getValue(AXIS_Z) ? 1 : 0;
            return state.getValue(AXIS_X) ? index * 2 : index;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState state = context.getLevel().getBlockState(context.getClickedPos());
        if(state.getBlock() != this) {
            state = super.getStateForPlacement(context);
            if (state == null) {
                return null;
            }
        }
        return switch (context.getClickedFace().getAxis()) {
            case X -> state.setValue(AXIS_X, true);
            case Z -> state.setValue(AXIS_Z, true);
            default -> state.setValue(AXIS_Y, true);
        };
    }

    @Override
    public boolean canBeReplaced(@NotNull BlockState state, BlockPlaceContext useContext) {
        ItemStack itemstack = useContext.getItemInHand();
        if(useContext.getPlayer() != null && useContext.getPlayer().isCrouching())
            return false;
        if(itemstack.getItem() == this.asItem()) {
            return switch (useContext.getClickedFace().getAxis()) {
                case X -> !state.getValue(AXIS_X);
                case Y -> !state.getValue(AXIS_Y);
                case Z -> !state.getValue(AXIS_Z);
            };
        }
        return false;
    }

    @Override
    public @NotNull BlockState rotate(BlockState state, Rotation rotation) {
        boolean x = state.getValue(AXIS_X);
        boolean z = state.getValue(AXIS_Z);
        return switch (rotation) {
            case CLOCKWISE_90, COUNTERCLOCKWISE_90 -> state.setValue(AXIS_X, z).setValue(AXIS_Z, x);
            default -> state;
        };
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable BlockGetter worldIn, @NotNull List<Component> tooltip, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        Utils.addTooltip(tooltip, Utils.TOOLTIP_PERGOLA);
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hit) {
        if (player.isCreative()) {
            Item item = player.getItemInHand(hand).getItem();
            return this.tryPutPlant(item, state, level, pos);
        }
        if (state.getValue(AXIS_Y)) {
            BlockState stateUnder = level.getBlockState(pos.below());
            if (stateUnder.getBlock() == Blocks.GRASS_BLOCK || stateUnder.is(BlockTags.DIRT)) {
                Item item = player.getItemInHand(hand).getItem();
                return this.tryPutPlant(item, state, level, pos);
            }
        }
        return InteractionResult.PASS;
    }

    private InteractionResult tryPutPlant(Item itemInHand, BlockState currentState, @NotNull Level level, @NotNull BlockPos pos) {
        if (itemInHand == Blocks.VINE.asItem()) {
            level.setBlock(pos, this.copyShapeToPergola(currentState, GTBlocksRegistry.INSTANCE.IRON_PERGOLA_VINE.get()), 2);
            return InteractionResult.SUCCESS;
        }
        if (itemInHand == GTBlocksRegistry.INSTANCE.IVY.get().asItem()) {
            level.setBlock(pos, this.copyShapeToPergola(currentState, GTBlocksRegistry.INSTANCE.IRON_PERGOLA_IVY.get()), 2);
            return InteractionResult.SUCCESS;
        }
        if (itemInHand == GTItemsRegistry.INSTANCE.GRAPE_SEEDS.get()) {
            level.setBlock(pos, this.copyShapeToPergola(currentState, GTBlocksRegistry.INSTANCE.IRON_PERGOLA_GRAPE.get()), 2);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    public BlockState copyShapeToPergola(@NotNull BlockState currentState, PergolaBlock targetPergola) {
        return targetPergola.defaultBlockState()
                .setValue(AXIS_X, currentState.getValue(AXIS_X))
                .setValue(AXIS_Y, currentState.getValue(AXIS_Y))
                .setValue(AXIS_Z, currentState.getValue(AXIS_Z));
    }

    public static BlockPos randomSpread(BlockPos pos, RandomSource random) {
        return switch (random.nextInt(5)) {
            case 0 -> pos.north();
            case 1 -> pos.east();
            case 2 -> pos.south();
            case 3 -> pos.west();
            default -> pos.above();
        };
    }
}