package org.dawnoftime.gardentrails;

import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.autogen.AutoGen;
import dev.isxander.yacl3.config.v2.api.autogen.Boolean;

public class GTConfig {
    @SerialEntry
    @AutoGen(category = "loot", group = "chest_loot")
    @Boolean(colored = true, formatter = Boolean.Formatter.TRUE_FALSE)
    public boolean generateChestLoot = true;

    @SerialEntry
    @AutoGen(category = "loot", group = "chest_loot")
    @Boolean(colored = true, formatter = Boolean.Formatter.TRUE_FALSE)
    public boolean generateSilk = true;

    @SerialEntry
    @AutoGen(category = "loot", group = "chest_loot")
    @Boolean(colored = true, formatter = Boolean.Formatter.TRUE_FALSE)
    public boolean generateGrapes = true;

    @SerialEntry
    @AutoGen(category = "loot", group = "chest_loot")
    @Boolean(colored = true, formatter = Boolean.Formatter.TRUE_FALSE)
    public boolean generateMaize = true;

    @SerialEntry
    @AutoGen(category = "loot", group = "chest_loot")
    @Boolean(colored = true, formatter = Boolean.Formatter.TRUE_FALSE)
    public boolean generateRice = true;

    @SerialEntry
    @AutoGen(category = "loot", group = "chest_loot")
    @Boolean(colored = true, formatter = Boolean.Formatter.TRUE_FALSE)
    public boolean generateMulberry = true;

    // Recipes settings
    @SerialEntry(comment = "This makes the recipes of Dawn of Time use silk instead white wool")
    @AutoGen(category = "recipes")
    @Boolean(colored = true, formatter = Boolean.Formatter.YES_NO)
    public boolean dawnOfTimeUseSilk = true;
    @SerialEntry(comment = "This makes the recipes of Armor of Ages use silk instead string")
    @AutoGen(category = "recipes")
    @Boolean(colored = true, formatter = Boolean.Formatter.YES_NO)
    public boolean armorOfTheAgesUseSilk = true;
}
