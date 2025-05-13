package de.dafuqs.additionalentityattributes;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class ClientSupport {
    public static double getModelWidth(LivingEntityRenderState state, double original) {
        return Mth.clamp(original * ((CustomValueRenderState) state).aea$getEntityWidth() * ((CustomValueRenderState) state).aea$getModelScale() * ((CustomValueRenderState) state).aea$getModelWidth(), Support.MIN_SCALE, Support.MAX_SCALE);
    }

    public static double getModelHeight(LivingEntityRenderState state, double original) {
        return Mth.clamp(original * ((CustomValueRenderState) state).aea$getEntityHeight() * ((CustomValueRenderState) state).aea$getModelScale() * ((CustomValueRenderState) state).aea$getModelHeight(), Support.MIN_SCALE, Support.MAX_SCALE);
    }
}
