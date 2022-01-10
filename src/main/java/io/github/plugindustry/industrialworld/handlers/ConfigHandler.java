package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

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

    public static double getLootProbability(String key) {
        return lootConfig.getDouble(key);
    }

    public static YamlConfiguration getConfig() {
        return config;
    }
}
