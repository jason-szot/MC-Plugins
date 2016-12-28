package me.jason.freeze;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Freeze extends JavaPlugin implements Listener{
	
	ArrayList<String> frozen = new ArrayList<String>();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		Player p = event.getPlayer();
		if (frozen.contains(p.getName())){
			event.setTo(event.getFrom());
			p.sendMessage(ChatColor.RED + "You are frozen!");
		}
	}
	
	
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getLogger().info("freeze v" + this.getDescription().getVersion() + " enabled.");
	}
	
	public void onDisable(){
		Bukkit.getServer().getLogger().info("freeze v" + this.getDescription().getVersion() + " disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("freeze")){
			if(!sender.hasPermission("freeze.use")){
				sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
				return true;
			}
			if (args.length == 0){	// no players specified
				sender.sendMessage("Please specify a player.");
				return true;
			}
			@SuppressWarnings("deprecation")
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null){
				sender.sendMessage(ChatColor.RED + "Could not find player " + args[0] + ".");
				return true;
			}
			// have a player online
			if (frozen.contains(target.getName())){
				frozen.remove(target.getName());
				sender.sendMessage(ChatColor.GREEN + "Player " + target.getName() + " has been unfrozen.");
				return true;
			}
			frozen.add(target.getName());
			sender.sendMessage(ChatColor.GREEN + "Player " + target.getName() + " has been frozen!");
			return true;
		}
		
		return true;
	}

}

/*
 *	tutorial on YouTube 
 * 	youtube.com/watch?v=55UVkcM1Drs
 *  youtube.com/user/PogoStick29Dev
 * 	
 */