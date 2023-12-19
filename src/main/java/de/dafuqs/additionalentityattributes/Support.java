package de.dafuqs.additionalentityattributes;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.MathHelper;

public class Support {

	private static final double MIN_SCALE = 0.0625;
	private static final double MAX_SCALE = 16.0;

	public static float getExperienceMod(PlayerEntity player) {
		if(player == null) {
			return 1.0F;
		}
		EntityAttributeInstance attributeInstance = player.getAttributeInstance(AdditionalEntityAttributes.DROPPED_EXPERIENCE);
		if (attributeInstance == null) {
			return 1.0F;
		}
		return (float) attributeInstance.getValue();
	}

	public static double getHitboxWidth(LivingEntity entity, double original) {
		return MathHelper.clamp(original * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.WIDTH) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HITBOX_SCALE) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HITBOX_WIDTH), MIN_SCALE, MAX_SCALE);
	}

	public static double getModelWidth(LivingEntity entity, double original) {
		return MathHelper.clamp(original * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.WIDTH) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_SCALE) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_WIDTH), MIN_SCALE, MAX_SCALE);
	}

	public static double getHitboxHeight(LivingEntity entity, double original) {
		return MathHelper.clamp(original * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HEIGHT) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HITBOX_SCALE) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HITBOX_HEIGHT), MIN_SCALE, MAX_SCALE);
	}

	public static double getModelHeight(LivingEntity entity, double original) {
		return MathHelper.clamp(original * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HEIGHT) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_SCALE) * getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_HEIGHT), MIN_SCALE, MAX_SCALE);
	}

	public static double getScaleAttributeModifierValue(LivingEntity entity, RegistryEntry<EntityAttribute> attribute) {
		double value = 1.0F;

		if (entity == null) {
			return value;
		}

		EntityAttributeInstance instance = entity.getAttributeInstance(attribute);
		if (instance != null) {
			for (EntityAttributeModifier modifier : instance.getModifiers()) {
				float amount = (float) modifier.getValue();

				if (modifier.getOperation() == EntityAttributeModifier.Operation.ADDITION)
					value += amount;
				else
					value *= (amount + 1);
			}
		}

		return MathHelper.clamp(value, MIN_SCALE, MAX_SCALE);
	}

}
