package com.hbm.client.render;

import com.hbm.ExampleMod;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import com.hbm.util.ObjModelHelper;
import com.mojang.blaze3d.platform.GlStateManager;
import com.ultreon.mods.myron.api.Myron;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.math.random.Random;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;

import static com.mojang.blaze3d.systems.RenderSystem.bindTexture;

public class NukeCloudSmallEntityRenderer extends EntityRenderer<EntityNukeCloudSmall> {
    public static final EntityRendererFactory<EntityNukeCloudSmall> FACTORY = man -> new NukeCloudSmallEntityRenderer(man);

    private static final Identifier MUSH = new Identifier("hbm", "models/misc/effect/mush");
    private static final Identifier SHOCKWAVE = new Identifier("hbm", "models/misc/effect/ring_roller");
    private static final Identifier THINRING = new Identifier("hbm", "models/misc/effect/ring_thin");



    Random random = Random.create();
    private static final Identifier cloudlet = new Identifier("hbm" + ":textures/particle/particle_base.png");

    public NukeCloudSmallEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    public void render(EntityNukeCloudSmall cloud, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        //GL11.glPushMatrix();
        //GL11.glTranslated(x, y, z);
        //ExampleMod.LOGGER.info("Getting models");
        BakedModel mush = Myron.getModel(MUSH);
        BakedModel shockwave = Myron.getModel(SHOCKWAVE);
        BakedModel thinring = Myron.getModel(THINRING);

        if (mush != null) {
            //ExampleMod.LOGGER.info("Rendering mushroom");
            matrices.push();
            //matrices.translate(cloud.getX(), cloud.getY(), cloud.getZ());
            MatrixStack.Entry entry = matrices.peek();
            VertexConsumer consumer = vertexConsumers.getBuffer(RenderLayer.getSolid());
            // scrolling texture
            //matrices.translate(0, -(cloud.age) * 0.035, 0);

            Vector4f quads = entry.getPositionMatrix().transform(new Vector4f(0F, 0F, 0F, 0F));
            Vector3f normal = entry.getNormalMatrix().transform(new Vector3f(0F, 0F, 0F));
            //m = byteBuffer.getFloat(16);
            mush.getQuads(null, null, cloud.getWorld().random).forEach(quad -> {
                consumer.quad(entry, quad, 1F, 1F, 1F, light, 0);
                /*
                    float m = 0;
                    float n = 0;
                    ExampleMod.LOGGER.info(Arrays.toString(quad.getVertexData()));
                    for (int l = 0; l < 4; l++) {
                        consumer.vertex(
                                Float.intBitsToFloat(quad.getVertexData()[l * 8]),
                                Float.intBitsToFloat(quad.getVertexData()[l * 8 + 1]),
                                Float.intBitsToFloat(quad.getVertexData()[l * 8 + 2]),
                                1F, 1F, 1F, 1.0F,
                                m, (float) (n - (cloud.age) * 0.035),
                                0, light,
                                normal.x, normal.y, normal.z);
                    }
                 */
                });
            matrices.pop();
        }

        //(cloud, partialTicks);
        //cloudletWrapper(cloud, partialTicks);
        flashWrapper(cloud, 0, matrices, vertexConsumers);

        //GL11.glPopMatrix();
    }

    // Most of the code below this method doesn't actually run.
    // It's just a hold-over from the original code to help me work out the porting.
    // - Random

    @Override
    public Identifier getTexture(EntityNukeCloudSmall entity) {
        return null;
    }

    /*
     *     //      //  //////  //////  //////  //////  //////  //////  //////
     *    //      //  //  //  //  //  //  //  //  //  //      //  //  //
     *   //  //  //  ////    //////  //////  //////  ////    ////    //////
     *  ////  ////  //  //  //  //  //      //      //      //  //      //
     * //      //  //  //  //  //  //      //      //////  //  //  //////
     */

    /**
     * Wrapper for the initial flash
     * Caps the rendering at 60 ticks and sets the alpha function
     * @param cloud
     * @param interp
     */
    private void flashWrapper(EntityNukeCloudSmall cloud, float interp, MatrixStack matrices, VertexConsumerProvider vertices) {

        if(cloud.age < 60) {
            matrices.push();
            //Function [0, 1] that determines the scale and intensity (inverse!) of the flash
            double scale = (cloud.age + interp) / 60D;
            //GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0F);

            //Euler function to slow down the scale as it progresses
            //Makes it start fast and the fade-out is nice and smooth
            scale = scale * Math.pow(Math.E, -scale) * 2.717391304D;

            renderFlash(scale, matrices, vertices);
            //GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
            matrices.pop();
        }
    }

