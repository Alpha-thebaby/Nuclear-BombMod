package com.hbm.items.bomb;

import java.util.List;

import com.hbm.items.special.ItemHazard;
import com.hbm.main.MainRegistry;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGadget extends ItemHazard {

	public ItemGadget(float radiation, String s) {
		super(radiation, s);
		this.setCreativeTab(MainRegistry.nukeTab);
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<String> list, ITooltipFlag flagIn) {
		list.add("Used in:");
		list.add("The Gadget");
		super.addInformation(stack, world, list, flagIn);
	}

}
