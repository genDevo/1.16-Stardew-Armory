package net.gendevo.stardewarmory.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class IridiumOre extends Block {
    public IridiumOre() {
        super(Block.Properties.of(Material.STONE)
                .lightLevel((p_235470_0_) -> 6)
                .strength(3.5F, 4)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE));
    }

    @Override
    public int getExpDrop(BlockState state, LevelReader world, BlockPos pos, int fortune, int silktouch) {
        return 10 + (fortune * 3);
    }
}
