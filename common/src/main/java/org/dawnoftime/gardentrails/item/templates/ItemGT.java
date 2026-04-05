package org.dawnoftime.gardentrails.item.templates;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

public class ItemGT extends Item {
    // Translation key for the "Silk Process:" AQUA header (shared by all silk chain items)
    private static final String SILK_PROCESS_HEADER_KEY = "lore.gardentrails.silk_process";

    @Nullable private final String loreKey;
    // Description key for the silk process pattern (supports %s substitution for the highlighted item name)
    @Nullable private final String processDescKey;
    // Translation key for the item name to highlight — null means no header, no highlight (e.g. Silk)
    @Nullable private final String processArgKey;
    // Color of the highlighted item name — defaults to YELLOW, can be overridden (e.g. LIGHT_PURPLE for Silk)
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

    // Silk process pattern: AQUA header + white description with one highlighted item name (%s arg)
    // Pass null for processArgKey to show only the white description without a header (e.g. Silk — end of chain)
    public ItemGT(Properties properties, String processDescKey, @Nullable String processArgKey) {
        this(properties, null, processDescKey, processArgKey, ChatFormatting.YELLOW);
    }

    // Same as above but with a custom highlight color for the item name arg
    public ItemGT(Properties properties, String processDescKey, @Nullable String processArgKey, ChatFormatting argColor) {
        this(properties, null, processDescKey, processArgKey, argColor);
    }

    private ItemGT(Properties properties, @Nullable String loreKey, @Nullable String processDescKey, @Nullable String processArgKey, ChatFormatting processArgColor) {
        super(properties);
        this.loreKey = loreKey;
        this.processDescKey = processDescKey;
        this.processArgKey = processArgKey;
        this.processArgColor = processArgColor;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level world, @NotNull List<Component> tooltips, @NotNull TooltipFlag flagIn) {
        super.appendHoverText(stack, world, tooltips, flagIn);
        if (this.loreKey != null) {
            tooltips.add(Component.translatable(this.loreKey).withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
        }
        if (this.processDescKey != null) {
            if (this.processArgKey != null) {
                // Header "Silk Process:" in aqua, then white description with highlighted item name
                tooltips.add(Component.translatable(SILK_PROCESS_HEADER_KEY).withStyle(ChatFormatting.AQUA));
                Component highlightedArg = Component.translatable(this.processArgKey).withStyle(this.processArgColor);
                tooltips.add(Component.translatable(this.processDescKey, highlightedArg).withStyle(ChatFormatting.WHITE));
            } else {
                // End of chain (Silk): just a white description, no header
                tooltips.add(Component.translatable(this.processDescKey).withStyle(ChatFormatting.WHITE));
            }
        }
    }
}
