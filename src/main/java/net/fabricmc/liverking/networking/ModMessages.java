package net.fabricmc.liverking.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.liverking.LiverKing;
import net.fabricmc.liverking.networking.packet.LogOnC2S;
import net.fabricmc.liverking.networking.packet.SyncPrimalDataS2C;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier PRIMAL_SYNC_ID = new Identifier(LiverKing.MOD_ID, "primal_sync");
    public static final Identifier LOG_ON_ID = new Identifier(LiverKing.MOD_ID, "log_on");

    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(LOG_ON_ID, LogOnC2S::receive);
    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(PRIMAL_SYNC_ID, SyncPrimalDataS2C::receive);
    }
}
