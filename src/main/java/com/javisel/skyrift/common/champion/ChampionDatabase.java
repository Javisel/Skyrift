package com.javisel.skyrift.common.champion;

import com.javisel.skyrift.common.champion.champions.pyro.Pyro;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import java.util.HashMap;
import java.util.UUID;

public class ChampionDatabase {

    public static Champion pyro = new Pyro();

    public static HashMap<UUID, Champion> championHashMap = new HashMap<>();


    static {

        championHashMap.put(pyro.getId(), pyro);

    }
}
