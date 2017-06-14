package mortuusterra.listeners;

import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import mortuusterra.timers.MeteorStrikeTimer;
import mortuusterra.Main;

public class BlockFormListener implements Listener {

	private final Main plugin;
	
	public BlockFormEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerDestroysBlock(EntityDamageByEntityEvent event) {
    		if (event.getDamager() instanceof FallingBlock && event.getEntity() instanceof Player
				&& event.getDamager().getCustomName().equals(MeteorStrikeTimer.meteorName)) {
			Player player = (Player) event.getEntity();
			event.setDamage(player.getHealth() * 0.9);
		}
	}
	
	@EventHandler
	public void playerDestroysBlock(EntityChangeBlockEvent event) {
    		if (event.getEntity() instanceof FallingBlock && event.getEntity().getCustomName().equals(MeteorStrikeTimer.meteorName)) {
			event.getBlock().setType(Material.IRON_ORE);
			event.setCancelled(true);
		}
	}
}
