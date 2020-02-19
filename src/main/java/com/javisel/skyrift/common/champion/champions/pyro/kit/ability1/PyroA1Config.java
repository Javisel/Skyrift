package com.javisel.skyrift.common.champion.champions.pyro.kit.ability1;

import com.javisel.skyrift.common.champion.ability.AbilityConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class PyroA1Config extends AbilityConfig {

    public ForgeConfigSpec.ConfigValue<List<? extends Double>> bonusMagicDamage;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> bonusMagicScaling;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> onhitSlowStrength;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> onhitSlowDuration;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> armorShred;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> onhitArmorDuration;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> trueDamage;

    //Active
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> buffDuration;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeMagicDamage;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeMagicScaling;

    public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeSlow;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeSlowDuration;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeSlowScaling;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> heatBonusPercentage;


    public PyroA1Config() {
        super("pyro", "pyro_ability_1");

    }

    @Override
    public void configureAbility() {

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment(abilityname + " base statistics");
        cost = getDoubleList(builder, "cost", "cost", 0d,0d,0d,0d,0d,0d);
        charges = getDoubleList(builder, "charges", "charge", 0d,0d,0d,0d,0d,0d);
        bonusMagicDamage = getDoubleList(builder, "onAttackMagicDamage", "onAttackMagicDamage", 0D, 0.2D, .4D, .6D, .8D, 1D);
        bonusMagicScaling = getDoubleList(builder, "On Attack Magic Damage Scaling", "onAttackMagicScaling", 0D, 0.35D, .35D, .35D, .35D, 0.35D);
        onhitSlowStrength = getDoubleList(builder, "On Hit Slow", "onhitSlowPercentage", 0.05, 0.10, 0.15, 0.2, 0.25);
        onhitSlowDuration = getDoubleList(builder, "On Hit Slow Duration", "onhitSlowDuration", 0D, 1D, 1D, 1D, 1D, 1D);
        armorShred = getDoubleList(builder, "On Hit Armor Shred", "onHitArmorShred", 0D, .1D, .15D, .2D, .25D, .3D);
        onhitArmorDuration = getDoubleList(builder, "On Hit Armor Shred Duration", "onHitArmorDuration", 0D, 5D, 5D, 5D, 5D, 5D);
        trueDamage = getDoubleList(builder, "On Hit True Damage", "onHitTrueDamage", 0D, 10D, 20D, 30D, 40D, 50D);


        activeMagicDamage = getDoubleList(builder, "Active Magic Damage", "activeMagicDamage", 0d, 60d, 100d, 140d, 180d, 220d);
        activeMagicScaling = getDoubleList(builder, "Active Magic Scaling", "activeMagicScaling", 0d, 40d, 45d, 50d, 55d, 60d);

        activeSlow = getDoubleList(builder, "Active Slow", "activeSlow", 0d, 10d, 15d, 20d, 25d, 30d);
        activeSlowScaling = getDoubleList(builder, "Active Slow Scaling per 50 Power", "activeSlowScaling", 0d, 0.05, 0.05, 0.05, 0.05, 0.05);
        activeSlowDuration = getDoubleList(builder, "Active Slow Duration", "activeSlowDuration", 0d, 2.25, 2.25, 2.25, 2.25, 2.25);
        heatBonusPercentage = getDoubleList(builder, "Heat Bonus for Slow and Damage", "heatBonusPercentage", 0.d, 0.01, 0.01, 0.01, .01, 0.01);
        cooldown = getDoubleList(builder, "cooldown", "cooldown", 0.d, 14d, 14d, 14d, 14d, 14D);
        buffDuration = getDoubleList(builder, "Active Buff Duration", "buffduration", 0.d, 5d, 5d, 5d, 5d, 5d);
        cost = getDoubleList(builder, "Heat Consumed", "cost", 0.d, 25d, 25d, 25d, 25d, 25D);

        config = builder.build();


    }








}
