package com.javisel.skyrift.common.champion.resource;

import com.javisel.skyrift.main.SkyRift;
import net.minecraft.util.ResourceLocation;

public class Resource {


    public final boolean spawnFull;
    public ResourceLocation RESOURCE_TEXTURES;
    String resourceName;
    int id;

    public Resource(String nameIn, int idIn, boolean spawnFull) {
        resourceName = nameIn;
        id = idIn;
        this.spawnFull = spawnFull;
        RESOURCE_TEXTURES = new ResourceLocation(SkyRift.MODID, "textures/gui/overlay/champion/" + resourceName.toLowerCase() + ".png");


    }


    public static class Resources {


        public static Resource NONE = new Resource("none", 0, false);

        public static Resource MANA = new Resource("mana", 1, true);

        public static Resource ENERGY = new Resource("energy", 2, true);
        public static Resource HEAT = new Resource("heat", 3, false);


    }

}
