package net.esotericsteam.esoterics.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

import java.awt.im.InputContext;

public class KeyBinding {
    public static final String KEY_CATEGORY_ESOTERICS = "key.category.esoterics.tutorial";
    public static final String KEY_SEE_MANA = "key.esoterics.see_mana";

    public static final KeyMapping MANA_KEY = new KeyMapping(KEY_SEE_MANA, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_ESOTERICS);
}
