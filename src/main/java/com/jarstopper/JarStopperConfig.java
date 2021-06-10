package com.jarstopper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("example")
public interface JarStopperConfig extends Config
{
//	@ConfigItem(
//		keyName = "greeting",
//		name = "Welcome Greeting",
//		description = "The message to show to the user when they login"
//	)
//	default String greeting()
//	{
//		return "Hello";
//	}

	@ConfigItem(
		keyName = "swapBabyJars",
		name = "Baby Implings",
		description = "Swap baby impling jars when you have a beginner or easy clue"
	)
	default boolean swapBabyJars() { return false; }

	@ConfigItem(
			keyName = "swapYoungJars",
			name = "Young Implings",
			description = "Swap young impling jars when you have a beginner or easy clue"
	)
	default boolean swapYoungJars() { return false; }

	@ConfigItem(
			keyName = "swapGourmetJars",
			name = "Gourmet Implings",
			description = "Swap gourmet impling jars when you have an easy clue"
	)
	default boolean swapGourmetJars() { return false; }

	@ConfigItem(
			keyName = "swapEarthJars",
			name = "Earth Implings",
			description = "Swap earth impling jars when you have a medium clue"
	)
	default boolean swapEarthJars() { return false; }

	@ConfigItem(
			keyName = "swapEssenceJars",
			name = "Essence Implings",
			description = "Swap essence impling jars when you have a medium clue"
	)
	default boolean swapEssenceJars() { return false; }

	@ConfigItem(
			keyName = "swapEclecticJars",
			name = "Eclectic Implings",
			description = "Swap eclectic impling jars when you have a medium clue"
	)
	default boolean swapEclecticJars() { return true; }

	@ConfigItem(
			keyName = "swapNatureJars",
			name = "Nature Implings",
			description = "Swap nature impling jars when you have a hard clue"
	)
	default boolean swapNatureJars() { return false; }

	@ConfigItem(
			keyName = "swapMagpieJars",
			name = "Magpie Implings",
			description = "Swap magpie impling jars when you have a hard clue"
	)
	default boolean swapMagpieJars() { return false; }

	@ConfigItem(
			keyName = "swapNinjaJars",
			name = "Ninja Implings",
			description = "Swap ninja impling jars when you have a hard clue"
	)
	default boolean swapNinjaJars() { return false; }

	@ConfigItem(
			keyName = "swapCrystalJars",
			name = "Crystal Implings",
			description = "Swap crystal impling jars when you have an elite clue"
	)
	default boolean swapCrystalJars() { return false; }

	@ConfigItem(
			keyName = "swapDragonJars",
			name = "Dragon Implings",
			description = "Swap dragon impling jars when you have an elite clue"
	)
	default boolean swapDragonJars() { return true; }


}
