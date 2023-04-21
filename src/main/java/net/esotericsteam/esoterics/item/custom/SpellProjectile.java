package net.esotericsteam.esoterics.item.custom;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class SpellProjectile extends Projectile {
    protected SpellProjectile(EntityType<? extends Projectile> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void shoot(double p_37266_, double p_37267_, double p_37268_, float p_37269_, float p_37270_) {
        super.shoot(p_37266_, p_37267_, p_37268_, p_37269_, p_37270_);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {}
}
