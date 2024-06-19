package net.berserker_rpg.item.weapons;

import net.berserker_rpg.BerserkerClassMod;
import net.berserker_rpg.item.BerserkerGroup;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.weapon.Weapon;
import net.spell_power.api.SpellPowerMechanics;
import net.spell_power.api.SpellSchools;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;

public class WeaponsRegister {
    public static final ArrayList<Weapon.Entry> entries = new ArrayList<>();

    private static Weapon.Entry entry(String name, Weapon.CustomMaterial material, Item item, ItemConfig.Weapon defaults) {
        return entry(null, name, material, item, defaults);
    }

    private static Weapon.Entry entry(String requiredMod, String name, Weapon.CustomMaterial material, Item item, ItemConfig.Weapon defaults) {
        var entry = new Weapon.Entry(BerserkerClassMod.MOD_ID, name, material, item, defaults, null);
        if (entry.isRequiredModInstalled()) {
            entries.add(entry);
        }
        return entry;
    }

    private static Supplier<Ingredient> ingredient(String idString) {
        return ingredient(idString, Items.DIAMOND);
    }

    private static Supplier<Ingredient> ingredient(String idString, Item fallback) {
        var id = new Identifier(idString);
        return () -> {
            var item = Registries.ITEM.get(id);
            var ingredient = item != null ? item : fallback;
            return Ingredient.ofItems(ingredient);
        };
    }

    public static float berserker_axe_attackSpeed = -3.1f;

    //BERSERKER-AXE
    private static Weapon.Entry berserker_axes(String name, Weapon.CustomMaterial material, float damage) {
        return berserker_axes(null, name, material, damage);}
    private static Weapon.Entry berserker_axes(String requiredMod, String name, Weapon.CustomMaterial material, float damage) {
        var settings = new Item.Settings();
        var item = new BerserkerAxeItem(material,settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(damage, berserker_axe_attackSpeed));}

    private static Weapon.Entry special_berserker_axe_1(String name, Weapon.CustomMaterial material, float damage) {
        return special_berserker_axe_1(null, name, material, damage);}
    private static Weapon.Entry special_berserker_axe_1(String requiredMod, String name, Weapon.CustomMaterial material, float damage) {
        var settings = new Item.Settings();
        var item = new FrozenBerserkerAxeItem(material,settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(damage, berserker_axe_attackSpeed));}

    private static Weapon.Entry special_berserker_axe_2(String name, Weapon.CustomMaterial material, float damage) {
        return special_berserker_axe_2(null, name, material, damage);}
    private static Weapon.Entry special_berserker_axe_2(String requiredMod, String name, Weapon.CustomMaterial material, float damage) {
        var settings = new Item.Settings();
        var item = new ThunderBerserkerAxeItem(material,settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(damage, berserker_axe_attackSpeed));}

    private static Weapon.Entry special_berserker_axe_3(String name, Weapon.CustomMaterial material, float damage) {
        return special_berserker_axe_3(null, name, material, damage);}
    private static Weapon.Entry special_berserker_axe_3(String requiredMod, String name, Weapon.CustomMaterial material, float damage) {
        var settings = new Item.Settings();
        var item = new SoulBerserkerAxeItem(material,settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(damage, berserker_axe_attackSpeed));}

    public static final Weapon.Entry flint_berserker_axe = berserker_axes("flint_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.WOOD, () -> Ingredient.ofItems(Items.FLINT)), 7.0F)
            ;
    public static final Weapon.Entry stone_berserker_axe = berserker_axes("stone_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.STONE, () -> Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS)), 9.0F)
            ;
    public static final Weapon.Entry iron_berserker_axe = berserker_axes("iron_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.IRON, () -> Ingredient.ofItems(Items.IRON_INGOT)), 9.0F)
            ;
    public static final Weapon.Entry golden_berserker_axe = berserker_axes("golden_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.GOLD, () -> Ingredient.ofItems(Items.GOLD_INGOT)), 7.0F)
            ;
    public static final Weapon.Entry diamond_berserker_axe = berserker_axes("diamond_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.DIAMOND, () -> Ingredient.ofItems(Items.DIAMOND)), 10.5F)
            ;
    public static final Weapon.Entry netherite_berserker_axe = berserker_axes("netherite_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.NETHERITE_INGOT)), 12.0F)
            ;
    public static final Weapon.Entry frozen_berserker_axe = special_berserker_axe_1("frozen_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.DIAMOND)),11.5F)
            .attribute(ItemConfig.Attribute.multiply(SpellPowerMechanics.CRITICAL_CHANCE.id, 0.04F))
            .attribute(ItemConfig.Attribute.bonus(SpellSchools.FROST.id, 3))
            ;
    public static final Weapon.Entry thunder_berserker_axe = special_berserker_axe_2("thunder_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.DIAMOND)),11.5F)
            .attribute(ItemConfig.Attribute.multiply(SpellPowerMechanics.CRITICAL_DAMAGE.id, 0.1F))
            .attribute(ItemConfig.Attribute.bonus(SpellSchools.LIGHTNING.id, 3))
            ;
    public static final Weapon.Entry soul_berserker_axe = special_berserker_axe_3("soul_berserker_axe",
            Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.NETHERITE_INGOT)),12.5F)
            .attribute(ItemConfig.Attribute.bonus(SpellSchools.SOUL.id, 4))
            ;

    //Registration
    public static void register(Map<String, ItemConfig.Weapon> configs) {
        Weapon.register(configs, entries, BerserkerGroup.BERSERKER_KEY);
    }
}
