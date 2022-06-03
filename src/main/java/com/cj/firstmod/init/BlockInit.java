package com.cj.firstmod.init;

import java.util.function.Function;

import com.cj.firstmod.FirstMod;
import com.cj.firstmod.block.custom.SmelterBlock;
import com.google.common.base.Supplier;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FirstMod.MOD_ID);
	public static final DeferredRegister<Item> ITEMS = ItemInit.ITEMS;
	
	//create a block (RegisterBlock if you DON'T want an item with it, register if you do)
	//blockBehaviour is how it acts as a block, item.properties is how it acts as an item
	public static final RegistryObject<Block> EXAMPLE_BLOCK = register("example_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GREEN).strength(3.0f).sound(SoundType.METAL)
			.requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(FirstMod.CHRIS_TAB)));
	
	
	public static final RegistryObject<Block> TIN_ORE = register("tin_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f).sound(SoundType.STONE)
			.requiresCorrectToolForDrops(), UniformInt.of(2,4)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(FirstMod.CHRIS_TAB)));
	public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = register("deepslate_tin_ore", () -> new OreBlock(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f)
			.sound(SoundType.STONE).requiresCorrectToolForDrops(), UniformInt.of(2, 4)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(FirstMod.CHRIS_TAB)));
	
	public static final RegistryObject<Block> TIN_BLOCK = register("tin_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(FirstMod.CHRIS_TAB)));
	
	public static final RegistryObject<Block> BRONZE_BLOCK = register("bronze_block", () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_BROWN).strength(3.0f).sound(SoundType.METAL).requiresCorrectToolForDrops()), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(FirstMod.CHRIS_TAB)));
	
	
	public static final RegistryObject<Block> SMELTER_BLOCK = register("smelter_block", () -> new SmelterBlock(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.COLOR_GRAY).strength(3.0f).sound(SoundType.STONE)), 
			object -> () -> new BlockItem(object.get(), new Item.Properties().tab(FirstMod.CHRIS_TAB)));
	
	
	
	private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> block){
		return BLOCKS.register(name, block);
	}
	
	//register block with name, and register item with the block
	private static <T extends Block> RegistryObject<T> register(final String name, final Supplier<? extends T> block, Function<RegistryObject<T>, Supplier<? extends Item>> item){
		RegistryObject<T> obj = registerBlock(name, block);
		ITEMS.register(name, item.apply(obj));
		
		return obj;
	}
	
}
