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
	 * /attribute Player662 additionalentityattributes:critical_bonuse_damage base get
	 * /attribute Player583 additionalentityattributes:lava_visibility base set 10
	 * /attribute Player583 additionalentityattributes:lava_speed base set 0.5
	 * /attribute Player556 additionalentityattributes:water_speed base set 0.5
	 */
	
	/**
	 * Controls the bonus damage dealt when dealing critical hits
	 * By default, critical hits deal 1.5 times the damage, so the base value of this attribute is 0.5.
	 * Adding a flat value of 0.5 will bump that value up to make critical hits deal 2x the damage, for example
	 * Multiplying this attribute's value with a modifier value of 0.5 will increase the critical hit damage
	 * by 50%, meaning it will add 50% of the base 50% bonus damage on top, resulting in a critical hit damage
	 * multiplier of 75% (1.75x damage).
	 */
	public static final EntityAttribute CRITICAL_BONUS_DAMAGE = createAttribute("critical_bonus_damage", 0.5, -1.0, 1024.0);
	
	/**
	 * Controls the speed of the player when in water
	 * The base value of this attribute is always set dynamically, therefore setting it via a command will have no effect.
	 * For the sake of maneuverability and server performance it is capped at 1.
	 * Stacks with dolphins grace and depth strider, albeit the latter has little felt effect at higher speeds.
	 */
	public static final EntityAttribute WATER_SPEED = createAttribute("water_speed", 0.5, 0, 1);
	
	/**
	 * Controls the vision of the player when in water by adjusting the fog distance
	 */
	public static final EntityAttribute WATER_VISIBILITY = createAttribute("water_visibility", 96.0, 0, 1024.0);
	
	/**
	 * Controls the speed of the player when in lava
	 * The base value of this attribute is always set dynamically, therefore setting it via a command will have no effect.
	 * For the sake of maneuverability and server performance it is capped at 1.
	 * Negative values will make the player even slower with -1.0 resulting in being almost unable to move
	 */
	public static final EntityAttribute LAVA_SPEED = createAttribute("lava_speed", 0.5, 0, 1);
	
	/**
	 * Controls the vision of the player when in lava by adjusting the fog distance
	 */
	public static final EntityAttribute LAVA_VISIBILITY = createAttribute("lava_visibility", 1.0, 0, 1024.0);
	
	@Override
	public void onInitialize() {
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "critical_bonus_damage"), CRITICAL_BONUS_DAMAGE);
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "water_speed"), WATER_SPEED);
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "water_visibility"), WATER_VISIBILITY);
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "lava_speed"), LAVA_SPEED);
		Registry.register(Registry.ATTRIBUTE, new Identifier(MOD_ID, "lava_visibility"), LAVA_VISIBILITY);
	}
	
	private static EntityAttribute createAttribute(final String name, double base, double min, double max) {
		return new ClampedEntityAttribute("attribute.name.generic." + MOD_ID + '.' + name, base, min, max).setTracked(true);
	}
	
}