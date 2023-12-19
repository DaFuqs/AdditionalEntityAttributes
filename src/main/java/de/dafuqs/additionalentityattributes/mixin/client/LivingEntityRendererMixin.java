package de.dafuqs.additionalentityattributes.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = LivingEntityRenderer.class, priority = 500)
public abstract class LivingEntityRendererMixin {
    @ModifyArgs(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;scale(FFF)V", ordinal = 0))
    private void additionalEntityAttributes$applyModelScales(Args args, LivingEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        float x = args.get(0);
        float y = args.get(1);
        float z = args.get(2);
        args.set(0, (float)Support.getModelWidth(livingEntity, x));
        args.set(1, (float)Support.getModelHeight(livingEntity, y));
        args.set(2, (float)Support.getModelWidth(livingEntity, z));
    }

    @ModifyReturnValue(method = "getShadowRadius(Lnet/minecraft/entity/LivingEntity;)F", at = @At("RETURN"))
    private float additionalEntityAttributes$modifyShadowRadius(float original, LivingEntity livingEntity) {
        return (float)Support.getModelWidth(livingEntity, original / ((EntityRendererAccessor)this).additionalEntityAttributes$getShadowRadius()) * ((EntityRendererAccessor)this).additionalEntityAttributes$getShadowRadius();
    }
}
