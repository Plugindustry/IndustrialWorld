package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.AbstractMap;

public class RegistryHandler {
    public static void vanillaRecipeRegistry() {

    }

    public static void lootRegistry() {
        LootHandler mainLootHandler = new LootHandler();
        mainLootHandler.registerBlockLoot(Material.OAK_LEAVES, ConfigHandler.getLootPairs("leaves"));

        MainHandler.registerBlockBreakHandler(mainLootHandler);
    }

    public static void doRegistry() {
        vanillaRecipeRegistry();
        lootRegistry();
    }
}
