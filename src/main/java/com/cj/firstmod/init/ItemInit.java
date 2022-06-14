package com.cj.firstmod.init;

import com.cj.firstmod.FirstMod;
import com.google.common.base.Supplier;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FirstMod.MOD_ID);
	
	public static final RegistryObject<Item> EXAMPLE_ITEM = register("example_item", 
			() -> new Item(new Item.Properties().tab(FirstMod.CHRIS_TAB)));
	
// TIN ITEMS
	public static final RegistryObject<Item> RAW_TIN = register("raw_tin", 
			() -> new Item(new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(64)));
	
	public static final RegistryObject<Item> TIN_INGOT = register("tin_ingot", 
			() -> new Item(new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(64)));
	
	
	//TIN TOOLS
	public static final RegistryObject<SwordItem> TIN_SWORD = register("tin_sword",
			() -> new SwordItem(ToolMaterialInit.TIN_TIER, 6, 1.6f, new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(1)));
	
	public static final RegistryObject<AxeItem> TIN_AXE = register("tin_axe",
			() -> new AxeItem(ToolMaterialInit.TIN_TIER, 9, 1.6f, new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(1)));
	
	public static final RegistryObject<PickaxeItem> TIN_PICKAXE = register("tin_pickaxe",
			() -> new PickaxeItem(ToolMaterialInit.TIN_TIER, 6, 1.6f, new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(1)));
	
	public static final RegistryObject<ShovelItem> TIN_SHOVEL = register("tin_shovel",
			() -> new ShovelItem(ToolMaterialInit.TIN_TIER, 6, 1.6f, new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(1)));
	
	public static final RegistryObject<HoeItem> TIN_HOE = register("tin_hoe",
			() -> new HoeItem(ToolMaterialInit.TIN_TIER, 6, 1.6f, new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(1)));
	
	//TIN ARMOUR
	public static final RegistryObject<ArmorItem> TIN_HELMET = register("tin_helmet",
			() -> new ArmorItem(ArmorMaterialInit.TIN,EquipmentSlot.HEAD, new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(1)));
	
	public static final RegistryObject<ArmorItem> TIN_CHESTPLATE = register("tin_chestplate",
			() -> new ArmorItem(ArmorMaterialInit.TIN,EquipmentSlot.CHEST, new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(1)));
	
	public static final RegistryObject<ArmorItem> TIN_LEGGINGS = register("tin_leggings",
			() -> new ArmorItem(ArmorMaterialInit.TIN,EquipmentSlot.LEGS, new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(1)));
	
	public static final RegistryObject<ArmorItem> TIN_BOOTS = register("tin_boots",
			() -> new ArmorItem(ArmorMaterialInit.TIN,EquipmentSlot.FEET, new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(1)));
	
//BRONZE ITEMS
	public static final RegistryObject<Item> BRONZE_INGOT = register("bronze_ingot", 
			() -> new Item(new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(64)));




//FOOD ITEMS
	//TIN CANS
	public static final RegistryObject<Item> TIN_CAN = register("tin_can",
			() -> new Item(new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(64)));

	public static final RegistryObject<Item> TIN_CAN_TOMATO = register("tin_can_tomato",
			() -> new Item(new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(64)));

	//FOOD & CROPS
	public static final RegistryObject<Item> TOMATO = register("tomato",
			() -> new Item(new Item.Properties().tab(FirstMod.CHRIS_TAB).stacksTo(64).food(ModFoods.TOMATO)));




	private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item){
		return ITEMS.register(name, item);
	}
}
