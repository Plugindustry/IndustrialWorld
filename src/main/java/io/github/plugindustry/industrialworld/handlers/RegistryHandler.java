package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.ConstItem;
import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.industrialworld.blocks.BlockMalachite;
import io.github.plugindustry.industrialworld.utils.RegisterHelper;
import io.github.plugindustry.wheelcore.manager.ItemMapping;
import io.github.plugindustry.wheelcore.manager.MainManager;
import io.github.plugindustry.wheelcore.manager.RecipeRegistry;
import io.github.plugindustry.wheelcore.manager.recipe.ShapedRecipeFactory;
import io.github.plugindustry.wheelcore.manager.recipe.ShapelessRecipe;
import io.github.plugindustry.wheelcore.manager.recipe.SmeltingRecipe;
import io.github.plugindustry.wheelcore.manager.recipe.choice.ItemInstanceChoice;
import io.github.plugindustry.wheelcore.manager.recipe.choice.OreDictionaryChoice;
import io.github.plugindustry.wheelcore.manager.recipe.choice.VanillaMaterialChoice;
import io.github.plugindustry.wheelcore.utils.Pair;
import io.github.plugindustry.wheelcore.world.OverworldPopulator;
import io.github.plugindustry.wheelcore.world.WorldGenMinable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class RegistryHandler {
    public static void recipeRegistry() {
        // Vanilla recipes removal
        for (String key : ConfigHandler.getRecipeConfig().getStringList("removal"))
            Bukkit.removeRecipe(NamespacedKey.minecraft(key));

        // Custom recipes

        // Crafting table
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                                   .map('a', new OreDictionaryChoice("planks"))
                                                   .pattern("aaa", "aaa", "aaa")
                                                   .build(new ItemStack(Material.CRAFTING_TABLE)),
                "minecraft/crafting_table");
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                                   .map('a',
                                                           new OreDictionaryChoice(Collections.singletonList("pickaxe"),
                                                                   false))
                                                   .map('b',
                                                           new OreDictionaryChoice(Collections.singletonList("axe"),
                                                                   false))
                                                   .map('c', new OreDictionaryChoice("log"))
                                                   .addDamage(
                                                           new OreDictionaryChoice(Collections.singletonList("pickaxe"),
                                                                   false),
                                                           1)
                                                   .addDamage(new OreDictionaryChoice(Collections.singletonList("axe"),
                                                           false), 1)
                                                   .pattern("ab", "cc")
                                                   .build(new ItemStack(Material.CRAFTING_TABLE)),
                "minecraft/crafting_table_2");

        // Grass & hay
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                                   .map('a',
                                                           new ItemInstanceChoice(
                                                                   MainManager.getItemInstance(ConstItem.GRASS)))
                                                   .pattern("aa", "aa")
                                                   .build(ConstItem.GRASS_STACK), "iw/grass_stack");

        // Flint tools
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                                   .map('a', new VanillaMaterialChoice(Material.FLINT))
                                                   .map('b', new VanillaMaterialChoice(Material.STICK))
                                                   .pattern("aa", "b")
                                                   .build(ConstItem.FLINT_PICKAXE), "iw/flint_pickaxe");
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                                   .map('a', new VanillaMaterialChoice(Material.FLINT))
                                                   .map('b', new VanillaMaterialChoice(Material.STICK))
                                                   .pattern("aa", "ba")
                                                   .build(ConstItem.FLINT_AXE), "iw/flint_axe");
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                                   .map('a', new VanillaMaterialChoice(Material.FLINT))
                                                   .map('b', new VanillaMaterialChoice(Material.STICK))
                                                   .pattern("a", "b")
                                                   .build(ConstItem.FLINT_SWORD), "iw/flint_sword");

        // Hammers
        RecipeRegistry.register(ShapedRecipeFactory.create().map('a', new VanillaMaterialChoice(Material.STICK))
                                                   .map('b', new OreDictionaryChoice("planks"))
                                                   .pattern("bbb", "bab", "nan").build(ConstItem.WOODEN_HAMMER),
                "iw/wooden_hammer");
        RecipeRegistry.register(ShapedRecipeFactory.create().map('a', new VanillaMaterialChoice(Material.STICK))
                                                   .map('b', new VanillaMaterialChoice(Material.COBBLESTONE))
                                                   .pattern("bbb", "bab", "nan").build(ConstItem.STONE_HAMMER),
                "iw/stone_hammer");
        RecipeRegistry.register(ShapedRecipeFactory.create().map('a', new VanillaMaterialChoice(Material.STICK))
                                                   .map('b', new VanillaMaterialChoice(Material.IRON_INGOT))
                                                   .pattern("bbb", "bab", "nan").build(ConstItem.IRON_HAMMER),
                "iw/iron_hammer");
        RecipeRegistry.register(ShapedRecipeFactory.create().map('a', new VanillaMaterialChoice(Material.STICK))
                                                   .map('b', new VanillaMaterialChoice(Material.GOLD_INGOT))
                                                   .pattern("bbb", "bab", "nan").build(ConstItem.GOLDEN_HAMMER),
                "iw/golden_hammer");
        RecipeRegistry.register(ShapedRecipeFactory.create().map('a', new VanillaMaterialChoice(Material.STICK))
                                                   .map('b', new VanillaMaterialChoice(Material.DIAMOND))
                                                   .pattern("bbb", "bab", "nan").build(ConstItem.DIAMOND_HAMMER),
                "iw/diamond_hammer");

        // Crushed malachite
        RecipeRegistry.register(new ShapelessRecipe(
                Arrays.asList(new ItemInstanceChoice(MainManager.getItemInstance(ConstItem.MALACHITE)),
                        new OreDictionaryChoice(Collections.singletonList("hammer"), false)),
                ConstItem.CRUSHED_MALACHITE).addItemCost(
                new OreDictionaryChoice(Collections.singletonList("hammer"), false), 1), "iw/crushed_malachite");

        // Raw copper
        RecipeRegistry.register(new SmeltingRecipe(ConstItem.PURE_CRUSHED_MALACHITE, ConstItem.RAW_COPPER, 0.5f, 400),
                "iw/raw_copper");

        RecipeRegistry.updatePlaceholders();
    }

    public static void itemRegistry() {
        RegisterHelper.registerItems(IndustrialWorld.class.getPackageName());
        RegisterHelper.generateItems(ConstItem.class);
    }

    public static void blockRegistry() {
        RegisterHelper.registerBlocks(IndustrialWorld.class.getPackageName());
    }

    public static void lootRegistry() {
        LootHandler mainLootHandler = new LootHandler();
        List<Pair<ItemStack, Double>> leavesList = ConfigHandler.getLootPairs("leaves");
        mainLootHandler.registerBlockLoot(Material.OAK_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.ACACIA_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.BIRCH_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.DARK_OAK_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.JUNGLE_LEAVES, leavesList);
        mainLootHandler.registerBlockLoot(Material.SPRUCE_LEAVES, leavesList);

        mainLootHandler.registerBlockLoot(Material.GRASS_BLOCK, ConfigHandler.getLootPairs("grass"));

        MainHandler.registerBlockBreakHandler(mainLootHandler);

        VanillaChangeHandler vanillaChangeHandler = new VanillaChangeHandler();
        vanillaChangeHandler.init();
        MainHandler.registerBlockBreakHandler(vanillaChangeHandler);
    }

    public static void oreDictRegistry() {
        if (Material.getMaterial("RAW_COPPER") != null)
            ItemMapping.registerVanillaOreDict(Objects.requireNonNull(Material.getMaterial("RAW_COPPER")),
                    Collections.singleton("raw_copper"));
        if (Material.getMaterial("COPPER_INGOT") != null)
            ItemMapping.registerVanillaOreDict(Objects.requireNonNull(Material.getMaterial("COPPER_INGOT")),
                    Collections.singleton("copper_ingot"));
    }

    public static void worldGenRegistry() {
        OverworldPopulator.generators.add(new WorldGenMinable(BlockMalachite.INSTANCE, 15, 40, 30, 120));
    }

    public static void doRegistry() {
        oreDictRegistry();
        itemRegistry();
        blockRegistry();
        worldGenRegistry();

        recipeRegistry();
        lootRegistry();
    }
}
