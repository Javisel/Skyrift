package com.javisel.skyrift.common.capabilities;

import com.javisel.skyrift.common.champion.Champion;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;

public interface IEntityData {

    //Offensive

    void setGold(int amount);

    float getGold();

    IAttributeInstance getAttackDamage();
    IAttributeInstance getPhysicalPower();
    IAttributeInstance getMagicalPower();
    IAttributeInstance getCritChance();
    IAttributeInstance getCritDamage();
    IAttributeInstance getLifesteal();
    IAttributeInstance getFlatArmorPenetration();
    IAttributeInstance getFlatMagicPenetration();
    IAttributeInstance getPercentArmorPenetration();
    IAttributeInstance getPercentMagicPenetration();
    IAttributeInstance getSpellVamp();


    //DEFENSIVE

    float getHealth();

    void setHealth(float amount);

    IAttributeInstance getMaxHealth();
        IAttributeInstance getHealthRegen();
        IAttributeInstance getResourceRegen();
        IAttributeInstance getArmor();
        IAttributeInstance getMagicResist();

    //UTILITY
    float getResourceAmount();

    void addResourceAmount(float amount);
    IAttributeInstance getMaxResourceAmount();
    IAttributeInstance getCooldownReduction();
    IAttributeInstance getExperience();
    IAttributeInstance getGoldGeneration();
    IAttributeInstance getRange();

    void addHealth(float amount);
    ArrayList<IAttributeInstance> corestats();

    boolean isRanged();

     void loadNBT(CompoundNBT nbt);

void resetData();
    public CompoundNBT writeNBT();
    AttributeMap getAttributeMap();


    void setIsRanged(Boolean isRanged);

    void setResourceAmount(double amount);
}
