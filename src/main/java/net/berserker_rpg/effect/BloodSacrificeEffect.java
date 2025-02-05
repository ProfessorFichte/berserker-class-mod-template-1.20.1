package net.berserker_rpg.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.spell_engine.api.spell.registry.SpellRegistry;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;
import static net.berserker_rpg.BerserkerClassMod.effectsConfig;

public class BloodSacrificeEffect extends StatusEffect {
    private int healthPerStack;

    public BloodSacrificeEffect(StatusEffectCategory category, int color) {
        super(category, color);
        this.healthPerStack = 2  ;
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        return entity.getAbsorptionAmount() > 0.0F || entity.getWorld().isClient;
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    public void onApplied(LivingEntity entity, int amplifier) {
        var spellEntry = SpellRegistry.from(entity.getWorld()).getEntry(Identifier.of(MOD_ID, "bloody_strike")).orElse(null);
        var spell = spellEntry.value();
        float modifier = spell.impact[0].action.damage.spell_power_coefficient;
        var attack_damage = entity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float actual_health_player = entity.getHealth();
        double amount = modifier * attack_damage;
        float self_damage_calc = (float) (amount * effectsConfig.value.bloody_strike_self_damage);
        if(actual_health_player <= 0.5F){
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 300,2,false,false,true));
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 300,2,false,false,true));
        }else{
            if(self_damage_calc > actual_health_player){
                entity.setHealth(0.5F);
            }else{
                entity.setHealth(actual_health_player- self_damage_calc);
            }
            entity.setAbsorptionAmount(Math.max(entity.getAbsorptionAmount(), healthPerStack  * (1 + amplifier )));
        }

        super.onApplied(entity, amplifier);
    }

}
