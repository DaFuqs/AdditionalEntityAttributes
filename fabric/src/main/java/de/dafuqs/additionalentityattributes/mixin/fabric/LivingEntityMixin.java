package de.dafuqs.additionalentityattributes.mixin.fabric;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.material.Fluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyReturnValue(method = "createLivingAttributes", require = 1, allow = 1, at = @At("RETURN"))
    private static AttributeSupplier.Builder additionalEntityAttributes$addAttributes(AttributeSupplier.Builder original) {
        return original
            .add(AdditionalEntityAttributes.WATER_SPEED)
            .add(AdditionalEntityAttributes.LAVA_SPEED)
            .add(AdditionalEntityAttributes.WIDTH)
            .add(AdditionalEntityAttributes.HEIGHT)
            .add(AdditionalEntityAttributes.HITBOX_SCALE)
            .add(AdditionalEntityAttributes.HITBOX_WIDTH)
            .add(AdditionalEntityAttributes.HITBOX_HEIGHT)
            .add(AdditionalEntityAttributes.MODEL_SCALE)
            .add(AdditionalEntityAttributes.MODEL_WIDTH)
            .add(AdditionalEntityAttributes.MODEL_HEIGHT)
            .add(AdditionalEntityAttributes.MOB_DETECTION_RANGE)
            .add(AdditionalEntityAttributes.MAGIC_PROTECTION);
    }

    @ModifyExpressionValue(method = "jumpInLiquid", at = @At(value = "CONSTANT", args = "doubleValue=0.03999999910593033D"))
    public double additionalEntityAttributes$modifyUpwardSwimming(double original, TagKey<Fluid> fluid) {
        if (fluid == FluidTags.WATER) {
            AttributeInstance waterSpeed = ((LivingEntity) (Object) this).getAttribute(AdditionalEntityAttributes.WATER_SPEED);
            if (waterSpeed == null) {
                return original;
            } else {
                if (waterSpeed.getBaseValue() != original) {
                    waterSpeed.setBaseValue(original);
                }
                return waterSpeed.getValue();
            }
        } else {
            return original;
        }
    }

    @ModifyExpressionValue(method = "goDownInWater", at = @At(value = "CONSTANT", args = "doubleValue=-0.03999999910593033D"))
    public double additionalEntityAttributes$knockDownwards(double original) {
        AttributeInstance waterSpeed = ((LivingEntity) (Object) this).getAttribute(AdditionalEntityAttributes.WATER_SPEED);
        if (waterSpeed == null) {
            return original;
        } else {
            if (waterSpeed.getBaseValue() != -original) {
                waterSpeed.setBaseValue(-original);
            }
            return -waterSpeed.getValue();
        }
    }

    @ModifyArg(method = "travelInWater", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V", ordinal = 0))
    public float additionalEntityAttributes$waterSpeed(float original) {
        AttributeInstance waterSpeed = ((LivingEntity) (Object) this).getAttribute(AdditionalEntityAttributes.WATER_SPEED);
        if (waterSpeed == null) {
            return original;
        } else {
            if (waterSpeed.getBaseValue() != original) {
                waterSpeed.setBaseValue(original);
            }
            return (float) waterSpeed.getValue();
        }
    }

    @ModifyArg(method = "travelInLava", at = @At(target = "Lnet/minecraft/world/phys/Vec3;scale(D)Lnet/minecraft/world/phys/Vec3;", value = "INVOKE", ordinal = 0))
    private double additionalEntityAttributes$increasedLavaSpeed(double original) {
        AttributeInstance lavaSpeed = ((LivingEntity) (Object) this).getAttribute(AdditionalEntityAttributes.LAVA_SPEED);
        if (lavaSpeed == null) {
            return original;
        } else {
            if (lavaSpeed.getBaseValue() != original) {
                lavaSpeed.setBaseValue(original);
            }
            return lavaSpeed.getValue();
        }
    }

}