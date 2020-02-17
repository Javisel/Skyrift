package com.javisel.skyrift.common.capabilities.devicedata;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DeviceDataProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IDeviceData.class)
    public static Capability<IDeviceData> Device_DATA_CAPABILITY = null;

    private LazyOptional<IDeviceData> instance = LazyOptional.of(Device_DATA_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == Device_DATA_CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return (LazyOptional<T>) instance;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) Device_DATA_CAPABILITY.getStorage().writeNBT(Device_DATA_CAPABILITY, this.instance.orElseThrow(NullPointerException::new), null);

    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        Device_DATA_CAPABILITY.getStorage().readNBT(Device_DATA_CAPABILITY, this.instance.orElseThrow(NullPointerException::new), null, nbt);

    }
}

