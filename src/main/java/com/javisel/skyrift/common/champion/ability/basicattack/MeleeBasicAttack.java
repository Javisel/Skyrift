package com.javisel.skyrift.common.champion.ability.basicattack;

import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import static com.javisel.skyrift.main.SkyRift.BASIC_ATTACK_SCALING;
import static com.javisel.skyrift.main.SkyRift.SWINGAMOUNT;

public class MeleeBasicAttack extends BasicAttack {


    protected MeleeBasicAttack(String name, Properties properties) {
        super(name, properties, EnumAbilityTags.BASICATTACK);
    }

    @Override
    public void setData(PlayerEntity entity, ItemStack stack) {
        super.setData(entity, stack);

        stack.getTag().putFloat(SWINGAMOUNT, 0f);
        stack.getTag().putFloat(BASIC_ATTACK_SCALING, 100);

    }


    @Override
    public boolean hitEntity(ItemStack p_77644_1_, LivingEntity p_77644_2_, LivingEntity p_77644_3_) {


        return super.hitEntity(p_77644_1_, p_77644_2_, p_77644_3_);
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


        stack.getTag().putFloat(SWINGAMOUNT, player.getCooledAttackStrength(0));


        return false;
    }

    /**
     * Called when a entity tries to play the 'swing' animation.
     *
     * @param stack
     * @param entity The entity swinging the item.
     * @return True to cancel any further processing by EntityLiving
     */
    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        return false;
    }


}
