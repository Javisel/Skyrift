package com.javisel.skyrift.common.champion.resource;

import com.javisel.skyrift.main.SkyRift;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Resource {




     String resourceName;
    int id;
    public  ResourceLocation RESOURCE_TEXTURES;
    public Resource(String nameIn, int idIn) {
        resourceName = nameIn;
        id=idIn;
        RESOURCE_TEXTURES = new ResourceLocation(SkyRift.MODID,"textures/gui/overlay/champion/" + resourceName.toLowerCase()+".png");


    }



    public static class Resources {


        public static Resource NONE = new Resource("none",0);

        public static Resource MANA = new Resource("mana",1);

        public static Resource ENERGY = new Resource("energy",2);
        public static Resource HEAT = new Resource("heat",3);


    }

}
