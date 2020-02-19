package com.javisel.skyrift.common.champion.champions.pyro.kit.ability2;

import com.javisel.skyrift.common.champion.ability.AbilityConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class PyroA2Config extends AbilityConfig {


    public ForgeConfigSpec.ConfigValue<List<? extends Double>> passiveMovementSpeed;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeMovementSpeed;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> activeDuration;


    public PyroA2Config() {
        super("pyro", "pyro_ability_2");
    }



    public void configureAbility() {

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment(abilityname + " base statistics");
        cooldown = getDoubleList(builder, "cooldown", "cooldown", 0d,8d,8d,8d,8d,8d);
        cost = getDoubleList(builder, "cost", "cost", 0d,25d,25d,25d,25d,25d);
        passiveMovementSpeed = getDoubleList(builder, "Passive Movement Speed", "passiveSpeed", 0d,0.1,0.12,0.14,0.16,0.18);
        activeMovementSpeed = getDoubleList(builder, "Active Movement Speed", "activeSpeed", 0d,.4,.5,.6,.7,.8);

        activeDuration = getDoubleList(builder, "Active Movement Speed Duration", "activeDuration", 0d,2d,2d,2d,2d,2d);

        config = builder.build();


    }




}
