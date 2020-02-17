package com.javisel.skyrift.common.champion;

import com.javisel.skyrift.common.capabilities.entitydata.EntityDataProvider;
import com.javisel.skyrift.common.capabilities.entitydata.IEntityData;
import com.javisel.skyrift.common.capabilities.entitydata.IPlayerData;
import com.javisel.skyrift.common.capabilities.entitydata.PlayerDataProvider;
import com.javisel.skyrift.common.champion.ability.AbstractAbility;
import com.javisel.skyrift.common.champion.resource.Resource;
import com.javisel.skyrift.common.damagesource.EnumDamageType;
import com.javisel.skyrift.common.damagesource.SkyRiftDamageSource;
import com.javisel.skyrift.main.SkyriftUtilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static com.javisel.skyrift.main.SkyRift.TICKCOUNT;

public abstract class Champion {

    private final String name;
    private final ChampionConfig basedata;
    private final Resource resource;
    private final UUID id;
    private final EnumDamageType basicAttackType;
    private ArrayList<AbstractAbility> kit = new ArrayList<>();

    public Champion(ChampionConfig config, UUID id, Resource resource, EnumDamageType basicAttackType, AbstractAbility... kit) {
        basedata = config;
        this.basicAttackType = basicAttackType;

        this.kit.addAll(Arrays.asList(kit));
        name = basedata.getName();
        this.id = id;
        this.resource = resource;

    }

    public UUID getId() {
        return id;
    }

    public float getBasicAttackDamage(PlayerEntity playerEntity) {


        return 0;
    }


    public String getName() {
        return name;
    }

    public ChampionConfig getBasedata() {
        return basedata;
    }

    public ArrayList<AbstractAbility> getKit() {
        return kit;
    }


    public Resource getResource() {
        return resource;
    }

    public void tick(PlayerEntity playerEntity) {


        IPlayerData playerData = playerEntity.getCapability(PlayerDataProvider.Player_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);
        IEntityData entityData = playerEntity.getCapability(EntityDataProvider.Entity_DATA_CAPABILITY, null).orElseThrow(NullPointerException::new);


        if (playerData.getChampionData().getInt(TICKCOUNT) < 20) {
            playerData.getChampionData().putInt(TICKCOUNT, playerData.getChampionData().getInt(TICKCOUNT) + 1);


        } else {
            playerData.getChampionData().putInt(TICKCOUNT, 0);
            SkyriftUtilities.heal(playerEntity, playerEntity, (float) entityData.getHealthRegen().getValue());
            SkyriftUtilities.addResource(playerEntity, playerEntity, (float) entityData.getResourceRegen().getValue());
            //TODO HEALING
        }

        for (ItemStack stack : playerData.getAbilities()) {

            if (stack.getItem() instanceof AbstractAbility) {

                ((AbstractAbility) stack.getItem()).tick(playerEntity, stack);

            }


        }


    }

    public void construct(PlayerEntity playerEntity) {

        if (playerEntity.world.isRemote) {
            return;
        }

        IPlayerData playerData = SkyriftUtilities.getPlayerData(playerEntity);


        for (int i = 0; i < getKit().size(); i++) {

            playerData.getAbilities().add(new ItemStack(getKit().get(i)));


        }

        for (int i = 0; i < playerData.getAbilities().size(); i++) {

            ((AbstractAbility) playerData.getAbilities().get(i).getItem()).setData(playerEntity, playerData.getAbilities().get(i));









        }


    }


    public void onAttacked(PlayerEntity victim, SkyRiftDamageSource source) {


    }

    public void onPreDamage(DamageSource source, PlayerEntity playerEntity) {


    }

    public void onPostDamage(DamageSource source, PlayerEntity playerEntity) {


    }

    public void onDeath(SkyRiftDamageSource source) {


    }


    public void levelUp(PlayerEntity playerEntity) {


    }

    @OnlyIn(Dist.CLIENT)
    public void renderOverlayData() {


    }


    public EnumDamageType getbasicAttackDamageType() {


        return basicAttackType;
    }
}
