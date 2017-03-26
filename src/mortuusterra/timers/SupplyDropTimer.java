package mortuusterra.timers;

import mortuusterra.Main;

public class SupplyDropTimer implements Runnable {
	

	Main plugin;

	public SupplyDropTimer(Main plugin) {
		this.plugin = plugin;

	}

	public void run() {
		plugin.getWorldListener().deliverSupplyDrop();
		
	}

}
