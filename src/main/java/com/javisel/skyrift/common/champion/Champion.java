package com.javisel.skyrift.common.champion;

import com.javisel.skyrift.common.capabilities.IPlayerData;
import com.javisel.skyrift.common.capabilities.PlayerData;
import com.javisel.skyrift.common.capabilities.PlayerDataProvider;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.resource.Resource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.UUID;

public abstract class Champion {

   private final  String name;
   private final  ChampionConfig basedata;
    private final ArrayList<AbstractAbility> kit;
    private final Resource resource;
    public UUID getId() {
        return id;
    }

    private final UUID id;
    public Champion(ChampionConfig config, UUID id, Resource resource, ArrayList<AbstractAbility> kit) {
        basedata=config;
        this.kit = kit;
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


        if (playerData.getChampionData().getInt("tickcount") <20) {
            playerData.getChampionData().putInt("tickcount", playerData.getChampionData().getInt("tickcount") + 1);


        }
        else {
            playerData.getChampionData().putInt("tickcount", 0);


            //TODO HEALING
        }



    }

    public void levelUp(PlayerEntity playerEntity) {


    }

    @OnlyIn(Dist.CLIENT)
    public void renderOverlayData() {


    }







}
