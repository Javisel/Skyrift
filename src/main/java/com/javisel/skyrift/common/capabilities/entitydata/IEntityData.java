package com.javisel.skyrift.common.capabilities.entitydata;

import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;

public interface IEntityData {

    //Offensive

    float getGold();

    void setGold(int amount);

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

    void setResourceAmount(double amount);

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

    CompoundNBT writeNBT();

    AttributeMap getAttributeMap();

    void setIsRanged(Boolean isRanged);
}
