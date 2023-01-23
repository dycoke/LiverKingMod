package net.fabricmc.liverking.networking.packet;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.liverking.networking.ModMessages;
import net.fabricmc.liverking.util.IEntityDataSaver;
import net.fabricmc.liverking.util.PrimalData;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class LogOnC2S {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        int primal = ((IEntityDataSaver) player).getPersistentData().getInt("primal_energy");
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(primal);
        ServerPlayNetworking.send(player, ModMessages.PRIMAL_SYNC_ID, buffer);
    }
}
