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
    public final Supplier<Block> BOXWOOD_BUSH = register("boxwood_bush", () -> new BiomeColoredBushBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES), BUSH_SHAPES, IBiomeColoredBlock.ColorType.FOLIAGE), BlockTags.SWORD_EFFICIENT);
    public final Supplier<Block> BOXWOOD_TALL_HEDGE = register("boxwood_tall_hedge", () -> new BiomeColoredPlateBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES), IBiomeColoredBlock.ColorType.FOLIAGE), BlockTags.SWORD_EFFICIENT);
    public final Supplier<Block> BOXWOOD_SMALL_HEDGE = register("boxwood_small_hedge", () -> new BiomeColoredEdgeBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES), IBiomeColoredBlock.ColorType.FOLIAGE), BlockTags.SWORD_EFFICIENT);
    public Supplier<Block> IVY;
    public Supplier<Block> GERANIUM_PINK;
    public final Supplier<Block> PLANTER_GERANIUM_PINK = register("planter_geranium_pink", () -> new PlanterBlock(Block.Properties.copy(Blocks.CLAY).strength(0.6F).noOcclusion()));
    public final Supplier<Block> WILD_GRAPE = register("wild_grape", () -> new WildPlantBlock(Block.Properties.copy(Blocks.DANDELION)), BlockTags.SWORD_EFFICIENT);
    public final Supplier<Block> BAMBOO_DRYING_TRAY = register("bamboo_drying_tray", () -> new DryerBlock(Block.Properties.copy(Blocks.OAK_PLANKS).noOcclusion(), DRYER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public Supplier<GrowingBushBlock> CAMELLIA;
    public Supplier<MulberryBlock> MULBERRY;
    public final Supplier<WaterDoubleCropsBlock> RICE = registerWithItem("rice", () -> new WaterDoubleCropsBlock(2), (block) -> new SoilSeedsItem(block, null), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> STICK_BUNDLE = register("stick_bundle", () -> new StickBundleBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(2.0F, 3.0F).sound(SoundType.GRASS).noOcclusion()).setBurnable(), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> MAPLE_RED_TRUNK = registerWithItem("maple_red_trunk", () -> new MapleTrunkBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)), null, BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> MAPLE_RED_LEAVES = registerWithItem("maple_red_leaves", () -> new MapleLeavesBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)), null, BlockTags.MINEABLE_WITH_AXE);
    public Supplier<Block> MAPLE_RED_SAPLING;
    public final Supplier<Block> PAUSED_MAPLE_RED_SAPLING = registerWithItem("paused_maple_red_sapling", () -> new PausedMapleSaplingBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)), null, BlockTags.MINEABLE_WITH_AXE);
    public Supplier<SoilCropsBlock> COMMELINA;
    public final Supplier<Block> WILD_MAIZE = register("wild_maize", () -> new WildMaizeBlock(Block.Properties.copy(Blocks.DANDELION)), BlockTags.SWORD_EFFICIENT);
    public Supplier<DoubleCropsBlock> MAIZE;

    public void postRegister() {
        CYPRESS = registerWithFlowerPotItem("cypress", () -> new CypressBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES).randomTicks()).setBurnable(), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        MAIZE = registerWithFlowerPotItem("maize", () -> new DoubleCropsBlock(SoilCropsBlock.PlantType.CROP, 4), (block) -> new SoilSeedsItem(block, Foods.MAIZE));
        COMMELINA = registerWithFlowerPotItem("commelina", () -> new SoilCropsBlock(SoilCropsBlock.PlantType.PLAINS), (block) -> new SoilSeedsItem(block, null));
        MAPLE_RED_SAPLING = registerWithFlowerPotItem("maple_red_sapling", () -> new MapleSaplingBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        CAMELLIA = registerWithFlowerPotItem("camellia", () -> new GrowingBushBlock(SoilCropsBlock.PlantType.PLAINS, 3), "camellia_seeds", (block) -> new SoilSeedsItem(block, null));
        MULBERRY = registerWithFlowerPotItem("mulberry", () -> new MulberryBlock(SoilCropsBlock.PlantType.PLAINS, 3, 2), (block) -> new SoilSeedsItem(block, Foods.MULBERRY));
        IVY = registerWithFlowerPotItem("ivy", () -> new IvyBlock(Block.Properties.copy(Blocks.VINE).randomTicks().strength(0.2F).sound(SoundType.VINE)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        GERANIUM_PINK = registerWithFlowerPotItem("geranium_pink", () -> new GeraniumBlock(Block.Properties.copy(Blocks.SUNFLOWER).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.GRASS)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
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
