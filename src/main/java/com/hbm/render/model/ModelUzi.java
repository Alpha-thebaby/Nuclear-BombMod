package com.hbm.render.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelUzi extends ModelBase {

	ModelRenderer BodyBack;
	ModelRenderer BodyBottom;
	ModelRenderer BodySide;
	ModelRenderer Ejector;
	ModelRenderer BodyFront;
	ModelRenderer GripFront;
	ModelRenderer StockFront;
	ModelRenderer StockPlate;
	ModelRenderer HandleBase;
	ModelRenderer Handle;
	ModelRenderer HandleBack;
	ModelRenderer HandleBottom;
	ModelRenderer HandlePlate;
	ModelRenderer HandleTop;
	ModelRenderer Muzzle1;
	ModelRenderer Muzzle2;
	ModelRenderer StockBack;
	ModelRenderer StockPlate1;
	ModelRenderer Stock1;
	ModelRenderer Stock2;
	ModelRenderer Stcok3;
	ModelRenderer Stock4;
	ModelRenderer Hump1;
	ModelRenderer Hump2;
	ModelRenderer Hump3;
	ModelRenderer Hump4;
	ModelRenderer Hump5;
	ModelRenderer Hump6;
	ModelRenderer SightBack;
	ModelRenderer Sight1;
	ModelRenderer Sight2;
	ModelRenderer Hump7;
	ModelRenderer Hump8;
	ModelRenderer Hump9;
	ModelRenderer Hump10;
	ModelRenderer Hump11;
	ModelRenderer Hump12;
	ModelRenderer Sight3;
	ModelRenderer SightFront;
	ModelRenderer Knob1;
	ModelRenderer Knob2;
	ModelRenderer Bolt1;
	ModelRenderer Bolt2;
	ModelRenderer Mag;
	ModelRenderer Trigger;
	ModelRenderer Frame1;
	ModelRenderer Frame2;

	public ModelUzi() {
		textureWidth = 128;
		textureHeight = 64;

		BodyBack = new ModelRenderer(this, 0, 0);
		BodyBack.addBox(0F, 0F, 0F, 14, 6, 4);
		BodyBack.setRotationPoint(0F, 0F, 0F);
		BodyBack.setTextureSize(128, 64);
		BodyBack.mirror = true;
		setRotation(BodyBack, 0F, 0F, 0F);
		BodyBottom = new ModelRenderer(this, 82, 0);
		BodyBottom.addBox(0F, 0F, 0F, 6, 3, 4);
		BodyBottom.setRotationPoint(-6F, 3F, 0F);
		BodyBottom.setTextureSize(128, 64);
		BodyBottom.mirror = true;
		setRotation(BodyBottom, 0F, 0F, 0F);
		BodySide = new ModelRenderer(this, 102, 0);
		BodySide.addBox(0F, 0F, 0F, 6, 3, 2);
		BodySide.setRotationPoint(-6F, 0F, 0F);
		BodySide.setTextureSize(128, 64);
		BodySide.mirror = true;
		setRotation(BodySide, 0F, 0F, 0F);
		Ejector = new ModelRenderer(this, 102, 5);
		Ejector.addBox(0F, 0F, 0F, 6, 3, 2);
		Ejector.setRotationPoint(-6F, 0.5F, 1.5F);
		Ejector.setTextureSize(128, 64);
		Ejector.mirror = true;
		setRotation(Ejector, 0F, 0F, 0F);
		BodyFront = new ModelRenderer(this, 36, 0);
		BodyFront.addBox(0F, 0F, 0F, 19, 6, 4);
		BodyFront.setRotationPoint(-25F, 0F, 0F);
		BodyFront.setTextureSize(128, 64);
		BodyFront.mirror = true;
		setRotation(BodyFront, 0F, 0F, 0F);
		GripFront = new ModelRenderer(this, 0, 10);
		GripFront.addBox(0F, 0F, 0F, 14, 5, 5);
		GripFront.setRotationPoint(-24.5F, 1.5F, -0.5F);
		GripFront.setTextureSize(128, 64);
		GripFront.mirror = true;
		setRotation(GripFront, 0F, 0F, 0F);
		StockFront = new ModelRenderer(this, 38, 10);
		StockFront.addBox(0F, 0F, 0F, 14, 3, 4);
		StockFront.setRotationPoint(3F, 6F, 0F);
		StockFront.setTextureSize(128, 64);
		StockFront.mirror = true;
		setRotation(StockFront, 0F, 0F, 0F);
		StockPlate = new ModelRenderer(this, 118, 0);
		StockPlate.addBox(0F, -4F, 0F, 1, 4, 4);
		StockPlate.setRotationPoint(3F, 9F, 0F);
		StockPlate.setTextureSize(128, 64);
		StockPlate.mirror = true;
		setRotation(StockPlate, 0F, 0F, -0.3490659F);
		HandleBase = new ModelRenderer(this, 0, 20);
		HandleBase.addBox(0F, 0F, 0F, 12, 3, 5);
		HandleBase.setRotationPoint(-10F, 4F, -0.5F);
		HandleBase.setTextureSize(128, 64);
		HandleBase.mirror = true;
		setRotation(HandleBase, 0F, 0F, 0F);
		Handle = new ModelRenderer(this, 0, 28);
		Handle.addBox(0F, 0F, 0F, 4, 10, 3);
		Handle.setRotationPoint(-5F, 7F, 0.5F);
		Handle.setTextureSize(128, 64);
		Handle.mirror = true;
		setRotation(Handle, 0F, 0F, 0F);
		HandleBack = new ModelRenderer(this, 14, 28);
		HandleBack.addBox(0F, 0F, 0F, 2, 4, 3);
		HandleBack.setRotationPoint(-1F, 11F, 0.5F);
		HandleBack.setTextureSize(128, 64);
		HandleBack.mirror = true;
		setRotation(HandleBack, 0F, 0F, 0F);
		HandleBottom = new ModelRenderer(this, 14, 35);
		HandleBottom.addBox(-2F, 0F, 0F, 2, 3, 3);
		HandleBottom.setRotationPoint(1F, 15F, 0.5F);
		HandleBottom.setTextureSize(128, 64);
		HandleBottom.mirror = true;
		setRotation(HandleBottom, 0F, 0F, 0.7853982F);
		HandlePlate = new ModelRenderer(this, 24, 28);
		HandlePlate.addBox(-2F, -3F, 0F, 2, 3, 3);
		HandlePlate.setRotationPoint(1F, 11F, 0.5F);
		HandlePlate.setTextureSize(128, 64);
		HandlePlate.mirror = true;
		setRotation(HandlePlate, 0F, 0F, -0.4886922F);
		HandleTop = new ModelRenderer(this, 24, 34);
		HandleTop.addBox(-2F, 0F, 0F, 2, 3, 3);
		HandleTop.setRotationPoint(1F, 7F, 0.5F);
		HandleTop.setTextureSize(128, 64);
		HandleTop.mirror = true;
		setRotation(HandleTop, 0F, 0F, 0.5235988F);
		Muzzle1 = new ModelRenderer(this, 82, 12);
		Muzzle1.addBox(0F, 0F, 0F, 3, 3, 2);
		Muzzle1.setRotationPoint(-28F, 1.5F, 1F);
		Muzzle1.setTextureSize(128, 64);
		Muzzle1.mirror = true;
		setRotation(Muzzle1, 0F, 0F, 0F);
		Muzzle2 = new ModelRenderer(this, 82, 7);
		Muzzle2.addBox(0F, 0F, 0F, 3, 2, 3);
		Muzzle2.setRotationPoint(-28F, 2F, 0.5F);
		Muzzle2.setTextureSize(128, 64);
		Muzzle2.mirror = true;
		setRotation(Muzzle2, 0F, 0F, 0F);
		StockBack = new ModelRenderer(this, 34, 20);
		StockBack.addBox(0F, 0F, 0F, 1, 11, 3);
		StockBack.setRotationPoint(14F, 7F, 0.5F);
		StockBack.setTextureSize(128, 64);
		StockBack.mirror = true;
		setRotation(StockBack, 0F, 0F, 0F);
		StockPlate1 = new ModelRenderer(this, 42, 17);
		StockPlate1.addBox(-1F, -11F, 0F, 1, 11, 3);
		StockPlate1.setRotationPoint(15F, 18F, 0.5F);
		StockPlate1.setTextureSize(128, 64);
		StockPlate1.mirror = true;
		setRotation(StockPlate1, 0F, 0F, 0.0959931F);
		Stock1 = new ModelRenderer(this, 50, 17);
		Stock1.addBox(-1F, -3F, 0F, 1, 3, 4);
		Stock1.setRotationPoint(17F, 6F, 0F);
		Stock1.setTextureSize(128, 64);
		Stock1.mirror = true;
		setRotation(Stock1, 0F, 0F, -0.3490659F);
		Stock2 = new ModelRenderer(this, 60, 17);
		Stock2.addBox(0F, -3F, 0F, 1, 3, 4);
		Stock2.setRotationPoint(14F, 6F, 0F);
		Stock2.setTextureSize(128, 64);
		Stock2.mirror = true;
		setRotation(Stock2, 0F, 0F, 0.3490659F);
		Stcok3 = new ModelRenderer(this, 50, 24);
		Stcok3.addBox(0F, 0F, 0F, 1, 3, 4);
		Stcok3.setRotationPoint(15F, 3F, 0F);
		Stcok3.setTextureSize(128, 64);
		Stcok3.mirror = true;
		setRotation(Stcok3, 0F, 0F, 0F);
		Stock4 = new ModelRenderer(this, 60, 24);
		Stock4.addBox(0F, 0F, 0F, 2, 5, 3);
		Stock4.setRotationPoint(13.5F, 1.5F, 0.5F);
		Stock4.setTextureSize(128, 64);
		Stock4.mirror = true;
		setRotation(Stock4, 0F, 0F, 0F);
		Hump1 = new ModelRenderer(this, 74, 10);
		Hump1.addBox(-2F, -3F, 0F, 2, 3, 1);
		Hump1.setRotationPoint(14F, 0F, 0F);
		Hump1.setTextureSize(128, 64);
		Hump1.mirror = true;
		setRotation(Hump1, 0F, 0F, -0.2617994F);
		Hump2 = new ModelRenderer(this, 74, 14);
		Hump2.addBox(0F, -3F, 0F, 2, 3, 1);
		Hump2.setRotationPoint(10F, 0F, 0F);
		Hump2.setTextureSize(128, 64);
		Hump2.mirror = true;
		setRotation(Hump2, 0F, 0F, 0.2617994F);
		Hump3 = new ModelRenderer(this, 74, 18);
		Hump3.addBox(0F, 0F, 0F, 2, 1, 1);
		Hump3.setRotationPoint(11F, -3F, 0F);
		Hump3.setTextureSize(128, 64);
		Hump3.mirror = true;
		setRotation(Hump3, 0F, 0F, 0F);
		Hump4 = new ModelRenderer(this, 70, 20);
		Hump4.addBox(-2F, -3F, 0F, 2, 3, 1);
		Hump4.setRotationPoint(14F, 0F, 3F);
		Hump4.setTextureSize(128, 64);
		Hump4.mirror = true;
		setRotation(Hump4, 0F, 0F, -0.2617994F);
		Hump5 = new ModelRenderer(this, 70, 24);
		Hump5.addBox(0F, -3F, 0F, 2, 3, 1);
		Hump5.setRotationPoint(10F, 0F, 3F);
		Hump5.setTextureSize(128, 64);
		Hump5.mirror = true;
		setRotation(Hump5, 0F, 0F, 0.2617994F);
		Hump6 = new ModelRenderer(this, 70, 28);
		Hump6.addBox(0F, 0F, 0F, 2, 1, 1);
		Hump6.setRotationPoint(11F, -3F, 3F);
		Hump6.setTextureSize(128, 64);
		Hump6.mirror = true;
		setRotation(Hump6, 0F, 0F, 0F);
		SightBack = new ModelRenderer(this, 80, 17);
		SightBack.addBox(0F, 0F, 0F, 1, 1, 1);
		SightBack.setRotationPoint(11.5F, -2F, 1.5F);
		SightBack.setTextureSize(128, 64);
		SightBack.mirror = true;
		setRotation(SightBack, 0F, 0F, 0F);
		Sight1 = new ModelRenderer(this, 80, 19);
		Sight1.addBox(0F, 0F, 0F, 1, 1, 1);
		Sight1.setRotationPoint(11.5F, -1F, 1.5F);
		Sight1.setTextureSize(128, 64);
		Sight1.mirror = true;
		setRotation(Sight1, 0F, 0F, 0F);
		Sight2 = new ModelRenderer(this, 84, 17);
		Sight2.addBox(0F, 0F, 0F, 2, 1, 2);
		Sight2.setRotationPoint(11F, -0.5F, 1F);
		Sight2.setTextureSize(128, 64);
		Sight2.mirror = true;
		setRotation(Sight2, 0F, 0F, 0F);
		Hump7 = new ModelRenderer(this, 94, 7);
		Hump7.addBox(-2F, -3F, 0F, 2, 3, 1);
		Hump7.setRotationPoint(-21F, 0F, 0F);
		Hump7.setTextureSize(128, 64);
		Hump7.mirror = true;
		setRotation(Hump7, 0F, 0F, -0.2617994F);
		Hump8 = new ModelRenderer(this, 94, 11);
		Hump8.addBox(0F, -3F, 0F, 2, 3, 1);
		Hump8.setRotationPoint(-25F, 0F, 0F);
		Hump8.setTextureSize(128, 64);
		Hump8.mirror = true;
		setRotation(Hump8, 0F, 0F, 0.2617994F);
		Hump9 = new ModelRenderer(this, 94, 15);
		Hump9.addBox(0F, 0F, 0F, 2, 1, 1);
		Hump9.setRotationPoint(-24F, -3F, 0F);
		Hump9.setTextureSize(128, 64);
		Hump9.mirror = true;
		setRotation(Hump9, 0F, 0F, 0F);
		Hump10 = new ModelRenderer(this, 100, 10);
		Hump10.addBox(-2F, -3F, 0F, 2, 3, 1);
		Hump10.setRotationPoint(-21F, 0F, 3F);
		Hump10.setTextureSize(128, 64);
		Hump10.mirror = true;
		setRotation(Hump10, 0F, 0F, -0.2617994F);
		Hump11 = new ModelRenderer(this, 100, 14);
		Hump11.addBox(0F, -3F, 0F, 2, 3, 1);
		Hump11.setRotationPoint(-25F, 0F, 3F);
		Hump11.setTextureSize(128, 64);
		Hump11.mirror = true;
		setRotation(Hump11, 0F, 0F, 0.2617994F);
		Hump12 = new ModelRenderer(this, 100, 18);
		Hump12.addBox(0F, 0F, 0F, 2, 1, 1);
		Hump12.setRotationPoint(-24F, -3F, 3F);
		Hump12.setTextureSize(128, 64);
		Hump12.mirror = true;
		setRotation(Hump12, 0F, 0F, 0F);
		Sight3 = new ModelRenderer(this, 34, 34);
		Sight3.addBox(0F, 0F, 0F, 2, 1, 2);
		Sight3.setRotationPoint(-24F, -0.5F, 1F);
		Sight3.setTextureSize(128, 64);
		Sight3.mirror = true;
		setRotation(Sight3, 0F, 0F, 0F);
		SightFront = new ModelRenderer(this, 34, 37);
		SightFront.addBox(0F, 0F, 0F, 1, 2, 0);
		SightFront.setRotationPoint(-23.5F, -1.5F, 2F);
		SightFront.setTextureSize(128, 64);
		SightFront.mirror = true;
		setRotation(SightFront, 0F, 0F, 0F);
		Knob1 = new ModelRenderer(this, 42, 31);
		Knob1.addBox(0F, 0F, 0F, 2, 2, 2);
		Knob1.setRotationPoint(-15F, -2F, 1F);
		Knob1.setTextureSize(128, 64);
		Knob1.mirror = true;
		setRotation(Knob1, 0F, 0F, 0F);
		Knob2 = new ModelRenderer(this, 50, 31);
		Knob2.addBox(-1F, 0F, -1F, 2, 2, 2);
		Knob2.setRotationPoint(-14F, -2F, 2F);
		Knob2.setTextureSize(128, 64);
		Knob2.mirror = true;
		setRotation(Knob2, 0F, 0.7853982F, 0F);
		Bolt1 = new ModelRenderer(this, 42, 35);
		Bolt1.addBox(0F, 0F, 0F, 1, 1, 1);
		Bolt1.setRotationPoint(11.5F, -2F, -0.5F);
		Bolt1.setTextureSize(128, 64);
		Bolt1.mirror = true;
		setRotation(Bolt1, 0F, 0F, 0F);
		Bolt2 = new ModelRenderer(this, 46, 35);
		Bolt2.addBox(0F, 0F, 0F, 1, 1, 1);
		Bolt2.setRotationPoint(11.5F, -2F, 3.5F);
		Bolt2.setTextureSize(128, 64);
		Bolt2.mirror = true;
		setRotation(Bolt2, 0F, 0F, 0F);
		Mag = new ModelRenderer(this, 118, 8);
		Mag.addBox(0F, 0F, 0F, 3, 10, 2);
		Mag.setRotationPoint(-4.5F, 17F, 1F);
		Mag.setTextureSize(128, 64);
		Mag.mirror = true;
		setRotation(Mag, 0F, 0F, 0F);
		Trigger = new ModelRenderer(this, 0, 41);
		Trigger.addBox(-1F, 0F, 0F, 1, 3, 1);
		Trigger.setRotationPoint(-6F, 7F, 1.5F);
		Trigger.setTextureSize(128, 64);
		Trigger.mirror = true;
		setRotation(Trigger, 0F, 0F, 0.3490659F);
		Frame1 = new ModelRenderer(this, 4, 41);
		Frame1.addBox(0F, 0F, 0F, 3, 4, 2);
		Frame1.setRotationPoint(-10F, 7F, 1F);
		Frame1.setTextureSize(128, 64);
		Frame1.mirror = true;
		setRotation(Frame1, 0F, 0F, 0F);
		Frame2 = new ModelRenderer(this, 14, 41);
		Frame2.addBox(0F, -1F, 0F, 3, 1, 2);
		Frame2.setRotationPoint(-7F, 11F, 1F);
		Frame2.setTextureSize(128, 64);
		Frame2.mirror = true;
		setRotation(Frame2, 0F, 0F, -0.4363323F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GL11.glDisable(GL11.GL_CULL_FACE);
		BodyBack.render(f5);
		BodyBottom.render(f5);
		BodySide.render(f5);
		Ejector.render(f5);
		BodyFront.render(f5);
		GripFront.render(f5);
		StockFront.render(f5);
		StockPlate.render(f5);
		HandleBase.render(f5);
		Handle.render(f5);
		HandleBack.render(f5);
		HandleBottom.render(f5);
		HandlePlate.render(f5);
		HandleTop.render(f5);
		Muzzle1.render(f5);
		Muzzle2.render(f5);
		StockBack.render(f5);
		StockPlate1.render(f5);
		Stock1.render(f5);
		Stock2.render(f5);
		Stcok3.render(f5);
		Stock4.render(f5);
		Hump1.render(f5);
		Hump2.render(f5);
		Hump3.render(f5);
		Hump4.render(f5);
		Hump5.render(f5);
		Hump6.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
		SightBack.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
		Sight1.render(f5);
		Sight2.render(f5);
		Hump7.render(f5);
		Hump8.render(f5);
		Hump9.render(f5);
		Hump10.render(f5);
		Hump11.render(f5);
		Hump12.render(f5);
		Sight3.render(f5);
		SightFront.render(f5);
		Knob1.render(f5);
		Knob2.render(f5);
		Bolt1.render(f5);
		Bolt2.render(f5);
		Mag.render(f5);
		GL11.glDisable(GL11.GL_CULL_FACE);
		Trigger.render(f5);
		Frame1.render(f5);
		Frame2.render(f5);
		GL11.glEnable(GL11.GL_CULL_FACE);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
