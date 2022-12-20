package com.hbm;

import com.hbm.entity.effect.EntityNukeCloudSmall;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		//FabricDefaultAttributeRegistry.register(NUKE_COUD_SMALL, Entity.attributes);
	}
}
