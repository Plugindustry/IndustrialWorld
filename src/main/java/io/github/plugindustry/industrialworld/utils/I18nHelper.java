package io.github.plugindustry.industrialworld.utils;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.wheelcore.i18n.I18n;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;

public class I18nHelper {
    public static void loadAllLanguages() {
        try {
            Files.walk(FileSystems.newFileSystem(
                            Objects.requireNonNull(IndustrialWorld.class.getClassLoader().getResource("langs/")).toURI(),
                            Collections.emptyMap()).getPath("langs/")).filter(Files::isRegularFile)
                    .filter(resource -> resource.getFileName().toString().endsWith(".lang")).forEach(resource -> {
                        try {
                            I18n.load(Locale.forLanguageTag(resource.getFileName().toString().replace(".lang", "")),
                                    Files.newBufferedReader(resource, StandardCharsets.UTF_8));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}