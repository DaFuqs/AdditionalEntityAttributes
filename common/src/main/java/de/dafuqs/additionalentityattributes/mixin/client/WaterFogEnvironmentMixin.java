package de.dafuqs.additionalentityattributes.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.client.Camera;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.fog.FogData;
import net.minecraft.client.renderer.fog.environment.WaterFogEnvironment;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WaterFogEnvironment.class)
public abstract class WaterFogEnvironmentMixin {
    @Inject(method = "setupFog", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;entity()Lnet/minecraft/world/entity/Entity;", shift = At.Shift.AFTER))
    private static void additionalEntityAttributes$modifyWaterVisibility(FogData fogData, Camera camera, ClientLevel level, float p_423585_, DeltaTracker deltaTracker, CallbackInfo ci) {
        AttributeInstance waterVisibilityAttribute = camera.entity() instanceof LivingEntity living ? living.getAttribute(AdditionalEntityAttributes.WATER_VISIBILITY) : null;
        float original = fogData.environmentalEnd;

        if (waterVisibilityAttribute != null) {
            if (waterVisibilityAttribute.getBaseValue() != original) {
                waterVisibilityAttribute.setBaseValue(original);
            }

            fogData.environmentalEnd = (float) waterVisibilityAttribute.getValue();
        }
    }
}
