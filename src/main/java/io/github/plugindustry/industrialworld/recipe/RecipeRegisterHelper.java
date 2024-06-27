package io.github.plugindustry.industrialworld.recipe;

import io.github.plugindustry.industrialworld.IndustrialWorld;
import io.github.plugindustry.industrialworld.item.ConstItems;
import io.github.plugindustry.wheelcore.manager.RecipeRegistry;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class RecipeRegisterHelper {
    public static void registerLithicReductionRecipes() {
        RecipeRegistry.register(LithicReductionRecipe.Builder.create(ConstItems.FLINT_AXE_HEAD)
                .pattern("aabba", "bbbbb", "bbbbb", "aabbb", "aaabb").ingradient('b', new ItemStack(Material.FLINT))
                .get(), new NamespacedKey(IndustrialWorld.getInstance(), "flint_axe_head"));
    }

    public static void registerRecipes() {
        registerLithicReductionRecipes();
    }
}