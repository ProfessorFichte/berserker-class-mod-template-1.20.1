package net.berserker_rpg.item.weapons;

import more_rpg_loot.item.weapons.ElderGuardianMeleeWeapon;
import net.minecraft.item.ToolMaterial;

public class ElderGuardianAxe extends ElderGuardianMeleeWeapon {
    public ElderGuardianAxe(ToolMaterial material, Settings settings) {
        this(material, 1, 2.4F, settings);
    }

    public ElderGuardianAxe(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
