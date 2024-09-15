package net.berserker_rpg.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.more_rpg_classes.entity.attribute.MRPGCEntityAttributes;
import net.spell_engine.api.effect.*;

import java.util.ArrayList;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;
import static net.berserker_rpg.BerserkerClassMod.effectsConfig;

public class Effects {
    private static final ArrayList<Entry> entries = new ArrayList<>();
    public static class Entry {
        public final Identifier id;
        public final StatusEffect effect;
        public RegistryEntry<StatusEffect> registryEntry;
        public Entry(String name, StatusEffect effect) {
            this.id = Identifier.of(MOD_ID, name);
            this.effect = effect;
            entries.add(this);
        }
        public void register() {
            registryEntry = Registry.registerReference(Registries.STATUS_EFFECT, id, effect);
        }
        public Identifier modifierId() {
            return Identifier.of(MOD_ID, "effect." + id.getPath());
        }
    }

    public static final Entry RAGE=  new Entry("rage",new CustomStatusEffect(StatusEffectCategory.BENEFICIAL, 0xf70000));
    public static final Entry SOUL_DEVOURER =  new Entry("soul_devourer",new CustomStatusEffect(StatusEffectCategory.BENEFICIAL, 0x01d9cf));
    public static final Entry BLOOD_RECKONING=  new Entry("blood_reckoning",new BloodReckoningEffect(StatusEffectCategory.BENEFICIAL, 0xf70000));
    public static final Entry BLOOD_SACRIFICE =  new Entry("blood_sacrifice",new BloodSacrificeEffect(StatusEffectCategory.BENEFICIAL, 0xf70000));


    public static void register(){
        RAGE.effect
                .addAttributeModifier(MRPGCEntityAttributes.RAGE_MODIFIER, RAGE.modifierId(),
                        effectsConfig.value.rage_rage_attribute_increase_per_stack, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE)
                .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, RAGE.modifierId(),
                        effectsConfig.value.rage_attack_speed_increase_per_stack, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
        BLOOD_SACRIFICE.effect.addAttributeModifier(EntityAttributes.GENERIC_MAX_ABSORPTION,
                BLOOD_SACRIFICE.modifierId(), 4, EntityAttributeModifier.Operation.ADD_VALUE);

        Synchronized.configure(RAGE.effect,true);
        Synchronized.configure(SOUL_DEVOURER.effect,true);
        Synchronized.configure(BLOOD_RECKONING.effect,true);
        Synchronized.configure(BLOOD_SACRIFICE.effect,true);

        HealthImpacting.configureDamageTaken(RAGE.effect,effectsConfig.value.rage_increased_incoming_damage_per_stack);

        for (var entry: entries) {
            entry.register();
        }
    }
}
