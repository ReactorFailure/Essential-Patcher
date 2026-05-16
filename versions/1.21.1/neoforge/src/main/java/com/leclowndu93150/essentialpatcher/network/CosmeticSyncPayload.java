package com.leclowndu93150.essentialpatcher.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public record CosmeticSyncPayload(UUID playerUuid, Map<String, String> equippedCosmetics) implements CustomPacketPayload {

    public static final Type<CosmeticSyncPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath("essentialpatcher", "cosmetic_sync"));

    public static final StreamCodec<FriendlyByteBuf, CosmeticSyncPayload> CODEC = new StreamCodec<>() {
        @Override
        public CosmeticSyncPayload decode(FriendlyByteBuf buf) {
            UUID uuid = buf.readUUID();
            int size = buf.readVarInt();
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < size; i++) {
                map.put(buf.readUtf(), buf.readUtf());
            }
            return new CosmeticSyncPayload(uuid, map);
        }

        @Override
        public void encode(FriendlyByteBuf buf, CosmeticSyncPayload payload) {
            buf.writeUUID(payload.playerUuid);
            buf.writeVarInt(payload.equippedCosmetics.size());
            for (var entry : payload.equippedCosmetics.entrySet()) {
                buf.writeUtf(entry.getKey());
                buf.writeUtf(entry.getValue());
            }
        }
    };

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
