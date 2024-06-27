package io.github.plugindustry.industrialworld.behavior;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.wheelcore.manager.ItemMapping;
import io.github.plugindustry.wheelcore.manager.PlayerDigHandler;
import io.github.plugindustry.wheelcore.utils.BlockUtil;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;

public class WoodChopBehavior extends Behavior {
    private static WoodChopBehavior INSTANCE;

    private WoodChopBehavior() {}

    public static Behavior getInstance() {
        if (INSTANCE == null) INSTANCE = new WoodChopBehavior();
        return INSTANCE;
    }

    private static boolean isLog(Material type) {
        return ItemMapping.getVanillaOreDict(type).contains("log");
    }

    @Override
    public void onActivate() {
        PlayerDigHandler.destroySpeedModifiers.add(new PlayerDigHandler.DestroySpeedModifier() {
            @NotNull
            @Override
            public Pair<Boolean, Float> modify(@NotNull Block block, @NotNull Player player, float v) {
                if (isLog(block.getType())) return Pair.of(true,
                        BlockUtil.isPreferredTool(block, player.getInventory().getItemInMainHand()) ? v : v * 0.3f);
                else return Pair.of(true, v);
            }
        });
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onBlockBreak(BlockBreakEvent event) {
                if (isLog(event.getBlock().getType()) &&
                    !BlockUtil.isPreferredTool(event.getBlock(), event.getPlayer().getInventory().getItemInMainHand()))
                    event.setDropItems(false);
            }
        }, IndustrialWorld.getInstance());
    }
}