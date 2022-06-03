 package com.cj.firstmod;

import com.google.common.base.Supplier;

import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

public class BaseToolMaterial implements Tier {

	private final float attackDamageBonus, speed;
	private final int enchantability, harvestLevel, durability;
	private final Supplier<Ingredient> repairMaterial;
	
	//constructor
	public BaseToolMaterial(float attackDamageBonus, float speed, int enchantability, int harvestLevel, int durability, Supplier<Ingredient>repairMaterial) {
		this.attackDamageBonus = attackDamageBonus;
		this.speed = speed;
		this.enchantability = enchantability;
		this.harvestLevel = harvestLevel;
		this.durability = durability;
		this.repairMaterial = repairMaterial;
	}
	
	@Override
	public int getUses() {
		// TODO Auto-generated method stub
		return this.durability;
	}

	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return this.speed;
	}

	@Override
	public float getAttackDamageBonus() {
		// TODO Auto-generated method stub
		return this.attackDamageBonus;
	}

	@Override
	public int getLevel() {
		// TODO Auto-generated method stub
		return this.harvestLevel;
	}

	@Override
	public int getEnchantmentValue() {
		// TODO Auto-generated method stub
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		// TODO Auto-generated method stub
		return this.repairMaterial.get();
	}

}

