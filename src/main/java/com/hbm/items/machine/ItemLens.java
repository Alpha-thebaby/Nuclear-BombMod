package com.hbm.items.machine;

import java.util.List;

import com.hbm.items.ModItems;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemLens extends Item {

	public long maxDamage;
	
	public ItemLens(long maxDamage, String s) {
		this.setUnlocalizedName(s);
		this.setRegistryName(s);
		this.maxDamage = maxDamage;
		
		ModItems.ALL_ITEMS.add(this);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		long damage = getLensDamage(stack);
		int percent = (int) ((maxDamage - damage) * 100 / maxDamage);
		
		tooltip.add("Durability: " + (maxDamage - damage) + "/" + maxDamage + " (" + percent + "%)");
	}
	
	@Override
    public double getDurabilityForDisplay(ItemStack stack){
        return (double)getLensDamage(stack) / (double)maxDamage;
    }
    
    @Override
    public boolean showDurabilityBar(ItemStack stack){
        return getDurabilityForDisplay(stack) != 0;
    }
	
	public static long getLensDamage(ItemStack stack) {
		
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		
		return stack.getTagCompound().getLong("damage");
	}
	
	public static void setLensDamage(ItemStack stack, long damage) {
		
		if(!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.getTagCompound().setLong("damage", damage);
	}
	
	
}
