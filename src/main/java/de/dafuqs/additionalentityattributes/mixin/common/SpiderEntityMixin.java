package de.dafuqs.additionalentityattributes.mixin.common;

import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.SpiderEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = SpiderEntity.class, priority = 500)
public class SpiderEntityMixin {

    @ModifyArg(method = "getVehicleAttachmentPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;<init>(DDD)V"), index = 1)
    private double additionalEntityAttributes$modifySpiderVehicleAttachmentPos(double y) {
        return Support.getHitboxHeight((LivingEntity)(Object)this, y / 0.3125) * 0.3125;
    }
}
