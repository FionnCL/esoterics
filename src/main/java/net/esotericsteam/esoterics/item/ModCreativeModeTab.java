package net.esotericsteam.esoterics.item;

import net.esotericsteam.esoterics.Esoterics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Esoterics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab ESOTERICS_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event){
        ESOTERICS_TAB = event.registerCreativeModeTab(
                new ResourceLocation(Esoterics.MOD_ID, "esoterics_tab"),
                builder -> builder.icon(
                        () -> new ItemStack(ModItems.INCANTATION_BOWL.get()))
                        .title(Component.translatable("item.esoterics.esoterics_tab"))
                        .build()
        );
    }
}
