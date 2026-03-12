package de.dafuqs.additionalentityattributes.mixin.fabric;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.additionalentityattributes.AdditionalEntityAttributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Player.class)
public abstract class PlayerMixin {

	@Unique
    @ModifyReturnValue(method = "createAttributes", require = 1, allow = 1, at = @At("RETURN"))
	private static AttributeSupplier.Builder additionalEntityAttributes$addPlayerAttributes(AttributeSupplier.Builder original) {
		return original
			.add(AdditionalEntityAttributes.WATER_VISIBILITY)
			.add(AdditionalEntityAttributes.LAVA_VISIBILITY)
			.add(AdditionalEntityAttributes.CRITICAL_BONUS_DAMAGE)
			.add(AdditionalEntityAttributes.BONUS_LOOT_COUNT_ROLLS)
			.add(AdditionalEntityAttributes.BONUS_RARE_LOOT_ROLLS)
			.add(AdditionalEntityAttributes.DROPPED_EXPERIENCE)
			.add(AdditionalEntityAttributes.COLLECTION_RANGE);
	}
	
}