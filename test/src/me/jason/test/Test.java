package me.jason.test;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Test extends JavaPlugin{

	public void onEnable(){
		
		Bukkit.getServer().getLogger().info("Test plugin enabled!");
	}
	
	public void onDisable(){
		Bukkit.getServer().getLogger().info("Test plugin enabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (!(sender instanceof Player)){	// check if sender is a player
			if (cmd.getName().equalsIgnoreCase("test")){
				sender.sendMessage(ChatColor.AQUA + "The console ran the test command!");
				return true;
			}
		}
		
		Player player = (Player) sender;	// cast sender as Player
		
		if (cmd.getName().equalsIgnoreCase("test")){
			player.sendMessage(ChatColor.GOLD + "You have used the test command!");
			return true;
		}
		
		return true;
		
	}
}

/*
 *	tutorial on youtube 
 * 	youtube.com/watch?v=bVySbfryiMM
 * 
 * 	
 * 
 */