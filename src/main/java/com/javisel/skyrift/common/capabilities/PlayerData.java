package com.javisel.skyrift.common.capabilities;

import com.javisel.skyrift.common.champion.Champion;
import com.javisel.skyrift.common.champion.ChampionDatabase;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;

public class PlayerData implements IPlayerData {

    boolean isChampion = false;
    Champion champion;
    int level =0;

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
        this.champion=champion;
    }

    @Override
    public void setisChampion() {

        if  (isChampion) isChampion=false;
        else {
            isChampion=true;
        }
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int levelIn) {
        level=levelIn;
    }

    @Override
    public CompoundNBT writeNBT() {

        CompoundNBT compoundNBT = new CompoundNBT();

        compoundNBT.putBoolean("ischampion",isChampion);
        compoundNBT.putInt("level",level);
        if (champion!=null) {
            compoundNBT.putUniqueId("championid", champion.getId());

        }
        return compoundNBT;
    }

    @Override
    public void loadNBT(CompoundNBT nbt) {

        isChampion=nbt.getBoolean("ischampion");

        if (nbt.hasUniqueId("championid")) {
            System.out.println("there is an id!");

            champion = ChampionDatabase.championHashMap.get(nbt.getUniqueId("championid"));
        }
        level=nbt.getInt("level");

    }
}
