package de.dafuqs.additionalentityattributes.mixin.common;


import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
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

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getScale()F"))
    private void additionalEntityAttributes$recalculateDimensions(CallbackInfo ci) {
        LivingEntity thisAsLiving = (LivingEntity)(Object)this;
        boolean updateScales = false;

        double hitboxWidth = Support.getHitboxWidth(thisAsLiving, 1.0F);
        double hitboxHeight = Support.getHitboxHeight(thisAsLiving, 1.0F);

        if (!additionalEntityAttributes$hasInitializedDimensions) {
            additionalEntityAttributes$previousHitboxWidth = hitboxWidth;
            additionalEntityAttributes$previousHitboxHeight = hitboxHeight;
            additionalEntityAttributes$hasInitializedDimensions = true;
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
            thisAsLiving.calculateDimensions();
        }
    }

    @ModifyVariable(method = "modifyAppliedDamage", at = @At(value = "LOAD", ordinal = 4), argsOnly = true)
    private float additionalEntityAttributes$reduceMagicDamage(float damage, DamageSource source) {
        EntityAttributeInstance magicProt = ((LivingEntity) (Object) this).getAttributeInstance(AdditionalEntityAttributes.MAGIC_PROTECTION);

        if (magicProt == null) {
            return damage;
        }

        if (source.isIn(DamageTypeTags.WITCH_RESISTANT_TO) && magicProt.getValue() > 0) {
            damage = (float) Math.max(damage - magicProt.getValue(), 0);
        }
        return damage;
    }

    @ModifyReturnValue(method = "getDimensions", at = @At("RETURN"))
    private EntityDimensions additionalEntityAttributes$modifyDimensions(EntityDimensions original) {
        return original.scaled((float) Support.getHitboxWidth((LivingEntity)(Object)this, 1.0F), (float)Support.getHitboxHeight((LivingEntity)(Object)this, 1.0F));
    }

    @ModifyArg(method = "getPassengerRidingPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getPassengerAttachmentPos(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/EntityDimensions;F)Lnet/minecraft/util/math/Vec3d;"), index = 2)
    private float additionalEntityAttributes$modifyPassengerRidingPos(float value) {
        return (float)Support.getHitboxHeight((LivingEntity)(Object)this, value);
    }
}
