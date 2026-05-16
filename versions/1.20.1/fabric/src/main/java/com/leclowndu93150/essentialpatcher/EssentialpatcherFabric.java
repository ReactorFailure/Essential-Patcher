package com.leclowndu93150.essentialpatcher;

import com.leclowndu93150.essentialpatcher.config.PatcherConfig;
import com.leclowndu93150.essentialpatcher.network.NetworkHandler;
import com.leclowndu93150.essentialpatcher.platform.Platform;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

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
        NetworkHandler.register();

        if (config.disableAutoUpdate) {
            disableEssentialAutoUpdate("1.20.1");
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
