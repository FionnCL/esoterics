package net.esotericsteam.esoterics;

import net.esotericsteam.esoterics.entity.custom.CrystalStormSpellProjectile;
import net.esotericsteam.esoterics.entity.custom.SpellProjectile;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

public class Spell extends SpellProjectile{
    public Spell(EntityType<? extends SpellProjectile> entityType, Level level) {
        super(entityType, level);
    }

    public Spell(EntityType<? extends SpellProjectile> entityType, LivingEntity livingEntity, double d0, double d1, double d2, Level level) {
        super(entityType, livingEntity, d0, d1, d2, level);
    }

    public boolean spellEffect(EntityHitResult entityHitResult, SpellProjectile spellProjectile, SpellModifier spellModifier) {
        // Below could be done by spell builder.
        // SpellBuilder can hold saved spells, perhaps.
        if (spellProjectile instanceof CrystalStormSpellProjectile) {
            if(spellModifier instanceof SpellModifier)
                return entityHitResult.getEntity().hurt(damageSources().magic(), 10);
        }
        return false;
    }
}
