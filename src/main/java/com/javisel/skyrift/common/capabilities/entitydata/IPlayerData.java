package com.javisel.skyrift.common.capabilities.entitydata;

import com.javisel.skyrift.common.champion.Champion;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.ArrayList;

public interface IPlayerData {

    Champion getChampion();

    boolean isChampion();

    void setChampion(Champion champion);

    void setisChampion();

    int getLevel();

    void setLevel(int levelIn);

    CompoundNBT writeNBT();

    CompoundNBT getChampionData();


    void loadNBT(CompoundNBT nbt);

    void resetData();

    void tick(PlayerEntity player);

    ArrayList<ItemStack> getAbilities();

    ArrayList<ItemStack> getItems();


}
