package com.pulsebeat02.customrecipecreator.recipe.smith;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

import com.pulsebeat02.customrecipecreator.CustomRecipeCreator;
import com.pulsebeat02.customrecipecreator.recipe.RecipeCollections;

public class SmithingRecipe {

	private String name;

	private ItemStack ingredient;
	private ItemStack addition;
	private ItemStack result;

	public SmithingRecipe(String name, ItemStack ingredient, ItemStack addition, ItemStack result) {

		this.setName(name);
		this.ingredient = ingredient;
		this.addition = addition;
		this.result = result;
		this.addRecipe();

		RecipeCollections.smithingRecipes.add(this);

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

	public ItemStack getAddition() {
		return addition;
	}

	public void setAddition(ItemStack addition) {
		this.addition = addition;
	}

	@SuppressWarnings("deprecation")
	public void addRecipe() {

		org.bukkit.inventory.SmithingRecipe recipe = new org.bukkit.inventory.SmithingRecipe(
				new NamespacedKey(CustomRecipeCreator.getInstance(), "brewery"), this.result,
				new RecipeChoice.ExactChoice(this.ingredient), new RecipeChoice.ExactChoice(this.addition));

		CustomRecipeCreator.getInstance().getServer().addRecipe(recipe);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
