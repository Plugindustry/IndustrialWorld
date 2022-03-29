package io.github.plugindustry.industrialworld;

import io.github.plugindustry.industrialworld.annotations.GeneratedItem;
import io.github.plugindustry.industrialworld.items.ItemFlintAxe;
import io.github.plugindustry.industrialworld.items.ItemFlintPickaxe;
import io.github.plugindustry.industrialworld.items.ItemFlintSword;
import io.github.plugindustry.wheelcore.interfaces.item.DummyBlockItem;
import io.github.plugindustry.wheelcore.interfaces.item.DummyItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class ConstItem {
    @GeneratedItem(type = Material.WHEAT, instance = DummyItem.class, id = "iw:grass")
    public static ItemStack GRASS;
    @GeneratedItem(type = Material.HAY_BLOCK, instance = DummyBlockItem.class, id = "iw:grass_stack")
    public static ItemStack GRASS_STACK;
    @GeneratedItem(type = Material.WHEAT, instance = DummyItem.class, id = "iw:hay")
    public static ItemStack HAY;
    @GeneratedItem(type = Material.HAY_BLOCK, instance = DummyBlockItem.class, id = "iw:hay_stack")
    public static ItemStack HAY_STACK;
    @GeneratedItem(type = Material.FLINT_AND_STEEL, instance = DummyItem.class, id = "iw:fire_starter")
    public static ItemStack FIRE_STARTER;

    @GeneratedItem(type = Material.STONE_PICKAXE, instance = ItemFlintPickaxe.class, oreDictionary = {"pickaxe"})
    public static ItemStack FLINT_PICKAXE;
    @GeneratedItem(type = Material.STONE_AXE, instance = ItemFlintAxe.class, oreDictionary = {"axe"})
    public static ItemStack FLINT_AXE;
    @GeneratedItem(type = Material.STONE_SWORD, instance = ItemFlintSword.class, oreDictionary = {"sword"})
    public static ItemStack FLINT_SWORD;
}
