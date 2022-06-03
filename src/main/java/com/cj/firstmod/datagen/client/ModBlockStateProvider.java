package com.cj.firstmod.datagen.client;

import com.cj.firstmod.FirstMod;
import com.cj.firstmod.init.BlockInit;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider{

	public ModBlockStateProvider(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, FirstMod.MOD_ID, helper);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void registerStatesAndModels() { //generate BlockState + Block Model
		// TODO Auto-generated method stub
		simpleBlock(BlockInit.EXAMPLE_BLOCK.get());
	}

}
