package net.esotericsteam.esoterics.entity.custom;

import net.esotericsteam.esoterics.entity.ModEntityTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;


public class CrystalStormSpellProjectile extends SpellProjectile {
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR =
            SynchedEntityData.defineId(CrystalStormSpellProjectile.class, EntityDataSerializers.INT);

    public CrystalStormSpellProjectile(EntityType<? extends CrystalStormSpellProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public CrystalStormSpellProjectile(Level level, LivingEntity livingEntity, double d0, double d1, double d2) {
        super(ModEntityTypes.CRYSTAL_STORM_SPELL_PROJECTILE.get(), livingEntity, d0, d1, d2, level);
    }

    // Don't know what this does.
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_EFFECT_COLOR, -1);
    }

    // TODO: Edit this test function.
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        // This if-statement is boilerplate, and NECESSARY!
        // It stops client-side events and spells colliding with each other.
        if(!level.isClientSide && !(entityHitResult.getEntity() instanceof Projectile)) {
            entityHitResult.getEntity().hurt(damageSources().magic(), 1);
        }
    }

    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
    }

    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
    }
}
