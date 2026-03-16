package de.dafuqs.additionalentityattributes.mixin.common;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithEnchantedBonusCondition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootItemRandomChanceWithEnchantedBonusCondition.class)
public abstract class LootItemRandomChanceWithEnchantedBonusConditionMixin {
	
	@Shadow
	@Final
	private Holder<Enchantment> enchantment;
	
	@Shadow
	@Final
	private LevelBasedValue enchantedChance;

	@ModifyReturnValue(method = "test(Lnet/minecraft/world/level/storage/loot/LootContext;)Z", at = @At("RETURN"))
	public boolean additionalEntityAttributes$applyBonusLoot(boolean original, LootContext lootContext) {
		// if the result was to not drop a drop before reroll
		if (!original && lootContext.getOptionalParameter(LootContextParams.ATTACKING_ENTITY) instanceof LivingEntity livingEntity) {
			AttributeInstance attributeInstance = livingEntity.getAttribute(AdditionalEntityAttributes.BONUS_RARE_LOOT_ROLLS);
			if (attributeInstance != null) {
				int level = EnchantmentHelper.getEnchantmentLevel(this.enchantment, livingEntity);
				if(level > 0) {
					return lootContext.getRandom().nextFloat() < this.enchantedChance.calculate(level) * (float) attributeInstance.getValue();
				}
			}
		}
		return original;
	}
	
}
