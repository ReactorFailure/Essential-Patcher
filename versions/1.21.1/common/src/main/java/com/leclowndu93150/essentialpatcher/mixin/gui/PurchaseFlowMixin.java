package com.leclowndu93150.essentialpatcher.mixin.gui;

import com.leclowndu93150.essentialpatcher.config.PatcherConfig;
import gg.essential.gui.wardrobe.Item;
import gg.essential.gui.wardrobe.PurchaseKt;
import gg.essential.gui.wardrobe.WardrobeState;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

/**
 * Intercepts every wardrobe purchase entry point. Instead of contacting Essential's
 * checkout infra (which would fail because we lie about prices via showAllFree), we
 * locally mark the catalog as "all unlocked" and report success to the GUI. The
 * unlockAllCosmetics call mutates CosmeticsManager.unlockedCosmeticsData so the
 * wardrobe UI immediately reflects the cosmetic as owned, the "Claim" button flips
 * to "Equip", and the purchase-success toast fires.
 */
@Mixin(value = PurchaseKt.class, remap = false)
public class PurchaseFlowMixin {

    @Inject(method = "purchaseCosmeticOrEmote(Lgg/essential/gui/wardrobe/WardrobeState;Lgg/essential/gui/wardrobe/Item$CosmeticOrEmote;Lkotlin/jvm/functions/Function1;)V",
            at = @At("HEAD"), cancellable = true)
    private static void onPurchaseCosmetic(WardrobeState state, Item.CosmeticOrEmote item, Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (PatcherConfig.get().disablePurchaseFlows) {
            state.getCosmeticsManager().unlockAllCosmetics();
            callback.invoke(true);
            ci.cancel();
        }
    }

    @Inject(method = "purchaseAndCreateOutfitForBundle", at = @At("HEAD"), cancellable = true)
    private static void onPurchaseBundle(WardrobeState state, Item.Bundle item, boolean changeSelectedOutfit, Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (PatcherConfig.get().disablePurchaseFlows) {
            state.getCosmeticsManager().unlockAllCosmetics();
            callback.invoke(true);
            ci.cancel();
        }
    }

    @Inject(method = "purchaseSelectedBundle", at = @At("HEAD"), cancellable = true)
    private static void onPurchaseSelectedBundle(WardrobeState state, Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (PatcherConfig.get().disablePurchaseFlows) {
            state.getCosmeticsManager().unlockAllCosmetics();
            callback.invoke(true);
            ci.cancel();
        }
    }

    @Inject(method = "purchaseEquippedCosmetics", at = @At("HEAD"), cancellable = true)
    private static void onPurchaseEquipped(WardrobeState state, Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (PatcherConfig.get().disablePurchaseFlows) {
            state.getCosmeticsManager().unlockAllCosmetics();
            callback.invoke(true);
            ci.cancel();
        }
    }

    @Inject(method = "purchaseSelectedEmote", at = @At("HEAD"), cancellable = true)
    private static void onPurchaseSelectedEmote(WardrobeState state, Function1<? super Boolean, Unit> callback, CallbackInfo ci) {
        if (PatcherConfig.get().disablePurchaseFlows) {
            state.getCosmeticsManager().unlockAllCosmetics();
            callback.invoke(true);
            ci.cancel();
        }
    }

    @Inject(method = "giftCosmeticOrEmote", at = @At("HEAD"), cancellable = true)
    private static void onGiftCosmetic(WardrobeState state, Item.CosmeticOrEmote item, UUID giftTo, Function2<? super Boolean, ? super String, Unit> callback, CallbackInfo ci) {
        if (PatcherConfig.get().disablePurchaseFlows) {
            // Gifting actually needs a real recipient on Essential's side, so spoofing
            // success would be a lie. Cancel quietly.
            callback.invoke(false, null);
            ci.cancel();
        }
    }

    @Inject(method = "openPurchaseItemModal", at = @At("HEAD"), cancellable = true)
    private static void onOpenPurchaseModal(WardrobeState state, Item item, Function0<Unit> primaryAction, CallbackInfo ci) {
        if (PatcherConfig.get().disablePurchaseFlows) {
            // Run the primary action immediately instead of opening the modal.
            // The primary action is the actual purchase call we intercept above,
            // so it'll short-circuit to success via the other injectors.
            primaryAction.invoke();
            ci.cancel();
        }
    }
}
