package com.javisel.skyrift.common.champion.resource;

import com.javisel.skyrift.main.SkyRift;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class Resource {




     String resourceName;
    int id;
    public static ResourceLocation RESOURCE_TEXTURES;
    public Resource(String nameIn, int idIn) {
        resourceName = nameIn;
        id=idIn;
        RESOURCE_TEXTURES = new ResourceLocation(SkyRift.MODID,"textures/overlay/champion/" + resourceName.toLowerCase()+".png");


    }





}
