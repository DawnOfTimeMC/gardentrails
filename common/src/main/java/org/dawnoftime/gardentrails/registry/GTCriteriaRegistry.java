package org.dawnoftime.gardentrails.registry;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import org.dawnoftime.gardentrails.advancement.KillSilkmothTrigger;

import java.lang.reflect.Method;

public class GTCriteriaRegistry {

    public static final KillSilkmothTrigger KILL_SILKMOTH = register(new KillSilkmothTrigger());

    /**
     * CriteriaTriggers.register() is private in MC 1.20.1, so we call it via reflection.
     * We iterate declared methods to avoid depending on the exact erased parameter type.
     */
    @SuppressWarnings("unchecked")
    private static <T extends SimpleCriterionTrigger<?>> T register(T trigger) {
        try {
            Method method = null;
            for (Method m : CriteriaTriggers.class.getDeclaredMethods()) {
                // Search by type signature rather than name to survive Forge's production obfuscation.
                // In production jars, the method may be named with its SRG id instead of "register".
                if (m.getParameterCount() == 1
                        && java.lang.reflect.Modifier.isStatic(m.getModifiers())
                        && m.getParameterTypes()[0].isAssignableFrom(trigger.getClass())) {
                    method = m;
                    break;
                }
            }
            if (method == null) {
                throw new RuntimeException("Could not find CriteriaTriggers.register() method");
            }
            method.setAccessible(true);
            return (T) method.invoke(null, trigger);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to register criterion trigger: " + trigger.getId(), e);
        }
    }

    /**
     * Accessing this class triggers static initialization of both criteria.
     * Call from GTCommon.init() to ensure registration happens before any
     * datapack or advancement loading on both Forge and Fabric.
     */
    public static void init() {}
}
