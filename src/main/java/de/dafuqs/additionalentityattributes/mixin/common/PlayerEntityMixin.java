package de.dafuqs.additionalentityattributes.mixin.common;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
	
	/**
	 * By default, the additional crit damage is a 50% bonus
	 */
	@ModifyConstant(method = "attack(Lnet/minecraft/entity/Entity;)V", constant = @Constant(floatValue = 1.5F))
	public float applyCriticalDamageMultiplierAttribute(float original) {
		EntityAttributeInstance criticalDamageMultiplier = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE);
		if(criticalDamageMultiplier == null) {
			return original;
		} else {
			return 1 + (float) criticalDamageMultiplier.getValue();
		}
	}

}