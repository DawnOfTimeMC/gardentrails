package org.dawnoftime.dawnoftime.registry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.dawnoftime.dawnoftime.blockentity.DryerBlockEntity;

import java.util.function.BiFunction;
import java.util.function.Supplier;

public abstract class DoTBBlockEntitiesRegistry {
    public static DoTBBlockEntitiesRegistry INSTANCE;

    public final Supplier<BlockEntityType<DryerBlockEntity>> DRYER = register("dryer",
            DryerBlockEntity::new, () -> new Block[] { DoTBBlocksRegistry.INSTANCE.BAMBOO_DRYING_TRAY.get() });

    public abstract <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String name, BiFunction<BlockPos, BlockState, T> factoryIn, Supplier<Block[]> validBlocksSupplier);

}