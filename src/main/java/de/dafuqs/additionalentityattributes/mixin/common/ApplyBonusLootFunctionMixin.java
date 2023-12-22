package de.dafuqs.additionalentityattributes.mixin.common;

import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ApplyBonusLootFunction.class)
public abstract class ApplyBonusLootFunctionMixin {

	@Shadow
	@Final
	private ApplyBonusLootFunction.Formula formula;

	@Shadow
	@Final
	private RegistryEntry<Enchantment> enchantment;

	@ModifyVariable(method = "process(Lnet/minecraft/item/ItemStack;Lnet/minecraft/loot/context/LootContext;)Lnet/minecraft/item/ItemStack;", at = @At("STORE"), ordinal = 1)
	public int additionalEntityAttributes$applyBonusLoot(int original, ItemStack stack, LootContext context) {
		// the bonus loot of this method gets rerolled x times
		// and the highest result will be returned
		ItemStack itemStack = context.get(LootContextParameters.TOOL);
		Entity entity = context.get(LootContextParameters.THIS_ENTITY);
		if (itemStack != null && entity instanceof LivingEntity livingEntity) {
			int enchantmentLevel = EnchantmentHelper.getLevel(this.enchantment.value(), itemStack);
			if (enchantmentLevel > 0) {
				EntityAttributeInstance attributeInstance = livingEntity.getAttributeInstance(AdditionalEntityAttributes.BONUS_LOOT_COUNT_ROLLS);
				if (attributeInstance != null) {
					int bonusRollCount = (int) attributeInstance.getValue();
					int highestRoll = original;
					for (int i = 0; i < bonusRollCount; i++) {
						int thisRoll = this.formula.getValue(context.getRandom(), stack.getCount(), enchantmentLevel);
						highestRoll = Math.max(highestRoll, thisRoll);
					}
					return highestRoll;
				}
			}
		}
		return original;
	}
	
}
