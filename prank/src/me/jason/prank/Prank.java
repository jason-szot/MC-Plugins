package me.jason.prank;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Prank extends JavaPlugin{
	
	public void onEnable(){
		Bukkit.getServer().getLogger().info("Prank v" + this.getDescription().getVersion() + " has been enabled.");
	}
	
	public void onDisable(){
		Bukkit.getServer().getLogger().info("Prank v" + this.getDescription().getVersion() + " has been disabled.");
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		// fakeop and fakejoin
		
		if (cmd.getName().equalsIgnoreCase("fakeop")){
			// need a valid, online player to use this command against
			if (args.length == 0){	
				sender.sendMessage(ChatColor.RED + "Please specify a player.");
				return true;
			}
			// have a target
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null){	// check if target is real
				sender.sendMessage(ChatColor.RED + "could not find player " + args[0]);
				return false;
			}
			// send messages to players
			target.sendMessage(ChatColor.YELLOW + "You are now op");
			sender.sendMessage(ChatColor.GREEN + "Success");
		}
		
		if (cmd.getName().equalsIgnoreCase("fakejoin")){
			// broadcast fake join message with given name from command
			if (args.length == 0){	// no name given for fake join
				sender.sendMessage(ChatColor.RED + "Please specify a name.");
				return true;
			}
			// broadcast message
			Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + args[0] + " joined the game.");
			return true;
		}
		
		return true;
	}

}

/*
 *	tutorial on YouTube 
 * 	youtube.com/watch?v=cLMkNkaqoEE
 *  youtube.com/user/PogoStick29Dev
 * 	
 */