package org.dawnoftime.dawnoftime.util;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;


import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.WallSide;
import org.dawnoftime.dawnoftime.registry.DoTBBlocksRegistry;
import org.dawnoftime.dawnoftime.registry.DoTBItemsRegistry;
import org.jetbrains.annotations.NotNull;

public class BlockStatePropertiesAA {
    public static final BooleanProperty CUT = BooleanProperty.create("cut");
    public static final IntegerProperty MULTIBLOCK_3X = IntegerProperty.create("multiblock_3x", 0, 2);
    public static final IntegerProperty MULTIBLOCK_2Y = IntegerProperty.create("multiblock_2y", 0, 1);
    public static final IntegerProperty MULTIBLOCK_3Z = IntegerProperty.create("multiblock_3z", 0, 2);
    public static final IntegerProperty AGE_0_6 = IntegerProperty.create("age", 0, 6);
    public static final IntegerProperty SIZE_0_2 = IntegerProperty.create("size", 0, 2);
    public static final IntegerProperty SIZE_0_5 = IntegerProperty.create("size", 0, 5);
    public static final EnumProperty<ClimbingPlant> CLIMBING_PLANT = EnumProperty.create("climbing_plant", ClimbingPlant.class);
    public static final EnumProperty<HorizontalConnection> HORIZONTAL_CONNECTION = EnumProperty.create("horizontal_connection", HorizontalConnection.class);
    public static final EnumProperty<VerticalConnection> VERTICAL_CONNECTION = EnumProperty.create("vertical_connection", VerticalConnection.class);

    public enum VerticalLimitedConnection implements StringRepresentable {
        NONE("none", 0),
        BOTTOM("bottom", 1),
        TOP("top", 2);
        private final String name;
        private final int index;

        VerticalLimitedConnection(final String name, final int index) {
            this.name = name;
            this.index = index;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public enum HorizontalConnection implements StringRepresentable {
        NONE("none", 0),
        LEFT("left", 1),
        RIGHT("right", 2),
        BOTH("both", 3);
        private final String name;
        private final int index;

        HorizontalConnection(final String name, final int index) {
            this.name = name;
            this.index = index;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public enum VerticalConnection implements StringRepresentable {
        NONE("none", 0),
        UNDER("under", 1),
        ABOVE("above", 2),
        BOTH("both", 3);
        private final String name;
        private final int index;

        VerticalConnection(final String name, final int index) {
            this.name = name;
            this.index = index;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        public int getIndex() {
            return this.index;
        }
    }

    public enum PillarConnection implements StringRepresentable {
        NOTHING("nothing"),
        FOUR_PX("4_pixels"),
        SIX_PX("6_pixels"),
        EIGHT_PX("8_pixels"),
        TEN_PX("10_pixels");
        private final String name;

        PillarConnection(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }

    public enum FencePillar implements StringRepresentable {
        NONE("none"),
        PILLAR_BIG("pillar_big"),
        PILLAR_SMALL("pillar_small"),
        CAP_PILLAR_BIG("cap_pillar_big");
        private final String name;

        FencePillar(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }

    public enum SquareCorners implements StringRepresentable {
        TOP_LEFT("top_left", -1, 1),
        TOP_RIGHT("top_right", 1, 1),
        BOTTOM_RIGHT("bottom_right", 1, -1),
        BOTTOM_LEFT("bottom_left", -1, -1);
        private final String name;
        private final int horizontal_offset;
        private final int vertical_offset;

        SquareCorners(final String name, final int horizontal_offset, final int vertical_offset) {
            this.name = name;
            this.horizontal_offset = horizontal_offset;
            this.vertical_offset = vertical_offset;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        public int getHorizontalOffset(final SquareCorners referenceCorner) {
            return referenceCorner.horizontal_offset == this.horizontal_offset ? 0 : this.horizontal_offset;
        }

        public int getVerticalOffset(final SquareCorners referenceCorner) {
            return referenceCorner.vertical_offset == this.vertical_offset ? 0 : this.vertical_offset;
        }

        public boolean isTopCorner() {
            return this.vertical_offset == 1;
        }

        public SquareCorners getAdjacentCorner(final boolean vertically) {
            return switch (this) {
                case TOP_RIGHT -> vertically ? BOTTOM_RIGHT : TOP_LEFT;
                case BOTTOM_RIGHT -> vertically ? TOP_RIGHT : BOTTOM_LEFT;
                case BOTTOM_LEFT -> vertically ? TOP_LEFT : BOTTOM_RIGHT;
                default -> vertically ? BOTTOM_LEFT : TOP_RIGHT;
            };
        }
    }

    public enum OpenPosition implements StringRepresentable {
        CLOSED("closed"),
        HALF("half"),
        FULL("full");
        private final String name;

        OpenPosition(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        public boolean isOpen() {
            return this != CLOSED;
        }
    }

    public enum WaterTrickleEnd implements StringRepresentable {
        STRAIGHT("straight"),
        FADE("fade"),
        SPLASH("splash");
        private final String name;

        WaterTrickleEnd(final String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }
    }

    public enum ClimbingPlant implements StringRepresentable {
        NONE("none"),
        VINE("vine"),
        IVY("ivy"),
        GRAPE("grape", true, 4, 6, 0, 2, 2);
        //CLEMATIS("clematis", true, 4, 5, 1, 2, 2);
        private final String name;
        private final boolean cycle;
        private final int[] moonPhasePerAge;

        ClimbingPlant(final String name, final boolean cycle, final int age2, final int age3, final int age4, final int age5, final int age6) {
            this.name = name;
            this.cycle = cycle;
            this.moonPhasePerAge = new int[]{
                    age2, age3, age4, age5, age6
            };
        }

        ClimbingPlant(final String name) {
            this(name, false, 0, 0, 0, 0, 0);
        }

        public static ClimbingPlant getFromItem(final Item item) {
            if (item == DoTBItemsRegistry.INSTANCE.GRAPE_SEEDS.get()) {
                return GRAPE;
            }
            //if(item == CLEMATIS_SEEDS.get()) return CLEMATIS;
            if (item == Blocks.VINE.asItem()) {
                return VINE;
            }
            if (item == DoTBBlocksRegistry.INSTANCE.IVY.get().asItem()) {
                return IVY;
            }
            return NONE;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        public int maxAge() {
            return this.cycle ? 6 : 2;
        }

        public boolean canGrow(final Level worldIn, int currentAge) {
            if (!this.cycle || currentAge < 2 || currentAge > 6) {
                return false;
            }
            currentAge -= 2;
            final int currentPhase = worldIn.dimensionType().moonPhase(worldIn.getDayTime());
            return (currentPhase - this.moonPhasePerAge[currentAge] + 8) % 8 < 4;
        }

        public boolean hasNoPlant() {
            return this == NONE;
        }
    }
}