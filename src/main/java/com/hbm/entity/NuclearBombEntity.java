package com.hbm.entity;

import com.hbm.ExampleMod;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NuclearBombEntity extends Entity implements GeoEntity {

    public NuclearBombEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);
        this.setVelocity(0, -1.5, 0);
        this.ignoreCameraFrustum = true;
    }

    public NuclearBombEntity(World world, double x, double y, double z) {
        super(ExampleMod.NUCLEAR_BOMB, world);
        this.setPos(x, y, z);
        this.ignoreCameraFrustum = true;
    }

    public float getScale() {
        return 4.0f;
    }

    @Override
    public void tick() {
        super.tick();
        super.move(MovementType.SELF, new Vec3d(0, -1.5, 0));
        if (this.isAlive() && this.isOnGround()) {
            this.explode();
            this.discard();
        }
    }

    private void spawnExplosion() {
        if (!world.isClient) {
            world.spawnEntity(new EntityNukeCloudSmall(world, (double)this.getX() + 0.5D, (double)this.getY(), (double)this.getZ() + 0.5D));
            world.spawnEntity(new EntityNukeExplosionMK4(world, (double)this.getX() + 0.5D, (double)this.getY() + 12.0D, (double)this.getZ() + 0.5D));
        }
    }
    private void affectWorld() {

    }

    private void explode() {
        if (!this.world.isClient) {
            spawnExplosion();
            affectWorld();
        }
    }

    @Override
    protected void initDataTracker() {}

    @Override
    protected void readCustomDataFromNbt(NbtCompound var1) {}

    @Override
    protected void writeCustomDataToNbt(NbtCompound var1) {}

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    /*
     * Animation Side
     */

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}