package net.esotericsteam.esoterics.item.custom;

import net.esotericsteam.esoterics.entity.ModEntityTypes;
import net.esotericsteam.esoterics.entity.custom.CrystalStormSpellProjectile;
import net.esotericsteam.esoterics.entity.custom.SpellProjectile;
import net.esotericsteam.esoterics.item.client.GauntletRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

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

    public SpellProjectile createSpellProjectile(Level level, LivingEntity livingEntity) {
        // Hard Coded to return only 1 type of spell.
        return new CrystalStormSpellProjectile(
                ModEntityTypes.CRYSTAL_STORM_SPELL_PROJECTILE.get(),
                livingEntity,
                level
        );
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

    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int release) {
        // FIXME: Literally carbon-copy of BowItem.
        if (livingEntity instanceof Player player) {
            // If player in creative, doesn't need ammo???
            boolean flag = player.getAbilities().instabuild || EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, stack) > 0;
            ItemStack itemstack = player.getProjectile(stack);

            int i = this.getUseDuration(stack) - release;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, level, player, i, !itemstack.isEmpty() || flag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || flag) {
                if (itemstack.isEmpty()) {
                    // Gives arrow if player is in creative? and the ItemStack is empty?
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getPowerForTime(i);
                if (!((double)f < 0.1D)) {
                    boolean flag1 = player.getAbilities().instabuild || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, stack, player));
                    if (!level.isClientSide) {
                        SpellProjectile abstractSpellProjectile = createSpellProjectile(level, player);
                        abstractSpellProjectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);

                        stack.hurtAndBreak(1, player, (p_276007_) -> {
                            p_276007_.broadcastBreakEvent(player.getUsedItemHand());
                        });

                        level.addFreshEntity(abstractSpellProjectile);
                    }

                    level.playSound(
                            (Player)null,
                            player.getX(),
                            player.getY(),
                            player.getZ(),
                            SoundEvents.ARROW_SHOOT,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    // Removes arrow when shot, if the player isn't in creative mode.
                    if (!flag1 && !player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            player.getInventory().removeItem(itemstack);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    public static float getPowerForTime(int p_40662_) {
        float f = (float)p_40662_ / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BOW;
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        //return (itemStack) -> itemStack.is(ModTags.Items.SPELLS);
        return ARROW_ONLY;
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
