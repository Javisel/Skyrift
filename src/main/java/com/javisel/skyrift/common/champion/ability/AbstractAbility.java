package com.javisel.skyrift.common.champion.ability;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

import static com.javisel.skyrift.main.SkyRift.*;

public abstract class AbstractAbility extends Item {


    //USED to display the COOLDOWN overlay
    public static final String DISPLAY_DATA_1 = "display_data_1";
    public static final String DISPLAY_DATA_1_MAX = "display_data_1_max";
    //USED to display a progress bar. Typically for Buffs.
    public static final String DISPLAY_DATA_2 = "display_data_2";
    public static final String DISPLAY_DATA_2_MAX = "display_data_2_max";
    public static final String DISPLAY_DATA_COST = "display_data_cost";
    public static final String DISPLAY_DATA_COUNT = "display_data_count";
    public static final String DISPLAY_DATA_1_ACTIVE = "display_data_1_active";
    public static final String DISPLAY_DATA_2_ACTIVE = "display_data_2_active";
    public static final String DISPLAY_DATA_COST_ACTIVE = "display_data_cost_active";
    public static final String DISPLAY_DATA_COUNT_ACTIVE = "display_data_count_active";
    public static final String CAN_RANK_UP = "can_rank_up";
    private final String name;
    private final EnumAbilityTags[] abilityTags;
    public AbstractAbility(String name, Properties properties, EnumAbilityTags... abilityTags) {
        super(properties);
        setRegistryName(name);
        this.name = name;
        this.abilityTags = abilityTags;
    }

    public EnumAbilityTags[] getAbilityTags() {
        return abilityTags;
    }

    public void setData(PlayerEntity entity, ItemStack stack) {

        CompoundNBT nbt;

        nbt = stack.hasTag() ? stack.getTag() : new CompoundNBT();


        nbt.putInt(RANK, 0);
        nbt.putInt(MODE, 0);
        nbt.putBoolean(CAN_RANK_UP, false);
        CompoundNBT clienttag = new CompoundNBT();

        clienttag.putFloat(DISPLAY_DATA_1, 0);
        clienttag.putFloat(DISPLAY_DATA_1_MAX, 0);
        clienttag.putFloat(DISPLAY_DATA_2, 0);
        clienttag.putFloat(DISPLAY_DATA_2_MAX, 0);
        clienttag.putFloat(DISPLAY_DATA_COST, 0);
        clienttag.putFloat(DISPLAY_DATA_COUNT, 0);
        clienttag.putBoolean(DISPLAY_DATA_1_ACTIVE, false);
        clienttag.putBoolean(DISPLAY_DATA_2_ACTIVE, false);
        clienttag.putBoolean(DISPLAY_DATA_COST_ACTIVE, false);
        clienttag.putBoolean(DISPLAY_DATA_COUNT_ACTIVE, false);


        nbt.put(DISPLAYDATA, new CompoundNBT());

        stack.setTag(nbt);


    }

    public float getCost(PlayerEntity playerEntity, ItemStack stack) {


        return 0;
    }


    public boolean isCastable(LivingEntity caster, ItemStack castitem) {


        return true;

    }

    public void rankupAbility(LivingEntity caster, ItemStack castitem) {
    }

    public void commitCosts(LivingEntity caster, ItemStack castitem) {
    }


    public boolean containsTag(EnumAbilityTags tag) {


        for (EnumAbilityTags taglist : abilityTags) {


            if (taglist == tag) {
                return true;
            }
        }
        return false;
    }


    public void passiveTrigger(LivingEntity entity, EnumPassiveTriggers passivetrigger) {


    }

    public void tick(PlayerEntity playerEntity, ItemStack stack) {


    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerLivingEntity, Hand hand) {


        if (attemptCast(playerLivingEntity, playerLivingEntity.getHeldItem(hand))) {

        }


        return super.onItemRightClick(world, playerLivingEntity, hand);
    }

    public void forceActivate(LivingEntity caster, ItemStack castitem) {
    }


    public void startAbility(LivingEntity caster, ItemStack castitem) {


    }

    public boolean attemptCast(LivingEntity caster, ItemStack castitem) {

        if (isCastable(caster, castitem)) {
            startAbility(caster, castitem);
            return true;
        }
        return false;
    }

    public void tickAbility(LivingEntity caster, ItemStack castitem) {
    }


    public void endAbility(LivingEntity caster, ItemStack castitem) {
    }

    public void interruptAbility(LivingEntity caster, @Nullable LivingEntity interupter) {
    }

    public void removeAbility(LivingEntity caster, ItemStack castitem) {
    }


}
