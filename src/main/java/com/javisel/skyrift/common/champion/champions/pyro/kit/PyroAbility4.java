package com.javisel.skyrift.common.champion.champions.pyro.kit;

import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.registration.ItemProperties;

public class PyroAbility4 extends AbstractAbility {
    public PyroAbility4() {
        super("pyro_ability_4", ItemProperties.SPELL_PROPERTIES, EnumAbilityTags.AOE,EnumAbilityTags.ACTIVE,EnumAbilityTags.DAMAGE);
    }
}

