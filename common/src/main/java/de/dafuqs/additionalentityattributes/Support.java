package de.dafuqs.additionalentityattributes;

import net.minecraft.core.Holder;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;

public class Support {

	public static final double MIN_SCALE = 0.0625;
	public static final double MAX_SCALE = 16.0;
	
	public static float getExperienceMod(LivingEntity entity) {
		if (entity == null) {
			return 1.0F;
		}
		AttributeInstance attributeInstance = entity.getAttribute(AdditionalEntityAttributes.DROPPED_EXPERIENCE);
		if (attributeInstance == null) {
			return 1.0F;
		}
		return (float) attributeInstance.getValue();
	}

	public static double getHitboxWidth(LivingEntity entity, double original) {
		return Mth.clamp(original * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.WIDTH) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HITBOX_SCALE) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HITBOX_WIDTH), MIN_SCALE, MAX_SCALE);
	}

	public static double getModelWidth(LivingEntity entity, double original) {
		return Mth.clamp(original * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.WIDTH) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_SCALE) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_WIDTH), MIN_SCALE, MAX_SCALE);
	}

	public static double getHitboxHeight(LivingEntity entity, double original) {
		return Mth.clamp(original * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HEIGHT) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HITBOX_SCALE) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HITBOX_HEIGHT), MIN_SCALE, MAX_SCALE);
	}

	public static double getModelHeight(LivingEntity entity, double original) {
		return Mth.clamp(original * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HEIGHT) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_SCALE) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_HEIGHT), MIN_SCALE, MAX_SCALE);
	}

	public static double getScaleAttributeModifierValue(LivingEntity entity, Holder<Attribute> attribute) {
		double value = 1.0F;

		if (entity == null) {
			return value;
		}

		AttributeInstance instance = entity.getAttribute(attribute);
		if (instance != null) {
			value = instance.getValue();
		}

		return Mth.clamp(value, MIN_SCALE, MAX_SCALE);
	}

	public static double getMobDetectionValue(LivingEntity entity, double original) {
		if (entity == null) {
			return original;
		}

		AttributeInstance instance = entity.getAttribute(AdditionalEntityAttributes.MOB_DETECTION_RANGE);
		if (instance != null) {
			for (AttributeModifier modifier : instance.getModifiers()) {
				float amount = (float) modifier.amount();

				if (modifier.operation() == AttributeModifier.Operation.ADD_VALUE)
					original += amount;
				else
					original *= (amount + 1);
			}
		}

		return original;
	}

}
