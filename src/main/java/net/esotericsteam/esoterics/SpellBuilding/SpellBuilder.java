package net.esotericsteam.esoterics.SpellBuilding;

public class SpellBuilder {
    String BASE_AMETHYST = "base_amethyst";
    String BASE_AIR = "base_air";
    String BASE_FIRE = "base_fire";
    String[] base = new String[]{ BASE_AMETHYST, BASE_AIR, BASE_FIRE };

    String MODIFIER_DAMAGE = "modifier_amethyst";
    String[] modifiers = new String[]{ MODIFIER_DAMAGE };


    public Spell saveSpell(Spell spell){
        return spell;
    }

    public void getEntityEffects(){
        // do something to an entity.
    }

    public void getBlockEffects(){
        // do something to a block.
    }
}
