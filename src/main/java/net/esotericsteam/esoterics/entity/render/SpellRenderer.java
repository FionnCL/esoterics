package net.esotericsteam.esoterics.entity.render;

import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.entity.custom.SpellProjectile;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Arrow;

public class SpellRenderer extends EntityRenderer<SpellProjectile> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(Esoterics.MOD_ID, "textures/entity/test_texture.png");

    public SpellRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    public ResourceLocation getTextureLocation(SpellProjectile spell) {
        return TEXTURE;
    }
}
