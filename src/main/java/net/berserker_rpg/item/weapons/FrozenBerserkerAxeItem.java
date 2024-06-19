package net.berserker_rpg.item.weapons;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.more_rpg_classes.effect.MRPGCEffects;
import net.spell_engine.api.item.weapon.MeleeWeaponItem;
import net.spell_engine.api.item.weapon.SpellSwordItem;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.internals.SpellRegistry;
import net.spell_engine.internals.casting.SpellCast;
import net.spell_engine.utils.TargetHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;
import static net.spell_engine.internals.SpellRegistry.getSpell;

public class FrozenBerserkerAxeItem extends MeleeWeaponItem {
    public FrozenBerserkerAxeItem(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    //10% chance to frost
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        int frosted_duration = 100;
        int frosted_chance = 10;
        int randomrange_freeze = (int) ((Math.random() * (1 + frosted_chance)) + 1);

        if (randomrange_freeze >= frosted_duration ) {
            target.addStatusEffect(new StatusEffectInstance(MRPGCEffects.FROSTED, frosted_duration));

        }
        stack.damage(1, attacker, (e)->{
            e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
        });
        return true;
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
