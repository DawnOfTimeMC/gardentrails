package org.dawnoftime.dawnoftime.registry;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import org.dawnoftime.dawnoftime.block.french.*;
import org.dawnoftime.dawnoftime.block.general.*;
import org.dawnoftime.dawnoftime.block.german.*;
import org.dawnoftime.dawnoftime.block.japanese.*;
import org.dawnoftime.dawnoftime.block.templates.LanternBlock;
import org.dawnoftime.dawnoftime.block.persian.MoraqMosaicColumnBlock;
import org.dawnoftime.dawnoftime.block.precolumbian.*;
import org.dawnoftime.dawnoftime.block.roman.*;
import org.dawnoftime.dawnoftime.block.templates.*;
import org.dawnoftime.dawnoftime.item.IHasFlowerPot;
import org.dawnoftime.dawnoftime.item.templates.PotAndBlockItem;
import org.dawnoftime.dawnoftime.item.templates.SoilSeedsItem;
import org.dawnoftime.dawnoftime.util.Foods;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static org.dawnoftime.dawnoftime.util.VoxelShapes.*;

@SuppressWarnings({"unused", "unchecked"})
public abstract class DoTBBlocksRegistry {
    public static DoTBBlocksRegistry INSTANCE;
    public static Map<TagKey<Block>, Set<Supplier<Block>>> blockTagsMap = new HashMap<>();
    public static final HashMap<String, Block> POT_BLOCKS = new HashMap<>();
    public Supplier<Block> CYPRESS;
    public final Supplier<Block> BOXWOOD_BUSH = register("boxwood_bush", () -> new BushBlockDoT(Block.Properties.copy(Blocks.SPRUCE_LEAVES), BUSH_SHAPES), BlockTags.SWORD_EFFICIENT);
    public final Supplier<Block> BOXWOOD_TALL_HEDGE = register("boxwood_tall_hedge", () -> new PlateBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)), BlockTags.SWORD_EFFICIENT);
    public final Supplier<Block> BOXWOOD_SMALL_HEDGE = register("boxwood_small_hedge", () -> new EdgeBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)), BlockTags.SWORD_EFFICIENT);
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

    // French
    public final Supplier<Block> LIMESTONE_BALUSTER = register("limestone_baluster", () -> new PlateBlock(Block.Properties.copy(Blocks.STONE_BRICKS).noOcclusion()));
    public final Supplier<Block> LIMESTONE_SIDED_COLUMN = register("limestone_sided_column", () -> new ConnectedVerticalSidedBlock(Block.Properties.copy(Blocks.STONE_BRICKS), LIMESTONE_SIDED_COLUMN_SHAPES));
    public final Supplier<Block> LIMESTONE_CHIMNEY = register("limestone_chimney", () -> new AxisChimneyBlock(Block.Properties.copy(Blocks.STONE_BRICKS), LIMESTONE_CHIMNEY_SHAPES));
    public final Supplier<Block> LIMESTONE_FIREPLACE = register("limestone_fireplace", () -> new ConnectedVerticalSidedPlanFireplaceBlock(Block.Properties.copy(Blocks.STONE_BRICKS).noOcclusion().lightLevel(litBlockEmission(15))));
    public final Supplier<Block> BLACK_WROUGHT_IRON_BALUSTER = register("black_wrought_iron_baluster", () -> new PlateBlock(Block.Properties.copy(Blocks.IRON_BARS), THIN_PLATE_SHAPES));
    public final Supplier<Block> BLACK_WROUGHT_IRON_FENCE = register("black_wrought_iron_fence", () -> new IronFenceBlock(Block.Properties.copy(Blocks.IRON_BARS)));
    public final Supplier<Block> REINFORCED_BLACK_WROUGHT_IRON_FENCE = register("reinforced_black_wrought_iron_fence", () -> new ReinforcedIronFenceBlock(Block.Properties.copy(Blocks.IRON_BARS)));
    public final Supplier<Block> REINFORCED_GOLDEN_WROUGHT_IRON_FENCE = register("reinforced_golden_wrought_iron_fence", () -> new ReinforcedIronFenceBlock(Block.Properties.copy(Blocks.IRON_BARS)));
    public final Supplier<Block> FIREPLACE = register("fireplace", () -> new FireplaceBlock(Block.Properties.copy(Blocks.STONE).strength(1.5F, 6.0F).lightLevel(DoTBBlocksRegistry.litBlockEmission(15))));
    public final Supplier<Block> IRON_PORTCULLIS = register("iron_portcullis", () -> new PortcullisBlock(Block.Properties.copy(Blocks.IRON_DOOR)));
    public final Supplier<Block> IRON_FANCY_LANTERN = register("iron_fancy_lantern", () -> new LanternBlock(Block.Properties.copy(Blocks.IRON_BARS).noOcclusion().lightLevel(state -> 15), IRON_FANCY_LANTERN_SHAPES));
    public final Supplier<Block> IRON_COLUMN = register("iron_column", () -> new IronColumnBlock(Block.Properties.copy(Blocks.IRON_BARS)));
    public final Supplier<Block> WROUGHT_IRON_FENCE = register("wrought_iron_fence", () -> new IronFenceBlock(Block.Properties.copy(Blocks.IRON_BARS)));
    public final Supplier<Block> WATER_FLOWING_TRICKLE = registerWithItem("water_flowing_trickle", () -> new WaterFlowingTrickleBlock(Block.Properties.copy(Blocks.WATER).randomTicks()), null);
    public final Supplier<Block> WATER_SOURCE_TRICKLE = register("water_source_trickle", () -> new WaterSourceTrickleBlock(Block.Properties.copy(Blocks.SEAGRASS).randomTicks()));

    // German
    public final Supplier<Block> LATTICE_GLASS = register("lattice_glass", () -> new GlassBlock(Block.Properties.copy(Blocks.GLASS)));
    public final Supplier<Block> LATTICE_GLASS_PANE = register("lattice_glass_pane", () -> new PaneBlockDoT(Block.Properties.copy(Blocks.GLASS)));
    public final Supplier<Block> LATTICE_WAXED_OAK_WINDOW = register("lattice_waxed_oak_window", () -> new SidedWindowBlock(Block.Properties.copy(Blocks.GLASS), SIDED_WINDOW_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> LATTICE_STONE_BRICKS_WINDOW = register("lattice_stone_bricks_window", () -> new SidedWindowBlock(Block.Properties.copy(Blocks.GLASS), SIDED_WINDOW_SHAPES));
    public final Supplier<Block> STONE_BRICKS_CHIMNEY = register("stone_bricks_chimney", () -> new ChimneyBlockDoT(Block.Properties.copy(Blocks.STONE_BRICKS), STONE_BRICKS_CHIMNEY_SHAPES));
    public final Supplier<Block> STONE_BRICKS_FIREPLACE = register("stone_bricks_fireplace", () -> new ConnectedVerticalSidedPlanFireplaceBlock(Block.Properties.copy(Blocks.STONE_BRICKS).noOcclusion().lightLevel(litBlockEmission(15))));
    public final Supplier<Block> WAXED_OAK_DOOR = register("waxed_oak_door", () -> new DoorBlockDoT(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0F, 5.0F).noOcclusion(), BlockSetType.ACACIA), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> WAXED_OAK_SHUTTER = register("waxed_oak_shutters", () -> new ShutterBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0F, 5.0F)), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> WAXED_OAK_BALUSTER = register("waxed_oak_baluster", () -> new PlateBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0F, 5.0F).noOcclusion(), WAXED_OAK_BALUSTER_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> WAXED_OAK_SMALL_SHUTTER = register("waxed_oak_small_shutters", () -> new SmallShutterBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0F, 5.0F)).setBurnable(), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> WAXED_OAK_CHANDELIER = register("waxed_oak_chandelier", () -> new WaxedOakChandelierBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0F, 5.0F).noOcclusion().lightLevel(litBlockEmission(15))), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> WAXED_OAK_CHAIR = register("waxed_oak_chair", () -> new DoubleChairBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0F, 5.0F).noOcclusion(), 11.0F, WAXED_OAK_CHAIR_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> WAXED_OAK_TABLE = register("waxed_oak_table", () -> new ConnectedHorizontalPlanDoubleTableBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0F, 5.0F).noOcclusion(), WAXED_OAK_TABLE_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> STONE_BRICKS_POOL = register("stone_bricks_pool", () -> new PoolBlock(Block.Properties.copy(Blocks.STONE), 16, 14, POOL_SHAPES));
    public final Supplier<Block> STONE_BRICKS_SMALL_POOL = register("stone_bricks_small_pool", () -> new SmallPoolBlock(Block.Properties.copy(Blocks.STONE)));
    public final Supplier<Block> STONE_BRICKS_FAUCET = register("stone_bricks_faucet", () -> new FaucetBlock(Block.Properties.copy(Blocks.STONE).noOcclusion().noCollission().randomTicks()));
    public final Supplier<Block> STONE_BRICKS_WATER_JET = register("stone_bricks_water_jet", () -> new WaterJetBlock(Block.Properties.copy(Blocks.STONE).noOcclusion().noCollission()));

    // Japanese
    public final Supplier<Block> CAST_IRON_TEAPOT_GRAY = register("cast_iron_teapot_gray", () -> new SpecialDisplayBlock(Block.Properties.copy(Blocks.IRON_BLOCK).strength(1.0F).noOcclusion(), CAST_IRON_TEAPOT_SHAPES));
    public final Supplier<Block> CAST_IRON_TEAPOT_GREEN = register("cast_iron_teapot_green", () -> new SpecialDisplayBlock(Block.Properties.copy(Blocks.IRON_BLOCK).strength(1.0F).noOcclusion(), CAST_IRON_TEAPOT_SHAPES));
    public final Supplier<Block> CAST_IRON_TEAPOT_DECORATED = register("cast_iron_teapot_decorated", () -> new SpecialDisplayBlock(Block.Properties.copy(Blocks.IRON_BLOCK).strength(1.0F).noOcclusion(), CAST_IRON_TEAPOT_SHAPES));
    public final Supplier<Block> CAST_IRON_TEACUP_GRAY = register("cast_iron_teacup_gray", () -> new SpecialDisplayBlock(Block.Properties.copy(Blocks.IRON_BLOCK).strength(1.0F).noOcclusion(), CAST_IRON_TEACUP_SHAPES));
    public final Supplier<Block> CAST_IRON_TEACUP_GREEN = register("cast_iron_teacup_green", () -> new SpecialDisplayBlock(Block.Properties.copy(Blocks.IRON_BLOCK).strength(1.0F).noOcclusion(), CAST_IRON_TEACUP_SHAPES));
    public final Supplier<Block> CAST_IRON_TEACUP_DECORATED = register("cast_iron_teacup_decorated", () -> new SpecialDisplayBlock(Block.Properties.copy(Blocks.IRON_BLOCK).strength(1.0F).noOcclusion(), CAST_IRON_TEACUP_SHAPES));
    public final Supplier<Block> IKEBANA_FLOWER_POT = register("ikebana_flower_pot", () -> new SidedFlowerPotBlock(null));
    public final Supplier<Block> SPRUCE_LOW_TABLE = register("spruce_low_table", () -> new SpruceLowTableBlock(Block.Properties.copy(Blocks.OAK_WOOD).mapColor(MapColor.COLOR_BLACK).strength(2.0F, 6.0F).noOcclusion().lightLevel(litBlockEmission(14))), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> SPRUCE_LEGLESS_CHAIR = register("spruce_legless_chair", () -> new ChairBlock(Block.Properties.copy(Blocks.OAK_WOOD).mapColor(MapColor.COLOR_BLACK).strength(2.0F, 6.0F).noOcclusion(), 3.0F, SPRUCE_LEGLESS_CHAIR_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> SMALL_TATAMI_MAT = register("small_tatami_mat", () -> new SmallTatamiMatBlock(Block.Properties.copy(Blocks.WHITE_CARPET)), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> SMALL_TATAMI_FLOOR = registerWithItem("small_tatami_floor", () -> new SmallTatamiFloorBlock(Block.Properties.copy(Blocks.WHITE_CARPET)), null, BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> TATAMI_MAT = register("tatami_mat", () -> new TatamiMatBlock(Block.Properties.copy(Blocks.WHITE_CARPET)), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> TATAMI_FLOOR = registerWithItem("tatami_floor", () -> new TatamiFloorBlock(Block.Properties.copy(Blocks.WHITE_CARPET)), null, BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> LIGHT_GRAY_FUTON = register("light_gray_futon", () -> new FutonBlock(DyeColor.LIGHT_GRAY, Block.Properties.copy(Blocks.LIGHT_GRAY_BED)), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> IRORI_FIREPLACE = register("irori_fireplace", () -> new IroriFireplaceBlock(Block.Properties.copy(Blocks.SPRUCE_PLANKS).noOcclusion().lightLevel(litBlockEmission(15))));
    public final Supplier<Block> SAKE_BOTTLE = register("sake_bottle", () -> new SpecialDisplayBlock(Block.Properties.copy(Blocks.FLOWER_POT), SAKE_BOTTLE_SHAPES));
    public final Supplier<Block> SAKE_CUP = register("sake_cup", () -> new SpecialDisplayBlock(Block.Properties.copy(Blocks.FLOWER_POT), SAKE_CUP_SHAPES));

    // Pre_columbian
    public final Supplier<Block> PLASTERED_STONE_COLUMN = register("plastered_stone_column", () -> new ConnectedVerticalBlock(Block.Properties.copy(Blocks.STONE_BRICKS), PLASTERED_STONE_COLUMN_SHAPES));
    public final Supplier<Block> PLASTERED_STONE_CRESSET = register("plastered_stone_cresset", () -> new PlasteredStoneCressetBlock(Block.Properties.copy(Blocks.STONE_BRICKS).noOcclusion().lightLevel(litBlockEmission(15))));
    public final Supplier<Block> FEATHERED_SERPENT_SCULPTURE = register("feathered_serpent_sculpture", () -> new WaterloggedHorizontalBlock(Block.Properties.copy(Blocks.STONE_BRICKS).noOcclusion(), FEATHERED_SERPENT_SCULPTURE_SHAPES));
    public final Supplier<Block> SERPENT_SCULPTED_COLUMN = register("serpent_sculpted_column", () -> new ConnectedVerticalSidedBlock(Block.Properties.copy(Blocks.STONE_BRICKS), SERPENT_SCULPTED_COLUMN_SHAPES));

    // Roman
    public final Supplier<Block> SANDSTONE_COLUMN = register("sandstone_column", () -> new SandstoneColumnBlock(Block.Properties.copy(Blocks.SANDSTONE)));
    public final Supplier<Block> SANDSTONE_SIDED_COLUMN = register("sandstone_sided_column", () -> new ConnectedVerticalSidedBlock(Block.Properties.copy(Blocks.SANDSTONE), SANDSTONE_SIDED_COLUMN_SHAPES));
    public final Supplier<Block> BIRCH_FANCY_FENCE = register("birch_fancy_fence", () -> new PlateBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0F, 5.0F).noOcclusion(), THIN_PLATE_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> BIRCH_FOOTSTOOL = register("birch_footstool", () -> new BirchFootstoolBlock(Block.Properties.copy(Blocks.BIRCH_PLANKS), 9.0F), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> BIRCH_COUCH = register("birch_couch", () -> new BirchCouchBlock(Block.Properties.copy(Blocks.BIRCH_PLANKS), 13.0F, ROMAN_COUCH_SHAPES), BlockTags.MINEABLE_WITH_AXE);
    public final Supplier<Block> MARBLE_STATUE_MARS = register("marble_statue_mars", () -> new MarbleStatueBlock(Block.Properties.copy(Blocks.BRICKS).noOcclusion()));
    public final Supplier<Block> BIG_FLOWER_POT = register("big_flower_pot", () -> new WaterloggedBlock(Block.Properties.copy(Blocks.CLAY), BIG_FLOWER_POT_SHAPES));
    public final Supplier<Block> MARBLE_BIG_FLOWER_POT = register("marble_big_flower_pot", () -> new WaterloggedBlock(Block.Properties.copy(Blocks.STONE), MARBLE_BIG_FLOWER_POT_SHAPES));
    public final Supplier<Block> MARBLE_FANCY_FENCE = register("marble_fancy_fence", () -> new PlateBlock(Block.Properties.copy(Blocks.STONE).strength(3.0F, 5.0F).noOcclusion(), THIN_PLATE_SHAPES));
    public final Supplier<Block> MARBLE_COLUMN = register("marble_column", () -> new MarbleColumnBlock(Block.Properties.copy(Blocks.STONE)));
    public final Supplier<Block> MARBLE_SIDED_COLUMN = register("marble_sided_column", () -> new ConnectedVerticalSidedBlock(Block.Properties.copy(Blocks.STONE), MARBLE_SIDED_COLUMN_SHAPES));

    public void postRegister() {
        CYPRESS = registerWithFlowerPotItem("cypress", () -> new CypressBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES).randomTicks()).setBurnable(), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        MAIZE = registerWithFlowerPotItem("maize", () -> new DoubleCropsBlock(SoilCropsBlock.PlantType.CROP, 4), (block) -> new SoilSeedsItem(block, Foods.MAIZE));
        COMMELINA = registerWithFlowerPotItem("commelina", () -> new SoilCropsBlock(SoilCropsBlock.PlantType.PLAINS), (block) -> new SoilSeedsItem(block, null));
        MAPLE_RED_SAPLING = registerWithFlowerPotItem("maple_red_sapling", () -> new MapleSaplingBlock(Block.Properties.copy(Blocks.SPRUCE_LEAVES)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        CAMELLIA = registerWithFlowerPotItem("camellia", () -> new GrowingBushBlock(SoilCropsBlock.PlantType.PLAINS, 3), "camellia_seeds", (block) -> new SoilSeedsItem(block, null));
        MULBERRY = registerWithFlowerPotItem("mulberry", () -> new MulberryBlock(SoilCropsBlock.PlantType.PLAINS, 3, 2), (block) -> new SoilSeedsItem(block, Foods.MULBERRY));
        IVY = registerWithFlowerPotItem("ivy", () -> new IvyBlock(Block.Properties.copy(Blocks.VINE).randomTicks().strength(0.2F).sound(SoundType.VINE)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        GERANIUM_PINK = registerWithFlowerPotItem("geranium_pink", () -> new GeraniumBlock(Block.Properties.copy(Blocks.SUNFLOWER).offsetType(BlockBehaviour.OffsetType.NONE).instabreak().sound(SoundType.GRASS)), (block) -> new PotAndBlockItem(block, new Item.Properties()));
        // WAXED_OAK_CANOPY_BED_WOOD = registerWithItem("waxed_oak_canopy_bed_wood", () -> new WaxedOakCanopyBedWoodBlock(BlockBehaviour.Properties.copy(WAXED_OAK_PLANKS.get()).noOcclusion()), null, BlockTags.MINEABLE_WITH_AXE);
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
