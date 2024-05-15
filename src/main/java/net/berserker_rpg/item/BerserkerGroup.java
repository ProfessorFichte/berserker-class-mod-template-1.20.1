package net.berserker_rpg.item;

import net.berserker_rpg.BerserkerClassMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class BerserkerGroup {
    public static Identifier ID = new Identifier(BerserkerClassMod.MOD_ID, "generic");
    public static RegistryKey<ItemGroup> BERSERKER_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(),new Identifier(BerserkerClassMod.MOD_ID,"generic"));
    public static ItemGroup BERSERKER;

    public static void registerItemGroups() {
        BerserkerClassMod.LOGGER.info("Registering Item Groups for " + BerserkerClassMod.MOD_ID);
    }
}