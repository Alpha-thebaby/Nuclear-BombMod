package com.hbm.block;

import com.hbm.ExampleMod;
import com.hbm.entity.EntityNukeExplosionMK4;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NukeBoyBlock extends Block {
    public NukeBoyBlock(Settings settings) {
        super(settings);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isReceivingRedstonePower(pos)) {
            if (!world.isClient) {
                world.spawnEntity(new EntityNukeCloudSmall(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D));
                world.spawnEntity(new EntityNukeExplosionMK4(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 12.0D, (double)pos.getZ() + 0.5D));
            }
            world.removeBlock(pos, false);
        }

    }
}
