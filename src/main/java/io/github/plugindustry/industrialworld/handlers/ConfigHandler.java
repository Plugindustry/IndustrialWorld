package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {
    private static YamlConfiguration config;
    private static YamlConfiguration lootConfig;

    public static YamlConfiguration loadConfiguration(String path) {
        File dataFolder = IndustrialWorld.instance.getDataFolder();

        File config_yml = new File(dataFolder, path);
        if (!(config_yml.isFile())) {
            IndustrialWorld.instance.saveDefaultConfig();
        }

        return YamlConfiguration.loadConfiguration(config_yml);
    }

    public static void init() {
        File dataFolder = IndustrialWorld.instance.getDataFolder();

        if (!dataFolder.isDirectory()) {
            dataFolder.mkdirs();
        }

        config = loadConfiguration("config.yml");
        lootConfig = loadConfiguration("config/loot.yml");
    }

    public static List<Pair<ItemStack, Double>> getLootPairs(String key) {
        // TODO: load loot pairs from configuration

        return new ArrayList<>();
    }

    public static YamlConfiguration getConfig() {
        return config;
    }
}