    /**
     * Wrapper for the entire mush (head + stem)
     * Renders the entire thing twice to allow for smooth color gradients
     * @param cloud
     * @param interp
     */
    private void mushWrapper(EntityNukeCloudSmall cloud, float interp) {

        float size = cloud.getDataTracker().get(EntityNukeCloudSmall.SCALE) * 5;

        GL11.glPushMatrix();

        GL11.glScalef(size, size, size);

        boolean balefire = cloud.getDataTracker().get(EntityNukeCloudSmall.TYPE) == 1;

        /*
        if(balefire)
            bindTexture(ResourceManager.balefire);
        else
            bindTexture(ResourceManager.fireball);

         */

        GlStateManager._disableCull();
        GlStateManager._disableTexture();
        //GlStateManager._disableLighting();

        //Float [0, 1] for the initial solid-colored layer fade-in
        float func = MathHelper.clamp((cloud.age + interp) * 0.0075F, 0, 1);
        //Function that determines how high the cloud has risen. The values are the results of trial and error and i forgot what they mean
        double height = Math.max(20 - 30 * 20 / ((((cloud.age + interp) * 0.5) - 60 * 0.1) + 1), 0);

        if(balefire)
            GlStateManager._clearColor(1.0F - (1.0F - 0.64F) * func, 1.0F, 1.0F - (1.0F - 0.5F) * func, 1F);
        else
            GlStateManager._clearColor(1.0F, 1.0F - (1.0F - 0.7F) * func, 1.0F - (1.0F - 0.48F) * func, 1F);

        renderMushHead(cloud.age + interp, height);
        renderMushStem(cloud.age + interp, height);
        //GlStateManager._enableLighting();
        GlStateManager._enableTexture();

        //Float [0.75, 0] That determines the occupancy of the texture layer
        float texAlpha = func * 0.875F;

        GlStateManager._clearColor(1, 1, 1, texAlpha);
        //Sets blend to "how you'd expect it" mode
        //Drillgon200: AKA src alpha, one minus src alpha
        GlStateManager._blendFuncSeparate(770, 771, 1, 0);
        GlStateManager._enableBlend();

        //And now we fuck with texture transformations
        //GlStateManager.matrixMode(GL11.GL_TEXTURE);
        GL11.glLoadIdentity();

        GL11.glTranslated(0, -(cloud.age + interp) * 0.035, 0);

        //GlStateManager.matrixMode(GL11.GL_MODELVIEW);

        GL11.glPushMatrix();
        //It's the thing that makes glow-in-the-dark work
        GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
        //OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        renderMushHead(cloud.age + interp, height);
        renderMushStem(cloud.age + interp, height);
        GL11.glPopAttrib();
        //GlStateManager._enableLighting();
        GL11.glPopMatrix();

        //Clean this up otherwise the game becomes one-dimensional
        //Drillgon200: Really should have used push/pop matrix here, but whatever.
        // Please send help.  - Random
        //GlStateManager.matrixMode(GL11.GL_TEXTURE);
        GL11.glLoadIdentity();
        //GlStateManager.matrixMode(GL11.GL_MODELVIEW);

        GlStateManager._disableBlend();
        GlStateManager._enableCull();

        GL11.glPopMatrix();
    }

    /**
     * Adds all cloudlets to the tessellator and then draws them
     * @param cloud
     * @param interp
     */
    private void cloudletWrapper(EntityNukeCloudSmall cloud, float interp) {

        GL11.glPushMatrix();
        GlStateManager._enableBlend();
        //To prevent particles cutting off before fully fading out
        //Drillgon200: What'd the point of setting the alpha func if you're just going to disable the test anyway?
        // This contributes to my overall happiness.  - Random
        /*
        GlStateManager._alphaFunc(GL11.GL_GEQUAL, 0.01F);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableAlpha();
        GlStateManager.depthMask(false);
         */

        //bindTexture(cloudlet);

        Tessellator tess = Tessellator.getInstance();
        BufferBuilder buf = tess.getBuffer();
        buf.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE_COLOR);

