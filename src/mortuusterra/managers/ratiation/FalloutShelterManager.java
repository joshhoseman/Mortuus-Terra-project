package mortuusterra.managers.ratiation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.Location;

import mortuusterra.Main;
import mortuusterra.objects.custom.FalloutShelter;

public class FalloutShelterManager {
	
private final Main plugin;
	
	private List<FalloutShelter> falloutShelters;
	
	public FalloutShelterManager(Main plugin) {
		
		this.plugin = plugin;
		
		falloutShelters = new ArrayList<FalloutShelter>();
		
	}
	
	public FalloutShelter getFalloutShelterObjectByLocation(Location location) {
		
		FalloutShelter foundFalloutShelter = null;
		
		for (FalloutShelter falloutShelter : falloutShelters) {
			
			if (falloutShelter.getFalloutShelterLocation().equals(location)) {
				
				foundFalloutShelter = falloutShelter;
				
			}
			
		}
		
		return foundFalloutShelter;
		
	}

	public void addFalloutShelter(Location falloutShelterLocation) {
		
		falloutShelters.add(new FalloutShelter(falloutShelterLocation, true));

	}

	public void removeFalloutShelter(Location falloutShelterLocation) {
		
		FalloutShelter falloutShelterObject = getFalloutShelterObjectByLocation(falloutShelterLocation);

		falloutShelters.remove(falloutShelterObject);
		
	}
	
	public void saveFalloutSheltersToDisk() {
		
		try {

			File falloutSheltersFile = new File(plugin.getDataFolder() + File.separator + "fallout-shelters.txt");
			falloutSheltersFile.delete();
	
			falloutSheltersFile = new File(plugin.getDataFolder() + File.separator + "fallout-shelters.txt");
			falloutSheltersFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		plugin.getLogger().info("Saving Fallout Shelters To Disk");

		try {
			
			PrintWriter falloutSheltersFile = new PrintWriter(plugin.getDataFolder() + File.separator + "fallout-shelters.txt");
				
			if (falloutShelters.size() > 0) {

				for (FalloutShelter falloutShelter : falloutShelters) {
					
					String falloutShelterString = falloutShelter.getSerializedFalloutShelterObject();
	
					falloutSheltersFile.println(falloutShelterString);
					
				}

			}
			
			falloutSheltersFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadFalloutSheltersFromDisk() {
		
		ensureFalloutSheltersFileExists();
		
		plugin.getLogger().info("Loading Fallout Shelters From Disk");

		try {

			File falloutSheltersFile = new File(plugin.getDataFolder() + File.separator + "fallout-shelters.txt");
			Scanner inputFile = new Scanner(falloutSheltersFile);
			
			while (inputFile.hasNextLine()) {
	
				String falloutShelterString = inputFile.nextLine();
				String[] falloutShelterArray = falloutShelterString.split("~");

				Location falloutShelterLocation = new Location(plugin.getServer().getWorld(falloutShelterArray[0]), Double.valueOf(falloutShelterArray[1]), Double.valueOf(falloutShelterArray[2]), Double.valueOf(falloutShelterArray[3]));
	
				falloutShelters.add(new FalloutShelter(falloutShelterLocation, false));
					
			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void ensureFalloutSheltersFileExists() {
		
		plugin.getLogger().info("Creating Fallout Shelters File");

		// create file if not exists
		try {
			
			File falloutSheltersFile = new File(plugin.getDataFolder() + File.separator + "fallout-shelters.txt");
	
			if (falloutSheltersFile.exists() == false) {
				
				falloutSheltersFile = new File(plugin.getDataFolder() + File.separator + "fallout-shelters.txt");
				falloutSheltersFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}

}
