package com.javisel.skyrift.common.capabilities.devicedata;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class DeviceDataStorage implements Capability.IStorage<IDeviceData> {


    @Nullable
    @Override
    public INBT writeNBT(Capability<IDeviceData> capability, IDeviceData instance, Direction side) {
        return instance.writeNBT();
    }

    @Override
    public void readNBT(Capability<IDeviceData> capability, IDeviceData instance, Direction side, INBT nbt) {

        instance.readNBT((CompoundNBT) nbt);
    }
}

