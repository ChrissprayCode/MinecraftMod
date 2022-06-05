package com.cj.firstmod.screen;

import com.cj.firstmod.FirstMod;
import com.cj.firstmod.block.entity.custom.SmelterBlockEntity;
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
	protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight); //draws the BG?

        //blit(pPoseStack, x + 62, y + 18, 221, 14, 16, 52); // draws lava
        blit(pPoseStack, x + 62, y + 70 - menu.getScaledLavaProgress(), 221, 66 - menu.getScaledLavaProgress(), 16, 52);


        if(menu.isCrafting()) {
            //start xPos, start yPos, copy from xPos, copy from yPos (top right corners), drawX, drawY
            blit(pPoseStack, x + 116, y + 41, 177, 0, menu.getScaledProgress(), 8);
        }


        
	}

	@Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.font.draw(pPoseStack, menu.getScaledLavaProgress() + "", x + 63, y + 11, 0x404040); //draws count


    }
	
}
