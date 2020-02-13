package com.javisel.skyrift.common.capabilities;

import com.javisel.skyrift.common.champion.attribute.SkyriftAttributeMap;
import com.javisel.skyrift.main.SkyriftAttributes;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;

import java.util.ArrayList;

import static com.javisel.skyrift.main.SkyriftAttributes.*;

public class EntityData implements IEntityData{

    AttributeMap skyriftAttributes = new SkyriftAttributeMap();
    float RESOURCE_AMOUNT=0;
    boolean isRanged = false;
    float health=0;
    float gold = 0;



    @Override

    public void loadNBT(CompoundNBT nbt) {

        skyriftAttributes = new SkyriftAttributeMap();
        SharedMonsterAttributes.readAttributes(skyriftAttributes,  nbt.getList("attributemap",10));

        isRanged=nbt.getBoolean("isranged");
        health=nbt.getFloat("health");


    }

    @Override
    public void addHealth(float amount) {

        if (health+amount>getMaxHealth().getValue()) {
            amount= (float) (getMaxHealth().getValue()-health);


        }

        health+=amount;
        if (health<0) {
            health=0;
        }


    }

    @Override
    public void setGold(int amount) {

        gold=amount;
    }

    @Override
    public float getGold() {
        return gold;
    }

    @Override
    public IAttributeInstance getAttackDamage() {
        return skyriftAttributes.getAttributeInstance(ATTACK_DAMAGE);
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
        nbt.putBoolean("isranged",isRanged);
        nbt.putFloat("health",health);
        return  nbt;
    }




    @Override
    public IAttributeInstance getPhysicalPower() {
                 return skyriftAttributes.getAttributeInstance(PHYSICAL_POWER);

    }

    @Override
    public IAttributeInstance getMagicalPower() {
        return skyriftAttributes.getAttributeInstance(MAGICAL_POWER);

    }


    @Override
    public IAttributeInstance getCritChance() {
        return skyriftAttributes.getAttributeInstance(CRITCHANCE);
    }

    @Override
    public IAttributeInstance getCritDamage() {
                 return skyriftAttributes.getAttributeInstance(CRITDAMAGE);

    }

    @Override
    public void setIsRanged(Boolean isRanged) {
        this.isRanged=isRanged;
    }

    @Override
    public IAttributeInstance getLifesteal() {
        return skyriftAttributes.getAttributeInstance(LIFESTEAL);
    }

    @Override
    public IAttributeInstance getFlatArmorPenetration() {
        return skyriftAttributes.getAttributeInstance(FLATARMORPEN);
    }

    @Override
    public IAttributeInstance getFlatMagicPenetration() {
        return skyriftAttributes.getAttributeInstance(FLATMAGICPEN);
    }

    @Override
    public IAttributeInstance getPercentArmorPenetration() {
        return skyriftAttributes.getAttributeInstance(PERCENTARMORPEN);
    }

    @Override
    public IAttributeInstance getPercentMagicPenetration() {
        return skyriftAttributes.getAttributeInstance(PERCENTMAGICPEN);
    }

    @Override
    public IAttributeInstance getSpellVamp() {
        return skyriftAttributes.getAttributeInstance(SPELLVAMP);

    }

    @Override
    public float getHealth() {
        return health;
    }

    @Override
    public void setHealth(float amount){

        this.health=amount;
        if (health>this.getMaxHealth().getValue()) {
            health= (float) this.getMaxHealth().getValue();
        }
    }


    @Override
    public IAttributeInstance getMaxHealth() {
        return skyriftAttributes.getAttributeInstance(MAX_HEALTH);
    }

    @Override
    public IAttributeInstance getHealthRegen() {
        return skyriftAttributes.getAttributeInstance(HEALTH_REGEN);
    }

    @Override
    public IAttributeInstance getResourceRegen() {
        return skyriftAttributes.getAttributeInstance(RESOURCE_REGEN);
    }

    @Override
    public IAttributeInstance getArmor() {
        return skyriftAttributes.getAttributeInstance(ARMOR);
    }

    @Override
    public IAttributeInstance getMagicResist() {
        return skyriftAttributes.getAttributeInstance(MAGIC_RESIST);
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
        return skyriftAttributes.getAttributeInstance(MAX_RESOURCE_AMOUNT);
    }

    @Override
    public IAttributeInstance getCooldownReduction() {
        return skyriftAttributes.getAttributeInstance(COOLDOWN_REDUCTION);
    }

    @Override
    public IAttributeInstance getExperience() {


        if (skyriftAttributes.getAttributeInstance(EXPERIENCE)==null) {
            System.err.println("There is no attribute with said instance");
            skyriftAttributes.registerAttribute(EXPERIENCE);
            return null;
        } else {

            return skyriftAttributes.getAttributeInstance(EXPERIENCE);

        }
    }

    @Override
    public IAttributeInstance getGoldGeneration() {
        return skyriftAttributes.getAttributeInstance(GOLD_GENERATION);
    }

    @Override
    public IAttributeInstance getRange() {
        return skyriftAttributes.getAttributeInstance(RANGE);
    }


    @Override
    public ArrayList<IAttributeInstance> corestats() {

        ArrayList<IAttributeInstance> corestats =new ArrayList<>();

        corestats.add(skyriftAttributes.getAttributeInstance(PHYSICAL_POWER));
        corestats.add(skyriftAttributes.getAttributeInstance(MAGICAL_POWER));
        corestats.add(skyriftAttributes.getAttributeInstance(ARMOR));
        corestats.add(skyriftAttributes.getAttributeInstance(MAGIC_RESIST));

        return  corestats;



    }



    @Override
    public boolean isRanged() {
        return isRanged;
    }

    public void resetData() {
        skyriftAttributes = new SkyriftAttributeMap();
        RESOURCE_AMOUNT=0;
        isRanged=false;

    }

    @Override
    public void setResourceAmount(double amount) {
        RESOURCE_AMOUNT= (float) amount;
    }
}
