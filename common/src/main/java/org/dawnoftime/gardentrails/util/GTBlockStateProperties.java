package org.dawnoftime.gardentrails.util;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class GTBlockStateProperties {
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    public static final BooleanProperty AXIS_X = BooleanProperty.create("axis_x");
    public static final BooleanProperty AXIS_Y = BooleanProperty.create("axis_y");
    public static final BooleanProperty AXIS_Z = BooleanProperty.create("axis_z");
    public static final IntegerProperty MULTIBLOCK_3X = IntegerProperty.create("multiblock_3x", 0, 2);
    public static final IntegerProperty MULTIBLOCK_2Y = IntegerProperty.create("multiblock_2y", 0, 1);
    public static final IntegerProperty MULTIBLOCK_3Z = IntegerProperty.create("multiblock_3z", 0, 2);
    public static final IntegerProperty SIZE_0_2 = IntegerProperty.create("size", 0, 2);
    public static final IntegerProperty SIZE_0_5 = IntegerProperty.create("size", 0, 5);
    public static final IntegerProperty AGE_6 = IntegerProperty.create("age", 0, 6);
}