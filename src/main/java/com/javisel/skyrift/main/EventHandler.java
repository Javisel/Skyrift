package com.javisel.skyrift.main;

import com.javisel.skyrift.common.capabilities.entitydata.EntityData;
import com.javisel.skyrift.common.capabilities.entitydata.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.entitydata.IPlayerData;
import com.javisel.skyrift.common.capabilities.entitydata.PlayerDataProvider;
import com.javisel.skyrift.common.damagesource.EnumDamageType;
import com.javisel.skyrift.common.damagesource.SkyRiftDamageSource;
import com.javisel.skyrift.common.entity.BypassDamage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)

public class EventHandler {


    @SubscribeEvent
    public void disableDamage(LivingAttackEvent e) {

        if (!(e.getSource() instanceof SkyRiftDamageSource) && e.getSource() != DamageSource.OUT_OF_WORLD && !(e.getSource() instanceof BypassDamage)) {


            if (e.getSource().getTrueSource() instanceof PlayerEntity) {

                e.getEntityLiving().heal(5);
            } else {

                e.setCanceled(true);
            }
        } else {

            if (e.getEntityLiving() instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) e.getEntityLiving();

                playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new).getChampion().onAttacked(playerEntity, (SkyRiftDamageSource) e.getSource());


            }


        }


    }


    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent e) {


        IPlayerData playerData = e.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);

        if (!e.player.world.isRemote && e.player.isAlive()) {

            if (playerData.isChampion()) {
                playerData.tick(e.player);
                e.player.getFoodStats().setFoodLevel(0);

                if ( e.player.getHeldItemMainhand().getItem() != playerData.getAbilities().get(1).getItem() && !e.player.isCreative()) {

                    e.player.inventory.clear();
                    ItemStack stack = playerData.getAbilities().get(1).copy();
                    e.player.setHeldItem(Hand.MAIN_HAND, stack);


                }
                SkyriftUtilities.sync(e.player);
            }

        }
    }


    @SubscribeEvent
    public void logIn(PlayerEvent.PlayerLoggedInEvent e) {

        if (!e.getPlayer().getEntityWorld().isRemote) {

            SkyriftUtilities.sync(e.getPlayer());


        }


    }

    @SubscribeEvent
    public void damageEvent(LivingDamageEvent e) {

        if (e.getEntityLiving() != null) {
            e.getEntityLiving().hurtResistantTime = 0;

        }
        if (e.getSource() instanceof SkyRiftDamageSource) {

            SkyRiftDamageSource damageSource = (SkyRiftDamageSource) e.getSource();
            EntityData victimData = (EntityData) e.getEntityLiving().getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);
            EntityData attackerData = (EntityData) e.getEntityLiving().getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);


            if (e.getEntityLiving() instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) e.getEntityLiving();

                playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new).getChampion().onPreDamage(e.getSource(), playerEntity);

            }

            double newdamage = e.getAmount();
            if (damageSource.damageType == EnumDamageType.TRUE) {

                newdamage = e.getAmount();


            } else if (damageSource.damageType == EnumDamageType.MAGIC || damageSource.damageType == EnumDamageType.PHYSICAL) {

                double flatPen;
                double percentPen;
                double defence;
                if (damageSource.damageType == EnumDamageType.MAGIC) {

                    flatPen = attackerData.getFlatMagicPenetration().getValue();
                    percentPen = attackerData.getPercentMagicPenetration().getValue();
                    defence = victimData.getMagicResist().getValue();
                } else {
                    flatPen = attackerData.getFlatArmorPenetration().getValue();
                    percentPen = attackerData.getPercentArmorPenetration().getValue();
                    defence = victimData.getArmor().getValue();

                }

                newdamage = (e.getAmount() * 100) / ((defence * (1 - (percentPen / 100)) - flatPen));


            }

            if (newdamage <= 0) {
                return;
            }


            victimData.addHealth((float) (newdamage * -1));

            e.getEntityLiving().hurtTime = e.getEntityLiving().maxHurtTime;

            if (victimData.getHealth() <= 0) {

                e.getEntityLiving().setHealth(1);
                e.getEntityLiving().attackEntityFrom(new BypassDamage(e.getSource().getTrueSource(), damageSource), (float) newdamage);


            }

        }


    }


    @SubscribeEvent
    public void deathEvent(LivingDeathEvent e) {

        if (e.getEntityLiving() instanceof PlayerEntity && e.getSource() instanceof SkyRiftDamageSource) {
            PlayerEntity playerEntity = (PlayerEntity) e.getEntityLiving();

            playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new).getChampion().onDeath((SkyRiftDamageSource) e.getSource());


        }


    }


}
