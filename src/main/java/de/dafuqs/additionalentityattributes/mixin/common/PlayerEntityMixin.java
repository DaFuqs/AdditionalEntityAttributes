package de.dafuqs.additionalentityattributes.mixin.common;

import de.dafuqs.additionalentityattributes.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.stat.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.*;
import java.util.function.*;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {

	@Inject(method = "createPlayerAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", require = 1, allow = 1, at = @At("RETURN"))
	private static void additionalEntityAttributes$addPlayerAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
		info.getReturnValue().add(AdditionalEntityAttributes.WATER_VISIBILITY);
		info.getReturnValue().add(AdditionalEntityAttributes.LAVA_VISIBILITY);
		info.getReturnValue().add(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE);
		info.getReturnValue().add(AdditionalEntityAttributes.DIG_SPEED);
		info.getReturnValue().add(AdditionalEntityAttributes.BONUS_LOOT_COUNT_ROLLS);
		info.getReturnValue().add(AdditionalEntityAttributes.BONUS_RARE_LOOT_ROLLS);
		info.getReturnValue().add(AdditionalEntityAttributes.DROPPED_EXPERIENCE);
		info.getReturnValue().add(AdditionalEntityAttributes.COLLECTION_RANGE);
	}
	
	/**
	 * By default, the additional crit damage is a 50% bonus
	 */
	@ModifyConstant(method = "attack(Lnet/minecraft/entity/Entity;)V", constant = @Constant(floatValue = 1.5F))
	public float additionalEntityAttributes$applyCriticalBonusDamage(float original) {
		EntityAttributeInstance criticalDamageMultiplier = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE);
		if (criticalDamageMultiplier == null) {
			return original;
		} else {
			return 1 + (float) criticalDamageMultiplier.getValue();
		}
	}
	
	@ModifyVariable(method = "getBlockBreakingSpeed", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectUtil;hasHaste(Lnet/minecraft/entity/LivingEntity;)Z"), index = 2)
	private float additionalEntityAttributes$adjustBlockBreakingSpeed(float f) {
		EntityAttributeInstance instance = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.DIG_SPEED);
		
		if (instance != null) {
			for (EntityAttributeModifier modifier : instance.getModifiers()) {
				float amount = (float) modifier.getValue();
				
				if (modifier.getOperation() == EntityAttributeModifier.Operation.ADDITION)
					f += amount;
				else
					f *= (amount + 1);
			}
		}
		
		return f;
	}

	@ModifyVariable(method = "tickMovement", at = @At("STORE"))
	private List<Entity> additionalEntityAttributes$adjustCollectionRange(List<Entity> original) {
		PlayerEntity thisPlayer = (PlayerEntity)(Object) this;
		EntityAttributeInstance instance = thisPlayer.getAttributeInstance(AdditionalEntityAttributes.COLLECTION_RANGE);

		if (instance != null && instance.getValue() > 0) {
			Box expandedBox;
			if (thisPlayer.hasVehicle() && !thisPlayer.getVehicle().isRemoved()) {
				expandedBox = thisPlayer.getBoundingBox().union(thisPlayer.getVehicle().getBoundingBox()).expand(1.0, 0.0, 1.0).expand(instance.getValue());
			} else {
				expandedBox = thisPlayer.getBoundingBox().expand(1.0, 0.5, 1.0).expand(instance.getValue());
			}

			original.addAll(thisPlayer.getWorld().getOtherEntities(thisPlayer, expandedBox, new Predicate<Entity>() {
				@Override
				public boolean test(Entity entity) {
					EntityType<?> type = entity.getType();
					return type.isIn(AdditionalEntityAttributesEntityTags.AFFECTED_BY_COLLECTION_RANGE) && !original.contains(entity);
				}
			}));
		}
		return original;
	}
	
}