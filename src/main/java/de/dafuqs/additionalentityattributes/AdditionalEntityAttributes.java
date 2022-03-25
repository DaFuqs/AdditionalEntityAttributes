package de.dafuqs.additionalentityattributes;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AdditionalEntityAttributes implements ModInitializer {
	
	public static final String MOD_ID = "additionalentityattributes";
	
	/**
	 * for testing, default vanilla commands can be used:
	 * /attribute Player662 additionalentityattributes:critical_damage_multiplier base get
	 * /attribute Player583 additionalentityattributes:lava_visibility base set 10
	 * /attribute Player583 additionalentityattributes:lava_speed base set 0.5
	 */
	
	/**
	 * Increases the dealt damage when dealing critical hits
	 * By default, critical hits deal 1.5 times the damage
	 * Using a multiplier of 0.5 will bump that value up to 2x the damage, for example
	 */
	public static final EntityAttribute CRITICAL_DAMAGE_MULTIPLIER = createAttribute("critical_damage_multiplier", 0.0, 0.0, 1024.0);
	
	/**
	 * Increases or decreases (negative values) the speed of the player when in water
	 * The max value will make players zoom through the water much faster than an elytra ever could
	 * For the sake of maneuverability and server performance it is capped at 1.0
	 */
	public static final EntityAttribute WATER_SPEED = createAttribute("water_speed", 0.0, 0.0, 1.0);
	
	/**
	 * Increases or decreases (negative values) the vision of the player when in water by adjusting the fog distance
	 * A value of -0.5 would make the screen be completely fog, unable to see anything, values > 2 look pretty bad
	 */
	public static final EntityAttribute WATER_VISIBILITY = createAttribute("water_visibility", 0.0, -0.48, 2.0);
	
	/**
	 * Increases or decreases (negative values) the speed of the player when in lava
	 * Values approaching the max value will feel very sluggish. Best to keep it under 0.4
	 * Negative values will make the player even slower with -1.0 resulting in being almost unable to move
	 */
	public static final EntityAttribute LAVA_SPEED = createAttribute("lava_speed", 0.0, -1.0, 0.49);
	
	/**
	 * Increases or decreases (negative values) the vision of the player when in lava by adjusting the fog distance
	 */
	public static final EntityAttribute LAVA_VISIBILITY = createAttribute("lava_visibility", 0.0, -1024.0, 1024.0);
	
	@Override
	public void onInitialize() {
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "critical_damage_multiplier"), CRITICAL_DAMAGE_MULTIPLIER);
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "water_speed"), WATER_SPEED);
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "water_visibility"), WATER_VISIBILITY);
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "lava_speed"), LAVA_SPEED);
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "lava_visibility"), LAVA_VISIBILITY);
	}
	
	private static EntityAttribute createAttribute(final String name, double base, double min, double max) {
		return new ClampedEntityAttribute("attribute.name.generic." + MOD_ID + '.' + name, base, min, max).setTracked(true);
	}
	
}