package de.dafuqs.additionalentityattributes.mixin.common;

import com.llamalad7.mixinextras.sugar.Local;
import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(TargetPredicate.class)
public class TargetPredicateMixin {
    @ModifyArg(method = "test", at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(DD)D"), index = 1)
    private double additionalEntityAttributes$setMobDetectionMinimum(double original, @Local(ordinal = 1) LivingEntity targetEntity) {
        return Support.getMobDetectionValue(targetEntity, original);
    }
}
