package io.github.plugindustry.industrialworld.utils;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.wheelcore.i18n.I18n;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.stream.Stream;

public class I18nHelper {
    public static void loadAllLanguages() {
        try {
            try (FileSystem fs = FileSystems.newFileSystem(
                    Objects.requireNonNull(IndustrialWorld.class.getClassLoader().getResource("langs/")).toURI(),
                    Collections.emptyMap()); Stream<Path> langs = Files.walk(fs.getPath("langs/"))) {
                langs.filter(Files::isRegularFile)
                        .filter(resource -> resource.getFileName().toString().endsWith(".lang")).forEach(resource -> {
                            try {
                                I18n.load(Locale.forLanguageTag(resource.getFileName().toString().replace(".lang", "")),
                                        IndustrialWorld.instance, Files.newBufferedReader(resource, StandardCharsets.UTF_8));
                            } catch (IOException e) {
                                IndustrialWorld.instance.getLogger().log(Level.WARNING, "Exception loading language: ", e);
                            }
                        });
            }
        } catch (IOException | URISyntaxException e) {
            IndustrialWorld.instance.getLogger().log(Level.WARNING, "Exception listing languages: ", e);
        }
    }
}