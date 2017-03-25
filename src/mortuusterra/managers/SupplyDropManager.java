package mortuusterra.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bukkit.Location;
import org.bukkit.block.Chest;

import mortuusterra.Main;
import mortuusterra.objects.supplydrops.SupplyDrops;

public class SupplyDropManager {

	Main plugin;
	
	private List<SupplyDrops> supplyDrops;

	public SupplyDropManager(Main plugin) {
		this.plugin = plugin;

		supplyDrops = new ArrayList<SupplyDrops>();

		initializeData();
	}

	public void initializeData() {

		try {

			File myFile = new File(plugin.getDataFolder() + File.separator + "supply-drops.txt");

			if (myFile.exists() == false) {

				myFile = new File(plugin.getDataFolder() + File.separator + "supply-drops.txt");
				myFile.createNewFile();

			}

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public void loadData() {

		plugin.getLogger().info("[Mortuus Terra] Loading Supply Drops");

		try {

			File dataFile = new File(plugin.getDataFolder() + File.separator + "supply-drops.txt");
			Scanner inputFile = new Scanner(dataFile);

			while (inputFile.hasNextLine()) {

				String dataString = inputFile.nextLine();
				String[] dataArray = dataString.split("~");

				Location dataLocation = new Location(plugin.getServer().getWorld(dataArray[0]),
						Double.valueOf(dataArray[1]), Double.valueOf(dataArray[2]), Double.valueOf(dataArray[3]));

				Chest chestBlock = (Chest) dataLocation.getBlock().getState();

				addSupplyDrop(new SupplyDrops(plugin, chestBlock, dataLocation, chestBlock.getBlockInventory()));

			}

			inputFile.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

	}

	public void saveData() {

		plugin.getLogger().info("[Mortuus Terra] Saving Supply Drops");

		try {

			File dataFile = new File(plugin.getDataFolder() + File.separator + "supply-drops.txt");
			dataFile.delete();

			dataFile = new File(plugin.getDataFolder() + File.separator + "supply-drops.txt");
			dataFile.createNewFile();

		} catch (IOException e) {

			e.printStackTrace();

		}

		try {

			PrintWriter dataFile = new PrintWriter(plugin.getDataFolder() + File.separator + "supply-drops.txt");

			if (supplyDrops.size() > 0) {

				for (SupplyDrops supplyDrop : supplyDrops) {

					String dataString = supplyDrop.getDropLocation().getWorld().getName() + "~"
							+ supplyDrop.getDropLocation().getX() + "~" + supplyDrop.getDropLocation().getY() + "~"
							+ supplyDrop.getDropLocation().getZ();

					dataFile.println(dataString);

				}

			}

			dataFile.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		}

	}

	public SupplyDrops getSupplyDropByLocation(Location location) {

		SupplyDrops foundSupplyDrop = null;

		for (SupplyDrops supplyDrop : supplyDrops) {

			if (supplyDrop.getDropLocation().equals(location)) {

				foundSupplyDrop = supplyDrop;

				break;

			}

		}

		return foundSupplyDrop;

	}

	public SupplyDrops getNearestSupplyDrop(Location location) {

		Double shortestDistance = -1.0;
		SupplyDrops closestSupplyDrop = null;

		for (SupplyDrops supplyDrop : supplyDrops) {

			if ((supplyDrop.getDropLocation().distance(location) < shortestDistance || shortestDistance == -1.0)
					&& supplyDrop.isEmpty() == false) {

				closestSupplyDrop = supplyDrop;

			}

		}

		return closestSupplyDrop;

	}

	public void checkSupplyDropContents(Location supplyDropLocation) {

		boolean supplyDropIsEmpty = true;

		SupplyDrops supplyDrop = getSupplyDropByLocation(supplyDropLocation);

		if (supplyDrop == null) {

			return;

		}

		for (int i = 0; i < supplyDrop.getDropInventory().getSize(); i++) {

			if (supplyDrop.getDropInventory().getItem(i) == null) {
			} else {
				supplyDropIsEmpty = false;
			}

		}

		if (supplyDropIsEmpty == true) {

			supplyDrops.remove(supplyDrop);

		}

	}

	public void addSupplyDrop(SupplyDrops supplyDrop) {

		supplyDrops.add(supplyDrop);

	}

	public void removeSupplyDrop(Location location) {

		SupplyDrops supplyDrop = getSupplyDropByLocation(location);

		supplyDrops.remove(supplyDrop);

	}

}
