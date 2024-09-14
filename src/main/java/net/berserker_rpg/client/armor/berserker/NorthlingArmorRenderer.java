package net.berserker_rpg.client.armor.berserker;

import mod.azure.azurelibarmor.common.api.client.renderer.GeoArmorRenderer;
import net.berserker_rpg.item.armor.NorthlingArmor;

public class NorthlingArmorRenderer extends GeoArmorRenderer<NorthlingArmor> {
    public NorthlingArmorRenderer() {
        super(new NorthlingArmorModel());
    }
}
