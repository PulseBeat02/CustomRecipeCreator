package com.pulsebeat02.customrecipecreator.recipe.stonecutter;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.StonecuttingRecipe;

import com.pulsebeat02.customrecipecreator.CustomRecipeCreator;
import com.pulsebeat02.customrecipecreator.recipe.RecipeCollections;

public class StoneCuttingRecipe {

	private String name;

	private ItemStack ingredient;
	private ItemStack result;

	private boolean ignoreMeta;

	public StoneCuttingRecipe(String name, ItemStack ingredient, ItemStack result, boolean ignoreMeta) {

		this.setName(name);
		this.ingredient = ingredient;
		this.result = result;
		this.ignoreMeta = ignoreMeta;
		this.addRecipe();

		RecipeCollections.stoneCuttingRecipes.add(this);

	}

	public ItemStack getIngredients() {
		return ingredient;
	}

	public void setIngredients(ItemStack ingredients) {
		this.ingredient = ingredients;
	}

	public ItemStack getResult() {
		return result;
	}

	public void setResult(ItemStack result) {
		this.result = result;
	}

	public ItemStack getIngredient() {
		return ingredient;
	}

	public void setIngredient(ItemStack ingredient) {
		this.ingredient = ingredient;
	}

	public boolean isIgnoreMeta() {
		return ignoreMeta;
	}

	public void setIgnoreMeta(boolean ignoreMeta) {
		this.ignoreMeta = ignoreMeta;
	}

	@SuppressWarnings("deprecation")
	public void addRecipe() {

		StonecuttingRecipe recipe;

		if (!this.isIgnoreMeta()) {

			recipe = new StonecuttingRecipe(new NamespacedKey(CustomRecipeCreator.getInstance(), "brewery"),
					this.result, new RecipeChoice.ExactChoice(this.ingredient));

		} else {

			recipe = new StonecuttingRecipe(new NamespacedKey(CustomRecipeCreator.getInstance(), "brewery"),
					this.result, this.ingredient.getType());

		}

		CustomRecipeCreator.getInstance().getServer().addRecipe(recipe);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
