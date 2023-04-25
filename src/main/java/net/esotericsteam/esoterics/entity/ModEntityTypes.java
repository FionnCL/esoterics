package net.esotericsteam.esoterics.entity;

import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.SpellBuilding.Spell;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    // Deferred Registry
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Esoterics.MOD_ID);

    // Spells
    public static final RegistryObject<EntityType<Spell>> SPELL =
            ENTITY_TYPES.register(
                    "spell",
                    () -> EntityType.Builder
                            .of(
                                    (EntityType.EntityFactory<Spell>)
                                            Spell::new,
                                    MobCategory.MISC
                            )
                            .sized(0.5F, 0.5F)
                            .build(new ResourceLocation(Esoterics.MOD_ID, "spell").toString())
            );

    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
