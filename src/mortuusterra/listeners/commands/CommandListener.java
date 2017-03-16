package mortuusterra.listeners.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class CommandListener {
	
	private mortuusterra.Main plugin;

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("effects")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("You must be a player to use this cammand!");
				return false;
			}
			
			//mortuusterra.listeners.player.BiomeListener.getBiomCheck();
		}
		return true;
	}

}
