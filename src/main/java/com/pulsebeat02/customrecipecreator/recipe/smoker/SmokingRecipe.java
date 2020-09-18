package com.pulsebeat02.customrecipecreator.recipe.smoker;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;

import com.pulsebeat02.customrecipecreator.CustomRecipeCreator;
import com.pulsebeat02.customrecipecreator.recipe.RecipeCollections;

public class SmokingRecipe {

	private String name;

	private ItemStack ingredient;
	private ItemStack result;

	private float experience;
	private int time;

	private boolean ignoreMeta;

	public SmokingRecipe(String name, ItemStack ingredient, ItemStack result, float experience, int time,
			boolean ignoreMeta) {

		this.setName(name);
		this.ingredient = ingredient;
		this.result = result;
		this.experience = experience;
		this.time = time;
		this.ignoreMeta = ignoreMeta;
		this.addRecipe();

		RecipeCollections.smokingRecipes.add(this);

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

	public float getExperience() {
		return experience;
	}

	public void setExperience(float experience) {
		this.experience = experience;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isIgnoreMeta() {
		return ignoreMeta;
	}

	public void setIgnoreMeta(boolean ignoreMeta) {
		this.ignoreMeta = ignoreMeta;
	}

	@SuppressWarnings("deprecation")
	public void addRecipe() {

		org.bukkit.inventory.SmokingRecipe recipe;

		if (!this.isIgnoreMeta()) {

			recipe = new org.bukkit.inventory.SmokingRecipe(
					new NamespacedKey(CustomRecipeCreator.getInstance(), "brewery"), this.result,
					new RecipeChoice.ExactChoice(this.ingredient), this.experience, this.time);

		} else {

			recipe = new org.bukkit.inventory.SmokingRecipe(
					new NamespacedKey(CustomRecipeCreator.getInstance(), "brewery"), this.result,
					this.ingredient.getType(), this.experience, this.time);

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
