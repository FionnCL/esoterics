package net.esotericsteam.esoterics.event;

import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.mana.PlayerMana;
import net.esotericsteam.esoterics.mana.PlayerManaProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Esoterics.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerManaProvider.PLAYER_MANA).isPresent()){
                event.addCapability(new ResourceLocation(Esoterics.MOD_ID, "properties"), new PlayerManaProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event){
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event){
        event.register(PlayerMana.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
        if(event.side == LogicalSide.SERVER){
            event.player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                if(mana.getMana() > 0 && event.player.getRandom().nextFloat() < 0.0005f) {
                    mana.subtractMana(1);
                    event.player.sendSystemMessage(Component.literal("Mana Decreased"));
                }
            });
        }
    }
}
