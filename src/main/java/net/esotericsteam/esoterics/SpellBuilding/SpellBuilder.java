package net.esotericsteam.esoterics.SpellBuilding;

import net.esotericsteam.esoterics.entity.custom.CrystalStormSpellProjectile;
import net.esotericsteam.esoterics.entity.custom.SpellProjectile;

public class SpellBuilder {
    // These spells can have increased damage.
    int[] acceptedProjectilesIDsDamage = new int[]{ CrystalStormSpellProjectile.SPELL_ID };
    SpellModifiers DAMAGE = new SpellModifiers("damage", acceptedProjectilesIDsDamage);
    // TODO: These spells can have increase AOE.
    // TODO: These spells can have extra strength.
    // All Modifiers
    SpellModifiers[] allModifiers = new SpellModifiers[]{ DAMAGE };

    // Base Spells
    SpellProjectile CRYSTAL_STORM_SPELL_PROJECTILE;
    // All Spells
    SpellProjectile[] allProjectiles = new SpellProjectile[]{ CRYSTAL_STORM_SPELL_PROJECTILE };

    SpellProjectile chosenProjectile;

    public boolean addBase(SpellProjectile projectile){
        // may not work
        for(SpellProjectile spellProjectile : allProjectiles){
            if(projectile.SPELL_ID == spellProjectile.SPELL_ID){
                chosenProjectile = spellProjectile;
                return true;
            }
        }
        return false;
    }

}
