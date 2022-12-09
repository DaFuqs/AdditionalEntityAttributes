package de.dafuqs.additionalentityattributes;

import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;

public class Support {
	
	public static float getExperienceMod(PlayerEntity player) {
		EntityAttributeInstance attributeInstance = player.getAttributeInstance(AdditionalEntityAttributes.DROPPED_EXPERIENCE);
		if (attributeInstance == null) {
			return 1.0F;
		}
		return (float) attributeInstance.getValue();
	}
	
}
