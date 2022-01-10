package io.github.plugindustry.industrialworld.handlers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.AbstractMap;
import java.util.HashMap;

public class LootHandler {
    public static HashMap<Material, AbstractMap.SimpleEntry<ItemStack, Double>> blockLootTable = new HashMap<>();

    public static void registerBlockLoot(Material material, ItemStack lootItem, Double probability) {
        blockLootTable.put(material, new AbstractMap.SimpleEntry(lootItem, probability));
    }

    public static boolean hasBlockLoot(Material material) {
        return blockLootTable.containsKey(material);
    }

    public static AbstractMap.SimpleEntry<ItemStack, Double> getBlockLoot(Material material) {
        if (!blockLootTable.containsKey(material)) {
            return new AbstractMap.SimpleEntry(new ItemStack(Material.AIR), 0);
        }
        return blockLootTable.get(material);
    }
}
