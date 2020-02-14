package com.javisel.skyrift.common.damagesource;

public enum EnumDamageType {

    TRUE(0, "true"),
    MAGIC(1, "magic"),
    PHYSICAL(2, "physical");

    int id;
    String name;

    EnumDamageType(int idIn, String nameIn) {

        id = idIn;
        name = nameIn;


    }
}
