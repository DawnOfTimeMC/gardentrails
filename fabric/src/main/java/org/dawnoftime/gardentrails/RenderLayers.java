package org.dawnoftime.gardentrails;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import org.dawnoftime.gardentrails.registry.GTBlocksRegistry;

public class RenderLayers {
    public static void init() {
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.WILD_MAIZE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.WILD_GRAPE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.STICK_BUNDLE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.RICE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.PLANTER_GERANIUM_PINK.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.PAUSED_MAPLE_RED_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.MULBERRY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.MAPLE_RED_TRUNK.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.MAPLE_RED_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.MAPLE_RED_LEAVES.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.MAIZE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.IVY.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.GERANIUM_PINK.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.CYPRESS.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.POT_BLOCKS.get("cypress_flower_pot"), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.COMMELINA.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.CAMELLIA.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.BOXWOOD_TALL_HEDGE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.BOXWOOD_SMALL_HEDGE.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.BOXWOOD_BUSH.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GTBlocksRegistry.INSTANCE.BAMBOO_DRYING_TRAY.get(), RenderType.cutout());
    }
}
