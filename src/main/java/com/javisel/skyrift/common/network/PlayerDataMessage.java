package com.javisel.skyrift.common.network;

import com.javisel.skyrift.common.capabilities.entitydata.PlayerDataProvider;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerDataMessage {

    public final CompoundNBT nbt;


    public PlayerDataMessage(CompoundNBT nbtag) {

        nbt = nbtag;
        ItemStack test = ItemStack.read(nbt.getCompound("kit_part_2"));

        System.out.println("PDM CDR: "+ SkyriftUtilities.getDeviceData(test).getCurrentCooldown());
    }


    public static void encode(PlayerDataMessage pkt, PacketBuffer buf) {
        buf.writeCompoundTag(pkt.nbt);
    }

    public static PlayerDataMessage decode(PacketBuffer buf) {
        return new PlayerDataMessage(buf.readCompoundTag());
    }


    public static class Handler {

        public static void handle(final PlayerDataMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {

                Minecraft minecraft = Minecraft.getInstance();
                SkyriftUtilities.getPlayerData(minecraft.player).loadNBT(mes.nbt);
                ItemStack test = ItemStack.read(mes.nbt.getCompound("kit_part_2"));

                System.out.println("Client PDM CDR: "+ SkyriftUtilities.getDeviceData(test).getCurrentCooldown());
            });

            ctx.get().setPacketHandled(true);

        }
    }

}
