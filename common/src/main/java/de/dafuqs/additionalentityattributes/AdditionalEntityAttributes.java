package de.dafuqs.additionalentityattributes;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class AdditionalEntityAttributes {
	
	public static final String MOD_ID = "additionalentityattributes";

    /*
     * For testing, default vanilla commands can be used.
     * Each attribute contains a test command beneath its description.
     */


	/**
	 * Controls the bonus damage dealt when dealing critical hits
	 * By default, critical hits deal 1.5 times the damage, so the base value of this attribute is 0.5.
	 * Adding a flat value of 0.5 will bump that value up to make critical hits deal 2x the damage, for example,
	 * Multiplying this attribute's value with a modifier value of 0.5 will increase the critical hit damage
	 * by 50%, meaning it will add 50% of the base 50% bonus damage on top, resulting in a critical hit damage
	 * multiplier of 75% (1.75x damage).
     * <br>
     * /attribute @s additionalentityattributes:critical_bonus_damage modifier add additionalentityattributes:critical_bonus_damage 1 add_multiplied_base
     * /attribute @s additionalentityattributes:critical_bonus_damage modifier remove additionalentityattributes:critical_bonus_damage
     */
	public static final Holder<Attribute> CRITICAL_BONUS_DAMAGE = register("critical_bonus_damage", 0.5, -1.0, 1024.0);

	/**
	 * Controls the speed of the player when in water
	 * The base value of this attribute is always set dynamically, therefore, setting it via a command will have no effect.
	 * For the sake of maneuverability and server performance, it is capped at 1.
	 * Stacks with dolphins grace and depth strider, albeit the latter has little felt effect at higher speeds.
     * <br>
     * /attribute @s additionalentityattributes:water_speed modifier add additionalentityattributes:water_speed 0.5 add_multiplied_base
     * /attribute @s additionalentityattributes:water_speed modifier remove additionalentityattributes:water_speed
     */
	public static final Holder<Attribute> WATER_SPEED = register("water_speed", 0.5, 0, 1);

	/**
	 * Controls the vision of the player when in water by adjusting the fog distance
	 */
	public static final Holder<Attribute> WATER_VISIBILITY = register("water_visibility", 96.0, 0, 1024.0);

	/**
	 * Controls the speed of the player when in lava
	 * The base value of this attribute is always set dynamically, therefore, setting it via a command will have no effect.
	 * For the sake of maneuverability and server performance, it is capped at 1.
	 * Negative values will make the player even slower with -1.0 resulting in being almost unable to move
     * <br>
     * /attribute @s additionalentityattributes:lava_speed modifier add additionalentityattributes:lava_speed -1 add_multiplied_base
     * /attribute @s additionalentityattributes:lava_speed modifier remove additionalentityattributes:lava_speed
     */
	public static final Holder<Attribute> LAVA_SPEED = register("lava_speed", 0.5, 0, 1);

	/**
	 * Controls the player's width.
	 * The default of 1.0 is an unmodified scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale attribute.
     * <br>
     * /attribute @s additionalentityattributes:width modifier add additionalentityattributes:width 0.25 add_multiplied_base
     * /attribute @s additionalentityattributes:width modifier remove additionalentityattributes:width
     */
	public static final Holder<Attribute> WIDTH = register("width", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's height.
	 * The default of 1.0 is an unmodified scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale attribute.
     * <br>
     * /attribute @s additionalentityattributes:height modifier add additionalentityattributes:height 0.75 add_multiplied_base
     * /attribute @s additionalentityattributes:height modifier remove additionalentityattributes:height
     */
	public static final Holder<Attribute> HEIGHT = register("height", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's hitbox scale.
	 * The default of 1.0 is an unmodified scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale attribute.
     * <br>
     * /attribute @s additionalentityattributes:hitbox_scale modifier add additionalentityattributes:hitbox_scale 1 add_multiplied_base
     * /attribute @s additionalentityattributes:hitbox_scale modifier remove additionalentityattributes:hitbox_scale
     */
	public static final Holder<Attribute> HITBOX_SCALE = register("hitbox_scale", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's hitbox width.
	 * The default of 1.0 is an unmodified scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale attribute.
     * <br>
     * /attribute @s additionalentityattributes:hitbox_width modifier add additionalentityattributes:hitbox_width 0.25 add_multiplied_base
     * /attribute @s additionalentityattributes:hitbox_width modifier remove additionalentityattributes:hitbox_width
     */
	public static final Holder<Attribute> HITBOX_WIDTH = register("hitbox_width", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's hitbox height.
	 * The default of 1.0 is an unmodified scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale attribute.
     * <br>
     * /attribute @s additionalentityattributes:hitbox_height modifier add additionalentityattributes:hitbox_height 0.75 add_multiplied_base
     * /attribute @s additionalentityattributes:hitbox_height modifier remove additionalentityattributes:hitbox_height
     */
	public static final Holder<Attribute> HITBOX_HEIGHT = register("hitbox_height", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's model scale.
	 * The default of 1.0 is an unmodified scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale attribute.
     * <br>
     * /attribute @s additionalentityattributes:model_scale modifier add additionalentityattributes:model_scale test 0.25 add_multiplied_base
     * /attribute @s additionalentityattributes:model_scale modifier remove additionalentityattributes:model_scale
     */
	public static final Holder<Attribute> MODEL_SCALE = register("model_scale", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's model width.
	 * The default of 1.0 is an unmodified scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale attribute.
     * <br>
     * /attribute @s additionalentityattributes:model_width modifier add additionalentityattributes:model_width test 0.25 add_multiplied_base
     * /attribute @s additionalentityattributes:model_width modifier remove additionalentityattributes:model_width
     */
	public static final Holder<Attribute> MODEL_WIDTH = register("model_width", 1.0, 0.0625, 16.0);

	/**
	 * Controls the player's model height.
	 * The default of 1.0 is an unmodified scale.
	 * Stacks with vanilla's scale modifier and other AEA scale modifiers, but cannot go past
	 * the min or max value of the original scale attribute.
     * <br>
     * /attribute @s additionalentityattributes:model_height modifier add additionalentityattributes:model_height test 0.75 add_multiplied_base
     * /attribute @s additionalentityattributes:model_height modifier remove additionalentityattributes:model_height
     */
	public static final Holder<Attribute> MODEL_HEIGHT = register("model_height", 1.0, 0.0625, 16.0);

	/**
	 * Controls the range that the player can be detected by hostile mobs.
	 * By default, this starts at 1.0, but may be different depending on a few factors.
     * <br>
     * /attribute @s additionalentityattributes:mob_detection_range modifier add additionalentityattributes:mob_detection_range test 6 add
     * /attribute @s additionalentityattributes:mob_detection_range modifier remove additionalentityattributes:mob_detection_range
     */
	public static final Holder<Attribute> MOB_DETECTION_RANGE = register("mob_detection_range", 0.0, 0.0, 1024.0);

	/**
	 * Controls the vision of the player when in lava by adjusting the fog distance
     * <br>
     * /attribute @s additionalentityattributes:lava_visibility modifier add additionalentityattributes:lava_visibility test 10 add
     * /attribute @s additionalentityattributes:lava_visibility modifier remove additionalentityattributes:lava_visibility
	 */
	public static final Holder<Attribute> LAVA_VISIBILITY = register("lava_visibility", 1.0, 0, 1024.0);

	/**
	 * Controls the drops the player gets when using enchantments, such as looting or fortune
	 * (more precise: everything that uses the ApplyBonusLootFunction to increase drops based on an enchantment's level)
	 * Each full +1 on this stat will roll the bonus count another time. The highest one is kept.
     * <br>
     * /attribute @s additionalentityattributes:bonus_loot_count_rolls modifier add additionalentityattributes:bonus_loot_count_rolls test 1 add
     * /attribute @s additionalentityattributes:bonus_loot_count_rolls modifier remove additionalentityattributes:bonus_loot_count_rolls
     */
	public static final Holder<Attribute> BONUS_LOOT_COUNT_ROLLS = register("bonus_loot_count_rolls", 0.0D, 0.0D, 128.0);

	/**
	 * If a loot table that does not have a 100 % chance (RandomChanceLootCondition, RandomChanceWithLootingLootCondition),
	 * increases the chance to get that drop up to the point that drop is guaranteed. Will not increase drop count.
	 * Example: Zombies have a ~1% chance to drop an Iron Ingot. BONUS_RARE_LOOT_ROLLS increases that 1% chance (but will not make it drop 2 ingots)
	 * <br>
	 * A value of 1.0 will result in one additional roll with the original chance. So if a drop has a 10% chance:
	 * - `BONUS_RARE_LOOT_ROLLS = 1.0` will roll another time with 10% chance
	 * - `BONUS_RARE_LOOT_ROLLS = 2.0` will result in another roll with 20% chance
	 * - `BONUS_RARE_LOOT_ROLLS = 0.5` will result in another roll with 5% chance
     * <br>
     * /attribute @s additionalentityattributes:bonus_rare_loot_rolls modifier add additionalentityattributes:bonus_rare_loot_rolls test 2 add
     * /attribute @s additionalentityattributes:bonus_rare_loot_rolls modifier remove additionalentityattributes:bonus_rare_loot_rolls
     */
	public static final Holder<Attribute> BONUS_RARE_LOOT_ROLLS = register("bonus_rare_loot_rolls", 0.0D, 0.0D, 128.0);

	/**
	 * Modifies the experience dropped from mining blocks and killing mobs.
	 * The default of 1.0 equals the vanilla drop amount, 0.0 will result in no xp drops altogether.
     * <br>
     * /attribute @s additionalentityattributes:bonus_rare_loot_rolls modifier add additionalentityattributes:bonus_rare_loot_rolls test -1 add_multiplied_total
     * /attribute @s additionalentityattributes:bonus_rare_loot_rolls modifier remove additionalentityattributes:bonus_rare_loot_rolls
     */
	public static final Holder<Attribute> DROPPED_EXPERIENCE = register("dropped_experience", 1.0D, 0.0D, 1024.0D);

	/**
	 * Reduces the amount of magic damage taken.
	 * By default, the player has 0 points, and each point reduces the damage taken by 1.
     * <br>
     * /attribute @s additionalentityattributes:magic_protection modifier add additionalentityattributes:magic_protection test 20 add
     * /attribute @s additionalentityattributes:magic_protection modifier remove additionalentityattributes:magic_protection
     */
	public static final Holder<Attribute> MAGIC_PROTECTION = register("magic_protection", 0.0D, 0.0D, 1024.0D);

	/**
	 * Increases the range to collect items and experience in blocks
	 * By default, vanilla uses a hitbox that is one block bigger than the players hitbox (or their mount)
     * <br>
     * /attribute @s additionalentityattributes:collection_range modifier add additionalentityattributes:collection_range test -4 add
     * /attribute @s additionalentityattributes:collection_range modifier remove additionalentityattributes:collection_range
     */
	public static final Holder<Attribute> COLLECTION_RANGE = register("collection_range", 0.0D, -64.0D, 64.0D);


    private static Holder<Attribute> register(final String name, double base, double min, double max) {
        Attribute attribute = new RangedAttribute("attribute.name." + MOD_ID + '.' + name, base, min, max).setSyncable(true);
        return Registry.registerForHolder(BuiltInRegistries.ATTRIBUTE, Identifier.fromNamespaceAndPath(MOD_ID, name), attribute);
    }

    public static void init() {

    }
	
}