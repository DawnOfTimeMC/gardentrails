package org.dawnoftime.gardentrails.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.dawnoftime.gardentrails.block.IBiomeColoredBlock;
import org.dawnoftime.gardentrails.block.german.*;
import org.dawnoftime.gardentrails.block.japanese.*;
import org.dawnoftime.gardentrails.block.precolumbian.*;
import org.dawnoftime.gardentrails.block.roman.*;
import org.dawnoftime.gardentrails.block.templates.*;
import org.dawnoftime.gardentrails.item.IHasFlowerPot;
import org.dawnoftime.gardentrails.item.templates.PotAndBlockItem;
import org.dawnoftime.gardentrails.item.templates.SoilSeedsItem;
import org.dawnoftime.gardentrails.util.Foods;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static org.dawnoftime.gardentrails.util.VoxelShapes.*;

@SuppressWarnings({"unused", "unchecked"})
public abstract class GTBlocksRegistry {
    public static GTBlocksRegistry INSTANCE;

    public static Map<TagKey<Block>, Set<Supplier<Block>>> blockTagsMap = new HashMap<>();
    public static final HashMap<String, Block> POT_BLOCKS = new HashMap<>();

    public Supplier<Block> CYPRESS;
    public final Supplier<Block> BOXWOOD_BUSH = register("boxwood_bush", () -> new BiomeColoredBushBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES), BUSH_SHAPES, IBiomeColoredBlock.ColorType.FOLIAGE), BlockTags.SWORD_EFFICIENT);
    public final Supplier<Block> BOXWOOD_TALL_HEDGE = register("boxwood_tall_hedge", () -> new BiomeColoredPlateBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES), IBiomeColoredBlock.ColorType.FOLIAGE), BlockTags.SWORD_EFFICIENT);
    public final Supplier<Block> BOXWOOD_SMALL_HEDGE = register("boxwood_small_hedge", () -> new BiomeColoredEdgeBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES), IBiomeColoredBlock.ColorType.FOLIAGE), BlockTags.SWORD_EFFICIENT);
    public Supplier<Block> IVY;
    public Supplier<Block> GERANIUM_PINK;
    public Supplier<Block> GERANIUM_ORANGE;
    public Supplier<Block> GERANIUM_PURPLE;
    public Supplier<Block> GERANIUM_PURPLEISH;
    public Supplier<Block> GERANIUM_RED;
    public Supplier<Block> GERANIUM_WHITE;
    public final Supplier<Block> PLANTER_GERANIUM_PINK = register("planter_geranium_pink", () -> new PlanterBlock(Block.Properties.ofFullCopy(Blocks.CLAY).strength(0.6F).noOcclusion()));
    public final Supplier<Block> WILD_GRAPE = register("wild_grape", () -> new WildPlantBlock(Block.Properties.ofFullCopy(Blocks.DANDELION)), BlockTags.SWORD_EFFICIENT);
    public final Supplier<Block> BAMBOO_DRYING_TRAY = register("bamboo_drying_tray", () -> new DryerBlock(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> OAK_DRYING_TRAY = register("oak_drying_tray", () -> new DryerBlock(Block.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> BIRCH_DRYING_TRAY = register("birch_drying_tray", () -> new DryerBlock(Block.Properties.ofFullCopy(Blocks.BIRCH_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> ACACIA_DRYING_TRAY = register("acacia_drying_tray", () -> new DryerBlock(Block.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> MANGROVE_DRYING_TRAY = register("mangrove_drying_tray", () -> new DryerBlock(Block.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> JUNGLE_DRYING_TRAY = register("jungle_drying_tray", () -> new DryerBlock(Block.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> CHERRY_DRYING_TRAY = register("cherry_drying_tray", () -> new DryerBlock(Block.Properties.ofFullCopy(Blocks.CHERRY_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> DARK_OAK_DRYING_TRAY = register("dark_oak_drying_tray", () -> new DryerBlock(Block.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> SPRUCE_DRYING_TRAY = register("spruce_drying_tray", () -> new DryerBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public Supplier<ColoredGrowingBushBlock> CAMELLIA;
    public Supplier<MulberryBlock> MULBERRY;
    public final Supplier<WaterDoubleCropsBlock> RICE = registerWithItem("rice", () -> new WaterDoubleCropsBlock(2), (block) -> new SoilSeedsItem(block, null), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> STICK_BUNDLE = register("stick_bundle", () -> new StickBundleBlock(Block.Properties.ofFullCopy(Blocks.OAK_WOOD).strength(2.0F, 3.0F).sound(SoundType.GRASS).noOcclusion()).setBurnable(), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> MAPLE_RED_TRUNK = registerWithItem("maple_red_trunk", () -> new MapleTrunkBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)), null, BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> MAPLE_RED_LEAVES = registerWithItem("maple_red_leaves", () -> new MapleLeavesBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)), null, BlockTags.MINEABLE_WITH_AXE);
    public Supplier<Block> MAPLE_RED_SAPLING;
    public final Supplier<Block> PAUSED_MAPLE_RED_SAPLING = registerWithItem("paused_maple_red_sapling", () -> new PausedMapleSaplingBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)), null, BlockTags.MINEABLE_WITH_AXE);
    public Supplier<SoilCropsBlock> COMMELINA;
    public final Supplier<Block> WILD_MAIZE = register("wild_maize", () -> new WildMaizeBlock(Block.Properties.ofFullCopy(Blocks.DANDELION)), BlockTags.SWORD_EFFICIENT);
    public Supplier<DoubleCropsBlock> MAIZE;
    public final Supplier<PergolaBlock> IRON_PERGOLA = register("iron_pergola", () -> new PergolaBlock(Block.Properties.ofFullCopy(Blocks.IRON_BARS),
            () -> INSTANCE.IRON_PERGOLA_VINE.get(), () -> INSTANCE.IRON_PERGOLA_IVY.get(), () -> INSTANCE.IRON_PERGOLA_GRAPE.get()));
    public final Supplier<PergolaBlock> IRON_PERGOLA_VINE = registerWithItem("iron_pergola_vine", () -> new PergolaPlantBlock(Block.Properties.ofFullCopy(Blocks.IRON_BARS),
            () -> INSTANCE.IRON_PERGOLA.get(), () -> INSTANCE.IRON_PERGOLA_VINE.get(), () -> INSTANCE.IRON_PERGOLA_IVY.get(), () -> INSTANCE.IRON_PERGOLA_GRAPE.get()), null);
    public final Supplier<PergolaBlock> IRON_PERGOLA_IVY = registerWithItem("iron_pergola_ivy", () -> new PergolaPlantBlock(Block.Properties.ofFullCopy(Blocks.IRON_BARS),
            () -> INSTANCE.IRON_PERGOLA.get(), () -> INSTANCE.IRON_PERGOLA_VINE.get(), () -> INSTANCE.IRON_PERGOLA_IVY.get(), () -> INSTANCE.IRON_PERGOLA_GRAPE.get()), null);
    public final Supplier<PergolaBlock> IRON_PERGOLA_GRAPE = registerWithItem("iron_pergola_grape", () -> new PergolaCropBlock(Block.Properties.ofFullCopy(Blocks.IRON_BARS), 4, 6, 0, 2, 2,
            () -> INSTANCE.IRON_PERGOLA.get(), () -> INSTANCE.IRON_PERGOLA_VINE.get(), () -> INSTANCE.IRON_PERGOLA_IVY.get(), () -> INSTANCE.IRON_PERGOLA_GRAPE.get()), null);

    public final Supplier<PergolaBlock> COPPER_PERGOLA = register("copper_pergola", () -> new PergolaBlock(Block.Properties.ofFullCopy(Blocks.COPPER_BLOCK).noOcclusion(),
            () -> INSTANCE.COPPER_PERGOLA_VINE.get(), () -> INSTANCE.COPPER_PERGOLA_IVY.get(), () -> INSTANCE.COPPER_PERGOLA_GRAPE.get()));
    public final Supplier<PergolaBlock> COPPER_PERGOLA_VINE = registerWithItem("copper_pergola_vine", () -> new PergolaPlantBlock(Block.Properties.ofFullCopy(Blocks.COPPER_BLOCK).noOcclusion(),
            () -> INSTANCE.COPPER_PERGOLA.get(), () -> INSTANCE.COPPER_PERGOLA_VINE.get(), () -> INSTANCE.COPPER_PERGOLA_IVY.get(), () -> INSTANCE.COPPER_PERGOLA_GRAPE.get()), null);
    public final Supplier<PergolaBlock> COPPER_PERGOLA_IVY = registerWithItem("copper_pergola_ivy", () -> new PergolaPlantBlock(Block.Properties.ofFullCopy(Blocks.COPPER_BLOCK).noOcclusion(),
            () -> INSTANCE.COPPER_PERGOLA.get(), () -> INSTANCE.COPPER_PERGOLA_VINE.get(), () -> INSTANCE.COPPER_PERGOLA_IVY.get(), () -> INSTANCE.COPPER_PERGOLA_GRAPE.get()), null);
    public final Supplier<PergolaBlock> COPPER_PERGOLA_GRAPE = registerWithItem("copper_pergola_grape", () -> new PergolaCropBlock(Block.Properties.ofFullCopy(Blocks.COPPER_BLOCK).noOcclusion(), 4, 6, 0, 2, 2,
            () -> INSTANCE.COPPER_PERGOLA.get(), () -> INSTANCE.COPPER_PERGOLA_VINE.get(), () -> INSTANCE.COPPER_PERGOLA_IVY.get(), () -> INSTANCE.COPPER_PERGOLA_GRAPE.get()), null);

    public final Supplier<PergolaBlock> OXIDIZED_COPPER_PERGOLA = register("oxidized_copper_pergola", () -> new PergolaBlock(Block.Properties.ofFullCopy(Blocks.OXIDIZED_COPPER).noOcclusion(),
            () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_VINE.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_IVY.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_GRAPE.get()));
    public final Supplier<PergolaBlock> OXIDIZED_COPPER_PERGOLA_VINE = registerWithItem("oxidized_copper_pergola_vine", () -> new PergolaPlantBlock(Block.Properties.ofFullCopy(Blocks.OXIDIZED_COPPER).noOcclusion(),
            () -> INSTANCE.OXIDIZED_COPPER_PERGOLA.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_VINE.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_IVY.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_GRAPE.get()), null);
    public final Supplier<PergolaBlock> OXIDIZED_COPPER_PERGOLA_IVY = registerWithItem("oxidized_copper_pergola_ivy", () -> new PergolaPlantBlock(Block.Properties.ofFullCopy(Blocks.OXIDIZED_COPPER).noOcclusion(),
            () -> INSTANCE.OXIDIZED_COPPER_PERGOLA.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_VINE.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_IVY.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_GRAPE.get()), null);
    public final Supplier<PergolaBlock> OXIDIZED_COPPER_PERGOLA_GRAPE = registerWithItem("oxidized_copper_pergola_grape", () -> new PergolaCropBlock(Block.Properties.ofFullCopy(Blocks.OXIDIZED_COPPER).noOcclusion(), 4, 6, 0, 2, 2,
            () -> INSTANCE.OXIDIZED_COPPER_PERGOLA.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_VINE.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_IVY.get(), () -> INSTANCE.OXIDIZED_COPPER_PERGOLA_GRAPE.get()), null);

    public void postRegister() {
        CYPRESS = registerWithFlowerPotItem("cypress", () -> new CypressBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES).randomTicks()).setBurnable(), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        MAIZE = registerWithFlowerPotItem("maize", () -> new DoubleCropsBlock(SoilCropsBlock.PlantType.CROP, 4), (block) -> new SoilSeedsItem(block, Foods.MAIZE));
        COMMELINA = registerWithFlowerPotItem("commelina", () -> new SoilCropsBlock(SoilCropsBlock.PlantType.PLAINS), (block) -> new SoilSeedsItem(block, null));
        MAPLE_RED_SAPLING = registerWithFlowerPotItem("maple_red_sapling", () -> new MapleSaplingBlock(Block.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        CAMELLIA = registerWithFlowerPotItem("camellia", () -> new ColoredGrowingBushBlock(SoilCropsBlock.PlantType.PLAINS, 3), "camellia_seeds", (block) -> new SoilSeedsItem(block, null));
        MULBERRY = registerWithFlowerPotItem("mulberry", () -> new MulberryBlock(SoilCropsBlock.PlantType.PLAINS, 3, 2), (block) -> new SoilSeedsItem(block, Foods.MULBERRY));
        IVY = registerWithFlowerPotItem("ivy", () -> new IvyBlock(Block.Properties.ofFullCopy(Blocks.VINE).randomTicks().strength(0.2F).sound(SoundType.VINE)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        GERANIUM_PINK = registerWithFlowerPotItem("geranium_pink", () -> new GeraniumBlock(Block.Properties.ofFullCopy(Blocks.SUNFLOWER).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.GRASS)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        GERANIUM_ORANGE = registerWithFlowerPotItem("geranium_orange", () -> new GeraniumBlock(Block.Properties.ofFullCopy(Blocks.SUNFLOWER).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.GRASS)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        GERANIUM_PURPLE = registerWithFlowerPotItem("geranium_purple", () -> new GeraniumBlock(Block.Properties.ofFullCopy(Blocks.SUNFLOWER).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.GRASS)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        GERANIUM_PURPLEISH = registerWithFlowerPotItem("geranium_purpleish", () -> new GeraniumBlock(Block.Properties.ofFullCopy(Blocks.SUNFLOWER).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.GRASS)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        GERANIUM_RED = registerWithFlowerPotItem("geranium_red", () -> new GeraniumBlock(Block.Properties.ofFullCopy(Blocks.SUNFLOWER).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.GRASS)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        GERANIUM_WHITE = registerWithFlowerPotItem("geranium_white", () -> new GeraniumBlock(Block.Properties.ofFullCopy(Blocks.SUNFLOWER).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.GRASS)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
    }

    public <T extends Block> Supplier<T> register(String id, Supplier<T> block) {
        return this.registerWithItem(id, block, (T blockObject) -> new BlockItem(blockObject, new Item.Properties()));
    }

    public <T extends Block> Supplier<T> register(String id, Supplier<T> block, TagKey<Block>... tagKeys) {
        return this.registerWithItem(id, block, (T blockObject) -> new BlockItem(blockObject, new Item.Properties()), tagKeys);
    }

    public abstract <T extends Block, Y extends Item> Supplier<T> registerWithItem(String id, Supplier<T> block, Function<T, Y> item, TagKey<Block>... tags);
    public abstract <T extends Block, Y extends Item & IHasFlowerPot> Supplier<T> registerWithFlowerPotItem(String blockID, Supplier<T> block, String itemID, Function<T, Y> item);

    public <T extends Block, Y extends Item & IHasFlowerPot> Supplier<T> registerWithFlowerPotItem(String id, Supplier<T> block, Function<T, Y> item) {
        return this.registerWithFlowerPotItem(id, block, id, item);
    }
    

    public <T extends Block> void addBlockTag(Supplier<T> block, TagKey<Block> tag){
        blockTagsMap.computeIfAbsent(tag, k -> new HashSet<>()).add((Supplier<Block>) block);
    }

    //Old Function
    private static ToIntFunction<BlockState> litBlockEmission(final int lightValue) {
        return state -> state.getValue(BlockStateProperties.LIT) ? lightValue : 0;
    }
}
