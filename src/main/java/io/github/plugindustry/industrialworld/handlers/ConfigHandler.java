package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConfigHandler {
    private static YamlConfiguration config;
    private static YamlConfiguration lootConfig;
    private static YamlConfiguration recipeConfig;

    public static @NotNull YamlConfiguration loadConfiguration(String path) {
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
        recipeConfig = loadConfiguration("config/recipe.yml");
    }

    public static @NotNull
    List<Pair<ItemStack, Double>> getLootPairs(String key) {
        List<Pair<ItemStack, Double>> pairs = new ArrayList<>();
        for (Object o : Objects.requireNonNull(lootConfig.getList(key, new ArrayList<>()))) {
            ConfigurationSection cs = (ConfigurationSection) o;
            pairs.add(Pair.of(new ItemStack(Objects.requireNonNull(Material.matchMaterial(Objects.requireNonNull(cs.getString("type")))), cs.getInt("amount")), cs.getDouble("probability")));
        }

        return pairs;
    }

    public static YamlConfiguration getConfig() {
        return config;
    }

    public static YamlConfiguration getRecipeConfig() {
        return recipeConfig;
    }
}
