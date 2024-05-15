package net.berserker_rpg.item.armor;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import mod.azure.azurelibarmor.animatable.GeoItem;
import mod.azure.azurelibarmor.animatable.client.RenderProvider;
import net.berserker_rpg.client.armor.berserker.NorthlingArmorRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Util;
import net.more_rpg_classes.entity.attribute.MRPGCEntityAttributes;
import net.spell_engine.api.item.armor.Armor;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.UUID;
import java.util.function.Consumer;

public class NorthlingArmor extends ModArmorItem implements GeoItem {
    public NorthlingArmor(Armor.CustomMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }
    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private NorthlingArmorRenderer renderer;

            @Override
            public @NotNull BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, BipedEntityModel<LivingEntity> original) {
                if (renderer == null)
                    renderer = new NorthlingArmorRenderer();

                renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);
                return renderer;
            }
        });
    }

    private Multimap<EntityAttribute, EntityAttributeModifier> attributes;
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        if (attributes == null) {
            return super.getAttributeModifiers(slot);
        }
        return slot == this.type.getEquipmentSlot() ? this.attributes : super.getAttributeModifiers(slot);
    }
    private static final EnumMap MODIFIERS = (EnumMap) Util.make(new EnumMap(Type.class), (uuidMap) -> {
        uuidMap.put(Type.BOOTS, UUID.fromString("9e27d6b9-9f6b-4c85-9c70-bf5990b4b7eb"));
        uuidMap.put(Type.LEGGINGS, UUID.fromString("5144b570-2657-42a5-86ab-4f6f3dfcfff3"));
        uuidMap.put(Type.CHESTPLATE, UUID.fromString("5144b570-2657-42a5-86ab-4f6f3dfcfff3"));
        uuidMap.put(Type.HELMET, UUID.fromString("5144b570-2657-42a5-86ab-4f6f3dfcfff3"));
    });

    @Override
    public void setAttributes(Multimap<EntityAttribute, EntityAttributeModifier> attributes) {
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.putAll(attributes);
        UUID uuid = (UUID)MODIFIERS.get(this.type);
        builder.put(MRPGCEntityAttributes.RAGE_MODIFIER,new EntityAttributeModifier(uuid,"rage",0.025, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        this.attributes = builder.build();
    }
}
