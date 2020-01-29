package com.javisel.skyrift.client;

import com.javisel.skyrift.common.capabilities.*;
import com.javisel.skyrift.common.champion.attribute.SkyriftAttribute;
import com.javisel.skyrift.main.SkyRift;
import com.javisel.skyrift.main.SkyriftUtilities;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.AttackIndicatorStatus;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ForgeIngameGui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;

import static net.minecraftforge.fml.client.config.GuiUtils.drawTexturedModalRect;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverlayHandler extends GuiUtils {

    Minecraft instance = Minecraft.getInstance();
    private final ResourceLocation mobahud = new ResourceLocation(SkyRift.MODID, "textures/gui/overlay/mobahud.png");
    private final ResourceLocation xpbar = new ResourceLocation(SkyRift.MODID, "textures/gui/overlay/xpbar.png");


    @SubscribeEvent
    public void healthbarOverride(RenderGameOverlayEvent event) {

            if (instance.player.isCreative() || !SkyriftUtilities.isChampion(instance.player) || !instance.player.isAlive()) {
                return;
            } else {
                if (event.getType()== RenderGameOverlayEvent.ElementType.EXPERIENCE) {
                    event.setCanceled(true);
                    renderXPBar(event);
                }

              else if (event.getType()== RenderGameOverlayEvent.ElementType.FOOD) {
                    event.setCanceled(true);
                    renderManaBar(event);
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



            }

    }



    public void renderXPBar(RenderGameOverlayEvent event) {

        MainWindow scaledresolution = instance.mainWindow;


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
         int width = 0;
         int height = 0;
         instance.textureManager.bindTexture(xpbar);
         double xpRatio = (((entityData.getExperience().getValue() )  / SkyRift.getLevelupExp(playerData.getLevel()+1)));
         drawTexturedModalRect(width, height, 0, 0, 100, 5, -2);
         drawTexturedModalRect(width + 1, height + 1, 1, 6, (int) (98 * xpRatio), 3, -1);

         String experience = entityData.getExperience().getValue()+ "/" + SkyRift.getLevelupExp(playerData.getLevel()+1);
         instance.fontRenderer.drawString(experience, 0, 0, Color.WHITE.getRGB());
         instance.fontRenderer.drawString("Level: " + playerData.getLevel(), 0, 20, Color.WHITE.getRGB());






    }

    public void renderManaBar(RenderGameOverlayEvent event) {

    }

    public void renderHealthBar(RenderGameOverlayEvent event) {


    }


    public void renderAbilities(RenderGameOverlayEvent event) {

    }

    public void renderItems(RenderGameOverlayEvent event) {


    }








}
