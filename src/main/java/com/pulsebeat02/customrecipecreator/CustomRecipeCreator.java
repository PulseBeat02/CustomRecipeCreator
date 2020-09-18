package com.pulsebeat02.customrecipecreator;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.pulsebeat02.customrecipecreator.gui.MainPageGUI;
import com.pulsebeat02.customrecipecreator.recipe.brew.BrewingRecipe;
import com.pulsebeat02.customrecipecreator.recipe.brew.PotionEvent;
import com.pulsebeat02.customrecipecreator.recipe.brew.example.CustomLeatherArmor;

import net.md_5.bungee.api.ChatColor;

public class CustomRecipeCreator extends JavaPlugin implements Listener {

	private static CustomRecipeCreator INSTANCE;

	@Override
	public void onEnable() {

		/*
		 * We first create a plugin instance of Brewery. It is required if we want to
		 * add our custom potion recipes.
		 * 
		 * We then register our events that are needed for InventoryClickEvent.
		 * 
		 * After that, we create a new Custom Brewing Recipe with our pre-made class,
		 * CustomLeatherArmor.
		 * 
		 */

		INSTANCE = this;

		getServer().getPluginManager().registerEvents(new PotionEvent(), this);

		new BrewingRecipe(Material.BLACK_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.BLUE_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.BROWN_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.CYAN_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.GRAY_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.GREEN_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.LIGHT_BLUE_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.LIGHT_GRAY_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.LIME_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.MAGENTA_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.ORANGE_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.PINK_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.PURPLE_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.RED_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.WHITE_DYE, new CustomLeatherArmor());
		new BrewingRecipe(Material.YELLOW_DYE, new CustomLeatherArmor());

	}

	public static Plugin getInstance() {
		return INSTANCE;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if (sender instanceof Player) {
			
			Player p = (Player) sender;
			
			if (command.getName().equalsIgnoreCase("crc")) {
				
				MainPageGUI gui = new MainPageGUI();
				gui.openInventory(p);
				
				p.sendMessage(ChatColor.GOLD + "Sent GUI to Player!");
				
			}
			
		} else {
			
			sender.sendMessage(ChatColor.DARK_RED + "You must be a player to use this command.");
			return true;
			
		}
		
		return false;
		
	}

}















