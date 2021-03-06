package com.javisel.skyrift.main;

import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;

import java.util.ArrayList;

public class SkyriftAttributes {

    public static final IAttribute MAX_HEALTH = new RangedAttribute(null, "skyrift.MaxHealth", 0, 0.0D, 1000000).setDescription("A entity's health.").setShouldWatch(true);

    public static final IAttribute ATTACK_DAMAGE = new RangedAttribute(null, "skyrift.AttackDamage", 0, 0.0D, 10000).setDescription("Determines how much damage a basic attack will do.").setShouldWatch(true);
    public static final IAttribute MAGICAL_POWER = new RangedAttribute(null, "skyrift.MagicalPower", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute PHYSICAL_POWER = new RangedAttribute(null, "skyrift.PhysicalPower", 0, 0.0D, 10000).setDescription("The Physical Power of an entity.").setShouldWatch(true);
    public static final IAttribute CRITCHANCE = new RangedAttribute(null, "skyrift.CritChance", 0, 0.0D, 10000).setDescription("The Critical Strike Chance of an entity.").setShouldWatch(true);
    public static final IAttribute CRITDAMAGE = new RangedAttribute(null, "skyrift.CritDamage", 2.0D, 0.0D, 10000).setDescription("The Critical Strike Multiplier.").setShouldWatch(true);
    public static final IAttribute LIFESTEAL = new RangedAttribute(null, "skyrift.Lifesteal", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute FLATARMORPEN = new RangedAttribute(null, "skyrift.FlatArmorPen", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute FLATMAGICPEN = new RangedAttribute(null, "skyrift.FlatMagicPen", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute PERCENTARMORPEN = new RangedAttribute(null, "skyrift.PercentArmorPen", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute PERCENTMAGICPEN = new RangedAttribute(null, "skyrift.PercentMagicPen", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);

    public static final IAttribute SPELLVAMP = new RangedAttribute(null, "skyrift.SpellVamp", 2.0D, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute HEALTH_REGEN = new RangedAttribute(null, "skyrift.HealthRegen", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute ARMOR = new RangedAttribute(null, "skyrift.Armor", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute MAGIC_RESIST = new RangedAttribute(null, "skyrift.MagicResist", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute MAX_RESOURCE_AMOUNT = new RangedAttribute(null, "skyrift.MaxResourceAmount", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute RESOURCE_REGEN = new RangedAttribute(null, "skyrift.ResourceRegen", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute COOLDOWN_REDUCTION = new RangedAttribute(null, "skyrift.CooldownReduction", 2.0D, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute EXPERIENCE = new RangedAttribute(null, "skyrift.Experience", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute GOLD_GENERATION = new RangedAttribute(null, "skyrift.GoldGeneration", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static final IAttribute RANGE = new RangedAttribute(null, "skyrift.Range", 0, 0.0D, 10000).setDescription("The Magical Power of an entity.").setShouldWatch(true);
    public static ArrayList<IAttribute> attributes = new ArrayList<>();


    static {
        attributes.add(MAX_HEALTH);

        attributes.add(ATTACK_DAMAGE);

        attributes.add(MAGICAL_POWER);
        attributes.add(PHYSICAL_POWER);
        attributes.add(CRITCHANCE);
        attributes.add(CRITDAMAGE);
        attributes.add(LIFESTEAL);
        attributes.add(FLATARMORPEN);
        attributes.add(FLATMAGICPEN);
        attributes.add(PERCENTARMORPEN);
        attributes.add(PERCENTMAGICPEN);
        attributes.add(SPELLVAMP);
        attributes.add(HEALTH_REGEN);
        attributes.add(ARMOR);
        attributes.add(MAGIC_RESIST);
        attributes.add(MAX_RESOURCE_AMOUNT);
        attributes.add(COOLDOWN_REDUCTION);
        attributes.add(EXPERIENCE);
        attributes.add(GOLD_GENERATION);
        attributes.add(RANGE);
        attributes.add(RESOURCE_REGEN);

    }


    public static void loadAttributes(AttributeMap attributeMap) {
        attributeMap.registerAttribute(MAX_HEALTH);

        attributeMap.registerAttribute(ATTACK_DAMAGE);
        attributeMap.registerAttribute(MAGICAL_POWER);
        attributeMap.registerAttribute(PHYSICAL_POWER);
        attributeMap.registerAttribute(CRITCHANCE);
        attributeMap.registerAttribute(CRITDAMAGE);
        attributeMap.registerAttribute(LIFESTEAL);
        attributeMap.registerAttribute(FLATARMORPEN);
        attributeMap.registerAttribute(FLATMAGICPEN);
        attributeMap.registerAttribute(PERCENTARMORPEN);
        attributeMap.registerAttribute(PERCENTMAGICPEN);
        attributeMap.registerAttribute(SPELLVAMP);
        attributeMap.registerAttribute(HEALTH_REGEN);

        attributeMap.registerAttribute(ARMOR);
        attributeMap.registerAttribute(MAGIC_RESIST);
        attributeMap.registerAttribute(MAX_RESOURCE_AMOUNT);
        attributeMap.registerAttribute(COOLDOWN_REDUCTION);
        attributeMap.registerAttribute(EXPERIENCE);
        attributeMap.registerAttribute(GOLD_GENERATION);
        attributeMap.registerAttribute(RANGE);
        attributeMap.registerAttribute(RESOURCE_REGEN);


    }


}
