package net.berserker_rpg.client.armor.berserker;

import mod.azure.azurelibarmor.common.api.client.model.GeoModel;
import net.berserker_rpg.BerserkerClassMod;
import net.berserker_rpg.item.armor.WildlingArmor;
import net.minecraft.util.Identifier;

public class WildlingArmorModel extends GeoModel<WildlingArmor> {
    @Override
    public Identifier getModelResource(WildlingArmor object) {
        return Identifier.of(BerserkerClassMod.MOD_ID, "geo/wildling_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(WildlingArmor armor) {
        return Identifier.of(BerserkerClassMod.MOD_ID, "textures/armor/wildling.png");
    }

    @Override
    public Identifier getAnimationResource(WildlingArmor animatable) {
        return null;
    }
}