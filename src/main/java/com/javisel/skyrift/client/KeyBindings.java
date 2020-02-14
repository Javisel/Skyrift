package com.javisel.skyrift.client;

import com.javisel.skyrift.main.SkyRift;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class KeyBindings {


    public static final KeyBinding ABILITY1 = new KeyBinding(SkyRift.MODID + ".key.ability1", GLFW.GLFW_KEY_Q, "Skyrift");
    public static final KeyBinding ABILITY2 = new KeyBinding(SkyRift.MODID + ".key.ability2", GLFW.GLFW_KEY_E, "Skyrift");
    public static final KeyBinding ABILITY3 = new KeyBinding(SkyRift.MODID + ".key.ability3", GLFW.GLFW_KEY_R, "Skyrift");
    public static final KeyBinding ABILITY4 = new KeyBinding(SkyRift.MODID + ".key.ability4", GLFW.GLFW_KEY_V, "Skyrift");
    public static final KeyBinding ITEM1 = new KeyBinding(SkyRift.MODID + ".key.item1", GLFW.GLFW_KEY_1, "Skyrift");
    public static final KeyBinding ITEM2 = new KeyBinding(SkyRift.MODID + ".key.item2", GLFW.GLFW_KEY_2, "Skyrift");
    public static final KeyBinding ITEM3 = new KeyBinding(SkyRift.MODID + ".key.item3", GLFW.GLFW_KEY_3, "Skyrift");
    public static final KeyBinding ITEM4 = new KeyBinding(SkyRift.MODID + ".key.item4", GLFW.GLFW_KEY_4, "Skyrift");
    public static final KeyBinding ITEM5 = new KeyBinding(SkyRift.MODID + ".key.item5", GLFW.GLFW_KEY_5, "Skyrift");
    public static final KeyBinding ITEM6 = new KeyBinding(SkyRift.MODID + ".key.item6", GLFW.GLFW_KEY_6, "Skyrift");

    public static final KeyBinding[] abilitybindings = new KeyBinding[]{ABILITY1, ABILITY2, ABILITY3, ABILITY4};

    public static void registerKeys() {
        ClientRegistry.registerKeyBinding(ABILITY1);
        ClientRegistry.registerKeyBinding(ABILITY2);
        ClientRegistry.registerKeyBinding(ABILITY3);
        ClientRegistry.registerKeyBinding(ABILITY4);
        ClientRegistry.registerKeyBinding(ITEM1);
        ClientRegistry.registerKeyBinding(ITEM2);
        ClientRegistry.registerKeyBinding(ITEM3);
        ClientRegistry.registerKeyBinding(ITEM4);
        ClientRegistry.registerKeyBinding(ITEM5);
        ClientRegistry.registerKeyBinding(ITEM6);
    }


}
