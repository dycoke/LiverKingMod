package net.fabricmc.liverking.client;


import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.liverking.LiverKing;
import net.fabricmc.liverking.util.IEntityDataSaver;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PrimalHudOverlay implements HudRenderCallback {
    private static final Identifier FILLED_PRIMAL = new Identifier(LiverKing.MOD_ID, "textures/primal_bar/filled_primal.png");
    private static final Identifier HALF_FILLED_PRIMAL = new Identifier(LiverKing.MOD_ID, "textures/primal_bar/half_filled_primal.png");
    private static final Identifier EMPTY_PRIMAL = new Identifier(LiverKing.MOD_ID, "textures/primal_bar/empty_primal.png");


    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {

        int x = 0, y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if(client != null && !client.player.isCreative() && !client.player.isSpectator()) {
            int width = client.getWindow().getScaledWidth(), height = client.getWindow().getScaledHeight();

            x = width / 2;
            y = height;
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, EMPTY_PRIMAL);
            y -= 59;
            x -= 91;
            if(client.player.getArmor() == 0) {
                y += 9; //removes the gap between hearts and primal when there's no armor on
            }
            for(int i = 0; i < 10; i++) {
                DrawableHelper.drawTexture(matrixStack, x + i*8, y, 0, 0, 9, 10, 9, 10);
            }

            int primal_energy_amount = ((IEntityDataSaver) client.player).getPersistentData().getInt("primal_energy");
            RenderSystem.setShaderTexture(0, FILLED_PRIMAL);
            for(int i = 0; i < 10; i++) {
                if(primal_energy_amount > i*2) {
                    DrawableHelper.drawTexture(matrixStack, x + i*8, y, 0, 0, 9, 10, 9, 10);
                } else {
                    break;
                }
            }

            if((primal_energy_amount&1) == 1) {
                RenderSystem.setShaderTexture(0, HALF_FILLED_PRIMAL);
                DrawableHelper.drawTexture(matrixStack, x + (primal_energy_amount/2)*8, y, 0, 0, 9, 10, 9, 10);
            }
        }
    }
}
