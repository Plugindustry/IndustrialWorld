package io.github.plugindustry.industrialworld.recipe;

import io.github.plugindustry.wheelcore.manager.recipe.MatrixInputRecipe;
import io.github.plugindustry.wheelcore.utils.ItemStackUtil;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class LithicReductionRecipe implements MatrixInputRecipe {
    private final List<List<ItemStack>> inputs;
    private final ItemStack result;

    public LithicReductionRecipe(List<List<ItemStack>> inputs, ItemStack result) {
        if (!(inputs.size() == 5 && inputs.stream().allMatch(l -> l.size() == 5))) throw new IllegalArgumentException();
        this.inputs = inputs.stream()
                .map(l -> l.stream().map(item -> item == null ? null : item.clone()).collect(Collectors.toList()))
                .collect(Collectors.toList());
        this.result = result.clone();
    }

    @Override
    public boolean matches(@NotNull List<List<ItemStack>> matrix) {
        if (matrix.size() != 5) return false;
        for (int i = 0; i < 5; ++i) {
            List<ItemStack> inputRow = inputs.get(i);
            List<ItemStack> row = matrix.get(i);
            if (inputRow.size() != row.size()) return false;
            for (int j = 0; j < 5; ++j) if (!ItemStackUtil.isSimilar(inputRow.get(j), row.get(j))) return false;
        }
        return true;
    }

    @Override
    public ItemStack getResult() {
        return result.clone();
    }

    public static class Builder {
        private final ItemStack result;
        private List<String> pattern;
        private final HashMap<Character, ItemStack> map = new HashMap<>();

        private Builder(ItemStack result) {
            this.result = result;
        }

        public static Builder create(ItemStack result) {
            return new Builder(result);
        }

        public Builder pattern(String... pattern) {
            if (pattern.length != 5) throw new IllegalArgumentException();
            if (!Arrays.stream(pattern).allMatch(str -> str.length() == 5)) throw new IllegalArgumentException();
            this.pattern = Arrays.asList(pattern);
            return this;
        }

        public Builder ingradient(char c, ItemStack item) {
            map.put(c, item);
            return this;
        }

        public LithicReductionRecipe get() {
            return new LithicReductionRecipe(pattern.stream().map(str -> {
                List<ItemStack> row = new ArrayList<>();
                for (char c : str.toCharArray()) row.add(map.get(c));
                return row;
            }).collect(Collectors.toList()), result);
        }
    }
}