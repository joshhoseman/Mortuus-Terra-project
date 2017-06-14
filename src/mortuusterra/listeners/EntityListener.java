package mortuusterra.listeners;

import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import mortuusterra.timers.MeteorStrikeTimer;
import mortuusterra.Main;

public class EntityListener implements Listener {
	public EntityListener(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("unused")
	private final Main plugin;
	
	
	
	@EventHandler
	public void entityDamageEntityEvent(EntityDamageByEntityEvent event) {
    		if (event.getDamager() instanceof FallingBlock && event.getEntity() instanceof Player
				&& event.getDamager().getCustomName().equals(MeteorStrikeTimer.meteorName)) {
			Player player = (Player) event.getEntity();
			event.setDamage(player.getHealth() * 0.9);
		}
	}
	
	@EventHandler
	public void entityChangeBlockEvent(EntityChangeBlockEvent event) {
    		if (event.getEntity() instanceof FallingBlock && event.getEntity().getCustomName().equals(MeteorStrikeTimer.meteorName)) {
			event.getBlock().setType(Material.IRON_ORE);
			event.setCancelled(true);
		}
	}
}
