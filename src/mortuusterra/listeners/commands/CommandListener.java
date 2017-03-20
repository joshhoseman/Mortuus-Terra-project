package mortuusterra.listeners.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener {

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("effects")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("You must be a player to use this cammand!");
				return false;
			}
		}
		return true;
	}

}
