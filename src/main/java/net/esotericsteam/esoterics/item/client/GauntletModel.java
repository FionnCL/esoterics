package net.esotericsteam.esoterics.item.client;

import net.esotericsteam.esoterics.Esoterics;
import net.esotericsteam.esoterics.item.custom.Gauntlet;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GauntletModel extends GeoModel<Gauntlet> {

    @Override
    public ResourceLocation getModelResource(Gauntlet animatable) {
        return new ResourceLocation(Esoterics.MOD_ID, "geo/gauntlet.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Gauntlet animatable) {
        return new ResourceLocation(Esoterics.MOD_ID, "textures/item/gauntlet_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Gauntlet animatable) {
        return new ResourceLocation(Esoterics.MOD_ID, "animations/gauntlet.animation.json");
    }
}
