package com.javisel.skyrift.common.champion.champions.pyro.kit.passive;

import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import net.minecraft.item.Item;

import static com.javisel.skyrift.common.champion.ChampionDatabase.championConfigs;
import static com.javisel.skyrift.common.champion.ChampionDatabase.pyro;

public class PyroPassive extends AbstractAbility {


    public PyroPassive() {
        super("pyro_passive",championConfigs.get("pyro").abilityConfigs.get(0), new Item.Properties().maxStackSize(1), EnumAbilityTags.BUFF, EnumAbilityTags.PASSIVE, EnumAbilityTags.AOE);
    }







}
