package io.github.plugindustry.industrialworld.items;

import io.github.plugindustry.industrialworld.annotations.ItemInstance;
import io.github.plugindustry.wheelcore.interfaces.item.DummyTool;
import io.github.plugindustry.wheelcore.utils.BlockUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

@ItemInstance("iw:flint_axe")
public class ItemFlintAxe extends DummyTool {
    public final static ItemFlintAxe INSTANCE = new ItemFlintAxe();
    private final static ItemStack WOODEN_AXE = new ItemStack(Material.WOODEN_AXE);

    private ItemFlintAxe() {
    }

    @Override
    public float getToolBonus(@Nonnull Block block, @Nonnull ItemStack itemStack) {
        return BlockUtil.getToolBonus(block, WOODEN_AXE) * 0.8f;
    }

    @Override
    public boolean isSuitable(@Nonnull Block block, @Nonnull ItemStack itemStack) {
        return BlockUtil.isPreferredTool(block, WOODEN_AXE);
    }

    @Nonnull
    @Override
    public Optional<Integer> onItemDamage(@Nullable Player player, @Nonnull ItemStack item, int damage) {
        return Optional.of(damage * 4);
    }
}
