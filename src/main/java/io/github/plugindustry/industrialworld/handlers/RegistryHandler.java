package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.ConstItem;
import io.github.plugindustry.industrialworld.blocks.BlockGrassStack;
import io.github.plugindustry.industrialworld.blocks.BlockHayStack;
import io.github.plugindustry.wheelcore.manager.MainManager;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RegistryHandler {
    public static void vanillaRecipeRegistry() {
        // Custom Vanilla recipes

        // Vanilla recipes removal
        for (String key : ConfigHandler.getRecipeConfig().getStringList("removal"))
            Bukkit.removeRecipe(NamespacedKey.minecraft(key));
    }

    public static void blockRegistry() {
        MainManager.registerBlock("iw:grass_stack", BlockGrassStack.INSTANCE);
        MainManager.registerBlock("iw:hay_stack", BlockHayStack.INSTANCE);
    }

    public static void lootRegistry() {
        LootHandler mainLootHandler = new LootHandler();
        List<Pair<ItemStack, Double>> leavesList = ConfigHandler.getLootPairs("leaves");
        mainLootHandler.registerBlockLoot(Material.OAK_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.ACACIA_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.BIRCH_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.DARK_OAK_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.JUNGLE_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.SPRUCE_LEAVES, leavesList);

        mainLootHandler.registerBlockLoot(Material.GRASS_BLOCK, ConfigHandler.getLootPairs("grass"));

        MainHandler.registerBlockBreakHandler(mainLootHandler);
    }

    public static void doRegistry() {
        blockRegistry();
        ConstItem.init();

        vanillaRecipeRegistry();
        lootRegistry();
    }
}
