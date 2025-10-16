package org.dawnoftime.gardentrails.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class DryerRecipeSerializer implements RecipeSerializer<DryerRecipe> {
    public static final MapCodec<DryerRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    Codec.STRING.optionalFieldOf("group", "").forGetter(DryerRecipe::group),
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(DryerRecipe::ingredient),
                    ItemStack.CODEC.fieldOf("result").forGetter(DryerRecipe::result),
                    Codec.FLOAT.optionalFieldOf("experience", 0f).forGetter(DryerRecipe::experience),
                    Codec.INT.optionalFieldOf("dryingTime", 1200).forGetter(DryerRecipe::dryingTime)
            ).apply(instance, DryerRecipe::new)
    );

    private static final StreamCodec<ByteBuf, Ingredient> INGREDIENT_STREAM_CODEC = ByteBufCodecs.fromCodec(Ingredient.CODEC);
    public static final StreamCodec<RegistryFriendlyByteBuf, DryerRecipe> STREAM_CODEC = StreamCodec.of(DryerRecipeSerializer::write, DryerRecipeSerializer::read);

    @Override
    public MapCodec<DryerRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, DryerRecipe> streamCodec() {
        return STREAM_CODEC;
    }

    public static DryerRecipe read(RegistryFriendlyByteBuf buf) {
        String group = buf.readUtf(32767);
        Ingredient ingredient = INGREDIENT_STREAM_CODEC.decode(buf);
        ItemStack itemStackResult = ItemStack.STREAM_CODEC.decode(buf);
        float experience = buf.readFloat();
        int dryingTime = buf.readVarInt();
        return new DryerRecipe(group, ingredient, itemStackResult, experience, dryingTime);
    }

    public static void write(RegistryFriendlyByteBuf buf, DryerRecipe recipe) {
        buf.writeUtf(recipe.group(), 32767);
        INGREDIENT_STREAM_CODEC.encode(buf, recipe.ingredient());
        ItemStack.STREAM_CODEC.encode(buf, recipe.result());
        buf.writeFloat(recipe.experience());
        buf.writeVarInt(recipe.dryingTime());
    }
}
