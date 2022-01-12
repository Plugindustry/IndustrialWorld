package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.handlers.interfaces.BlockBreakHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MainHandler {
    // Return true for events don't need to be cancelled.

    static List<BlockBreakHandler> blockBreakHandlers = new ArrayList<>();

    public static boolean processBlockBreak(BlockBreakEvent event) {
        for (BlockBreakHandler blockBreakHandler : blockBreakHandlers) {
            blockBreakHandler.handleBlockBreak(event);
        }
        return true;
    }

    public static void registerBlockBreakHandler(BlockBreakHandler handler) {
        blockBreakHandlers.add(handler);
    }
}
