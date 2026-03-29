package de.dafuqs.additionalentityattributes.mixin.client;

import de.dafuqs.additionalentityattributes.Support;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = Camera.class, priority = 500)
public class CameraMixin {
    
    @Shadow private Entity entity;
    
    @ModifyVariable(method = "alignWithEntity", at = @At("STORE"), name = "cameraScale")
    private float additionalEntityAttributes$updateCameraScaleValue(float scale) {
        if (this.entity instanceof LivingEntity living) {
            return (float) Support.getModelHeight(living, scale);
        }
        return scale;
    }
    
}
