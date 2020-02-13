package com.javisel.skyrift.common.registration;

import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.champions.pyro.Pyro;
import com.javisel.skyrift.common.champion.champions.pyro.kit.*;
import com.javisel.skyrift.main.SkyRift;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(SkyRift.MODID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)

public class AbilityRegistration {

    //PYRO
    public static PyroPassive PYRO_PASSIVE = null;
    public static PyroBasicAttack PYRO_BASIC_ATTACK = null;
    public static PyroAbility1 PYRO_ABILITY_1 = null;
    public static PyroAbility2 PYRO_ABILITY_2 = null;
    public static PyroAbility3 PYRO_ABILITY_3 = null;
    public static PyroAbility4 PYRO_ABILITY_4 = null;
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void registerAbility(final RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll
                (

                        PYRO_PASSIVE = new PyroPassive(),
                       PYRO_BASIC_ATTACK = new PyroBasicAttack(),
                        PYRO_ABILITY_1 = new PyroAbility1(),
                        PYRO_ABILITY_2 = new PyroAbility2(),
                        PYRO_ABILITY_3 = new PyroAbility3(),
                        PYRO_ABILITY_4 = new PyroAbility4()







                );



    }


}