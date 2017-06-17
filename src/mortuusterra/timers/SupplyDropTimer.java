package mortuusterra.timers;

import mortuusterra.Main;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class SupplyDropTimer extends BukkitRunnable {
	

	Main plugin;

	public SupplyDropTimer(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test text A");
		run();

	}

	public void run() {
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test text B");
		plugin.getWorldListener().deliverSupplyDrop();
		
	}

}
