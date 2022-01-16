package io.github.plugindustry.industrialworld.blocks;

import io.github.plugindustry.wheelcore.interfaces.Tickable;
import io.github.plugindustry.wheelcore.interfaces.block.DummyBlock;

public class BlockHayStack extends DummyBlock implements Tickable {
    public static final BlockHayStack INSTANCE = new BlockHayStack();

    private BlockHayStack() {};

    @Override
    public void onTick() {

    }
}
