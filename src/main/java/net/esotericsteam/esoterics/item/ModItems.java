package net.esotericsteam.esoterics.item;

import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.block.ModBlocks;
import net.esotericsteam.esoterics.item.custom.Gauntlet;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
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


    // Seeds and their Yields
    public static final RegistryObject<Item> FLESH_SEEDS = ITEMS.register("flesh_seeds",
            () -> new ItemNameBlockItem(ModBlocks.FLESH_CROP.get(),
                    new Item.Properties()
            )
    );

    public static final RegistryObject<Item> FLESH = ITEMS.register("flesh",
            () -> new Item(new Item.Properties())
    );

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}