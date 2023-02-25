package com.hbm.client.model;

import com.hbm.entity.EntityNukeExplosionMK4;
import com.hbm.entity.NuclearBombEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class NukeCloudEntityModel extends GeoModel<NuclearBombEntity> {
    @Override
    public Identifier getModelResource(NuclearBombEntity object) {
        //return new Identifier("hbm", "geo/nuclear_explosion.geo.json");
        return null;
    }

    @Override
    public Identifier getTextureResource(NuclearBombEntity object) {
        return new Identifier("hbm", "textures/entity/nuclear_explosion.png");
    }

    @Override
    public Identifier getAnimationResource(NuclearBombEntity animatable) {
        return new Identifier("hbm", "animations/nuclear_explosion.animation.json");
    }
}
