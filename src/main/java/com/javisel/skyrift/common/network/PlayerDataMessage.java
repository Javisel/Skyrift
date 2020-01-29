package com.javisel.skyrift.common.network;

import com.javisel.skyrift.common.capabilities.PlayerDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PlayerDataMessage {

    public static  CompoundNBT nbt;


    public PlayerDataMessage(CompoundNBT nbtag) {

        nbt = nbtag;

    }


    public static void encode(PlayerDataMessage pkt, PacketBuffer buf) {
        buf.writeCompoundTag(nbt);
    }

    public static PlayerDataMessage decode(PacketBuffer buf) {
        return new PlayerDataMessage(buf.readCompoundTag());
    }


    public static class Handler {

        public static void handle(final PlayerDataMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {

                Minecraft minecraft = Minecraft.getInstance();
                minecraft.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new).loadNBT(mes.nbt);

            });

            ctx.get().setPacketHandled(true);

        }
    }

}
