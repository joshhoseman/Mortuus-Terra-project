package mortuusterra.listeners.world;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import mortuusterra.Main;
import mortuusterra.objects.supplydrops.SupplyDrops;

public class WorldListener implements Listener {
	
	private final Main plugin;

	private Long lastTimestamp;
	public Integer minecraftQuarterDaysPassed;
	public Integer minecraftFullDaysPassed;
	
	public WorldListener(Main plugin) {
		
		this.plugin = plugin;
		this.lastTimestamp = Long.valueOf(0);
		this.minecraftQuarterDaysPassed = 0;
		this.minecraftFullDaysPassed = 0;
		
	}

	@EventHandler(priority=EventPriority.LOW)
	public void playerDestroysSupplyDrop(BlockBreakEvent event) {
		
		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;
			
		}
		
		if (!(event.getBlock().getState().toString().contains("CraftChest"))) {
			
			return;
			
		}
		
		SupplyDrops supplyDrop = plugin.getSupplyDropManager().getSupplyDropByLocation(event.getBlock().getLocation());
		
		if (supplyDrop == null) {
			
			return;
			
		}
		
		supplyDrop.setIsEmpty(true);
		plugin.getSupplyDropManager().removeSupplyDrop(supplyDrop.getDropLocation());
		plugin.getLogger().info("Removing Supply Drop At: " + supplyDrop.getDropLocation().toString());		
		
	}

	public void deliverSupplyDrop(World world) {
				
		Random ran = new Random();
		
		double x = ran.nextInt(500);
		double y = 0.0;
		double z = ran.nextInt(500);
		
		Location dropLocation = new Location(world, x, y, z);
		dropLocation.setY(world.getHighestBlockYAt(dropLocation));
		
		dropLocation.getBlock().setType(Material.CHEST);
				
		Chest dropChest = (Chest) dropLocation.getBlock().getState();

		SupplyDrops supplyDrop = new SupplyDrops(plugin, dropChest, dropChest.getLocation(), dropChest.getBlockInventory());
		
		plugin.getSupplyDropManager().addSupplyDrop(supplyDrop);
		plugin.getLogger().info("Adding Supply Drop At: " + supplyDrop.getDropLocation().toString());

		for (Player player : world.getPlayers()) {
			
			player.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]");
			player.sendMessage(ChatColor.WHITE + "[Emergency Alert] " + ChatColor.GRAY + "Supply Drop Made at " + dropLocation.getX() + "," + dropLocation.getY() + "," + dropLocation.getZ());
			player.sendMessage(ChatColor.GRAY + "Use your compass to find the supply drop. Right-click to target the signal.");
			
		}

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
