package com.jarstopper;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.ClientTick;
import net.runelite.client.util.Text;
import net.runelite.client.game.ItemManager;
import net.runelite.client.input.MouseManager;

@Slf4j
@PluginDescriptor(
	name = "JarStopper"
)
public class JarStopperPlugin extends Plugin
{
	private boolean hasBeginner;
	private boolean hasEasy;
	private boolean hasMedium;
	private boolean hasHard;
	private boolean hasElite;
	private JarStopperMouseListener mouseListener;
	private int testCounter = 0;

	@Inject
	private Client client;

	@Inject
	private JarStopperConfig config;

	@Inject
	private ItemManager itemManager;

	@Inject
	private MouseManager mouseManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");

		mouseListener = new JarStopperMouseListener(this);
		mouseManager.registerMouseListener(mouseListener);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onClientTick(ClientTick clientTick) {
		if (client.getGameState() != GameState.LOGGED_IN || client.isMenuOpen())// || client.isKeyPressed(KeyCode.KC_SHIFT))
		{
			return;
		}
		MenuEntry[] menuEntries = client.getMenuEntries();

		if (menuEntries.length <= 1) {
			return;
		}
		checkInventory();

		String sourceName = Text.removeTags(menuEntries[1].getTarget());
		if (config.swapBabyJars() && (hasBeginner || hasEasy) && sourceName.equals("Baby impling jar") ||
				config.swapYoungJars() && (hasBeginner || hasEasy) && sourceName.equals("Young impling jar") ||
				config.swapGourmetJars() && hasEasy && sourceName.equals("Gourmet impling jar") ||
				config.swapEarthJars() && hasMedium && sourceName.equals("Earth impling jar") ||
				config.swapEssenceJars() && hasMedium && sourceName.equals("Essence impling jar") ||
				config.swapEclecticJars() && hasMedium && sourceName.equals("Eclectic impling jar") ||
				config.swapNatureJars() && hasHard && sourceName.equals("Nature impling jar") ||
				config.swapMagpieJars() && hasHard && sourceName.equals("Magpie impling jar") ||
				config.swapNinjaJars() && hasHard && sourceName.equals("Ninja impling jar") ||
				config.swapCrystalJars() && hasElite && sourceName.equals("Crystal impling jar") ||
				config.swapDragonJars() && hasElite && sourceName.equals("Dragon impling jar")) {
			int wieldIndex = -1;
			int checkChargesIndex = -1;

			for (int j = 0; j < menuEntries.length; j ++) {
				if (Text.removeTags(menuEntries[j].getOption()).equals("Use")) {
					wieldIndex = j;
				}
				if (Text.removeTags(menuEntries[j].getOption()).equals("Loot")) {
					checkChargesIndex = j;
				}
			}

			if (wieldIndex == -1 || checkChargesIndex == -1) {
				return;
			}
			MenuEntry entry1 = menuEntries[wieldIndex];
			MenuEntry entry2 = menuEntries[checkChargesIndex];
			menuEntries[wieldIndex] = entry2;
			menuEntries[checkChargesIndex] = entry1;
			client.setMenuEntries(menuEntries);

		}
	}

	public void checkInventory() {
		Item[] items = client.getItemContainer(InventoryID.INVENTORY).getItems();
		hasBeginner = false;
		hasEasy = false;
		hasMedium = false;
		hasHard = false;
		hasElite = false;

		for (int i = 0; i < items.length; i ++) {
			String itemName = itemManager.getItemComposition(items[i].getId()).getName();

			if (itemName.equals("Clue scroll (beginner)")) {
				hasBeginner = true;
			} else if (itemName.equals("Clue scroll (easy)")) {
				hasEasy = true;
			} else if (itemName.equals("Clue scroll (medium)")) {
				hasMedium = true;
			} else if (itemName.equals("Clue scroll (hard)")) {
				hasHard = true;
			} else if (itemName.equals("Clue scroll (elite)")) {
				hasElite = true;
			}
		}
	}

	@Provides
	JarStopperConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(JarStopperConfig.class);
	}
}
