package com.javisel.skyrift.common.capabilities.entitydata;

import net.minecraft.item.DyeColor;

import java.util.Arrays;
import java.util.Comparator;

public enum EnumTeam {

    NONE("NONE",0),
    NEUTRAL("NEUTRAL",1),
    EARTH("EARTH",2),
    MARS("MARS",3);



    private static final EnumTeam[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(EnumTeam::getId)).toArray((p_199795_0_) -> {
        return new EnumTeam[p_199795_0_];
    });

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    int id;
  String name;
    EnumTeam(String name, int id) {
        this.name=name;
        this.id=id;



    }


    public static EnumTeam getTeamByInt(int id) {


        for (EnumTeam team : VALUES) {

                if (team.getId() == id) {

                    return  team;
                }
        }



        return  null;
    }






}
