package io.github.plugindustry.industrialworld.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class RegistryHandler {
    public static void vanillaRecipeRegistry() {
        // Custom Vanilla recipes

        // Vanilla recipes removal
        for (String key : ConfigHandler.getRecipeConfig().getStringList("removal")) {
            Bukkit.removeRecipe(NamespacedKey.minecraft(key));
        }
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
