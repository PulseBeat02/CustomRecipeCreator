package com.pulsebeat02.customrecipecreator.recipe.craft;

import java.util.HashMap;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

import com.pulsebeat02.customrecipecreator.CustomRecipeCreator;
import com.pulsebeat02.customrecipecreator.recipe.RecipeCollections;

public class CraftingRecipe {

	private String name;

	private HashMap<Integer, ItemStack> ingredients;
	private ItemStack result;

	private boolean shapeless;
	private boolean ignoreMeta;

	public CraftingRecipe(String name, HashMap<Integer, ItemStack> ingredients, ItemStack result, boolean shapeless,
			boolean ignoreMeta) {

		this.setName(name);
		this.ingredients = ingredients;
		this.result = result;
		this.shapeless = shapeless;
		this.ignoreMeta = ignoreMeta;
		this.addRecipe();

		RecipeCollections.craftingRecipes.add(this);

	}

	public HashMap<Integer, ItemStack> getIngredients() {
		return ingredients;
	}

	public void setIngredients(HashMap<Integer, ItemStack> ingredients) {
		this.ingredients = ingredients;
	}

	public ItemStack getResult() {
		return result;
	}

	public void setResult(ItemStack result) {
		this.result = result;
	}

	public boolean isShapeless() {
		return shapeless;
	}

	public void setShapeless(boolean shapeless) {
		this.shapeless = shapeless;
	}

	public boolean isIgnoreMeta() {
		return ignoreMeta;
	}

	public void setIgnoreMeta(boolean ignoreMeta) {
		this.ignoreMeta = ignoreMeta;
	}

	@SuppressWarnings("deprecation")
	public void addRecipe() {

		if (this.isShapeless()) {

			ShapelessRecipe recipe = new ShapelessRecipe(
					new NamespacedKey(CustomRecipeCreator.getInstance(), "brewery"), this.getResult());

			HashMap<Integer, ItemStack> map = this.getIngredients();
			for (int key : map.keySet()) {
				if (!this.isIgnoreMeta()) {
					recipe.addIngredient(new RecipeChoice.ExactChoice(map.get(key)));
				} else {
					recipe.addIngredient(map.get(key).getType());
				}
			}

			CustomRecipeCreator.getInstance().getServer().addRecipe(recipe);

		} else {

			ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(CustomRecipeCreator.getInstance(), "brewery"),
					this.getResult());
			recipe.shape("012", "345", "678");

			HashMap<Integer, ItemStack> map = this.getIngredients();
			for (int key : map.keySet()) {
				if (!this.isIgnoreMeta()) {
					recipe.setIngredient((char) (key + '0'), new RecipeChoice.ExactChoice(map.get(key)));
				} else {
					recipe.setIngredient((char) (key + '0'), map.get(key).getType());
				}
			}

			CustomRecipeCreator.getInstance().getServer().addRecipe(recipe);

		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
