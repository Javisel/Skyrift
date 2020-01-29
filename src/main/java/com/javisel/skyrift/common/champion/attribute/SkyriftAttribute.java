package com.javisel.skyrift.common.champion.attribute;

import net.minecraft.entity.ai.attributes.AttributeModifier;

import java.util.HashMap;
import java.util.UUID;

public class SkyriftAttribute {


    HashMap<UUID, AttributeModifier> modifierHashMap;


    UUID id;
    String name;
    float baseValue;
    float minValue;
    float maxValue;

    public SkyriftAttribute(UUID id, String name, float baseValue, float minValue, float maxValue) {
        this.id = id;
        this.name = name;
        this.baseValue = baseValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }












}
