package net.esotericsteam.esoterics.mana;

import net.minecraft.nbt.CompoundTag;

public class PlayerMana {
    private int mana;
    private final int MIN_MANA = 0;
    private int maxMana = 100;

    public int getMana(){
        return mana;
    }

    public void addMana(int add){
        this.mana = Math.min(this.mana + add, this.maxMana);
    }

    public void subtractMana(int sub){
        this.mana = Math.max(this.mana - sub, MIN_MANA);
    }

    public void copyFrom(PlayerMana source){
        this.mana = source.mana;
    }

    public void saveNBTData(CompoundTag nbt){
        nbt.putInt("mana", this.mana);
        nbt.putInt("maxMana", maxMana);
    }

    public void loadNBTData(CompoundTag nbt){
        this.mana = nbt.getInt("mana");
        this.maxMana = nbt.getInt("maxMana");
    }
}
