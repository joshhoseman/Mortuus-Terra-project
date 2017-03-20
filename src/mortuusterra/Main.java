package mortuusterra;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.timers.RadiationTimer;

public class Main extends JavaPlugin {
	@SuppressWarnings("unused")
	private PluginManager plugin = getServer().getPluginManager();
	

	private Logger logger = Logger.getLogger("Minecraft");

	public void onEnable() {
		logger.info("|---------|");
		// initiateManagers();
		 registerListeners();
		 
		 getServer().getScheduler().scheduleSyncRepeatingTask(this, new RadiationTimer(this), 0L, 20L);

		// saveDefaultConfig()

		mkdir();

		logger.info("MortuusTerra has been enabled");
		
		
		
		logger.info("|---------|");

	}

	private void mkdir() {
		
		try {
		getDataFolder().mkdir();
		
	}
		catch (Exception e){
			e.printStackTrace();
			
		}
	}
	
	private void registerListeners() {

	}
}
