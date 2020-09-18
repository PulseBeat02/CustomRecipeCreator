package com.pulsebeat02.customrecipecreator.gui;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.pulsebeat02.customrecipecreator.CustomRecipeCreator;
import com.pulsebeat02.customrecipecreator.recipe.blast.BlastingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.brew.BrewingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.campfire.CampFireRecipe;
import com.pulsebeat02.customrecipecreator.recipe.craft.CraftingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.merchant.MerchantRecipe;
import com.pulsebeat02.customrecipecreator.recipe.smelt.SmeltingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.smith.SmithingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.smoker.SmokingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.stonecutter.StoneCuttingRecipe;

import net.md_5.bungee.api.ChatColor;

public class ItemListGUI implements Listener {

	private ArrayList<Inventory> pages = new ArrayList<>();

	private ItemStack emptySlot = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
	private ItemStack closeSlot = new ItemStack(Material.BARRIER);
	private ItemStack nextSlot = SkullCreation.itemWithBase64(SkullCreation.createSkull(),
			"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzE1NDQ1ZGExNmZhYjY3ZmNkODI3ZjcxYmFlOWMxZDJmOTBjNzNlYjJjMWJkMWVmOGQ4Mzk2Y2Q4ZTgifX19");
	private ItemStack backSlot = SkullCreation.itemWithBase64(SkullCreation.createSkull(),
			"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWVkNzg4MjI1NzYzMTdiMDQ4ZWVhOTIyMjdjZDg1ZjdhZmNjNDQxNDhkY2I4MzI3MzNiYWNjYjhlYjU2ZmExIn19fQ==");

	private Queue<?> items;

	public ItemListGUI(Object obj) {

		if (obj == null) {

			return;

		}

		CustomRecipeCreator.getInstance().getServer().getPluginManager().registerEvents(this,
				CustomRecipeCreator.getInstance());

		this.pages.add(Bukkit.createInventory(null, 54, "Recipe GUI"));

		if (obj instanceof BrewingRecipe) {

			items = new LinkedList<BrewingRecipe>();

		} else if (obj instanceof CraftingRecipe) {

			items = new LinkedList<CraftingRecipe>();

		} else if (obj instanceof SmeltingRecipe) {

			items = new LinkedList<SmeltingRecipe>();

		} else if (obj instanceof BlastingRecipe) {

			items = new LinkedList<BlastingRecipe>();

		} else if (obj instanceof CampFireRecipe) {

			items = new LinkedList<CampFireRecipe>();

		} else if (obj instanceof SmokingRecipe) {

			items = new LinkedList<SmokingRecipe>();

		} else if (obj instanceof StoneCuttingRecipe) {

			items = new LinkedList<StoneCuttingRecipe>();

		} else if (obj instanceof SmithingRecipe) {

			items = new LinkedList<SmithingRecipe>();

		} else if (obj instanceof MerchantRecipe) {

			items = new LinkedList<MerchantRecipe>();

		}

		init();

	}

	public void init() {

		ItemMeta empty = emptySlot.getItemMeta();
		ItemMeta close = closeSlot.getItemMeta();
		ItemMeta next = nextSlot.getItemMeta();
		ItemMeta back = backSlot.getItemMeta();

		empty.setDisplayName(ChatColor.DARK_GRAY + " ");
		close.setDisplayName(ChatColor.RED + "Close Menu");
		next.setDisplayName(ChatColor.GREEN + "Next Page");
		back.setDisplayName(ChatColor.RED + "Page Before");

		initializeRow(0);

	}

