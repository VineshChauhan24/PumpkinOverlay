package net.asbyth.pumpkinoverlay.render;

import net.asbyth.pumpkinoverlay.config.Options;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderOverlay extends GuiIngame {

    private static final ResourceLocation pumpkinBlurTexPath = new ResourceLocation("textures/misc/pumpkinblur.png");

    public RenderOverlay(Minecraft mcIn) {
        super(mcIn);
    }

    @SubscribeEvent
    public void renderPumpkinOverlay(RenderGameOverlayEvent.Post event) {
        if (Options.PUMPKIN_OVERLAY) {
            if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
                GlStateManager.enableBlend();
                GlStateManager.disableDepth();
                GlStateManager.depthMask(false);
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                GlStateManager.disableAlpha();
                mc.getTextureManager().bindTexture(pumpkinBlurTexPath);
                Tessellator tessellator = Tessellator.getInstance();
                WorldRenderer worldrenderer = tessellator.getWorldRenderer();
                worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
                worldrenderer.pos(0.0D, (double) event.resolution.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
                worldrenderer.pos((double) event.resolution.getScaledWidth(), (double) event.resolution.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
                worldrenderer.pos((double) event.resolution.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
                worldrenderer.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
                tessellator.draw();
                GlStateManager.depthMask(true);
                GlStateManager.enableDepth();
                GlStateManager.enableAlpha();
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}
