package com.leclowndu93150.essentialpatcher.cosmetics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.minecraft.client.Minecraft;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CosmeticSaver {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type MAP_TYPE = new TypeToken<Map<String, String>>() {}.getType();

    private static Path savePath() {
        return Minecraft.getInstance().gameDirectory.toPath().resolve("config").resolve("essentialpatcher-equipped.json");
    }

    public static void saveEquippedCosmetics(Map<String, String> slotToCosmetic) {
        try {
            Path path = savePath();
            Files.createDirectories(path.getParent());
            Files.writeString(path, GSON.toJson(slotToCosmetic));
        } catch (IOException e) {
            System.err.println("[EssentialPatcher] Failed to save equipped cosmetics: " + e.getMessage());
        }
    }

    public static Map<String, String> loadEquippedCosmetics() {
        Path path = savePath();
        if (!Files.exists(path)) {
            return new HashMap<>();
        }
        try {
            String json = Files.readString(path);
            Map<String, String> result = GSON.fromJson(json, MAP_TYPE);
            return result != null ? result : new HashMap<>();
        } catch (Exception e) {
            System.err.println("[EssentialPatcher] Failed to load equipped cosmetics: " + e.getMessage());
            return new HashMap<>();
        }
    }
}
