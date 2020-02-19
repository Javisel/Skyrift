package com.javisel.skyrift.common.champion;

import com.javisel.skyrift.common.champion.champions.pyro.Pyro;
import com.javisel.skyrift.common.champion.champions.pyro.PyroConfig;

import java.util.HashMap;
import java.util.UUID;

public class ChampionDatabase {

    public static Champion pyro;

    public static HashMap<UUID, Champion> championHashMap = new HashMap<>();
    public static HashMap<String,ChampionConfig> championConfigs = new HashMap<>();

    static {

        championConfigs.put("pyro",new PyroConfig());

    }
    public static void forgeChamps() {

        pyro = new Pyro();
        championHashMap.put(pyro.getId(), pyro);
    }
}
