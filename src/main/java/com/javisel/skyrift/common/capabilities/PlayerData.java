package com.javisel.skyrift.common.capabilities;

import com.javisel.skyrift.common.champion.Champion;
import com.javisel.skyrift.common.champion.ChampionDatabase;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.items.SkyRiftItem;
import com.javisel.skyrift.main.SkyRift;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.IParticleData;

import java.util.ArrayList;

public class PlayerData implements IPlayerData {

    boolean isChampion = false;
    Champion champion;
    CompoundNBT championData = new CompoundNBT();
    ArrayList<ItemStack> abilities = new ArrayList<>();
    ArrayList<ItemStack> items = new ArrayList<>();
    @Override
    public CompoundNBT getChampionData() {
        return championData;
    }

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

            champion = ChampionDatabase.championHashMap.get(nbt.getUniqueId("championid"));
        }
        level=nbt.getInt("level");

    }

    public void resetData() {
        isChampion=false;

        champion=null;
        championData=new CompoundNBT();
        level=0;



    }

    @Override
    public void tick(PlayerEntity playerEntity) {
        champion.tick(playerEntity);

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
