package com.javisel.skyrift.common.damagesource;

public enum  EnumDamageDevice  {
    NONE(0,"none"),
    BASIC_ATTACK(1,"basic_attack"),
    ABILITY(2,"ability"),
    ITEM(3,"item");



    int id;
    String name;

    EnumDamageDevice(int idIn, String nameIn) {

        id=idIn;
        name=nameIn;


    }
}
