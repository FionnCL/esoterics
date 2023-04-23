package net.esotericsteam.esoterics.item;

import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.item.custom.Gauntlet;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // Basic Items
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Esoterics.MOD_ID);

    public static final RegistryObject<Item> INCANTATION_BOWL = ITEMS.register("incantation_bowl",
            () -> new Item(new Item.Properties().stacksTo(1)));

    // Advanced Items
    public static final RegistryObject<Item> GAUNTLET = ITEMS.register("gauntlet",
            () -> new Gauntlet(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}