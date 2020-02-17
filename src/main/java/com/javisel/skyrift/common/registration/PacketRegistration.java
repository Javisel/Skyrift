package com.javisel.skyrift.common.registration;

import com.javisel.skyrift.common.network.AbilityActivationMessage;
import com.javisel.skyrift.common.network.EntityDataMessage;
import com.javisel.skyrift.common.network.PlayerDataMessage;
import com.javisel.skyrift.main.SkyRift;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class PacketRegistration {

    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public static final SimpleChannel HANDLER = NetworkRegistry.ChannelBuilder
            .named(new ResourceLocation(SkyRift.MODID, "main_channel"))
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();
    private static int index;

    public static void register() {

        registerMessage(PlayerDataMessage.class, PlayerDataMessage::encode, PlayerDataMessage::decode, PlayerDataMessage.Handler::handle);
        registerMessage(EntityDataMessage.class, EntityDataMessage::encode, EntityDataMessage::decode, EntityDataMessage.Handler::handle);
        registerMessage(AbilityActivationMessage.class, AbilityActivationMessage::encode, AbilityActivationMessage::decode, AbilityActivationMessage.Handler::handle);
    }

    private static <MSG> void registerMessage(Class<MSG> type, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder,
                                              BiConsumer<MSG, Supplier<NetworkEvent.Context>> consumer) {
        HANDLER.registerMessage(index++, type, encoder, decoder, consumer);
    }

}
