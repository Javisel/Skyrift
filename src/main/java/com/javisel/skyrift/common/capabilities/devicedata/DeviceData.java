package com.javisel.skyrift.common.capabilities.devicedata;

import net.minecraft.nbt.CompoundNBT;

public class DeviceData implements IDeviceData {


    CompoundNBT compoundNBT;
    boolean canUpgrade = false;
    byte rank;
    byte count;
    short cost;
    float cooldown;
    float maxcooldown;
    float buffduration;
    float maxBuffDuration;

    @Override
    public void setCompoundNBT(CompoundNBT compoundNBT) {
        this.compoundNBT = compoundNBT;
    }

    @Override
    public void setCooldown(float cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public void setMaxcooldown(float maxcooldown) {
        this.maxcooldown = maxcooldown;
    }

    @Override
    public void setBuffduration(float buffduration) {
        this.buffduration = buffduration;
    }

    @Override
    public void setMaxBuffDuration(float maxBuffDuration) {
        this.maxBuffDuration = maxBuffDuration;
    }

    @Override
    public CompoundNBT deviceData() {
        return compoundNBT;
    }

    @Override
    public byte getRank() {
        return rank;
    }

    @Override
    public void setRank(byte rank) {
        this.rank = rank;
    }

    @Override
    public byte getCount() {
        return count;
    }

    @Override
    public void setCount(byte count) {
        this.count = count;
    }

    @Override
    public short getCost() {
        return cost;
    }

    @Override
    public void setCost(short cost) {
        this.cost = cost;
    }

    @Override
    public float getCurrentCooldown() {
        return cooldown;
    }

    @Override
    public float getMaxCooldown() {
        return maxcooldown;
    }

    @Override
    public float buffDuration() {
        return buffduration;
    }

    @Override
    public float buffMaxDuration() {
        return maxBuffDuration;
    }

    @Override
    public boolean canUpgrade() {
        return canUpgrade;
    }

    @Override
    public void setCanUpgrade() {

        canUpgrade = !canUpgrade;
    }

    @Override
    public CompoundNBT writeNBT() {

        CompoundNBT compoundNBT = new CompoundNBT();

        if (this.compoundNBT!=null) {
            compoundNBT.put("devicedata", this.compoundNBT);
        }
        compoundNBT.putByte("rank", rank);
        compoundNBT.putByte("count", count);
        compoundNBT.putShort("cost", cost);
        compoundNBT.putFloat("cooldown", cooldown);
        compoundNBT.putFloat("maxCooldown", maxcooldown);
        compoundNBT.putFloat("buffduration", buffduration);
        compoundNBT.putFloat("maxbuffduration", maxBuffDuration);
        compoundNBT.putBoolean("canupgrade", canUpgrade);

        return compoundNBT;
    }

    @Override
    public void readNBT(CompoundNBT nbt) {

        if (nbt.hasUniqueId("devicedata")) {
            compoundNBT = nbt.getCompound("devicedata");
        }
        rank = nbt.getByte("rank");
        count = nbt.getByte("count");
        cooldown = nbt.getFloat("cooldown");
        maxcooldown = nbt.getFloat("maxCooldown");
        buffduration = nbt.getFloat("buffduration");
        maxBuffDuration = nbt.getFloat("maxbuffduration");
        canUpgrade = nbt.getBoolean("canupgrade");


    }
}
