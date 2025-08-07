package org.dawnoftime.dawnoftime.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.dawnoftime.dawnoftime.platform.Services;
import org.dawnoftime.dawnoftime.util.BlockStatePropertiesAA;
import org.dawnoftime.dawnoftime.util.Utils;

import java.util.List;

import static net.minecraft.world.level.block.LeavesBlock.PERSISTENT;
import static org.dawnoftime.dawnoftime.util.BlockStatePropertiesAA.AGE_0_6;
import static org.dawnoftime.dawnoftime.util.BlockStatePropertiesAA.CLIMBING_PLANT;

public interface IBlockClimbingPlant {

    default void tickPlant(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource random) {
        if(!worldIn.isClientSide()) {
            if(stateIn.getValue(CLIMBING_PLANT).hasNoPlant() || stateIn.getValue(PERSISTENT))
                return;
            if(!worldIn.isLoaded(pos))
                return; // Forge: prevent loading unloaded chunks when checking neighbor's light

            if(worldIn.getRawBrightness(pos, 0) >= 8) {
                int age = stateIn.getValue(AGE_0_6);
				if (random.nextInt(Services.PLATFORM.getConfig().climbingPlantGrowthChance) == 0) {//Probability "can grow"
					if(age < 2){
						this.placePlant(stateIn.setValue(AGE_0_6, age + 1), worldIn, pos, 2);
						return;
					}else{
						if(stateIn.getValue(CLIMBING_PLANT).canGrow(worldIn, age)){
							this.placePlant(stateIn.setValue(AGE_0_6, 2 + ((age - 1) % 5)), worldIn, pos, 2);
							return;
						}
					}
				}
                if(age < 2 || random.nextInt(Services.PLATFORM.getConfig().climbingPlantSpreadChance) != 0)
                    return;//Probability "can spread"
                BlockPos[] positions = new BlockPos[] {
                        pos.north(),
                        pos.east(),
                        pos.south(),
                        pos.west(),
                        pos.above()
                };
                int index = random.nextInt(5);//Probability "chose the adjacent block to grow on"
                BlockState newState = worldIn.getBlockState(positions[index]);
                if(newState.getBlock() instanceof IBlockClimbingPlant newBlock) {
                    if(newBlock.canHavePlant(newState) && newState.getValue(CLIMBING_PLANT).hasNoPlant()) {
                        newBlock.placePlant(newState.setValue(CLIMBING_PLANT, stateIn.getValue(CLIMBING_PLANT)), worldIn, positions[index], 2);
                    }
                }
            }
        }
    }

    default boolean tryPlacingPlant(BlockState stateIn, Level worldIn, BlockPos pos, Player player, InteractionHand handIn) {
        if(player.isCrouching())
            return false;
        ItemStack heldItemStack = player.getItemInHand(handIn);
        if(this.canHavePlant(stateIn) && stateIn.getValue(CLIMBING_PLANT).hasNoPlant()) {
            BlockStatePropertiesAA.ClimbingPlant plant = BlockStatePropertiesAA.ClimbingPlant.getFromItem(heldItemStack.getItem());
            if(!plant.hasNoPlant()) {
                stateIn = stateIn.setValue(CLIMBING_PLANT, plant);
                if(!player.isCreative()) {
                    heldItemStack.shrink(1);
                }
                this.placePlant(stateIn, worldIn, pos, 10);
                return true;
            }
        }
        return false;
    }

    default void placePlant(BlockState state, Level world, BlockPos pos, int option) {
        world.setBlock(pos, state, option);
    }

    default InteractionResult harvestPlant(BlockState stateIn, Level worldIn, BlockPos pos, Player player, InteractionHand handIn) {
        if(player.isCreative() && stateIn.getValue(PERSISTENT) && !stateIn.getValue(CLIMBING_PLANT).hasNoPlant()) {
            if(player.isCrouching()) {
                if(stateIn.getValue(AGE_0_6) > 0) {
                    this.placePlant(stateIn.setValue(AGE_0_6, stateIn.getValue(AGE_0_6) - 1), worldIn, pos, 10);
                } else {
                    this.placePlant(stateIn.setValue(CLIMBING_PLANT, BlockStatePropertiesAA.ClimbingPlant.NONE).setValue(AGE_0_6, 0), worldIn, pos, 10);
                }
                return InteractionResult.SUCCESS;
            } else {
                if(stateIn.getValue(AGE_0_6) < stateIn.getValue(CLIMBING_PLANT).maxAge()) {
                    this.placePlant(stateIn.setValue(AGE_0_6, stateIn.getValue(AGE_0_6) + 1), worldIn, pos, 10);
                    return InteractionResult.SUCCESS;
                }
                return InteractionResult.PASS;
            }
        } else {
            if(stateIn.getValue(AGE_0_6) > 2) {
                if(this.dropPlant(stateIn, worldIn, pos, player.getItemInHand(handIn), true)) {
                    stateIn = stateIn.setValue(AGE_0_6, 2);
                    this.placePlant(stateIn, worldIn, pos, 10);
                    worldIn.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
                    return InteractionResult.SUCCESS;
                }
            }
            if(player.isCrouching()) {
                return tryRemovingPlant(stateIn, worldIn, pos, player.getItemInHand(handIn));
            }
            return InteractionResult.PASS;
        }
    }

    default InteractionResult tryRemovingPlant(BlockState stateIn, Level worldIn, BlockPos pos, ItemStack heldItemStack) {
        if(!stateIn.getValue(CLIMBING_PLANT).hasNoPlant()) {
            stateIn = this.removePlant(stateIn, worldIn, pos, heldItemStack);
            this.placePlant(stateIn, worldIn, pos, 10);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    default BlockState removePlant(BlockState stateIn, LevelAccessor worldIn, BlockPos pos, ItemStack heldItemStack) {
        this.dropPlant(stateIn, worldIn, pos, heldItemStack, true);
        worldIn.playSound(null, pos, SoundEvents.GRASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        stateIn = stateIn.setValue(CLIMBING_PLANT, BlockStatePropertiesAA.ClimbingPlant.NONE).setValue(AGE_0_6, 0);
        return stateIn;
    }

    default boolean dropPlant(BlockState stateIn, LevelAccessor worldIn, BlockPos pos, ItemStack heldItemStack, boolean bool) {
        if(worldIn.isClientSide())
            return false;
        BlockStatePropertiesAA.ClimbingPlant plant = stateIn.getValue(CLIMBING_PLANT);
        if(plant.hasNoPlant())
            return false;
        List<ItemStack> drops = Utils.getLootList((ServerLevel) worldIn, stateIn, heldItemStack, plant.getSerializedName() + "_" + stateIn.getValue(AGE_0_6));
        return Utils.dropLootFromList(worldIn, pos, drops, 1.0F);
    }

    default boolean canHavePlant(BlockState state) {
        if(state.getBlock() instanceof SimpleWaterloggedBlock) {
            return !state.getValue(BlockStateProperties.WATERLOGGED);
        }
        return true;
    }

    default boolean canSustainClimbingPlant(BlockState stateUnder) {
        Block block = stateUnder.getBlock();
        return block == Blocks.GRASS_BLOCK || stateUnder.is(BlockTags.DIRT);
    }
}
