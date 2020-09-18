package com.pulsebeat02.customrecipecreator.recipe.brew;

import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

public abstract class BrewAction {

	public abstract void brew(BrewerInventory inventory, ItemStack item, ItemStack ingredient);

}
