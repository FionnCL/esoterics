package net.esotericsteam.esoterics.item.client;

import net.esotericsteam.esoterics.item.custom.Gauntlet;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class GauntletRenderer extends GeoItemRenderer<Gauntlet> {
    public GauntletRenderer() {
        super(new GauntletModel());
    }
}
