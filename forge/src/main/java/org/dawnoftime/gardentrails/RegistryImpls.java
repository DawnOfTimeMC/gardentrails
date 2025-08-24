package org.dawnoftime.gardentrails;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.dawnoftime.gardentrails.block.templates.FlowerPotBlockGT;
import org.dawnoftime.gardentrails.entity.SilkmothEntity;
import org.dawnoftime.gardentrails.item.IHasFlowerPot;
import org.dawnoftime.gardentrails.item.IconItem;
import org.dawnoftime.gardentrails.registry.*;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class RegistryImpls {
    public static class ForgeBlockEntitiesRegistry extends GTBlockEntitiesRegistry {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GTCommon.MOD_ID);

        @Override
        public <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String name, BiFunction<BlockPos, BlockState, T> factoryIn, Supplier<Block[]> validBlocksSupplier) {
            return BLOCK_ENTITY_TYPES_REGISTRY.register(name, () -> BlockEntityType.Builder.of(factoryIn::apply, validBlocksSupplier.get()).build(null));
        }
    }

    public static class ForgeBlocksRegistry extends GTBlocksRegistry {
        public static final DeferredRegister<Block> BLOCKS_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, GTCommon.MOD_ID);
        public static final DeferredRegister<Item> BLOCK_ITEMS_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, GTCommon.MOD_ID);
        public ForgeBlocksRegistry() {
            postRegister();
        }

        @SafeVarargs
        @Override
        public final <T extends Block, Y extends Item> Supplier<T> registerWithItem(String id, Supplier<T> block, Function<T, Y> item, TagKey<Block>... tags) {
            RegistryObject<T> registryBlock = BLOCKS_REGISTRY.register(id, block);
            if(item != null) {
                BLOCK_ITEMS_REGISTRY.register(id, () -> item.apply(registryBlock.get()));
            }
            if(tags.length == 0){
                addBlockTag(registryBlock, BlockTags.MINEABLE_WITH_PICKAXE);
            }else{
                for (TagKey<Block> tag : tags) {
                    addBlockTag(registryBlock, tag);
                }
            }
            return registryBlock;
        }

        @Override
        public <T extends Block, Y extends Item & IHasFlowerPot> Supplier<T> registerWithFlowerPotItem(String blockID, Supplier<T> block, String itemID, Function<T, Y> item) {
            RegistryObject<T> registryBlock = BLOCKS_REGISTRY.register(blockID, block);
            if (item != null) {
                final String potName = blockID + "_flower_pot";

                Supplier<FlowerPotBlockGT> potBlockObject = this.register(potName, () -> {
                    final FlowerPotBlockGT potBlock = new FlowerPotBlockGT(null);
                    POT_BLOCKS.put(potName, potBlock);
                    return potBlock;
                }, BlockTags.MINEABLE_WITH_PICKAXE);

                BLOCK_ITEMS_REGISTRY.register(itemID, () -> {
                    var item1 = item.apply(registryBlock.get());
                    FlowerPotBlockGT potBlock = potBlockObject.get();

                    item1.setPotBlock(potBlock);
                    potBlock.setItemInPot(item1);
                    return item1;
                });
            }
            addBlockTag(registryBlock, BlockTags.SWORD_EFFICIENT);
            return registryBlock;
        }
    }

    public static class ForgeEntitiesRegistry extends GTEntitiesRegistry {
        public static final DeferredRegister<EntityType<?>> ENTITY_TYPES_REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GTCommon.MOD_ID);
        @Override
        public <T extends Entity> Supplier<EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builder) {
            return ENTITY_TYPES_REGISTRY.register(name, () -> builder.get().build(name));
        }
    }

    public static class ForgeFeaturesRegistry extends GTFeaturesRegistry {
        public static final DeferredRegister<Feature<?>> FEATURES_REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, GTCommon.MOD_ID);

        @Override
        public <Y extends FeatureConfiguration, T extends Feature<Y>> Supplier<T> register(String name, Supplier<T> featureSupplier) {
            return FEATURES_REGISTRY.register(name, featureSupplier);
        }
    }

    public static class ForgeItemsRegistry extends GTItemsRegistry {
        public static final DeferredRegister<Item> ITEMS_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, GTCommon.MOD_ID);

        public final Supplier<Item> SILKMOTH_SPAWN_EGG = register("silkmoth_spawn_egg", () -> new ForgeSpawnEggItem(GTEntitiesRegistry.INSTANCE.SILKMOTH_ENTITY, 0xDBD8BD, 0xFEFEFC, new Item.Properties()));

        public ForgeItemsRegistry() {
            postRegister();
        }

        @Override
        public <T extends Item> Supplier<Item> register(String name, Supplier<T> itemSupplier) {
            return ITEMS_REGISTRY.register(name, itemSupplier);
        }

        @Override
        public <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(String name, Supplier<T> itemSupplier) {
            return registerWithFlowerPot(name, name, itemSupplier);
        }

        @Override
        public <T extends Item & IHasFlowerPot> Supplier<Item> registerWithFlowerPot(String plantName, String seedName, Supplier<T> itemSupplier) {
            final String potName = plantName + "_flower_pot";

            Supplier<FlowerPotBlockGT> potBlockObject = GTBlocksRegistry.INSTANCE.register(potName, () -> {
                final FlowerPotBlockGT potBlock = new FlowerPotBlockGT(null);
                GTBlocksRegistry.POT_BLOCKS.put(potName, potBlock);
                return potBlock;
            }, BlockTags.MINEABLE_WITH_PICKAXE);

            return this.register(seedName, () -> {
                T item = itemSupplier.get();
                FlowerPotBlockGT potBlock = potBlockObject.get();

                item.setPotBlock(potBlock);
                potBlock.setItemInPot(item);

                return item;
            });
        }
    }

    public static class ForgeRecipeSerializersRegistry extends GTRecipeSerializersRegistry {
        public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS_REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GTCommon.MOD_ID);

        @Override
        public <T extends RecipeSerializer<? extends Recipe<?>>> Supplier<T> register(String name, Supplier<T> recipeSerializer) {
            return RECIPE_SERIALIZERS_REGISTRY.register(name, recipeSerializer);
        }
    }

    public static class ForgeRecipeTypesRegistry extends GTRecipeTypesRegistry {
        public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES_REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, GTCommon.MOD_ID);

        @Override
        public <T extends Recipe<?>> Supplier<RecipeType<T>> register(String name) {
            return RECIPE_TYPES_REGISTRY.register(name, () -> RecipeType.simple(new ResourceLocation(GTCommon.MOD_ID, name)));
        }
    }

    public static class ForgeCreativeModeTabsRegistry extends GTCreativeModeTabsRegistry {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GTCommon.MOD_ID);

        @Override
        public <T extends CreativeModeTab> Supplier<CreativeModeTab> register(String name, Supplier<ItemStack> iconSupplier, Component title) {
            return CREATIVE_MODE_TABS_REGISTRY.register(name, () -> CreativeModeTab.builder().icon(iconSupplier).title(title).build());
        }
    }

    public static class ForgeTagsRegistry extends GTTags {
        @Override
        public TagKey<Block> registerBlock(ResourceLocation id) {
            return TagKey.create(Registries.BLOCK, id);
        }

        @Override
        public TagKey<Item> registerItem(ResourceLocation id) {
            return TagKey.create(Registries.ITEM, id);
        }
    }

    public static void init(IEventBus bus) {
        GTEntitiesRegistry.INSTANCE = new ForgeEntitiesRegistry();
        ForgeEntitiesRegistry.ENTITY_TYPES_REGISTRY.register(bus);

        GTBlocksRegistry.INSTANCE = new ForgeBlocksRegistry();
        GTItemsRegistry.INSTANCE = new ForgeItemsRegistry();
        GTBlockEntitiesRegistry.INSTANCE = new ForgeBlockEntitiesRegistry();
        GTFeaturesRegistry.INSTANCE = new ForgeFeaturesRegistry();
        GTRecipeSerializersRegistry.INSTANCE = new ForgeRecipeSerializersRegistry();
        GTRecipeTypesRegistry.INSTANCE = new ForgeRecipeTypesRegistry();
        GTTags.INSTANCE = new ForgeTagsRegistry();
        GTCreativeModeTabsRegistry.INSTANCE = new ForgeCreativeModeTabsRegistry();

        // Register all deffered registries
        ForgeBlocksRegistry.BLOCKS_REGISTRY.register(bus);
        ForgeBlocksRegistry.BLOCK_ITEMS_REGISTRY.register(bus);
        ForgeItemsRegistry.ITEMS_REGISTRY.register(bus);
        ForgeBlockEntitiesRegistry.BLOCK_ENTITY_TYPES_REGISTRY.register(bus);
        ForgeFeaturesRegistry.FEATURES_REGISTRY.register(bus);
        ForgeRecipeSerializersRegistry.RECIPE_SERIALIZERS_REGISTRY.register(bus);
        ForgeRecipeTypesRegistry.RECIPE_TYPES_REGISTRY.register(bus);
        ForgeCreativeModeTabsRegistry.CREATIVE_MODE_TABS_REGISTRY.register(bus);

        bus.addListener((EntityAttributeCreationEvent event) -> event.put(GTEntitiesRegistry.INSTANCE.SILKMOTH_ENTITY.get(), SilkmothEntity.createAttributes().build()));
        bus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if(event.getTab() == GTCreativeModeTabsRegistry.INSTANCE.GT_TAB.get()) {
                ForgeRegistries.ITEMS.getEntries().stream().filter(entry ->
                                entry.getKey().location().getNamespace().equalsIgnoreCase(GTCommon.MOD_ID) &&
                                        !(entry.getValue() instanceof IconItem))
                        .map(Map.Entry::getValue)
                        .forEachOrdered(event::accept);
            }
        });
    }
}
