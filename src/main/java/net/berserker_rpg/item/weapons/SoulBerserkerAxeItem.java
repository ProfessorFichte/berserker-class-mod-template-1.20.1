package net.berserker_rpg.item.weapons;

import com.google.common.collect.Multimap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.more_rpg_classes.entity.attribute.MRPGCEntityAttributes;
import net.spell_engine.api.item.ConfigurableAttributes;
import net.spell_engine.api.item.weapon.MeleeWeaponItem;
import net.spell_engine.api.item.weapon.SpellSwordItem;
import com.google.common.collect.ImmutableMultimap;
import net.minecraft.entity.EquipmentSlot;

import java.util.List;
import java.util.UUID;

public class SoulBerserkerAxeItem extends MeleeWeaponItem implements ConfigurableAttributes {
    public SoulBerserkerAxeItem(ToolMaterial material, Settings settings) {
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

    private Multimap<EntityAttribute, EntityAttributeModifier> attributes;
    public void setAttributes(Multimap<EntityAttribute, EntityAttributeModifier> attributes) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(attributes);
        builder.put(MRPGCEntityAttributes.LIFESTEAL_MODIFIER,new EntityAttributeModifier(UUID.fromString("05e6eef1-2973-4ed1-94fd-cb85f7f4649f"),
                "lifesteal",0.1, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        builder.put(MRPGCEntityAttributes.RAGE_MODIFIER,new EntityAttributeModifier(UUID.fromString("05e6eef1-2973-4ed1-94fd-cb85f7f4649f"),
                "rage",0.05, EntityAttributeModifier.Operation.MULTIPLY_BASE));


        this.attributes = builder.build();
    }
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {

        if (this.attributes == null) {
            return super.getAttributeModifiers(slot);
        } else {
            return slot == EquipmentSlot.MAINHAND ? this.attributes : super.getAttributeModifiers(slot);
        }
    }
}
