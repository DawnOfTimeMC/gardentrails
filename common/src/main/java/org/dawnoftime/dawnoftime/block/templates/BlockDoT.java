package org.dawnoftime.dawnoftime.block.templates;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.dawnoftime.dawnoftime.DoTBCommon;
import org.dawnoftime.dawnoftime.block.IFlammable;
import org.jetbrains.annotations.NotNull;

import static org.dawnoftime.dawnoftime.DoTBCommon.MOD_ID;
import static org.dawnoftime.dawnoftime.util.VoxelShapes.FULL_SHAPE;

public class BlockDoT extends Block implements IFlammable {
    private int fireSpreadSpeed = 0;
    private int fireDestructionSpeed = 0;
    private final VoxelShape[] shapes;

    public BlockDoT(Properties properties, VoxelShape[] shapes) {
        super(properties);
        this.shapes = shapes;
    }

    public BlockDoT(Properties properties){
        this(properties, FULL_SHAPE);
    }

    @Override
    public @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        try {
            return this.getShape(this.getShapeIndex(state, level, pos, context));
        } catch (ArrayIndexOutOfBoundsException e) {
            DoTBCommon.LOG.error(MOD_ID + " : Error in the VoxelShape of the block : {}", state.getBlock().getName().getString());
            throw e;
        }
    }

    public @NotNull VoxelShape getShape(int index){
        return this.shapes[index];
    }

    public int getShapeIndex(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return 0;
    }

    public Block setBurnable() {
        return setBurnable(5, 20);
    }

    public Block setBurnable(int fireSpreadSpeed, int fireDestructionSpeed) {
        this.fireSpreadSpeed = fireSpreadSpeed;
        this.fireDestructionSpeed = fireDestructionSpeed;
        return this;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : this.fireSpreadSpeed;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED) ? 0 : this.fireDestructionSpeed;
    }
}
