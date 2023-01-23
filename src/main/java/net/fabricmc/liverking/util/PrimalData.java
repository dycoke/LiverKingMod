package net.fabricmc.liverking.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.liverking.networking.ModMessages;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class PrimalData {
    public static int addPrimal(IEntityDataSaver player, int amount) {

        NbtCompound nbt = player.getPersistentData();

        int primal_energy = nbt.getInt("primal_energy");
        primal_energy = Math.min(primal_energy + amount, 20);

        nbt.putInt("primal_energy", primal_energy);

        syncPrimal(primal_energy, (ServerPlayerEntity) player);

        return primal_energy;
    }
    
    public static int removePrimal(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();

        int primal_energy = nbt.getInt("primal_energy");
        primal_energy = Math.max(primal_energy - amount, 0);

        nbt.putInt("primal_energy", primal_energy);
        syncPrimal(primal_energy, (ServerPlayerEntity) player);

        return primal_energy;
    }

    public static void syncPrimal(int primal, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(primal);
        ServerPlayNetworking.send(player, ModMessages.PRIMAL_SYNC_ID, buffer);
    }
}
