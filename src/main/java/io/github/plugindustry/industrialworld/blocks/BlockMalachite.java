package io.github.plugindustry.industrialworld.blocks;

import io.github.plugindustry.industrialworld.annotations.BlockInstance;
import io.github.plugindustry.wheelcore.interfaces.block.DummyBlock;

@BlockInstance("malachite")
public class BlockMalachite extends DummyBlock {
    public static final BlockMalachite INSTANCE = new BlockMalachite();

    private BlockMalachite() {
    }
}