package de.dafuqs.additionalentityattributes.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.ClientSupport;
import net.minecraft.client.renderer.entity.ShulkerRenderer;
import net.minecraft.client.renderer.entity.state.ShulkerRenderState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = ShulkerRenderer.class, priority = 500)
public class ShulkerRendererMixin {
    @ModifyReturnValue(method = "getRenderOffset(Lnet/minecraft/client/renderer/entity/state/ShulkerRenderState;)Lnet/minecraft/world/phys/Vec3;", at = @At("RETURN"))
    private Vec3 additionalEntityAttributes$applyModelScaleToShulkerOffset(Vec3 original, ShulkerRenderState renderState) {
        return original.multiply(ClientSupport.getModelWidth(renderState, 1.0F), ClientSupport.getModelHeight(renderState, 1.0F), ClientSupport.getModelWidth(renderState, 1.0F));
    }
}
