package mortuusterra.managers.files;

import mortuusterra.Main;

public class FileManager {

	Main plugin;

	public FileManager(Main plugin) {
		this.plugin = plugin;

	}

	public void loadFiles() {

		plugin.getProtectionManager().loadProtectionsFromDisk();
		plugin.getPlayerManager().loadPlayersFromDisk();
		plugin.getCraterManager().loadCratersFromDisk();
		plugin.getFalloutShelterManager().loadFalloutSheltersFromDisk();
		plugin.getGECKManager().loadGECKsFromDisk();

	}

	public void saveFiles() {

		plugin.getProtectionManager().saveProtectionsToDisk();
		plugin.getPlayerManager().savePlayersToDisk();
		plugin.getCraterManager().saveCratersToDisk();
		plugin.getFalloutShelterManager().saveFalloutSheltersToDisk();
		plugin.getGECKManager().saveGECKsToDisk();

	}

}
