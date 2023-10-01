# AdditionalEntityAttributes

Adds a list of new attributes for entities and players.
These attributes allow mod compatibility and serve as an API for mods that make use of them.

## Current Attributes
- WATER_SPEED: Controls the speed of the player when in water.
- WATER_VISIBILITY: Controls the player's visibility in water by adjusting the fog distance.
- LAVA_SPEED: Controls the player's speed when in lava.
- LAVA_VISIBILITY: Controls the player's visibility in lava by adjusting the fog distance.
- CRITICAL_BONUS_DAMAGE: Controls the bonus damage dealt when critical hits are made.
- DIG_SPEED: Controls the player's digging speed
- LUNG_CAPACITY: Extra air when underwater / suffocating
- BONUS_LOOT_COUNT_ROLLS: Controls the number of drops the player receives when using enchantments such as Loot and Luck.
- BONUS_RARE_LOOT_ROLLS: The number of times the chance-based loot table is rolled.
- DROPPED_EXPERIENCE: Changes the amount of experience dropped from mining blocks and killing mobs.
- MAGIC_PROTECTION: Reduces the amount of magic damage taken.

### Players
You will probably not need to install this library yourself, as most mods that use it will ship with it. If you want to use the above attributes via commands or anything else, feel free to do so, of course.

### @Developers
Feel free to JIJ.
You can fetch all builds from the Modrinth Maven:

```
repositories {
	exclusiveContent {
		forRepository {
			maven {
				name = "Modrinth"
				url = "https://api.modrinth.com/maven"
			}
		}
		filter {
			includeGroup "maven.modrinth"
		}
	}
}
```

```
dependencies {
	modImplementation include("maven.modrinth:AdditionalEntityAttributes:<version>")
}
```
