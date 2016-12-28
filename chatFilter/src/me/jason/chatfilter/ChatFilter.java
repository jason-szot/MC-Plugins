package me.jason.chatfilter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class ChatFilter extends JavaPlugin implements Listener{
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event){
		for (String word : event.getMessage().split(" ")){
			if ( getConfig().getStringList("badwords").contains(word)){
				event.setCancelled(true);
				event.getPlayer().sendMessage(ChatColor.RED + "Dont curse!");
			}
		}
	}
	
	public void onEnable(){
		getConfig().options().copyDefaults(true);
		saveConfig();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getLogger().info("ChatFilter v" + this.getDescription().getVersion() + " enabled.");
	}
	
	public void onDisable(){
		Bukkit.getServer().getLogger().info("ChatFilter v" + this.getDescription().getVersion() + " disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		
		return true;
	}

}

/*
 *	tutorial on YouTube 
 * 	youtube.com/watch?v=rlAuOharALc
 *  youtube.com/user/PogoStick29Dev
 *  
 *  lists in a config file
 *  chat monitoring / filtering
 * 	
 */