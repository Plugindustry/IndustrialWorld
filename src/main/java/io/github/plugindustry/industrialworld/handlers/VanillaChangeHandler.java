package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.handlers.interfaces.BlockBreakHandler;
import io.github.plugindustry.wheelcore.manager.MainManager;
import io.github.plugindustry.wheelcore.manager.PlayerDigHandler;
import io.github.plugindustry.wheelcore.utils.BlockUtil;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

import javax.annotation.Nonnull;

public class VanillaChangeHandler implements BlockBreakHandler {
    public void init() {
        PlayerDigHandler.destroySpeedModifiers.add(new PlayerDigHandler.DestroySpeedModifier() {
            @Override
            public Pair<Boolean, Float> modify(@Nonnull Block block, @Nonnull Player player, float v) {
                if (MainManager.getBlockInstance(block.getLocation()) == null && block.getType().name()
                                                                                      .endsWith(
                                                                                              "_LOG") && !BlockUtil.isPreferredTool(
                        block, player.getInventory().getItemInMainHand())) return Pair.of(true, v * 3f / 10f);
                else return Pair.of(true, v);
            }
        });
    }

    @Override
    public boolean handleBlockBreak(BlockBreakEvent event) {
        if (MainManager.getBlockInstance(event.getBlock().getLocation()) == null && event.getBlock().getType()
                                                                                         .name().endsWith(
                        "_LOG") && !BlockUtil.isPreferredTool(event.getBlock(),
                event.getPlayer().getInventory().getItemInMainHand())) {
            event.setDropItems(false);
        }
        return true;
    }
}
