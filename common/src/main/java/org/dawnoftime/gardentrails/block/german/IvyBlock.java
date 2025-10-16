package org.dawnoftime.gardentrails.block.german;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;


import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import org.dawnoftime.gardentrails.block.IBlockGeneration;
import org.dawnoftime.gardentrails.block.templates.BlockGT;
import org.dawnoftime.gardentrails.platform.Services;
import org.dawnoftime.gardentrails.registry.GTTags;
import org.dawnoftime.gardentrails.util.Utils;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static net.minecraft.tags.BlockTags.DIRT;
import static net.minecraft.tags.BlockTags.SAND;
import static org.dawnoftime.gardentrails.util.Utils.TOOLTIP_CROP;
import static org.dawnoftime.gardentrails.util.VoxelShapes.IVY_SHAPES;

public class IvyBlock extends BlockGT implements IBlockGeneration {
    public static final BooleanProperty NORTH = BlockStateProperties.NORTH;
    public static final BooleanProperty EAST = BlockStateProperties.EAST;
    public static final BooleanProperty SOUTH = BlockStateProperties.SOUTH;
    public static final BooleanProperty WEST = BlockStateProperties.WEST;
    public static final BooleanProperty PERSISTENT = BlockStateProperties.PERSISTENT;
    private static final IntegerProperty AGE = BlockStateProperties.AGE_2;

    public IvyBlock(Properties properties) {
        super(properties.pushReaction(PushReaction.DESTROY), IVY_SHAPES);
        this.registerDefaultState(this.defaultBlockState().setValue(AGE, 0).setValue(NORTH, false).setValue(EAST, false).setValue(SOUTH, false).setValue(WEST, false).setValue(PERSISTENT, false));
    }

    private static ArrayList<Direction> getCurrentDirections(BlockState state) {
        ArrayList<Direction> list = new ArrayList<>();
        if (state.getValue(NORTH))
            list.add(Direction.NORTH);
        if (state.getValue(EAST))
            list.add(Direction.EAST);
        if (state.getValue(SOUTH))
            list.add(Direction.SOUTH);
        if (state.getValue(WEST))
            list.add(Direction.WEST);
        return list;
    }

    private static BooleanProperty getProperty(Direction direction) {
        return switch (direction) {
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            case EAST -> EAST;
            default -> NORTH;
        };
    }

    private static boolean hasFullFace(LevelReader Level, BlockPos currentPos, Direction face) {
        currentPos = currentPos.relative(face);
        return hasFullFace(Level.getBlockState(currentPos), Level, currentPos, face);
    }

    private static boolean hasFullFace(BlockState state, LevelReader Level, BlockPos pos, Direction face) {
        Block block = state.getBlock();
        if (block.defaultBlockState().is(DIRT) || block.defaultBlockState().is(SAND) || block.defaultBlockState().is(GTTags.INSTANCE.GRAVEL))
            return false;
        return Block.isFaceFull(state.getCollisionShape(Level, pos), face.getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.@NotNull Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(NORTH, EAST, SOUTH, WEST, AGE, PERSISTENT);
    }

    @Override
    public int getShapeIndex(@NotNull BlockState state, @NotNull BlockGetter levelIn, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        int index = 0;
        if (state.getValue(SOUTH))
            index += 1;
        if (state.getValue(WEST))
            index += 2;
        if (state.getValue(NORTH))
            index += 4;
        if (state.getValue(EAST))
            index += 8;
        if (index > 14)
            index = 0;
        return index;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);
        Direction facing = context.getHorizontalDirection();
        pos = pos.relative(facing);
        if (!hasFullFace(level.getBlockState(pos), level, pos, facing)) {
            return null;
        }
        if (state.getBlock() != this) {
            state = this.defaultBlockState();
        }
        return state.setValue(getProperty(facing), true);
    }

    @Override
    public boolean canBeReplaced(@NotNull BlockState state, BlockPlaceContext useContext) {
        ItemStack itemstack = useContext.getItemInHand();
        if (useContext.getPlayer() != null && useContext.getPlayer().isCrouching()) {
            return false;
        }
        if (itemstack.getItem() == this.asItem()) {
            Direction newDirection = useContext.getHorizontalDirection();
            return switch (newDirection) {
                case WEST -> !state.getValue(WEST);
                case NORTH -> !state.getValue(NORTH);
                case EAST -> !state.getValue(EAST);
                default -> !state.getValue(SOUTH);
            };
        }
        return false;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !state.getValue(PERSISTENT);
    }

