package me.jason.medic;

import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.md_5.bungee.api.ChatColor;

public class MedicListener implements Listener{

	@EventHandler
	public void onSignChange(SignChangeEvent event){
		if (event.getLine(0).equalsIgnoreCase("[heal]")){
			event.setLine(0, "§3[Heal]");
			event.setLine(1, "§3click here");
			event.setLine(2, "§3to get healed");
		}
		if (event.getLine(0).equalsIgnoreCase("[feed]")){
			event.setLine(0, "§3[Feed]");
			event.setLine(1, "§3click here");
			event.setLine(2, "§3to get fed");
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK)){
			if (event.getClickedBlock().getState() instanceof Sign){
				Sign s = (Sign) event.getClickedBlock().getState();
				if (s.getLine(0).equalsIgnoreCase("§3[Heal]")){
					event.getPlayer().setHealth(20);
					event.getPlayer().sendMessage(ChatColor.GREEN + "You were healed!");
				}
				if (s.getLine(0).equalsIgnoreCase("§3[Feed]")){
					event.getPlayer().setFoodLevel(20);
					event.getPlayer().sendMessage(ChatColor.GREEN + "You were fed!");
				}
			}
		}
	}
}
