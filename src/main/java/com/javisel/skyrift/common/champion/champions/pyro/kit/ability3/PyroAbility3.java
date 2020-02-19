package com.javisel.skyrift.common.champion.champions.pyro.kit.ability3;

import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.registration.ItemProperties;

import static com.javisel.skyrift.common.champion.ChampionDatabase.championConfigs;
import static com.javisel.skyrift.common.champion.ChampionDatabase.pyro;

public class PyroAbility3 extends AbstractAbility {
    public PyroAbility3() {
        super("pyro_ability_3",championConfigs.get("pyro").abilityConfigs.get(4), ItemProperties.SPELL_PROPERTIES, EnumAbilityTags.BUFF, EnumAbilityTags.HEALING, EnumAbilityTags.SELFISH);
    }
}

