package com.javisel.skyrift.common.champion.champions.pyro;

import com.javisel.skyrift.common.champion.ChampionConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class PyroConfig extends ChampionConfig {


    public PyroConfig() {
        super("pyro");

    }

    @Override
    public void configureChampion() {
        ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        BUILDER.comment(name + " base statistics");
        baseHealth = BUILDER.comment("Base health").defineInRange("baseHealth", 480D, 1, 40000);
        healthPerLevel = BUILDER.comment("Health per level").defineInRange("healthPerLevel", 85D, 1, 40000);
        baseHPS = BUILDER.comment("Base Health Regen.").defineInRange("baseHPS", 1.8, 1, 40000);
        hpsPerLevel = BUILDER.comment("Health Regen per Level").defineInRange("hpsPerLevel", 0.16, 1, 40000);
        basePhysicalDefence = BUILDER.comment("Base Physical Defence").defineInRange("basePhysicalDefence", 17D, 1, 40000);
        physicalDefencePerLevel = BUILDER.comment("Physical Defence per Level").defineInRange("physicalDefencePerLevel", 3D, 1, 40000);
        baseMagicDefence = BUILDER.comment("Base Magic Defence").defineInRange("baseMagicDefence", 30D, 1, 40000);
        magicalDefencePerLevel = BUILDER.comment("Magic Defence per Level").defineInRange("magicDefencePerLevel", 0.9D, 1, 40000);
        baseResource = BUILDER.comment("Base Resource Amount").defineInRange("baseResource", 100D, 1, 40000);

        resourcePerLevel = BUILDER.comment("Resource Per Level").defineInRange("resourcePerLevel", 0D, 0, 40000);
        baseRPS = BUILDER.comment("Base Resource Regen").defineInRange("baseRPS", 0D, 0, 40000);
        rpsPerLevel = BUILDER.comment("Resource Regen per level").defineInRange("rpsPerLevel", 0D, 0, 40000);
        baseAttackDamage = BUILDER.comment("Base Attack Damage").defineInRange("baseAttackDamage", 38D, 1, 40000);
        attackDamagePerLevel = BUILDER.comment("Attack Damage Per Level").defineInRange("attackDamagePerLevel", 2D, 1, 40000);
        attackScaling = BUILDER.comment("Basic Attack Scaling").defineInRange("basicAttackScaling", 1D,0, 40000);

        baseAttackSpeed = BUILDER.comment("Base Attack Speed").defineInRange("baseAttackSpeed", 0.95, 1, 40000);
        attackSpeedPerLevel = BUILDER.comment("Attack Speed Per Level").defineInRange("attackSpeedPerLevel", 0.0145, 1, 40000);
        movementSpeed = BUILDER.comment("Base Movement Speed").defineInRange("baseMovementSpeed", 0.097D, 0, 1);
        isRanged = BUILDER.comment("Is Ranged").define("isRanged", false);


        config = BUILDER.build();
    }
}
