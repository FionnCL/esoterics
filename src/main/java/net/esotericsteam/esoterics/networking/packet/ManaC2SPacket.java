package net.esotericsteam.esoterics.networking.packet;

import net.esotericsteam.esoterics.mana.PlayerManaProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaC2SPacket {

    public ManaC2SPacket(){}

    public ManaC2SPacket(FriendlyByteBuf buf){

    }

    public void toBytes(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Here, we are on the server.
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();

            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(mana -> {
                mana.addMana(1);
                level.playSound(null, player.getOnPos(), SoundEvents.GENERIC_SPLASH, SoundSource.PLAYERS);
                player.sendSystemMessage(Component.literal("Current Mana: " + mana.getMana()).
                        withStyle(ChatFormatting.AQUA));
            });
        });
        return true;
    }
}
