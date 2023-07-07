package de.dafuqs.additionalentityattributes.mixin.common;

import com.llamalad7.mixinextras.injector.*;
import de.dafuqs.additionalentityattributes.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @ModifyReturnValue(method = "getMaxAir", at = @At("RETURN"))
    public int getMaxAir(int original) {
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
