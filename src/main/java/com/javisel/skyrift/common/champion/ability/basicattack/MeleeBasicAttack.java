package com.javisel.skyrift.common.champion.ability.basicattack;

import com.javisel.skyrift.common.capabilities.entitydata.IEntityData;
import com.javisel.skyrift.common.capabilities.entitydata.IPlayerData;
import com.javisel.skyrift.common.champion.ability.EnumAbilityTags;
import com.javisel.skyrift.common.damagesource.EnumDamageDevice;
import com.javisel.skyrift.common.damagesource.SkyRiftDamageSource;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

import static com.javisel.skyrift.main.SkyRift.BASIC_ATTACK_SCALING;
import static com.javisel.skyrift.main.SkyRift.SWINGAMOUNT;

public class MeleeBasicAttack extends BasicAttack {


    protected MeleeBasicAttack(String name, Properties properties) {
        super(name, properties, EnumAbilityTags.BASICATTACK);
    }

    public static void basicAttack(PlayerEntity attacker, Entity target, float swingStrength) {

        IEntityData entityData = SkyriftUtilities.getEntityData(attacker);
        IPlayerData playerData = SkyriftUtilities.getPlayerData(attacker);


        float attackDamage = playerData.getChampion().getBasicAttackDamage(attacker);

        if (MathHelper.nextInt(random, 0, 100) <= entityData.getCritChance().getValue()) {
            attackDamage *= entityData.getCritDamage().getValue();
        }

        if (swingStrength < 0.5f) {

            attackDamage *= 0.25f;
        } else {

            attackDamage *= swingStrength;
        }


        SkyRiftDamageSource damageSource = new SkyRiftDamageSource(attacker, attackDamage, EnumDamageDevice.BASIC_ATTACK, playerData.getChampion().getbasicAttackDamageType());

        target.attackEntityFrom(damageSource, attackDamage);


    }

    @Override
    public void setData(PlayerEntity entity, ItemStack stack) {
        super.setData(entity, stack);

        stack.getTag().putFloat(SWINGAMOUNT, 0f);
        stack.getTag().putFloat(BASIC_ATTACK_SCALING, SkyriftUtilities.getPlayerData(entity).getChampion().getBasedata().getAttackScaling().get().floatValue());

    }

    @Override
    public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {


        if (!super.hitEntity(stack, target, attacker)) {

            if (attacker instanceof PlayerEntity) {
                PlayerEntity playerEntity = (PlayerEntity) attacker;

                basicAttack(playerEntity, target, stack.getTag().getFloat(SWINGAMOUNT));


            }


            return false;
        }

        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, PlayerEntity player, Entity entity) {

        if (!player.world.isRemote) {


            stack.getTag().putFloat(SWINGAMOUNT, player.getCooledAttackStrength(0));

            SkyriftUtilities.getPlayerData(player).getAbilities().set(1, stack);


            return false;
        }
        return true;
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
