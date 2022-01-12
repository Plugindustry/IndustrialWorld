package io.github.plugindustry.industrialworld.handlers.interfaces;

import org.bukkit.event.block.BlockBreakEvent;

public interface BlockBreakHandler extends AbstractHandler {
    boolean handleBlockBreak(BlockBreakEvent event);
}
