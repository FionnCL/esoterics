package net.esotericsteam.esoterics.client;

import net.minecraftforge.client.gui.overlay.IGuiOverlay;

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
