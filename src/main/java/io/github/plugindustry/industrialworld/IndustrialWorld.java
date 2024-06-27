package io.github.plugindustry.industrialworld;

import io.github.plugindustry.industrialworld.behavior.BehaviorRegistry;
import io.github.plugindustry.industrialworld.i18n.I18nHelper;
import io.github.plugindustry.industrialworld.item.ConstItems;
import io.github.plugindustry.industrialworld.recipe.RecipeRegisterHelper;
import org.bukkit.plugin.java.JavaPlugin;

public final class IndustrialWorld extends JavaPlugin {
    private static IndustrialWorld INSTANCE;

    public static JavaPlugin getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        getLogger().info("\n|_   _|        | |         | |      (_)     | | | |  | |          | |   | |\n" +
                "  | | _ __   __| |_   _ ___| |_ _ __ _  __ _| | | |  | | ___  _ __| | __| |\n" +
                "  | || '_ \\ / _` | | | / __| __| '__| |/ _` | | | |/\\| |/ _ \\| '__| |/ _` |\n" +
                " _| || | | | (_| | |_| \\__ \\ |_| |  | | (_| | | \\  /\\  / (_) | |  | | (_| |\n" +
                " \\___/_| |_|\\__,_|\\__,_|___/\\__|_|  |_|\\__,_|_|  \\/  \\/ \\___/|_|  |_|\\__,_|\n" +
                "                                                                           ");

        I18nHelper.loadAllLocales();
        ConstItems.initItems();
        RecipeRegisterHelper.registerRecipes();
        BehaviorRegistry.activeBehaviors();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}