package me.jason.kit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Kit extends JavaPlugin{
	public void onEnable(){
		Bukkit.getServer().getLogger().info("kit v" + this.getDescription().getVersion() + " enabled.");
	}
	
	public void onDisable(){
		Bukkit.getServer().getLogger().info("kit v" + this.getDescription().getVersion() + " disabled.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (!(sender instanceof Player)){
			sender.sendMessage(ChatColor.RED + "only players can get kits!");
			return true;
		}
		
		Player p = (Player) sender;
		
		if (cmd.getName().equalsIgnoreCase("kit")){
			// give the player some items
			PlayerInventory pi = p.getInventory();
			pi.addItem(new ItemStack(Material.STONE_SWORD, 1));
			pi.addItem(new ItemStack(Material.STONE_PICKAXE, 1));
			pi.addItem(new ItemStack(Material.APPLE, 5));
			p.sendMessage(ChatColor.GREEN + "You got your kit!");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("ci")){
			// clear the player's inventory
			PlayerInventory pi = p.getInventory();
			pi.clear();
			p.sendMessage(ChatColor.GREEN + "Inventory cleared!");
			return true;
		}
		
		return true;
	}

}

/*
 *	tutorial on YouTube 
 * 	youtube.com/watch?v=NVNSfUjXJow
 *  youtube.com/user/PogoStick29Dev
 * 	
 */