package com.javisel.skyrift.common.champion.champions.pyro.kit;

import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.champion.ability.basicattack.BasicAttack;
import com.javisel.skyrift.common.registration.ItemProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import static com.javisel.skyrift.main.SkyRift.SWINGAMOUNT;

public class PyroBasicAttack extends BasicAttack {
    public PyroBasicAttack() {
        super("pyro_basic_attack", ItemProperties.MELEE_BASIC_ATTACK_PROPERTIES, EnumAbilityTags.BASICATTACK);
    }

    /**
     * Called when the player Left Clicks (attacks) an entity. Processed before
     * damage is done, if return value is true further processing is canceled and
     * the entity is not attacked.
     *
     * @param stack  The Item being used
     * @param player The player that is attacking
     * @param entity The entity being attacked
     * @return True to cancel the rest of the interaction.
     */
    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {

        if (super.isCastable(player, stack)) {

            stack.getTag().putFloat(SWINGAMOUNT, player.getCooledAttackStrength(0));


        }


        return false;
    }


}
