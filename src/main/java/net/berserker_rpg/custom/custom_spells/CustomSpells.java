package net.berserker_rpg.custom.custom_spells;

import net.berserker_rpg.damage.BerserkerSpellCostSource;
import net.berserker_rpg.effect.Effects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.RaycastContext;
import net.more_rpg_classes.effect.MRPGCEffects;
import net.more_rpg_classes.entity.attribute.MRPGCEntityAttributes;
import net.spell_engine.api.spell.CustomSpellHandler;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.SpellInfo;
import net.spell_engine.internals.SpellHelper;
import net.spell_engine.utils.SoundHelper;
import net.spell_engine.utils.TargetHelper;

import java.util.List;
import java.util.function.Predicate;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;
import static net.more_rpg_classes.util.CustomMethods.clearNegativeEffects;
import static net.spell_engine.internals.SpellRegistry.getSpell;

public class CustomSpells {
    public static void register() {
        float bloody_strike_heal = 1.0F;
        float spellcost_soulaxe_drain = 1.0f;
        int wild_rage_duration = 600;

        ////BERSERKER_SPELLS
        /// BLOODY STRIKE
        CustomSpellHandler.register(new Identifier(MOD_ID, "bloody_strike"), (data) -> {
            CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
            float modifier = getSpell(new Identifier(MOD_ID, "bloody_strike")).impact[0].action.damage.spell_power_coefficient;
            var actualSchool = data1.caster().getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            for (Entity entity : data1.targets()) {
                if (entity instanceof LivingEntity living) {
                    double amount = modifier * actualSchool;
                    if (living.isUndead()) {
                        entity.damage(living.getDamageSources().playerAttack(data1.caster()),(float) amount);
                        //entity.damage(SpellDamageSource.player(actualSchool, data1.caster()), (float) amount);
                    } else {
                        SpellHelper.performImpacts(entity.getWorld(), data1.caster(), entity, entity, new SpellInfo(getSpell(new Identifier(MOD_ID, "bloody_strike")),new Identifier(MOD_ID)), data1.impactContext());
                        float healamount = (float) amount * bloody_strike_heal;
                        data1.caster().heal(healamount);
                        ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(MRPGCEffects.BLEEDING,6));
                    }
                    return true;
                }
            }
            return true;
        });
        /// WILD RAGE
        CustomSpellHandler.register(new Identifier(MOD_ID, "wild_rage"), (data) -> {
            CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
            Predicate<Entity> selectionPredicate = (target2) -> {
                return (TargetHelper.actionAllowed(TargetHelper.TargetingMode.AREA, TargetHelper.Intent.HARMFUL, data1.caster(), target2)
                );
            };
            if (!data1.caster().getWorld().isClient) {
                data1.caster().addStatusEffect(new StatusEffectInstance(Effects.RAGE, wild_rage_duration));
                List<Entity> list = data1.caster().getWorld().getOtherEntities(data1.caster(), data1.caster().getBoundingBox().expand(getSpell(new Identifier(MOD_ID, "wild_rage")).range), selectionPredicate);
                for (Entity entity : list) {
                    SpellHelper.performImpacts(entity.getWorld(), data1.caster(), entity, entity, new SpellInfo(getSpell(new Identifier(MOD_ID, "wild_rage")),new Identifier(MOD_ID)), data1.impactContext());
                }
            }
            return true;
        });
        /// OUTRAGE
        CustomSpellHandler.register(new Identifier(MOD_ID, "outrage"), (data) -> {
            CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
            float modifier = getSpell(new Identifier(MOD_ID, "outrage")).impact[0].action.damage.spell_power_coefficient;
            double rage_attr = (data1.caster().getAttributeValue(MRPGCEntityAttributes.RAGE_MODIFIER)-100) / 10;

            Predicate<Entity> selectionPredicate = (target2) -> {
                return (TargetHelper.actionAllowed(TargetHelper.TargetingMode.DIRECT, TargetHelper.Intent.HARMFUL, data1.caster(), target2)
                );
            };
            if (!data1.caster().getWorld().isClient) {
                for (Entity entity : data1.targets()) {

                    if (data1.caster().hasStatusEffect(Effects.RAGE)) {
                        SoundHelper.playSound(data1.caster().getWorld(), entity, getSpell(new Identifier(MOD_ID, "outrage")).impact[0].sound);
                        double amount2 = (((float)rage_attr * modifier));
                        entity.damage(entity.getDamageSources().playerAttack(data1.caster()),(float) amount2);
                        SpellHelper.performImpacts(entity.getWorld(), data1.caster(), entity, entity, new SpellInfo(getSpell(new Identifier(MOD_ID, "outrage")),new Identifier(MOD_ID)), data1.impactContext());
                        clearNegativeEffects(data1.caster(),true);
                    }else{
                        SpellHelper.performImpacts(entity.getWorld(), data1.caster(), entity, entity, new SpellInfo(getSpell(new Identifier(MOD_ID, "outrage")),new Identifier(MOD_ID)), data1.impactContext());
                        SoundHelper.playSound(data1.caster().getWorld(), entity, getSpell(new Identifier(MOD_ID, "outrage")).impact[0].sound);
                    }
                }
            }
            return false;
        });
        /// SOULAXE DRAIN
        CustomSpellHandler.register(new Identifier(MOD_ID,"soulaxe_drain"),(data) -> {
            CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
            Predicate<Entity> selectionPredicate = (target2) -> {
                return (TargetHelper.actionAllowed(TargetHelper.TargetingMode.AREA, TargetHelper.Intent.HARMFUL, data1.caster(), target2)
                );
            };
            if (!data1.caster().getWorld().isClient) {
                data1.caster().damage(new BerserkerSpellCostSource(data1.caster().getDamageSources().starve().getTypeRegistryEntry()), spellcost_soulaxe_drain);
                List<Entity> list = data1.caster().getWorld().getOtherEntities(data1.caster(), data1.caster().getBoundingBox().expand(getSpell(new Identifier(MOD_ID, "soulaxe_drain")).range), selectionPredicate);
                for (Entity entity : list) {
                    SpellHelper.performImpacts(entity.getWorld(), data1.caster(), entity, entity, new SpellInfo(getSpell(new Identifier(MOD_ID, "soulaxe_drain")),new Identifier(MOD_ID)), data1.impactContext());
                }
            }
            return false;
        });
        /// RUMBLING SWING
        CustomSpellHandler.register(new Identifier(MOD_ID,"rumbling_swing"),(data) -> {
            float range = getSpell(new Identifier(MOD_ID, "rumbling_swing")).range;
            CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;
            BlockHitResult result = data1.caster().getWorld().raycast(new RaycastContext(data1.caster().getEyePos(), data1.caster().getEyePos()
                    .add(data1.caster().getRotationVector().multiply(getSpell(new Identifier(MOD_ID,"rumbling_swing")).range)),
                    RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE,data1.caster()));
            if(result.getPos() != null) {
                data1.caster().requestTeleport(result.getPos().getX(),result.getPos().getY(),result.getPos().getZ());
            }
            List<Entity> list = TargetHelper.targetsFromArea(data1.caster(),data1.caster().getEyePos(),range,new Spell.Release.Target.Area(), target -> TargetHelper.allowedToHurt(data1.caster(),target) );
            for(Entity entity : list){
                SpellHelper.performImpacts(data1.caster().getWorld(),data1.caster(),entity,data1.caster(), new SpellInfo(getSpell(new Identifier(MOD_ID, "rumbling_swing")),new Identifier(MOD_ID)),data1.impactContext());
            }
            return true;
        });
        /*
        /// NORDIC STORM
        CustomSpellHandler.register(new Identifier(MOD_ID, "nordic_storm"), (data) -> {
            CustomSpellHandler.Data data1 = (CustomSpellHandler.Data) data;

            Predicate<Entity> selectionPredicate = (target2) -> {
                return (TargetHelper.actionAllowed(TargetHelper.TargetingMode.AREA, TargetHelper.Intent.HARMFUL, data1.caster(), target2)
                );
            };
                if (!data1.caster().getWorld().isClient) {
                    List<Entity> list = data1.caster().getWorld().getOtherEntities(data1.caster(), data1.caster().getBoundingBox().expand(getSpell(new Identifier(MOD_ID, "nordic_storm")).range), selectionPredicate);
                    ParticleHelper.sendBatches(data1.caster(), getSpell(new Identifier(MOD_ID, "nordic_storm")).release.particles);
                    for (Entity entity : list) {
                        SpellHelper.performImpacts(entity.getWorld(), data1.caster(), entity, entity , new SpellInfo(getSpell(new Identifier(MOD_ID, "nordic_storm")),new Identifier(MOD_ID)), data1.impactContext());
                        SoundHelper.playSound(data1.caster().getWorld(), entity, getSpell(new Identifier(MOD_ID, "nordic_storm")).impact[0].sound);
                    }
                }
            return false;
        });
        */
    }
}
