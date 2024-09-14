package net.berserker_rpg.item.weapons;

import net.berserker_rpg.item.BerserkerGroup;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.weapon.SpellSwordItem;
import net.spell_engine.api.item.weapon.Weapon;
import net.spell_power.api.SpellSchools;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;

public class WeaponsRegister {
    public static final ArrayList<Weapon.Entry> entries = new ArrayList<>();
    private static Weapon.Entry entry(String name, Weapon.CustomMaterial material, Weapon.Factory factory, ItemConfig.Weapon defaults, boolean fireproof) {
        return entry(null, name, material, factory, defaults, fireproof);
    }

    private static Weapon.Entry entry(String requiredMod, String name, Weapon.CustomMaterial material, Weapon.Factory factory, ItemConfig.Weapon defaults, boolean fireproof) {
        var entry = new Weapon.Entry(MOD_ID, name, material, factory, defaults, requiredMod);
        if (entry.isRequiredModInstalled()) {
            entries.add(entry);
        }
        return entry;
    }

    private static Supplier<Ingredient> ingredient(String idString, boolean requirement, Item fallback) {
        var id = Identifier.of(idString);
        if (requirement) {
            return () -> {
                return Ingredient.ofItems(fallback);
            };
        } else {
            return () -> {
                var item = Registries.ITEM.get(id);
                var ingredient = item != null ? item : fallback;
                return Ingredient.ofItems(ingredient);
            };
        }
    }

    public static float berserker_axe_attackSpeed = -3.1f;

    //BERSERKER-AXE
    private static Weapon.Entry berserker_axes(String name, Weapon.CustomMaterial material, float damage, boolean fireproof) {
        return entry(name, material, SpellSwordItem::new, new ItemConfig.Weapon(damage, berserker_axe_attackSpeed), fireproof);
    }
    private static Weapon.Entry frozen_berserker_axe(String name, Weapon.CustomMaterial material, float damage, boolean fireproof) {
        return entry(name, material, FrozenBerserkerAxeItem::new, new ItemConfig.Weapon(damage, berserker_axe_attackSpeed), fireproof);
    }


    public static final Weapon.Entry flint_berserker_axe = berserker_axes("flint_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.WOOD, () -> Ingredient.ofItems(Items.FLINT)), 7.0F, false)
            ;
    public static final Weapon.Entry stone_berserker_axe = berserker_axes("stone_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.STONE, () -> Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS)), 9.0F, false)
            .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.01F))
            ;
    public static final Weapon.Entry iron_berserker_axe = berserker_axes("iron_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.IRON, () -> Ingredient.ofItems(Items.IRON_INGOT)), 9.0F, false)
            .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.02F))
            ;
    public static final Weapon.Entry golden_berserker_axe = berserker_axes("golden_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.GOLD, () -> Ingredient.ofItems(Items.GOLD_INGOT)), 7.0F, false)
            .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.02F))
            ;
    public static final Weapon.Entry diamond_berserker_axe = berserker_axes("diamond_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.DIAMOND, () -> Ingredient.ofItems(Items.DIAMOND)), 10.5F, false)
            .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.035F))
            ;
    public static final Weapon.Entry netherite_berserker_axe = berserker_axes("netherite_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.NETHERITE_INGOT)), 12.0F, true)
            .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.05F))
            ;
    public static final Weapon.Entry frozen_berserker_axe = frozen_berserker_axe("frozen_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.DIAMOND)),11.5F, true)
            .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.075F))
            .attribute(ItemConfig.Attribute.bonus(SpellSchools.FROST.id, 3))
            ;
    public static final Weapon.Entry thunder_berserker_axe = berserker_axes("thunder_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.DIAMOND)),11.5F, true)
            .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.075F))
            .attribute(ItemConfig.Attribute.bonus(SpellSchools.LIGHTNING.id, 3))
            ;
    public static final Weapon.Entry soul_berserker_axe = berserker_axes("soul_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.NETHERITE_INGOT)),12.5F, true)
            .attribute(ItemConfig.Attribute.bonus(SpellSchools.SOUL.id, 4))
            .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:lifesteal_modifier")),0.10F))
            .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.10F));
            ;

    private static final String BETTER_END = "betterend";
    private static final String BETTER_NETHER = "betternether";
    //Registration
    public static void register(Map<String, ItemConfig.Weapon> configs) {
        if(FabricLoader.getInstance().isModLoaded(BETTER_NETHER)){
            var repair = ingredient("betternether:nether_ruby", FabricLoader.getInstance().isModLoaded(BETTER_NETHER), Items.NETHERITE_INGOT);
            berserker_axes("ruby_berserker_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, repair),15.0F, true)
                    .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.10F));
        }
        if(FabricLoader.getInstance().isModLoaded(BETTER_END)){
            var repair = ingredient("betterend:aeternium_ingot", FabricLoader.getInstance().isModLoaded(BETTER_END), Items.NETHERITE_INGOT);
            berserker_axes("aeternium_berserker_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, repair),15.0F, true)
                    .attribute(ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),0.10F));
        }
        Weapon.register(configs, entries, BerserkerGroup.BERSERKER_KEY);
    }
}
