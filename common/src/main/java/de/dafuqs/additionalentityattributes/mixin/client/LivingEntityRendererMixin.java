package de.dafuqs.additionalentityattributes.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import de.dafuqs.additionalentityattributes.ClientSupport;
import de.dafuqs.additionalentityattributes.CustomValueRenderState;
import de.dafuqs.additionalentityattributes.Support;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = LivingEntityRenderer.class, priority = 500)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, S extends LivingEntityRenderState, M extends EntityModel<? super S>> {
    @ModifyArgs(method = "render(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;scale(FFF)V", ordinal = 0))
    private void additionalEntityAttributes$applyModelScales(Args args, @Local(argsOnly = true) LivingEntityRenderState renderState) {
        float x = args.get(0);
        float y = args.get(1);
        float z = args.get(2);
        args.set(0, (float)ClientSupport.getModelWidth(renderState, x));
        args.set(1, (float)ClientSupport.getModelHeight(renderState, y));
        args.set(2, (float)ClientSupport.getModelWidth(renderState, z));
    }

    @ModifyReturnValue(method = "getShadowRadius(Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;)F", at = @At("RETURN"))
    private float additionalEntityAttributes$modifyShadowRadius(float original, LivingEntityRenderState renderState) {
        return (float) ClientSupport.getModelWidth(renderState, original / ((EntityRendererAccessor)this).additionalEntityAttributes$getShadowRadius()) * ((EntityRendererAccessor)this).additionalEntityAttributes$getShadowRadius();
    }

    @Inject(method = "extractRenderState(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;F)V", at = @At("TAIL"))
    private void additionalEntityAttributes$storeModelScales(T entity, S state, float delta, CallbackInfo ci) {
        ((CustomValueRenderState) state).aea$setEntityWidth(Support.getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.WIDTH));
        ((CustomValueRenderState) state).aea$setEntityHeight(Support.getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.HEIGHT));
        ((CustomValueRenderState) state).aea$setModelWidth(Support.getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_WIDTH));
        ((CustomValueRenderState) state).aea$setModelHeight(Support.getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_HEIGHT));
        ((CustomValueRenderState) state).aea$setModelScale(Support.getScaleAttributeModifierValue(entity, AdditionalEntityAttributes.MODEL_SCALE));
    }
}
