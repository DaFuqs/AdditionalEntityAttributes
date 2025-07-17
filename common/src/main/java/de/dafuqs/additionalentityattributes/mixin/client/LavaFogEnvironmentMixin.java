package de.dafuqs.additionalentityattributes.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.client.renderer.fog.environment.LavaFogEnvironment;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LavaFogEnvironment.class)
public abstract class LavaFogEnvironmentMixin {
    @ModifyExpressionValue(method = "setupFog", at = @At(value = "CONSTANT", args = "floatValue=0.25F", ordinal = 0))
    private static float additionalEntityAttributes$modifyLavaVisibilityMinWithoutFireResistance(float original, @Local(argsOnly = true) Entity entity) {
        AttributeInstance lavaVisibilityAttribute = entity instanceof LivingEntity living ? living.getAttribute(AdditionalEntityAttributes.LAVA_VISIBILITY) : null;
        if (lavaVisibilityAttribute == null) {
            return original;
        } else {
            if (lavaVisibilityAttribute.getBaseValue() != original) {
                lavaVisibilityAttribute.setBaseValue(original);
            }
            return original - (float) lavaVisibilityAttribute.getValue() * 0.25F;
        }
    }

    @ModifyExpressionValue(method = "setupFog", at = @At(value = "CONSTANT", args = "floatValue=1.0F", ordinal = 0))
    private static float additionalEntityAttributes$modifyLavaVisibilityMaxWithoutFireResistance(float original, @Local(argsOnly = true) Entity entity) {
        AttributeInstance lavaVisibilityAttribute = entity instanceof LivingEntity living ? living.getAttribute(AdditionalEntityAttributes.LAVA_VISIBILITY) : null;
        if (lavaVisibilityAttribute == null) {
            return original;
        } else {
            if (lavaVisibilityAttribute.getBaseValue() != original) {
                lavaVisibilityAttribute.setBaseValue(original);
            }
            return (float) lavaVisibilityAttribute.getValue();
        }
    }

    @ModifyExpressionValue(method = "setupFog", at = @At(value = "CONSTANT", args = "floatValue=0.0F", ordinal = 0))
    private static float additionalEntityAttributes$modifyLavaVisibilityMinFireResistance(float original, @Local(argsOnly = true) Entity entity) {
        AttributeInstance lavaVisibilityAttribute = entity instanceof LivingEntity living ? living.getAttribute(AdditionalEntityAttributes.LAVA_VISIBILITY) : null;
        if (lavaVisibilityAttribute == null) {
            return original;
        } else {
            if (lavaVisibilityAttribute.getBaseValue() != original) {
                lavaVisibilityAttribute.setBaseValue(original);
            }
            return original - (float) lavaVisibilityAttribute.getValue();
        }
    }

    @ModifyExpressionValue(method = "setupFog", at = @At(value = "CONSTANT", args = "floatValue=5.0F", ordinal = 0))
    private static float additionalEntityAttributes$modifyLavaVisibilityMaxWithFireResistance(float original, @Local(argsOnly = true) Entity entity) {
        AttributeInstance lavaVisibilityAttribute = entity instanceof LivingEntity living ? living.getAttribute(AdditionalEntityAttributes.LAVA_VISIBILITY) : null;
        if (lavaVisibilityAttribute == null) {
            return original;
        } else {
            if (lavaVisibilityAttribute.getBaseValue() != original) {
                lavaVisibilityAttribute.setBaseValue(original);
            }
            return (float) lavaVisibilityAttribute.getValue();
        }
    }
}
