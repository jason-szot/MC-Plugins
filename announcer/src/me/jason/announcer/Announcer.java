package me.jason.announcer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import net.md_5.bungee.api.ChatColor;

public class Announcer extends JavaPlugin{

	public void onEnable(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			public void run(){
				Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "This is an announcement.");
			}
		}, 20, 6000);
	}
}
