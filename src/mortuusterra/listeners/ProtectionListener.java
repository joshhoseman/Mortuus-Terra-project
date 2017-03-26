package mortuusterra.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import mortuusterra.Main;

public class ProtectionListener implements Listener {

	private final Main plugin;
	
	public ProtectionListener(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
	@EventHandler(priority=EventPriority.LOW)
	public void playerDestroysBlock(BlockBreakEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		if (plugin.getProtectionManager().locationIsProtected(event.getBlock().getLocation()) == true) {
			
			plugin.getProtectionManager().informPlayer(event.getPlayer());
			
			if (event.getPlayer().isOp() == false && !(plugin.getPermissionsManager().playerHasPermissions(event.getPlayer(), "mt.admin")))  {

				event.setCancelled(true);
				
			}
			
		}
		
	}

	@EventHandler(priority=EventPriority.LOW)
	public void playerPlacesBlock(BlockPlaceEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {
			
			return;
			
		}

		if (plugin.getProtectionManager().locationIsProtected(event.getBlock().getLocation()) == true) {
			
			plugin.getProtectionManager().informPlayer(event.getPlayer());
			
			if (event.getPlayer().isOp() == false && !(plugin.getPermissionsManager().playerHasPermissions(event.getPlayer(), "mt.admin")))  {

				event.setCancelled(true);
				
			}
			
		}
		
	}
	
}