package de.dafuqs.additionalentityattributes.mixin.client;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

// We run at a lower priority because we clamp values.
// This is so Pehkui and other mods that may touch this may run without limits.
@Mixin(value = EntityRenderer.class, priority = 500)
public interface EntityRendererAccessor {
    @Accessor("shadowRadius")
    float additionalEntityAttributes$getShadowRadius();
}
