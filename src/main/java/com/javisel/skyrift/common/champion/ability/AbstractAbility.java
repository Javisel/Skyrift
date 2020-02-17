package com.javisel.skyrift.common.champion.ability;


import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import javax.annotation.Nullable;

public abstract class AbstractAbility extends Item {


    private final EnumAbilityTags[] abilityTags;

    public AbstractAbility(String name, Properties properties, EnumAbilityTags... abilityTags) {
        super(properties);
        setRegistryName(name);
        this.abilityTags = abilityTags;
    }

    public EnumAbilityTags[] getAbilityTags() {
        return abilityTags;
    }

    public void setData(PlayerEntity entity, ItemStack stack) {


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


        return 0;
    }


    public boolean isCastable(PlayerEntity caster, ItemStack castitem) {

        return true;

    }

    public void rankupAbility(PlayerEntity caster, ItemStack castitem) {
    }

    public void commitCosts(PlayerEntity caster, ItemStack castitem) {
    }


    public boolean containsTag(EnumAbilityTags tag) {


        for (EnumAbilityTags taglist : abilityTags) {


            if (taglist == tag) {
                return true;
            }
        }
        return false;
    }

    /**
     * If this function returns true (or the item is damageable), the ItemStack's NBT tag will be sent to the client.
     */
    @Override
    public boolean shouldSyncTag() {
        return true;
    }

    public void passiveTrigger(PlayerEntity entity, EnumPassiveTriggers passivetrigger) {


    }

    public void tick(PlayerEntity playerEntity, ItemStack stack) {


        if (SkyriftUtilities.getDeviceData(stack).getCurrentCooldown() != 0) {

            SkyriftUtilities.getDeviceData(stack).setCooldown(SkyriftUtilities.getDeviceData(stack).getCurrentCooldown() - 1);

        }

        System.out.println("Current Cooldown: " + SkyriftUtilities.getDeviceData(stack).getCurrentCooldown()/20);




    }

    public void forceActivate(PlayerEntity caster, ItemStack castitem) {
    }


    public void startAbility(PlayerEntity caster, ItemStack castitem) {


    }

    public boolean attemptCast(PlayerEntity caster, ItemStack castitem) {


        SkyriftUtilities.getDeviceData(castitem).setCooldown(600);
        SkyriftUtilities.getDeviceData(castitem).setMaxcooldown(600);

        return false;
    }


    public void endAbility(PlayerEntity caster, ItemStack castitem) {
    }

    public void interruptAbility(PlayerEntity caster, @Nullable PlayerEntity interupter) {
    }

    public void removeAbility(PlayerEntity caster, ItemStack castitem) {
    }

}
