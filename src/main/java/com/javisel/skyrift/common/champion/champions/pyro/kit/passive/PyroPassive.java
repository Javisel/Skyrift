package com.javisel.skyrift.common.champion.champions.pyro.kit.passive;

import com.javisel.skyrift.common.capabilities.entitydata.IEntityData;
import com.javisel.skyrift.common.capabilities.entitydata.IPlayerData;
import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.champion.ability.AbilityConfig;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.champion.ability.EnumPassiveTriggers;
import com.javisel.skyrift.common.champion.champions.pyro.kit.ability2.PyroA2Config;
import com.javisel.skyrift.common.damagesource.EnumDamageArchetype;
import com.javisel.skyrift.common.damagesource.EnumDamageDevice;
import com.javisel.skyrift.common.damagesource.EnumDamageType;
import com.javisel.skyrift.common.damagesource.SkyRiftDamageSource;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.AxisAlignedBB;
import org.apache.logging.log4j.core.util.UuidUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.javisel.skyrift.common.champion.ChampionDatabase.championConfigs;
import static com.javisel.skyrift.common.champion.ChampionDatabase.pyro;

public class PyroPassive extends AbstractAbility {



    UUID MAGIC_POWER_BUFF = UUID.fromString("3deac0d9-fb16-42af-bc78-b21c71f48a76");
    UUID MOVE_SPEED_BUFF  = UUID.fromString("2bf66746-8e12-48cb-8a3d-8235c3c1934d");
    static  AxisAlignedBB aurasize;


    public PyroPassive() {
        super("pyro_passive",championConfigs.get("pyro").abilityConfigs.get(0), new Item.Properties().maxStackSize(1), EnumAbilityTags.BUFF, EnumAbilityTags.PASSIVE, EnumAbilityTags.AOE);


        aurasize= new AxisAlignedBB(((PyroPassiveConfig)this.getConfig()).size.get() ,((PyroPassiveConfig)this.getConfig()).size.get(),((PyroPassiveConfig)this.getConfig()).size.get(),((PyroPassiveConfig)this.getConfig()).size.get(),((PyroPassiveConfig)this.getConfig()).size.get(),((PyroPassiveConfig)this.getConfig()).size.get());
    }


    @Override
    public void setData(PlayerEntity entity, ItemStack stack) {
        super.setData(entity, stack);
        stack.getTag().putByte(RANK, (byte) 1);
        stack.getTag().putFloat(MAX_BUFF_DURATION, 200);
        stack.getTag().putBoolean(DISPLAY_COUNT, true);

    }

    public void addHeat(PlayerEntity playerEntity, float amount ) {



        SkyriftUtilities.getEntityData(playerEntity).addResourceAmount(amount);
        recalculateHeatBuffs(playerEntity);

    }


    @Override
    public void tick(PlayerEntity playerEntity, ItemStack stack) {
        super.tick(playerEntity, stack);



        stack.getTag().putFloat(BUFF_DURATION,SkyriftUtilities.getEntityData(playerEntity).getCombatTimer());

        if (stack.getTag().getFloat(BUFF_DURATION) <=0 && playerEntity.ticksExisted %20==0 &&!playerEntity.getEntityWorld().isRemote) {

            SkyriftUtilities.getEntityData(playerEntity).addResourceAmount(-5);

            recalculateHeatBuffs(playerEntity);
        } PyroPassiveConfig config = (PyroPassiveConfig) getConfig();

        if (stack.getTag().getByte(COUNT)>=1 && playerEntity.ticksExisted%  (20*config.tickRate.get().get(stack.getTag().getByte(COUNT)))  ==0) {



            AxisAlignedBB bounds = new AxisAlignedBB(playerEntity.getPosition().add(aurasize.minX*-1,aurasize.minY*-1,aurasize.minZ*-1),playerEntity.getPosition().add(aurasize.maxX,aurasize.maxY,aurasize.maxZ));

          List<Entity> e = playerEntity.getEntityWorld().getEntitiesInAABBexcluding(playerEntity, bounds,  (o) -> o instanceof LivingEntity );


          float damageamount = (float) (config.flatDamage.get() + (config.damagePerRank.get() + (SkyriftUtilities.getEntityData(playerEntity).getMagicalPower().getValue()*  config.damagePowerScalingperRank.get().floatValue()))  *stack.getTag().getByte(COUNT) );

          SkyRiftDamageSource damageSource = new SkyRiftDamageSource(playerEntity,damageamount, EnumDamageDevice.ABILITY, EnumDamageArchetype.PERSISITENT, EnumDamageType.MAGIC);


          for (Entity entity : e) {


              entity.attackEntityFrom(damageSource,damageamount);


          }








        }





    }

    public void  recalculateHeatBuffs(PlayerEntity playerEntity) {

        IEntityData entityData = SkyriftUtilities.getEntityData(playerEntity);
        IPlayerData playerData = SkyriftUtilities.getPlayerData(playerEntity);


        entityData.getMagicalPower().removeModifier(MAGIC_POWER_BUFF);
        playerEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).removeModifier(MOVE_SPEED_BUFF);

        float poweramount = (float) (10 +(4.5*playerData.getLevel()));
        poweramount/=100;
        float speedboost = ((PyroA2Config)playerData.getChampion().getBasedata().abilityConfigs.get(3)).passiveMovementSpeed.get().get(playerData.getAbilities().get(3).getTag().getByte(RANK)).floatValue();
        speedboost/=100;
        speedboost*=entityData.getResourceAmount();
        poweramount*=entityData.getResourceAmount();
         speedboost*=0.097;

         if (playerData.getAbilities().get(3).getTag().getFloat(COOLDOWN) !=0) {
             speedboost=0;
         }
        entityData.getMagicalPower().applyModifier(new AttributeModifier(MAGIC_POWER_BUFF,"Pyro Passive Buff",poweramount, AttributeModifier.Operation.ADDITION));
        playerEntity.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).applyModifier(new AttributeModifier(MOVE_SPEED_BUFF,"Pyro 2 buff",speedboost, AttributeModifier.Operation.ADDITION));


        if (entityData.getResourceAmount()%25==0) {


            byte heatrank = (byte) (Math.round(entityData.getResourceAmount() / 25));
            playerData.getAbilities().get(0).getTag().putByte(COUNT,heatrank);
        }
         if  (entityData.getResourceAmount() <=25) {


             playerData.getAbilities().get(0).getTag().putByte(COUNT, (byte) 0);

         }







    }




}
