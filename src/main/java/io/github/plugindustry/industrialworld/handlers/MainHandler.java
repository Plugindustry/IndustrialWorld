package io.github.plugindustry.industrialworld.handlers;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class MainHandler {
    // Return true for events don't need to be cancelled.

    public static boolean processBlockBreak(BlockBreakEvent event) {
        if (LootHandler.hasBlockLoot(event.getBlock().getType())) {
            if (Math.random() > LootHandler.getBlockLoot(event.getBlock().getType()).getValue()) {
                event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), LootHandler.getBlockLoot(event.getBlock().getType()).getKey());
            }
        }
        return true;
    }
}
