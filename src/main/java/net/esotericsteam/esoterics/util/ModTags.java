package net.esotericsteam.esoterics.util;

import net.esotericsteam.esoterics.Esoterics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Blocks {

    }

    public static class Items {
        public static final TagKey<Item> SPELLS = ItemTags.create(new ResourceLocation(Esoterics.MOD_ID, "items/spell_projectiles"));
    }
}