    @Override
    public void tick(@NotNull BlockState state, ServerLevel levelIn, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (levelIn.isClientSide()) {
            return;
        }
        if (!levelIn.isLoaded(pos)) {
            return;
        }
        if (levelIn.getRawBrightness(pos, 0) >= 8) {
            int age = state.getValue(AGE);
            if (age < 2) {
                if (random.nextInt(Services.PLATFORM.getConfig().climbingPlantGrowthChance) == 0) {
                    levelIn.setBlock(pos, state.setValue(AGE, age + 1), 2);
                }
                return;
            }
            if (random.nextInt(Services.PLATFORM.getConfig().climbingPlantSpreadChance) == 0) {
                // The Ivy will spread
                ArrayList<Direction> faces = getCurrentDirections(state);
                int startSpread = random.nextInt(4);
                for (Direction face : faces) {
                    BlockPos studiedPos;
                    for (int i = 0; i < 4; i++) {
                        int spreadIndex = (startSpread + i) % 4;
                        if (spreadIndex < 2) {
                            // 0 : spread on the left
                            // 1 : spread on the right
                            Direction rotFace = spreadIndex == 0 ? face.getCounterClockWise() : face.getClockWise();
                            if (hasFullFace(levelIn, pos, rotFace)) {
                                if (!state.getValue(getProperty(rotFace))) {
                                    levelIn.setBlock(pos, state.setValue(getProperty(rotFace), true), 2);
                                    return;
                                }
                            } else {
                                studiedPos = pos.relative(rotFace);
                                if (levelIn.getBlockState(studiedPos).isAir()) {
                                    if (hasFullFace(levelIn, studiedPos, face)) {
                                        levelIn.setBlock(studiedPos, this.defaultBlockState().setValue(getProperty(face), true), 2);
                                        return;
                                    } else {
                                        studiedPos = studiedPos.relative(face);
                                        if (levelIn.getBlockState(studiedPos).isAir()) {
                                            rotFace = spreadIndex == 0 ? face.getClockWise() : face.getCounterClockWise();
                                            if (hasFullFace(levelIn, studiedPos, rotFace)) {
                                                levelIn.setBlock(studiedPos, this.defaultBlockState().setValue(getProperty(rotFace), true), 2);
                                                return;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            // 2 : spread above
                            // 3 : spread below
                            studiedPos = spreadIndex == 2 ? pos.above() : pos.below();
                            if (levelIn.getBlockState(studiedPos).isAir()) {
                                if (hasFullFace(levelIn, studiedPos, face)) {
                                    levelIn.setBlock(studiedPos, this.defaultBlockState().setValue(getProperty(face), true), 2);
                                    return;
                                }
                            }
                        }
                    }
                }
                // After failing to grow in any direction, we switch to PERSISTENT
                levelIn.setBlock(pos, state.setValue(PERSISTENT, true), 2);
            }
        }
    }

    @Override
    public @NotNull BlockState updateShape(@NotNull BlockState stateIn, Direction facing, @NotNull BlockState facingState, @NotNull LevelAccessor levelIn, @NotNull BlockPos currentPos, @NotNull BlockPos facingPos) {
        stateIn = stateIn.setValue(PERSISTENT, false);
        if (facing.getAxis().isHorizontal()) {
            if (facing == Direction.NORTH && stateIn.getValue(NORTH) && !hasFullFace(facingState, levelIn, facingPos, facing)) {
                stateIn = stateIn.setValue(NORTH, false);
            }
            if (facing == Direction.EAST && stateIn.getValue(EAST) && !hasFullFace(facingState, levelIn, facingPos, facing)) {
                stateIn = stateIn.setValue(EAST, false);
            }
            if (facing == Direction.SOUTH && stateIn.getValue(SOUTH) && !hasFullFace(facingState, levelIn, facingPos, facing)) {
                stateIn = stateIn.setValue(SOUTH, false);
            }
            if (facing == Direction.WEST && stateIn.getValue(WEST) && !hasFullFace(facingState, levelIn, facingPos, facing)) {
                stateIn = stateIn.setValue(WEST, false);
            }
        }
        if (getCurrentDirections(stateIn).isEmpty())
            return Blocks.AIR.defaultBlockState();
        return stateIn;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(PERSISTENT)) {
            if (player.isCreative()) {
                int age = state.getValue(AGE);
                if (player.isCrouching()) {
                    if (age > 0) {
                        level.setBlock(pos, state.setValue(AGE, age - 1), 10);
                        return InteractionResult.SUCCESS;
                    }
                } else {
                    if (age < 2) {
                        level.setBlock(pos, state.setValue(AGE, age + 1), 10);
                        return InteractionResult.SUCCESS;
                    }
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!state.getValue(PERSISTENT)) {
            if (Utils.useLighter(level, pos, player, hand)) {
                Random rand = new Random();
                for (int i = 0; i < 5; i++) {
                    level.addAlwaysVisibleParticle(ParticleTypes.SMOKE, (double) pos.getX() + rand.nextDouble(), (double) pos.getY() + 0.5D + rand.nextDouble() / 2, (double) pos.getZ() + rand.nextDouble(), 0.0D, 0.07D, 0.0D);
                }
                level.setBlock(pos, state.setValue(PERSISTENT, true), 10);
                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.FAIL;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        Utils.addTooltip(tooltipComponents, TOOLTIP_CROP);
    }

    @Override
    public boolean generateOnPos(WorldGenLevel world, BlockPos pos, BlockState state, RandomSource random) {
        if (!world.getBlockState(pos).is(BlockTags.LOGS)) {
            if (world.getBlockState(pos.north()).is(BlockTags.LOGS)) {
                pos = pos.north();
            } else if (world.getBlockState(pos.east()).is(BlockTags.LOGS)) {
                pos = pos.east();
            } else if (world.getBlockState(pos.south()).is(BlockTags.LOGS)) {
                pos = pos.south();
            } else if (world.getBlockState(pos.west()).is(BlockTags.LOGS)) {
                pos = pos.west();
            } else {
                return false;
            }
        }
        BlockPos.MutableBlockPos mutPos = pos.mutable().move(Direction.DOWN);
        for (int yDown = 0; yDown < 20; yDown++) {
            if (world.getBlockState(mutPos).is(BlockTags.LOGS)) {
                mutPos.move(Direction.DOWN);
            } else {
                mutPos.move(Direction.UP);
                break;
            }
        }
        int trunkSize = 0;
        while (trunkSize <= 20) {
            if (world.getBlockState(mutPos).is(BlockTags.LOGS)) {
                trunkSize++;
            } else {
                break;
            }
            mutPos.move(Direction.UP);
        }
        if (trunkSize > 0) {
            return tryPlaceOnTrunk(world, pos.north(), state.setValue(SOUTH, true), random, trunkSize)
                    && tryPlaceOnTrunk(world, pos.east(), state.setValue(WEST, true), random, trunkSize)
                    && tryPlaceOnTrunk(world, pos.south(), state.setValue(NORTH, true), random, trunkSize)
                    && tryPlaceOnTrunk(world, pos.west(), state.setValue(EAST, true), random, trunkSize);
        }
        return false;
    }

    private boolean tryPlaceOnTrunk(WorldGenLevel world, BlockPos pos, BlockState state, RandomSource random, int trunkSize) {
        BlockPos.MutableBlockPos mutPos = pos.mutable();
        int sideSize = 0;
        while (sideSize < trunkSize) {
            if (world.getBlockState(mutPos).isAir()) {
                sideSize++;
            } else {
                break;
            }
            mutPos.move(Direction.UP);
        }
        mutPos = pos.mutable();
        if (sideSize > 0) {
            sideSize = random.nextInt(1, sideSize + 1);
            for (int yOffset = 0; yOffset < sideSize; yOffset++) {
                int age = (yOffset + 1 == sideSize) ? random.nextInt(3) : 2;
                world.setBlock(mutPos, state.setValue(AGE, age), 2);
                mutPos.move(Direction.UP);
            }
            return true;
        }
        return false;
    }
}
