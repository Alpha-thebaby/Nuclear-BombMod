package com.hbm;

import com.hbm.block.NukeBoyBlock;
import com.hbm.entity.EntityNukeExplosionMK4;
import com.hbm.entity.NuclearBombEntity;
import com.hbm.entity.effect.EntityNukeCloudSmall;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	public static final EntityType<EntityNukeCloudSmall> NUKE_COUD_SMALL = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("hbm", "nuke_cloud_small"),
			FabricEntityTypeBuilder.<EntityNukeCloudSmall>create(SpawnGroup.MISC, EntityNukeCloudSmall::new).dimensions(EntityDimensions.fixed(20.0f, 40.0f)).build()
			);

	public static final EntityType<EntityNukeExplosionMK4> NUKE_EXPLOSION_MK4 = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("hbm", "nuke_explosion_mk4"),
			FabricEntityTypeBuilder.<EntityNukeExplosionMK4>create(SpawnGroup.MISC, com.hbm.entity.EntityNukeExplosionMK4::new).dimensions(EntityDimensions.fixed(20.0f, 40.0f)).build()
	);

	public static final EntityType<NuclearBombEntity> NUCLEAR_BOMB = Registry.register(
			Registries.ENTITY_TYPE,
			new Identifier("hbm", "nuclear_bomb"),
			FabricEntityTypeBuilder.<NuclearBombEntity>create(SpawnGroup.MISC, com.hbm.entity.NuclearBombEntity::new).dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build()
	);

	public static final Block NUKE_BOY = new NukeBoyBlock(FabricBlockSettings.of(Material.METAL).requiresTool().strength(2.0f).sounds(BlockSoundGroup.COPPER));


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		//FabricDefaultAttributeRegistry.register(NUKE_COUD_SMALL, Entity.attributes);
		Registry.register(Registries.BLOCK, new Identifier("hbm", "nuke_boy"), NUKE_BOY);
		Registry.register(Registries.ITEM, new Identifier("hbm", "nuke_boy"), new BlockItem(NUKE_BOY, new FabricItemSettings()));

	}
}
