package com.javisel.skyrift.common.champion.ability;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.javisel.skyrift.common.champion.Champion;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AbilityConfig {

    public ForgeConfigSpec config;

    String champion;
    public String abilityname;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> cooldown;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> cost;
    public ForgeConfigSpec.ConfigValue<List<? extends Double>> charges;




    public AbilityConfig(String champion, String abilityname) {
        this.champion = champion;

        this.configureAbility();
        Path path = FMLPaths.CONFIGDIR.get().resolve("skyrift/champions/" + champion + "/");

        if (Files.notExists(path)) {

            new File(path.toString()).mkdirs();

        }
        this.abilityname=abilityname;
        loadConfig(config, FMLPaths.CONFIGDIR.get().resolve("skyrift/champions/" + champion + "/" + abilityname + "_stats.toml"));


    }



    public ForgeConfigSpec.ConfigValue<List<? extends Double>> getDoubleList(ForgeConfigSpec.Builder builder, String comment, String path, Double... def) {

        ArrayList<Double> defaults = new ArrayList<>(Arrays.asList(def));


        builder.comment(comment);


        return builder.defineList(path, defaults, (o) -> o instanceof Double);


    }

    public ForgeConfigSpec.ConfigValue<List<? extends Byte>> getByteList(ForgeConfigSpec.Builder builder, String comment, String path, Byte... def) {

        ArrayList<Byte> defaults = new ArrayList<>(Arrays.asList(def));


        builder.comment(comment);


        return builder.defineList(path, defaults, (o) -> o instanceof Byte);


    }
    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        CommentedFileConfig configData;


        configData = CommentedFileConfig.builder(path).sync().autosave().writingMode(WritingMode.REPLACE).build();


        configData.load();
        spec.setConfig(configData);
    }


    public void configureAbility() {

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        builder.comment(abilityname + " base statistics");
      cooldown = getDoubleList(builder, "cooldown", "cooldown", 0d,0d,0d,0d,0d,0d);
        cost = getDoubleList(builder, "cost", "cost", 0d,0d,0d,0d,0d,0d);
        charges = getDoubleList(builder, "charges", "charge", 0d,0d,0d,0d,0d,0d);


        config = builder.build();


    }


}
