package mortuusterra.managers;

import mortuusterra.Main;

public class FileManager {
	
	Main plugin;

	public FileManager(Main plugin) {
		this.plugin = plugin;

	}
	
public void loadFiles() {
	
		
		plugin.getSupplyDropManager().loadData();
		//plugin.getProtectionManager().loadProtectionsFromDisk();
		//plugin.getPlayerManager().loadPlayersFromDisk();
		//plugin.getCraterManager().loadCratersFromDisk();
		//plugin.getFalloutShelterManager().loadFalloutSheltersFromDisk();
		//plugin.getGECKManager().loadGECKsFromDisk();
		
	}
	
	public void saveFiles() {
		
		plugin.getSupplyDropManager().saveData();
		//plugin.getProtectionManager().saveProtectionsToDisk();
		//plugin.getPlayerManager().savePlayersToDisk();
		//plugin.getCraterManager().saveCratersToDisk();
		//plugin.getFalloutShelterManager().saveFalloutSheltersToDisk();
		//plugin.getGECKManager().saveGECKsToDisk();
		
	}

}
