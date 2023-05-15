package net.esotericsteam.esoterics;

import com.mojang.logging.LogUtils;
import net.esotericsteam.esoterics.block.ModBlocks;
import net.esotericsteam.esoterics.entity.ModEntityTypes;
import net.esotericsteam.esoterics.item.ModCreativeModeTab;
import net.esotericsteam.esoterics.item.ModItems;
import net.esotericsteam.esoterics.networking.ModMessages;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Esoterics.MOD_ID)
public class Esoterics
{
    public static final String MOD_ID = "esoterics";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Esoterics()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntityTypes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        ModMessages.register();
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event){
        if(event.getTab() == ModCreativeModeTab.ESOTERICS_TAB){
            // Items
            event.accept(ModItems.INCANTATION_BOWL);

            // Weapons
            event.accept(ModItems.GAUNTLET);

            // Armour

            // Crops & Yields
            event.accept(ModItems.FLESH_SEEDS);
            event.accept(ModItems.FLESH);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.FLESH_CROP.get(), RenderType.cutout());
        }
    }
}
