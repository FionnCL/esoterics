package net.esotericsteam.esoterics.entity;

import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.entity.custom.CrystalStormSpellProjectile;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    // Deferred Registry
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Esoterics.MOD_ID);

    // Spells
    public static final RegistryObject<EntityType<CrystalStormSpellProjectile>> CRYSTAL_STORM_SPELL_PROJECTILE =
            ENTITY_TYPES.register(
                    "crystal_storm_spell_projectile", () -> EntityType.Builder.createNothing(
                            CrystalStormSpellProjectile::new,
                            new ResourceLocation(Esoterics.MOD_ID, "crystal_storm_spell_projectile")
                    )
            );

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
