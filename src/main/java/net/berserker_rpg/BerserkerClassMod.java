package net.berserker_rpg;

import net.berserker_rpg.client.particle.Particles;
import net.berserker_rpg.config.Default;
import net.berserker_rpg.custom.custom_spells.CustomSpells;
import net.berserker_rpg.effect.Effects;
import net.berserker_rpg.item.BerserkerGroup;
import net.berserker_rpg.item.BerserkerItems;
import net.berserker_rpg.item.armor.Armors;
import net.berserker_rpg.item.weapons.WeaponsRegister;
import net.berserker_rpg.config.EffectsConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.loot.LootConfig;
import net.spell_engine.api.loot.LootHelper;
import net.tinyconfig.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BerserkerClassMod implements ModInitializer {
	public static final String MOD_ID = "berserker_rpg";
	public static final Logger LOGGER = LoggerFactory.getLogger("berserker_rpg");

	public static ConfigManager<ItemConfig> itemConfig = new ConfigManager<ItemConfig>
			("items_v2", Default.itemConfig)
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();

	public static ConfigManager<LootConfig> lootConfig = new ConfigManager<LootConfig>
			("loot_v2", Default.lootConfig)
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.constrain(LootConfig::constrainValues)
			.build();
	public static ConfigManager<EffectsConfig> effectsConfig = new ConfigManager<EffectsConfig>
			("effects_v1", new EffectsConfig())
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();

	private void registerItemGroup() {
		BerserkerGroup.BERSERKER = FabricItemGroup.builder()
				.icon(() -> new ItemStack(Armors.northlingArmorSet.chest.asItem()))
				.displayName(Text.translatable("itemGroup." + MOD_ID + ".general"))
				.build();
		Registry.register(Registries.ITEM_GROUP, BerserkerGroup.BERSERKER_KEY, BerserkerGroup.BERSERKER);
	}

	private void subscribeEvents() {
		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
			LootHelper.configure(id, tableBuilder, BerserkerClassMod.lootConfig.value, BerserkerItems.entries);
		});
	}


	@Override
	public void onInitialize() {
		lootConfig.refresh();
		itemConfig.refresh();
		effectsConfig.refresh();
		BerserkerItems.registerModItems();
		Effects.register();
		Particles.register();
		BerserkerGroup.registerItemGroups();
		CustomSpells.register();
		WeaponsRegister.register(itemConfig.value.weapons);
		Armors.register(itemConfig.value.armor_sets);
		itemConfig.save();
		registerItemGroup();
		subscribeEvents();
	}
}