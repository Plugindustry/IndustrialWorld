package io.github.plugindustry.industrialworld;

import io.github.plugindustry.wheelcore.i18n.I18n;
import io.github.plugindustry.wheelcore.interfaces.item.DummyBlockItem;
import io.github.plugindustry.wheelcore.interfaces.item.DummyItem;
import io.github.plugindustry.wheelcore.interfaces.item.ItemBase;
import io.github.plugindustry.wheelcore.manager.ItemMapping;
import io.github.plugindustry.wheelcore.manager.MainManager;
import io.github.plugindustry.wheelcore.utils.ItemStackUtil;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ConstItem {
    public static ItemStack ITEM_GRASS;
    public static ItemStack ITEM_GRASS_STACK;
    public static ItemStack ITEM_HAY;
    public static ItemStack ITEM_HAY_STACK;
    public static ItemStack ITEM_FIRE_STARTER;

    public static void init() {
        ItemBase instance;

        instance = new DummyItem();
        MainManager.registerItem("iw:grass", instance);
        ItemMapping.set("iw:grass", ItemStackUtil.create(Material.WHEAT).setDisplayName(I18n.getLocalePlaceholder(
                "iw#item/grass/name")).setInstance(instance).getItemStack());
        ITEM_GRASS = ItemMapping.get("iw:grass");

        instance = new DummyItem();
        MainManager.registerItem("iw:hay", instance);
        ItemMapping.set("iw:hay", ItemStackUtil.create(Material.WHEAT).setDisplayName(I18n.getLocalePlaceholder(
                "iw#item/hay/name")).setInstance(instance).getItemStack());
        ITEM_HAY = ItemMapping.get("iw:hay");

        instance = new DummyBlockItem();
        MainManager.registerItem("iw:hay_stack", instance);
        ItemMapping.set("iw:hay_stack",
                        ItemStackUtil.create(Material.HAY_BLOCK).setDisplayName(I18n.getLocalePlaceholder(
                                "iw#item/hay_stack/name")).setInstance(instance).getItemStack());
        ITEM_HAY_STACK = ItemMapping.get("iw:hay_stack");

        instance = new DummyBlockItem();
        MainManager.registerItem("iw:grass_stack", instance);
        ItemMapping.set("iw:grass_stack",
                        ItemStackUtil.create(Material.HAY_BLOCK).setDisplayName(I18n.getLocalePlaceholder(
                                "iw#item/grass_stack/name")).setInstance(instance).getItemStack());
        ITEM_GRASS_STACK = ItemMapping.get("iw:grass_stack");

        instance = new DummyItem();
        MainManager.registerItem("iw:fire_starter", instance);
        ItemMapping.set("iw:fire_starter",
                        ItemStackUtil.create(Material.FLINT_AND_STEEL).setDisplayName(I18n.getLocalePlaceholder(
                                "iw#item/fire_starter/name")).setInstance(instance).getItemStack());
        ITEM_FIRE_STARTER = ItemMapping.get("iw:fire_starter");
    }
}
