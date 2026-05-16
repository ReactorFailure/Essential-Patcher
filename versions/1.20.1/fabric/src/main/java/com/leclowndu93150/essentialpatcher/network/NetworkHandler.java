package com.leclowndu93150.essentialpatcher.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NetworkHandler {

    public static final ResourceLocation CHANNEL_ID = new ResourceLocation("essentialpatcher", "cosmetic_sync");

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(CHANNEL_ID, (client, handler, buf, responseSender) -> {
            UUID uuid = buf.readUUID();
            Map<String, String> cosmetics = readMap(buf);
            client.execute(() -> CosmeticSyncData.applyRemoteCosmetics(uuid, cosmetics));
        });

        ServerPlayNetworking.registerGlobalReceiver(CHANNEL_ID, (server, player, handler, buf, responseSender) -> {
            UUID uuid = buf.readUUID();
            Map<String, String> cosmetics = readMap(buf);
            server.execute(() -> {
                for (var other : server.getPlayerList().getPlayers()) {
                    if (!other.getUUID().equals(uuid) && ServerPlayNetworking.canSend(other, CHANNEL_ID)) {
                        FriendlyByteBuf outBuf = new FriendlyByteBuf(io.netty.buffer.Unpooled.buffer());
                        writePacket(outBuf, uuid, cosmetics);
                        ServerPlayNetworking.send(other, CHANNEL_ID, outBuf);
                    }
                }
            });
        });

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            var player = Minecraft.getInstance().player;
            if (player == null) return;
            var cosmetics = CosmeticSyncData.getLocalEquipped();
            if (cosmetics.isEmpty()) return;
            FriendlyByteBuf buf = new FriendlyByteBuf(io.netty.buffer.Unpooled.buffer());
            writePacket(buf, player.getUUID(), cosmetics);
            ClientPlayNetworking.send(CHANNEL_ID, buf);
        });
    }

    private static Map<String, String> readMap(FriendlyByteBuf buf) {
        int size = buf.readVarInt();
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            map.put(buf.readUtf(), buf.readUtf());
        }
        return map;
    }

    private static void writePacket(FriendlyByteBuf buf, UUID uuid, Map<String, String> cosmetics) {
        buf.writeUUID(uuid);
        buf.writeVarInt(cosmetics.size());
        for (var entry : cosmetics.entrySet()) {
            buf.writeUtf(entry.getKey());
            buf.writeUtf(entry.getValue());
        }
    }
}
