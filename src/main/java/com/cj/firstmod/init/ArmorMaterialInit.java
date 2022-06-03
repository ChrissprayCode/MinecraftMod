package com.cj.firstmod.init;

import com.cj.firstmod.BaseArmorMaterial;
import com.cj.firstmod.FirstMod;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class ArmorMaterialInit {

	
	public static final ArmorMaterial TIN = new BaseArmorMaterial(100, new int[] {128, 200, 180, 150}, new int[] {1,4,5,2}, 0f, 0f, FirstMod.MOD_ID + ":tin",
			SoundEvents.ARMOR_EQUIP_IRON, () -> Ingredient.of(ItemInit.TIN_INGOT.get()));
	
	private ArmorMaterialInit() {
		
	}
}
