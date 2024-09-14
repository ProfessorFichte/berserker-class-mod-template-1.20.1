package net.berserker_rpg.mixin;

import net.berserker_rpg.effect.Effects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.more_rpg_classes.effect.MRPGCEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


import static net.berserker_rpg.BerserkerClassMod.effectsConfig;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(at = @At("HEAD"), method = "onKilledOther")
    public void berserker$onKilledOther(ServerWorld world, LivingEntity other, CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (player instanceof ServerPlayerEntity) {
            if (player.hasStatusEffect(Effects.SOUL_DEVOURER.registryEntry)) {
                if(!player.hasStatusEffect(MRPGCEffects.COLLECTED_SOUL.registryEntry)){
                    player.addStatusEffect(new StatusEffectInstance(MRPGCEffects.COLLECTED_SOUL.registryEntry,400,0,false,false,true));
                    player.heal(other.getMaxHealth()*effectsConfig.value.soul_devourer_heal_on_kill_target_max_health_amount);
                }else{
                    int amp_coll_soul = player.getStatusEffect(MRPGCEffects.COLLECTED_SOUL.registryEntry).getAmplifier();
                    int max_amp = 9;
                    if(amp_coll_soul >= 9){
                        player.addStatusEffect(new StatusEffectInstance(MRPGCEffects.COLLECTED_SOUL.registryEntry,400,amp_coll_soul+1,false,false,true));
                    }
                    player.heal(other.getMaxHealth()*effectsConfig.value.soul_devourer_heal_on_kill_target_max_health_amount);
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "attack")
    public void berserker$attack(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        if (player instanceof ServerPlayerEntity) {
            if (player.hasStatusEffect(Effects.RAGE.registryEntry)) {
                int dura_rage = player.getStatusEffect(Effects.RAGE.registryEntry).getDuration();
                final int amp_rage = player.getStatusEffect(Effects.RAGE.registryEntry).getAmplifier();
                int rage_amplifier_max = effectsConfig.value.rage_max_amplifier_stack - 1;
                if(amp_rage != rage_amplifier_max){
                    player.addStatusEffect(new StatusEffectInstance(Effects.RAGE.registryEntry, dura_rage,amp_rage+1,false,false,true));
                }
            }
        }
    }

}
