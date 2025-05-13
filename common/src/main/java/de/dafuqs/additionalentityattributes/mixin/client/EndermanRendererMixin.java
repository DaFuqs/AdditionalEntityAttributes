package de.dafuqs.additionalentityattributes.mixin.client;

import de.dafuqs.additionalentityattributes.ClientSupport;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = EndermanRenderer.class, priority = 500)
public class EndermanRendererMixin {
    @ModifyVariable(method = "getRenderOffset(Lnet/minecraft/client/renderer/entity/state/EndermanRenderState;)Lnet/minecraft/world/phys/Vec3;", at = @At("LOAD"))
    private double additionalEntityAttributes$applyModelScaleToEndermanOffset(double d, EndermanRenderState renderState) {
        return ClientSupport.getModelWidth(renderState, d / 0.02) * 0.02;
    }
}
