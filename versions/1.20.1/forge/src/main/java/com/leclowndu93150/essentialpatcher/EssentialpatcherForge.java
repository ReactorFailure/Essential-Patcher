package com.leclowndu93150.essentialpatcher;

import com.leclowndu93150.essentialpatcher.config.PatcherConfig;
import com.leclowndu93150.essentialpatcher.network.CosmeticSyncData;
import com.leclowndu93150.essentialpatcher.network.CosmeticSyncPacket;
import com.leclowndu93150.essentialpatcher.network.NetworkHandler;
import com.leclowndu93150.essentialpatcher.platform.Platform;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

@Mod("essentialpatcher")
public class EssentialpatcherForge {

    public EssentialpatcherForge() {
        Platform.Holder.set(new Platform() {
            @Override
            public Path getConfigDir() { return FMLPaths.CONFIGDIR.get(); }
            @Override
            public Path getGameDir() { return FMLPaths.GAMEDIR.get(); }
        });

        NetworkHandler.register();
        MinecraftForge.EVENT_BUS.register(this);

        PatcherConfig config = PatcherConfig.get();
        if (config.disableAutoUpdate) {
            disableEssentialAutoUpdate("1.20.1");
        }
    }

    @SubscribeEvent
    public void onPlayerJoin(ClientPlayerNetworkEvent.LoggingIn event) {
        if (!PatcherConfig.get().unlockAllCosmetics) return;
        var player = Minecraft.getInstance().player;
        if (player == null) return;
        try {
            var cosmetics = CosmeticSyncData.getLocalEquipped();
            if (!cosmetics.isEmpty()) {
                NetworkHandler.sendToServer(new CosmeticSyncPacket(player.getUUID(), cosmetics));
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
