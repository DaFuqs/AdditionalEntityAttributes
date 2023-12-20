package de.dafuqs.additionalentityattributes.mixin.common;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.*;
import net.fabricmc.api.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.player.*;
import net.minecraft.fluid.*;
import net.minecraft.registry.tag.*;
import org.jetbrains.annotations.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    @Nullable
    protected PlayerEntity attackingPlayer;

    @Inject(method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", require = 1, allow = 1, at = @At("RETURN"))
    private static void additionalEntityAttributes$addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        info.getReturnValue().add(AdditionalEntityAttributes.WATER_VISIBILITY);
        info.getReturnValue().add(AdditionalEntityAttributes.WATER_SPEED);
        info.getReturnValue().add(AdditionalEntityAttributes.LUNG_CAPACITY);
        info.getReturnValue().add(AdditionalEntityAttributes.LAVA_VISIBILITY);
        info.getReturnValue().add(AdditionalEntityAttributes.LAVA_SPEED);
        info.getReturnValue().add(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE);
        info.getReturnValue().add(AdditionalEntityAttributes.DIG_SPEED);
        info.getReturnValue().add(AdditionalEntityAttributes.BONUS_LOOT_COUNT_ROLLS);
        info.getReturnValue().add(AdditionalEntityAttributes.BONUS_RARE_LOOT_ROLLS);
        info.getReturnValue().add(AdditionalEntityAttributes.WIDTH);
        info.getReturnValue().add(AdditionalEntityAttributes.HEIGHT);
        info.getReturnValue().add(AdditionalEntityAttributes.HITBOX_SCALE);
        info.getReturnValue().add(AdditionalEntityAttributes.HITBOX_WIDTH);
        info.getReturnValue().add(AdditionalEntityAttributes.HITBOX_HEIGHT);
        info.getReturnValue().add(AdditionalEntityAttributes.MODEL_SCALE);
        info.getReturnValue().add(AdditionalEntityAttributes.MODEL_WIDTH);
        info.getReturnValue().add(AdditionalEntityAttributes.MODEL_HEIGHT);
        info.getReturnValue().add(AdditionalEntityAttributes.MOB_DETECTION_RANGE);
        info.getReturnValue().add(AdditionalEntityAttributes.MAGIC_PROTECTION);
    }

    @ModifyArg(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V", ordinal = 0))
    public float additionalEntityAttributes$waterSpeed(float original) {
        EntityAttributeInstance waterSpeed = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.WATER_SPEED);
        if (waterSpeed == null) {
            return original;
        } else {
            if (waterSpeed.getBaseValue() != original) {
                waterSpeed.setBaseValue(original);
            }
            return (float) waterSpeed.getValue();
        }
    }

    @ModifyConstant(method = "swimUpward", constant = @Constant(doubleValue = 0.03999999910593033D))
    public double additionalEntityAttributes$modifyUpwardSwimming(double original, TagKey<Fluid> fluid) {
        if (fluid == FluidTags.WATER) {
            EntityAttributeInstance waterSpeed = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.WATER_SPEED);
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

    @Environment(EnvType.CLIENT)
    @ModifyConstant(method = "knockDownwards", constant = @Constant(doubleValue = -0.03999999910593033D))
    public double additionalEntityAttributes$knockDownwards(double original) {
        EntityAttributeInstance waterSpeed = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.WATER_SPEED);
        if (waterSpeed == null) {
            return original;
        } else {
            if (waterSpeed.getBaseValue() != -original) {
                waterSpeed.setBaseValue(-original);
            }
            return -waterSpeed.getValue();
        }
    }

    @ModifyConstant(method = "travel", constant = {@Constant(doubleValue = 0.5D, ordinal = 0), @Constant(doubleValue = 0.5D, ordinal = 1), @Constant(doubleValue = 0.5D, ordinal = 2)})
    private double additionalEntityAttributes$increasedLavaSpeed(double original) {
        EntityAttributeInstance lavaSpeed = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.LAVA_SPEED);
        if (lavaSpeed == null) {
            return original;
        } else {
            if (lavaSpeed.getBaseValue() != original) {
                lavaSpeed.setBaseValue(original);
            }
            return lavaSpeed.getValue();
        }
    }

    @ModifyArg(method = "dropXp()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V"), index = 2)
    protected int additionalEntityAttributes$modifyExperience(int originalXP) {
        if (this.attackingPlayer == null) {
            return originalXP;
        } else {
            return (int) (originalXP * Support.getExperienceMod(this.attackingPlayer));
        }
    }

    @ModifyReturnValue(method = "getAttackDistanceScalingFactor", at = @At("RETURN"))
    private double additionalEntityAttributes$modifyVisibility(double original) {
        LivingEntity thisAsLiving = (LivingEntity)(Object)this;
        return Support.getMobDetectionValue(thisAsLiving, original);
    }
}