package de.dafuqs.additionalentityattributes;

import net.fabricmc.api.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.*;

public class AdditionalEntityAttributes implements ModInitializer {
	
	public static final String MOD_ID = "additionalentityattributes";
	
	/*
	 * For testing, default vanilla commands can be used:
	 * /attribute @s additionalentityattributes:critical_bonus_damage modifier add 135e1f1e-755d-4cfe-82da-3648626eeba2 test 1 multiply_base
	 * /attribute @s additionalentityattributes:lava_visibility modifier add 135e1f1e-755d-4cfe-82da-3648626eeba2 test 10 add
	 * /attribute @s additionalentityattributes:lava_speed modifier add 135e1f1e-755d-4cfe-82da-3648626eeba2 test -1 multiply
	 * /attribute @s additionalentityattributes:water_speed modifier add 135e1f1e-755d-4cfe-82da-3648626eeba2 test 0.5 multiply_base
	 * /attribute @s additionalentityattributes:water_visibility modifier add 135e1f1e-755d-4cfe-82da-3648626eeba2 test -0.5 multiply
	 * /attribute @s additionalentityattributes:water_visibility modifier add 135e1f1e-755d-4cfe-82da-3eeee26eeba2 test 300 add
	 */
	
	/**
	 * Controls the bonus damage dealt when dealing critical hits
	 * By default, critical hits deal 1.5 times the damage, so the base value of this attribute is 0.5.
	 * Adding a flat value of 0.5 will bump that value up to make critical hits deal 2x the damage, for example
	 * Multiplying this attribute's value with a modifier value of 0.5 will increase the critical hit damage
	 * by 50%, meaning it will add 50% of the base 50% bonus damage on top, resulting in a critical hit damage
	 * multiplier of 75% (1.75x damage).
	 */
	public static final RegistryEntry<EntityAttribute> CRITICAL_BONUS_DAMAGE = createAttribute("critical_bonus_damage", "critical_bonus_damage", 0.5, -1.0, 1024.0);
	
	/**
	 * Controls the speed of the player when in water
	 * The base value of this attribute is always set dynamically, therefore setting it via a command will have no effect.
	 * For the sake of maneuverability and server performance it is capped at 1.
	 * Stacks with dolphins grace and depth strider, albeit the latter has little felt effect at higher speeds.
	 */
	public static final RegistryEntry<EntityAttribute> WATER_SPEED = createAttribute("water_speed", "water_speed", 0.5, 0, 1);
	
	/**
	 * Controls the vision of the player when in water by adjusting the fog distance
	 */
	public static final RegistryEntry<EntityAttribute> WATER_VISIBILITY = createAttribute("water_visibility", "water_visibility", 96.0, 0, 1024.0);

	/**
	 * Controls the maximum amount of air the entity can have, measured in ticks
	 * This value modifies the natural value of Entity.getMaxAir()
	 */
	public static final RegistryEntry<EntityAttribute> LUNG_CAPACITY = createAttribute("lung_capacity", "lung_capacity", 0.0, -40000, 40000);
	/**
	 * Controls the speed of the player when in lava
	 * The base value of this attribute is always set dynamically, therefore setting it via a command will have no effect.
	 * For the sake of maneuverability and server performance it is capped at 1.
	 * Negative values will make the player even slower with -1.0 resulting in being almost unable to move
	 */
	public static final RegistryEntry<EntityAttribute> LAVA_SPEED = createAttribute("lava_speed", "lava_speed", 0.5, 0, 1);
	
	/**
	 * Controls the vision of the player when in lava by adjusting the fog distance
	 */
	public static final RegistryEntry<EntityAttribute> LAVA_VISIBILITY = createAttribute("lava_visibility", "lava_visibility", 1.0, 0, 1024.0);

	/**
	 * Controls the dig speed of the player
	*/
	public static final RegistryEntry<EntityAttribute> DIG_SPEED = createAttribute("dig_speed", "generic.dig_speed", 0.0D, 0.0D, 2048.0D);
	
	/**
	 * Controls the drops the player gets when using enchantments, such as looting or fortune
	 * (more precise: everything that uses the ApplyBonusLootFunction to increase drops based on an enchantments level)
	 * Each full +1 on this stat will roll the bonus count another time. Highest one is kept.
	 */
	public static final RegistryEntry<EntityAttribute> BONUS_LOOT_COUNT_ROLLS = createAttribute("bonus_loot_count_rolls", "generic.bonus_loot_count_rolls", 0.0D, 0.0D, 128.0);

	/**
	 *
	 */
	public static final RegistryEntry<EntityAttribute> BONUS_RARE_LOOT_ROLLS = createAttribute("bonus_rare_loot_rolls", "generic.bonus_rare_loot_rolls", 0.0D, 0.0D, 128.0);

	/**
	 * Controls the player's width.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale.
	 */
	public static final RegistryEntry<EntityAttribute> WIDTH = createAttribute("width", "generic.width", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's height.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale.
	 */
	public static final RegistryEntry<EntityAttribute> HEIGHT = createAttribute("height", "generic.height", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's hitbox scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale.
	 */
	public static final RegistryEntry<EntityAttribute> HITBOX_SCALE = createAttribute("hitbox_scale", "generic.hitbox_scale", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's hitbox width.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale.
	 */
	public static final RegistryEntry<EntityAttribute> HITBOX_WIDTH = createAttribute("hitbox_width", "generic.hitbox_width", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's hitbox height.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale.
	 */
	public static final RegistryEntry<EntityAttribute> HITBOX_HEIGHT = createAttribute("hitbox_height", "generic.hitbox_height", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's model scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale.
	 */
	public static final RegistryEntry<EntityAttribute> MODEL_SCALE = createAttribute("model_scale", "generic.model_scale", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's model width.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale.
	 */
	public static final RegistryEntry<EntityAttribute> MODEL_WIDTH = createAttribute("model_width", "generic.model_width", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's model height.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale.
	 */
	public static final RegistryEntry<EntityAttribute> MODEL_HEIGHT = createAttribute("model_height", "generic.model_height", 1.0, 0.0625, 16.0);

	/**
	 * Modifies the experience dropped from mining blocks and killing mobs.
	 * The default of 1.0 equals the vanilla drop amount, 0.0 will result in no xp drops altogether.
	 */
	public static final RegistryEntry<EntityAttribute> DROPPED_EXPERIENCE = createAttribute("dropped_experience", "player.dropped_experience", 1.0D, 0.0D, 1024.0D);

	/**
	 * Reduces the amount of magic damage taken.
	 * By default, the player has 0 points, and each point of reduces the damage taken by 1.
	 */
	public static final RegistryEntry<EntityAttribute> MAGIC_PROTECTION = createAttribute("magic_protection", "player.magic_protection", 0.0D, 0.0D, 1024.0D);

	@Override
	public void onInitialize() {

	}

	private static RegistryEntry<EntityAttribute> createAttribute(final String id, final String name, double base, double min, double max) {
		return Registry.registerReference(Registries.ATTRIBUTE, new Identifier(MOD_ID, id), new ClampedEntityAttribute("attribute.name.generic." + MOD_ID + '.' + name, base, min, max).setTracked(true));
	}
}