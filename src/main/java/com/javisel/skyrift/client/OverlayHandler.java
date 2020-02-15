package com.javisel.skyrift.client;

import com.javisel.skyrift.common.capabilities.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.IEntityData;
import com.javisel.skyrift.common.capabilities.IPlayerData;
import com.javisel.skyrift.common.capabilities.PlayerDataProvider;
import com.javisel.skyrift.main.SkyRift;
import com.javisel.skyrift.main.SkyriftUtilities;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.gui.GuiUtils;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

import java.awt.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.javisel.skyrift.common.champion.ability.AbstractAbility.*;
import static com.javisel.skyrift.common.champion.resource.Resource.Resources.NONE;
import static com.javisel.skyrift.main.SkyRift.*;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class OverlayHandler extends GuiUtils {

    private final ResourceLocation mobahud = new ResourceLocation(SkyRift.MODID, "textures/gui/overlay/mobahud.png");
    private final ResourceLocation hpbar = new ResourceLocation(SkyRift.MODID, "textures/gui/overlay/healthbar.png");
    Minecraft instance = Minecraft.getInstance();


    @SubscribeEvent
    public void overlayOverride(RenderGameOverlayEvent event) {

        if (instance.player.isCreative() || !SkyriftUtilities.isChampion(instance.player) || !instance.player.isAlive()) {
        } else {
            if (event.getType() == RenderGameOverlayEvent.ElementType.EXPERIENCE) {
                event.setCanceled(true);
                renderXPBar(event);

            } else if (event.getType() == RenderGameOverlayEvent.ElementType.FOOD) {
                event.setCanceled(true);
                renderResourceBar(event);

            } else if (event.getType() == RenderGameOverlayEvent.ElementType.HEALTH) {
                event.setCanceled(true);
                renderHealthBar(event);
                renderStatBar(event);


            } else if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
                event.setCanceled(true);
                renderAbilities(event);
                renderItemSet(event);


            } else if (event.getType() == RenderGameOverlayEvent.ElementType.AIR) {
                event.setCanceled(true);

            } else if (event.getType() == RenderGameOverlayEvent.ElementType.ARMOR) {
                event.setCanceled(true);

            } else if (event.getType() == RenderGameOverlayEvent.ElementType.JUMPBAR) {
                event.setCanceled(true);
            }

        }
        //instance.fontRenderer.drawString("SKYRIFT INDEV",0,0,Color.WHITE.getRGB());

    }

    public void renderItemSet(RenderGameOverlayEvent event) {

        MainWindow scaledresolution = instance.getMainWindow();


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);

        int width = 40;
        int height = scaledresolution.getScaledHeight() - 7;

        instance.textureManager.bindTexture(mobahud);
        drawTexturedModalRect(width, height, 45, 162, 62, 7, 50);

        for (int y = 1; y < 3; y++) {

            for (int x = 0; x < 3; x++) {
                drawTexturedModalRect(width + (17 * x), height - (17 * y), 45, 144, 18, 18, 50);

            }

        }

        String gold = Float.toString(entityData.getGold());



    }


    public void renderStatBar(RenderGameOverlayEvent event) {

        MainWindow scaledresolution = instance.getMainWindow();


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);

        int width = 0;
        int height = scaledresolution.getScaledHeight() - 78;

        instance.textureManager.bindTexture(mobahud);
        drawTexturedModalRect(width, height, 1, 91, 40, 78, 0);

        int stringx = width + 26;
        int stringy = height + 2;




        ArrayList<String> displaystat = new ArrayList<>();

        displaystat.add(Integer.toString((int) playerData.getChampion().getBasicAttackDamage(instance.player)));
        displaystat.add(getroundedDecimal(instance.player.getAttribute(SharedMonsterAttributes.ATTACK_SPEED).getValue()));


        for (IAttributeInstance attributeInstance : entityData.corestats()) {

            displaystat.add(Integer.toString((int) attributeInstance.getValue()));


        }
        displaystat.add(getroundedDecimal(instance.player.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getValue() * 100));



        for (int i = 0; i < displaystat.size(); i++) {

            int x = ((stringx) - instance.fontRenderer.getStringWidth(displaystat.get(i)) / 2);

            int y = stringy + (11 * i);


            instance.fontRenderer.drawString(displaystat.get(i), x, y, Color.WHITE.getRGB());


        }







    }


    public String getroundedDecimal(double decimal) {
        DecimalFormat df = new DecimalFormat("0.00");

        df.setRoundingMode(RoundingMode.UP);
        return df.format(decimal);


    }


    public void renderXPBar(RenderGameOverlayEvent event) {

        MainWindow scaledresolution = instance.getMainWindow();


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);

        int xpos = (scaledresolution.getScaledWidth() / 2) - 70;
        int ypos = (scaledresolution.getScaledHeight()) - 20;
        instance.textureManager.bindTexture(mobahud);
        double xpRatio = playerData.getLevel() != 20 ? (((entityData.getExperience().getValue()) / SkyRift.getLevelupExp(playerData.getLevel() + 1))) : 1;

        String xpString = Integer.toString(playerData.getLevel());
        int x = ((xpos + 10) - instance.fontRenderer.getStringWidth(xpString) / 2);


        drawTexturedModalRect(xpos, ypos, 2, 49, 20, 20, -2);
        drawTexturedModalRect(xpos + 1, (int) ((ypos + 19) - (18 * xpRatio)), 3, (70), 18, (int) (18 * xpRatio), -1);

        instance.fontRenderer.drawString(xpString, x, ypos + 6, Color.WHITE.getRGB());


    }

    public void renderResourceBar(RenderGameOverlayEvent event) {

        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);

        if (playerData.getChampion().getResource() != NONE) {

            MainWindow scaledresolution = instance.getMainWindow();

            IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);


            int offset = 10;

            int height = scaledresolution.getScaledHeight() - offset;
            int width = (scaledresolution.getScaledWidth() / 2) - 50;
            String hp = Math.round(entityData.getResourceAmount()) + "/" + Math.round(entityData.getMaxResourceAmount().getValue());

            int hpwidth = (scaledresolution.getScaledWidth() / 2);
            int hpheight = height + 1;
            int x = (hpwidth - instance.fontRenderer.getStringWidth(hp) / 2);
            instance.textureManager.bindTexture(playerData.getChampion().getResource().RESOURCE_TEXTURES);
            double healthRatio = (((entityData.getResourceAmount()) / entityData.getMaxResourceAmount().getValue()));
            drawTexturedModalRect(width, height, 0, 0, 100, 10, 0);
            drawTexturedModalRect(width + 1, height + 1, 1, 11, (int) (98 * healthRatio), 8, -1);
            instance.fontRenderer.drawString(hp, x, hpheight, Color.WHITE.getRGB());

        }


    }

    public void renderHealthBar(RenderGameOverlayEvent event) {
        MainWindow scaledresolution = instance.getMainWindow();


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);


        int offset = playerData.getChampion().getResource() == NONE ? 10 : 20;

        int height = scaledresolution.getScaledHeight() - offset;
        int width = (scaledresolution.getScaledWidth() / 2) - 50;
        String hp = Math.round(entityData.getHealth()) + "/" + Math.round(entityData.getMaxHealth().getValue());


        int hpwidth = (scaledresolution.getScaledWidth() / 2);
        int hpheight = height + 1;
        int x = (hpwidth - instance.fontRenderer.getStringWidth(hp) / 2);

        instance.textureManager.bindTexture(hpbar);
        double healthRatio = (((entityData.getHealth()) / entityData.getMaxHealth().getValue()));
        drawTexturedModalRect(width, height, 0, 0, 100, 10, -1);
        drawTexturedModalRect(width + 1, height + 1, 1, 11, (int) (98 * healthRatio), 8, 0);

        instance.fontRenderer.drawString(hp, x, hpheight, Color.WHITE.getRGB());

    }


    public void renderAbilities(RenderGameOverlayEvent event) {


        MainWindow scaledresolution = instance.getMainWindow();


        IEntityData entityData = instance.player.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);
        IPlayerData playerData = instance.player.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);

        int yoffset = playerData.getChampion().getResource() == NONE ? 10 : 20;

        int y = scaledresolution.getScaledHeight() - (yoffset + 36);
        int x = (scaledresolution.getScaledWidth() / 2);

        x = (x - 86 / 2);
        instance.textureManager.bindTexture(mobahud);

        for (int i = 0; i < 4; i++) {
            ItemStack ability = playerData.getAbilities().get(2 + i);

            //Ability Displays
            drawTexturedModalRect(x + (22 * i), y, 2 + (ability.getTag().getInt(MODE)), 2, 20, 20, -3);

            if (ability.getTag().getBoolean(CAN_RANK_UP)) {

                drawTexturedModalRect(x + (22 * i), y, 68, 2, 20, 20, -2);

            }


            RenderSystem.enableRescaleNormal();
            RenderSystem.enableBlend();
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderHelper.enableStandardItemLighting();

            if (playerData.getAbilities().size() > 2) {
                renderHotbarItem(x + 2 + (22 * i), y + 2, event.getPartialTicks(), instance.player, playerData.getAbilities().get(2 + i));
            }

            //Information
            float z = 51;

            //DISPLAYDATA 1
            if (ability.getTag().getCompound(DISPLAYDATA).getBoolean(DISPLAY_DATA_1_ACTIVE)) {
                float ratio = ability.getTag().getCompound(DISPLAYDATA).getFloat(DISPLAY_DATA_1) / ability.getTag().getCompound(DISPLAYDATA).getFloat(DISPLAY_DATA_1_MAX);

                int ypos = y + 2;


                drawTexturedModalRect(x + 2 + (22 * i), ypos + 15, 4, 46, (int) (16 * ratio), 2, 52);

            }

            if (ability.getTag().getCompound(DISPLAYDATA).getBoolean(DISPLAY_DATA_2_ACTIVE)) {
                float ratio = ability.getTag().getCompound(DISPLAYDATA).getFloat(DISPLAY_DATA_2) / ability.getTag().getCompound(DISPLAYDATA).getFloat(DISPLAY_DATA_2_MAX);

                int ypos = y + 2;


                drawTexturedModalRect(x + 2 + (22 * i), (int) ((ypos + 16) - (16 * ratio)), 4, 29, 16, (int) (16 * ratio), 51);

            }


            RenderHelper.disableStandardItemLighting();
            RenderSystem.disableRescaleNormal();
            RenderSystem.disableBlend();
            instance.textureManager.bindTexture(mobahud);

            int rankposx = x + (22 * i) + 3;
            int rankposy = y + 22;

            for (int g = 0; g < 5; g++) {


                drawTexturedModalRect(rankposx + (3 * g), rankposy, 5, playerData.getAbilities().get(2 + i).getTag().getInt(RANK) >= g - 1 && playerData.getAbilities().get(2 + i).getTag().getInt(RANK) != 0 ? 26 : 23, 2, 2, -2);


            }
            //Keybinding Reminder
            drawTexturedModalRect(rankposx + 3, rankposy + 3, 30, 29, 8, 8, -1);
            KeyBinding binding = KeyBindings.abilitybindings[i];
            String text = "";
            if (binding.getKey().getKeyCode() == -1) {
                text = "UKN";
            } else if (binding.getKey().getKeyCode() == 0) {

                text = "LMB";

            } else if (binding.getKey().getKeyCode() == 1) {

                text = "RMB";

            } else if (binding.getKey().getKeyCode() == 2) {

                text = "MMB";

            } else {

                text = binding.getLocalizedName().toUpperCase();
            }

            float stringx = ((rankposx + 11) - instance.fontRenderer.getStringWidth(text) / 2) *1.1f;
            float stringy = ((rankposy + 4) * 1.1f) + 3;
            RenderSystem.pushMatrix();
            RenderSystem.scalef(0.9f, 0.9f, 1f);
            instance.fontRenderer.drawString(text, stringx, stringy, Color.WHITE.getRGB());
            RenderSystem.popMatrix();
            instance.textureManager.bindTexture(mobahud);


        }


    }


    private void renderHotbarItem(int x, int y, float partialTicks, PlayerEntity player, ItemStack stack) {
        if (!stack.isEmpty()) {
            float f = (float)stack.getAnimationsToGo() - partialTicks;
            if (f > 0.0F) {
                RenderSystem.pushMatrix();
                float f1 = 1.0F + f / 5.0F;
                RenderSystem.translatef((float)(x + 8), (float)(y + 12), 0.0F);
                RenderSystem.scalef(1.0F / f1, (f1 + 1.0F) / 2.0F, 1.0F);
                RenderSystem.translatef((float)(-(x + 8)), (float)(-(y + 12)), 0.0F);
            }

            this.instance.getItemRenderer().renderItemAndEffectIntoGUI(player, stack, x, y);
            if (f > 0.0F) {
                RenderSystem.popMatrix();
            }

        }
    }

    public void renderChampionData(RenderGameOverlayEvent event) {


    }


}
