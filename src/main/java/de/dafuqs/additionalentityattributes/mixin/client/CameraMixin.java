package de.dafuqs.additionalentityattributes.mixin.client;

import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = Camera.class, priority = 500)
public class CameraMixin {
    @Shadow private Entity focusedEntity;

    @ModifyArg(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;clipToSpace(D)D"), index = 0)
    private double additionalEntityAttributes$updateCameraScaleValue(double desiredCameraDistance) {
        if (this.focusedEntity instanceof LivingEntity living) {
            return (float) Support.getModelHeight(living, desiredCameraDistance / 4.0F) * 4.0F;
        }
        return desiredCameraDistance;
    }
}
