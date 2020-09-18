package com.pulsebeat02.customrecipecreator.recipe.brew;

import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import com.pulsebeat02.customrecipecreator.CustomRecipeCreator;

public class PotionEvent implements Listener {

	@EventHandler(priority = EventPriority.LOWEST)
	public void PotionListener(InventoryClickEvent e) {

		/*
		 * If the current clicked item is empty, the GUI is not correct, or the
		 * ingredient is wrong, we shouldn't call the brewing event, because it isn't
		 * valid. We also check if the recipe itself exists. If it does, we should call
		 * our function and start the brewing process.
		 * 
		 */

		if (e.getClickedInventory() == null || e.getClickedInventory().getType() != InventoryType.BREWING) {
			return;
		}

		if (((BrewerInventory) e.getInventory()).getIngredient() == null && e.getCursor() != null) {
			return;
		}

		BrewingRecipe recipe = BrewingRecipe.getRecipe(((BrewerInventory) e.getClickedInventory()));

		if (recipe == null) {

			return;

		} else {

			recipe.startBrewing((BrewerInventory) e.getClickedInventory());

		}

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void customPotionItemStackClick(final InventoryClickEvent e) {

		/*
		 * Minecraft by default doesn't allow us to put any item in any slot in the
		 * Brewing Stand. (Like Paper, Sugar Cane, etc) That's why we must override it
		 * and force put the item in the Brewing Stand slot. We first check if the
		 * current item that is being selected is empty or the GUI isn't correct. After
		 * that, we make another check to make sure that the user is actually dragging
		 * and clicking the item into the Brewing Stand slot. We do this by checking the
		 * user has left clicked. Finally, we check if the ItemStacks are similar, and
		 * if they are, we combine the amounts together. Finally, we create a delayed
		 * task and force set the item in the GUI, and update the inventory.
		 * 
		 */

		if (e.getClickedInventory() == null || e.getClickedInventory().getType() != InventoryType.BREWING) {
			return;
		}

		if (!(e.getClick() == ClickType.LEFT || e.getClick() == ClickType.RIGHT)) {
			return;
		}

		ItemStack is = e.getCurrentItem(); // GETS ITEMSTACK THAT IS BEING CLICKED
		ItemStack is2 = e.getCursor(); // GETS CURRENT ITEMSTACK HELD ON MOUSE

		if (e.getClick() == ClickType.RIGHT && !isSimilar(is, is2)) {
			return;
		}

		Player p = (Player) (e.getView().getPlayer());

		Bukkit.getScheduler().scheduleSyncDelayedTask(CustomRecipeCreator.getInstance(), new Runnable() {
			@Override
			public void run() {

				if (e.getClick() == ClickType.LEFT) {

					if (isSimilar(is, is2)) {

						int remaining = 64 - is.getAmount();

						if (is2.getAmount() >= remaining) {

							is.setAmount(64);
							is2.setAmount(is2.getAmount() - remaining);
							p.updateInventory();

						} else if (is2.getAmount() < remaining) {

							is.setAmount(is.getAmount() + is2.getAmount());
							is2.setAmount(0);
							p.updateInventory();

						}

					} else {

						e.getWhoClicked().setItemOnCursor(is);
						e.getClickedInventory().setItem(e.getSlot(), is2);
						p.updateInventory();

					}

				} else {

					is2.setAmount(is.getAmount() - 1);
					is.setAmount(is2.getAmount() + 1);

					e.getClickedInventory().setItem(e.getSlot(), is2);
					p.updateInventory();

				}

			}
		}, 1L);
		p.updateInventory();

		e.setCancelled(true);

	}

	private boolean isSimilar(ItemStack item1, ItemStack item2) {

		if (item2 == null) {
			return false;
		}

		if (item2.getType() == item1.getType()
				&& ((Damageable) item2.getItemMeta()).getDamage() == ((Damageable) item1.getItemMeta()).getDamage()) {
			ItemMeta item1Meta = item1.getItemMeta();
			ItemMeta item2Meta = item2.getItemMeta();
			if (item1Meta.hasDisplayName() != item2Meta.hasDisplayName()) {
				return false;
			}
			if (item1Meta.hasDisplayName()) {
				if (!item1Meta.getDisplayName().equals(item2Meta.getDisplayName())) {
					return false;
				}
			}
			if (item1Meta.hasLore() != item2Meta.hasLore()) {
				return false;
			}
			if (item1Meta.hasLore()) {
				if (item1Meta.getLore().size() != item2Meta.getLore().size()) {
					return false;
				}
				for (int index = 0; index < item1Meta.getLore().size(); index++) {
					if (item1Meta.getLore().get(index).equals(item2Meta.getLore().get(index))) {
						return false;
					}
				}
			}
			if (item1Meta.hasEnchants() != item2Meta.hasEnchants()) {
				return false;
			}
			if (item1Meta.hasEnchants()) {
				if (item1Meta.getEnchants().size() != item2Meta.getEnchants().size()) {
					return false;
				}
				for (Entry<Enchantment, Integer> enchantInfo : item1Meta.getEnchants().entrySet()) {
					if (item1Meta.getEnchantLevel(enchantInfo.getKey()) != item2Meta
							.getEnchantLevel(enchantInfo.getKey())) {
						return false;
					}
				}
			}
			if (item1Meta.getItemFlags().size() != item2Meta.getItemFlags().size()) {
				return false;
			}
			for (ItemFlag flag : item1Meta.getItemFlags()) {
				if (!item2Meta.hasItemFlag(flag)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
