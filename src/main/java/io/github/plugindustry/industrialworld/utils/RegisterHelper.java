package io.github.plugindustry.industrialworld.utils;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.industrialworld.annotations.BlockInstance;
import io.github.plugindustry.industrialworld.annotations.GeneratedItem;
import io.github.plugindustry.industrialworld.annotations.ItemInstance;
import io.github.plugindustry.wheelcore.i18n.I18n;
import io.github.plugindustry.wheelcore.interfaces.block.BlockBase;
import io.github.plugindustry.wheelcore.interfaces.item.DummyBlockItem;
import io.github.plugindustry.wheelcore.interfaces.item.DummyItem;
import io.github.plugindustry.wheelcore.interfaces.item.ItemBase;
import io.github.plugindustry.wheelcore.manager.ItemMapping;
import io.github.plugindustry.wheelcore.manager.MainManager;
import io.github.plugindustry.wheelcore.utils.ItemStackUtil;
import org.bukkit.inventory.ItemStack;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.logging.Level;

public class RegisterHelper {
    public static void registerBlocks(String pack) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().forPackage(pack, IndustrialWorld.class.getClassLoader())
                                          .filterInputsBy(new FilterBuilder().includePackage(pack))
                                          .setExpandSuperTypes(false));
        reflections.getTypesAnnotatedWith(BlockInstance.class).forEach(clazz -> {
            String id = clazz.getAnnotation(BlockInstance.class).value();
            try {
                MainManager.registerBlock(id, (BlockBase) clazz.getDeclaredField("INSTANCE").get(null));
            } catch (Exception e) {
                IndustrialWorld.instance.getLogger().log(Level.SEVERE, e, () -> "Failed to register block " + id);
            }
        });
    }

    public static void registerItems(String pack) {
        Reflections reflections = new Reflections(
                new ConfigurationBuilder().forPackage(pack, IndustrialWorld.class.getClassLoader())
                                          .filterInputsBy(new FilterBuilder().includePackage(pack))
                                          .setExpandSuperTypes(false));
        reflections.getTypesAnnotatedWith(ItemInstance.class).forEach(clazz -> {
            String id = clazz.getAnnotation(ItemInstance.class).value();
            try {
                MainManager.registerItem(id, (ItemBase) clazz.getDeclaredField("INSTANCE").get(null));
            } catch (Exception e) {
                IndustrialWorld.instance.getLogger().log(Level.SEVERE, e, () -> "Failed to register item " + id);
            }
        });
    }

    public static void generateItems(Class<?> clazz) {
        Arrays.stream(clazz.getDeclaredFields()).filter(field -> Modifier.isStatic(
                field.getModifiers()) && field.getType() == ItemStack.class && field.canAccess(
                null) && field.isAnnotationPresent(GeneratedItem.class)).forEach(field -> {
            try {
                GeneratedItem annotation = field.getAnnotation(GeneratedItem.class);
                ItemBase instance;
                if (annotation.instance() == DummyItem.class)
                    MainManager.registerItem(annotation.id(), instance = new DummyItem());
                else if (annotation.instance() == DummyBlockItem.class)
                    MainManager.registerItem(annotation.id(), instance = new DummyBlockItem());
                else instance = (ItemBase) annotation.instance().getDeclaredField("INSTANCE").get(null);

                String id = Objects.requireNonNull(MainManager.getIdFromInstance(instance));
                ItemStackUtil.ItemStackFactory temp = ItemStackUtil.create(annotation.type()).instance(instance)
                                                                   .oreDictionary(annotation.oreDictionary());
                if (annotation.hasDisplayName())
                    temp.displayName(I18n.getLocalePlaceholder(
                            String.format("%s#item/%s/name", id.substring(0, id.indexOf(":")),
                                    id.substring(id.indexOf(":") + 1))));
                if (annotation.hasLore())
                    temp.lore(Collections.singletonList(I18n.getLocaleListPlaceholder(
                            String.format("%s#item/%s/lore", id.substring(0, id.indexOf(":")),
                                    id.substring(id.indexOf(":") + 1)))));
                switch (annotation.customModelDataType()) {
                    case NONE -> ItemMapping.set(id, temp.getItemStack());
                    case ID_GENERATED -> ItemMapping.set(id, temp.customModelData(id).getItemStack());
                    case MANUAL -> ItemMapping.set(id,
                            temp.customModelData(annotation.customModelData()).getItemStack());
                }
                field.set(null, ItemMapping.get(id));
            } catch (Exception e) {
                IndustrialWorld.instance.getLogger().log(Level.SEVERE, e,
                        () -> String.format("Failed to generate item %s.%s", clazz.getName(), field.getName()));
            }
        });
    }
}
