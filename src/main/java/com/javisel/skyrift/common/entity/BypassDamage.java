package com.javisel.skyrift.common.entity;

import com.javisel.skyrift.common.damagesource.SkyRiftDamageSource;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class BypassDamage extends EntityDamageSource {

    SkyRiftDamageSource damageSource;

    public BypassDamage(Entity source, SkyRiftDamageSource damageSource) {
        super("skyriftentityslays", source);
        this.damageSource=damageSource;
    }
}
