package io.github.plugindustry.industrialworld.items;

import io.github.plugindustry.wheelcore.interfaces.item.DummyTool;
import io.github.plugindustry.wheelcore.utils.BlockUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class ItemFlintPickaxe extends DummyTool {
    public final static ItemFlintPickaxe INSTANCE = new ItemFlintPickaxe();
    private final static ItemStack WOODEN_PICKAXE = new ItemStack(Material.WOODEN_PICKAXE);

    private ItemFlintPickaxe() {}

    @Override
    public float getToolBonus(@Nonnull Block block, @Nonnull ItemStack itemStack) {
        return BlockUtil.getToolBonus(block, WOODEN_PICKAXE) * 0.8f;
    }

    @Override
    public boolean isSuitable(@Nonnull Block block, @Nonnull ItemStack itemStack) {
        return BlockUtil.isPreferredTool(block, WOODEN_PICKAXE);
    }

    @Nonnull
    @Override
    public Optional<Integer> onItemDamage(@Nullable Player player, @Nonnull ItemStack item, int damage) {
        return Optional.of(damage * 2);
    }
}
