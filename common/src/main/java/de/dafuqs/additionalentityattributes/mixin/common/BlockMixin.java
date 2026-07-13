package de.dafuqs.additionalentityattributes.mixin.common;

import java.lang.ref.WeakReference;

import de.dafuqs.additionalentityattributes.Support;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(Block.class)
public abstract class BlockMixin {
	
	@Unique
	private WeakReference<Player> additionalEntityAttributes$breakingPlayer;

	@Inject(method = "playerDestroy", at = @At("HEAD"))
	public void additionalEntityAttributes$saveBreakingPlayer(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack, CallbackInfo callbackInfo) {
		additionalEntityAttributes$breakingPlayer = new WeakReference<>(player);
	}
	
	@ModifyArg(method = "popExperience", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ExperienceOrb;award(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;I)V"))
	private int additionalEntityAttributes$modifyExperience(int originalXP) {
		if(additionalEntityAttributes$breakingPlayer != null && additionalEntityAttributes$breakingPlayer.get() != null) {
			return (int) (originalXP * Support.getExperienceMod(additionalEntityAttributes$breakingPlayer.get()));
		}
		return originalXP;
	}


}
