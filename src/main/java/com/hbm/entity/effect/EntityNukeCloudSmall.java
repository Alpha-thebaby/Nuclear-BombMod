package com.hbm.entity.effect;

import com.hbm.ExampleMod;
import com.hbm.interfaces.IConstantRenderer;
import com.hbm.util.Vec3;
import net.fabricmc.api.Environment;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.MixinEnvironment;

import java.util.ArrayList;

public class EntityNukeCloudSmall extends Entity implements IConstantRenderer {
    // 16
    private static final TrackedData<Integer> AGE = DataTracker.registerData(EntityNukeCloudSmall.class,
            TrackedDataHandlerRegistry.INTEGER);
    // 17
    private static final TrackedData<Integer> MAXAGE = DataTracker.registerData(EntityNukeCloudSmall.class,
            TrackedDataHandlerRegistry.INTEGER);
    // 18
    public static final TrackedData<Float> SCALE = DataTracker.registerData(EntityNukeCloudSmall.class,
            TrackedDataHandlerRegistry.FLOAT);
    // I really don't know. Some documentation would have been nice
    // 19
    public static final TrackedData<Byte> TYPE = DataTracker.registerData(EntityNukeCloudSmall.class,
            TrackedDataHandlerRegistry.BYTE);
    public int maxAge = 1000;
    public int age;
    public static int cloudletLife = 50;
    public ArrayList<Cloudlet> cloudlets = new ArrayList<>();

    protected void initDataTracker() {
        this.getDataTracker().startTracking(AGE, 0);
        this.getDataTracker().startTracking(MAXAGE, 0);
        this.getDataTracker().startTracking(SCALE, 1.0F);
        this.getDataTracker().startTracking(TYPE, (byte)0);
    }

    public EntityNukeCloudSmall(EntityType<? extends EntityNukeCloudSmall> entityType, World world) {
        super(entityType, world);
        // these are set in entity registry
        //this.scale(20, 40);
        //this.isImmuneToFire = true;
        this.ignoreCameraFrustum = true;
        this.age = 0;
        this.noClip = true;
        setRenderDistanceMultiplier(5.0D);
    }

    public EntityNukeCloudSmall(World world, double x, double y, double z) {
        super(ExampleMod.NUKE_COUD_SMALL, world);
        // these are set in entity registry
        //this.scale(20, 40);
        //this.isImmuneToFire = true;
        this.ignoreCameraFrustum = true;
        this.age = 0;
        this.noClip = true;
        this.setPos(x, y, z);
        setRenderDistanceMultiplier(5.0D);
    }

    public EntityNukeCloudSmall(EntityType<? extends EntityNukeCloudSmall> entityType, World world, int maxAge, float scale) {
        super(entityType, world);
        // these are set in entity registry
        //this.setSize(20, 40);
        //this.isImmuneToFire = true;
        this.ignoreCameraFrustum = true;
        this.age = 0;
        this.maxAge = maxAge;
        this.dataTracker.set(SCALE, scale);
        this.noClip = true;
        setRenderDistanceMultiplier(5.0D);
    }

    @Override
    public void tick() {
        super.tick();
        this.age++;
        world.setLightningTicksLeft(2);

        if (this.age >= this.maxAge) {
            this.age = 0;
            this.discard();
        }
        int cloudCount = age * 3;

        Vec3 vec = Vec3.createVectorHelper(age * 2, 0, 0);

        int toRem = 0;

        for(int i = 0; i < this.cloudlets.size(); i++) {

            if(age > cloudlets.get(i).age + cloudletLife)
                toRem = i;
            else
                break;
        }

        for(int i = 0; i < toRem; i++)
            this.cloudlets.remove(0);

        if(age < 200) {
            for(int i = 0; i < cloudCount; i++) {
                vec.rotateAroundY((float)(Math.PI * 2 * world.random.nextDouble()));

                this.cloudlets.add(new Cloudlet(vec.xCoord, world.getHeight(), vec.zCoord, age));
            }
        }

        this.dataTracker.set(MAXAGE, maxAge);
        this.dataTracker.set(AGE, age);
    }


    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("maxAge"))
            maxAge = nbt.getShort("maxAge");
        if (nbt.contains("age"))
            age = nbt.getShort("age");
        if (nbt.contains("scale"))
            this.dataTracker.set(SCALE, nbt.getFloat("scale"));
        if(nbt.contains("type"))
            this.dataTracker.set(TYPE, nbt.getByte("type"));
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("maxAge", (short) maxAge);
        nbt.putShort("age", (short) age);
        nbt.putFloat("scale", this.dataTracker.get(SCALE));
        nbt.putByte("type", this.dataTracker.get(TYPE));
    }

    public static EntityNukeCloudSmall statFac(EntityType<EntityNukeCloudSmall> entity, World world, double x, double y, double z, float radius) {
        EntityNukeCloudSmall cloud = new EntityNukeCloudSmall(entity, world, (int) radius * 5, radius * 0.005F);
        cloud.setPos(x, y, z);
        cloud.dataTracker.set(TYPE, (byte) 0);

        return cloud;
    }

    public static EntityNukeCloudSmall statFacBale(EntityType<EntityNukeCloudSmall> entity, World world, double x, double y, double z, float radius,
                                                   int maxAge) {

        EntityNukeCloudSmall cloud = new EntityNukeCloudSmall(entity, world, (int) radius * 5, radius * 0.005F);
        cloud.setPos(x, y, z);
        cloud.dataTracker.set(TYPE, (byte) 1);

        return cloud;
    }

    public static class Cloudlet {

        public double posX;
        public double posY;
        public double posZ;
        public int age;

        public Cloudlet(double posX, double posY, double posZ, int age) {
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.age = age;
        }
    }
}
