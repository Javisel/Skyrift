package com.javisel.skyrift.common.network;

import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class AbilityActivationMessage {


    public final int ability;

    public AbilityActivationMessage(int ability) {

        this.ability = ability;


    }


    public static void encode(AbilityActivationMessage pkt, PacketBuffer buf) {
        CompoundNBT nbt = new CompoundNBT();


        nbt.putInt("ability", pkt.ability);
        buf.writeCompoundTag(nbt);
    }

    public static AbilityActivationMessage decode(PacketBuffer buf) {


        return new AbilityActivationMessage(buf.readCompoundTag().getInt("ability"));
    }


    public static class Handler {

        public static void handle(final AbilityActivationMessage mes, Supplier<NetworkEvent.Context> ctx) {


            ctx.get().enqueueWork(() -> {


                ServerPlayerEntity playerEntity = ctx.get().getSender();

                AbstractAbility abstractAbility = (AbstractAbility) SkyriftUtilities.getPlayerData(playerEntity).getAbilities().get(mes.ability).getItem();

                abstractAbility.attemptCast(playerEntity, SkyriftUtilities.getPlayerData(playerEntity).getAbilities().get(mes.ability));


            });

            ctx.get().setPacketHandled(true);

        }
    }

}
