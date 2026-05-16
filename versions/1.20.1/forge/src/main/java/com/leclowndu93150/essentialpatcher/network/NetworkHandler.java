package com.leclowndu93150.essentialpatcher.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

public class NetworkHandler {

    private static SimpleChannel CHANNEL;

    public static void register() {
        CHANNEL = ChannelBuilder
                .named(new ResourceLocation("essentialpatcher", "sync"))
                .optional()
                .networkProtocolVersion(1)
                .simpleChannel();

        CHANNEL.messageBuilder(CosmeticSyncPacket.class, 0)
                .encoder(CosmeticSyncPacket::encode)
                .decoder(CosmeticSyncPacket::new)
                .consumerMainThread((packet, context) -> {
                    if (context.getDirection() == NetworkDirection.PLAY_TO_SERVER) {
                        ServerPlayer sender = context.getSender();
                        if (sender == null || sender.getServer() == null) return;
                        for (ServerPlayer player : sender.getServer().getPlayerList().getPlayers()) {
                            if (!player.getUUID().equals(packet.playerUuid)) {
                                CHANNEL.send(packet, PacketDistributor.PLAYER.with(player));
                            }
                        }
                    } else {
                        CosmeticSyncData.applyRemoteCosmetics(packet.playerUuid, packet.equippedCosmetics);
                    }
                })
                .add();
    }

    public static void sendToServer(CosmeticSyncPacket packet) {
        if (CHANNEL != null) {
            CHANNEL.send(packet, PacketDistributor.SERVER.noArg());
        }
    }
}
