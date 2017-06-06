package mortuusterra.listeners.world;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;

import mortuusterra.Main;
import mortuusterra.managers.supplydrops.SupplyDropsManager;

public class WorldListener implements Listener {

	private final Main plugin;

	protected Long lastTimestamp;
	public Integer minecraftQuarterDaysPassed;
	public Integer minecraftFullDaysPassed;

	public WorldListener(Main plugin) {

		this.plugin = plugin;
		this.lastTimestamp = Long.valueOf(0);
		this.minecraftQuarterDaysPassed = 0;
		this.minecraftFullDaysPassed = 0;

	}

	@EventHandler(priority = EventPriority.LOW)
	public void playerDestroysSupplyDrop(BlockBreakEvent event) {
		Chest chest = plugin.getSupplyDropsManager().chest;

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;

		} else if (!(event.getBlock().getType().equals(chest))) {

			return;

		}

		SupplyDropsManager supplyDrop = plugin.getSupplyDropsManager();

		supplyDrop.setIsEmpty(true);

	}

	public void deliverSupplyDrop(World world) {
		Location droplocation = plugin.getSupplyDropsManager().getdropLocation();
		Inventory ChestInv = plugin.getSupplyDropsManager().getDropInventory();

		plugin.getSupplyDropsManager().setDropLocation(droplocation, world);

		plugin.getSupplyDropsManager().spawnSupplyDrop(ChestInv);

		plugin.getSupplyDropsManager().placeDropSign();

		plugin.getSupplyDropsManager().setDropInventory(ChestInv);

		plugin.getSupplyDropsManager().setIsEmpty(false);
		
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "End of Supply Drop Creation!");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]");
		
		plugin.getSupplyDropsManager().soundAlarm();
	}

	public void minecraftDayPasses() {

		minecraftQuarterDaysPassed = 0;

	}

	public void deliverSupplyDrop() {
		if (plugin.getConfigManager().supplyDropsEnabled == true && plugin.getConfigManager().supplyDropsChance > 0) {
			for (World world : plugin.getServer().getWorlds()) {
				if ((plugin.getConfigManager().isWorldEnabled(world.getName()) == true)) {
					double ran = Math.random();
					if (ran < plugin.getConfigManager().supplyDropsChance) {
						deliverSupplyDrop(world);
					}
				}
			}
		}

	}

}
