package org.dawnoftime.gardentrails.mixin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.Tuple;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.RecipeManager;
import org.dawnoftime.gardentrails.GTCommon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public class DynamicRecipes {
    @Inject(method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("HEAD"))
    public void gt$interceptRecipesApply(final Map<ResourceLocation, JsonElement> map, final ResourceManager resourceManager, final ProfilerFiller profiler, final CallbackInfo ci) {
        if(GTCommon.dynamicRecipes !=null){
            for(final Tuple<ResourceLocation, JsonObject> recipePair: GTCommon.dynamicRecipes){
                ResourceLocation id = recipePair.getA();
                JsonObject recipe = recipePair.getB();
                if (id != null && recipe != null) {
                    map.remove(id); // removes old recipe if it exists
                    map.put(id, recipe); // adds new one
                }
            }
        }
    }
}
