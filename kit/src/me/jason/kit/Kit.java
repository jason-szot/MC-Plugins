package me.jason.kit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Kit extends JavaPlugin{
	public static Economy econ = null;
	
	public void onEnable(){
		if (!setupEconomy() ) {
            Bukkit.getServer().getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
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
			// give the player some items, cost 10 currency
			@SuppressWarnings("deprecation")
			EconomyResponse r = econ.withdrawPlayer(p.getName(), 10);
			if (r.transactionSuccess()){
				PlayerInventory pi = p.getInventory();
				pi.addItem(new ItemStack(Material.STONE_SWORD, 1));
				pi.addItem(new ItemStack(Material.STONE_PICKAXE, 1));
				pi.addItem(new ItemStack(Material.APPLE, 5));
				p.sendMessage(ChatColor.GREEN + "You got your kit!");
				return true;
			}else{
				p.sendMessage(ChatColor.RED + "You cannot get a kit at this time.");
				return true;
			}
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

	
	// setup econ from vault api page
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

}

/*
 *	tutorial on YouTube 
 * 	youtube.com/watch?v=NVNSfUjXJow - part 1 - basic inventory stuff
 *  youtube.com/watch?v=GeIkAhdavMg - part 2 - hooking into another plugin for economy (using Vault)
 *  youtube.com/user/PogoStick29Dev
 * 	
 */