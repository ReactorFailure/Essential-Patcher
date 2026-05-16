package com.leclowndu93150.essentialpatcher.network;

import com.leclowndu93150.essentialpatcher.cosmetics.CosmeticSaver;
import gg.essential.mod.cosmetics.CosmeticSlot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CosmeticSyncData {

    public static Map<String, String> getLocalEquipped() {
        return CosmeticSaver.loadEquippedCosmetics();
    }

    public static void applyRemoteCosmetics(UUID playerUuid, Map<String, String> cosmetics) {
        try {
            if (cosmetics.isEmpty()) return;

            var essential = Class.forName("gg.essential.Essential").getMethod("getInstance").invoke(null);
            var connectionManager = essential.getClass().getMethod("getConnectionManager").invoke(essential);
            var cosmeticsManager = connectionManager.getClass().getMethod("getCosmeticsManager").invoke(connectionManager);
            var infraManager = cosmeticsManager.getClass().getMethod("getInfraEquippedOutfitsManager").invoke(cosmeticsManager);

            Map<CosmeticSlot, String> cosmeticMap = new HashMap<>();
            for (var entry : cosmetics.entrySet()) {
                CosmeticSlot slot = slotById(entry.getKey());
                if (slot != null) {
                    cosmeticMap.put(slot, entry.getValue());
                }
            }

            var infraOutfitClass = Class.forName("gg.essential.network.connectionmanager.cosmetics.InfraEquippedOutfitsManager$InfraOutfit");
            var constructor = infraOutfitClass.getConstructors()[0];
            var outfit = constructor.newInstance(cosmeticMap, Collections.emptyMap(), null);

            var updateMethod = infraManager.getClass().getMethod("update", UUID.class, infraOutfitClass);
            updateMethod.invoke(infraManager, playerUuid, outfit);
        } catch (Exception e) {
            System.err.println("[EssentialPatcher] Failed to apply remote cosmetics for " + playerUuid + ": " + e.getMessage());
        }
    }

    private static CosmeticSlot slotById(String id) {
        for (CosmeticSlot slot : CosmeticSlot.values()) {
            if (slot.getId().equals(id)) {
                return slot;
            }
        }
        return null;
    }
}
