package net.esotericsteam.esoterics.entity.custom;

import net.esotericsteam.esoterics.entity.ModEntityTypes;
import net.esotericsteam.esoterics.item.ModItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class CrystalStormSpellProjectile extends SpellProjectile {
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR =
            SynchedEntityData.defineId(CrystalStormSpellProjectile.class, EntityDataSerializers.INT);

    public CrystalStormSpellProjectile(EntityType<? extends SpellProjectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void shoot(double p_37266_, double p_37267_, double p_37268_, float p_37269_, float p_37270_) {
        super.shoot(p_37266_, p_37267_, p_37268_, p_37269_, p_37270_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_EFFECT_COLOR, -1);
    }

    // TODO: Edit this test function.
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        entityHitResult.getEntity().spawnAtLocation(ModItems.INCANTATION_BOWL.get());
    }
}
