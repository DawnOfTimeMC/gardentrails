package org.dawnoftime.gardentrails.block.templates;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.dawnoftime.gardentrails.block.IBlockGeneration;
import org.dawnoftime.gardentrails.platform.Services;

import javax.annotation.Nonnull;


public class SoilCropsBlock extends CropBlock implements IBlockGeneration {
    private final PlantType plantType;

    public enum PlantType {
        DESERT, NETHER, CROP, CAVE, PLAINS, WATER, BEACH
    }

    public SoilCropsBlock(PlantType type) {
        super(Properties.copy(Blocks.SUNFLOWER).offsetType(OffsetType.NONE).randomTicks().sound(SoundType.CROP));
        this.plantType = type;
        this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0));
    }

    @Override
    public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
        if(!worldIn.isLoaded(pos))
            return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if(worldIn.getRawBrightness(pos, 0) >= 9) {
            int age = this.getAge(state);
            if(age < this.getMaxAge()) {
                float f = getGrowthSpeed(this, worldIn, pos);
                if(random.nextInt((int) (25.0F / f) + 1) == 0) {
                    this.setPlantWithAge(state, worldIn, pos, age + 1);
                }
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        return this.mayGenerateOn(world, pos.below(), this.getPlantType()) && super.canSurvive(state, world, pos);
    }

    private PlantType getPlantType() {
        return this.plantType;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos pos) {
        return this.mayGenerateOn(world, pos, this.getPlantType());
    }

    /**
     * Checks the block at the given position and states if the plant can be generated on.
     *
     * @param worldIn   of the block.
     * @param pos       to be checked (block under the plant).
     * @param plantType of the plant, which is used to get the whitelisted blocks.
     *
     * @return true if the plant can be generated on this block, false otherwise.
     */
    public boolean mayGenerateOn(BlockGetter worldIn, BlockPos pos, PlantType plantType) {
        BlockState stateOn = worldIn.getBlockState(pos);
        Block blockOn = stateOn.getBlock();

        if(plantType.equals(PlantType.DESERT)) {
            return stateOn.is(Blocks.SAND)
                    || stateOn.is(Blocks.TERRACOTTA)
                    || blockOn instanceof GlazedTerracottaBlock;
        } else if(plantType.equals(PlantType.NETHER)) {
            return stateOn.is(Blocks.SOUL_SAND);
        } else if(plantType.equals(PlantType.CROP)) {
            return stateOn.is(Blocks.FARMLAND) || isModCompatible(stateOn);
        } else if(plantType.equals(PlantType.CAVE)) {
            return stateOn.isFaceSturdy(worldIn, pos, Direction.UP);
        } else if(plantType.equals(PlantType.PLAINS)) {
            return stateOn.is(Blocks.GRASS_BLOCK)
                    || stateOn.is(Blocks.DIRT)
                    || stateOn.is(Blocks.FARMLAND)
                    || isModCompatible(stateOn);
        } else if(plantType.equals(PlantType.WATER)) {
            return worldIn.getFluidState(pos.above()).getType() == Fluids.WATER
                    && (stateOn.is(Blocks.CLAY)
                    || stateOn.is(Blocks.DIRT)
                    || stateOn.is(Blocks.FARMLAND)
                    || stateOn.is(Blocks.GRAVEL));
        } else if(plantType.equals(PlantType.BEACH)) {
            boolean isBeach = stateOn.is(Blocks.GRASS_BLOCK)
                    || stateOn.is(Blocks.DIRT)
                    || stateOn.is(Blocks.SAND)
                    || stateOn.is(Blocks.RED_SAND);
            if(isBeach) {
                boolean hasWater = false;
                for(Direction face : Direction.Plane.HORIZONTAL) {
                    BlockState blockState = worldIn.getBlockState(pos.relative(face));
                    FluidState fluidState = worldIn.getFluidState(pos.relative(face));
                    hasWater = blockState.is(Blocks.FROSTED_ICE);
                    hasWater |= fluidState.is(FluidTags.WATER);
                    if(hasWater)
                        break; //No point continuing.
                }
                return hasWater;
            }
        }
        return false;
    }

    private boolean isModCompatible(BlockState stateOn) {
        if (Services.PLATFORM.isModLoaded("farmersdelight") && stateOn.is(getBlockFromRegistry("farmersdelight:rich_soil_farmland")))
            return true;

        else if (Services.PLATFORM.isModLoaded("aether") && stateOn.is(getBlockFromRegistry("aether:aether_farmland")))
            return true;

        else return Services.PLATFORM.isModLoaded("immersive_weathering") && (
                    stateOn.is(getBlockFromRegistry("immersive_weathering:loamy_farmland")) ||
                    stateOn.is(getBlockFromRegistry("immersive_weathering:earthen_clay_farmland")) ||
                    stateOn.is(getBlockFromRegistry("immersive_weathering:sandy_farmland")) ||
                    stateOn.is(getBlockFromRegistry("immersive_weathering:silty_farmland"))
            );
    }

    private Block getBlockFromRegistry(String name) {
        return BuiltInRegistries.BLOCK.get(ResourceLocation.tryParse(name));
    }

    public void setPlantWithAge(BlockState currentState, LevelAccessor worldIn, BlockPos pos, int newAge) {
        worldIn.setBlock(pos, currentState.setValue(this.getAgeProperty(), newAge), 10);
    }

	@Override
    @Nonnull
	protected ItemLike getBaseSeedId() {
		return this.asItem();
	}

    @Override
    public boolean generateOnPos(WorldGenLevel world, BlockPos pos, BlockState state, RandomSource random) {
        final BlockState groundState = world.getBlockState(pos.below());

        if(!groundState.is(BlockTags.DIRT)) {
            return false;
        }

        this.setPlantWithAge(state, world, pos, random.nextInt(this.getMaxAge() + 1));
        return true;
    }
}
