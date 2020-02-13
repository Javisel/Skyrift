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




    public EnumAbilityTags[] getAbilityTags() {
        return abilityTags;
    }

    private final String name;
    private final EnumAbilityTags abilityTags[];

    public AbstractAbility(String name, Properties properties, EnumAbilityTags...abilityTags) {
        super(properties);
        setRegistryName(name);
        this.name = name;
        this.abilityTags=abilityTags;
    }


    public void setData(PlayerEntity entity,ItemStack stack) {

        CompoundNBT nbt;

        nbt = stack.hasTag() ? stack.getTag() : new CompoundNBT();


        nbt.putInt(RANK,0);
        nbt.putInt(MODE,0);
        nbt.put(DISPLAYDATA,new CompoundNBT() );
        stack.setTag(nbt);


    }




    public boolean isCastable(LivingEntity caster, ItemStack castitem){








        return  true;

    }

    public void rankupAbility(LivingEntity caster, ItemStack castitem) {}

    public void commitCosts(LivingEntity caster, ItemStack castitem) {
    }



    public boolean containsTag(EnumAbilityTags tag) {


        for (EnumAbilityTags taglist : abilityTags) {


            if (taglist==tag) {
                return true;
            }
        }
        return  false;
    }


    public void passiveTrigger(LivingEntity entity, EnumPassiveTriggers passivetrigger) {




    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity playerLivingEntity, Hand hand) {


            if (attemptCast(playerLivingEntity, playerLivingEntity.getHeldItem(hand))) {
                
            }





        return super.onItemRightClick(world, playerLivingEntity, hand);
    }

    public void forceActivate(LivingEntity caster , ItemStack castitem) {}


    public void startAbility(LivingEntity caster, ItemStack castitem){


    }

    public boolean attemptCast(LivingEntity caster, ItemStack castitem) {

        if (isCastable(caster,  castitem)) {
            startAbility(caster,  castitem);
            return true;
        }
            return false;
    }

    public void tickAbility(LivingEntity caster, ItemStack castitem) {}



    public void endAbility(LivingEntity caster, ItemStack castitem) {}

    public void interruptAbility(LivingEntity caster, @Nullable LivingEntity interupter){}

    public void removeAbility(LivingEntity caster, ItemStack castitem){}


}
