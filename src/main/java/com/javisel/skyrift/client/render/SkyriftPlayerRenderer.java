package com.javisel.skyrift.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.PlayerRenderer;

public class SkyriftPlayerRenderer extends PlayerRenderer {
    public SkyriftPlayerRenderer(EntityRendererManager renderManager) {
        super(renderManager);
    }

    public SkyriftPlayerRenderer(EntityRendererManager renderManager, boolean useSmallArms) {
        super(renderManager, useSmallArms);
    }
}
