package com.hbm.util;

import com.ultreon.mods.myron.api.Myron;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import org.joml.Vector3f;

public class ObjModelHelper {
    public void render(Identifier MODEL, MatrixStack matrices, VertexConsumerProvider vertexConsumers, Random random, int light, int overlay) {
        BakedModel model = Myron.getModel(MODEL);

        if (model != null) {
            VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getSolid());
            matrices.push();
            MatrixStack.Entry entry = matrices.peek();
            model.getQuads(null, null, random).forEach(quad -> {
                consumer.quad(entry, quad, 1F, 1F, 1F, light, overlay);
            });

            matrices.pop();
        }
    }
}
