package net.berserker_rpg.custom.custom_spells;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.util.Identifier;
import net.more_rpg_classes.entity.attribute.MRPGCEntityAttributes;
import net.spell_power.SpellPowerMod;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;

import static net.spell_power.api.SpellPowerMechanics.PERCENT_ATTRIBUTE_BASELINE;

public class BerserkerSpellSchool {
    public static final SpellSchool BERSERKER_MELEE = new SpellSchool(SpellSchool.Archetype.MAGIC,
            new Identifier(SpellPowerMod.ID, "berserker_melee"),
            0xb3b3b3,
            DamageTypes.PLAYER_ATTACK,
            EntityAttributes.GENERIC_ATTACK_DAMAGE);

    public static void initialize() {
        BERSERKER_MELEE.attributeManagement = SpellSchool.Manage.EXTERNAL;
        BERSERKER_MELEE.addSource(SpellSchool.Trait.POWER, SpellSchool.Apply.ADD, query -> {
            var power = query.entity().getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            var level = EnchantmentHelper.getLevel(Enchantments.SHARPNESS, query.entity().getMainHandStack());
            power *= 1 + (0.05 * level);
            return power;
        });
        BERSERKER_MELEE.addSource(SpellSchool.Trait.CRIT_CHANCE, new SpellSchool.Source(SpellSchool.Apply.ADD, query ->  {
            var value = SpellPowerMod.attributesConfig.value.base_spell_critical_chance_percentage
                    + query.entity().getAttributeValue(MRPGCEntityAttributes.RAGE_MODIFIER);
            var rate = (value / PERCENT_ATTRIBUTE_BASELINE);
            return (rate - 1)/10;
        }));
        BERSERKER_MELEE.addSource(SpellSchool.Trait.CRIT_DAMAGE, new SpellSchool.Source(SpellSchool.Apply.ADD, query -> {
            var value = SpellPowerMod.attributesConfig.value.base_spell_critical_damage_percentage
                    + query.entity().getAttributeValue(MRPGCEntityAttributes.RAGE_MODIFIER);
            var rate = (value / PERCENT_ATTRIBUTE_BASELINE);
            return (rate - 1)/4;
        }));
        SpellSchools.configureSpellCritDamage(BERSERKER_MELEE);
        SpellSchools.configureSpellCritChance(BERSERKER_MELEE);
        SpellSchools.configureSpellHaste(BERSERKER_MELEE);
        SpellSchools.register(BERSERKER_MELEE);
    }
}
