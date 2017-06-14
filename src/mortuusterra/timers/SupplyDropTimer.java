package mortuusterra.timers;

import mortuusterra.Main;

import org.bukkit.scheduler.BukkitRunnable;

public class SupplyDropTimer extends BukkitRunnable {
	

	Main plugin;

	public SupplyDropTimer(Main plugin) {
		this.plugin = plugin;
		run();

	}

	public void run() {
		plugin.getWorldListener().deliverSupplyDrop();
		
	}

}
