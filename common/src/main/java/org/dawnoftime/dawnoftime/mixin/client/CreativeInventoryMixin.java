package org.dawnoftime.dawnoftime.mixin.client;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.CreativeModeTab;
import org.dawnoftime.dawnoftime.client.gui.elements.buttons.SocialsButton;
import org.dawnoftime.dawnoftime.registry.DoTBCreativeModeTabsRegistry;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@Debug(print = true)
@Mixin(CreativeModeInventoryScreen.class)
public abstract class CreativeInventoryMixin extends EffectRenderingInventoryScreen<CreativeModeInventoryScreen.ItemPickerMenu> {

    @Unique
    private Button dOTBuilder$discord;
    @Unique
    private Button dOTBuilder$curse;
    @Unique
    private Button dOTBuilder$patreon;
    @Unique
    private Button dOTBuilder$github;
    @Unique
    private boolean dOTBuilder$tabDoTBSelected;

    protected CreativeInventoryMixin(CreativeModeInventoryScreen.ItemPickerMenu $$0, Inventory $$1, Component $$2) {
        super($$0, $$1, $$2);
    }

    @Inject(method = "init", at = @At(value = "TAIL"))
    public void dawnoftimebuilder$init(CallbackInfo ci) {
        this.addRenderableWidget(this.dOTBuilder$discord = new SocialsButton(this.leftPos - 40, this.topPos, "discord", button -> dOTBuilder$openLink("https://discord.gg/cteCdn9Hnf")));
        this.dOTBuilder$discord.setTooltip(Tooltip.create(Component.literal("Discord")));
        this.addRenderableWidget(this.dOTBuilder$curse = new SocialsButton(this.leftPos - 40, this.topPos + 35, "curse", button -> dOTBuilder$openLink("https://www.curseforge.com/minecraft/mc-mods/dawn-of-time")));
        this.dOTBuilder$curse.setTooltip(Tooltip.create(Component.literal("Curse Forge")));
        this.addRenderableWidget(this.dOTBuilder$patreon = new SocialsButton(this.leftPos - 40, this.topPos + 70, "patreon", button -> dOTBuilder$openLink("https://www.patreon.com/dawnoftimemod")));
        this.dOTBuilder$patreon.setTooltip(Tooltip.create(Component.literal("Patreon")));
        this.addRenderableWidget(this.dOTBuilder$github = new SocialsButton(this.leftPos - 40, this.topPos + 105, "github", button -> dOTBuilder$openLink("https://github.com/PierreChag/dawnoftimebuilder")));
        this.dOTBuilder$github.setTooltip(Tooltip.create(Component.literal("Github")));
        dOTBuilder$toggleButtons(this.dOTBuilder$tabDoTBSelected);
    }

    @Inject(method = "render", at = @At(value = "HEAD"))
    public void dawnoftimebuilder$render(GuiGraphics $$0, int $$1, int $$2, float $$3, CallbackInfo ci) {
        dOTBuilder$toggleButtons(this.dOTBuilder$tabDoTBSelected);
    }

    @Unique
    private void dOTBuilder$toggleButtons(boolean val) {
        this.dOTBuilder$discord.visible = val;
        this.dOTBuilder$curse.visible = val;
        this.dOTBuilder$patreon.visible = val;
        this.dOTBuilder$github.visible = val;
    }

    @Inject(method = "selectTab", at = @At(value = "HEAD"), cancellable = false)
    public void dawnoftimebuilder$selectTab(CreativeModeTab tab, CallbackInfo ci) {
        dOTBuilder$tabDoTBSelected = tab == DoTBCreativeModeTabsRegistry.INSTANCE.DOT_TAB.get();
    }

    @Unique
    private void dOTBuilder$openLink(String link) {
        Util.getPlatform().openUri(link);
    }
}