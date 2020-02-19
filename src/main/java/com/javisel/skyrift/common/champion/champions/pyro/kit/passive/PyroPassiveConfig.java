package com.javisel.skyrift.common.champion.champions.pyro.kit.passive;

import com.javisel.skyrift.common.champion.Champion;
import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.champion.ability.AbilityConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class PyroPassiveConfig extends AbilityConfig {

    public ForgeConfigSpec.ConfigValue<Double> bonusMagicPower;
    public ForgeConfigSpec.ConfigValue<Double> magicPowerPerLevel;
    public ForgeConfigSpec.ConfigValue<Double> flatDamage;
    public ForgeConfigSpec.ConfigValue<Double> damagePerRank;
    public ForgeConfigSpec.ConfigValue<Double> damagePowerScalingperRank;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> size;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> tickRate;


    public PyroPassiveConfig() {
        super("pyro", "pyro_passive");
    }




    @Override
    public void configureAbility() {

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment(abilityname + " base statistics");
        cooldown = getDoubleList(builder, "cooldown", "cooldown", 0d,0d,0d,0d,0d,0d);
        cost = getDoubleList(builder, "cost", "cost", 0d,0d,0d,0d,0d,0d);
        charges = getDoubleList(builder, "charges", "charge", 0d,0d,0d,0d,0d,0d);
        bonusMagicPower = builder.comment("Bonus Magic Power").defineInRange("bonusMagicPower", 0.10D, 0, Double.MAX_VALUE);
        magicPowerPerLevel = builder.comment("Bonus Magic Power per Level").defineInRange("bonusMagicPowerperLevel", 0.045, 0, Double.MAX_VALUE);
        flatDamage = builder.comment("Flat Magic Damage").defineInRange("flatDamage", 5, 0, Double.MAX_VALUE);
        damagePerRank = builder.comment("Magic Damage per Rank. Rank refers to Heat thresholds.").defineInRange("damagePerRank", 5, 0, Double.MAX_VALUE);
        damagePowerScalingperRank = builder.comment("Burn Power Scaling per Rank").defineInRange("burnPowerScaling", 0.025, 0, Double.MAX_VALUE);


        size = getDoubleList(builder, "Burn Size", "burnSize", 0D, 2.5, 2.5, 2.5D);

        tickRate = getDoubleList(builder, "Burn Tick Rate", "tickRate", 0D, 1d, 0.8d, 0.6d, 0.4d);

        config = builder.build();


    }





}
