package com.javisel.skyrift.main;

import com.javisel.skyrift.common.capabilities.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.IEntityData;
import com.javisel.skyrift.common.capabilities.IPlayerData;
import com.javisel.skyrift.common.capabilities.PlayerDataProvider;
import com.javisel.skyrift.common.network.EntityDataMessage;
import com.javisel.skyrift.common.network.PlayerDataMessage;
import com.javisel.skyrift.common.registration.PacketRegistration;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.fml.network.NetworkDirection;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

public class SkyriftUtilities {



    public static boolean isChampion(PlayerEntity playerEntity) {


        return  playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new).isChampion();

    }

    public static void addExp(PlayerEntity player, double exp) {


        IPlayerData playerData = player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IEntityData entityData = player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);


        if (playerData.getLevel()==20) {
            return;
        }


        entityData.getExperience().applyModifier(new AttributeModifier(UUID.randomUUID(),"Experience Bonus",exp, AttributeModifier.Operation.ADDITION));

        if(entityData.getExperience().getValue() >= SkyRift.getLevelupExp(playerData.getLevel()+1)) {

        playerData.setLevel(playerData.getLevel()+1);

            double newvalue = entityData.getExperience().getValue() - SkyRift.getLevelupExp(playerData.getLevel());
            entityData.getExperience().removeAllModifiers();
            Collection c = entityData.getExperience().getModifiers();

            Iterator i = c.iterator();

            while (i.hasNext()) {

                AttributeModifier at = (AttributeModifier) i.next();
                System.out.println(at.toString());
                UUID id = at.getID();
                entityData.getExperience().removeModifier(id);

            }
            if (newvalue>0) {
                addExp(player, newvalue);
            }





            System.out.println("Player Level is Now : " + playerData.getLevel());
            System.out.println("Level-Up Threshold  is now: " + SkyRift.getLevelupExp(playerData.getLevel()+1));
            System.out.println("New Experience: " +  entityData.getExperience().getValue());

        }

        if (!player.world.isRemote) {
            PacketRegistration.HANDLER.sendTo(new PlayerDataMessage(playerData.writeNBT()), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
            PacketRegistration.HANDLER.sendTo(new EntityDataMessage(entityData.writeNBT()), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

        }



    }




}
