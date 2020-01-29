package com.javisel.skyrift.common.capabilities;

import com.javisel.skyrift.common.champion.Champion;
import net.minecraft.nbt.CompoundNBT;

public interface IPlayerData {

    Champion getChampion();
    boolean isChampion();
    void setChampion(Champion champion);
    void setisChampion();
    int getLevel();
    void setLevel(int levelIn);
    CompoundNBT writeNBT();
    void loadNBT(CompoundNBT nbt);





}
