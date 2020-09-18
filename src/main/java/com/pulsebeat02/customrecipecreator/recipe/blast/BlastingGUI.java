package com.pulsebeat02.customrecipecreator.recipe.blast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.pulsebeat02.customrecipecreator.gui.SkullCreation;
import com.pulsebeat02.customrecipecreator.recipe.RecipeCollections;

import net.md_5.bungee.api.ChatColor;

public class BlastingGUI {

	private Inventory gui;
	private BlastingRecipe recipe;

	private HashMap<Integer, ItemStack> menu;

	// Mode: Edit
	public BlastingGUI(BlastingRecipe recipe) {
		this.recipe = recipe;
		this.gui = Bukkit.createInventory(null, 54, "Editing Blast Furnace Recipe: " + recipe.getName());
		init();
	}

	// Mode: New
	public BlastingGUI(String name) {
		this.recipe = new BlastingRecipe(name, new ItemStack(Material.AIR), new ItemStack(Material.AIR), 0, 400, true);
		this.gui = Bukkit.createInventory(null, 54, "Creating Blast Furnace Recipe: " + recipe.getName());
		init();
	}

	public void init() {

		String upwardArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWMyNjg3NmE0NTQ4ODQ0ZTI4YTZmN2JhMWYzNzdjODBlNTk0OTVmN2QzMjIxNGJjYzQ5MjgwNGIxNjYxOTMzOSJ9fX0=";
		String downwardArrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2U4OTE0ZGZjYThjMjA5NDMxNzVjMDUzMzMxYWUzNzNhNDhhZjQ1ZWQ1YmQxNTdjODk0OTVjYWU0NmVjOTgifX19";

		menu.put(10, getSkull(upwardArrow));
		menu.put(13, recipe.getIngredient());
		menu.put(16, getSkull(upwardArrow));
		menu.put(19, getExperienceIcon());
		menu.put(22, getIOIcon());
		menu.put(25, getTimeIcon());
		menu.put(28, getSkull(downwardArrow));
		menu.put(31, recipe.getResult());
		menu.put(34, getSkull(downwardArrow));
		menu.put(48, getMetaType(true));
		menu.put(49, closeMenu());
		menu.put(50, saveChanges());

		for (int i = 0; i < 54; i++) {

			if (!menu.containsKey(i)) {

				gui.setItem(i, empty());

			} else {

				gui.setItem(i, menu.get(i));

			}

		}

	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {

		if (e.getInventory() != gui) {
			return;
		}

		e.setCancelled(true);

		Player p = (Player) e.getWhoClicked();

		switch (e.getSlot()) {

		case 10:
			recipe.setExperience(recipe.getExperience() + 10);
			p.sendMessage(ChatColor.AQUA + "Increased Experience by 10");
			break;

		case 16:
			recipe.setTime(recipe.getTime() + 10);
			p.sendMessage(ChatColor.AQUA + "Increased Time by 0.5 Seconds");
			break;

		case 28:
			recipe.setTime(recipe.getTime() - 10);
			p.sendMessage(ChatColor.AQUA + "Decreased Experience by 10");
			break;

		case 34:
			recipe.setTime(recipe.getTime() - 10);
			p.sendMessage(ChatColor.AQUA + "Decreased Time by 0.5 Seconds");
			break;

		case 48:
			if (recipe.isIgnoreMeta()) {
				menu.put(48, getMetaType(false));
				p.sendMessage(ChatColor.GREEN + "Meta Doesn't Matter");
			} else {
				menu.put(48, getMetaType(true));
				p.sendMessage(ChatColor.RED + "Meta Does Matter");
			}

		case 49:
			p.closeInventory();
			p.sendMessage(ChatColor.AQUA + "Closed the GUI");
			break;

		case 50:
			for (BlastingRecipe br : RecipeCollections.blastingRecipes) {
				if (br.getName().equals(recipe.getName())) {
					RecipeCollections.blastingRecipes.remove(br);
					RecipeCollections.blastingRecipes.add(recipe);
				}
			}
			p.sendMessage(ChatColor.AQUA + "Added the Blast Furnace Recipe!");
			break;

		default:
			p.sendMessage(ChatColor.AQUA + "Nothing Here!");
			break;

		}

	}

	public ItemStack getExperienceIcon() {

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.AQUA + "Use the up and down buttons to adjust the amount");
		lore.add(ChatColor.AQUA + "Up -> +10 XP");
		lore.add(ChatColor.AQUA + "Down -> -10 XP");

		ItemStack xp = new ItemStack(Material.EXPERIENCE_BOTTLE);

		ItemMeta im = xp.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Experience: " + recipe.getExperience());
		im.setLore(lore);

		xp.setItemMeta(im);

		return xp;

	}

	public ItemStack getIOIcon() {

		String arrow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTllOTM4MTgxZDhjOTZiNGY1OGY2MzMyZDNkZDIzM2VjNWZiODUxYjVhODQwNDM4ZWFjZGJiNjE5YTNmNWYifX19";

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.AQUA + "The slot above me is the slot for the ingredient");
		lore.add(ChatColor.AQUA + "The slot below me is the slot for the result");

		ItemStack io = getSkull(arrow);

		ItemMeta im = io.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Item Placement");
		im.setLore(lore);

		io.setItemMeta(im);

		return io;

	}

	public ItemStack getTimeIcon() {

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.AQUA + "Use the up and down buttons to adjust the amount");
		lore.add(ChatColor.AQUA + "Up -> +0.5 Seconds");
		lore.add(ChatColor.AQUA + "Down -> -0.5 Seconds");

		ItemStack time = new ItemStack(Material.REPEATER);

		ItemMeta im = time.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Seconds: " + (float) recipe.getTime() / 20);
		im.setLore(lore);

		time.setItemMeta(im);

		return time;

	}

	public ItemStack getMetaType(boolean mode) {

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.AQUA + "Check if you want the meta to ignored or not");

		ItemStack meta;

		if (mode) {

			meta = new ItemStack(Material.GREEN_WOOL);

			ItemMeta im = meta.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "Meta Being Ignored");
			im.setLore(lore);

		} else {

			meta = new ItemStack(Material.RED_WOOL);

			ItemMeta im = meta.getItemMeta();
			im.setDisplayName(ChatColor.GREEN + "Meta NOT Being Ignored");
			im.setLore(lore);

		}

		return meta;

	}

	public ItemStack closeMenu() {

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.AQUA + "Close the menu");

		ItemStack close = new ItemStack(Material.BARRIER);

		ItemMeta im = close.getItemMeta();
		im.setDisplayName(ChatColor.RED + "Close the Menu");
		im.setLore(lore);

		close.setItemMeta(im);

		return close;

	}

	public ItemStack saveChanges() {

		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.AQUA + "Save all changes made");

		ItemStack save = new ItemStack(Material.NETHER_STAR);

		ItemMeta im = save.getItemMeta();
		im.setDisplayName(ChatColor.YELLOW + "Save Changes");
		im.setLore(lore);

		save.setItemMeta(im);

		return save;

	}

	public ItemStack empty() {

		ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

		ItemMeta im = empty.getItemMeta();
		im.setDisplayName(ChatColor.GRAY + "");

		empty.setItemMeta(im);

		return empty;

	}

	public ItemStack getSkull(String skull) {
		return SkullCreation.itemWithBase64(SkullCreation.createSkull(), skull);
	}

}
