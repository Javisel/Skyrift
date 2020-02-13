package com.javisel.skyrift.common.damagesource;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;

public class SkyRiftDamageSource extends DamageSource {



    public  Entity source;
    public  float amount;
    public EnumDamageDevice damageDevice;
    public EnumDamageType damageType;
    public SkyRiftDamageSource( Entity source, float amount, EnumDamageDevice damageDevice, EnumDamageType damageType) {
        super("skyrift_damage_source");
        this.source = source;
        this.amount = amount;
        this.damageDevice = damageDevice;
        this.damageType = damageType;
    }
}
