package net.esotericsteam.esoterics.entity.custom;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.network.NetworkHooks;

public abstract class SpellProjectile extends AbstractHurtingProjectile {
    public SpellProjectile(EntityType<? extends SpellProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public SpellProjectile(EntityType<? extends SpellProjectile> entityType, double d0, double d1, double d2, double d3, double d4, double d5, Level level) {
        super(entityType, d0, d1, d2, d3, d4, d5, level);
    }

    public SpellProjectile(EntityType<? extends SpellProjectile> entityType, LivingEntity livingEntity, double d0, double d1, double d2, Level level) {
        super(entityType, livingEntity, d0, d1, d2, level);
    }

    @Override
    protected boolean shouldBurn() {
        return false;
    }

    @Override
    protected ParticleOptions getTrailParticle() {
        return ParticleTypes.SMOKE;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void shoot(double x, double y, double z, float scale, float triangle) {
        super.shoot(x, y, z, scale, triangle);
    }

    @Override
    protected void defineSynchedData() {}

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
    }
}
