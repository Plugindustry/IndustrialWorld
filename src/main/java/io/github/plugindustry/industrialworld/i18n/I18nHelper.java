package io.github.plugindustry.industrialworld.i18n;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.wheelcore.i18n.I18n;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Objects;

public class I18nHelper {
    public static void loadAllLocales() {
        I18n.load(Locale.SIMPLIFIED_CHINESE, IndustrialWorld.getInstance(),
                new InputStreamReader(Objects.requireNonNull(IndustrialWorld.getInstance().getResource("zh_cn.lang")),
                        StandardCharsets.UTF_8));
        I18n.load(Locale.US, IndustrialWorld.getInstance(),
                new InputStreamReader(Objects.requireNonNull(IndustrialWorld.getInstance().getResource("en_us.lang")),
                        StandardCharsets.UTF_8));
    }
}