package org.dawnoftime.gardentrails.advancement;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.SerializationContext;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.dawnoftime.gardentrails.GTCommon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KillSilkmothTrigger extends SimpleCriterionTrigger<KillSilkmothTrigger.TriggerInstance> {

    public static final ResourceLocation ID = new ResourceLocation(GTCommon.MOD_ID, "kill_silkmoth");

    // Per-player kill counts for this session. Resets on server restart which is acceptable.
    private static final Map<UUID, Integer> KILL_COUNTS = new HashMap<>();

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    protected TriggerInstance createInstance(JsonObject json, ContextAwarePredicate predicate, DeserializationContext context) {
        int count = json.has("count") ? json.get("count").getAsInt() : 1;
        return new TriggerInstance(predicate, count);
    }

    /**
     * Called when a player kills a silkmoth. Increments the kill count and fires
     * any criteria whose required count has now been reached.
     */
    public void trigger(ServerPlayer player) {
        int newCount = KILL_COUNTS.merge(player.getUUID(), 1, Integer::sum);
        this.trigger(player, instance -> instance.matches(newCount));
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        private final int requiredCount;

        public TriggerInstance(ContextAwarePredicate predicate, int requiredCount) {
            super(ID, predicate);
            this.requiredCount = requiredCount;
        }

        public boolean matches(int currentCount) {
            return currentCount >= this.requiredCount;
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject json = super.serializeToJson(context);
            json.addProperty("count", this.requiredCount);
            return json;
        }
    }
}
