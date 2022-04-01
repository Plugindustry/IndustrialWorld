package io.github.plugindustry.industrialworld;

import io.github.plugindustry.industrialworld.annotations.GeneratedItem;
import io.github.plugindustry.industrialworld.items.ItemCrushedMalachite;
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

    @GeneratedItem(type = Material.STONE, instance = DummyBlockItem.class, id = "iw:malachite")
    public static ItemStack MALACHITE;
    @GeneratedItem(type = Material.GUNPOWDER, instance = ItemCrushedMalachite.class)
    public static ItemStack CRUSHED_MALACHITE;
    @GeneratedItem(type = Material.GUNPOWDER, instance = DummyItem.class, id = "iw:pure_crushed_malachite")
    public static ItemStack PURE_CRUSHED_MALACHITE;
    @GeneratedItem(type = Material.IRON_INGOT, instance = DummyItem.class, id = "iw:raw_copper", oreDictionary = {"raw_copper"})
    public static ItemStack RAW_COPPER;

    @GeneratedItem(type = Material.GUNPOWDER, instance = DummyItem.class, id = "iw:stone_powder")
    public static ItemStack STONE_POWDER;
    @GeneratedItem(type = Material.GUNPOWDER, instance = DummyItem.class, id = "iw:quartz_powder")
    public static ItemStack QUARTZ_POWDER;

    @GeneratedItem(type = Material.WOODEN_AXE, instance = DummyItem.class, id = "iw:wooden_hammer", oreDictionary = {"hammer"})
    public static ItemStack WOODEN_HAMMER;
    @GeneratedItem(type = Material.STONE_AXE, instance = DummyItem.class, id = "iw:stone_hammer", oreDictionary = {"hammer"})
    public static ItemStack STONE_HAMMER;
    @GeneratedItem(type = Material.IRON_AXE, instance = DummyItem.class, id = "iw:iron_hammer", oreDictionary = {"hammer"})
    public static ItemStack IRON_HAMMER;
    @GeneratedItem(type = Material.GOLDEN_AXE, instance = DummyItem.class, id = "iw:golden_hammer", oreDictionary = {"hammer"})
    public static ItemStack GOLDEN_HAMMER;
    @GeneratedItem(type = Material.DIAMOND_AXE, instance = DummyItem.class, id = "iw:diamond_hammer", oreDictionary = {"hammer"})
    public static ItemStack DIAMOND_HAMMER;
}
