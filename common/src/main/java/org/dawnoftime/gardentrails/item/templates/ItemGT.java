package org.dawnoftime.gardentrails.item.templates;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import javax.annotation.Nullable;
import java.util.List;

public class ItemGT extends Item {
    private static final String SILK_PROCESS_HEADER_KEY = "lore.gardentrails.silk_process";

    @Nullable private final String loreKey;
    @Nullable private final String processDescKey;
    @Nullable private final String processArgKey;
    private final ChatFormatting processArgColor;

    public ItemGT() {
        this(new Properties(), null, null, null, ChatFormatting.YELLOW);
    }

    public ItemGT(Properties properties) {
        this(properties, null, null, null, ChatFormatting.YELLOW);
    }

    public ItemGT(String loreKey) {
        this(new Properties(), loreKey, null, null, ChatFormatting.YELLOW);
    }

    public ItemGT(Properties properties, @Nullable String loreKey) {
        this(properties, loreKey, null, null, ChatFormatting.YELLOW);
    }

    // Silk process: AQUA header + WHITE description with one highlighted item name
    // Pass null for processArgKey to show only the description without header (Silk -- end of chain)
    public ItemGT(Properties properties, String processDescKey, @Nullable String processArgKey) {
        this(properties, null, processDescKey, processArgKey, ChatFormatting.YELLOW);
    }

    public ItemGT(Properties properties, String processDescKey, @Nullable String processArgKey, ChatFormatting argColor) {
        this(properties, null, processDescKey, processArgKey, argColor);
    }

    private ItemGT(Properties properties, @Nullable String loreKey, @Nullable String processDescKey,
                   @Nullable String processArgKey, ChatFormatting processArgColor) {
        super(properties);
        this.loreKey = loreKey;
        this.processDescKey = processDescKey;
        this.processArgKey = processArgKey;
        this.processArgColor = processArgColor;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context,
                                List<Component> tooltips, TooltipFlag flagIn) {
        super.appendHoverText(stack, context, tooltips, flagIn);
        if (this.loreKey != null) {
            tooltips.add(Component.translatable(this.loreKey)
                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
        if (this.processDescKey != null) {
            if (this.processArgKey != null) {
                tooltips.add(Component.translatable(SILK_PROCESS_HEADER_KEY)
                    .withStyle(ChatFormatting.AQUA));
                Component arg = Component.translatable(this.processArgKey)
                    .withStyle(this.processArgColor);
                tooltips.add(Component.translatable(this.processDescKey, arg)
                    .withStyle(ChatFormatting.WHITE));
            } else {
                tooltips.add(Component.translatable(this.processDescKey)
                    .withStyle(ChatFormatting.WHITE));
            }
        }
    }
}
