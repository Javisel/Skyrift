package com.javisel.skyrift.common.champion.champions.pyro.kit;

import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import net.minecraft.item.Item;

public class PyroPassive extends AbstractAbility {


    public PyroPassive() {
        super("pyro_passive", new Item.Properties().maxStackSize(1), EnumAbilityTags.BUFF, EnumAbilityTags.PASSIVE, EnumAbilityTags.AOE);
    }
}
