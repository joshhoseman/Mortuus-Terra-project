package mortuusterra.listeners;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import mortuusterra.timers.MeteorStrikeTimer;
import mortuusterra.Main;

@SuppressWarnings("unused")
public class EntityListener implements Listener {
	
	private final Main plugin;
	
	public EntityListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void entityDamageEntityEvent(EntityDamageByEntityEvent event) {
    		if (event.getDamager() instanceof FallingBlock && event.getEntity() instanceof Player
				&& event.getDamager().getCustomName().equals(MeteorStrikeTimer.meteorName)) {
			Player player = (Player) event.getEntity();
			Map<String, ProtectedRegion> regions = WGBukkit.getRegionManager(player.getWorld()).getRegions();
			boolean inRegion = false;

			Location local = player.getLocation();
			int x = ((int) local.getX());
			int y = ((int) local.getY());
			int z = ((int) local.getZ());

			for (ProtectedRegion region : regions.values()) {
				if (region.contains(x, y, z)) {
					inRegion = true;
				}
			}
			if (!inRegion) {
				event.setDamage(player.getHealth() * 0.9);
			} else {
				event.setDamage(0);	
			}
		}
	}
	
	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent event) {
		if (event.getEntity() instanceof FallingBlock && event.getEntity().getCustomName().equals("METEOR")) {
			Map<String, ProtectedRegion> regions = WGBukkit.getRegionManager(event.getEntity().getWorld()).getRegions();
			boolean inRegion = false;

			Location local = event.getEntity().getLocation();
			int x = ((int) local.getX());
			int y = ((int) local.getY());
			int z = ((int) local.getZ());

			for (ProtectedRegion region : regions.values()) {
				if (region.contains(x, y, z)) {
					inRegion = true;
				}
			}
			if (!inRegion) {
				event.getBlock().setType(Material.IRON_ORE);
			} else {
				event.getBlock().setType(Material.AIR);	
			}
			event.setCancelled(true);
		}
	}
}
