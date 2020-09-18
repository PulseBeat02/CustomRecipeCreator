package com.pulsebeat02.customrecipecreator.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.pulsebeat02.customrecipecreator.CustomRecipeCreator;

public class MainPageGUI implements Listener {

	private final Inventory inv;

	public MainPageGUI() {

		CustomRecipeCreator.getInstance().getServer().getPluginManager().registerEvents(new MainPageGUI(),
				CustomRecipeCreator.getInstance());
		inv = Bukkit.createInventory(null, 36, "CustomRecipeCreator");
		init();

	}

	public void init() {

		for (int i = 0; i < 9; i++) {
			inv.setItem(i, create(Material.GRAY_STAINED_GLASS_PANE, "§8"));
		}

		inv.setItem(9, create(Material.GRAY_STAINED_GLASS, "§8"));
		inv.setItem(10, create(Material.BLAST_FURNACE, "§l§bCustom Blast Furnace Recipes", ""));
		inv.setItem(11, create(Material.BREWING_STAND, "§l§bCustom Potions", ""));
		inv.setItem(12, create(Material.CAMPFIRE, "§l§bCustom Campfire Recipes", ""));
		inv.setItem(13, create(Material.CRAFTING_TABLE, "§l§bCustom Crafting Recipes", ""));
		inv.setItem(14, create(Material.VILLAGER_SPAWN_EGG, "§l§bCustom Merchant Recipes", ""));
		inv.setItem(15, create(Material.FURNACE, "§l§bCustom Furnace Recipes", ""));
		inv.setItem(16, create(Material.SMITHING_TABLE, "§l§bCustom Smithing Table Recipes", ""));
		inv.setItem(17, create(Material.GRAY_STAINED_GLASS, "§8"));
		inv.setItem(18, create(Material.GRAY_STAINED_GLASS, "§8"));
		inv.setItem(19, create(Material.SMOKER, "§l§bCustom Smoker Recipes", ""));
		inv.setItem(20, create(Material.STONECUTTER, "§l§bCustom Stonecutter Recipes", ""));
		inv.setItem(27, create(Material.GRAY_STAINED_GLASS, "§8"));

		for (int i = 27; i < 36; i++) {
			inv.setItem(i, create(Material.GRAY_STAINED_GLASS, "§8"));
		}

	}

	public ItemStack create(Material material, String name, String... lore) {

		ItemStack item = new ItemStack(material, 1);

		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));

		item.setItemMeta(meta);

		return item;

	}

	public void openInventory(final HumanEntity ent) {
		ent.openInventory(inv);
	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {

		if (e.getInventory() != inv) {
			return;
		}

		e.setCancelled(true);

		ItemStack clickedItem = e.getCurrentItem();

		if (clickedItem == null || clickedItem.getType() == Material.AIR)
			return;

		final Player p = (Player) e.getWhoClicked();
		p.sendMessage("You clicked at slot " + e.getRawSlot());
	}

}
