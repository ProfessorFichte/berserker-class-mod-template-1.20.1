package net.berserker_rpg.client.armor.berserker;

import mod.azure.azurelibarmor.common.api.client.model.GeoModel;
import net.berserker_rpg.BerserkerClassMod;
import net.berserker_rpg.item.armor.NorthlingArmor;
import net.minecraft.util.Identifier;

public class NorthlingArmorModel extends GeoModel<NorthlingArmor> {
    @Override
    public Identifier getModelResource(NorthlingArmor object) {
        return Identifier.of(BerserkerClassMod.MOD_ID, "geo/northling_armor.geo.json");
    }

    @Override
    public Identifier getTextureResource(NorthlingArmor armor) {
        var textureId = armor.getFirstLayerId();
        return Identifier.of(BerserkerClassMod.MOD_ID, "textures/armor/" + textureId.getPath() + ".png");
    }

    @Override
    public Identifier getAnimationResource(NorthlingArmor animatable) {
        return null;
    }
}
