package de.dafuqs.additionalentityattributes.mixin.client;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import de.dafuqs.additionalentityattributes.CustomValueRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@Mixin(LivingEntityRenderState.class)
public class LivingEntityRenderStateMixin implements CustomValueRenderState {
    @Unique private double aea$entityWidth = AdditionalEntityAttributes.WIDTH.value().getDefaultValue();
    @Unique private double aea$entityHeight = AdditionalEntityAttributes.HEIGHT.value().getDefaultValue();
    @Unique private double aea$modelScale = AdditionalEntityAttributes.MODEL_SCALE.value().getDefaultValue();
    @Unique private double aea$modelWidth = AdditionalEntityAttributes.MODEL_WIDTH.value().getDefaultValue();
    @Unique private double aea$modelHeight = AdditionalEntityAttributes.MODEL_HEIGHT.value().getDefaultValue();

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
