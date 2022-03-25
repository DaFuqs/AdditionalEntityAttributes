package de.dafuqs.additionalentityattributes.mixin.common;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.FluidTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	
	@Inject(method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", require = 1, allow = 1, at = @At("RETURN"))
	private static void addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
		info.getReturnValue().add(AdditionalEntityAttributes.WATER_VISIBILITY);
		info.getReturnValue().add(AdditionalEntityAttributes.WATER_SPEED);
		info.getReturnValue().add(AdditionalEntityAttributes.LAVA_VISIBILITY);
		info.getReturnValue().add(AdditionalEntityAttributes.LAVA_SPEED);
		info.getReturnValue().add(AdditionalEntityAttributes.CRITICAL_DAMAGE_MULTIPLIER);
	}
	
	@Redirect(method = "travel", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V", ordinal = 0))
	public void modifySwimSpeed(LivingEntity livingEntity, float speedMultiplier, Vec3d movementInput) {
		EntityAttributeInstance waterSpeed = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.WATER_SPEED);
		if(waterSpeed == null) {
			livingEntity.updateVelocity(speedMultiplier, movementInput);
		} else {
			livingEntity.updateVelocity(speedMultiplier + (float) waterSpeed.getValue(), movementInput);
		}
	}
	
	@ModifyConstant(method = "swimUpward", constant = @Constant(doubleValue = 0.03999999910593033D))
	public double modifyUpwardSwimming(double original, Tag<Fluid> fluid) {
		if(fluid == FluidTags.WATER) {
			EntityAttributeInstance waterSpeed = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.WATER_SPEED);
			if(waterSpeed == null) {
				return original;
			} else {
				return original + waterSpeed.getValue();
			}
		} else {
			return original;
		}
	}
	
	@Environment(EnvType.CLIENT)
	@ModifyConstant(method = "knockDownwards", constant = @Constant(doubleValue = -0.03999999910593033D))
	public double knockDownwards(double original) {
		EntityAttributeInstance waterSpeed = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.WATER_SPEED);
		if(waterSpeed == null) {
			return original;
		} else {
			return original + waterSpeed.getValue();
		}
	}
	
	@ModifyConstant(method = "travel", constant = {@Constant(doubleValue = 0.5D, ordinal = 0), @Constant(doubleValue = 0.5D, ordinal = 1), @Constant(doubleValue = 0.5D, ordinal = 2)})
	private double increasedLavaSpeed(double original) {
		EntityAttributeInstance lavaSpeed = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.LAVA_SPEED);
		if(lavaSpeed == null) {
			return original;
		} else {
			return original + lavaSpeed.getValue();
		}
	}
}