package com.javisel.skyrift.client;

import com.javisel.skyrift.common.network.AbilityActivationMessage;
import com.javisel.skyrift.common.registration.PacketRegistration;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ClientEventHandler {


    @SubscribeEvent
    public void onPressed(InputEvent.KeyInputEvent e) {

        if (Minecraft.getInstance().player != null && !SkyriftUtilities.getPlayerData(Minecraft.getInstance().player).isChampion()) {
            return;
        }

        if (KeyBindings.ABILITY1.isPressed()) {


            PacketRegistration.HANDLER.sendToServer(new AbilityActivationMessage(2));
        }
        if (KeyBindings.ABILITY2.isPressed()) {

            PacketRegistration.HANDLER.sendToServer(new AbilityActivationMessage(3));
        }
        if (KeyBindings.ABILITY3.isPressed()) {

            PacketRegistration.HANDLER.sendToServer(new AbilityActivationMessage(4));
        }
        if (KeyBindings.ABILITY4.isPressed()) {

            PacketRegistration.HANDLER.sendToServer(new AbilityActivationMessage(5));
        }


    }


}
