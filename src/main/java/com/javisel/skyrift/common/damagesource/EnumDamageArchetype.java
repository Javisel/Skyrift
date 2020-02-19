package com.javisel.skyrift.common.damagesource;

public enum EnumDamageArchetype {

    ABSOLUTE(0, "absolute"),
    AREA(1, "area"),
    PERSISITENT(2, "persistent"),
    PROC(4, "proc"),
    PET(5, "pet"),
    BASIC_ATTACK(6, "basic_attack"),

            ;

    int id;
    String name;

    EnumDamageArchetype(int idIn, String nameIn) {

        id = idIn;
        name = nameIn;


    }
}
