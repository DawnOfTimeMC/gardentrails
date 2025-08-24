package org.dawnoftime.gardentrails.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import org.dawnoftime.gardentrails.entity.SilkmothEntity;

import java.util.function.Supplier;

public abstract class GTEntitiesRegistry {
    public static GTEntitiesRegistry INSTANCE;
    public final Supplier<EntityType<SilkmothEntity>> SILKMOTH_ENTITY = register("silkmoth", () -> EntityType.Builder.<SilkmothEntity>of((type, world) -> new SilkmothEntity(world), MobCategory.AMBIENT).sized(0.3F, 0.3F));

    public abstract <T extends Entity> Supplier<EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builder);
}