package de.dafuqs.additionalentityattributes.mixin.common;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.passive.DolphinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "getMaxAir", at = @At("HEAD"), cancellable = true)
    public void getMaxAir(CallbackInfoReturnable<Integer> cir) {
        int original = 300;
        if (!((Object) this instanceof LivingEntity livingEntity)) {
            cir.setReturnValue(original);
        } else {
            if (livingEntity.getAttributes() == null) {
                cir.setReturnValue(original);
            } else {
                EntityAttributeInstance maxAir = livingEntity.getAttributeInstance(AdditionalEntityAttributes.MAX_AIR);
                if (maxAir == null) {
                    cir.setReturnValue(original);
                } else {
                    cir.setReturnValue((int) maxAir.getValue());
                }
            }
        }
    }
}
