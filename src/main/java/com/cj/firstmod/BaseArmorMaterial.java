package com.cj.firstmod;

import com.google.common.base.Supplier;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

public class BaseArmorMaterial implements ArmorMaterial{

	private final int enchantability;
	private final int[] durability, damageReduction;
	private final float knockbackResistance, toughness;
	private final String name;
	private final SoundEvent equipSound;
	private final Supplier<Ingredient> repairMaterial; 
	
	public BaseArmorMaterial(int enchantability, int[] durability, int[] damageReduction, float knockbackResistance, float toughness, String name, SoundEvent equipSound, Supplier<Ingredient> repairMaterial) {
		this.enchantability = enchantability;
		this.durability = durability;
		this.damageReduction = damageReduction;
		this.knockbackResistance = knockbackResistance;
		this.toughness = toughness;
		this.name = name;
		this.equipSound = equipSound;
		this.repairMaterial = repairMaterial;
	}
	
	@Override
	public int getDurabilityForSlot(EquipmentSlot p_40410_) {
		// TODO Auto-generated method stub
		return this.durability[p_40410_.getIndex()];
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot p_40411_) {
		// TODO Auto-generated method stub
		return this.damageReduction[p_40411_.getIndex()];
	}

	@Override
	public int getEnchantmentValue() {
		// TODO Auto-generated method stub
		return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
		// TODO Auto-generated method stub
		return equipSound;
	}

	@Override
	public Ingredient getRepairIngredient() {
		// TODO Auto-generated method stub
		return this.repairMaterial.get();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public float getToughness() {
		// TODO Auto-generated method stub
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance() {
		// TODO Auto-generated method stub
		return this.knockbackResistance;
	}

}