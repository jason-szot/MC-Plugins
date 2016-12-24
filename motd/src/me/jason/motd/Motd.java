package me.jason.motd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Motd extends JavaPlugin implements Listener{
	
	public void onEnable(){
		getConfig().options().copyDefaults(true);
		saveConfig();
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		Bukkit.getServer().getLogger().info("MOTD v" + this.getDescription().getVersion() + " enabled.");
	}
	
	public void onDisable(){
		Bukkit.getServer().getLogger().info("MOTD v" + this.getDescription().getVersion() + " disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (cmd.getName().equalsIgnoreCase("motd")){
			sender.sendMessage(ChatColor.GREEN + getConfig().getString("message"));
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("setmotd")){
			if(args.length == 0){
				sender.sendMessage(ChatColor.RED + "Please provide a message of the day");
				return true;
			}
			StringBuilder str = new StringBuilder();
			for(int i = 0; i < args.length; i++){
				str.append(args[i] + " ");
			}
			String motd = str.toString();
			getConfig().set("message",  motd);
			saveConfig();
			sender.sendMessage(ChatColor.GREEN + "MOTD: " + motd);
			return true;
		}
		
		return true;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GREEN + getConfig().getString("message"));
	}

}

/*
 *	tutorial on YouTube 
 * 	youtube.com/watch?v=HlrMN06wW7U - part 1
 *  youtube.com/watch?v=SBvrpmNDr74 - part 2
 *  youtube.com/user/PogoStick29Dev
 * 	
 */