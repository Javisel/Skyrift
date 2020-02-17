package com.javisel.skyrift.common.champion.champions.pyro;

import com.javisel.skyrift.common.champion.Champion;
import com.javisel.skyrift.common.champion.resource.Resource;
import com.javisel.skyrift.common.damagesource.EnumDamageType;
import com.javisel.skyrift.common.registration.AbilityRegistration;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class Pyro extends Champion {


    public Pyro() {
        super(new PyroConfig(), UUID.fromString("32f22b99-049d-41fc-961f-a7ac5b348766"), Resource.Resources.HEAT, EnumDamageType.PHYSICAL, AbilityRegistration.PYRO_PASSIVE, AbilityRegistration.PYRO_BASIC_ATTACK, AbilityRegistration.PYRO_ABILITY_1, AbilityRegistration.PYRO_ABILITY_2, AbilityRegistration.PYRO_ABILITY_3, AbilityRegistration.PYRO_ABILITY_4);
    }


    @Override
    public float getBasicAttackDamage(PlayerEntity playerEntity) {


        return (float) ((SkyriftUtilities.getPlayerData(playerEntity).getChampion().getBasedata().getAttackScaling().get() * SkyriftUtilities.getEntityData(playerEntity).getPhysicalPower().getValue()) + SkyriftUtilities.getEntityData(playerEntity).getAttackDamage().getValue());


    }
}
