package de.dafuqs.additionalentityattributes.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = PlayerEntityRenderer.class, priority = 500)
public class PlayerEntityRendererMixin {
    @ModifyArg(method = "getPositionOffset(Lnet/minecraft/client/network/AbstractClientPlayerEntity;F)Lnet/minecraft/util/math/Vec3d;", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;<init>(DDD)V"), index = 1)
    private double additionalEntityAttributes$applyModelScaleToPlayerOffset(double d, @Local AbstractClientPlayerEntity playerEntity) {
        return (Support.getModelHeight(playerEntity, (d * 16.0F / 2.0F)) * -2.0F) / 16.0F;
    }
}
