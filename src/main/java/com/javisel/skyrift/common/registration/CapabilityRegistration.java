package com.javisel.skyrift.common.registration;

import com.javisel.skyrift.common.capabilities.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.PlayerDataProvider;
import com.javisel.skyrift.main.SkyRift;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityRegistration {


    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {

        if (event.getObject() instanceof LivingEntity) {
            event.addCapability(new ResourceLocation(SkyRift.MODID, "entitydata"), new EntityDataProvider());

        }
        if (event.getObject() instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(SkyRift.MODID, "playerdata"), new PlayerDataProvider());

        }

    }


}