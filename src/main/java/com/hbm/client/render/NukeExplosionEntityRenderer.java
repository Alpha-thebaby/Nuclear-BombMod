package com.hbm.client.render;

import com.hbm.entity.EntityNukeExplosionMK4;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;

public class NukeExplosionEntityRenderer extends EntityRenderer<EntityNukeExplosionMK4> {
    public NukeExplosionEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
    }

    @Override
    public Identifier getTexture(EntityNukeExplosionMK4 entity) {
        return null;
    }
}
