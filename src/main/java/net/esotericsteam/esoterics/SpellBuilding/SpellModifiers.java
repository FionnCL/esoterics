package net.esotericsteam.esoterics.SpellBuilding;

import net.esotericsteam.esoterics.entity.custom.SpellProjectile;

public class SpellModifiers {
    String NAME;
    int[] acceptedProjectilesIDs;

    public SpellModifiers(String modifier, int[] acceptedProjectilesIDs){
        this.NAME = modifier;
        this.acceptedProjectilesIDs = acceptedProjectilesIDs;
    }

    public boolean canUseModifier(){ return true; }
}
