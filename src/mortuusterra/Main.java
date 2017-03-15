package mortuusterra;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private Main plugin;
	

	private Logger logger = Logger.getLogger("Minecraft");

	public void onEnable() {
		logger.info("|---------|");
		// initiateManagers();
		// registerListeners();

		// saveDefaultConfig()

		setInstance(this);

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

	public Main getInstance() {
		return plugin;
	}

	public void setInstance(Main instance) {
		this.plugin = instance;
	}
}
