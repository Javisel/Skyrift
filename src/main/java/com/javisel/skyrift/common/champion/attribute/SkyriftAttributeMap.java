package com.javisel.skyrift.common.champion.attribute;

import com.javisel.skyrift.main.SkyriftAttributes;
import net.minecraft.entity.ai.attributes.AttributeMap;

public class SkyriftAttributeMap extends AttributeMap {


    public SkyriftAttributeMap() {
        super();

        SkyriftAttributes.loadAttributes(this);


    }
}
