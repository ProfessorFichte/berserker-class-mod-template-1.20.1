package net.berserker_rpg.item.weapons;

import more_rpg_loot.item.weapons.WitherMeleeWeapon;
import net.minecraft.item.ToolMaterial;

public class WitherAxe extends WitherMeleeWeapon {
    public WitherAxe(ToolMaterial material, Settings settings) {
        this(material, 1, 2.4F, settings);
    }

    public WitherAxe(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
