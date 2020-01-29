package com.javisel.skyrift.common.champion.champions.pyro;

import com.javisel.skyrift.common.champion.Champion;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.champions.pyro.PyroConfig;

import java.util.ArrayList;
import java.util.UUID;

public class Pyro extends Champion {


    public Pyro() {
        super(new PyroConfig(), UUID.fromString("32f22b99-049d-41fc-961f-a7ac5b348766"),new Heat(), new ArrayList<>());
    }







}
