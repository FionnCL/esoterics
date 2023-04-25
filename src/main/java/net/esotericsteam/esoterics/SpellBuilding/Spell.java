package net.esotericsteam.esoterics.SpellBuilding;

import net.esotericsteam.esoterics.entity.ModEntityTypes;
import net.esotericsteam.esoterics.entity.custom.SpellProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Spell extends SpellProjectile{
    public String userGivenName = "New Spell";
    SpellBuilder spellBuilder;

    public Spell(EntityType<? extends SpellProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public Spell(Level level, LivingEntity livingEntity, double d0, double d1, double d2) {
        // TODO:
        //  -Probably add a modifier parameter.
        //  -Probably add a spell base type parameter.
        super(ModEntityTypes.SPELL.get(), livingEntity, d0, d1, d2, level);
    }

    // TODO: STUFF
    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);
        spellBuilder.getEntityEffects();
    }

    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        spellBuilder.getBlockEffects();
    }

    @Override
    protected void onHit(HitResult hitResult) {
        super.onHit(hitResult);
    }

    // This should purely be shot out of the gauntlet. It is an entity.
    // Perhaps has an on hit method.
}
