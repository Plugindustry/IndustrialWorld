package io.github.plugindustry.industrialworld.items;

import io.github.plugindustry.industrialworld.annotations.ItemInstance;
import io.github.plugindustry.wheelcore.interfaces.item.DummyItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

@ItemInstance("iw:flint_sword")
public class ItemFlintSword extends DummyItem {
    public final static ItemFlintSword INSTANCE = new ItemFlintSword();

    private ItemFlintSword() {
    }

    @Nonnull
    @Override
    public Optional<Integer> onItemDamage(@Nullable Player player, @Nonnull ItemStack item, int damage) {
        return Optional.of(damage * 4);
    }
}
