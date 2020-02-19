package com.javisel.skyrift.common.champion.champions.pyro.kit.ability3;

import com.javisel.skyrift.common.champion.ability.AbilityConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class PyroA3Config  extends AbilityConfig {




    public ForgeConfigSpec.ConfigValue<List<? extends Double>> burstHeal;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> healMagicScaling;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> shield;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> shieldMagicScaling;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> shieldPhysicalScaling;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> armor;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> magicResist;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> duration;







    public PyroA3Config() {
        super("pyro", "pyro_ability_3");
    }







    @Override
    public void configureAbility() {

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment(abilityname + " base statistics");
        cooldown = getDoubleList(builder, "cooldown", "cooldown", 0d,18d,18d,18d,18d,18d);
        cost = getDoubleList(builder, "cost", "cost", 0d,40d,40d,40d,40d,40d);
        burstHeal = getDoubleList(builder,"Burst Heal","bursthealbase",0d,30d,50d,70d,90d,110d);
        healMagicScaling = getDoubleList(builder,"Heal Magical Scaling","healScaling",15d,15d,15d,15d,15d,15d);
        shield = getDoubleList(builder,"Shield Amount","shield",0d,100d,150d,200d,250d,300d);
        shieldMagicScaling = getDoubleList(builder,"Shield Magic power scaling","shieldMagicScaling",0d,25d,25d,25d,25d,25d);
        shieldPhysicalScaling = getDoubleList(builder,"Shield Physical power scaling","shieldPhysicalScaling",0d,50d,50d,50d,50d,50d);
        armor = getDoubleList(builder,"Bonus Armor","armor",0d,5d,10d,15d,20d,25d);
        magicResist = getDoubleList(builder,"Bonus Magic Resist","magicResist",0d,5d,10d,15d,20d,25d);
        duration = getDoubleList(builder,"Shield Duration","duration",0d,5d,5d,5d,5d,5d);








        config = builder.build();


    }

}
