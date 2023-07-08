package de.dafuqs.additionalentityattributes.mixin.common;

import com.llamalad7.mixinextras.injector.*;
import de.dafuqs.additionalentityattributes.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @ModifyReturnValue(method = "getMaxAir", at = @At("RETURN"))
    public int getMaxAir(int original) {
        if (!((Object) this instanceof LivingEntity livingEntity)) {
            return original;
        } else {
            if (livingEntity.getAttributes() == null) {
                return original;
            }
            EntityAttributeInstance maxAir = livingEntity.getAttributeInstance(AdditionalEntityAttributes.MAX_AIR);
            if (maxAir != null) {
                return MathHelper.clamp(original + (int) maxAir.getValue(), 1, Integer.MAX_VALUE);
            }
        }
        return original;
    }
}
