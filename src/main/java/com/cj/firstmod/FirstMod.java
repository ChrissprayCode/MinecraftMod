package com.cj.firstmod;


import com.cj.firstmod.block.entity.BlockEntities;
import com.cj.firstmod.block.entity.custom.SmelterBlockEntity;
import com.cj.firstmod.init.BlockInit;
import com.cj.firstmod.init.ItemInit;
import com.cj.firstmod.recipe.ModRecipes;
import com.cj.firstmod.screen.ModMenuTypes;
import com.cj.firstmod.screen.SmelterScreen;
import com.google.common.eventbus.EventBus;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("firstmod")
public class FirstMod {

	public static final String MOD_ID = "firstmod";
	
	public static final CreativeModeTab CHRIS_TAB = new CreativeModeTab(MOD_ID) {
		
		@Override
		@OnlyIn(Dist.CLIENT)
		public ItemStack makeIcon() {

			return new ItemStack(ItemInit.EXAMPLE_ITEM.get());
		}
	};
	
	public FirstMod() {
		
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		
		ItemInit.ITEMS.register(bus);
		BlockInit.BLOCKS.register(bus);
		BlockEntities.BLOCK_ENTITIES.register(bus);
		ModMenuTypes.register(bus);
		ModRecipes.register(bus);
		
		bus.addListener(this::clientSetup);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	
	private void clientSetup(final FMLClientSetupEvent event) {
		
		MenuScreens.register(ModMenuTypes.SMELTER_MENU.get(), SmelterScreen::new);
		
	}
}

