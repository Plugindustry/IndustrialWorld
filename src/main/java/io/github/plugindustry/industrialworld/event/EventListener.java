package io.github.plugindustry.industrialworld.event;

import io.github.plugindustry.industrialworld.handlers.MainHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!event.isCancelled()) {
            event.setCancelled(!MainHandler.processBlockBreak(event));
        }
    }
}
