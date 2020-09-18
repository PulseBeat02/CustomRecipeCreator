package com.pulsebeat02.customrecipecreator.recipe.brew;

import java.util.Arrays;

import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.block.BrewingStand;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.pulsebeat02.customrecipecreator.CustomRecipeCreator;
import com.pulsebeat02.customrecipecreator.recipe.RecipeCollections;

public class BrewingRecipe {

	/*
	 * @param {ItemStack} ingredient represents the ingredient used in our Brewing
	 * Recipe
	 * 
	 * @param {BrewAction} action represents the Abstract Class used for the result
	 * of the Brewing Recipe
	 * 
	 * @param {boolean} perfect represents whether the items can be brewed IF the
	 * two ItemStacks are similar.
	 * 
	 */

	private String name;

	private ItemStack ingredient;

	private BrewAction action;

	private BrewClock clock;

	private boolean perfect;

	public BrewingRecipe(String name, ItemStack ingredient, BrewAction action, boolean perfect) {
		this.setName(name);
		this.ingredient = ingredient;
		this.action = action;
		this.perfect = perfect;
		RecipeCollections.brewingRecipes.add(this);
	}

	public BrewingRecipe(Material ingredient, BrewAction action) {
		this("test", new ItemStack(ingredient), action, true);
	}

	public ItemStack getIngredient() {
		return ingredient;
	}

	public BrewAction getAction() {
		return action;
	}

	public BrewClock getClock() {
		return clock;
	}

	public boolean isPerfect() {
		return perfect;
	}

	@Nullable
	public static BrewingRecipe getRecipe(BrewerInventory inventory) {

		for (BrewingRecipe recipe : RecipeCollections.brewingRecipes) {

			if (!recipe.isPerfect() && inventory.getIngredient().getType() == recipe.getIngredient().getType()) {
				return recipe;
			}

			if (recipe.isPerfect() && inventory.getIngredient().isSimilar(recipe.getIngredient())) {
				return recipe;
			}

		}

		return null;

	}

	public void startBrewing(BrewerInventory inventory) {
		clock = new BrewClock(this, inventory, 400);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public class BrewClock extends BukkitRunnable {

		private BrewerInventory inventory;
		private BrewingRecipe recipe;
		private ItemStack ingredient;
		private BrewingStand stand;
		private int current;

		public BrewClock(BrewingRecipe recipe, BrewerInventory inventory, int time) {
			this.recipe = recipe;
			this.inventory = inventory;
			this.ingredient = inventory.getIngredient();
			this.stand = inventory.getHolder();
			this.current = time;
			runTaskTimer(CustomRecipeCreator.getInstance(), 0L, 1L);
		}

		@Override
		public void run() {

			if (current == 0) {
				inventory.setIngredient(new ItemStack(Material.AIR));
				inventory.setFuel(new ItemStack(Material.AIR));
				for (int i = 0; i < 3; i++) {
					if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR)
						continue;
					recipe.getAction().brew(inventory, inventory.getItem(i), ingredient);
				}

				cancel();
				return;
			}

			if (inventory.getFuel() == null || inventory.getIngredient() == null
					|| Arrays.stream(inventory.getContents()).allMatch(element -> element == null)) {

				cancel();
				return;

			}

			current--;
			stand.setBrewingTime(current);
			stand.update(true);

		}

	}

}
