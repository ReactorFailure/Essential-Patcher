package com.leclowndu93150.essentialpatcher.httpsync;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.server.IntegratedServer;

import java.util.Locale;

/**
 * Picks a session_id ingredient + label appropriate for the current connection.
 * - Multiplayer: ip:port (lowercased).
 * - LAN-published singleplayer: the world name plus the host's UUID so it's unique per host.
 * - Singleplayer (not published): the world name.
 *
 * The CosmeticHttpSync hashes this so the actual server_id we send to the panel is opaque.
 */
public final class SessionKey {
    private SessionKey() {
    }

    public static String[] compute() {
        Minecraft mc = Minecraft.getInstance();
        ServerData sd = mc.getCurrentServer();
        if (sd != null && sd.ip != null && !sd.ip.isBlank()) {
            return new String[] { sd.ip.toLowerCase(Locale.ROOT), "smp" };
        }
        IntegratedServer integ = mc.hasSingleplayerServer() ? mc.getSingleplayerServer() : null;
        if (integ != null) {
            String world = integ.getWorldData() != null ? integ.getWorldData().getLevelName() : "world";
            if (integ.isPublished()) {
                String host = mc.getUser() != null && mc.getUser().getProfileId() != null
                        ? mc.getUser().getProfileId().toString()
                        : "host";
                return new String[] { host + "|" + world, "lan" };
            }
            return new String[] { world, "sp" };
        }
        return null;
    }
}
