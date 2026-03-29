package de.dafuqs.additionalentityattributes.mixin.common;

import java.util.List;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributesEntityTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

@Mixin(Player.class)
public abstract class PlayerMixin {
	
	/**
	 * By default, the additional crit damage is a 50% bonus
	 */
	@ModifyExpressionValue(method = "attack", at = @At(value = "CONSTANT", args = "floatValue=1.5F"))
	public float additionalEntityAttributes$applyCriticalBonusDamage(float original) {
		AttributeInstance criticalDamageMultiplier = ((LivingEntity) (Object) this).getAttribute(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE);
		if (criticalDamageMultiplier == null) {
			return original;
		} else {
			return 1 + (float) criticalDamageMultiplier.getValue();
		}
	}
	
	// since we filter on an entity tag here, we cannot simply expand the original box
	// and therefore have to run a second `getOtherEntities` check :(
	@ModifyVariable(method = "aiStep", at = @At("STORE"), name = "entities")
	private List<Entity> additionalEntityAttributes$adjustCollectionRange(List<Entity> original) {
        Player thisPlayer = (Player)(Object) this;
		AttributeInstance instance = thisPlayer.getAttribute(AdditionalEntityAttributes.COLLECTION_RANGE);
		
		if (instance != null && instance.getValue() > 0) {
			AABB expandedBox;
			if (thisPlayer.isPassenger() && !thisPlayer.getVehicle().isRemoved()) {
				expandedBox = thisPlayer.getBoundingBox().minmax(thisPlayer.getVehicle().getBoundingBox()).inflate(1.0, 0.0, 1.0).inflate(instance.getValue());
			} else {
				expandedBox = thisPlayer.getBoundingBox().inflate(1.0, 0.5, 1.0).inflate(instance.getValue());
			}
			
			original.addAll(thisPlayer.level().getEntities(thisPlayer, expandedBox, entity -> {
				EntityType<?> type = entity.getType();
				return type.builtInRegistryHolder().is(AdditionalEntityAttributesEntityTags.AFFECTED_BY_COLLECTION_RANGE) && !original.contains(entity);
			}));
		}
		return original;
	}
	
}