package com.javisel.skyrift.common.champion.champions.pyro.kit.ability4;

import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.registration.ItemProperties;

import static com.javisel.skyrift.common.champion.ChampionDatabase.championConfigs;
import static com.javisel.skyrift.common.champion.ChampionDatabase.pyro;

public class PyroAbility4 extends AbstractAbility {
    public PyroAbility4() {
        super("pyro_ability_4",championConfigs.get("pyro").abilityConfigs.get(5),ItemProperties.SPELL_PROPERTIES, EnumAbilityTags.AOE, EnumAbilityTags.ACTIVE, EnumAbilityTags.DAMAGE, EnumAbilityTags.ULTIMATE);
    }
}

