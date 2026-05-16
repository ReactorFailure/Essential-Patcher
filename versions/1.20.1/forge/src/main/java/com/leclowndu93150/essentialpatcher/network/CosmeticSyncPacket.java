package com.leclowndu93150.essentialpatcher.network;

import net.minecraft.network.FriendlyByteBuf;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CosmeticSyncPacket {

    public final UUID playerUuid;
    public final Map<String, String> equippedCosmetics;

    public CosmeticSyncPacket(UUID playerUuid, Map<String, String> equippedCosmetics) {
        this.playerUuid = playerUuid;
        this.equippedCosmetics = equippedCosmetics;
    }

    public CosmeticSyncPacket(FriendlyByteBuf buf) {
        this.playerUuid = buf.readUUID();
        int size = buf.readVarInt();
        this.equippedCosmetics = new HashMap<>();
        for (int i = 0; i < size; i++) {
            equippedCosmetics.put(buf.readUtf(), buf.readUtf());
        }
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeUUID(playerUuid);
        buf.writeVarInt(equippedCosmetics.size());
        for (var entry : equippedCosmetics.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeUtf(entry.getValue());
        }
    }
}
