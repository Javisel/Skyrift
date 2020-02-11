package com.javisel.skyrift.main;

import com.javisel.skyrift.common.registration.ItemRegistration;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class SkyriftItemGroup extends ItemGroup {
    public SkyriftItemGroup() {
        super(SkyRift.MODID+"_itemgroup");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ItemRegistration.MAGIC_WAND);
    }
}
