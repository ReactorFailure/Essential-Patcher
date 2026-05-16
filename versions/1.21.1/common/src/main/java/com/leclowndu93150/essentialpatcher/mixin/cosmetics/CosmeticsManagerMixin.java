package com.leclowndu93150.essentialpatcher.mixin.cosmetics;

import com.leclowndu93150.essentialpatcher.config.PatcherConfig;
import com.leclowndu93150.essentialpatcher.cosmetics.CosmeticSaver;
import gg.essential.cosmetics.model.CosmeticUnlockData;
import gg.essential.event.client.ClientTickEvent;
import gg.essential.gui.elementa.state.v2.MutableState;
import gg.essential.mod.cosmetics.CosmeticSlot;
import gg.essential.network.connectionmanager.cosmetics.CosmeticsManager;
import gg.essential.network.connectionmanager.cosmetics.InfraEquippedOutfitsManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mixin(value = CosmeticsManager.class, remap = false)
public abstract class CosmeticsManagerMixin {

    @Shadow
    public abstract void unlockAllCosmetics();

    @Shadow
    public abstract InfraEquippedOutfitsManager getInfraEquippedOutfitsManager();

    @Shadow
    private MutableState<Boolean> cosmeticsLoaded;

    @Unique
    private boolean essentialPatcher$hasUnlockedThisSession = false;

    @Inject(method = "addUnlockedCosmeticsData", at = @At("RETURN"))
    private void onUnlockedCosmeticsReceived(Map<String, CosmeticUnlockData> unlockedCosmeticsData, CallbackInfo ci) {
        if (PatcherConfig.get().unlockAllCosmetics) {
            unlockAllCosmetics();
            essentialPatcher$hasUnlockedThisSession = true;
            essentialPatcher$restoreOutfit();
        }
    }

    @Inject(method = "tick", at = @At("RETURN"))
    private void onTick(ClientTickEvent event, CallbackInfo ci) {
        if (PatcherConfig.get().unlockAllCosmetics && !essentialPatcher$hasUnlockedThisSession) {
            if (cosmeticsLoaded.getUntracked()) {
                unlockAllCosmetics();
                essentialPatcher$hasUnlockedThisSession = true;
                essentialPatcher$restoreOutfit();
            }
        }
    }

    @Inject(method = "resetState", at = @At("HEAD"))
    private void onResetState(CallbackInfo ci) {
        essentialPatcher$hasUnlockedThisSession = false;
    }

    @Inject(method = "removeUnlockedCosmetics", at = @At("HEAD"), cancellable = true)
    private void onRevokeCosmetics(CallbackInfo ci) {
        if (PatcherConfig.get().unlockAllCosmetics) {
            ci.cancel();
        }
    }

    @Unique
    private void essentialPatcher$restoreOutfit() {
        try {
            Map<String, String> saved = CosmeticSaver.loadEquippedCosmetics();
            if (saved.isEmpty()) return;

            Map<CosmeticSlot, String> cosmeticMap = new HashMap<>();
            for (var entry : saved.entrySet()) {
                CosmeticSlot slot = essentialPatcher$slotById(entry.getKey());
                if (slot != null) {
                    cosmeticMap.put(slot, entry.getValue());
                }
            }

            InfraEquippedOutfitsManager manager = getInfraEquippedOutfitsManager();
            var ownUuidMethod = manager.getClass().getDeclaredMethod("getOwnUuid");
            ownUuidMethod.setAccessible(true);
            var ownUuid = (UUID) ownUuidMethod.invoke(manager);

            var infraOutfitClass = Class.forName("gg.essential.network.connectionmanager.cosmetics.InfraEquippedOutfitsManager$InfraOutfit");
            var constructor = infraOutfitClass.getConstructors()[0];
            var outfit = constructor.newInstance(cosmeticMap, Collections.emptyMap(), null);

            manager.update(ownUuid, (InfraEquippedOutfitsManager.InfraOutfit) outfit);
        } catch (Exception e) {
            System.err.println("[EssentialPatcher] Failed to restore equipped cosmetics: " + e.getMessage());
        }
    }

    @Unique
    private static CosmeticSlot essentialPatcher$slotById(String id) {
        for (CosmeticSlot slot : CosmeticSlot.values()) {
            if (slot.getId().equals(id)) {
                return slot;
            }
        }
        return null;
    }
}
