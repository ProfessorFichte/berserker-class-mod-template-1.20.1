package net.berserker_rpg.client.armor.berserker;

import mod.azure.azurelibarmor.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelibarmor.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.util.Identifier;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;

public class NorthlingArmorRenderer extends AzArmorRenderer {
    public static NorthlingArmorRenderer northling() {
        return new NorthlingArmorRenderer("northling_armor", "northling");
    }

    public static NorthlingArmorRenderer netherite_northling() {
        return new NorthlingArmorRenderer("northling_armor", "netherite_northling");
    }

    public NorthlingArmorRenderer(String modelName, String textureName) {
        super(AzArmorRendererConfig.builder(
                Identifier.of(MOD_ID, "geo/" + modelName + ".geo.json"),
                Identifier.of(MOD_ID, "textures/armor/" + textureName + ".png")
        ).build());
    }
}
