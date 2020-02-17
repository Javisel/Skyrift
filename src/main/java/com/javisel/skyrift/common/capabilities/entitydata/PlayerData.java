package com.javisel.skyrift.common.capabilities.entitydata;

import com.javisel.skyrift.common.champion.Champion;
import com.javisel.skyrift.common.champion.ChampionDatabase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;

public class PlayerData implements IPlayerData {

    boolean isChampion = false;
    Champion champion;
    CompoundNBT championData = new CompoundNBT();
    ArrayList<ItemStack> abilities = new ArrayList<>();
    ArrayList<ItemStack> items = new ArrayList<>();
    int level = 0;


    @Override
    public CompoundNBT getChampionData() {
        return championData;
    }

    @Override
    public Champion getChampion() {
        return champion;
    }

    @Override
    public boolean isChampion() {
        return isChampion;
    }

    @Override
    public void setChampion(Champion champion) {
        this.champion = champion;
    }

    @Override
    public void setisChampion() {

        isChampion = !isChampion;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int levelIn) {
        level = levelIn;
    }

    @Override
    public CompoundNBT writeNBT() {

        CompoundNBT compoundNBT = new CompoundNBT();

        compoundNBT.putBoolean("ischampion", isChampion);
        compoundNBT.putInt("level", level);


        if (champion != null) {
            compoundNBT.putUniqueId("championid", champion.getId());
            CompoundNBT abilities = new CompoundNBT();

            for (int i = 0; i < this.abilities.size(); i++) {

                abilities.put("kit_part_" + i, this.abilities.get(i).write(new CompoundNBT()));


            }
            compoundNBT.put("kit", abilities);

        }
        return compoundNBT;
    }

    @Override
    public void loadNBT(CompoundNBT nbt) {

        isChampion = nbt.getBoolean("ischampion");

        if (nbt.hasUniqueId("championid")) {

            champion = ChampionDatabase.championHashMap.get(nbt.getUniqueId("championid"));
        }
        level = nbt.getInt("level");

        CompoundNBT kit = nbt.getCompound("kit");
        boolean cancont = true;
        int i = 0;
        while (cancont) {

            if (kit.contains("kit_part_" + i)) {

                abilities.add(ItemStack.read(kit.getCompound("kit_part_" + i)));
                i++;


            } else {
                cancont = false;
            }


        }


    }

    public void resetData() {
        isChampion = false;
        champion = null;
        championData = new CompoundNBT();
        abilities.clear();
        level = 0;


    }

    @Override
    public void tick(PlayerEntity playerEntity) {
        if (champion != null) {
            champion.tick(playerEntity);
        }
    }

    @Override
    public ArrayList<ItemStack> getAbilities() {
        return abilities;
    }

    @Override
    public ArrayList<ItemStack> getItems() {
        return items;
    }


}
