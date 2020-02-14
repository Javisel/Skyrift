package com.javisel.skyrift.common.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class EntityDataStorage implements Capability.IStorage<IEntityData> {


    @Nullable
    @Override
    public INBT writeNBT(Capability<IEntityData> capability, IEntityData instance, Direction side) {
        return instance.writeNBT();
    }

    @Override
    public void readNBT(Capability<IEntityData> capability, IEntityData instance, Direction side, INBT nbt) {

        instance.loadNBT((CompoundNBT) nbt);
    }
}

