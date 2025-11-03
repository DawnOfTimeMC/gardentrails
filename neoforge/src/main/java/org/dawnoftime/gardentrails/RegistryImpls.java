package org.dawnoftime.gardentrails;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
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
    public static class NeoForgeBlockEntitiesRegistry extends GTBlockEntitiesRegistry {
        public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES_REGISTRY = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, GTCommon.MOD_ID);

        @Override
        public <T extends BlockEntity> Supplier<BlockEntityType<T>> register(String name, BiFunction<BlockPos, BlockState, T> factoryIn, Supplier<Block[]> validBlocksSupplier) {
            return BLOCK_ENTITY_TYPES_REGISTRY.register(name, () -> BlockEntityType.Builder.of(factoryIn::apply, validBlocksSupplier.get()).build(null));
        }
    }

    public static class NeoForgeBlocksRegistry extends GTBlocksRegistry {
        public static final DeferredRegister<Block> BLOCKS_REGISTRY = DeferredRegister.create(Registries.BLOCK, GTCommon.MOD_ID);
        public static final DeferredRegister<Item> BLOCK_ITEMS_REGISTRY = DeferredRegister.create(Registries.ITEM, GTCommon.MOD_ID);

        public NeoForgeBlocksRegistry() {
            postRegister();
        }

        @Override
        public <T extends Block, Y extends Item> Supplier<T> registerWithItem(String id, Supplier<T> block, Function<T, Y> item, TagKey<Block>... tags) {
            DeferredHolder<Block, T> registryBlock = BLOCKS_REGISTRY.register(id, block);
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
            DeferredHolder<Block, T> registryBlock = BLOCKS_REGISTRY.register(blockID, block);
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


    public static class NeoForgeEntitiesRegistry extends GTEntitiesRegistry {
        public static final DeferredRegister<EntityType<?>> ENTITY_TYPES_REGISTRY = DeferredRegister.create(Registries.ENTITY_TYPE, GTCommon.MOD_ID);
        @Override
        public <T extends Entity> Supplier<EntityType<T>> register(String name, Supplier<EntityType.Builder<T>> builder) {
            return ENTITY_TYPES_REGISTRY.register(name, () -> builder.get().build(name));
        }
    }


    public static class NeoForgeFeaturesRegistry extends GTFeaturesRegistry {
        public static final DeferredRegister<Feature<?>> FEATURES_REGISTRY = DeferredRegister.create(Registries.FEATURE, GTCommon.MOD_ID);

        @Override
        public <Y extends FeatureConfiguration, T extends Feature<Y>> Supplier<T> register(String name, Supplier<T> featureSupplier) {
            return FEATURES_REGISTRY.register(name, featureSupplier);
        }
    }


    public static class NeoForgeItemsRegistry extends GTItemsRegistry {
        public static final DeferredRegister<Item> ITEMS_REGISTRY = DeferredRegister.create(Registries.ITEM, GTCommon.MOD_ID);

        public final Supplier<Item> SILKMOTH_SPAWN_EGG = register("silkmoth_spawn_egg", () -> new SpawnEggItem(GTEntitiesRegistry.INSTANCE.SILKMOTH_ENTITY.get(), 0xDBD8BD, 0xFEFEFC, new Item.Properties()));
        public NeoForgeItemsRegistry() {
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

    public static class NeoForgeRecipeSerializersRegistry extends GTRecipeSerializersRegistry {
        public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS_REGISTRY = DeferredRegister.create(Registries.RECIPE_SERIALIZER, GTCommon.MOD_ID);

        @Override
        public <T extends RecipeSerializer<? extends Recipe<?>>> Supplier<T> register(String name, Supplier<T> recipeSerializer) {
            return RECIPE_SERIALIZERS_REGISTRY.register(name, recipeSerializer);
        }
    }

    public static class NeoForgeRecipeTypesRegistry extends GTRecipeTypesRegistry {
        public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES_REGISTRY = DeferredRegister.create(Registries.RECIPE_TYPE, GTCommon.MOD_ID);

        @Override
        public <T extends Recipe<?>> Supplier<RecipeType<T>> register(String name) {
            return RECIPE_TYPES_REGISTRY.register(name, () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(GTCommon.MOD_ID, name)));
        }
    }

    public static class NeoForgeCreativeModeTabsRegistry extends GTCreativeModeTabsRegistry {
        public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS_REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GTCommon.MOD_ID);

        @Override
        public <T extends CreativeModeTab> Supplier<CreativeModeTab> register(String name, Supplier<ItemStack> iconSupplier, Component title) {
            return CREATIVE_MODE_TABS_REGISTRY.register(name, () -> CreativeModeTab.builder().icon(iconSupplier).title(title).build());
        }
    }

    public static class NeoForgeTagsRegistry extends GTTags {
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
        GTEntitiesRegistry.INSTANCE = new NeoForgeEntitiesRegistry();
        NeoForgeEntitiesRegistry.ENTITY_TYPES_REGISTRY.register(bus);

        GTBlocksRegistry.INSTANCE = new NeoForgeBlocksRegistry();
        GTItemsRegistry.INSTANCE = new NeoForgeItemsRegistry();
        GTBlockEntitiesRegistry.INSTANCE = new NeoForgeBlockEntitiesRegistry();
        GTFeaturesRegistry.INSTANCE = new NeoForgeFeaturesRegistry();
        GTRecipeSerializersRegistry.INSTANCE = new NeoForgeRecipeSerializersRegistry();
        GTRecipeTypesRegistry.INSTANCE = new NeoForgeRecipeTypesRegistry();
        GTTags.INSTANCE = new NeoForgeTagsRegistry();
        GTCreativeModeTabsRegistry.INSTANCE = new NeoForgeCreativeModeTabsRegistry();

        // Register all deffered registries
        NeoForgeBlocksRegistry.BLOCKS_REGISTRY.register(bus);
        NeoForgeBlocksRegistry.BLOCK_ITEMS_REGISTRY.register(bus);
        NeoForgeItemsRegistry.ITEMS_REGISTRY.register(bus);
        NeoForgeBlockEntitiesRegistry.BLOCK_ENTITY_TYPES_REGISTRY.register(bus);
        NeoForgeFeaturesRegistry.FEATURES_REGISTRY.register(bus);
        NeoForgeRecipeSerializersRegistry.RECIPE_SERIALIZERS_REGISTRY.register(bus);
        NeoForgeRecipeTypesRegistry.RECIPE_TYPES_REGISTRY.register(bus);
        NeoForgeCreativeModeTabsRegistry.CREATIVE_MODE_TABS_REGISTRY.register(bus);

        bus.addListener((EntityAttributeCreationEvent event) -> event.put(GTEntitiesRegistry.INSTANCE.SILKMOTH_ENTITY.get(), SilkmothEntity.createAttributes().build()));
        bus.addListener((BuildCreativeModeTabContentsEvent event) -> {
            if(event.getTab() == GTCreativeModeTabsRegistry.INSTANCE.GT_TAB.get()) {
                BuiltInRegistries.ITEM.entrySet().stream().filter(entry ->
                                entry.getKey().location().getNamespace().equalsIgnoreCase(GTCommon.MOD_ID) &&
                                        !(entry.getValue() instanceof IconItem))
                        .map(Map.Entry::getValue)
                        .forEachOrdered(event::accept);
            }
        });
    }
}
