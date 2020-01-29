package com.javisel.skyrift.common.items;

import com.javisel.skyrift.common.capabilities.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.IEntityData;
import com.javisel.skyrift.common.capabilities.IPlayerData;
import com.javisel.skyrift.common.capabilities.PlayerDataProvider;
import com.javisel.skyrift.common.champion.Champion;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.UUID;

public abstract class SoulStone extends Item {

   private   Champion champion;

    public SoulStone(Champion champion) {
        super(new Properties().maxStackSize(1));
            this.champion=champion;
        setRegistryName(champion.getName()+"_soul_stone");


    }



    @Override
    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity playerEntity, Hand p_77659_3_) {


        if (!p_77659_1_.isRemote) {


            IPlayerData playerData = playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
             if (playerData.isChampion()) {
                 playerEntity.sendMessage(new StringTextComponent(TextFormatting.RED + "Error: " + TextFormatting.WHITE + " You are already a Champion! You are using " + TextFormatting.BOLD + playerData.getChampion().getName() +"'s powers!"  ));
             } else {
                 playerData.setisChampion();
                    playerData.setChampion(champion);
                    initateChampion(playerEntity);
                 playerEntity.sendMessage(new StringTextComponent( " Acquired " + TextFormatting.BOLD + champion.getName() +"'s powers!"  ));


             }





        }
        return super.onItemRightClick(p_77659_1_, playerEntity, p_77659_3_);
    }


    public void initateChampion(PlayerEntity playerEntity) {

    }




}
