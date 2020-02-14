package com.javisel.skyrift.common.champion.champions.pyro.kit;

import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.registration.ItemProperties;

public class PyroAbility2 extends AbstractAbility {
    public PyroAbility2() {
        super("pyro_ability_2", ItemProperties.SPELL_PROPERTIES, EnumAbilityTags.BUFF, EnumAbilityTags.MOVEMENT, EnumAbilityTags.SELFISH);
    }
}
