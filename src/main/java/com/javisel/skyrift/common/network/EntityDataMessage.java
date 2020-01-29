package com.javisel.skyrift.common.network;

import com.javisel.skyrift.common.capabilities.EntityData;
import com.javisel.skyrift.common.capabilities.EntityDataProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class EntityDataMessage {

    public static CompoundNBT nbt;


    public EntityDataMessage(CompoundNBT nbtag) {

        nbt = nbtag;

    }


    public static void encode(EntityDataMessage pkt, PacketBuffer buf) {
        buf.writeCompoundTag(nbt);
    }

    public static EntityDataMessage decode(PacketBuffer buf) {
        return new EntityDataMessage(buf.readCompoundTag());
    }


    public static class Handler {

        public static void handle(final EntityDataMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {

                Minecraft minecraft = Minecraft.getInstance();
                minecraft.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new).loadNBT(mes.nbt);
                System.out.println("Client EXP:" + minecraft.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new).getExperience().getValue());
            });

            ctx.get().setPacketHandled(true);

        }
    }

}
