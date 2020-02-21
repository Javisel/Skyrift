package com.javisel.skyrift.common.champion.champions.pyro.kit.ability1;

import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.champion.champions.pyro.PyroConfig;
import com.javisel.skyrift.common.registration.ItemProperties;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import static com.javisel.skyrift.common.champion.ChampionDatabase.*;

public class PyroAbility1 extends AbstractAbility {
    public PyroAbility1() {
        super("pyro_ability_1", championConfigs.get("pyro").abilityConfigs.get(2), ItemProperties.SPELL_PROPERTIES, EnumAbilityTags.BUFF, EnumAbilityTags.DAMAGE, EnumAbilityTags.ACTIVE, EnumAbilityTags.PASSIVE);
    }


    @Override
    public float getCost(PlayerEntity playerEntity, ItemStack stack) {


        return (SkyriftUtilities.getEntityData(playerEntity).getResourceAmount() /100) * getConfig().cost.get().get(stack.getTag().getByte(RANK)).floatValue();

    }

    @Override
    public boolean attemptCast(PlayerEntity caster, ItemStack castitem) {

        startCooldown(caster, castitem);
        castitem.getTag().putFloat(BUFF_DURATION,400);
        castitem.getTag().putFloat(MAX_BUFF_DURATION,400);
        castitem.getTag().putByte(COUNT, (byte) 5);
        castitem.getTag().putFloat(COST, 134);
        castitem.getTag().putBoolean(DISPLAY_COUNT, true);

        return  true;
    }








}








