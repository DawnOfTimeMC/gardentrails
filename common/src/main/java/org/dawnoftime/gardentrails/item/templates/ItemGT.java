package org.dawnoftime.gardentrails.item.templates;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.dawnoftime.gardentrails.util.Utils;

import java.util.List;

public class ItemGT extends Item {
    private final boolean hasTooltip;

    public ItemGT() {
        this(false);
    }

    public ItemGT(boolean hasTooltip) {
        this(new Properties(), hasTooltip);
    }

    public ItemGT(Properties properties) {
        this(properties, false);
    }

    public ItemGT(Properties properties, boolean hasTooltip) {
        super(properties);
        this.hasTooltip = hasTooltip;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (this.hasTooltip) {
            Utils.addTooltip(tooltipComponents, this);
        }
    }
}
