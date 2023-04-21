package net.esotericsteam.esoterics.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.function.Function;

public class ManaHudOverlay {
    //private static final String MANA = "gui.esoterics.mana";

    public static final IGuiOverlay HUD_MANA = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
        //TODO: Make this.
//        Function<ResourceLocation, FontSet> fontConstructor = (() -> {
//            return new FontSet(new TextureManager(), Minecraft.DEFAULT_FONT);
//        });
//
//        //AbstractContainerScreen.drawString(poseStack, font.draw());
//        Font font = new Font(fontConstructor, true);
//
//        int x = screenWidth / 2;
//        int y = screenHeight;
//
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//        GuiComponent.drawString(poseStack, font, Component.translatable(MANA), x, y, 0);
    });
}
