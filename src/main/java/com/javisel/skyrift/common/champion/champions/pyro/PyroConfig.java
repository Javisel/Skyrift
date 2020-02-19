package com.javisel.skyrift.common.champion.champions.pyro;

import com.javisel.skyrift.common.champion.ChampionConfig;
import com.javisel.skyrift.common.champion.ability.AbilityConfig;
import com.javisel.skyrift.common.champion.champions.pyro.kit.ability1.PyroA1Config;
import com.javisel.skyrift.common.champion.champions.pyro.kit.ability2.PyroA2Config;
import com.javisel.skyrift.common.champion.champions.pyro.kit.ability3.PyroA3Config;
import com.javisel.skyrift.common.champion.champions.pyro.kit.ability4.PyroA4Config;
import com.javisel.skyrift.common.champion.champions.pyro.kit.passive.PyroPassiveConfig;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class PyroConfig extends ChampionConfig {

   public    PyroPassiveConfig passiveConfig = new PyroPassiveConfig();
    public  PyroA1Config ability1Config = new PyroA1Config();
    public AbilityConfig basicattack = new AbilityConfig("pyro","basic_attack");
    public PyroA2Config Ability2Config = new PyroA2Config();
    public PyroA3Config Ability3Config = new PyroA3Config();
    public PyroA4Config Ability4Config = new PyroA4Config();

    public PyroConfig() {
        super("pyro");
        super.abilityConfigs.add(passiveConfig);
        super.abilityConfigs.add(basicattack);
        super.abilityConfigs.add(ability1Config);
        super.abilityConfigs.add(Ability2Config);
        super.abilityConfigs.add(Ability3Config);
        super.abilityConfigs.add(Ability4Config);

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




        config = builder.build();
    }




}
