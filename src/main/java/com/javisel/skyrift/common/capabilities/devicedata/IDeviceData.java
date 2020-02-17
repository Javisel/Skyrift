package com.javisel.skyrift.common.capabilities.devicedata;

import net.minecraft.nbt.CompoundNBT;

public interface IDeviceData {

    void setCompoundNBT(CompoundNBT compoundNBT);

    void setCooldown(float cooldown);

    void setMaxcooldown(float maxcooldown);

    void setBuffduration(float buffduration);

    void setMaxBuffDuration(float maxBuffDuration);

    CompoundNBT deviceData();

    byte getRank();

    void setRank(byte rank);

    byte getCount();

    void setCount(byte count);

    short getCost();

    void setCost(short cost);

    float getCurrentCooldown();

    float getMaxCooldown();

    float buffDuration();

    float buffMaxDuration();

    CompoundNBT writeNBT();

    void readNBT(CompoundNBT nbt);

    boolean canUpgrade();

    void setCanUpgrade();


}
