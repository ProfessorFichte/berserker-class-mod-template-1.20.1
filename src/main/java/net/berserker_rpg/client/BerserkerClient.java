package net.berserker_rpg.client;

import mod.azure.azurelibarmor.rewrite.render.armor.AzArmorRenderer;
import mod.azure.azurelibarmor.rewrite.render.armor.AzArmorRendererRegistry;
import net.berserker_rpg.client.armor.berserker.NorthlingArmorRenderer;
import net.berserker_rpg.client.armor.berserker.WildlingArmorRenderer;
import net.berserker_rpg.client.effect.RageParticles;
import net.berserker_rpg.client.effect.RageRenderer;
import net.berserker_rpg.client.effect.SoulDevourerRenderer;
import net.berserker_rpg.client.particle.Particles;
import net.berserker_rpg.client.particle.SmallThunderParticle;
import net.berserker_rpg.effect.Effects;
import net.berserker_rpg.item.armor.Armors;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.*;
import net.minecraft.util.Identifier;
import net.spell_engine.api.effect.CustomModelStatusEffect;
import net.spell_engine.api.effect.CustomParticleStatusEffect;
import net.spell_engine.api.item.armor.Armor;
import net.spell_engine.api.render.CustomModels;

import java.util.List;
import java.util.function.Supplier;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;
@Environment(EnvType.CLIENT)
public class BerserkerClient implements ClientModInitializer {
    public void  onInitializeClient(){
        CustomModels.registerModelIds(List.of(
                Identifier.of(MOD_ID, "projectile/lightning_bolt"),
                RageRenderer.modelIdRage,
                SoulDevourerRenderer.modelId
        ));
        ParticleFactoryRegistry.getInstance().register(Particles.RAGE_PAR, DamageParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(Particles.SMALL_THUNDER, SmallThunderParticle.Factory::new);

        registerArmorRenderer(Armors.wildlingArmorSet, WildlingArmorRenderer::wildling);
        registerArmorRenderer(Armors.northlingArmorSet, NorthlingArmorRenderer::northling);
        registerArmorRenderer(Armors.netheriteNorthlingArmorSet, NorthlingArmorRenderer::netherite_northling);

        CustomParticleStatusEffect.register(Effects.RAGE.effect, new RageParticles(1));
        CustomModelStatusEffect.register(Effects.RAGE.effect, new RageRenderer());
        CustomModelStatusEffect.register(Effects.SOUL_DEVOURER.effect, new SoulDevourerRenderer());
    }

    private static void registerArmorRenderer(Armor.Set set, Supplier<AzArmorRenderer> armorRendererSupplier) {
        AzArmorRendererRegistry.register(armorRendererSupplier, set.head, set.chest, set.legs, set.feet);
    }

}
