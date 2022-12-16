package com.hbm.util;

import com.hbm.capability.HbmLivingCapability;
import com.hbm.capability.HbmLivingProps;
import com.hbm.entity.mob.EntityNuclearCreeper;
import com.hbm.entity.mob.EntityQuackos;
import com.hbm.handler.ArmorUtil;
import com.hbm.handler.HazmatRegistry;
import com.hbm.interfaces.IRadiationImmune;
import com.hbm.lib.Library;
import com.hbm.lib.ModDamageSource;
import com.hbm.potion.HbmPotion;
import com.hbm.saveddata.RadiationSavedData;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class ContaminationUtil {

	/**
	 * Calculates how much radiation can be applied to this entity by calculating resistance
	 * @param entity
	 * @return
	 */
	public static float calculateRadiationMod(EntityLivingBase entity) {

		if(entity.isPotionActive(HbmPotion.mutation))
			return 0;
		float mult = 1;
		if(entity.getEntityData().hasKey("hbmradmultiplier", 99))
			mult = entity.getEntityData().getFloat("hbmradmultiplier");

		float koeff = 5.0F;
		return (float) Math.pow(koeff, -HazmatRegistry.getResistance(entity)) * mult;
	}

	private static void applyRadData(Entity e, float f) {

		if(e instanceof IRadiationImmune)
			return;
		
		if(!(e instanceof EntityLivingBase))
			return;

		if(e instanceof EntityPlayer && (((EntityPlayer) e).capabilities.isCreativeMode || ((EntityPlayer) e).isSpectator()))
			return;
		
		if(e instanceof EntityPlayer && e.ticksExisted < 200)
			return;
		
		EntityLivingBase entity = (EntityLivingBase)e;

		f *= calculateRadiationMod(entity);

		if(entity.hasCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null)) {
			HbmLivingCapability.IEntityHbmProps ent = entity.getCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null);
			ent.increaseRads(f);
		}
	}

	private static void applyRadDirect(Entity entity, float f) {

		if(entity instanceof IRadiationImmune)
			return;
		
		if(entity.getEntityData().hasKey("hbmradmultiplier", 99))
			f *= entity.getEntityData().getFloat("hbmradmultiplier");
		
		if(entity instanceof EntityPlayer && (((EntityPlayer) entity).capabilities.isCreativeMode || ((EntityPlayer) entity).isSpectator()))
			return;
		
		if(!(entity instanceof EntityLivingBase))
			return;

		if(((EntityLivingBase) entity).isPotionActive(HbmPotion.mutation))
			return;

		if(entity.hasCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null)) {
			HbmLivingCapability.IEntityHbmProps ent = entity.getCapability(HbmLivingCapability.EntityHbmPropsProvider.ENT_HBM_PROPS_CAP, null);
			ent.increaseRads(f);
		}
	}

	public static void printGeigerData(EntityPlayer player) {

		double eRad = ((int)(Library.getEntRadCap(player).getRads() * 10)) / 10D;

		RadiationSavedData data = RadiationSavedData.getData(player.world);
		double rads = ((int)(data.getRadNumFromCoord(player.getPosition()) * 10)) / 10D;
		double env = ((int)(HbmLivingProps.getRadBuf(player) * 10D)) / 10D;

		double res = ((int)(10000D - ContaminationUtil.calculateRadiationMod(player) * 10000)) / 100D;
		double resKoeff = ((int)(HazmatRegistry.getResistance(player) * 100)) / 100D;

		String chunkPrefix = getPreffixFromRad(rads);
		String envPrefix = getPreffixFromRad(env);
		String radPrefix = "";
		String resPrefix = "" + TextFormatting.WHITE;

		if(eRad < 200)
			radPrefix += TextFormatting.GREEN;
		else if(eRad < 400)
			radPrefix += TextFormatting.YELLOW;
		else if(eRad < 600)
			radPrefix += TextFormatting.GOLD;
		else if(eRad < 800)
			radPrefix += TextFormatting.RED;
		else if(eRad < 1000)
			radPrefix += TextFormatting.DARK_RED;
		else
			radPrefix += TextFormatting.DARK_GRAY;

		if(resKoeff > 0)
			resPrefix += TextFormatting.GREEN;

		//localization and server-side restrictions have turned this into a painful mess
		//a *functioning* painful mess, nonetheless
		player.sendMessage(new TextComponentString("===== ☢ ").appendSibling(new TextComponentTranslation("geiger.title")).appendSibling(new TextComponentString(" ☢ =====")).setStyle(new Style().setColor(TextFormatting.GOLD)));
		player.sendMessage(new TextComponentTranslation("geiger.chunkRad").appendSibling(new TextComponentString(" " + chunkPrefix + rads + " RAD/s")).setStyle(new Style().setColor(TextFormatting.YELLOW)));
		player.sendMessage(new TextComponentTranslation("geiger.envRad").appendSibling(new TextComponentString(" " + envPrefix + env + " RAD/s")).setStyle(new Style().setColor(TextFormatting.YELLOW)));
		player.sendMessage(new TextComponentTranslation("geiger.playerRad").appendSibling(new TextComponentString(" " + radPrefix + eRad + " RAD")).setStyle(new Style().setColor(TextFormatting.YELLOW)));
		player.sendMessage(new TextComponentTranslation("geiger.playerRes").appendSibling(new TextComponentString(" " + resPrefix + res + "% (" + resKoeff + ")")).setStyle(new Style().setColor(TextFormatting.YELLOW)));
	}
	
	public static String getPreffixFromRad(double rads) {

		String chunkPrefix = "";
		
		if(rads == 0)
			chunkPrefix += TextFormatting.GREEN;
		else if(rads < 1)
			chunkPrefix += TextFormatting.YELLOW;
		else if(rads < 10)
			chunkPrefix += TextFormatting.GOLD;
		else if(rads < 100)
			chunkPrefix += TextFormatting.RED;
		else if(rads < 1000)
			chunkPrefix += TextFormatting.DARK_RED;
		else
			chunkPrefix += TextFormatting.DARK_GRAY;
		
		return chunkPrefix;
	}
	
	public static float getRads(Entity e) {
		if(e instanceof IRadiationImmune)
			return 0.0F;
		return Library.getEntRadCap(e).getRads();
	}
	
	public static boolean isRadImmune(Entity e) {

		if(e instanceof EntityLivingBase && ((EntityLivingBase)e).isPotionActive(HbmPotion.mutation))
			return true;
		
		return e instanceof EntityNuclearCreeper ||
				e instanceof EntityMooshroom ||
				e instanceof EntityZombie ||
				e instanceof EntitySkeleton ||
				e instanceof EntityQuackos ||
				e instanceof EntityOcelot ||
				e instanceof IRadiationImmune;
	}
	
	/// ASBESTOS ///
		public static void applyAsbestos(Entity e, int i) {

			if(!(e instanceof EntityLivingBase))
				return;
			
			if(e instanceof EntityPlayer && ((EntityPlayer)e).capabilities.isCreativeMode)
				return;
			
			if(e instanceof EntityPlayer && e.ticksExisted < 200)
				return;
			
			EntityLivingBase entity = (EntityLivingBase)e;
			
			if(!(entity instanceof EntityPlayer && ArmorUtil.checkForGasMask((EntityPlayer) entity)))
				HbmLivingProps.incrementAsbestos(entity, i);
		}
		
		/// DIGAMMA ///
		public static void applyDigammaData(Entity e, float f) {

			if(!(e instanceof EntityLivingBase))
				return;
			
			if(e instanceof EntityPlayer && ((EntityPlayer)e).capabilities.isCreativeMode)
				return;
			
			if(e instanceof EntityPlayer && e.ticksExisted < 200)
				return;
			
			EntityLivingBase entity = (EntityLivingBase)e;
			
			if(entity.isPotionActive(HbmPotion.stability))
				return;
			
			if(!(entity instanceof EntityPlayer && ArmorUtil.checkForDigamma((EntityPlayer) entity)))
				HbmLivingProps.incrementDigamma(entity, f);
		}
		
		public static void applyDigammaDirect(Entity e, float f) {

			if(!(e instanceof EntityLivingBase))
				return;

			if(e instanceof IRadiationImmune)
				return;
			
			if(e instanceof EntityPlayer && ((EntityPlayer)e).capabilities.isCreativeMode)
				return;
			
			EntityLivingBase entity = (EntityLivingBase)e;
			HbmLivingProps.incrementDigamma(entity, f);
		}
		
		public static float getDigamma(Entity e) {

			if(!(e instanceof EntityLivingBase))
				return 0.0F;
			
			EntityLivingBase entity = (EntityLivingBase)e;
			return HbmLivingProps.getDigamma(entity);
		}
	
	public static enum HazardType {
		MONOXIDE,
		RADIATION,
		ASBESTOS,
		DIGAMMA
	}
	
	public static enum ContaminationType {
		GAS,				//filterable by gas mask
		GAS_NON_REACTIVE,	//not filterable by gas mask
		GOGGLES,			//preventable by goggles
		FARADAY,			//preventable by metal armor
		HAZMAT,				//preventable by hazmat
		HAZMAT2,			//preventable by heavy hazmat
		DIGAMMA,			//preventable by fau armor or stability
		DIGAMMA2,			//preventable by robes
		CREATIVE,			//preventable by creative mode, for rad calculation armor piece bonuses still apply
		RAD_BYPASS,			//same as creaative but fill not apply radiation resistance calculation
		NONE				//not preventable
	}
	
	/*
	 * This system is nice but the cont types are a bit confusing. Cont types should have much better names and multiple cont types should be applicable.
	 */
	@SuppressWarnings("incomplete-switch") //just shut up
	public static boolean contaminate(EntityLivingBase entity, HazardType hazard, ContaminationType cont, float amount) {
		
		if(hazard == HazardType.RADIATION) {
			float radEnv = HbmLivingProps.getRadEnv(entity);
			HbmLivingProps.setRadEnv(entity, radEnv + amount);
		}
		
		if(entity instanceof EntityPlayer) {
			
			EntityPlayer player = (EntityPlayer)entity;
			
			switch(cont) {
			case GAS:				if(ArmorUtil.checkForGasMask(player))	return false; break;
			case GAS_NON_REACTIVE:	if(ArmorUtil.checkForMonoMask(player))	return false; break;
			case GOGGLES:			if(ArmorUtil.checkForGoggles(player))	return false; break;
			case FARADAY:			if(ArmorUtil.checkForFaraday(player))	return false; break;
			case HAZMAT:			if(ArmorUtil.checkForHazmat(player))	return false; break;
			case HAZMAT2:			if(ArmorUtil.checkForHaz2(player))		return false; break;
			case DIGAMMA:			if(ArmorUtil.checkForDigamma(player))	return false; break;
			case DIGAMMA2: break;
			}
			
			if(player.capabilities.isCreativeMode && cont != ContaminationType.NONE)
				return false;
			
			if(player.ticksExisted < 200)
				return false;
		}
		
		if(hazard == HazardType.RADIATION && isRadImmune(entity))
			return false;
		
		switch(hazard) {
		case MONOXIDE: entity.attackEntityFrom(ModDamageSource.monoxide, amount); break;
		case RADIATION: HbmLivingProps.incrementRadiation(entity, amount * (cont == ContaminationType.RAD_BYPASS ? 1 : calculateRadiationMod(entity))); break;
		case ASBESTOS: HbmLivingProps.incrementAsbestos(entity, (int)amount); break;
		case DIGAMMA: HbmLivingProps.incrementDigamma(entity, amount); break;
		}
		
		return true;
	}
}