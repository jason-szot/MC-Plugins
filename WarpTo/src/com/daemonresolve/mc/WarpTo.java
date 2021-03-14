package com.daemonresolve.mc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.configuration.*;


public class WarpTo extends JavaPlugin{
	
	public void onEnable(){
		Bukkit.getServer().getLogger().info("WarpTo v" + this.getDescription().getVersion() + " enabled.");
	}
	
	public void onDisable(){
		Bukkit.getServer().getLogger().info("WarpTo v" + this.getDescription().getVersion() + " disabled.");
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (sender instanceof Player){
			Player p = (Player) sender;
			String name = p.getName();
			// setwarp command - usage /setwarp <warp name>
			if (cmd.getName().equalsIgnoreCase("setwarp")){
				// check for arguments
				if (args.length == 0){
					sender.sendMessage(ChatColor.RED + "No warp point named. Usage: /setwarp <warp name>");
					return true;
				}
				getConfig().set("warp." + name + "." + args[0] + ".world", p.getLocation().getWorld().getName());
				getConfig().set("warp." + name + "." + args[0] + ".x", p.getLocation().getX());
				getConfig().set("warp." + name + "." + args[0] + ".y", p.getLocation().getY());
				getConfig().set("warp." + name + "." + args[0] + ".z", p.getLocation().getZ());
				getConfig().set("warp." + name + "." + args[0] + ".pitch", p.getLocation().getPitch());
				getConfig().set("warp." + name + "." + args[0] + ".yaw", p.getLocation().getYaw());
				saveConfig();
				p.sendMessage(ChatColor.GREEN + "warp " + args[0] + " is set");
			}
			
			// warp command - usage /warp <warp name>
			if (cmd.getName().equalsIgnoreCase("warp")){
				// check for arguments
				if (args.length == 0){
					sender.sendMessage(ChatColor.RED + "No warp point named. Usage: /warp <warp name>");
					return true;
				}
				
				// null check
				if (getConfig().get("warp." + name + "." + args[0]) == null){
					sender.sendMessage(ChatColor.RED + "That warp does not exist. Try /listwarps to see what warps you have.");
				}else{
					World world = p.getWorld();
					double x = getConfig().getDouble("warp." + name + "." + args[0] + ".x");
					double y = getConfig().getDouble("warp." + name + "." + args[0] + ".y");
					double z = getConfig().getDouble("warp." + name + "." + args[0] + ".z");
					float pitch = (float) getConfig().getDouble("warp." + name + "." + args[0] + ".pitch");
					float yaw = (float) getConfig().getDouble("warp." + name + "." + args[0] + ".yaw");
					Location warpto = new Location(world, x, y, z, yaw, pitch);
					p.teleport(warpto);
					return true;
					
				}
			}
			
			// listwarps command - usage /listwarps - should send a list of warps to the player
			if (cmd.getName().equalsIgnoreCase("listwarps")){
				
				// null check
				if (getConfig().getString("warp." + name) == null){
					sender.sendMessage(ChatColor.AQUA + "No warps currently available");
					return true;
				}
				ConfigurationSection warps = (ConfigurationSection) getConfig().getKeys(false);
				warps.getString("warp." + name);
				sender.sendMessage((ChatColor.AQUA + "Available Warps: " + String.join(", ", (CharSequence) warps)));
				return true;
			}
			
			
		}else{
			sender.sendMessage(ChatColor.RED + "This command is for players only");
			return true;
		}
		
		return true;
	}

}
