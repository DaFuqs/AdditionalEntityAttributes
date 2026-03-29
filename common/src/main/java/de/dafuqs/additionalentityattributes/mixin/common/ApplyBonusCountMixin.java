package de.dafuqs.additionalentityattributes.mixin.common;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.item.ItemInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;

@Mixin(ApplyBonusCount.class)
public abstract class ApplyBonusCountMixin {

	@Shadow
	@Final
	private ApplyBonusCount.Formula formula;

	@Shadow
	@Final
	private Holder<Enchantment> enchantment;

	@ModifyVariable(method = "run", at = @At("STORE"), name = "newCount")
	public int additionalEntityAttributes$applyBonusLoot(int original, ItemStack stack, LootContext context) {
		// the bonus loot of this method gets rerolled x times
		// and the highest result will be returned
		ItemInstance itemStack = context.getOptionalParameter(LootContextParams.TOOL);
		Entity entity = context.getOptionalParameter(LootContextParams.THIS_ENTITY);
		if (itemStack != null && entity instanceof LivingEntity livingEntity) {
			int enchantmentLevel = EnchantmentHelper.getItemEnchantmentLevel(this.enchantment, itemStack);
			if (enchantmentLevel > 0) {
				AttributeInstance attributeInstance = livingEntity.getAttribute(AdditionalEntityAttributes.BONUS_LOOT_COUNT_ROLLS);
				if (attributeInstance != null) {
					int bonusRollCount = (int) attributeInstance.getValue();
					int highestRoll = original;
					for (int i = 0; i < bonusRollCount; i++) {
						int thisRoll = this.formula.calculateNewCount(context.getRandom(), stack.getCount(), enchantmentLevel);
						highestRoll = Math.max(highestRoll, thisRoll);
					}
					return highestRoll;
				}
			}
		}
		return original;
	}
	
}
