package com.javisel.skyrift.common.capabilities;

import com.javisel.skyrift.common.champion.attribute.SkyriftAttributeMap;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import static com.javisel.skyrift.main.SkyriftAttributes.*;

public class EntityData implements IEntityData{

    AttributeMap skyriftAttributes = new SkyriftAttributeMap();
    float RESOURCE_AMOUNT;





    @Override

    public void loadNBT(CompoundNBT nbt) {

        SharedMonsterAttributes.readAttributes(skyriftAttributes,  nbt.getList("attributemap",10));





    }

    @Override
    public AttributeMap getAttributeMap() {
        return  skyriftAttributes;
    }

    @Override
    public CompoundNBT writeNBT(){

        CompoundNBT nbt = new CompoundNBT();
        nbt.putFloat("resourceamount",getResourceAmount());
        nbt.put("attributemap",SharedMonsterAttributes.writeAttributes(skyriftAttributes));


        return  nbt;
    }




    @Override
    public IAttributeInstance getPhysicalPower() {
                 return (IAttributeInstance) skyriftAttributes.getAttributeInstance(PHYSICAL_POWER);

    }

    @Override
    public IAttributeInstance getMagicalPower() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(MAGICAL_POWER);

    }


    @Override
    public IAttributeInstance getCritChance() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(CRITCHANCE);
    }

    @Override
    public IAttributeInstance getCritDamage() {
                 return (IAttributeInstance) skyriftAttributes.getAttributeInstance(CRITDAMAGE);

    }

    @Override
    public IAttributeInstance getLifesteal() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(LIFESTEAL);
    }

    @Override
    public IAttributeInstance getFlatArmorPenetration() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(FLATARMORPEN);
    }

    @Override
    public IAttributeInstance getFlatMagicPenetration() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(FLATMAGICPEN);
    }

    @Override
    public IAttributeInstance getPercentArmorPenetration() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(PERCENTARMORPEN);
    }

    @Override
    public IAttributeInstance getPercentMagicPenetration() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(PERCENTMAGICPEN);
    }

    @Override
    public IAttributeInstance getSpellVamp() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(SPELLVAMP);

    }

    @Override
    public IAttributeInstance getHealthRegen() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(HEALTH_REGEN);
    }

    @Override
    public IAttributeInstance getResourceRegen() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(RESOURCE_REGEN);
    }

    @Override
    public IAttributeInstance getArmor() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(ARMOR);
    }

    @Override
    public IAttributeInstance getMagicResist() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(MAGIC_RESIST);
    }

    @Override
    public float getResourceAmount() {
        return RESOURCE_AMOUNT;
    }

    @Override
    public void addResourceAmount(float amount) {
        RESOURCE_AMOUNT+=amount;
    }

    @Override
    public IAttributeInstance getMaxResourceAmount() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(MAX_RESOURCE_AMOUNT);
    }

    @Override
    public IAttributeInstance getCooldownReduction() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(COOLDOWN_REDUCTION);
    }

    @Override
    public IAttributeInstance getExperience() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(EXPERIENCE);

    }

    @Override
    public IAttributeInstance getGoldGeneration() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(GOLD_GENERATION);
    }

    @Override
    public IAttributeInstance getRange() {
        return (IAttributeInstance) skyriftAttributes.getAttributeInstance(RANGE);
    }

    @Override
    public boolean isRanged() {
        return false;
    }


}
