package mortuusterra.listeners.world;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import mortuusterra.Main;

public class ChunkListener implements Listener {
	
private final Main plugin;
	
	public ChunkListener(Main plugin) {
		
		this.plugin = plugin;
				
	}

	@EventHandler(priority=EventPriority.LOW)
	public void chunkLoad(ChunkLoadEvent event) {
		
		if (plugin.getConfigManager().isWorldEnabled(event.getWorld().getName()) == false) {
			
			return;
			
		}
		
		if (event.isNewChunk() == false) {
			
			return;
			
		}
		
		Chunk chunk = event.getChunk();
		int x = chunk.getX() * 16;
		int z = chunk.getZ() * 16;
		int y = chunk.getWorld().getHighestBlockYAt(x + 8, z + 2);

		
		Location eventLocation = new Location(event.getWorld(), x + 8, y, z + 8);

		if (plugin.getConfigManager().generateFalloutSheltersEnabled == true) {

			if (Math.random() < (plugin.getConfigManager().generateFalloutSheltersChance / 10)) {
				
				placeFalloutShelter(eventLocation);
				
			}
			
		}
		
		if (plugin.getConfigManager().generateCratersEnabled == true) {

			if (Math.random() < (plugin.getConfigManager().generateCratersChance / 10)) {
				
				placeCrater(eventLocation);
				
			}

		}

	}

	public void placeFalloutShelter(Location falloutShelterLocation) {
		
		int falloutShelterLocationY = falloutShelterLocation.getWorld().getHighestBlockYAt(falloutShelterLocation);
		
		falloutShelterLocation.setY(falloutShelterLocationY);
	
		plugin.getFalloutShelterManager().addFalloutShelter(falloutShelterLocation);
		
	}
	
	public void placeCrater(Location craterLocation) {
		
		int craterLocationY = craterLocation.getWorld().getHighestBlockYAt(craterLocation);
		
		craterLocation.setY(craterLocationY);
		
		// craterLocation.getWorld().createExplosion(craterLocation, 1);
		
		plugin.getCraterManager().addCrater(craterLocation);
		
	}
	

}
