package com.javisel.skyrift.common.items;

import com.javisel.skyrift.common.champion.Champion;
import com.javisel.skyrift.main.SkyRift;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public abstract class SoulStone extends Item {

    private Champion champion;

    public SoulStone(Champion champion) {
        super(new Properties().maxStackSize(1).group(SkyRift.skyriftgroup));
        this.champion = champion;
        setRegistryName(champion.getName() + "_soul_stone");


    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity playerEntity, Hand p_77659_3_) {


        if (!p_77659_1_.isRemote) {


            SkyriftUtilities.InitializeChampion(playerEntity, champion);
            playerEntity.sendMessage(new StringTextComponent(" Acquired " + TextFormatting.BOLD + champion.getName() + "'s powers!"));


        }
        return super.onItemRightClick(p_77659_1_, playerEntity, p_77659_3_);
    }


}
