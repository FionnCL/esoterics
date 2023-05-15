package net.esotericsteam.esoterics.client;

import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.entity.ModEntityTypes;
import net.esotericsteam.esoterics.entity.render.SpellRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Esoterics.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        Object EntityInit;
        EntityRenderers.register(ModEntityTypes.SPELL.get(), SpellRenderer::new);
    }
}
