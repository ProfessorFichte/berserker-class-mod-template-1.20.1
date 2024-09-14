package net.berserker_rpg.item.armor;

import net.berserker_rpg.BerserkerClassMod;
import net.berserker_rpg.item.BerserkerGroup;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
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

public class Armors {

    private static final Supplier<Ingredient> WILDLING_INGREDIENTS = () -> Ingredient.ofItems(
            Items.LEATHER, Items.CHAIN, MRPGCItems.WOLF_FUR
    );
    private static final Supplier<Ingredient> NORTHLING_INGREDIENTS = () -> Ingredient.ofItems(
            Items.IRON_INGOT, Items.CHAIN, MRPGCItems.WOLF_FUR
    );

    public static final float berserker_atkspeed_T1 = 0.02F;
    public static final float berserker_atkspeed_T2 = 0.04F;
    public static final float berserker_rage_T1 = 0.025F;
    public static final float berserker_rage_T2 = 0.05F;
    public static final float berserker_atkdamage_T2 = 0.03F;


    public static final ArrayList<Armor.Entry> entries = new ArrayList<>();
    private static Armor.Entry create(Armor.CustomMaterial material, ItemConfig.ArmorSet defaults) {
        return new Armor.Entry(material, null, defaults);
    }


    public static final Armor.Set wildlingArmorSet =
            create(
                    new Armor.CustomMaterial(
                            "wildling",
                            10,
                            9,
                            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
                            WILDLING_INGREDIENTS
                    ),
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
                            new ItemConfig.ArmorSet.Piece(2)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T1),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T1 )
                                    )),
                            new ItemConfig.ArmorSet.Piece(1)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T1),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T1 )
                                    ))
                    ))   .bundle(material -> new Armor.Set<>(BerserkerClassMod.MOD_ID,
                            new WildlingArmor(material, ArmorItem.Type.HELMET, new Item.Settings()),
                            new WildlingArmor(material, ArmorItem.Type.CHESTPLATE, new Item.Settings()),
                            new WildlingArmor(material, ArmorItem.Type.LEGGINGS, new Item.Settings()),
                            new WildlingArmor(material, ArmorItem.Type.BOOTS, new Item.Settings())
                    ))
                    .put(entries).armorSet()
                    .allowSpellPowerEnchanting(false);

    public static final Armor.Set northlingArmorSet =
            create(
                    new Armor.CustomMaterial(
                            "northling",
                            20,
                            10,
                            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
                            NORTHLING_INGREDIENTS
                    ),
                    ItemConfig.ArmorSet.with(
                            new ItemConfig.ArmorSet.Piece(2)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T2),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T2 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T2)
                                    )),
                            new ItemConfig.ArmorSet.Piece(5)
                                    .addAll(List.of(
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_speed")),berserker_atkspeed_T2),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("more_rpg_classes:rage_modifier")),berserker_rage_T2 ),
                                            ItemConfig.Attribute.multiply(Objects.requireNonNull(Identifier.tryParse("minecraft:generic.attack_damage")),berserker_atkdamage_T2)
                                    )),
                            new ItemConfig.ArmorSet.Piece(3)
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
                    ))   .bundle(material -> new Armor.Set<>(BerserkerClassMod.MOD_ID,
                            new NorthlingArmor(material, ArmorItem.Type.HELMET, new Item.Settings()),
                            new NorthlingArmor(material, ArmorItem.Type.CHESTPLATE, new Item.Settings()),
                            new NorthlingArmor(material, ArmorItem.Type.LEGGINGS, new Item.Settings()),
                            new NorthlingArmor(material, ArmorItem.Type.BOOTS, new Item.Settings())
                    ))
                    .put(entries).armorSet()
                    .allowSpellPowerEnchanting(false);

    public static void register(Map<String, ItemConfig.ArmorSet> configs) {
        Armor.register(configs, entries, BerserkerGroup.BERSERKER_KEY);
    }
}