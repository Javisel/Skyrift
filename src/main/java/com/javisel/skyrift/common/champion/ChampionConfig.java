package com.javisel.skyrift.common.champion;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Path;

public class ChampionConfig {
    public ForgeConfigSpec config;
    public String name;
    protected ResourceLocation championDirectory;
    protected ForgeConfigSpec.ConfigValue<Double> baseHealth;
    protected ForgeConfigSpec.ConfigValue<Double> healthPerLevel;
    protected ForgeConfigSpec.ConfigValue<Double> baseHPS;
    protected ForgeConfigSpec.ConfigValue<Double> hpsPerLevel;
    protected ForgeConfigSpec.ConfigValue<Double> basePhysicalDefence;
    protected ForgeConfigSpec.ConfigValue<Double> physicalDefencePerLevel;
    protected ForgeConfigSpec.ConfigValue<Double> baseMagicDefence;
    protected ForgeConfigSpec.ConfigValue<Double> magicalDefencePerLevel;
    protected ForgeConfigSpec.ConfigValue<Double> baseResource;
    protected ForgeConfigSpec.ConfigValue<Double> resourcePerLevel;
    protected ForgeConfigSpec.ConfigValue<Double> baseRPS;
    protected ForgeConfigSpec.ConfigValue<Double> rpsPerLevel;
    protected ForgeConfigSpec.ConfigValue<Double> baseAttackDamage;
    protected ForgeConfigSpec.ConfigValue<Double> attackDamagePerLevel;
    protected ForgeConfigSpec.ConfigValue<Double> baseAttackSpeed;
    protected ForgeConfigSpec.ConfigValue<Double> attackSpeedPerLevel;
    protected ForgeConfigSpec.ConfigValue<Double> movementSpeed;
    protected ForgeConfigSpec.ConfigValue<Boolean> isRanged;

    protected ChampionConfig(String name) {
        this.name = name;
        configureChampion();


        ChampionConfig.loadConfig(config, FMLPaths.CONFIGDIR.get().resolve("skyrift/champions/" + name + "/" + name + "_stats.toml"));

    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {

        CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public ForgeConfigSpec.ConfigValue<Double> getBaseHealth() {
        return baseHealth;
    }

    public ForgeConfigSpec.ConfigValue<Double> getHealthPerLevel() {
        return healthPerLevel;
    }

    public ForgeConfigSpec.ConfigValue<Double> getBaseHPS() {
        return baseHPS;
    }

    public ForgeConfigSpec.ConfigValue<Double> getHpsPerLevel() {
        return hpsPerLevel;
    }

    public ForgeConfigSpec.ConfigValue<Double> getBasePhysicalDefence() {
        return basePhysicalDefence;
    }

    public ForgeConfigSpec.ConfigValue<Double> getPhysicalDefencePerLevel() {
        return physicalDefencePerLevel;
    }

    public ForgeConfigSpec.ConfigValue<Double> getBaseMagicDefence() {
        return baseMagicDefence;
    }

    public ForgeConfigSpec.ConfigValue<Double> getMagicalDefencePerLevel() {
        return magicalDefencePerLevel;
    }

    public ForgeConfigSpec.ConfigValue<Double> getBaseResource() {
        return baseResource;
    }

    public ForgeConfigSpec.ConfigValue<Double> getResourcePerLevel() {
        return resourcePerLevel;
    }

    public ForgeConfigSpec.ConfigValue<Double> getBaseRPS() {
        return baseRPS;
    }

    public ForgeConfigSpec.ConfigValue<Double> getRpsPerLevel() {
        return rpsPerLevel;
    }

    public ForgeConfigSpec.ConfigValue<Double> getBaseAttackDamage() {
        return baseAttackDamage;
    }

    public ForgeConfigSpec.ConfigValue<Double> getAttackDamagePerLevel() {
        return attackDamagePerLevel;
    }

    public ForgeConfigSpec.ConfigValue<Double> getBaseAttackSpeed() {
        return baseAttackSpeed;
    }

    public ForgeConfigSpec.ConfigValue<Double> getAttackSpeedPerLevel() {
        return attackSpeedPerLevel;
    }

    public ForgeConfigSpec.ConfigValue<Double> getMovementSpeed() {
        return movementSpeed;
    }

    public ForgeConfigSpec.ConfigValue<Boolean> getIsRanged() {
        return isRanged;
    }

    protected void setChampionDirectory(ResourceLocation championDirectory) {
        this.championDirectory = championDirectory;
    }

    public void configureChampion() {

        ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        BUILDER.comment(name + " base statistics");
        baseHealth = BUILDER.comment("Base health").defineInRange("baseHealth", 1D, 1, 40000);
        healthPerLevel = BUILDER.comment("Health per level").defineInRange("healthPerLevel", 1D, 1, 40000);
        baseHPS = BUILDER.comment("Base Health Regen.").defineInRange("baseHPS", 1D, 1, 40000);
        hpsPerLevel = BUILDER.comment("Health Regen per Level").defineInRange("hpsPerLevel", 1D, 1, 40000);
        basePhysicalDefence = BUILDER.comment("Base Physical Defence").defineInRange("basePhysicalDefence", 1D, 1, 40000);
        physicalDefencePerLevel = BUILDER.comment("Physical Defence per Level").defineInRange("physicalDefencePerLevel", 1D, 1, 40000);
        baseMagicDefence = BUILDER.comment("Base Magic Defence").defineInRange("baseMagicDefence", 1D, 1, 40000);
        magicalDefencePerLevel = BUILDER.comment("Magic Defence per Level").defineInRange("magicDefencePerLevel", 1D, 1, 40000);
        baseResource = BUILDER.comment("Base Resource Amount").defineInRange("baseResource", 1D, 1, 40000);
        resourcePerLevel = BUILDER.comment("Resource Per Level").defineInRange("resourcePerLevel", 1D, 1, 40000);
        baseRPS = BUILDER.comment("Base Resource Regen").defineInRange("baseRPS", 1D, 1, 40000);
        rpsPerLevel = BUILDER.comment("Resource Regen per level").defineInRange("rpsPerLevel", 1D, 1, 40000);
        baseAttackDamage = BUILDER.comment("Base Attack Damage").defineInRange("baseAttackDamage", 1D, 1, 40000);
        attackDamagePerLevel = BUILDER.comment("Attack Damage Per Level").defineInRange("attackDamagePerLevel", 1D, 1, 40000);
        baseAttackSpeed = BUILDER.comment("Base Attack Speed").defineInRange("baseAttackSpeed", 1D, 1, 40000);
        attackSpeedPerLevel = BUILDER.comment("Attack Speed Per Level").defineInRange("attackSpeedPerLevel", 1D, 1, 40000);
        movementSpeed = BUILDER.comment("Base Movement Speed").defineInRange("baseMovementSpeed", 1D, 1, 40000);
        isRanged = BUILDER.comment("Is Ranged").define("isRanged", false);


        config = BUILDER.build();


    }


}
