package org.dawnoftime.gardentrails;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import org.dawnoftime.gardentrails.registry.GTCriteriaRegistry;
import org.dawnoftime.gardentrails.registry.GTDynamicRecipes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class GTCommon {
	public static List<Tuple<ResourceLocation, JsonObject>> dynamicRecipes = new ArrayList<>();
	public static final String MOD_ID = "gardentrails";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);
	public static final ResourceLocation CREATIVE_ICONS = new ResourceLocation(MOD_ID, "textures/gui/creative_icons.png");

	public static void init() {
		GTCriteriaRegistry.init();
		GTDynamicRecipes.init();
	}
}