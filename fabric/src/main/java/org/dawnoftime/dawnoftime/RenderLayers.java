package org.dawnoftime.dawnoftime;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import org.dawnoftime.dawnoftime.registry.DoTBBlocksRegistry;

public class RenderLayers {
    public static void init() {
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.WILD_MAIZE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.WILD_GRAPE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.WAXED_OAK_CHAIR.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.WATER_SOURCE_TRICKLE.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.WATER_FLOWING_TRICKLE.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.STONE_BRICKS_WATER_JET.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.STONE_BRICKS_SMALL_POOL.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.STONE_BRICKS_POOL.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.STONE_BRICKS_FAUCET.get(), RenderType.translucent());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.STICK_BUNDLE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.RICE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.PLANTER_GERANIUM_PINK.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.PAUSED_MAPLE_RED_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.MULBERRY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.MAPLE_RED_TRUNK.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.MAPLE_RED_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.MAPLE_RED_LEAVES.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.MAIZE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.IVY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.GERANIUM_PINK.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.CYPRESS.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.COMMELINA.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.CAMELLIA.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.BOXWOOD_TALL_HEDGE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.BOXWOOD_SMALL_HEDGE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.BOXWOOD_BUSH.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.BIG_FLOWER_POT.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DoTBBlocksRegistry.INSTANCE.BAMBOO_DRYING_TRAY.get(), RenderType.cutout());
    }
}