        for(EntityNukeCloudSmall.Cloudlet cloudlet : cloud.cloudlets) {
            float scale = cloud.age + interp - cloudlet.age;
            tessellateCloudlet(buf, cloudlet.posX, cloudlet.posY - cloud.getY() + 2, cloudlet.posZ, scale, (int) cloud.getDataTracker().get(EntityNukeCloudSmall.TYPE));
        }
        tess.draw();

    	/*Random rand = new Random(cloud.getEntityId());
    	float size = cloud.getDataWatcher().getWatchableObjectFloat(18);

    	for(int i = 0; i < 300 * size; i++) {

    		float scale = size * 10;
    		Vec3 vec = Vec3.createVectorHelper(rand.nextGaussian() * scale, 0, rand.nextGaussian() * scale);

    		tessellateCloudlet(tess, vec.xCoord, (scale - vec.lengthVector()) * rand.nextDouble() * 0.5, vec.zCoord - 10, (float)(cloud.age * cloud.cloudletLife) / cloud.maxAge, cloud.getDataWatcher().getWatchableObjectByte(19));
    	}*/

        GlStateManager._depthMask(true);
        //GlStateManager._enableAlpha();
        //RenderHelper.enableStandardItemLighting();
        //GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
        GlStateManager._disableBlend();
        GL11.glPopMatrix();
    }

    /*
     *     //////  //////  //    //  ////    //////  //////  //////  //////  //////
     *    //  //  //      ////  //  //  //  //      //  //  //      //  //  //
     *   ////    ////    //  ////  //  //  ////    ////    ////    ////    //////
     *  //  //  //      //    //  //  //  //      //  //  //      //  //      //
     * //  //  //////  //    //  ////    //////  //  //  //////  //  //  //////
     */

    /**
     * Once again the recycled ender dragon death animation
     * It worked so well the last 14 times, let's go for 15
     * WE CAN MAKE IT 16  - Random
     * @param intensity Double [0, 1] that determines scale and alpha
     */
    private void renderFlash(double intensity, MatrixStack matrices, VertexConsumerProvider vertices) {

        //GL11.glScalef(0.2F, 0.2F, 0.2F);

        double inverse = 1.0D - intensity;

        //net.minecraft.client.render.Tessellator tessellator = net.minecraft.client.render.Tessellator.getInstance();
        //BufferBuilder buf = tessellator.getBuffer();

        VertexConsumer buf = vertices.getBuffer(RenderLayer.getLightning());
        //RenderHelper.disableStandardItemLighting();

        //new Random(432L)
        Random random = Random.create(432L);
        //GlStateManager._disableTexture();
        //GlStateManager._shadeModel(GL11.GL_SMOOTH);
        //GlStateManager._enableBlend();
        //GlStateManager._blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
        //GlStateManager._disableAlpha();
        //GlStateManager._enableCull();
        //GlStateManager._depthMask(false);

        matrices.push();

        float scale = 100;

        for(int i = 0; i < 300; i++) {

            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(random.nextFloat() * 360.0F));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360.0F));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(random.nextFloat() * 360.0F));
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(random.nextFloat() * 360.0F));
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360.0F));
            matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(random.nextFloat() * 360.0F));

            float vert1 = (random.nextFloat() * 20.0F + 5.0F + 1 * 10.0F) * (float)(intensity * scale);
            float vert2 = (random.nextFloat() * 2.0F + 1.0F + 1 * 2.0F) * (float)(intensity * scale);
            Matrix4f matrix4f = matrices.peek().getPositionMatrix();

            //buf.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
            //.endVertex()
            buf.vertex(matrix4f, 0, 0, 0).color(1.0F, 1.0F, 1.0F, (float) intensity).next();
            buf.vertex(matrix4f, (float) (-0.866D * vert2), vert1, -0.5F * vert2).color(1.0F, 1.0F, 1.0F, 0.0F).next();
            buf.vertex(matrix4f, (float) (0.866D * vert2), vert1, -0.5F * vert2).color(1.0F, 1.0F, 1.0F, 0.0F).next();
            buf.vertex(matrix4f, 0.0F, vert1, vert2).color(1.0F, 1.0F, 1.0F, 0.0F).next();
            buf.vertex(matrix4f, (float) (-0.866D * vert2), vert1, -0.5F * vert2).color(1.0F, 1.0F, 1.0F, 0.0F).next();

        }

        //GL11.glPopMatrix();
        matrices.pop();

        //GlStateManager._depthMask(true);
        //GlStateManager._disableCull();
        //GlStateManager._disableBlend();
        //GlStateManager.shadeModel(GL11.GL_FLAT);
        //GlStateManager._clearColor(1, 1, 1, 1);
        //GlStateManager._enableTexture();
        //GlStateManager._enableAlpha();
        //RenderHelper.enableStandardItemLighting();
    }

    /**
     * Render call for the mush head model
     * Includes offset and smoothing
     * Also scales the fireball along XZ
     * @param progress Lifetime + interpolation number
     * @param height The current animation offset
     */
    private void renderMushHead(float progress, double height) {

        GL11.glPushMatrix();

        double expansion = 100;
        double width = Math.min(progress, expansion) / expansion * 0.3 + 0.7;

        GL11.glTranslated(0, -26 + height, 0);
        GL11.glScaled(width, 1, width);

        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        //mush.renderPart("Ball");
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glShadeModel(GL11.GL_FLAT);

        GL11.glPopMatrix();
    }

    /**
     * Render call for the mush stem model
     * Includes offset and smoothing
     * @param progress Lifetime + interpolation number
     * @param height The current animation offset
     */
    private void renderMushStem(float progress, double height) {

        GL11.glPushMatrix();

        GL11.glTranslated(0, -26 + height, 0);

        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        //mush.renderPart("Stem");
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glShadeModel(GL11.GL_FLAT);

        GL11.glPopMatrix();
    }

    /**
     * Adds one cloudlet (one face) to the tessellator.
     * Rotation is done using ActiveRenderInfo, which I'd assume runs on magic
     * But hey, if it works for particles, why not here too?
     * @param buf
     * @param posX
     * @param posY
     * @param posZ
     * @param age The mush' age when the cloudlet was created
     * @param type DataWatcher byte #19 which differentiates between different mush types
     */
    private void tessellateCloudlet(BufferBuilder buf, double posX, double posY, double posZ, float age, int type) {

        float alpha = 1F - Math.max(age / (float)(EntityNukeCloudSmall.cloudletLife), 0F);
        float alphaorig = alpha;

        float scale = 5F * (alpha * 0.5F + 0.5F);

        if(age < 3)
            alpha = age * 0.333F;

        /*
        float f1 = ActiveRenderInfo.getRotationX();
        float f2 = ActiveRenderInfo.getRotationZ();
        float f3 = ActiveRenderInfo.getRotationYZ();
        float f4 = ActiveRenderInfo.getRotationXY();
        float f5 = ActiveRenderInfo.getRotationXZ();

         */
        float f1, f2, f3, f4, f5;
        f1 = f2 = f3 = f4 = f5 = 0;


        Random rand = Random.create();
                //new Random((long) ((posX * 5 + posY * 25 + posZ * 125) * 1000D));

        float brightness = rand.nextFloat() * 0.25F + 0.25F;

        float r, g, b, a;
        if(type == 1) {
            r = 0.25F * alphaorig;
            g = alphaorig - brightness * 0.5F;
            b = 0.25F * alphaorig;
            a = alpha;
        } else {
            r = g = b = brightness;
            a = alpha;
        }
        a = MathHelper.clamp(a, 0, 1);
        //.endVertex()
        buf.vertex((double)(posX - f1 * scale - f3 * scale), (double)(posY - f5 * scale), (double)(posZ - f2 * scale - f4 * scale)).texture(1, 1).color(r, g, b, a);
        buf.vertex((double)(posX - f1 * scale + f3 * scale), (double)(posY + f5 * scale), (double)(posZ - f2 * scale + f4 * scale)).texture(1, 0).color(r, g, b, a);
        buf.vertex((double)(posX + f1 * scale + f3 * scale), (double)(posY + f5 * scale), (double)(posZ + f2 * scale + f4 * scale)).texture(0, 0).color(r, g, b, a);
        buf.vertex((double)(posX + f1 * scale - f3 * scale), (double)(posY - f5 * scale), (double)(posZ + f2 * scale - f4 * scale)).texture(0, 1).color(r, g, b, a);
    }
}
