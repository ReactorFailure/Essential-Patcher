package com.leclowndu93150.essentialpatcher;

import com.leclowndu93150.essentialpatcher.config.PatcherConfig;
import com.leclowndu93150.essentialpatcher.config.PatcherConfigScreen;
import com.leclowndu93150.essentialpatcher.network.CosmeticSyncData;
import com.leclowndu93150.essentialpatcher.network.CosmeticSyncPayload;
import com.leclowndu93150.essentialpatcher.platform.Platform;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

@Mod("essentialpatcher")
public class EssentialpatcherNeoForge {

    public EssentialpatcherNeoForge(IEventBus modBus) {
        Platform.Holder.set(new Platform() {
            @Override
            public Path getConfigDir() { return FMLPaths.CONFIGDIR.get(); }
            @Override
            public Path getGameDir() { return FMLPaths.GAMEDIR.get(); }
        });

        PatcherConfig config = PatcherConfig.get();
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class,
                () -> (container, parent) -> PatcherConfigScreen.create(parent));
        modBus.addListener(this::registerPayloads);
        NeoForge.EVENT_BUS.addListener(this::onPlayerJoin);
        if (config.disableAutoUpdate) {
            disableEssentialAutoUpdate("1.21.1");
        }
    }

    private void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("essentialpatcher").optional();
        registrar.playBidirectional(
                CosmeticSyncPayload.TYPE,
                CosmeticSyncPayload.CODEC,
                (payload, context) -> {
                    if (context.flow().isClientbound()) {
                        context.enqueueWork(() -> CosmeticSyncData.applyRemoteCosmetics(payload.playerUuid(), payload.equippedCosmetics()));
                    } else {
                        context.enqueueWork(() -> {
                            if (context.player() instanceof ServerPlayer sender) {
                                var server = sender.getServer();
                                if (server == null) return;
                                for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                                    if (!player.getUUID().equals(payload.playerUuid())) {
                                        PacketDistributor.sendToPlayer(player, payload);
                                    }
                                }
                            }
                        });
                    }
                }
        );
    }

    private void onPlayerJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        if (!PatcherConfig.get().unlockAllCosmetics) return;
        var player = Minecraft.getInstance().player;
        if (player == null) return;
        try {
            var cosmetics = CosmeticSyncData.getLocalEquipped();
            if (!cosmetics.isEmpty()) {
                PacketDistributor.sendToServer(new CosmeticSyncPayload(player.getUUID(), cosmetics));
            }
        } catch (Exception e) {
            System.err.println("[EssentialPatcher] Failed to send cosmetic sync: " + e.getMessage());
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
