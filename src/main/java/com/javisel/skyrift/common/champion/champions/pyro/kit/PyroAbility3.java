package com.javisel.skyrift.common.champion.champions.pyro.kit;

import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.registration.ItemProperties;

public class PyroAbility3 extends AbstractAbility {
    public PyroAbility3() {
        super("pyro_ability_3", ItemProperties.SPELL_PROPERTIES, EnumAbilityTags.BUFF, EnumAbilityTags.HEALING, EnumAbilityTags.SELFISH);
    }
}

