package net.berserker_rpg.item.weapons;

import more_rpg_loot.item.weapons.DragonMeeleeWeapon;
import net.minecraft.item.ToolMaterial;

public class DragonAxe extends DragonMeeleeWeapon {
    public DragonAxe(ToolMaterial material, Settings settings) {
        this(material, 1, 2.4F, settings);
    }

    public DragonAxe(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
