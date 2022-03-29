package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.ConstItem;
import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.industrialworld.utils.RegisterHelper;
import io.github.plugindustry.wheelcore.manager.MainManager;
import io.github.plugindustry.wheelcore.manager.RecipeRegistry;
import io.github.plugindustry.wheelcore.manager.recipe.ShapedRecipeFactory;
import io.github.plugindustry.wheelcore.manager.recipe.choice.ItemInstanceChoice;
import io.github.plugindustry.wheelcore.manager.recipe.choice.OreDictionaryChoice;
import io.github.plugindustry.wheelcore.manager.recipe.choice.VanillaMaterialChoice;
import io.github.plugindustry.wheelcore.utils.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;
import java.util.List;

public class RegistryHandler {
    public static void recipeRegistry() {
        // Vanilla recipes removal
        for (String key : ConfigHandler.getRecipeConfig().getStringList("removal"))
            Bukkit.removeRecipe(NamespacedKey.minecraft(key));

        // Custom recipes
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                                   .map('a', new OreDictionaryChoice("planks"))
                                                   .pattern("aaa", "aaa", "aaa")
                                                   .build(new ItemStack(Material.CRAFTING_TABLE)),
                "minecraft/crafting_table");
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                                   .map('a',
                                                           new ItemInstanceChoice(
                                                                   MainManager.getItemInstance(ConstItem.GRASS)))
                                                   .pattern("aa", "aa")
                                                   .build(ConstItem.GRASS_STACK), "iw/grass_stack");
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

    public static void doRegistry() {
        itemRegistry();
        blockRegistry();

        recipeRegistry();
        lootRegistry();
    }
}
