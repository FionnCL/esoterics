package net.esotericsteam.esoterics.SpellBuilding;

public class SpellModifier {
    String name;
    int modifierCost;
    int manaCost;

    public SpellModifier(String modifier, int modifierCost, int manaCost){
        this.name = modifier;
        this.modifierCost = modifierCost;
        this.manaCost = manaCost;
    }

    public boolean canUseModifier(){
        return false;
    }
}
