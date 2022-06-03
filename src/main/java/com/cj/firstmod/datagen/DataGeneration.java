package com.cj.firstmod.datagen;

import com.cj.firstmod.FirstMod;
import com.cj.firstmod.datagen.client.ModBlockStateProvider;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID, bus = Bus.MOD)
public class DataGeneration {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper helper = event.getExistingFileHelper();
		
		if (event.includeClient()) { //Resource Pack Generation
			
			generator.addProvider(new ModBlockStateProvider(generator, helper));
			
		}
		
		if (event.includeServer()) { //Data Pack Generation
			
		}
		
	}
	
}
