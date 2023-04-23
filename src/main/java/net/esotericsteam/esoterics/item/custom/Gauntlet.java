package net.esotericsteam.esoterics.item.custom;

import net.esotericsteam.esoterics.entity.ModEntityTypes;
import net.esotericsteam.esoterics.entity.custom.CrystalStormSpellProjectile;
import net.esotericsteam.esoterics.entity.custom.SpellProjectile;
import net.esotericsteam.esoterics.item.client.GauntletRenderer;
import net.esotericsteam.esoterics.util.ModTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.text.DecimalFormat;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Gauntlet extends ProjectileWeaponItem implements GeoItem {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public Gauntlet(Properties properties) {
        super(properties);
    }

    public SpellProjectile customProjectile(SpellProjectile spell) {
        return spell;
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    // Hard coded in right now.
    public SpellProjectile createSpellProjectile(Level level){
        return new CrystalStormSpellProjectile(ModEntityTypes.CRYSTAL_STORM_SPELL_PROJECTILE.get(), level);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            // FIXME: Almost carbon-copy of ProjectileWeaponItem
            ItemStack stack = player.getItemInHand(interactionHand);
            boolean flag = !player.getProjectile(stack).isEmpty();

            InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(
                    stack, level, player, interactionHand, flag
            );
            if (ret != null) return ret;
            if (!player.getAbilities().instabuild && !flag) {
                return InteractionResultHolder.fail(stack);
            } else {
                player.startUsingItem(interactionHand);
                return InteractionResultHolder.consume(stack);
            }
            //Adding a cool-down could be a good idea for balancing, we'll see.
        }
        return super.use(level, player, interactionHand);
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        //return (itemStack) -> itemStack.is(ModTags.Items.SPELLS);
        return ARROW_ONLY;
    }

    private PlayState predicate(AnimationState animationState){
        animationState.getController().setAnimation(RawAnimation.begin().then("GauntletOrb", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object itemStack) {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GauntletRenderer renderer;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null){
                    renderer = new GauntletRenderer();
                }
                return renderer;
            }
        });
    }
}
