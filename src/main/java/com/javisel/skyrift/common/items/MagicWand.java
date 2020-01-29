package com.javisel.skyrift.common.items;

import com.javisel.skyrift.common.capabilities.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.IEntityData;
import com.javisel.skyrift.common.capabilities.IPlayerData;
import com.javisel.skyrift.common.capabilities.PlayerDataProvider;
import com.javisel.skyrift.common.network.EntityDataMessage;
import com.javisel.skyrift.common.network.PlayerDataMessage;
import com.javisel.skyrift.common.registration.PacketRegistration;
import com.javisel.skyrift.main.SkyRift;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;

import java.util.UUID;

import static com.javisel.skyrift.main.SkyriftAttributes.MAGICAL_POWER;


public class MagicWand extends Item {



    public MagicWand() {
        super(new Properties());
        setRegistryName(SkyRift.MODID, "magic_wand");
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     *
     * @param p_77659_1_
     * @param p_77659_2_
     * @param p_77659_3_
     */
    @Override
    public ActionResult<ItemStack> onItemRightClick(World p_77659_1_, PlayerEntity p_77659_2_, Hand p_77659_3_) {


        if (!p_77659_1_.isRemote) {



            IEntityData entityData = p_77659_2_.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);



            IPlayerData playerData = p_77659_2_.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);


            if (!playerData.isChampion()) {
                playerData.setisChampion();

            }

            if (p_77659_2_.isSneaking()) {
                entityData.getExperience().removeAllModifiers();
                playerData.setLevel(1);
            }  else
            {
                SkyriftUtilities.addExp(p_77659_2_, 50);

            }

        }
        return super.onItemRightClick(p_77659_1_, p_77659_2_, p_77659_3_);
    }
}
