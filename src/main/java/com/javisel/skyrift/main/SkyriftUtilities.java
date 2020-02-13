package com.javisel.skyrift.main;

import com.javisel.skyrift.common.capabilities.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.IEntityData;
import com.javisel.skyrift.common.capabilities.IPlayerData;
import com.javisel.skyrift.common.capabilities.PlayerDataProvider;
import com.javisel.skyrift.common.champion.Champion;
import com.javisel.skyrift.common.champion.attribute.SkyriftAttribute;
import com.javisel.skyrift.common.network.EntityDataMessage;
import com.javisel.skyrift.common.network.PlayerDataMessage;
import com.javisel.skyrift.common.registration.PacketRegistration;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.NetworkDirection;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

public class SkyriftUtilities {



    public static boolean isChampion(PlayerEntity playerEntity) {


        return  playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new).isChampion();

    }


    public static void sync(PlayerEntity player) {


        if (player.getEntityWorld().isRemote) {

            SkyRift.LOGGER.error("Cannot sync from client to server!");

        } else {


            IPlayerData playerData = player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
            IEntityData entityData = player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

            PacketRegistration.HANDLER.sendTo(new PlayerDataMessage(playerData.writeNBT()), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
            PacketRegistration.HANDLER.sendTo(new EntityDataMessage(entityData.writeNBT()), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);


        }


    }






    public static void addExp(PlayerEntity player, double exp) {


        IPlayerData playerData = player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IEntityData entityData = player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);


        if (playerData.getLevel()==20) {
            return;
        }


        entityData.getExperience().applyModifier(new AttributeModifier(UUID.randomUUID(),"Experience Bonus",exp, AttributeModifier.Operation.ADDITION));

        if(entityData.getExperience().getValue() >= SkyRift.getLevelupExp(playerData.getLevel()+1)) {


            levelUp(player);

            double newvalue = entityData.getExperience().getValue() - SkyRift.getLevelupExp(playerData.getLevel());
            Collection c = entityData.getExperience().getModifiers();

            for (Object o : c) {

                AttributeModifier at = (AttributeModifier) o;
                UUID id = at.getID();
                entityData.getExperience().removeModifier(id);

            }
            if (newvalue>0) {
                addExp(player, newvalue);
            }






        }

        if (!player.world.isRemote) {
            PacketRegistration.HANDLER.sendTo(new PlayerDataMessage(playerData.writeNBT()), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
            PacketRegistration.HANDLER.sendTo(new EntityDataMessage(entityData.writeNBT()), ((ServerPlayerEntity) player).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);

        }



    }




    public static void InitializeChampion(PlayerEntity playerEntity, Champion champion) {

        IPlayerData playerData = playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IEntityData entityData = playerEntity.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        playerData.resetData();
        entityData.resetData();
        playerEntity.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(1);
        playerEntity.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).removeAllModifiers();
        playerEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeAllModifiers();;
        playerEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1);
        playerData.setChampion(champion);
        playerData.setisChampion();
        playerData.getChampionData().putInt("tickcount",0);

        entityData.getMaxHealth().setBaseValue(champion.getBasedata().getBaseHealth().get());
        entityData.setHealth((float) entityData.getMaxHealth().getValue());

        entityData.getHealthRegen().setBaseValue(champion.getBasedata().getBaseHPS().get());

        entityData.getArmor().setBaseValue(champion.getBasedata().getBasePhysicalDefence().get());
        entityData.getMagicResist().setBaseValue(champion.getBasedata().getBaseMagicDefence().get());
        entityData.getMaxResourceAmount().setBaseValue(champion.getBasedata().getBaseResource().get());
        if (champion.getResource().spawnFull) {
            entityData.setResourceAmount(entityData.getMaxResourceAmount().getValue());
        }
        entityData.getResourceRegen().setBaseValue(champion.getBasedata().getBaseRPS().get());
        entityData.getAttackDamage().setBaseValue(champion.getBasedata().getBaseAttackDamage().get());

        playerEntity.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).setBaseValue(champion.getBasedata().getBaseAttackSpeed().get());
       playerEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(new AttributeModifier("Base Speed", (playerEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue()-champion.getBasedata().getMovementSpeed().get()) *-1, AttributeModifier.Operation.ADDITION));

        entityData.setIsRanged(champion.getBasedata().getIsRanged().get());
        levelUp(playerEntity);

        sync(playerEntity);
        champion.construct(playerEntity);






    }


    public static void levelUp(PlayerEntity playerEntity) {

        IPlayerData playerData = playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IEntityData entityData = playerEntity.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);



        playerData.setLevel(playerData.getLevel()+1);
        playerData.getChampion().levelUp(playerEntity);
        float healthratio = (float) (entityData.getHealth()/entityData.getMaxHealth().getValue());
        entityData.getMaxHealth().applyModifier(new AttributeModifier(UUID.randomUUID(),"levelup",playerData.getChampion().getBasedata().getHealthPerLevel().get(), AttributeModifier.Operation.ADDITION));

        entityData.setHealth((float) (entityData.getMaxHealth().getValue()*healthratio));
        playerEntity.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).applyModifier(new AttributeModifier(UUID.randomUUID(),"levelup",playerData.getChampion().getBasedata().getAttackSpeedPerLevel().get(), AttributeModifier.Operation.ADDITION));
        entityData.getHealthRegen().applyModifier(new AttributeModifier(UUID.randomUUID(),"levelup",playerData.getChampion().getBasedata().getHpsPerLevel().get(), AttributeModifier.Operation.ADDITION));
        entityData.getArmor().applyModifier(new AttributeModifier(UUID.randomUUID(),"levelup",playerData.getChampion().getBasedata().getPhysicalDefencePerLevel().get(), AttributeModifier.Operation.ADDITION));
        entityData.getMagicResist().applyModifier(new AttributeModifier(UUID.randomUUID(),"levelup",playerData.getChampion().getBasedata().getMagicalDefencePerLevel().get(), AttributeModifier.Operation.ADDITION));
      double resourceratio = entityData.getResourceAmount() / entityData.getMaxResourceAmount().getValue();

        entityData.getMaxResourceAmount().applyModifier(new AttributeModifier(UUID.randomUUID(),"levelup",playerData.getChampion().getBasedata().getResourcePerLevel().get(), AttributeModifier.Operation.ADDITION));
        entityData.setResourceAmount(entityData.getMaxResourceAmount().getValue()*resourceratio);
        entityData.getAttackDamage().applyModifier(new AttributeModifier(UUID.randomUUID(),"levelup",playerData.getChampion().getBasedata().getAttackDamagePerLevel().get(), AttributeModifier.Operation.ADDITION));


//TODO Sound effects


    }


    public static void heal(LivingEntity target, @Nullable LivingEntity source, float amount) {

IEntityData entityData = target.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        if (amount+entityData.getHealth() > entityData.getMaxHealth().getValue()) {
            amount= (float) (entityData.getMaxHealth().getValue()-entityData.getHealth());
        }

        entityData.setHealth(entityData.getHealth()+amount);

        if (target instanceof PlayerEntity) {

            sync((PlayerEntity) target);
        }








    }


    public static void addResource(LivingEntity target, @Nullable LivingEntity source, float amount) {

        IEntityData entityData = target.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        if (amount+entityData.getResourceAmount() > entityData.getMaxResourceAmount().getValue()) {
            amount= (float) (entityData.getMaxResourceAmount().getValue()-entityData.getResourceAmount());
        }

        entityData.setResourceAmount(entityData.getResourceAmount()+amount);

        if (target instanceof PlayerEntity) {

            sync((PlayerEntity) target);
        }








    }

}
