package com.cj.firstmod.init;

import com.cj.firstmod.BaseToolMaterial;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public final class ToolMaterialInit {

	private ToolMaterialInit() {
		
	}
	
	protected static final Tier TIN_TIER = new BaseToolMaterial(6f, 1.6f, 500, 5, 200, () -> Ingredient.of(ItemInit.TIN_INGOT.get()));
}
