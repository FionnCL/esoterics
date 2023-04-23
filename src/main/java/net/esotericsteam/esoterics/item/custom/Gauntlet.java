package net.esotericsteam.esoterics.item.custom;

import net.esotericsteam.esoterics.entity.ModEntityTypes;
import net.esotericsteam.esoterics.entity.custom.CrystalStormSpellProjectile;
import net.esotericsteam.esoterics.entity.custom.SpellProjectile;
import net.esotericsteam.esoterics.item.client.GauntletRenderer;
import net.esotericsteam.esoterics.util.ModTags;
import net.minecraft.Util;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractHurtingProjectile;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
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
    protected final RandomSource random = RandomSource.create();

    public Gauntlet(Properties properties) {
        super(properties);
    }

    public SpellProjectile createSpellProjectile(Level level, LivingEntity livingEntity, double d1, double d2, double d3) {
        // FIXME: Hard Coded to return only 1 type of spell.
        return new CrystalStormSpellProjectile(
                level,
                livingEntity,
                d1,
                d2,
                d3
        );
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if(!level.isClientSide() && interactionHand == InteractionHand.MAIN_HAND) {
            Vec3 vec3 = player.getLookAngle();

            double d0 = 1; // This denotes distance, which I don't believe matters.
            double d1 = (player.getX() + vec3.x * 10) - player.getX();
            double d2 = ((player.getY() + (player.getBbHeight() * 0.5D)) + (vec3.y * 10)) - player.getY(0.5D);
            double d3 = (player.getZ() + vec3.z * 10) - player.getZ();
            double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;

            DecimalFormat df = new DecimalFormat("#.###");
            player.sendSystemMessage(Component.literal("-------------"));
            player.sendSystemMessage(Component.literal("vec3.x: " + df.format(vec3.x)));
            player.sendSystemMessage(Component.literal("vec3.y: " + df.format(vec3.y)));
            player.sendSystemMessage(Component.literal("vec3.z: " + df.format(vec3.z)));
            player.sendSystemMessage(Component.literal("-------------"));

            SpellProjectile spell = createSpellProjectile(
                    level,
                    player,
                    player.getRandom().triangle(d1, 0.01F * d4),
                    d2,
                    player.getRandom().triangle(d3, 0.01F * d4)
            );

            spell.setPos(spell.getX(), player.getY(0.5D) + 0.5D, spell.getZ());
            level.addFreshEntity(spell);

            level.playSound(
                        (Player)null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        SoundEvents.BLAZE_SHOOT,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + 0.5F
                );
            player.awardStat(Stats.ITEM_USED.get(this));
        }

        return super.use(level, player, interactionHand);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 15;
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return (itemStack) -> itemStack.is(ModTags.Items.SPELLS);
    }

    // Animations
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
