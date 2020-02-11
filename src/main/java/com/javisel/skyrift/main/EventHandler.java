package com.javisel.skyrift.main;

import com.javisel.skyrift.common.capabilities.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.EntityDataStorage;
import com.javisel.skyrift.common.champion.attribute.SkyriftAttribute;
import com.javisel.skyrift.common.damagesource.SkyRiftDamageSource;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.javisel.skyrift.main.SkyriftAttributes.MAGICAL_POWER;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)

public class EventHandler {


    @SubscribeEvent
    public void disableDamage(LivingAttackEvent e) {

        if (!(e.getSource() instanceof SkyRiftDamageSource) && e.getSource()!= DamageSource.OUT_OF_WORLD) {

            e.setCanceled(true);
        }


    }

    @SubscribeEvent
    public void livingHungerEvent(TickEvent.PlayerTickEvent e) {

        e.player.getFoodStats().setFoodSaturationLevel(20);


    }



    @SubscribeEvent
    public void logout(PlayerEvent.PlayerLoggedInEvent e) {

        if (!e.getPlayer().getEntityWorld().isRemote) {

            SkyriftUtilities.sync(e.getPlayer());

        }



    }





    @SubscribeEvent
    public void deathEvent(LivingDeathEvent e) {




    }

    @SubscribeEvent
    public void Attributes(EntityJoinWorldEvent e) {

        if (e.getEntity() instanceof LivingEntity) {




            }








    }




}
