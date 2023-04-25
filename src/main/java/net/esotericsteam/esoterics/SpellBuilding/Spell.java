package net.esotericsteam.esoterics.SpellBuilding;

import net.esotericsteam.esoterics.entity.custom.CrystalStormSpellProjectile;
import net.esotericsteam.esoterics.entity.custom.SpellProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Spell extends SpellProjectile{
    public Spell(EntityType<? extends SpellProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public Spell(EntityType<? extends SpellProjectile> entityType, LivingEntity livingEntity, double d0, double d1, double d2, Level level) {
        super(entityType, livingEntity, d0, d1, d2, level);
    }

    // This should purely be shot out of the gauntlet. It is an entity.
    // Perhaps has an on hit method.
}
