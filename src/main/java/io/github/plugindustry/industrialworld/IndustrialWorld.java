package io.github.plugindustry.industrialworld;

import io.github.plugindustry.industrialworld.event.EventListener;
import io.github.plugindustry.industrialworld.handlers.ConfigHandler;
import io.github.plugindustry.industrialworld.handlers.RegistryHandler;
import io.github.plugindustry.industrialworld.utils.I18nHelper;
import org.bukkit.plugin.java.JavaPlugin;

public final class IndustrialWorld extends JavaPlugin {
    public static IndustrialWorld instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // IndustrialWorld logo
        getLogger().info("""

                |_   _|        | |         | |      (_)     | | | |  | |          | |   | |
                  | | _ __   __| |_   _ ___| |_ _ __ _  __ _| | | |  | | ___  _ __| | __| |
                  | || '_ \\ / _` | | | / __| __| '__| |/ _` | | | |/\\| |/ _ \\| '__| |/ _` |
                 _| || | | | (_| | |_| \\__ \\ |_| |  | | (_| | | \\  /\\  / (_) | |  | | (_| |
                 \\___/_| |_|\\__,_|\\__,_|___/\\__|_|  |_|\\__,_|_|  \\/  \\/ \\___/|_|  |_|\\__,_|
                                                                                          \s""");

        instance = this;

        ConfigHandler.init();

        I18nHelper.loadAllLanguages();

        getServer().getPluginManager().registerEvents(new EventListener(), this);

        RegistryHandler.doRegistry();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}