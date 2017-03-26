package mortuusterra.listeners.radiation;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import mortuusterra.Main;
import mortuusterra.objects.custom.radiation.GECKObject;

public class GECKListener implements Listener {
	
private final Main plugin;
	
	public GECKListener(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerChangesGECKPowerStatus(PlayerInteractEvent event) {
		
		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		if (event.hasBlock() && event.getClickedBlock().getType().equals(Material.LEVER) && event.getClickedBlock().getRelative(BlockFace.DOWN).getType().equals(Material.SPONGE)) {
			
			Block spongeBlock = event.getClickedBlock().getRelative(BlockFace.DOWN);
			
			if (plugin.getGECKManager().getGECKObjectByLocation(spongeBlock.getLocation()) != null) {
				
				// this is a geck.
				GECKObject geck = plugin.getGECKManager().getGECKObjectByLocation(spongeBlock.getLocation());
				
				if (geck.isPowered() == true) {
					
					// turning it off
					geck.turnOff();
					
				} else {
					
					// turning it on
					geck.turnOn();
					
				}
				
			}
			
		}
		
	}

}
