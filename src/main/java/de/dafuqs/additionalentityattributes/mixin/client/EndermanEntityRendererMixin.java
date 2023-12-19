package de.dafuqs.additionalentityattributes.mixin.client;

import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.client.render.entity.EndermanEntityRenderer;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = EndermanEntityRenderer.class, priority = 500)
public class EndermanEntityRendererMixin {
    @ModifyVariable(method = "getPositionOffset(Lnet/minecraft/entity/mob/EndermanEntity;F)Lnet/minecraft/util/math/Vec3d;", at = @At("LOAD"))
    private double additionalEntityAttributes$applyModelScaleToEndermanOffset(double d, EndermanEntity endermanEntity, float f) {
        return Support.getModelWidth(endermanEntity, d / 0.02) * 0.02;
    }
}
