package com.hbm.entity;

import com.hbm.ExampleMod;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import com.hbm.util.ExplosionNukeRay;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class EntityNukeExplosionMK4 extends Entity {
    // Strength of the blast
    public int strength = 240;
    // How many rays should be created
    public int count = 50;
    // How many rays are calculated per tick
    public int speed = 10;
    public int length = 25;

    public boolean mute = false;

    public boolean fallout = true;
    private int falloutAdd = 0;

    ExplosionNukeRay explosion;

    //private final AnimationFactory factory = GeckoLibUtil.createFactory(this);


    public EntityNukeExplosionMK4(EntityType<? extends EntityNukeExplosionMK4> entityType, World world) {
        super(entityType, world);
        this.ignoreCameraFrustum = true;
    }

    public EntityNukeExplosionMK4(World world, double x, double y, double z) {
        super(ExampleMod.NUKE_EXPLOSION_MK4, world);
        this.setPos(x, y, z);
        //this.ignoreCameraFrustum = true;
    }

    public EntityNukeExplosionMK4(EntityType<? extends EntityNukeExplosionMK4> entityType, World world, int strength, int count, int speed, int length) {
        super(entityType, world);
        this.strength = strength;
        this.count = count;
        this.speed = speed;
        this.length = length;
        //this.ignoreCameraFrustum = true;
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    public void tick() {
        if(strength == 0) {
            this.discard();
            return;
        }

        if(!world.isClient && fallout && explosion != null) {
            //RadiationSavedData.getData(world);

            // float radMax = (float) (length / 2F * Math.pow(length, 2) / 35F);
            float radMax = Math.min((float) (length / 2F * Math.pow(length, 1.5) / 35F), 15000);
            // System.out.println(radMax);
            float rad = radMax / 4F;
            //RadiationSavedData.incrementRad(world, this.getPosition(), rad, radMax);
        }

        if(!mute) {
            this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.AMBIENT, 10000.0F, 0.8F + this.random.nextFloat() * 0.2F);
            if(random.nextInt(5) == 0)
                this.world.playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.AMBIENT, 10000.0F, 0.8F + this.random.nextFloat() * 0.2F);
        }
        //ExplosionNukeGeneric.dealDamage(this.world, this.getX(), this.getY(), this.getZ(), this.length * 2);

        if(explosion == null) {

            explosion = new ExplosionNukeRay(world, (int) this.getX(), (int) this.getY(), (int) this.getZ(), this.strength, this.count, this.speed, this.length);

            // MainRegistry.logger.info("START: " + System.currentTimeMillis());

			/*if(!world.isRemote)
				for(int x = (int) (posX - 1); x <= (int) (posX + 1); x++)
					for(int y = (int) (posY - 1); y <= (int) (posY + 1); y++)
						for(int z = (int) (posZ - 1); z <= (int) (posZ + 1); z++)
							world.setBlock(x, y, z, Blocks.air);*/
        }

        // if(explosion.getStoredSize() < count / length) {
        if(!explosion.isAusf3Complete) {
            // if(!world.isRemote)
            ExampleMod.LOGGER.info(explosion.getProgress() + " / " + count
                    / length);
            // explosion.collectTip(speed * 10);
            explosion.collectTipMk4_5(speed * 10);
        } else if(explosion.getStoredSize() > 0) {
            // if(!world.isRemote)
            ExampleMod.LOGGER.info(explosion.getProgress() + " / " + count
             / length);
            explosion.processTip(50);
        } else if(fallout) {
            // MainRegistry.logger.info("STOP: " + System.currentTimeMillis());

            /*
            EntityFalloutRain fallout = new EntityFalloutRain(this.world);
            fallout.posX = this.getX();
            fallout.posY = this.getY();
            fallout.posZ = this.getZ();
            fallout.setScale((int) (this.length * 1.8 + falloutAdd) * BombConfig.falloutRange / 100);

            this.world.spawnEntity(fallout);
             */

            this.discard();
        } else {
            this.discard();
        }
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }

    /*
    public static EntityNukeExplosionMK4 statFac(World world, int r, double x, double y, double z) {
        if(r == 0)
            r = 25;

        r *= 2;

        EntityNukeExplosionMK4 mk4 = new EntityNukeExplosionMK4(orld);
        mk4.strength = (int) (r);
        mk4.count = (int) (4 * Math.PI * Math.pow(mk4.strength, 2) * 25);
        mk4.speed = (int) Math.ceil(100000 / mk4.strength);
        mk4.setPosition(x, y, z);
        mk4.length = mk4.strength / 2;
        return mk4;
    }



    public static EntityNukeExplosionMK4 statFacExperimental(World world, int r, double x, double y, double z) {
        r *= 2;

        EntityNukeExplosionMK4 mk4 = new EntityNukeExplosionMK4(world);
        mk4.strength = (int) (r);
        mk4.count = (int) (4 * Math.PI * Math.pow(mk4.strength, 2) * 25);
        mk4.speed = (int) Math.ceil(100000 / mk4.strength);
        mk4.setPosition(x, y, z);
        mk4.length = mk4.strength / 2;
        return mk4;
    }

    public static EntityNukeExplosionMK4 statFacNoRad(World world, int r, double x, double y, double z) {

        r *= 2;

        EntityNukeExplosionMK4 mk4 = new EntityNukeExplosionMK4(world);
        mk4.strength = (int) (r);
        mk4.count = (int) (4 * Math.PI * Math.pow(mk4.strength, 2) * 25);
        mk4.speed = (int) Math.ceil(100000 / mk4.strength);
        mk4.setPosition(x, y, z);
        mk4.length = mk4.strength / 2;
        mk4.fallout = false;
        return mk4;
    }
*/
    public EntityNukeExplosionMK4 moreFallout(int fallout) {
        falloutAdd = fallout;
        return this;
    }

    public EntityNukeExplosionMK4 mute() {
        this.mute = true;
        return this;
    }

    /*
     * Animation Side


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);


    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }


    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

     */
}
