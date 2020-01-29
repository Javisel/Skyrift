package com.javisel.skyrift.common.registration;

import com.javisel.skyrift.common.champion.champions.pyro.Pyro;
import com.javisel.skyrift.common.champion.champions.pyro.PyroSoulStone;
import com.javisel.skyrift.common.items.MagicWand;
import com.javisel.skyrift.main.SkyRift;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(SkyRift.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class ItemRegistration {

    public static Item MAGIC_WAND = null;
    public static  Item PYRO_SOUL_STONE = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll
                (


                        MAGIC_WAND = new MagicWand(),
                        PYRO_SOUL_STONE = new PyroSoulStone()




                );
    }


}
