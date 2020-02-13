package com.javisel.skyrift.client;

import com.javisel.skyrift.common.capabilities.*;
import com.javisel.skyrift.main.SkyRift;
import com.javisel.skyrift.main.SkyriftUtilities;
import com.mojang.blaze3d.platform.GlStateManager;
import cpw.mods.modlauncher.api.IEnvironment;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.opengl.GL;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import static com.javisel.skyrift.common.champion.resource.Resource.Resources.NONE;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverlayHandler extends GuiUtils {

    Minecraft instance = Minecraft.getInstance();
    private final ResourceLocation mobahud = new ResourceLocation(SkyRift.MODID, "textures/gui/overlay/mobahud.png");
    private final ResourceLocation hpbar = new ResourceLocation(SkyRift.MODID, "textures/gui/overlay/healthbar.png");


    @SubscribeEvent
    public void overlayOverride(RenderGameOverlayEvent event) {

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

            String gold = Float.toString(entityData.getGold());

        drawTexturedModalRect(width, height, 45, 162, 62, 7, -2);

    }
    public void renderStatBar(RenderGameOverlayEvent event) {

        MainWindow scaledresolution = instance.mainWindow;


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        int width = 0;
        int height = scaledresolution.getScaledHeight()-78;

        instance.textureManager.bindTexture(mobahud);
        drawTexturedModalRect(width,height,1,91,40,78,-2);

        int  stringx = width+26;
        int stringy = height+2;


        ArrayList<String> displaystat = new ArrayList<>();

        displaystat.add( Integer.toString((int)playerData.getChampion().getBasicAttackDamage(instance.player)));
        displaystat.add( getroundedDecimal(instance.player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).getValue()));



        for (IAttributeInstance attributeInstance : entityData.corestats()) {

            displaystat.add( Integer.toString((int)attributeInstance.getValue()));



        }
        displaystat.add( getroundedDecimal(instance.player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue()*100));


        for (int i =0; i<displaystat.size(); i++) {

            int x =  ((stringx) - instance.fontRenderer.getStringWidth(displaystat.get(i)) / 2);

            int y = stringy + (11*i);




            instance.fontRenderer.drawString(displaystat.get(i),x,y,Color.WHITE.getRGB());


        }




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

         int xpos = (scaledresolution.getScaledWidth()/2)-70;
         int ypos = (scaledresolution.getScaledHeight())-20;
         instance.textureManager.bindTexture(mobahud);
         double xpRatio = playerData.getLevel() != 20 ? (((entityData.getExperience().getValue() )  / SkyRift.getLevelupExp(playerData.getLevel()+1))) : 1;

         String xpString = Integer.toString(playerData.getLevel());
        int x =  ((xpos+10) - instance.fontRenderer.getStringWidth(xpString) / 2);


         drawTexturedModalRect(xpos, ypos, 2, 49, 20, 20, -2);
         drawTexturedModalRect(xpos + 1, (int) ((ypos + 19) - (18*xpRatio)), 3,  (70),18, (int) (18*xpRatio), -1);

         instance.fontRenderer.drawString(xpString, x, ypos+6, Color.WHITE.getRGB());






    }

    public void renderResourceBar(RenderGameOverlayEvent event) {

        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        if (playerData.getChampion().getResource()!=NONE) {

            MainWindow scaledresolution = instance.mainWindow;

            IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);


            int offset =  10;

            int height = scaledresolution.getScaledHeight()-offset;
            int width = (scaledresolution.getScaledWidth()/2)-50;
            String hp = Math.round(entityData.getResourceAmount()) + "/" + Math.round(entityData.getMaxResourceAmount().getValue());

            int hpwidth =(scaledresolution.getScaledWidth()/2);
            int hpheight = height+1;
            int x =  (hpwidth - instance.fontRenderer.getStringWidth(hp) / 2);
            instance.textureManager.bindTexture(playerData.getChampion().getResource().RESOURCE_TEXTURES);
            double healthRatio = (((entityData.getResourceAmount() )  /entityData.getMaxResourceAmount().getValue()));
            drawTexturedModalRect(width, height, 0, 0, 100, 10, 0);
            drawTexturedModalRect(width + 1, height + 1, 1, 11, (int) (98 * healthRatio), 8, -1);
            instance.fontRenderer.drawString(hp, x, hpheight, Color.WHITE.getRGB());

        }



    }

    public void renderHealthBar(RenderGameOverlayEvent event) {
        MainWindow scaledresolution = instance.mainWindow;


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);


        int offset = playerData.getChampion().getResource()==NONE ? 10 : 20;

        int height = scaledresolution.getScaledHeight()-offset;
        int width = (scaledresolution.getScaledWidth()/2)-50;
        String hp = Math.round(entityData.getHealth()) + "/" + Math.round(entityData.getMaxHealth().getValue());


        int hpwidth =(scaledresolution.getScaledWidth()/2);
        int hpheight = height+1;
        int x =  (hpwidth - instance.fontRenderer.getStringWidth(hp) / 2);

        instance.textureManager.bindTexture(hpbar);
        double healthRatio = (((entityData.getHealth() )  /entityData.getMaxHealth().getValue()));
        drawTexturedModalRect(width, height, 0, 0, 100, 10, -1);
        drawTexturedModalRect(width + 1, height + 1, 1, 11, (int) (98 * healthRatio), 8, 0);

        instance.fontRenderer.drawString(hp, x, hpheight, Color.WHITE.getRGB());

    }


    public void renderAbilities(RenderGameOverlayEvent event) {



        MainWindow scaledresolution = instance.mainWindow;


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY,null).orElseThrow(NullPointerException::new);

        int yoffset = playerData.getChampion().getResource()==NONE ? 10 : 20;

        int y = scaledresolution.getScaledHeight()-(yoffset+36);
        int x = (scaledresolution.getScaledWidth()/2);

        x =  (x - 86 / 2);
        instance.textureManager.bindTexture(mobahud);

        for (int i =0; i <4;i++) {

            //Ability Displays
            drawTexturedModalRect(x+(22*i), y, 2, 2, 20, 20, -3);

            int rankposx=x+(22*i) +3;
            int rankposy = y+22;

            for (int g = 0; g <5;g++) {

                //Ability Ranking
                drawTexturedModalRect(rankposx+(3*g),rankposy,5,23,2,2,-2);



            }
            //Keybinding Reminder
            drawTexturedModalRect(rankposx+3,rankposy+3,30,29,8,8,-1);
            KeyBinding binding = KeyBindings.abilitybindings[i];
            String text = "";
            if (binding.getKey().getKeyCode()==-1) {
                text="UKN";
            }
            else if  (binding.getKey().getKeyCode()==0) {

                text="LMB";

            }
            else if  (binding.getKey().getKeyCode()==1) {

                text="RMB";

            }
            else if  (binding.getKey().getKeyCode()==2) {

                text="MMB";

            } else {

                text=binding.getLocalizedName().toUpperCase();
            }

            int stringx =  ((rankposx+8) - instance.fontRenderer.getStringWidth(text) / 2);
            instance.fontRenderer.drawString(text, stringx, rankposy+4, Color.WHITE.getRGB());





        }



    }





    public void renderChampionData(RenderGameOverlayEvent event) {


    }





}
