package io.github.plugindustry.industrialworld.blocks;

import io.github.plugindustry.industrialworld.annotations.BlockInstance;
import io.github.plugindustry.wheelcore.interfaces.block.DummyBlock;

@BlockInstance("hay_stack")
public class BlockHayStack extends DummyBlock {
    public static final BlockHayStack INSTANCE = new BlockHayStack();

    private BlockHayStack() {
    }
}