package com.javisel.skyrift.common.champion.champions.pyro;

import com.javisel.skyrift.common.champion.ChampionConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class PyroConfig extends ChampionConfig {

    PassiveData passiveData;
    Ability1Data ability1Data;

    public PyroConfig() {
        super("pyro");
        ability1Data = new Ability1Data();
    }

    @Override
    public void configureChampion() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.push("Base Statistics");
        builder.defineList("Duck", new ArrayList<>(), (o) -> o instanceof Double);

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

        passiveData.bonusMagicPower = builder.comment("Bonus Magic Power").defineInRange("bonusMagicPower", 0.10D, 0, Double.MAX_VALUE);
        passiveData.magicPowerPerLevel = builder.comment("Bonus Magic Power per Level").defineInRange("bonusMagicPowerperLevel", 0.045, 0, Double.MAX_VALUE);
        passiveData.flatDamage = builder.comment("Flat Magic Damage").defineInRange("flatDamage", 5, 0, Double.MAX_VALUE);
        passiveData.damagePerRank = builder.comment("Magic Damage per Rank. Rank refers to Heat thresholds.").defineInRange("damagePerRank", 5, 0, Double.MAX_VALUE);
        passiveData.damagePowerScalingperRank = builder.comment("Burn Power Scaling per Rank").defineInRange("burnPowerScaling", 0.025, 0, Double.MAX_VALUE);


        passiveData.size = getDoubleList(builder, "Burn Size", "burnSize", 2.5, 2.5, 2.5D);

        passiveData.tickRate = getDoubleList(builder, "Burn Tick Rate", "tickRate", 1d, 0.8d, 0.6d, 0.4d);

        builder.pop();

        builder.push("Ability 1 Data");


        config = builder.build();
    }

    public class PassiveData {
        protected ForgeConfigSpec.ConfigValue<Double> bonusMagicPower;
        protected ForgeConfigSpec.ConfigValue<Double> magicPowerPerLevel;
        protected ForgeConfigSpec.ConfigValue<Double> flatDamage;
        protected ForgeConfigSpec.ConfigValue<Double> damagePerRank;
        protected ForgeConfigSpec.ConfigValue<Double> damagePowerScalingperRank;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> size;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> tickRate;


    }

    public class Ability1Data {
        //Passive Component
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> bonusMagicDamage;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> bonusMagicScaling;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> onhitSlow;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> armorShred;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> trueDamage;

        //Active
        protected ForgeConfigSpec.ConfigValue<Double> buffDuration;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> activeMagicDamage;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> activeSlow;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> heatBonusPercentage;
        protected ForgeConfigSpec.ConfigValue<List<? extends Double>> heatConsumed;


    }


}
