package net.fabricmc.liverking;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.liverking.client.PrimalHudOverlay;

public class LiverKingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(new PrimalHudOverlay());
    }

}
