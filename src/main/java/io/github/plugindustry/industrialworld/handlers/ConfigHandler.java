package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.wheelcore.manager.ItemMapping;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

public class ConfigHandler {
    private static YamlConfiguration config;
    private static YamlConfiguration lootConfig;
    private static YamlConfiguration recipeConfig;

    public static @NotNull YamlConfiguration loadConfiguration(String path) {
        File dataFolder = IndustrialWorld.instance.getDataFolder();

        File configFile = new File(dataFolder, path);
        if (!(configFile.isFile())) {
            if (path.equals("config.yml")) IndustrialWorld.instance.saveDefaultConfig();
            else IndustrialWorld.instance.saveResource(path, true);
        }

        return YamlConfiguration.loadConfiguration(configFile);
    }

    public static void init() {
        File dataFolder = IndustrialWorld.instance.getDataFolder();

        if (!dataFolder.isDirectory()) dataFolder.mkdirs();

        config = loadConfiguration("config.yml");
        lootConfig = loadConfiguration("config/loot.yml");
        recipeConfig = loadConfiguration("config/recipe.yml");
    }

    public static @NotNull List<Pair<ItemStack, Double>> getLootPairs(String key) {
        List<Pair<ItemStack, Double>> pairs = new ArrayList<>();
        for (Object o : Objects.requireNonNull(lootConfig.getList(key, new ArrayList<>()))) {
            LinkedHashMap<?, ?> map = (LinkedHashMap<?, ?>) o;

            String type = (String) Objects.requireNonNull(map.get("type"));
            String id = (String) Objects.requireNonNull(map.get("id"));
            Integer amount = (Integer) map.get("amount");
            Double probability = (Double) map.get("probability");

            ItemStack itemStack;

            if (type.equals("minecraft")) {
                itemStack = new ItemStack(Objects.requireNonNull(Material.matchMaterial(id)), amount);
                pairs.add(Pair.of(itemStack, probability));
            } else if (type.equals("wheelcore")) {
                if (ItemMapping.isItemExists(id)) {
                    itemStack = ItemMapping.get(id);
                    itemStack.setAmount(amount);
                    pairs.add(Pair.of(itemStack, probability));
                } else throw new IllegalArgumentException();
            }
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
