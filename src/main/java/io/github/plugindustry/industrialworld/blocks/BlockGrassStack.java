package io.github.plugindustry.industrialworld.blocks;

import io.github.plugindustry.industrialworld.annotations.BlockInstance;
import io.github.plugindustry.industrialworld.handlers.ConfigHandler;
import io.github.plugindustry.wheelcore.interfaces.Tickable;
import io.github.plugindustry.wheelcore.interfaces.block.BlockData;
import io.github.plugindustry.wheelcore.interfaces.block.DummyBlock;
import io.github.plugindustry.wheelcore.manager.MainManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@BlockInstance("iw:grass_stack")
public class BlockGrassStack extends DummyBlock implements Tickable {
    public static final BlockGrassStack INSTANCE = new BlockGrassStack();
    public static final int lifespan = ConfigHandler.getConfig().getInt("grass_lifespan");

    private BlockGrassStack() {
    }

    @Override
    public void onTick() {
        MainManager.blockDataProvider.blocksOf(this).forEach(block -> {
            Location location = Objects.requireNonNull(block);
            BlockGrassStackData temp = (BlockGrassStackData) Objects.requireNonNull(MainManager.getBlockData(location));

            temp.addAge(1);
            if (temp.isDried()) {
                MainManager.queuePostTickTask(() -> {
                    MainManager.removeBlock(location);
                    BlockHayStack.INSTANCE.onBlockPlace(null, location.getBlock(), null, null);
                });
            }
        });
    }

    @Nullable
    @Override
    public BlockData getInitialData(@Nullable ItemStack item, @NotNull Block block, @Nullable Block blockAgainst, @Nullable Player player) {
        return new BlockGrassStackData(0);
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
