package com.javisel.skyrift.common.champion.champions.pyro;

import com.javisel.skyrift.common.champion.ChampionConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class PyroConfig extends ChampionConfig {

    PassiveData passiveData;
    Ability1Data ability1Data;

    public PyroConfig() {
        super("pyro");

    }

    @Override
    public void configureChampion() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Base Statistics");

        baseHealth = builder.comment("Base health").defineInRange("baseHealth", 480D, 1, 40000);
        healthPerLevel = builder.comment("Health per level").defineInRange("healthPerLevel", 85D, 1, 40000);
        baseHPS = builder.comment("Base Health Regen.").defineInRange("baseHPS", 1.8, 0, 40000);
        hpsPerLevel = builder.comment("Health Regen per Level").defineInRange("hpsPerLevel", 0.16, 0, 40000);
        basePhysicalDefence = builder.comment("Base Physical Defence").defineInRange("basePhysicalDefence", 17D, 0, 40000);
        physicalDefencePerLevel = builder.comment("Physical Defence per Level").defineInRange("physicalDefencePerLevel", 3D, 1, 40000);
        baseMagicDefence = builder.comment("Base Magic Defence").defineInRange("baseMagicDefence", 30D, 1, 40000);
        magicalDefencePerLevel = builder.comment("Magic Defence per Level").defineInRange("magicDefencePerLevel", 0.9D, 0, 40000);
        baseResource = builder.comment("Base Resource Amount").defineInRange("baseResource", 100D, 0, 40000);

        resourcePerLevel = builder.comment("Resource Per Level").defineInRange("resourcePerLevel", 0D, 0, 40000);
        baseRPS = builder.comment("Base Resource Regen").defineInRange("baseRPS", 0D, 0, 40000);
        rpsPerLevel = builder.comment("Resource Regen per level").defineInRange("rpsPerLevel", 0D, 0, 40000);
        baseAttackDamage = builder.comment("Base Attack Damage").defineInRange("baseAttackDamage", 38D, 0, 40000);
        attackDamagePerLevel = builder.comment("Attack Damage Per Level").defineInRange("attackDamagePerLevel", 2D, 0, 40000);
        attackScaling = builder.comment("Basic Attack Scaling").defineInRange("basicAttackScaling", 1D, 0, 40000);

        baseAttackSpeed = builder.comment("Base Attack Speed").defineInRange("baseAttackSpeed", 0.95, 0, 40000);
        attackSpeedPerLevel = builder.comment("Attack Speed Per Level").defineInRange("attackSpeedPerLevel", 0.0145, 0, 40000);
        movementSpeed = builder.comment("Base Movement Speed").defineInRange("baseMovementSpeed", 0.097D, 0, 1);
        isRanged = builder.comment("Is Ranged").define("isRanged", false);

        builder.pop();


        builder.push("Passive Stats");


        passiveData = new PassiveData();
        passiveData.bonusMagicPower = builder.comment("Bonus Magic Power").defineInRange("bonusMagicPower", 0.10D, 0, Double.MAX_VALUE);
        passiveData.magicPowerPerLevel = builder.comment("Bonus Magic Power per Level").defineInRange("bonusMagicPowerperLevel", 0.045, 0, Double.MAX_VALUE);
        passiveData.flatDamage = builder.comment("Flat Magic Damage").defineInRange("flatDamage", 5, 0, Double.MAX_VALUE);
        passiveData.damagePerRank = builder.comment("Magic Damage per Rank. Rank refers to Heat thresholds.").defineInRange("damagePerRank", 5, 0, Double.MAX_VALUE);
        passiveData.damagePowerScalingperRank = builder.comment("Burn Power Scaling per Rank").defineInRange("burnPowerScaling", 0.025, 0, Double.MAX_VALUE);


        passiveData.size = getDoubleList(builder, "Burn Size", "burnSize", 0D, 2.5, 2.5, 2.5D);

        passiveData.tickRate = getDoubleList(builder, "Burn Tick Rate", "tickRate", 0D, 1d, 0.8d, 0.6d, 0.4d);

        builder.pop();

        builder.push("Ability 1 Data");
        ability1Data = new Ability1Data();
        ability1Data.bonusMagicDamage = getDoubleList(builder, "onAttackMagicDamage", "onAttackMagicDamage", 0D, 0.2D, .4D, .6D, .8D, 1D);
        ability1Data.bonusMagicScaling = getDoubleList(builder, "On Attack Magic Damage Scaling", "onAttackMagicScaling", 0D, 0.035D, .035D, .035D, .035D, 0.035D);
        ability1Data.onhitSlowStrength = getDoubleList(builder, "On Hit Slow", "onhitSlowPercentage", 0.05, 0.10, 0.15, 0.2, 0.25);
        ability1Data.onhitSlowDuration = getDoubleList(builder, "On Hit Slow Duration", "onhitSlowDuration", 0D, 1D, 1D, 1D, 1D, 1D);
        ability1Data.armorShred = getDoubleList(builder, "On Hit Armor Shred", "onHitArmorShred", 0D, .1D, .15D, .2D, .25D, .3D);
        ability1Data.onhitArmorDuration = getDoubleList(builder, "On Hit Armor Shred Duration", "onHitArmorDuration", 0D, 5D, 5D, 5D, 5D, 5D);
        ability1Data.trueDamage = getDoubleList(builder, "On Hit True Damage", "onHitTrueDamage", 0D, 10D, 20D, 30D, 40D, 50D);
        ability1Data.activeMagicDamage = getDoubleList(builder, "Active Magic Damage", "activeMagicDamage", 0d, 60d, 100d, 140d, 180d, 220d);
        ability1Data.activeSlow = getDoubleList(builder, "Active Slow", "activeSlow", 0d, 10d, 15d, 20d, 25d, 30d);
        ability1Data.activeSlowScaling = getDoubleList(builder, "Active Slow Scaling per 50 Power", "activeSlowScaling", 0d, 0.05, 0.05, 0.05, 0.05, 0.05);
        ability1Data.activeSlowDuration = getDoubleList(builder, "Active Slow Duration", "activeSlowDuration", 0d, 2.25, 2.25, 2.25, 2.25, 2.25);
        ability1Data.heatBonusPercentage = getDoubleList(builder, "Heat Bonus for Slow and Damage", "heatBonusPercentage", 0.d, 0.01, 0.01, 0.01, .01, 0.01);
        ability1Data.heatConsumed = getDoubleList(builder, "Heat Consumed", "heatConsumption", 0.d, 0.01, 0.01, 0.01, .01, 0.01);
        ability1Data.cooldown = getDoubleList(builder, "cooldown", "A1cooldown", 0.d, 14d, 14d, 14d, 14d, 14D);
        ability1Data.buffDuration = getDoubleList(builder, "Active Buff Duration", "A1cooldown", 0.d, 5d, 5d, 5d, 5d, 5d);

        builder.pop();


        config = builder.build();
    }

    public static class PassiveData {
        public ForgeConfigSpec.ConfigValue<Double> bonusMagicPower;
        public ForgeConfigSpec.ConfigValue<Double> magicPowerPerLevel;
        public ForgeConfigSpec.ConfigValue<Double> flatDamage;
        public ForgeConfigSpec.ConfigValue<Double> damagePerRank;
        public ForgeConfigSpec.ConfigValue<Double> damagePowerScalingperRank;
        public ForgeConfigSpec.ConfigValue<List<? extends Double>> size;
        public ForgeConfigSpec.ConfigValue<List<? extends Double>> tickRate;


    }

    public static class Ability1Data {
        //Passive Component
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
        public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeSlow;
        public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeSlowDuration;
        public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeSlowScaling;
        public ForgeConfigSpec.ConfigValue<List<? extends Double>> heatBonusPercentage;
        public ForgeConfigSpec.ConfigValue<List<? extends Double>> heatConsumed;
        public ForgeConfigSpec.ConfigValue<List<? extends Double>> cooldown;


    }


}
