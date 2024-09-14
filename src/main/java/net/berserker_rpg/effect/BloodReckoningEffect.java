package net.berserker_rpg.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;

import static net.berserker_rpg.BerserkerClassMod.effectsConfig;

public class BloodReckoningEffect extends StatusEffect {
    protected BloodReckoningEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    public void onApplied(LivingEntity entity, int amplifier) {
        super.onApplied(entity, amplifier);
        final float absorption = entity.getAbsorptionAmount();
        final float base_heal = effectsConfig.value.blood_reckoning_base_heal;
        entity.heal(base_heal + (absorption * effectsConfig.value.blood_reckoning_absoprtion_to_heal));
        if(entity.hasStatusEffect(Effects.RAGE.registryEntry)){
            final int amp_rage = entity.getStatusEffect(Effects.RAGE.registryEntry).getAmplifier();
            final int dura_rage = entity.getStatusEffect(Effects.RAGE.registryEntry).getDuration();
            entity.addStatusEffect(new StatusEffectInstance(Effects.RAGE.registryEntry, (int) ((dura_rage +absorption) * 2),amp_rage,false,false,true));
        }
        entity.setAbsorptionAmount(0);
    }
}
