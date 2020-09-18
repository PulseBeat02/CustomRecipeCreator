package com.pulsebeat02.customrecipecreator.recipe.merchant;

import org.bukkit.inventory.ItemStack;
import com.pulsebeat02.customrecipecreator.CustomRecipeCreator;
import com.pulsebeat02.customrecipecreator.recipe.RecipeCollections;

public class MerchantRecipe {

	private String name;

	private ItemStack result;

	private int uses;
	private int maxuses;
	private int villagerExperience;

	private float priceMultiplier;

	boolean experienceReward;

	public MerchantRecipe(String name, ItemStack result, int uses, int maxuses, boolean experienceReward,
			int villagerExperience, float priceMultiplier) {

		this.setName(name);
		this.result = result;
		this.uses = uses;
		this.maxuses = maxuses;
		this.experienceReward = experienceReward;
		this.villagerExperience = villagerExperience;
		this.experienceReward = experienceReward;
		this.addRecipe();

		RecipeCollections.merchantRecipes.add(this);

	}

	public ItemStack getResult() {
		return result;
	}

	public void setResult(ItemStack result) {
		this.result = result;
	}

	public int getUses() {
		return uses;
	}

	public void setUses(int uses) {
		this.uses = uses;
	}

	public int getMaxuses() {
		return maxuses;
	}

	public void setMaxuses(int maxuses) {
		this.maxuses = maxuses;
	}

	public int getVillagerExperience() {
		return villagerExperience;
	}

	public void setVillagerExperience(int villagerExperience) {
		this.villagerExperience = villagerExperience;
	}

	public float getPriceMultiplier() {
		return priceMultiplier;
	}

	public void setPriceMultiplier(float priceMultiplier) {
		this.priceMultiplier = priceMultiplier;
	}

	public boolean isExperienceReward() {
		return experienceReward;
	}

	public void setExperienceReward(boolean experienceReward) {
		this.experienceReward = experienceReward;
	}

	public void addRecipe() {

		org.bukkit.inventory.MerchantRecipe recipe = new org.bukkit.inventory.MerchantRecipe(this.result, this.uses,
				this.maxuses, this.experienceReward, this.villagerExperience, this.priceMultiplier);

		CustomRecipeCreator.getInstance().getServer().addRecipe(recipe);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
