package com.cj.firstmod.recipe;

import com.cj.firstmod.FirstMod;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {

	public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FirstMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<SmelterRecipe>> SMELTER_SERIALIZER =
            SERIALIZERS.register("smelter", () -> SmelterRecipe.Serializer.INSTANCE);
	
    public static void register(IEventBus eventBus) {
    	SERIALIZERS.register(eventBus);
    }
}
