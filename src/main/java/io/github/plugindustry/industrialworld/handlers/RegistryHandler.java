package io.github.plugindustry.industrialworld.handlers;

import io.github.plugindustry.industrialworld.ConstItem;
import io.github.plugindustry.industrialworld.blocks.BlockGrassStack;
import io.github.plugindustry.industrialworld.blocks.BlockHayStack;
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
                                        .build(new ItemStack(Material.CRAFTING_TABLE)), "minecraft:crafting_table");
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                        .map('a',
                                             new ItemInstanceChoice(MainManager.getItemInstance(ConstItem.ITEM_GRASS)))
                                        .pattern("aax", "aax", "xxx")
                                        .build(ConstItem.ITEM_GRASS_STACK), "iw:grass_stack");
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                        .map('a', new OreDictionaryChoice("pickaxe"))
                                        .map('b',
                                             new OreDictionaryChoice("axe"))
                                        .map('c', new OreDictionaryChoice("log"))
                                        .addDamage(new OreDictionaryChoice("pickaxe"), 1)
                                        .addDamage(new OreDictionaryChoice("axe"), 1)
                                        .pattern("abx", "ccx", "xxx")
                                        .build(new ItemStack(Material.CRAFTING_TABLE)), "minecraft:crafting_table_2");
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                        .map('a', new VanillaMaterialChoice(Material.FLINT))
                                        .map('b', new VanillaMaterialChoice(Material.STICK))
                                        .pattern("aax", "bxx", "xxx")
                                        .build(ConstItem.ITEM_FLINT_PICKAXE), "iw:flint_pickaxe");
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                        .map('a', new VanillaMaterialChoice(Material.FLINT))
                                        .map('b', new VanillaMaterialChoice(Material.STICK))
                                        .pattern("aax", "bax", "xxx")
                                        .build(ConstItem.ITEM_FLINT_AXE), "iw:flint_axe");
        RecipeRegistry.register(ShapedRecipeFactory.create()
                                        .map('a', new VanillaMaterialChoice(Material.FLINT))
                                        .map('b', new VanillaMaterialChoice(Material.STICK))
                                        .pattern("axx", "bxx", "xxx")
                                        .build(ConstItem.ITEM_FLINT_SWORD), "iw:flint_sword");
        RecipeRegistry.updatePlaceholders();
    }

    public static void blockRegistry() {
        MainManager.registerBlock("iw:grass_stack", BlockGrassStack.INSTANCE);
        MainManager.registerBlock("iw:hay_stack", BlockHayStack.INSTANCE);
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
    }

    public static void doRegistry() {
        blockRegistry();
        ConstItem.init();

        recipeRegistry();
        lootRegistry();
    }
}
