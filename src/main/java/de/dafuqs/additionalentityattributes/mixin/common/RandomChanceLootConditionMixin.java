package de.dafuqs.additionalentityattributes.mixin.common;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RandomChanceLootCondition.class)
public abstract class RandomChanceLootConditionMixin {
	
	@Shadow
	@Final
	float chance;
	
	@Inject(at = @At("RETURN"), method = "test(Lnet/minecraft/loot/context/LootContext;)Z", cancellable = true)
	public void additionalEntityAttributes$applyBonusLoot(LootContext lootContext, CallbackInfoReturnable<Boolean> cir) {
		// if the result was to not drop a drop before reroll
		// gets more probable with each additional level of Clovers Favor
		if (!cir.getReturnValue() && this.chance < 1.0F && lootContext.get(LootContextParameters.KILLER_ENTITY) instanceof LivingEntity livingEntity) {
			EntityAttributeInstance attributeInstance = livingEntity.getAttributeInstance(AdditionalEntityAttributes.BONUS_RARE_LOOT_ROLLS);
			if (attributeInstance != null) {
				cir.setReturnValue(lootContext.getRandom().nextFloat() < this.chance * (float) attributeInstance.getValue());
			}
		}
	}
	
}
