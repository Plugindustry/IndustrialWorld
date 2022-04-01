package io.github.plugindustry.industrialworld.items;

import io.github.plugindustry.industrialworld.ConstItem;
import io.github.plugindustry.industrialworld.annotations.ItemInstance;
import io.github.plugindustry.wheelcore.interfaces.item.DummyItem;
import io.github.plugindustry.wheelcore.manager.MainManager;
import io.github.plugindustry.wheelcore.utils.ItemStackUtil;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@ItemInstance("iw:crushed_malachite")
public class ItemCrushedMalachite extends DummyItem {
    public static final ItemCrushedMalachite INSTANCE = new ItemCrushedMalachite();

    private static final boolean flag;
    private static final Material NEEDED_CAULDRON_TYPE;

    static {
        if (Material.getMaterial("WATER_CAULDRON") != null) {
            NEEDED_CAULDRON_TYPE = Material.getMaterial("WATER_CAULDRON");
            flag = true;
        } else {
            NEEDED_CAULDRON_TYPE = Material.CAULDRON;
            flag = false;
        }
    }

    private ItemCrushedMalachite() {
    }

    @Override
    public boolean onInteract(@NotNull Player player, @NotNull Action action, @Nullable EquipmentSlot hand,
                              @Nullable ItemStack tool, @Nullable Block block, @Nullable Entity entity) {
        if (super.onInteract(player, action, hand, tool, block, entity)) {
            if (action == Action.RIGHT_CLICK_BLOCK && block != null && !MainManager.hasBlock(
                    block.getLocation()) && block.getType() == NEEDED_CAULDRON_TYPE) {
                Levelled data = (Levelled) block.getBlockData();
                if (data.getLevel() > 0) {
                    player.getInventory().setItem(Objects.requireNonNull(hand),
                            ItemStackUtil.clone(Objects.requireNonNull(tool), tool.getAmount() - 1));
                    player.getWorld()
                          .dropItem(player.getLocation(), ConstItem.PURE_CRUSHED_MALACHITE.clone());
                    player.getWorld().dropItem(player.getLocation(),
                            ItemStackUtil.clone(ConstItem.STONE_POWDER,
                                    ThreadLocalRandom.current().nextInt(1, 3)));
                    if (ThreadLocalRandom.current().nextBoolean())
                        player.getWorld().dropItem(player.getLocation(), ConstItem.QUARTZ_POWDER.clone());

                    if (data.getLevel() == 1 && flag) block.setType(Material.CAULDRON);
                    else {
                        data.setLevel(data.getLevel() - 1);
                        block.setBlockData(data);
                    }
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
