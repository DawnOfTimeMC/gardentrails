package org.dawnoftime.dawnoftime.registry;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

public abstract class DoTBMenuTypesRegistry {
	public static DoTBMenuTypesRegistry INSTANCE;

	public abstract <T extends AbstractContainerMenu> Supplier<MenuType<T>> register(String name, MenuTypeFactory<T> factory);

	@FunctionalInterface
	public interface MenuTypeFactory<T> {
		AbstractContainerMenu create(int windowId, Inventory playerInventory, FriendlyByteBuf additionalData);
	}
}
