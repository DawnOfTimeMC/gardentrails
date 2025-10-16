package org.dawnoftime.gardentrails.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import static org.dawnoftime.gardentrails.util.VoxelShapesBuilder.*;

public class VoxelShapes {

    // Default shapes
    public static final VoxelShape[] FULL_SHAPE = new VoxelShape[]{Shapes.block()};

    // Custom shapes
    public static final VoxelShape[] BUSH_SHAPES = new VoxelShape[]{Block.box(3.0D, 0.0D, 3.0D, 11.0D, 13.0D, 11.0D)};
    public static final VoxelShape[] CYPRESS_SHAPES = new VoxelShape[]{
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D),
            Block.box(6.0D, 0.0D, 6.0D, 10.0D, 8.0D, 10.0D),
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D),
            Shapes.block()};
    public static final VoxelShape[] DRYER_SHAPES = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
            Shapes.block()};
    public static final VoxelShape[] EDGE_SHAPES = makeEdgeShapes();
    public static final VoxelShape[] FLOWER_POT_SHAPE = new VoxelShape[]{Block.box(5.0D, 0.0D, 5.0D, 11.0D, 6.0D, 11.0D)};
    public static final VoxelShape[] GERANIUM_SHAPE = new VoxelShape[]{Block.box(-2.0D, -7.0D, -2.0D, 17.0D, 15.0D, 17.0D)};
    public static final VoxelShape[] IVY_SHAPES = makeIvyShapes();
    public static final VoxelShape[] PLANTER_SHAPES = Utils.generateHorizontalShapes(new VoxelShape[]{
            Block.box(0.0D, 0.0D, 8.0D, 16.0D, 8.0D, 16.0D),
            Block.box(0.0D, 8.0D, 8.0D, 16.0D, 16.0D, 16.0D)});
    public static final VoxelShape[] PLATE_SHAPES = makePlateShapes();
    public static final VoxelShape[] SAPLING_SHAPES = new VoxelShape[]{Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D)};
    public static final VoxelShape[] STICK_BUNDLE_SHAPES = new VoxelShape[]{
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D),
            Block.box(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D)};
    public static final VoxelShape[] WILD_PLANT_SHAPES = new VoxelShape[]{Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D)};
    public static final VoxelShape[] PERGOLA_SHAPES = makePergolaShapes();
    public static final VoxelShape[] PERGOLA_COLLISION_SHAPES = makePergolaCollisionShapes();
}
