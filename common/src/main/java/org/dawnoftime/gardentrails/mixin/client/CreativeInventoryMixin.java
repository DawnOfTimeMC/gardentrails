package org.dawnoftime.gardentrails.mixin.client;

import net.minecraft.Util;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.CreativeModeTab;
import org.dawnoftime.gardentrails.client.gui.elements.buttons.SocialsButton;
import org.dawnoftime.gardentrails.registry.GTCreativeModeTabsRegistry;
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
    private Button gTBuilder$discord;
    @Unique
    private Button gTBuilder$curse;
    @Unique
    private Button gTBuilder$patreon;
    @Unique
    private Button gTBuilder$github;
    @Unique
    private boolean gTBuilder$tabDoTBSelected;

    protected CreativeInventoryMixin(CreativeModeInventoryScreen.ItemPickerMenu $$0, Inventory $$1, Component $$2) {
        super($$0, $$1, $$2);
    }

    @Inject(method = "init", at = @At(value = "TAIL"))
    public void gardentrails$init(CallbackInfo ci) {
        this.addRenderableWidget(this.gTBuilder$discord = new SocialsButton(this.leftPos - 40, this.topPos, "discord", button -> gardentrails$openLink("https://discord.gg/cteCdn9Hnf")));
        this.gTBuilder$discord.setTooltip(Tooltip.create(Component.literal("Discord")));
        this.addRenderableWidget(this.gTBuilder$curse = new SocialsButton(this.leftPos - 40, this.topPos + 35, "curse", button -> gardentrails$openLink("https://www.curseforge.com/minecraft/mc-mods/dawn-of-time")));
        this.gTBuilder$curse.setTooltip(Tooltip.create(Component.literal("Curse Forge")));
        this.addRenderableWidget(this.gTBuilder$patreon = new SocialsButton(this.leftPos - 40, this.topPos + 70, "patreon", button -> gardentrails$openLink("https://www.patreon.com/dawnoftimemod")));
        this.gTBuilder$patreon.setTooltip(Tooltip.create(Component.literal("Patreon")));
        this.addRenderableWidget(this.gTBuilder$github = new SocialsButton(this.leftPos - 40, this.topPos + 105, "github", button -> gardentrails$openLink("https://github.com/PierreChag/gardentrails")));
        this.gTBuilder$github.setTooltip(Tooltip.create(Component.literal("Github")));
        gardentrails$toggleButtons(this.gTBuilder$tabDoTBSelected);
    }

    @Inject(method = "render", at = @At(value = "HEAD"))
    public void gardentrails$render(GuiGraphics $$0, int $$1, int $$2, float $$3, CallbackInfo ci) {
        gardentrails$toggleButtons(this.gTBuilder$tabDoTBSelected);
    }

    @Unique
    private void gardentrails$toggleButtons(boolean val) {
        this.gTBuilder$discord.visible = val;
        this.gTBuilder$curse.visible = val;
        this.gTBuilder$patreon.visible = val;
        this.gTBuilder$github.visible = val;
    }

    @Inject(method = "selectTab", at = @At(value = "HEAD"), cancellable = false)
    public void gardentrails$selectTab(CreativeModeTab tab, CallbackInfo ci) {
        gTBuilder$tabDoTBSelected = tab == GTCreativeModeTabsRegistry.INSTANCE.GT_TAB.get();
    }

    @Unique
    private void gardentrails$openLink(String link) {
        Util.getPlatform().openUri(link);
    }
}