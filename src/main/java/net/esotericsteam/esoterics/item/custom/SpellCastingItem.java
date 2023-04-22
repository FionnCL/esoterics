package net.esotericsteam.esoterics.item.custom;

import net.esotericsteam.esoterics.entity.custom.CrystalStormSpellProjectile;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.function.Predicate;

public class SpellCastingItem extends ProjectileWeaponItem {
    public SpellCastingItem(Properties properties) {
        super(properties);
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        ItemStack stack = new ItemStack(CrystalStormSpellProjectile);
        return new Predicate<ItemStack>();
    }

    @Override
    public int getDefaultProjectileRange() {
        return 64;
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            Vec3 vec3 = player.getLookAngle();
            //CrystalStormSpellProjectile.shoot(vec3.x, vec3.y, vec3.z, 1, 1);
            DecimalFormat df = new DecimalFormat("#.##");
            player.sendSystemMessage(Component.literal(
                        "Vector: { x:" + df.format(vec3.x) +
                        ", y:" + df.format(vec3.y) +
                        ", z:" + df.format(vec3.z) + " }"
                    ).
                    withStyle(ChatFormatting.WHITE));
            //Adding a cool-down could be a good idea for balancing, we'll see.
        }

        return super.use(level, player, interactionHand);
    }
}
