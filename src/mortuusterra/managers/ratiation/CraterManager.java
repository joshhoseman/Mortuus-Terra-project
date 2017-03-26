package mortuusterra.managers.ratiation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.bukkit.Location;

import mortuusterra.Main;
import mortuusterra.objects.custom.CraterObject;

public class CraterManager {
	
private final Main plugin;
	
	private List<CraterObject> craters;
	
	public CraterManager(Main plugin) {
		
		this.plugin = plugin;
		
		craters = new ArrayList<CraterObject>();
		
	}
	
	public List<CraterObject> getCraters() {
		
		return craters;
		
	}
	
	public CraterObject getCraterObjectByLocation(Location location) {
		
		CraterObject foundCrater = null;
		
		for (CraterObject crater : craters) {
			
			if (crater.getCraterLocation().equals(location)) {
				
				foundCrater = crater;
				
			}
			
		}
		
		return foundCrater;
		
	}

	public void addCrater(Location craterLocation) {
		
		Date now = new Date();
		Long craterTime = now.getTime();

		craters.add(new CraterObject(craterLocation, craterTime));
		
	}

	public void removeCrater(Location craterLocation) {
		
		CraterObject craterObject = getCraterObjectByLocation(craterLocation);

		craters.remove(craterObject);
		
	}
	
	public void saveCratersToDisk() {
		
		try {

			File cratersFile = new File(plugin.getDataFolder() + File.separator + "craters.txt");
			cratersFile.delete();
	
			cratersFile = new File(plugin.getDataFolder() + File.separator + "craters.txt");
			cratersFile.createNewFile();
		
		} catch (IOException e) {
		
			e.printStackTrace();
			
		}
		
		plugin.getLogger().info("Saving Craters To Disk");

		try {
			
			PrintWriter cratersFile = new PrintWriter(plugin.getDataFolder() + File.separator + "craters.txt");
				
			if (craters.size() > 0) {

				for (CraterObject crater : craters) {
					
					String craterString = crater.getSerializedCraterObject();
	
					cratersFile.println(craterString);
					
				}

			}
			
			cratersFile.close();          
			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void loadCratersFromDisk() {
		
		ensureCratersFileExists();
		
		plugin.getLogger().info("Loading Craters From Disk");

		try {

			File cratersFile = new File(plugin.getDataFolder() + File.separator + "craters.txt");
			Scanner inputFile = new Scanner(cratersFile);
			
			while (inputFile.hasNextLine()) {
	
				String craterString = inputFile.nextLine();
				String[] craterArray = craterString.split("~");

				Location craterLocation = new Location(plugin.getServer().getWorld(craterArray[1]), Double.valueOf(craterArray[2]), Double.valueOf(craterArray[3]), Double.valueOf(craterArray[4]));
	
				addCrater(craterLocation);
					
			}
			
			inputFile.close();
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
	
		}
		
	}
	
	public void ensureCratersFileExists() {
		
		plugin.getLogger().info("Creating Craters File");

		// create file if not exists
		try {
			
			File cratersFile = new File(plugin.getDataFolder() + File.separator + "craters.txt");
	
			if (cratersFile.exists() == false) {
				
				cratersFile = new File(plugin.getDataFolder() + File.separator + "craters.txt");
				cratersFile.createNewFile();
				
			}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}	
		
	}

}
