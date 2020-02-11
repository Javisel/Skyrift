package com.javisel.skyrift.common.champion.champions.pyro;

import com.javisel.skyrift.common.capabilities.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.IEntityData;
import com.javisel.skyrift.common.capabilities.IPlayerData;
import com.javisel.skyrift.common.capabilities.PlayerDataProvider;
import com.javisel.skyrift.common.champion.Champion;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.champions.pyro.PyroConfig;
import net.minecraft.entity.player.PlayerEntity;

import java.util.ArrayList;
import java.util.UUID;

public class Pyro extends Champion {


    public Pyro() {
        super(new PyroConfig(), UUID.fromString("32f22b99-049d-41fc-961f-a7ac5b348766"),new Heat(), new ArrayList<>());
    }


    @Override
    public float getBasicAttackDamage(PlayerEntity playerEntity) {

        IPlayerData playerData = playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        IEntityData entityData = playerEntity.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);





        return (float) ((playerData.getChampion().getBasedata().getAttackScaling().get() * entityData.getPhysicalPower().getValue()) + entityData.getAttackDamage().getValue());


    }
}
