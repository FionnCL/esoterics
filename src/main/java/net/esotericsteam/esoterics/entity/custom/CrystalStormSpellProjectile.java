package net.esotericsteam.esoterics.entity.custom;

import net.esotericsteam.esoterics.entity.ModEntityTypes;
import net.esotericsteam.esoterics.item.ModItems;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class CrystalStormSpellProjectile extends SpellProjectile {
    private static final EntityDataAccessor<Integer> ID_EFFECT_COLOR =
            SynchedEntityData.defineId(CrystalStormSpellProjectile.class, EntityDataSerializers.INT);

    public CrystalStormSpellProjectile(EntityType<? extends CrystalStormSpellProjectile> p_37364_, Level p_37365_) {
        super(p_37364_, p_37365_);
    }

    public CrystalStormSpellProjectile(Level p_37375_, LivingEntity p_37376_, double p_37377_, double p_37378_, double p_37379_) {
        super(ModEntityTypes.CRYSTAL_STORM_SPELL_PROJECTILE.get(), p_37376_, p_37377_, p_37378_, p_37379_, p_37375_);
    }

    public CrystalStormSpellProjectile(Level p_37367_, double p_37368_, double p_37369_, double p_37370_, double p_37371_, double p_37372_, double p_37373_) {
        super(ModEntityTypes.CRYSTAL_STORM_SPELL_PROJECTILE.get(), p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, p_37367_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_EFFECT_COLOR, -1);
    }

    // TODO: Edit this test function.
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        level.playSound((Player)null,
                entityHitResult.getLocation().x,
                entityHitResult.getLocation().y,
                entityHitResult.getLocation().z,
                SoundEvents.BLAZE_SHOOT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F);
    }
}
