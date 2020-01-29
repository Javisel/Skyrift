package com.javisel.skyrift.common.capabilities;

import com.javisel.skyrift.common.champion.Champion;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

public interface IEntityData {

    //Offensive

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
    boolean isRanged();

     void loadNBT(CompoundNBT nbt);


    public CompoundNBT writeNBT();
    AttributeMap getAttributeMap();






}
