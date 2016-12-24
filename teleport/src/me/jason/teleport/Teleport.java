package me.jason.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Teleport extends JavaPlugin{
	
	public void onEnable(){
		Bukkit.getServer().getLogger().info("teleport v" + this.getDescription().getVersion() + " enabled.");
	}
	
	public void onDisable(){
		Bukkit.getServer().getLogger().info("teleport v" + this.getDescription().getVersion() + " disabled.");
	}
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (cmd.getName().equalsIgnoreCase("tp")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "This command is for players only");
				return true;
			}
			
			Player p = (Player) sender;
			if (args.length == 0){
				p.sendMessage(ChatColor.RED + "Please specify a player");
				return true;
			}
			Player target = Bukkit.getServer().getPlayer(args[0]);
			if (target == null){
				p.sendMessage(ChatColor.RED + "could not find player " + args[0]);
				return true;
			}
			p.teleport(target);
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("setspawn")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "This command is for players only");
				return true;
			}
			Player p = (Player) sender;
			getConfig().set("spawn.world", p.getLocation().getWorld().getName());
			getConfig().set("spawn.x", p.getLocation().getX());
			getConfig().set("spawn.y", p.getLocation().getY());
			getConfig().set("spawn.z", p.getLocation().getZ());
			getConfig().set("spawn.pitch", p.getLocation().getPitch());
			getConfig().set("spawn.yaw", p.getLocation().getYaw());
			saveConfig();
			p.sendMessage(ChatColor.GREEN + "spawn is set");
			return true;
		}
		
		if(cmd.getName().equalsIgnoreCase("spawn")){
			if(!(sender instanceof Player)){
				sender.sendMessage(ChatColor.RED + "This command is for players only");
				return true;
			}
			Player p = (Player) sender;
			if (getConfig().getConfigurationSection("spawn") == null){
				p.sendMessage(ChatColor.RED + "The spawn has not yet been set");
				return true;
			}
			World w = Bukkit.getServer().getWorld(getConfig().getString("spawn.world"));
			double x = getConfig().getDouble("spawn.x");
			double y = getConfig().getDouble("spawn.y");
			double z = getConfig().getDouble("spawn.z");
			float pitch = (float) getConfig().getDouble("spawn.pitch");
			float yaw = (float) getConfig().getDouble("spawn.yaw");
			p.teleport(new Location(w, x, y, z, yaw, pitch));
			p.sendMessage(ChatColor.GREEN + "welcome to spawn!");
			return true;
		}
		
		return true;
	}

}

/*
 *	tutorial on YouTube 
 * 	youtube.com/watch?v=kePU1t-CiHo
 *  youtube.com/user/PogoStick29Dev
 * 	
 */