package com.pulsebeat02.customrecipecreator.recipe;

import java.util.ArrayList;
import java.util.List;

import com.pulsebeat02.customrecipecreator.recipe.blast.BlastingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.brew.BrewingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.campfire.CampFireRecipe;
import com.pulsebeat02.customrecipecreator.recipe.craft.CraftingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.merchant.MerchantRecipe;
import com.pulsebeat02.customrecipecreator.recipe.smelt.SmeltingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.smith.SmithingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.smoker.SmokingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.stonecutter.StoneCuttingRecipe;

public class RecipeCollections {

	public static List<BrewingRecipe> brewingRecipes = new ArrayList<>();
	public static List<CraftingRecipe> craftingRecipes = new ArrayList<>();
	public static List<SmeltingRecipe> smeltingRecipes = new ArrayList<>();
	public static List<BlastingRecipe> blastingRecipes = new ArrayList<>();
	public static List<CampFireRecipe> campFireRecipes = new ArrayList<>();
	public static List<SmokingRecipe> smokingRecipes = new ArrayList<>();
	public static List<StoneCuttingRecipe> stoneCuttingRecipes = new ArrayList<>();
	public static List<SmithingRecipe> smithingRecipes = new ArrayList<>();
	public static List<MerchantRecipe> merchantRecipes = new ArrayList<>();

}
