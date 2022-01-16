package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.handlers.interfaces.BlockBreakHandler;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LootHandler implements BlockBreakHandler {
    public HashMap<Material, List<Pair<ItemStack, Double>>> blockLootTable;
    private boolean doCreativeLoot;

    public LootHandler() {
        this.doCreativeLoot = ConfigHandler.getConfig().getBoolean("do_creative_loot");
        blockLootTable = new HashMap<>();
    }

    @Override
    public boolean handleBlockBreak(@NotNull BlockBreakEvent event) {
        if (doCreativeLoot || event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            if (hasBlockLoot(event.getBlock().getType())) {
                for (Pair<ItemStack, Double> itemPair : getBlockLoot(event.getBlock().getType())) {
                    if (Math.random() < itemPair.second) {
                        event.getBlock().getWorld().dropItem(event.getBlock().getLocation().add(0,1,0), itemPair.first);
                    }
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
