package com.pulsebeat02.customrecipecreator.recipe.brew.example;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.pulsebeat02.customrecipecreator.recipe.brew.BrewAction;

public class CustomLeatherArmor extends BrewAction {

	@Override
	public void brew(BrewerInventory inventory, ItemStack item, ItemStack ingredient) {

		if (!(item.getType() == Material.LEATHER_BOOTS || item.getType() == Material.LEATHER_LEGGINGS
				|| item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.LEATHER_HELMET)) {

			return;

		}

		LeatherArmorMeta armorMeta = (LeatherArmorMeta) item.getItemMeta();

		Color color = null;

		if (ingredient.getType() == Material.BLACK_DYE) {

			color = Color.BLACK;

		} else if (ingredient.getType() == Material.BLUE_DYE) {

			color = Color.BLUE;

		} else if (ingredient.getType() == Material.BROWN_DYE) {

			color = Color.fromRGB(210, 105, 30);

		} else if (ingredient.getType() == Material.CYAN_DYE) {

			color = Color.AQUA;

		} else if (ingredient.getType() == Material.GRAY_DYE) {

			color = Color.GRAY;

		} else if (ingredient.getType() == Material.GREEN_DYE) {

			color = Color.GREEN;

		} else if (ingredient.getType() == Material.LIGHT_BLUE_DYE) {

			color = Color.fromRGB(100, 149, 237);

		} else if (ingredient.getType() == Material.LIGHT_GRAY_DYE) {

			color = Color.fromRGB(211, 211, 211);

		} else if (ingredient.getType() == Material.LIME_DYE) {

			color = Color.LIME;

		} else if (ingredient.getType() == Material.MAGENTA_DYE) {

			color = Color.PURPLE;

		} else if (ingredient.getType() == Material.ORANGE_DYE) {

			color = Color.ORANGE;

		} else if (ingredient.getType() == Material.PINK_DYE) {

			color = Color.fromRGB(255, 192, 203);

		} else if (ingredient.getType() == Material.PURPLE_DYE) {

			color = Color.PURPLE;

		} else if (ingredient.getType() == Material.RED_DYE) {

			color = Color.RED;

		} else if (ingredient.getType() == Material.WHITE_DYE) {

			color = Color.WHITE;

		} else if (ingredient.getType() == Material.YELLOW_DYE) {

			color = Color.YELLOW;

		}

		armorMeta.setColor(color);
		item.setItemMeta(armorMeta);

	}

}
