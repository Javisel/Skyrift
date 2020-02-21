package com.javisel.skyrift.common.champion.ability;


import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nullable;

public abstract class AbstractAbility extends Item {


    private final EnumAbilityTags[] abilityTags;



    private final AbilityConfig config;
    public AbstractAbility(String name, AbilityConfig config, Properties properties, EnumAbilityTags... abilityTags) {
        super(properties);
        this.config=config;
        setRegistryName(name);
        this.abilityTags = abilityTags;
    }




    public  AbilityConfig getConfig() {
        return config;
    }


    public EnumAbilityTags[] getAbilityTags() {
        return abilityTags;
    }
    //ABILITY MECHANICS

    public static final String COOLDOWN = "cooldown";
    public static final String MAX_COOLDOWN = "max_cooldown";
    public static final String BUFF_DURATION = "buffduration";
    public static final String MAX_BUFF_DURATION = "max_buff_duration";
    public static final String CAST_WINDOW = "cast_window";
    public static final String MAX_CAST_WINDOW = "max_cast_window";
    public static final String COUNT = "count";
    public static final String COST = "cost";
    public static final String RANK = "count";
    public static final String CAN_UPGRADE = "canupgrade";
    public static final String SECONDARYDATA="secondarydata";
    public static final String DISPLAY_COUNT ="displaycount";

    public void setData(PlayerEntity entity, ItemStack stack) {

        CompoundNBT nbt = stack.hasTag() ? stack.getTag() : new CompoundNBT();

        CompoundNBT SECONDARYDATA = new CompoundNBT();
        nbt.put(AbstractAbility.SECONDARYDATA,SECONDARYDATA);
        nbt.putFloat(COOLDOWN,0);
        nbt.putFloat(MAX_COOLDOWN,0);
        nbt.putFloat(BUFF_DURATION,0);
        nbt.putFloat(MAX_BUFF_DURATION,0);
        nbt.putFloat(CAST_WINDOW,0);
        nbt.putFloat(MAX_CAST_WINDOW,0);
        nbt.putByte(RANK, (byte) 0);
        nbt.putByte(COUNT, (byte) 0);
        nbt.putFloat(COST,0);
        nbt.putBoolean(CAN_UPGRADE, false);
        nbt.putBoolean(DISPLAY_COUNT, false);

        stack.setTag(nbt);
    }

    /**
     * Determine if the player switching between these two item stacks
     *
     * @param oldStack    The old stack that was equipped
     * @param newStack    The new stack
     * @param slotChanged If the current equipped slot was changed, Vanilla does not
     *                    play the animation if you switch between two slots that
     *                    hold the exact same item.
     * @return True to play the item change animation
     */
    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }


    public float getCost(PlayerEntity playerEntity, ItemStack stack) {


        return  getConfig().cost.get().get(stack.getTag().getByte(RANK)).floatValue();
    }



    public float getMaxCooldown(PlayerEntity playerEntity, ItemStack stack) {



         float precdr = (getConfig().cooldown.get().get(stack.getTag().getByte(RANK)).floatValue());

         float cdr = (float) (1- (SkyriftUtilities.getEntityData(playerEntity).getCooldownReduction().getValue() /100));
         precdr*=cdr;

        return precdr;

    }


    public boolean startCooldown(PlayerEntity playerEntity, ItemStack stack) {


        stack.getTag().putFloat(COOLDOWN,getMaxCooldown(playerEntity,stack) * 20);



        return  true;
    }

    public float getBuffDuration(PlayerEntity playerEntity, ItemStack stack) {



        return  stack.getTag().getFloat(BUFF_DURATION);
    }





    public float getCurrentCooldown(PlayerEntity playerEntity, ItemStack stack) {



        return  stack.getTag().getFloat(COOLDOWN);
    }




    public boolean isCastable(PlayerEntity caster, ItemStack castitem) {

        return castitem.getTag().getFloat(COOLDOWN) ==0 && getCost(caster,castitem) <= SkyriftUtilities.getEntityData(caster).getResourceAmount()  ;

    }

    public void rankupAbility(PlayerEntity caster, ItemStack castitem) {
    }

    public void commitCosts(PlayerEntity caster, ItemStack castitem) {



        SkyriftUtilities.getEntityData(caster).setResourceAmount(SkyriftUtilities.getEntityData(caster).getResourceAmount() -   getCost(caster,castitem));




    }

    public boolean canPayCost(PlayerEntity caster, ItemStack castitem) {


        return   getCost(caster,castitem) > SkyriftUtilities.getEntityData(caster).getResourceAmount()  ;
    }


    public boolean containsTag(EnumAbilityTags tag) {


        for (EnumAbilityTags taglist : abilityTags) {


            if (taglist == tag) {
                return true;
            }
        }
        return false;
    }



    public void passiveTrigger(PlayerEntity entity, EnumPassiveTriggers passivetrigger) {


    }

    public void tick(PlayerEntity playerEntity, ItemStack stack) {



        if (stack.getTag().getFloat(COOLDOWN) >0) {

            stack.getTag().putFloat(COOLDOWN,stack.getTag().getFloat(COOLDOWN)-1);
        }

        if (stack.getTag().getFloat(BUFF_DURATION) >0) {

            stack.getTag().putFloat(BUFF_DURATION,stack.getTag().getFloat(BUFF_DURATION)-1);
        }

        if (stack.getTag().getFloat(CAST_WINDOW) >0) {

            stack.getTag().putFloat(CAST_WINDOW,stack.getTag().getFloat(CAST_WINDOW)-1);
        }


    }

    public void forceActivate(PlayerEntity caster, ItemStack castitem) {
    }


    public void startAbility(PlayerEntity caster, ItemStack castitem) {










    }

    public boolean attemptCast(PlayerEntity caster, ItemStack castitem) {

        if (isCastable(caster,castitem)) {

            startAbility(caster, castitem);
            commitCosts(caster,castitem);


            return  true;
        }
        return false;
    }


    public void endAbility(PlayerEntity caster, ItemStack castitem) {
    }

    public void interruptAbility(PlayerEntity caster, @Nullable PlayerEntity interupter) {
    }

    public void removeAbility(PlayerEntity caster, ItemStack castitem) {
    }

}
