package de.dafuqs.additionalentityattributes.mixin.client;

import de.dafuqs.additionalentityattributes.CustomValueRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements CustomValueRenderState {
    @Unique private double aea$entityWidth;
    @Unique private double aea$entityHeight;
    @Unique private double aea$modelScale;
    @Unique private double aea$modelWidth;
    @Unique private double aea$modelHeight;

    @Override
    public double aea$getEntityWidth() {
        return aea$entityWidth;
    }

    @Override
    public void aea$setEntityWidth(double width) {
        aea$entityWidth = width;
    }

    @Override
    public double aea$getModelWidth() {
        return aea$modelWidth;
    }

    @Override
    public void aea$setModelWidth(double width) {
        aea$modelWidth = width;
    }

    @Override
    public double aea$getModelScale() {
        return aea$modelScale;
    }

    @Override
    public void aea$setModelScale(double scale) {
        aea$modelScale = scale;
    }

    @Override
    public double aea$getEntityHeight() {
        return aea$entityHeight;
    }

    @Override
    public void aea$setEntityHeight(double height) {
        aea$entityHeight = height;
    }

    @Override
    public double aea$getModelHeight() {
        return aea$modelHeight;
    }

    @Override
    public void aea$setModelHeight(double height) {
        aea$modelHeight = height;
    }
}
