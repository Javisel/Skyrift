package com.javisel.skyrift.common.champion.champions.pyro.kit;

import com.javisel.skyrift.common.capabilities.entitydata.IEntityData;
import com.javisel.skyrift.common.capabilities.entitydata.IPlayerData;
import com.javisel.skyrift.common.champion.ability.basicattack.MeleeBasicAttack;
import com.javisel.skyrift.common.champion.champions.pyro.kit.passive.PyroPassive;
import com.javisel.skyrift.common.damagesource.EnumDamageArchetype;
import com.javisel.skyrift.common.damagesource.EnumDamageDevice;
import com.javisel.skyrift.common.damagesource.SkyRiftDamageSource;
import com.javisel.skyrift.common.registration.ItemProperties;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import static com.javisel.skyrift.common.champion.ChampionDatabase.championConfigs;
import static com.javisel.skyrift.common.champion.ChampionDatabase.pyro;
import static com.javisel.skyrift.main.SkyRift.SWINGAMOUNT;

public class PyroBasicAttack extends MeleeBasicAttack {
    public PyroBasicAttack() {
        super("pyro_basic_attack", championConfigs.get("pyro").abilityConfigs.get(1),ItemProperties.MELEE_BASIC_ATTACK_PROPERTIES);
    }







    @Override
    public  boolean basicAttack(PlayerEntity attacker, Entity target, float swingStrength, ItemStack swingstack) {


        if  (super.basicAttack(attacker, target, swingStrength, swingstack)) {

            ((PyroPassive)SkyriftUtilities.getPlayerData(attacker).getAbilities().get(0).getItem()).addHeat(attacker, 5* swingStrength);
            return  true;
        }

        return  false;
    }



}
