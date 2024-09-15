package net.berserker_rpg.custom.custom_spells;

import net.berserker_rpg.BerserkerClassMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.more_rpg_classes.entity.attribute.MRPGCEntityAttributes;
import net.spell_power.SpellPowerMod;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;

import static net.spell_power.api.SpellPowerMechanics.PERCENT_ATTRIBUTE_BASELINE;

public class BerserkerSpellSchool {
    public static final SpellSchool BERSERKER_MELEE = new SpellSchool(SpellSchool.Archetype.MELEE,
            Identifier.of(SpellPowerMod.ID, "berserker_melee"),
            0xb3b3b3,
            DamageTypes.PLAYER_ATTACK,
            EntityAttributes.GENERIC_ATTACK_DAMAGE);

    public static void initialize() {
        BERSERKER_MELEE.addSource(SpellSchool.Trait.POWER, SpellSchool.Apply.ADD, query -> {
            var power = query.entity().getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE) +
                    ((query.entity().getAttributeValue(MRPGCEntityAttributes.RAGE_MODIFIER)-100) / 50) ;
            var world = query.entity().getWorld();
            var sharpness = world.getRegistryManager().get(RegistryKeys.ENCHANTMENT).getEntry(Enchantments.SHARPNESS);
            if (sharpness.isPresent()) {
                var level = EnchantmentHelper.getLevel(sharpness.get(), query.entity().getMainHandStack());
                power *= 1 + (0.05 * level);
            }
            return power;
        });
        BERSERKER_MELEE.addSource(SpellSchool.Trait.CRIT_CHANCE, new SpellSchool.Source(SpellSchool.Apply.ADD, query ->  {
            var value = SpellPowerMod.attributesConfig.value.base_spell_critical_chance_percentage
                    + query.entity().getAttributeValue(MRPGCEntityAttributes.RAGE_MODIFIER )- 100/ 10;
            return (value/ PERCENT_ATTRIBUTE_BASELINE)-1;
        }));
        BERSERKER_MELEE.addSource(SpellSchool.Trait.CRIT_DAMAGE, new SpellSchool.Source(SpellSchool.Apply.ADD, query -> {
            var value = SpellPowerMod.attributesConfig.value.base_spell_critical_damage_percentage
                    + query.entity().getAttributeValue(MRPGCEntityAttributes.RAGE_MODIFIER )- 100/ 4;
            return (value/ PERCENT_ATTRIBUTE_BASELINE)-1;
        }));
        SpellSchools.configureSpellCritDamage(BERSERKER_MELEE);
        SpellSchools.configureSpellCritChance(BERSERKER_MELEE);
        SpellSchools.configureSpellHaste(BERSERKER_MELEE);
        SpellSchools.register(BERSERKER_MELEE);
    }
}
