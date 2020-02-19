package com.javisel.skyrift.common.champion.champions.pyro.kit;

import com.javisel.skyrift.common.champion.ability.basicattack.MeleeBasicAttack;
import com.javisel.skyrift.common.registration.ItemProperties;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static com.javisel.skyrift.common.champion.ChampionDatabase.championConfigs;
import static com.javisel.skyrift.common.champion.ChampionDatabase.pyro;

public class PyroBasicAttack extends MeleeBasicAttack {
    public PyroBasicAttack() {
        super("pyro_basic_attack", championConfigs.get("pyro").abilityConfigs.get(1),ItemProperties.MELEE_BASIC_ATTACK_PROPERTIES);
    }


    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     *
     * @param worldIn
     * @param playerIn
     * @param handIn
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        SkyriftUtilities.getEntityData(playerIn).setHealth(50);

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }
}
