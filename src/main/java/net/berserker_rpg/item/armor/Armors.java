package net.berserker_rpg.item.armor;

import net.berserker_rpg.item.BerserkerGroup;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.more_rpg_classes.item.MRPGCItems;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.armor.Armor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import static net.berserker_rpg.BerserkerClassMod.MOD_ID;

public class Armors {
    private static final Supplier<Ingredient> WILDLING_INGREDIENTS = () -> Ingredient.ofItems(
            Items.LEATHER, Items.CHAIN, MRPGCItems.WOLF_FUR
    );
    private static final Supplier<Ingredient> NORTHLING_INGREDIENTS = () -> Ingredient.ofItems(
            Items.IRON_INGOT, Items.CHAIN, MRPGCItems.POLAR_BEAR_FUR
    );

    public static final float berserker_atkspeed_T1 = 0.02F;
    public static final float berserker_rage_T1 = 0.025F;
    public static final float berserker_atkspeed_T2 = 0.02F;
    public static final float berserker_rage_T2 = 0.05F;
    public static final float berserker_atkdamage_T2 = 0.04F;
    public static final float berserker_atkspeed_T3 = 0.02F;
    public static final float berserker_rage_T3 = 0.075F;
    public static final float berserker_atkdamage_T3 = 0.05F;


    public static RegistryEntry<ArmorMaterial> material(String name,
                                                        int protectionHead, int protectionChest, int protectionLegs, int protectionFeet,
                                                        int enchantability, RegistryEntry<SoundEvent> equipSound, Supplier<Ingredient> repairIngredient) {
        var material = new ArmorMaterial(
                Map.of(
                        ArmorItem.Type.HELMET, protectionHead,
                        ArmorItem.Type.CHESTPLATE, protectionChest,
                        ArmorItem.Type.LEGGINGS, protectionLegs,
                        ArmorItem.Type.BOOTS, protectionFeet),
                enchantability, equipSound, repairIngredient,
                List.of(new ArmorMaterial.Layer(Identifier.of(MOD_ID, name))),
                0,0
        );
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(MOD_ID, name), material);
    }

    public static RegistryEntry<ArmorMaterial> material_wildling = material(
            "wildling",
            1, 3, 3, 1,
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, WILDLING_INGREDIENTS);

    public static RegistryEntry<ArmorMaterial> material_northling = material(
            "northling",
            2, 4, 4, 2,
            11,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, NORTHLING_INGREDIENTS);
    public static RegistryEntry<ArmorMaterial> material_netherite_northling = material(
            "netherite_northling",
            2, 4, 4, 2,
            20,
            SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, () -> { return Ingredient.ofItems(Items.NETHERITE_INGOT); });

    public static final ArrayList<Armor.Entry> entries = new ArrayList<>();
    private static Armor.Entry create(RegistryEntry<ArmorMaterial> material, Identifier id, int durability, Armor.Set.ItemFactory factory, ItemConfig.ArmorSet defaults) {
        var entry = Armor.Entry.create(
                material,
                id,
                durability,
                factory,
                defaults);
        entries.add(entry);
        return entry;
    }

    public static final Armor.Set wildlingArmorSet =
            create(
                    material_wildling,
                    Identifier.of(MOD_ID, "wildling"),
                    15,
                    WildlingArmor::new,
                    ItemConfig.ArmorSet.with(
                            new ItemConfig.ArmorSet.Piece(1)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T1),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T1 )
                                    )),
                            new ItemConfig.ArmorSet.Piece(3)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T1),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T1 )
                                    )),
                            new ItemConfig.ArmorSet.Piece(3)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T1),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T1 )
                                    )),
                            new ItemConfig.ArmorSet.Piece(1)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T1),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T1 )
                                    ))
                    ))
                    .armorSet();

    public static final Armor.Set northlingArmorSet =
            create(
                    material_northling,
                    Identifier.of(MOD_ID, "northling"),
                    25,
                    NorthlingArmor::new,
                    ItemConfig.ArmorSet.with(
                            new ItemConfig.ArmorSet.Piece(2)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T2),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T2 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T2)
                                    )),
                            new ItemConfig.ArmorSet.Piece(4)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T2),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T2 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T2)
                                    )),
                            new ItemConfig.ArmorSet.Piece(4)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T2),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T2 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T2)
                                    )),
                            new ItemConfig.ArmorSet.Piece(2)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T2),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T2 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T2)
                                    ))
                    ))
                    .armorSet();

    public static final Armor.Set netheriteNorthlingArmorSet =
            create(
                    material_netherite_northling,
                    Identifier.of(MOD_ID, "netherite_northling"),
                    30,
                    NorthlingArmor::new,
                    ItemConfig.ArmorSet.with(
                            new ItemConfig.ArmorSet.Piece(2)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T3),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T3 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T3)
                                    )),
                            new ItemConfig.ArmorSet.Piece(4)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T3),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T3 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T3)
                                    )),
                            new ItemConfig.ArmorSet.Piece(4)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T3),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T3 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T3)
                                    )),
                            new ItemConfig.ArmorSet.Piece(2)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T3),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T3 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T3)
                                    ))
                    ))
                    .armorSet();

    public static void register(Map<String, ItemConfig.ArmorSet> configs) {
        Armor.register(configs, entries, BerserkerGroup.BERSERKER_KEY);
    }
}