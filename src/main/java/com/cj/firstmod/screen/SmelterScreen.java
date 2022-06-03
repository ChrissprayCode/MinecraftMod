package com.cj.firstmod.screen;

import com.cj.firstmod.FirstMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class SmelterScreen extends AbstractContainerScreen<SmelterMenu>{

	
	private static final ResourceLocation TEXTURE =
            new ResourceLocation(FirstMod.MOD_ID, "textures/gui/smelter_gui.png");
	
	
	public SmelterScreen(SmelterMenu p_97741_, Inventory p_97742_, Component p_97743_) {
		super(p_97741_, p_97742_, p_97743_);
		// TODO Auto-generated constructor stub
	}

//BOILERPLATE CODE TO DRAW THE MENU ONTO THE SCREEN
	@Override
	protected void renderBg(PoseStack p_97787_, float p_97788_, int p_97789_, int p_97790_) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(p_97787_, x, y, 0, 0, imageWidth, imageHeight);
		
        if(menu.isCrafting()) {
            blit(p_97787_, x + 117, y + 42, 177, 0, 8, menu.getScaledProgress());
        }
        
	}

	@Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
	
}
