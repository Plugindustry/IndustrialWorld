package io.github.plugindustry.industrialworld.item;

import io.github.plugindustry.wheelcore.interfaces.item.DummyTool;
import io.github.plugindustry.wheelcore.utils.BlockUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class ItemFlintAxe extends DummyTool {
    public static final ItemStack ITEM_WOODEN_AXE = new ItemStack(Material.WOODEN_AXE);
    private static ItemFlintAxe instance;

    private ItemFlintAxe() {}

    public static ItemFlintAxe getInstance() {
        if (instance == null) instance = new ItemFlintAxe();
        return instance;
    }

    @Override
    public float getToolBonus(@Nonnull Block block, @Nonnull ItemStack itemStack) {
        return BlockUtil.getVanillaToolBonus(block, ITEM_WOODEN_AXE);
    }

    @Override
    public boolean isSuitable(@Nonnull Block block, @Nonnull ItemStack itemStack) {
        return BlockUtil.isVanillaPreferredTool(block, itemStack);
    }

    @Nonnull
    @Override
    public Optional<Integer> onItemDamage(@Nullable Player player, @Nonnull ItemStack item, int damage) {
        return Optional.of(damage * 5);
    }
}