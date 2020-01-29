package com.javisel.skyrift.common.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityDataProvider implements ICapabilitySerializable<CompoundNBT> {

    @CapabilityInject(IEntityData.class)
    public static Capability<IEntityData> Entity_DATA_CAPABILITY = null;

    private LazyOptional<IEntityData> instance = LazyOptional.of(Entity_DATA_CAPABILITY::getDefaultInstance);

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == Entity_DATA_CAPABILITY ? instance.cast() : LazyOptional.empty();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
        return (LazyOptional<T>) instance;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return (CompoundNBT) Entity_DATA_CAPABILITY.getStorage().writeNBT(Entity_DATA_CAPABILITY, this.instance.orElseThrow(NullPointerException::new), null);

    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        Entity_DATA_CAPABILITY.getStorage().readNBT(Entity_DATA_CAPABILITY, this.instance.orElseThrow(NullPointerException::new), null, nbt);

    }
}

