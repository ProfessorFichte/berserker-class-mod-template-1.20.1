package net.berserker_rpg.item.armor;

import mod.azure.azurelibarmor.animatable.GeoItem;
import mod.azure.azurelibarmor.animatable.client.RenderProvider;
import net.berserker_rpg.client.armor.berserker.NorthlingArmorRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.spell_engine.api.item.armor.Armor;
import org.jetbrains.annotations.NotNull;

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

}