	public void initializeRow(int row) {

		switch (row) {

		case 0:
			for (int i = 0; i < 9; i++) {
				pages.get(pages.size() - 1).setItem(i, emptySlot);
			}
			row++;
			break;

		case 5:
			for (int i = 44; i < 53; i++) {
				pages.get(pages.size() - 1).setItem(i, emptySlot);
			}
			pages.add(Bukkit.createInventory(null, 54, "Recipe GUI"));
			initializeRow(0);
			break;

		default:
			pages.get(pages.size() - 1).setItem(9 * row + 1, emptySlot);
			pages.get(pages.size() - 1).setItem(9 * row + 9, emptySlot);

			for (int i = 9 * row + 2; i < 9 * row + 8; i++) {

				if (items.isEmpty()) {
					return;
				}

				Object obj = items.remove();

				if (obj instanceof BrewingRecipe) {

					BrewingRecipe br = BrewingRecipe.class.cast(obj);

					ItemMeta meta = br.getIngredient().getItemMeta();
					meta.setDisplayName(br.getName());

					ItemStack is = br.getIngredient().clone();
					is.setItemMeta(meta);

					pages.get(pages.size() - 1).setItem(i, is);

				} else if (obj instanceof CraftingRecipe) {

					CraftingRecipe br = CraftingRecipe.class.cast(obj);

					ItemMeta meta = br.getResult().getItemMeta();
					meta.setDisplayName(br.getName());

					ItemStack is = br.getResult().clone();
					is.setItemMeta(meta);

					pages.get(pages.size() - 1).setItem(i, is);

				} else if (obj instanceof SmeltingRecipe) {

					SmeltingRecipe br = SmeltingRecipe.class.cast(obj);

					ItemMeta meta = br.getResult().getItemMeta();
					meta.setDisplayName(br.getName());

					ItemStack is = br.getResult().clone();
					is.setItemMeta(meta);

					pages.get(pages.size() - 1).setItem(i, is);

				} else if (obj instanceof BlastingRecipe) {

					BlastingRecipe br = BlastingRecipe.class.cast(obj);

					ItemMeta meta = br.getResult().getItemMeta();
					meta.setDisplayName(br.getName());

					ItemStack is = br.getResult().clone();
					is.setItemMeta(meta);

					pages.get(pages.size() - 1).setItem(i, is);

				} else if (obj instanceof CampFireRecipe) {

					CampFireRecipe br = CampFireRecipe.class.cast(obj);

					ItemMeta meta = br.getResult().getItemMeta();
					meta.setDisplayName(br.getName());

					ItemStack is = br.getResult().clone();
					is.setItemMeta(meta);

					pages.get(pages.size() - 1).setItem(i, is);

				} else if (obj instanceof SmokingRecipe) {

					SmokingRecipe br = SmokingRecipe.class.cast(obj);

					ItemMeta meta = br.getResult().getItemMeta();
					meta.setDisplayName(br.getName());

					ItemStack is = br.getResult().clone();
					is.setItemMeta(meta);

					pages.get(pages.size() - 1).setItem(i, is);

				} else if (obj instanceof StoneCuttingRecipe) {

					StoneCuttingRecipe br = StoneCuttingRecipe.class.cast(obj);

					ItemMeta meta = br.getResult().getItemMeta();
					meta.setDisplayName(br.getName());

					ItemStack is = br.getResult().clone();
					is.setItemMeta(meta);

					pages.get(pages.size() - 1).setItem(i, is);

				} else if (obj instanceof SmithingRecipe) {

					SmithingRecipe br = SmithingRecipe.class.cast(obj);

					ItemMeta meta = br.getResult().getItemMeta();
					meta.setDisplayName(br.getName());

					ItemStack is = br.getResult().clone();
					is.setItemMeta(meta);

					pages.get(pages.size() - 1).setItem(i, is);

				} else if (obj instanceof MerchantRecipe) {

					MerchantRecipe br = MerchantRecipe.class.cast(obj);

					ItemMeta meta = br.getResult().getItemMeta();
					meta.setDisplayName(br.getName());

					ItemStack is = br.getResult().clone();
					is.setItemMeta(meta);

					pages.get(pages.size() - 1).setItem(i, is);

				}

			}
			row++;
			break;
		}

	}

	public void openInventory(final HumanEntity ent) {
		ent.openInventory(pages.get(0));
	}

	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e) {

		if (!pages.contains(e.getInventory())) {
			return;
		}

		e.setCancelled(true);

		final ItemStack clickedItem = e.getCurrentItem();

		if (clickedItem == null || clickedItem.getType() == Material.AIR) {
			return;
		}

		final Player p = (Player) e.getWhoClicked();
		p.sendMessage("You clicked at slot " + e.getRawSlot());
	}

	@EventHandler
	public void onInventoryClick(final InventoryDragEvent e) {

		if (pages.contains(e.getInventory())) {
			e.setCancelled(true);
		}

	}

}
