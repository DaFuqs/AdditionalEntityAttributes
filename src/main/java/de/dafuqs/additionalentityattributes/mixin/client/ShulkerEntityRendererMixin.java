package de.dafuqs.additionalentityattributes.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.client.render.entity.ShulkerEntityRenderer;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = ShulkerEntityRenderer.class, priority = 500)
public class ShulkerEntityRendererMixin {
    @ModifyReturnValue(method = "getPositionOffset(Lnet/minecraft/entity/mob/ShulkerEntity;F)Lnet/minecraft/util/math/Vec3d;", at = @At("RETURN"))
    private Vec3d additionalEntityAttributes$applyModelScaleToShulkerOffset(Vec3d original, ShulkerEntity shulkerEntity, float f) {
        return original.multiply(Support.getModelWidth(shulkerEntity, 1.0F), Support.getModelHeight(shulkerEntity, 1.0F), Support.getModelWidth(shulkerEntity, 1.0F));
    }
}
