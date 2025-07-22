package org.dawnoftime.dawnoftime;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.dawnoftime.dawnoftime.client.model.entity.SilkmothModel;
import org.dawnoftime.dawnoftime.client.renderer.blockentity.DryerBERenderer;
import org.dawnoftime.dawnoftime.client.renderer.entity.SilkmothRenderer;
import org.dawnoftime.dawnoftime.registry.DoTBBlockEntitiesRegistry;
import org.dawnoftime.dawnoftime.registry.DoTBEntitiesRegistry;

@Mod.EventBusSubscriber(modid = DoTBCommon.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DoTBForgeClient {
    public DoTBForgeClient() {}

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(DoTBForgeClient::registerLayerDefinitions);
        eventBus.addListener(DoTBForgeClient::registerRenderers);

        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (minecraftClient, parent) -> DoTBForge.HANDLER.generateGui().generateScreen(parent)
                )
        );
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SilkmothModel.LAYER_LOCATION, SilkmothModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(DoTBEntitiesRegistry.INSTANCE.SILKMOTH_ENTITY.get(), SilkmothRenderer::new);
        event.registerBlockEntityRenderer(DoTBBlockEntitiesRegistry.INSTANCE.DRYER.get(), DryerBERenderer::new);
    }
}
