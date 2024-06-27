package io.github.plugindustry.industrialworld.behavior;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.wheelcore.manager.ItemMapping;
import io.github.plugindustry.wheelcore.manager.PlayerDigHandler;
import io.github.plugindustry.wheelcore.utils.BlockUtil;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public class WoodChopBehavior extends Behavior {
    private static WoodChopBehavior INSTANCE;

    private WoodChopBehavior() {
    }

    public static Behavior getInstance() {
        if (INSTANCE == null) INSTANCE = new WoodChopBehavior();
        return INSTANCE;
    }

    private static boolean isLog(Material type) {
        return ItemMapping.getVanillaOreDict(type).contains("log");
    }

    private static boolean isStrippedLog(Material type) {
        return ItemMapping.getVanillaOreDict(type).contains("stripped_log");
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

        PlayerDigHandler.destroySpeedModifiers.add(new PlayerDigHandler.DestroySpeedModifier() {
            @NotNull
            @Override
            public Pair<Boolean, Float> modify(@NotNull Block block, @NotNull Player player, float v) {
                if (isStrippedLog(block.getType())) return Pair.of(true, v * 0.3f);
                else return Pair.of(true, v);
            }
        });
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onBlockBreak(BlockBreakEvent event) {
                if (isStrippedLog(event.getBlock().getType())) {
                    event.setDropItems(false);
                    event.getBlock().getWorld().dropItem(event.getBlock().getLocation(),
                            new ItemStack(getPlankMaterial(event.getBlock().getType()), 2));
                }
            }

            private Material getPlankMaterial(Material material) {
                String materialName = material.name().replaceFirst("STRIPPED_", "");
                String suffix = "_LOG";
                String plankName = materialName.substring(0, materialName.length() - suffix.length()) + "_PLANKS";
                return Material.getMaterial(plankName);
            }
        }, IndustrialWorld.getInstance());
        for (Material type : Material.values()) {
            if (!type.isItem()) continue;
            if (type.name().endsWith("_PLANKS")) {
                Bukkit.removeRecipe(NamespacedKey.minecraft(type.name().toLowerCase()));
            }
        }
    }
}