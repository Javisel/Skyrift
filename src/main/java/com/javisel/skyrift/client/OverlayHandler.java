package com.javisel.skyrift.client;

import com.javisel.skyrift.common.capabilities.*;
import com.javisel.skyrift.main.SkyRift;
import com.javisel.skyrift.main.SkyriftUtilities;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static com.javisel.skyrift.common.champion.resource.Resource.Resources.NONE;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverlayHandler extends GuiUtils {

    Minecraft instance = Minecraft.getInstance();
    private final ResourceLocation mobahud = new ResourceLocation(SkyRift.MODID, "textures/gui/overlay/mobahud.png");
    private final ResourceLocation hpbar = new ResourceLocation(SkyRift.MODID, "textures/gui/overlay/healthbar.png");


    @SubscribeEvent
    public void healthbarOverride(RenderGameOverlayEvent event) {

            if (instance.player.isCreative() || !SkyriftUtilities.isChampion(instance.player) || !instance.player.isAlive()) {
            } else {
                if (event.getType()== RenderGameOverlayEvent.ElementType.EXPERIENCE) {
                    event.setCanceled(true);
                    renderXPBar(event);
                }

              else if (event.getType()== RenderGameOverlayEvent.ElementType.FOOD) {
                    event.setCanceled(true);
                    renderResourceBar(event);

                }
              else if (event.getType()== RenderGameOverlayEvent.ElementType.HEALTH) {
                  event.setCanceled(true);
                  renderHealthBar(event);


                }
              else if (event.getType()== RenderGameOverlayEvent.ElementType.HOTBAR) {
                  event.setCanceled(true);
                  renderAbilities(event);

                }

              else if (event.getType()== RenderGameOverlayEvent.ElementType.AIR) {
                  event.setCanceled(true);
                }
              else if (event.getType()==RenderGameOverlayEvent.ElementType.ARMOR) {
                  event.setCanceled(true);

                }
                else if (event.getType()==RenderGameOverlayEvent.ElementType.JUMPBAR) {
                    event.setCanceled(true);
                }
                renderStatBar(event);
                renderItemSet(event);

            }

    }

    public void renderItemSet(RenderGameOverlayEvent event) {

        MainWindow scaledresolution = instance.mainWindow;


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        int width = 40;
        int height = scaledresolution.getScaledHeight()-7;

        instance.textureManager.bindTexture(mobahud);

            for (int y = 1; y <3; y++) {

            for (int x= 0; x <3;x++) {
                drawTexturedModalRect(width+(17*x), height-(17*y), 45, 144, 18, 18, -2);

            }

        }
        drawTexturedModalRect(width, height, 45, 162, 62, 7, -2);

    }
    public void renderStatBar(RenderGameOverlayEvent event) {

        MainWindow scaledresolution = instance.mainWindow;


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        int width = 0;
        int height = scaledresolution.getScaledHeight()-80;

        instance.textureManager.bindTexture(mobahud);
        drawTexturedModalRect(width,height,1,89,40,80,-2);

        GlStateManager.pushMatrix();
        int deductheight = (int) (height)+120;

        GlStateManager.scalef(.8f,.8f,.8f);
        int stringwidth = 16;
        deductheight*=.8;
        instance.fontRenderer.drawString(getroundedDecimal(playerData.getChampion().getBasicAttackDamage(instance.player)), stringwidth, deductheight, Color.WHITE.getRGB());
        deductheight+=16*.8;

        instance.fontRenderer.drawString( getroundedDecimal(instance.player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).getValue()), stringwidth, deductheight, Color.WHITE.getRGB());

        for (IAttributeInstance attributeInstance : entityData.corestats()) {
            deductheight+=16*.8;

            instance.fontRenderer.drawString( getroundedDecimal(attributeInstance.getValue()), stringwidth, deductheight, Color.WHITE.getRGB());


        }

        deductheight+=16*.8;

       instance.fontRenderer.drawString( getroundedDecimal(instance.player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()), stringwidth, deductheight, Color.WHITE.getRGB());


        GlStateManager.popMatrix();


    }


public String getroundedDecimal(double decimal) {
      DecimalFormat df = new DecimalFormat("0.00");

      df.setRoundingMode(RoundingMode.UP);
        return  df.format(decimal);



}

    public void renderXPBar(RenderGameOverlayEvent event) {

        MainWindow scaledresolution = instance.mainWindow;


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

         int width = (scaledresolution.getScaledWidth()/2)-60;
         int height = (scaledresolution.getScaledHeight())-20;
         instance.textureManager.bindTexture(mobahud);
         double xpRatio = playerData.getLevel() != 20 ? (((entityData.getExperience().getValue() )  / SkyRift.getLevelupExp(playerData.getLevel()+1))) : 1;

         drawTexturedModalRect(width, height, 2, 49, 20, 20, -2);
         drawTexturedModalRect(width + 1, (int) ((height + 20) - (18*xpRatio)), 3,  (70),18, (int) (18*xpRatio), -1);

         instance.fontRenderer.drawString(Integer.toString(playerData.getLevel()), width+6, height+6, Color.WHITE.getRGB());






    }

    public void renderResourceBar(RenderGameOverlayEvent event) {

        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        if (playerData.getChampion().getResource()!=NONE) {

            MainWindow scaledresolution = instance.mainWindow;

            IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);


            int offset =  10;

            int height = scaledresolution.getScaledHeight()-offset;
            int width = (scaledresolution.getScaledWidth()/2)-40;
            String hp = Math.round(entityData.getResourceAmount()) + "/" + Math.round(entityData.getMaxResourceAmount().getValue());

            int hpwidth =(scaledresolution.getScaledWidth()/2)-10;
            int hpheight = height+1;
            instance.textureManager.bindTexture(playerData.getChampion().getResource().RESOURCE_TEXTURES);
            double healthRatio = (((entityData.getResourceAmount() )  /entityData.getMaxResourceAmount().getValue()));
            drawTexturedModalRect(width, height, 0, 0, 100, 10, 0);
            drawTexturedModalRect(width + 1, height + 1, 1, 11, (int) (98 * healthRatio), 8, -1);
            instance.fontRenderer.drawString(hp, hpwidth, hpheight, Color.WHITE.getRGB());

        }



    }

    public void renderHealthBar(RenderGameOverlayEvent event) {
        MainWindow scaledresolution = instance.mainWindow;


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);


        int offset = playerData.getChampion().getResource()==NONE ? 10 : 20;

        int height = scaledresolution.getScaledHeight()-offset;
        int width = (scaledresolution.getScaledWidth()/2)-40;
        String hp = Math.round(entityData.getHealth()) + "/" + Math.round(entityData.getMaxHealth().getValue());

        int hpwidth =(scaledresolution.getScaledWidth()/2)-10;
        int hpheight = height+1;
        instance.textureManager.bindTexture(hpbar);
        double healthRatio = (((entityData.getHealth() )  /entityData.getMaxHealth().getValue()));
        drawTexturedModalRect(width, height, 0, 0, 100, 10, -1);
        drawTexturedModalRect(width + 1, height + 1, 1, 11, (int) (98 * healthRatio), 8, 0);

        instance.fontRenderer.drawString(hp, hpwidth, hpheight, Color.WHITE.getRGB());


    }


    public void renderAbilities(RenderGameOverlayEvent event) {

    }





    public void renderChampionData(RenderGameOverlayEvent event) {


    }





}
