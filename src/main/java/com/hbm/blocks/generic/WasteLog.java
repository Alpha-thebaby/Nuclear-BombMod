package com.hbm.blocks.generic;

import java.util.Random;

import com.hbm.blocks.ModBlocks;
import com.hbm.items.ModItems;
import com.hbm.main.MainRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WasteLog extends Block {

	public WasteLog(Material mat, String s) {
		super(mat);
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.setCreativeTab(MainRegistry.controlTab);
		ModBlocks.ALL_BLOCKS.add(this);
	}
	
	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		if(this == ModBlocks.waste_log) {
			return Items.COAL;
		}
		if(this == ModBlocks.frozen_log) {
			return Items.SNOWBALL;
		}
		return null;
	}
	
	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		Random rand = world instanceof World ? ((World)world).rand : RANDOM;
		if(this == ModBlocks.waste_log && rand.nextInt(1000) == 0) {
	        drops.add(new ItemStack(ModItems.burnt_bark));
	        return;
    	}
		super.getDrops(drops, world, pos, state, fortune);
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		return 2 + random.nextInt(3);
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		return 1;
	}
	@Override
	public Block setSoundType(SoundType sound) {
		return super.setSoundType(sound);
	}
}
