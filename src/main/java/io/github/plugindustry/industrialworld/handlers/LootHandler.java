package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.handlers.interfaces.BlockBreakHandler;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LootHandler implements BlockBreakHandler {
    public HashMap<Material, List<Pair<ItemStack, Double>>> blockLootTable;

    public LootHandler() {
        blockLootTable = new HashMap<>();
    }

    @Override
    public boolean handleBlockBreak(BlockBreakEvent event) {
        if (hasBlockLoot(event.getBlock().getType())) {
            for (Pair<ItemStack, Double> itemPair : getBlockLoot(event.getBlock().getType())) {
                if (Math.random() > itemPair.second) {
                    event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), itemPair.first);
                }
            }
        }

        return true;
    }

    public void registerBlockLoot(Material material, List<Pair<ItemStack, Double>> lootList) {
        blockLootTable.put(material, lootList);
    }

    private boolean hasBlockLoot(Material material) {
        return blockLootTable.containsKey(material);
    }

    private List<Pair<ItemStack, Double>> getBlockLoot(Material material) {
        if (!blockLootTable.containsKey(material)) {
            List<Pair<ItemStack, Double>> emptyLootList = new ArrayList<>();
            emptyLootList.add(Pair.of(new ItemStack(Material.AIR), 0.0));
            return emptyLootList;
        }

        return blockLootTable.get(material);
    }
}
