package com.hbm.block;

import com.hbm.ExampleMod;
import com.hbm.entity.EntityNukeExplosionMK4;
import com.hbm.entity.NuclearBombEntity;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class NukeBoyBlock extends Block {
    public NukeBoyBlock(Settings settings) {
        super(settings);
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isReceivingRedstonePower(pos)) {
            if (!world.isClient) {
                world.spawnEntity(new NuclearBombEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D));
                //world.spawnEntity(new EntityNukeExplosionMK4(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 12.0D, (double)pos.getZ() + 0.5D));
            }
            world.removeBlock(pos, false);
        }

    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        world.scheduleBlockTick(pos, this, this.getFallDelay());
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        world.scheduleBlockTick(pos, this, this.getFallDelay());
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (FallingBlock.canFallThrough(world.getBlockState(pos.down())) && pos.getY() >= world.getBottomY()) {
            world.spawnEntity(new NuclearBombEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D));
            world.removeBlock(pos, false);
        }
    }

    protected int getFallDelay() {
        return 2;
    }
}
