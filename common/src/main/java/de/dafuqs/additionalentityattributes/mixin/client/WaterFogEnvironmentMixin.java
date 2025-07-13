package de.dafuqs.additionalentityattributes.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.client.renderer.fog.environment.WaterFogEnvironment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WaterFogEnvironment.class)
public abstract class WaterFogEnvironmentMixin {
    @ModifyExpressionValue(method = "setupFog", at = @At(value = "CONSTANT", args = "floatValue=96F", ordinal = 0))
    private static float additionalEntityAttributes$modifyWaterVisibility(float original, @Local(argsOnly = true) Entity entity) {
        AttributeInstance waterVisibilityAttribute = entity instanceof LivingEntity living ? living.getAttribute(AdditionalEntityAttributes.WATER_VISIBILITY) : null;
        if (waterVisibilityAttribute == null) {
            return original;
        } else {
            if (waterVisibilityAttribute.getBaseValue() != original) {
                waterVisibilityAttribute.setBaseValue(original);
            }
            return (float) waterVisibilityAttribute.getValue();
        }
    }
}
