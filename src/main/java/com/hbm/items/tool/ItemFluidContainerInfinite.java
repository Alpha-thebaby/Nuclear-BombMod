package com.hbm.items.tool;

import com.hbm.forgefluid.HbmFluidHandlerItemStackInf;
import com.hbm.items.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class ItemFluidContainerInfinite extends Item {

	private int maxDrain;
	
	public ItemFluidContainerInfinite(int maxDrain, String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.maxDrain = maxDrain;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new HbmFluidHandlerItemStackInf(stack, maxDrain);
	}
}
