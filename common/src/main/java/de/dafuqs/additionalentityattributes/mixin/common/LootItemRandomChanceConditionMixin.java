package de.dafuqs.additionalentityattributes.mixin.common;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootItemRandomChanceCondition.class)
public abstract class LootItemRandomChanceConditionMixin {

	@Shadow
	@Final
	private NumberProvider chance;

	@ModifyReturnValue(at = @At("RETURN"), method = "test(Lnet/minecraft/world/level/storage/loot/LootContext;)Z")
	public boolean additionalEntityAttributes$applyBonusLoot(boolean original, LootContext lootContext) {
		// if the result was to not drop a drop before reroll
		if (!original && lootContext.getParamOrNull(LootContextParams.ATTACKING_ENTITY) instanceof LivingEntity livingEntity) {
			AttributeInstance attributeInstance = livingEntity.getAttribute(AdditionalEntityAttributes.BONUS_RARE_LOOT_ROLLS);
			if (attributeInstance != null) {
				return lootContext.getRandom().nextFloat() < this.chance.getFloat(lootContext) * (float) attributeInstance.getValue();
			}
		}
		return original;
	}
	
}
