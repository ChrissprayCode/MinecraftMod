package com.cj.firstmod.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {

	public static final Holder<PlacedFeature> TIN_ORE_PLACED = PlacementUtils.register("tin_ore_placed", 
			ModConfiguredFeatures.TIN_ORE, ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-50), VerticalAnchor.aboveBottom(100)))); //vein size, create triangle of spawn area

}
