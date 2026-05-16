package com.leclowndu93150.essentialpatcher;

import com.leclowndu93150.essentialpatcher.config.PatcherConfig;
import com.leclowndu93150.essentialpatcher.network.CosmeticSyncData;
import com.leclowndu93150.essentialpatcher.network.CosmeticSyncPayload;
import com.leclowndu93150.essentialpatcher.platform.Platform;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class EssentialpatcherFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        Platform.Holder.set(new Platform() {
            @Override
            public Path getConfigDir() { return FabricLoader.getInstance().getConfigDir(); }
            @Override
            public Path getGameDir() { return FabricLoader.getInstance().getGameDir(); }
        });

        PatcherConfig config = PatcherConfig.get();

        PayloadTypeRegistry.playC2S().register(CosmeticSyncPayload.TYPE, CosmeticSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CosmeticSyncPayload.TYPE, CosmeticSyncPayload.CODEC);

        ClientPlayNetworking.registerGlobalReceiver(CosmeticSyncPayload.TYPE, (payload, context) -> {
            context.client().execute(() -> CosmeticSyncData.applyRemoteCosmetics(payload.playerUuid(), payload.equippedCosmetics()));
        });

        ServerPlayNetworking.registerGlobalReceiver(CosmeticSyncPayload.TYPE, (payload, context) -> {
            context.server().execute(() -> {
                for (var player : context.server().getPlayerList().getPlayers()) {
                    if (!player.getUUID().equals(payload.playerUuid())) {
                        ServerPlayNetworking.send(player, payload);
                    }
                }
            });
        });

        ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> {
            if (!PatcherConfig.get().unlockAllCosmetics) return;
            var player = Minecraft.getInstance().player;
            if (player == null) return;
            try {
                var cosmetics = CosmeticSyncData.getLocalEquipped();
                if (!cosmetics.isEmpty()) {
                    ClientPlayNetworking.send(new CosmeticSyncPayload(player.getUUID(), cosmetics));
                }
            } catch (Exception e) {
                System.err.println("[EssentialPatcher] Failed to send cosmetic sync: " + e.getMessage());
            }
        });

        if (config.disableAutoUpdate) {
            disableEssentialAutoUpdate("1.21.1");
        }
    }

    static void disableEssentialAutoUpdate(String mcVersion) {
        try {
            Path essentialDir = Platform.Holder.get().getGameDir().resolve("essential");
            Path configFile = essentialDir.resolve("stage2." + mcVersion + ".properties");
            Properties props = new Properties();
            if (Files.exists(configFile)) {
                try (var in = Files.newInputStream(configFile)) {
                    props.load(in);
                }
            }
            props.setProperty("autoUpdate", "false");
            Files.createDirectories(essentialDir);
            try (OutputStream out = Files.newOutputStream(configFile)) {
                props.store(out, null);
            }
        } catch (Exception e) {
            System.err.println("[EssentialPatcher] Failed to disable auto-update: " + e.getMessage());
        }
    }
}
