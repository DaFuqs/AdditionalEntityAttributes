package de.dafuqs.additionalentityattributes.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BackgroundRenderer.class)
public abstract class BackgroundRendererMixin {
	
	@ModifyExpressionValue(method = "applyFog", at = @At(value = "CONSTANT", args = "floatValue=0.25F", ordinal = 0))
	private static float additionalEntityAttributes$modifyLavaVisibilityMinWithoutFireResistance(float original, Camera camera) {
		EntityAttributeInstance lavaVisibilityAttribute = MinecraftClient.getInstance().player.getAttributeInstance(AdditionalEntityAttributes.LAVA_VISIBILITY);
		if (lavaVisibilityAttribute == null) {
			return original;
		} else {
			if (lavaVisibilityAttribute.getBaseValue() != original) {
				lavaVisibilityAttribute.setBaseValue(original);
			}
			return original - (float) lavaVisibilityAttribute.getValue() * 0.25F;
		}
	}
	
	@ModifyExpressionValue(method = "applyFog", at = @At(value = "CONSTANT", args = "floatValue=1.0F", ordinal = 0))
	private static float additionalEntityAttributes$modifyLavaVisibilityMaxWithoutFireResistance(float original, Camera camera) {
		EntityAttributeInstance lavaVisibilityAttribute = MinecraftClient.getInstance().player.getAttributeInstance(AdditionalEntityAttributes.LAVA_VISIBILITY);
		if (lavaVisibilityAttribute == null) {
			return original;
		} else {
			if (lavaVisibilityAttribute.getBaseValue() != original) {
				lavaVisibilityAttribute.setBaseValue(original);
			}
			return (float) lavaVisibilityAttribute.getValue();
		}
	}
	
	@ModifyExpressionValue(method = "applyFog", at = @At(value = "CONSTANT", args = "floatValue=0.0F", ordinal = 0))
	private static float additionalEntityAttributes$modifyLavaVisibilityMinFireResistance(float original, Camera camera) {
		EntityAttributeInstance lavaVisibilityAttribute = MinecraftClient.getInstance().player.getAttributeInstance(AdditionalEntityAttributes.LAVA_VISIBILITY);
		if (lavaVisibilityAttribute == null) {
			return original;
		} else {
			if (lavaVisibilityAttribute.getBaseValue() != original) {
				lavaVisibilityAttribute.setBaseValue(original);
			}
			return original - (float) lavaVisibilityAttribute.getValue();
		}
	}
	
	@ModifyExpressionValue(method = "applyFog", at = @At(value = "CONSTANT", args = "floatValue=3.0F", ordinal = 0))
	private static float additionalEntityAttributes$modifyLavaVisibilityMaxWithFireResistance(float original, Camera camera) {
		EntityAttributeInstance lavaVisibilityAttribute = MinecraftClient.getInstance().player.getAttributeInstance(AdditionalEntityAttributes.LAVA_VISIBILITY);
		if (lavaVisibilityAttribute == null) {
			return original;
		} else {
			if (lavaVisibilityAttribute.getBaseValue() != original) {
				lavaVisibilityAttribute.setBaseValue(original);
			}
			return (float) lavaVisibilityAttribute.getValue();
		}
	}
	
	@ModifyExpressionValue(method = "applyFog", at = @At(value = "CONSTANT", args = "floatValue=96F", ordinal = 0))
	private static float additionalEntityAttributes$modifyWaterVisibility(float original, Camera camera) {
		EntityAttributeInstance waterVisibilityAttribute = MinecraftClient.getInstance().player.getAttributeInstance(AdditionalEntityAttributes.WATER_VISIBILITY);
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