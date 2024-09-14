package net.berserker_rpg.item.weapons;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.spell_engine.api.item.weapon.MeleeWeaponItem;

public class ThunderBerserkerAxeItem extends MeleeWeaponItem {
    public ThunderBerserkerAxeItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }


    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (state.isOf(Blocks.COBWEB)) {
            return 15.0F;
        } else {
            return state.isIn(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
        }
    }
    @Override
    public boolean isSuitableFor(BlockState state) {
        return state.isOf(Blocks.COBWEB);
    }
}

