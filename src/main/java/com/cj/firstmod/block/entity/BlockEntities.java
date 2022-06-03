package com.cj.firstmod.block.entity;

import com.cj.firstmod.FirstMod;
import com.cj.firstmod.block.entity.custom.SmelterBlockEntity;
import com.cj.firstmod.init.BlockInit;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
			DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, FirstMod.MOD_ID);
	
	public static final RegistryObject<BlockEntityType<SmelterBlockEntity>> SMELTER_ENTITY =
			BLOCK_ENTITIES.register("smelter_entity", () -> BlockEntityType.Builder.of(SmelterBlockEntity::new, BlockInit.SMELTER_BLOCK.get()).build(null));
	
	
	
	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}
	
}
