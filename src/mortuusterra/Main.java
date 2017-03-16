package mortuusterra;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.listeners.player.BiomeListener;

public class Main extends JavaPlugin {
	private PluginManager plugin = getServer().getPluginManager();
	

	private Logger logger = Logger.getLogger("Minecraft");

	public void onEnable() {
		logger.info("|---------|");
		// initiateManagers();
		 registerListeners();

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
		(plugin).registerEvents(new BiomeListener(), this);
	}
}
