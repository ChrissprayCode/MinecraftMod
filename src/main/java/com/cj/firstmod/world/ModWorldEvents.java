package com.cj.firstmod.world;

import com.cj.firstmod.FirstMod;
import com.cj.firstmod.recipe.SmelterRecipe;
import com.cj.firstmod.world.gen.ModOreGeneration;

import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FirstMod.MOD_ID)
public class ModWorldEvents {

	@SubscribeEvent
	public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
		ModOreGeneration.generateOres(event);
	}

}
