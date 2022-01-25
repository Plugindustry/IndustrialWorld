package io.github.plugindustry.industrialworld;

import io.github.plugindustry.industrialworld.event.EventListener;
import io.github.plugindustry.industrialworld.handlers.ConfigHandler;
import io.github.plugindustry.industrialworld.handlers.RegistryHandler;
import io.github.plugindustry.wheelcore.i18n.I18n;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Locale;
import java.util.Objects;

public final class IndustrialWorld extends JavaPlugin {
    public static IndustrialWorld instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        // IndustrialWorld logo
        getLogger().info("\n|_   _|        | |         | |      (_)     | | | |  | |          | |   | |\n" +
                         "  | | _ __   __| |_   _ ___| |_ _ __ _  __ _| | | |  | | ___  _ __| | __| |\n" +
                         "  | || '_ \\ / _` | | | / __| __| '__| |/ _` | | | |/\\| |/ _ \\| '__| |/ _` |\n" +
                         " _| || | | | (_| | |_| \\__ \\ |_| |  | | (_| | | \\  /\\  / (_) | |  | | (_| |\n" +
                         " \\___/_| |_|\\__,_|\\__,_|___/\\__|_|  |_|\\__,_|_|  \\/  \\/ \\___/|_|  |_|\\__,_|\n" +
                         "                                                                           ");

        instance = this;

        ConfigHandler.init();

        I18n.load(Locale.SIMPLIFIED_CHINESE, Objects.requireNonNull(getTextResource("langs/zh-cn.lang")));
        I18n.load(Locale.US, Objects.requireNonNull(getTextResource("langs/en-us.lang")));

        getServer().getPluginManager().registerEvents(new EventListener(), this);

        RegistryHandler.doRegistry();
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
