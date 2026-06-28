package org.dawnoftime.gardentrails;

public final class GTConstants {
    private GTConstants() {}

    // 1/x probability per random tick to spawn a Silkmoth on a Mulberry block top half
    public static final int SILKMOTH_SPAWN_CHANCE = 400;
    // Silkmoth orbit radius = 0.5 + SILKMOTH_ROTATION_RANGE * random float
    public static final int SILKMOTH_ROTATION_RANGE = 2;
    // If true, Silkmoth dies at 24000 ticks (required to produce silk)
    public static final boolean SILKMOTH_MUST_DIE = true;
    // 1/x probability per tick to pick a new orbit center
    public static final int SILKMOTH_ROTATION_CHANGE = 400;
    // If true, Silkmoth plays no ambient sound
    public static final boolean SILKMOTH_MUTE = false;

    // Drying time variance in percent; at 30 the interval is [83.3%, 120%]
    public static final int DRYING_TIME_VARIATION = 30;
    // 1/x probability per random tick for a climbing plant (Ivy/Pergola) to advance its age
    public static final int CLIMBING_GROWTH_CHANCE = 16;
    // 1/x probability per random tick for a fully-aged climbing plant to spread
    public static final int CLIMBING_SPREAD_CHANCE = 5;
    // 1/x probability per random tick for a StickBundle (with worms) to advance its age
    public static final int STICK_BUNDLE_GROWTH = 25;
}
