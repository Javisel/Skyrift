package com.javisel.skyrift.common.champion.champions.pyro.kit.ability2;

import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.registration.ItemProperties;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import static com.javisel.skyrift.common.champion.ChampionDatabase.championConfigs;
import static com.javisel.skyrift.common.champion.ChampionDatabase.pyro;

public class PyroAbility2 extends AbstractAbility {
    public PyroAbility2() {
        super("pyro_ability_2", championConfigs.get("pyro").abilityConfigs.get(3), ItemProperties.SPELL_PROPERTIES, EnumAbilityTags.BUFF, EnumAbilityTags.MOVEMENT, EnumAbilityTags.SELFISH);
    }


    @Override
    public float getCost(PlayerEntity playerEntity, ItemStack stack) {


        return  getConfig().cost.get().get(stack.getTag().getByte(RANK)).floatValue();
    }



    @Override
    public float getMaxCooldown(PlayerEntity playerEntity, ItemStack stack) {






        return (float) (getConfig().cooldown.get().get(stack.getTag().getByte(RANK))   * (1-(SkyriftUtilities.getEntityData(playerEntity).getCooldownReduction().getValue())/100));
    }
}
