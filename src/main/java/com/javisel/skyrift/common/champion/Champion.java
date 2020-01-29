package com.javisel.skyrift.common.champion;

import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.resource.Resource;

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
}
