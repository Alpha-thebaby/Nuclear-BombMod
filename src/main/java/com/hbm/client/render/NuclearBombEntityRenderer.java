package com.hbm.client.render;

import com.hbm.client.model.NuclearBombModel;
import com.hbm.client.model.NukeCloudEntityModel;
import com.hbm.entity.EntityNukeExplosionMK4;
import com.hbm.entity.NuclearBombEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NuclearBombEntityRenderer extends GeoEntityRenderer<NuclearBombEntity> {
    public NuclearBombEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new NuclearBombModel());
    }
}
