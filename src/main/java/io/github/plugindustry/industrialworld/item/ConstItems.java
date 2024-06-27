package io.github.plugindustry.industrialworld.item;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.wheelcore.i18n.I18n;
import io.github.plugindustry.wheelcore.interfaces.item.DummyItem;
import io.github.plugindustry.wheelcore.interfaces.item.ItemBase;
import io.github.plugindustry.wheelcore.manager.ItemMapping;
import io.github.plugindustry.wheelcore.manager.MainManager;
import io.github.plugindustry.wheelcore.utils.ItemStackUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class ConstItems {
    public static ItemStack FLINT_AXE_HEAD;

    public static void initItems() {
        ItemBase instance = new DummyItem();
        NamespacedKey id = new NamespacedKey(IndustrialWorld.getInstance(), "flint_axe_head");
        MainManager.registerItem(id, instance);
        FLINT_AXE_HEAD = new ItemStackUtil.ItemStackFactory(Material.FLINT).instance(instance).displayName(
                        I18n.getLocalePlaceholder(new NamespacedKey(IndustrialWorld.getInstance(), "item/flint_axe_head/name")))
                .oreDictionary("axe").getItemStack();
        ItemMapping.set(id, FLINT_AXE_HEAD);
    }
}