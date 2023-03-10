package com.hbm.client;

import com.hbm.ExampleMod;
import com.hbm.client.render.NuclearBombEntityRenderer;
import com.hbm.client.render.NukeCloudSmallEntityRenderer;
import com.hbm.client.render.NukeExplosionEntityRenderer;
import com.hbm.entity.NuclearBombEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRenderer;

@Environment(EnvType.CLIENT)
public class NukeBombClient implements ClientModInitializer {
    //public static final EntityModelLayer MODEL_CUBE_LAYER = new EntityModelLayer(new Identifier("entitytesting", "cube"), "main");
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ExampleMod.NUKE_COUD_SMALL, (context) -> {
            return new NukeCloudSmallEntityRenderer(context);
        });

        EntityRendererRegistry.register(ExampleMod.NUCLEAR_BOMB, (context) -> {
            return new NuclearBombEntityRenderer(context);
        });
        /*
        EntityRendererRegistry.register(ExampleMod.NUKE_EXPLOSION_MK4, (context) -> {
            return new NukeExplosionEntityRenderer(context);
        });
         */
        EntityRendererRegistry.register(ExampleMod.NUKE_EXPLOSION_MK4, NukeExplosionEntityRenderer::new);
        EntityRendererRegistry.register(ExampleMod.NUCLEAR_BOMB, NuclearBombEntityRenderer::new);
        //EntityModelLayerRegistry.registerModelLayer(MODEL_CUBE_LAYER, CubeEntityModel::getTexturedModelData);
        BlockRenderLayerMap.INSTANCE.putBlock(ExampleMod.NUKE_BOY, RenderLayer.getCutout());
    }
}
