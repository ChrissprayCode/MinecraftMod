package com.cj.firstmod.world.feature;

import java.util.List;

import com.cj.firstmod.FirstMod;
import com.cj.firstmod.init.BlockInit;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;

public class ModConfiguredFeatures {

	//This is where we create a new "rule" for certain ore generations
	public static final List<OreConfiguration.TargetBlockState> OVERWORLD_TIN_ORES = List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.TIN_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEEPSLATE_TIN_ORE.get().defaultBlockState()));

	public static final List<OreConfiguration.TargetBlockState> OVERWORLD_ZINC_ORES = List.of(
			OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, BlockInit.ZINC_ORE.get().defaultBlockState()),
			OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, BlockInit.DEEPSLATE_ZINC_ORE.get().defaultBlockState()));
	
	//Register the lists to a ConfiguredFeature
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> TIN_ORE = FeatureUtils.register("tin_ore", Feature.ORE, new OreConfiguration(OVERWORLD_TIN_ORES, 12));
	public static final Holder<ConfiguredFeature<OreConfiguration, ?>> ZINC_ORE = FeatureUtils.register("zinc_ore", Feature.ORE, new OreConfiguration(OVERWORLD_ZINC_ORES, 5));
}
