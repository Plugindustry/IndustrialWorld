package io.github.plugindustry.industrialworld.blocks;

import io.github.plugindustry.industrialworld.handlers.ConfigHandler;
import io.github.plugindustry.wheelcore.interfaces.Tickable;
import io.github.plugindustry.wheelcore.interfaces.block.BlockData;
import io.github.plugindustry.wheelcore.interfaces.block.DummyBlock;
import io.github.plugindustry.wheelcore.manager.MainManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BlockGrassStack extends DummyBlock implements Tickable {
    public static final BlockGrassStack INSTANCE = new BlockGrassStack();
    public static final int lifespan = ConfigHandler.getConfig().getInt("grass_lifespan");

    private BlockGrassStack() {

    }

    @Override
    public void onTick() {
        List<Location> toBeModified = new ArrayList<>();

        MainManager.blockDataProvider.blocksOf(this).forEach(block -> {
            Location location = Objects.requireNonNull(block);
            BlockGrassStackData temp = (BlockGrassStackData) Objects.requireNonNull(MainManager.getBlockData(location));

            temp.addAge(1);
            if (temp.isDried()) {
                toBeModified.add(location);
            }
        });

        for (Location location : toBeModified) {
            MainManager.removeBlock(location);
            MainManager.addBlock(location, BlockHayStack.INSTANCE, null);
        }
    }

    @Override
    public boolean onBlockPlace(@Nullable ItemStack item, @Nonnull Block block, @Nullable Block blockAgainst, @Nullable Player player) {
        if (super.onBlockPlace(item, block, blockAgainst, player)) {
            MainManager.setBlockData(block.getLocation(), new BlockGrassStackData(0));
            return true;
        }
        return false;
    }

    public static class BlockGrassStackData extends BlockData {
        public int age;

        public BlockGrassStackData(int age) {
            this.age = age;
        }

        public void addAge(int variation) {
            this.age += variation;
        }

        public boolean isDried() {
            return this.age > BlockGrassStack.lifespan;
        }
    }
}
