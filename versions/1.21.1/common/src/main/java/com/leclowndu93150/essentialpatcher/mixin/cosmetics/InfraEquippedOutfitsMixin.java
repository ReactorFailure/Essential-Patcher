package com.leclowndu93150.essentialpatcher.mixin.cosmetics;

import com.leclowndu93150.essentialpatcher.config.PatcherConfig;
import com.leclowndu93150.essentialpatcher.cosmetics.CosmeticSaver;
import com.leclowndu93150.essentialpatcher.httpsync.CosmeticHttpSync;
import gg.essential.network.connectionmanager.cosmetics.InfraEquippedOutfitsManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mixin(value = InfraEquippedOutfitsManager.class, remap = false)
public class InfraEquippedOutfitsMixin {

    @Inject(method = "update", at = @At("RETURN"))
    private void onUpdate(UUID playerId, InfraEquippedOutfitsManager.InfraOutfit outfit, CallbackInfo ci) {
        if (!PatcherConfig.get().unlockAllCosmetics) return;

        try {
            InfraEquippedOutfitsManager self = (InfraEquippedOutfitsManager) (Object) this;
            UUID ownUuid = getOwnUuid(self);
            if (ownUuid == null || !ownUuid.equals(playerId)) return;

            var equipped = self.getEquippedCosmetics();
            Map<String, String> serializable = new HashMap<>();
            for (var entry : equipped.getCosmetics().entrySet()) {
                serializable.put(entry.getKey().getId(), entry.getValue().getId());
            }
            CosmeticSaver.saveEquippedCosmetics(serializable);
            CosmeticHttpSync.get().onLocalCosmeticChange(serializable);
        } catch (Exception e) {
            System.err.println("[EssentialPatcher] Failed to save equipped cosmetics on update: " + e.getMessage());
        }
    }

    private static UUID getOwnUuid(InfraEquippedOutfitsManager manager) {
        try {
            var method = manager.getClass().getDeclaredMethod("getOwnUuid");
            method.setAccessible(true);
            return (UUID) method.invoke(manager);
        } catch (Exception e) {
            return null;
        }
    }
}
