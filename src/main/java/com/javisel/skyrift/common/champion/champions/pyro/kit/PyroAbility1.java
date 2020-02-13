package com.javisel.skyrift.common.champion.champions.pyro.kit;

import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.registration.ItemProperties;
import net.minecraft.item.Item;

public class PyroAbility1 extends AbstractAbility {
    public PyroAbility1() {
        super("pyro_ability_1", ItemProperties.SPELL_PROPERTIES, EnumAbilityTags.BUFF,EnumAbilityTags.DAMAGE,EnumAbilityTags.ACTIVE,EnumAbilityTags.PASSIVE);
    }
}
