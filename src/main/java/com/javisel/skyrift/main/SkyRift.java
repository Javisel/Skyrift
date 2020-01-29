package com.javisel.skyrift.main;

import com.javisel.skyrift.client.OverlayHandler;
import com.javisel.skyrift.common.capabilities.*;
import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.registration.CapabilityRegistration;
import com.javisel.skyrift.common.registration.PacketRegistration;
import net.minecraft.block.Block;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("skyrift")
public class SkyRift {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String RANK = "rank";
    public static final String SWINGAMOUNT = "swingamount";
    public static final String BASIC_ATTACK_SCALING = "bas";
    public static final String BASE_VALUE ="basevalue";
    public static final String MODID = "skyrift";

    public static ChampionDatabase championDatabase;
    public SkyRift() {
        championDatabase = new ChampionDatabase();
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new CapabilityRegistration());

    }

    private void setup(final FMLCommonSetupEvent event) {
        // some preinit code
        CapabilityManager.INSTANCE.register(IEntityData.class, new EntityDataStorage(), EntityData::new);
        CapabilityManager.INSTANCE.register(IPlayerData.class, new PlayerDataStorage(), PlayerData::new);
        PacketRegistration.register();

    }

    public static float getLevelupExp(int level) {


        return 308+(55*(level-2));


    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new OverlayHandler());
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
        // some example code to dispatch IMC to another mod
    }

    private void processIMC(final InterModProcessEvent event) {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m -> m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {



        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Item> blockRegistryEvent) {
            // register a new block here
        }
    }
}
