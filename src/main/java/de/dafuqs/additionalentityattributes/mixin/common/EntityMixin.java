package de.dafuqs.additionalentityattributes.mixin.common;

import de.dafuqs.additionalentityattributes.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

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
