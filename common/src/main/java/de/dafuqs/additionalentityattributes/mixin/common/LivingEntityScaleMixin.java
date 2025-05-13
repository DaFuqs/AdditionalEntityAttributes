package de.dafuqs.additionalentityattributes.mixin.common;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = LivingEntity.class, priority = 500)
public abstract class LivingEntityScaleMixin {
    @Unique
    private boolean additionalEntityAttributes$hasInitializedDimensions = false;
    @Unique
    private double additionalEntityAttributes$previousHitboxWidth;
    @Unique
    private double additionalEntityAttributes$previousHitboxHeight;
//
    @Inject(method = "onAttributeUpdated", at = @At(value = "TAIL"))
    private void additionalEntityAttributes$recalculateDimensions(CallbackInfo ci) {
        LivingEntity thisAsLiving = (LivingEntity)(Object)this;
        boolean updateScales = false;

        double hitboxWidth = Support.getHitboxWidth(thisAsLiving, 1.0F);
        double hitboxHeight = Support.getHitboxHeight(thisAsLiving, 1.0F);

        if (!additionalEntityAttributes$hasInitializedDimensions) {
            additionalEntityAttributes$previousHitboxWidth = hitboxWidth;
            additionalEntityAttributes$previousHitboxHeight = hitboxHeight;
            additionalEntityAttributes$hasInitializedDimensions = true;
            thisAsLiving.refreshDimensions();
            return;
        }

        if (hitboxWidth != additionalEntityAttributes$previousHitboxWidth) {
            this.additionalEntityAttributes$previousHitboxWidth = hitboxWidth;
            updateScales = true;
        }
        if (hitboxHeight != additionalEntityAttributes$previousHitboxHeight) {
            this.additionalEntityAttributes$previousHitboxHeight = hitboxHeight;
            updateScales = true;
        }

        if (updateScales) {
            thisAsLiving.refreshDimensions();
        }
    }

    @ModifyReturnValue(method = "getDimensions", at = @At("RETURN"))
    private EntityDimensions additionalEntityAttributes$modifyDimensions(EntityDimensions original) {
        return original.scale((float) Support.getHitboxWidth((LivingEntity)(Object)this, 1.0F), (float)Support.getHitboxHeight((LivingEntity)(Object)this, 1.0F));
    }

    @ModifyArg(method = "getPassengerRidingPosition", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getPassengerAttachmentPoint(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/EntityDimensions;F)Lnet/minecraft/world/phys/Vec3;"), index = 2)
    private float additionalEntityAttributes$modifyPassengerRidingPos(float value) {
        return (float)Support.getHitboxHeight((LivingEntity)(Object)this, value);
    }
}
