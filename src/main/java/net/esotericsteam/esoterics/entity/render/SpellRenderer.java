package net.esotericsteam.esoterics.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.entity.custom.SpellProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.DragonFireball;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class SpellRenderer<T extends SpellProjectile> extends EntityRenderer<T> {
    public static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(Esoterics.MOD_ID, "textures/entity/test_texture.png");
    private static final RenderType RENDER_TYPE = RenderType.entityCutoutNoCull(TEXTURE_LOCATION);

    public SpellRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    protected int getBlockLightLevel(SpellProjectile spellProjectile, BlockPos blockPos) {
        return 15;
    }

    public void render(T t, float v, float v1, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        poseStack.pushPose();
        poseStack.scale(0.5F, 0.5F, 2.0F);
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        PoseStack.Pose posestack$pose = poseStack.last();
        Matrix4f matrix4f = posestack$pose.pose();
        Matrix3f matrix3f = posestack$pose.normal();
        VertexConsumer vertexconsumer = multiBufferSource.getBuffer(RENDER_TYPE);
        vertex(vertexconsumer, matrix4f, matrix3f, i, 0.0F, 0, 0, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, i, 1.0F, 0, 1, 1);
        vertex(vertexconsumer, matrix4f, matrix3f, i, 1.0F, 1, 1, 0);
        vertex(vertexconsumer, matrix4f, matrix3f, i, 0.0F, 1, 0, 0);
        poseStack.popPose();
        super.render(t, v, v1, poseStack, multiBufferSource, i);
    }

    private static void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, int i, float v, int i1, int i2, int i3) {
        vertexConsumer
                .vertex(matrix4f, v - 0.5F, (float)i1 - 0.25F, 0.0F)
                .color(255, 255, 255, 255).uv((float)i2, (float)i3)
                .overlayCoords(OverlayTexture.NO_OVERLAY)
                .uv2(i)
                .normal(matrix3f, 0.0F, 1.0F, 0.0F)
                .endVertex();
    }

    public ResourceLocation getTextureLocation(SpellProjectile spellProjectile) {
        return TEXTURE_LOCATION;
    }
}
