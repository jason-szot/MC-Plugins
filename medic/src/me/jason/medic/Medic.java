package me.jason.medic;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Medic extends JavaPlugin{

	// heal or feed a player
	public void onEnable(){
		
	}
	
	public void onDisable(){
		
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (!(sender instanceof Player)){	// console sent command
			sender.sendMessage(ChatColor.RED + "The medic command is for players only");
			return true;
		}
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("heal")){
			if (args.length == 0){
				player.setHealth(20);
				player.sendMessage(ChatColor.GREEN + "You have been healed");
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if(target == null){
				player.sendMessage(ChatColor.RED + "Could not find player!");
				return true;
			}
			target.setHealth(20);
			target.sendMessage(ChatColor.GREEN + "You have been healed!");
			player.sendMessage(ChatColor.GREEN + target.getName() + " has been healed");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("feed")){
			if (args.length == 0){
				player.setFoodLevel(20);
				player.sendMessage(ChatColor.GREEN + "You have been fed");
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if(target == null){
				player.sendMessage(ChatColor.RED + "Could not find player!");
				return true;
			}
			target.setFoodLevel(20);
			target.sendMessage(ChatColor.GREEN + "You have been fed!");
			player.sendMessage(ChatColor.GREEN + target.getName() + " has been fed");
			return true;
		}
		
		return false;
		
	}// end of onCommand
}

/*
 *	tutorial on YouTube 
 * 	youtube.com/watch?v=GTMAs0uZ2KE
 *  youtube.com/user/PogoStick29Dev
 * 	
 */
