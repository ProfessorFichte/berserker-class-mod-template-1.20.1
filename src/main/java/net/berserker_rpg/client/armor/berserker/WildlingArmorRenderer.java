package net.berserker_rpg.client.armor.berserker;

import mod.azure.azurelibarmor.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelibarmor.rewrite.render.armor.AzArmorRendererConfig;
import net.minecraft.util.Identifier;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;

public class WildlingArmorRenderer extends AzArmorRenderer {
    public static WildlingArmorRenderer wildling() {
        return new WildlingArmorRenderer("wildling_armor", "wildling");
    }

    public WildlingArmorRenderer(String modelName, String textureName) {
        super(AzArmorRendererConfig.builder(
                Identifier.of(MOD_ID, "geo/" + modelName + ".geo.json"),
                Identifier.of(MOD_ID, "textures/armor/" + textureName + ".png")
        ).build());
    }
}
