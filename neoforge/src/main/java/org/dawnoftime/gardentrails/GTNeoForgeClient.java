package org.dawnoftime.gardentrails;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.dawnoftime.gardentrails.client.model.entity.SilkmothModel;
import org.dawnoftime.gardentrails.client.renderer.blockentity.DryerRenderer;
import org.dawnoftime.gardentrails.client.renderer.entity.SilkmothRenderer;
import org.dawnoftime.gardentrails.registry.GTBlockEntitiesRegistry;
import org.dawnoftime.gardentrails.registry.GTEntitiesRegistry;

@EventBusSubscriber(modid = GTCommon.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class GTNeoForgeClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        final IEventBus eventBus = ModLoadingContext.get().getActiveContainer().getEventBus();

        eventBus.addListener(GTNeoForgeClient::registerLayerDefinitions);
        eventBus.addListener(GTNeoForgeClient::registerRenderers);

        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (modContainer, parent) -> GTNeoForge.HANDLER.generateGui().generateScreen(parent)
        );
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SilkmothModel.LAYER_LOCATION, SilkmothModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(GTEntitiesRegistry.INSTANCE.SILKMOTH_ENTITY.get(), SilkmothRenderer::new);
        event.registerBlockEntityRenderer(GTBlockEntitiesRegistry.INSTANCE.DRYER.get(), DryerRenderer::new);
    }
}
