package mortuusterra.listeners;

import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import mortuusterra.timers.MeteorStrikeTimer;
import mortuusterra.Main;

public class BlockFormListener implements Listener {

	private final Main plugin;
	
	public BlockFormEvent(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void playerDestroysBlock(EntityChangeBlockEvent event) {
    		if (event.getEntity() instanceof FallingBlock && event.getEntity().getCustomName().equals(MeteorStrikeTimer.meteorName)) {
			event.getBlock().setType(Material.IRON_ORE);
			event.setCancelled(true);
		}
	}
}
