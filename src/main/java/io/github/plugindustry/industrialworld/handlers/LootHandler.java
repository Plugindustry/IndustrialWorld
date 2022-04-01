package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.handlers.interfaces.BlockBreakHandler;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LootHandler implements BlockBreakHandler {
    private final HashMap<Material, List<Pair<ItemStack, Double>>> blockLootTable = new HashMap<>();
    private final boolean doCreativeLoot = ConfigHandler.getConfig().getBoolean("do_creative_loot");

    @Override
    public boolean handleBlockBreak(@NotNull BlockBreakEvent event) {
        if ((doCreativeLoot || (event.getPlayer().getGameMode() != GameMode.CREATIVE)) && hasBlockLoot(
                event.getBlock().getType()))
            for (Pair<ItemStack, Double> itemPair : getBlockLoot(event.getBlock().getType()))
                if (Math.random() < itemPair.second) event.getBlock().getWorld().dropItem(
                        event.getBlock().getLocation().add(0, 1, 0), itemPair.first);

        return true;
    }

    public void registerBlockLoot(Material material, List<Pair<ItemStack, Double>> lootList) {
        blockLootTable.put(material, lootList);
    }

    private boolean hasBlockLoot(Material material) {
        return blockLootTable.containsKey(material);
    }

    private List<Pair<ItemStack, Double>> getBlockLoot(Material material) {
        return blockLootTable.getOrDefault(material,
                Collections.singletonList(Pair.of(new ItemStack(Material.AIR), 0.0)));
    }
}
