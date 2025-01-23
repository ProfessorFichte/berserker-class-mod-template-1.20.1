package net.berserker_rpg.item.weapons;

import more_rpg_loot.item.weapons.GlacialMeleeWeapon;
import net.minecraft.item.ToolMaterial;

public class GlacialAxe extends GlacialMeleeWeapon {
    public GlacialAxe(ToolMaterial material, Settings settings) {
        this(material, 1, 2.4F, settings);
    }

    public GlacialAxe(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}

