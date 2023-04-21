package net.esotericsteam.esoterics.event;

import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.networking.ModMessages;
import net.esotericsteam.esoterics.networking.packet.ManaC2SPacket;
import net.esotericsteam.esoterics.util.KeyBinding;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.client.event.InputEvent;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Esoterics.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event){
            if(KeyBinding.MANA_KEY.consumeClick()){
                ModMessages.sendToServer(new ManaC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Esoterics.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event){
            event.register(KeyBinding.MANA_KEY);
        }
    }
}
