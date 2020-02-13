package com.javisel.skyrift.common.champion;

import com.javisel.skyrift.common.capabilities.*;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.resource.Resource;
import com.javisel.skyrift.common.damagesource.SkyRiftDamageSource;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public abstract class Champion {

   private final  String name;
   private final  ChampionConfig basedata;
    private  ArrayList<AbstractAbility> kit = new ArrayList<>();
    private final Resource resource;
    public UUID getId() {
        return id;
    }

    private final UUID id;
    public Champion(ChampionConfig config, UUID id, Resource resource,AbstractAbility...kit) {
        basedata=config;

        this.kit.addAll(Arrays.asList(kit));
        name=basedata.getName();
        this.id=id;
        this.resource=resource;

    }


    public float getBasicAttackDamage(PlayerEntity playerEntity) {



        return  0;
    }



    public String getName() {
        return name;
    }

    public ChampionConfig getBasedata() {
        return basedata;
    }

    public ArrayList<AbstractAbility> getKit() {
        return kit;
    }


    public Resource getResource() {
        return resource;
    }

    public void tick(PlayerEntity playerEntity) {

        IPlayerData playerData = playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IEntityData entityData = playerEntity.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);


        if (playerData.getChampionData().getInt("tickcount") <20) {
            playerData.getChampionData().putInt("tickcount", playerData.getChampionData().getInt("tickcount") + 1);


        }
        else {
            playerData.getChampionData().putInt("tickcount", 0);
            SkyriftUtilities.heal(playerEntity,playerEntity, (float) entityData.getHealthRegen().getValue());
            SkyriftUtilities.addResource(playerEntity,playerEntity, (float) entityData.getResourceRegen().getValue());
            //TODO HEALING
        }



    }

    public void construct(PlayerEntity playerEntity) {

        IPlayerData playerData = playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IEntityData entityData = playerEntity.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);


        for (int i = 0; i < getKit().size();i++) {

            playerData.getAbilities().add(new ItemStack(getKit().get(i)));


        }

        for (int i= 0; i <playerData.getAbilities().size();i++) {

            ((AbstractAbility)playerData.getAbilities().get(i).getItem()).setData(playerEntity,playerData.getAbilities().get(i));


        }



    }







    public void onAttacked(PlayerEntity victim, SkyRiftDamageSource source) {


    }

    public void onPreDamage(DamageSource source, PlayerEntity playerEntity) {



    }
    public void onPostDamage(DamageSource source, PlayerEntity playerEntity) {



    }
    public void onDeath(SkyRiftDamageSource source) {



    }



    public void levelUp(PlayerEntity playerEntity) {


    }

    @OnlyIn(Dist.CLIENT)
    public void renderOverlayData() {


    }







}
