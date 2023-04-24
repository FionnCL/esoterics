package net.esotericsteam.esoterics;

import net.esotericsteam.esoterics.entity.custom.SpellProjectile;

public class SpellModifier {
    SpellModifier(SpellProjectile spellProjectile){

    }

    // Intended use:
    //  -Allow a user to choose modifications to add to their spell to be applied in SpellProjectile.
    //  -This will be under the method
    //  -This class will simply contain the different types of modifications.
    //  -And checks for if a modification can be applied to a certain spell.
    public boolean canUseModifier(){
        return true;
    }

    public String getSpellModifier(){

    }
}
