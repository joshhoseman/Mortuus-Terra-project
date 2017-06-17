package mortuusterra.managers.supplydrops;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import mortuusterra.Main;

public class SupplyDropsManager {

	public SupplyDropsManager(Main plugin) {
		this.plugin = plugin;
	}

	Main plugin;
	World world;
	public Chest chest;
	Random ran = new Random();
	public int x, y, z;
	private Inventory dropInventory;
	private Location dropLocation;
	private boolean isEmpty;

	public static ItemStack Inv; // set the actual Inv in SupplyDropInvManager

	// Step One
	public void setDropLocation(Location droplocation, World world) {
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "Beginning of Supply Drop Creation!");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "...");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "Setting Drop Location");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "...");
		this.dropLocation = droplocation;

		this.x = ran.nextInt();
		this.z = ran.nextInt();
		this.y = world.getHighestBlockYAt(x, z);
		world = this.world;

		droplocation = new Location(world, x, y, z);
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "Location set at "+ x + " " + y+ " " + z+"  ");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "...");
	}

	// Step Two
	public void spawnSupplyDrop(Inventory ChestInv) {
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "Spawning Supply Drop");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "...");
		this.dropInventory = ChestInv;

		
		String blockType = this.dropLocation.getBlock().getType().toString();
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + blockType);
		
		//this.dropLocation.getBlock().setType(Material.CHEST);
		
		

		ChestInv.clear();

		plugin.getServer().getConsoleSender().sendMessage(ChatColor.GRAY + "Supply Drop Spawnd");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "...");
		
		
		

	}

	// Step Three
	public void placeDropSign() {
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Creating Sign");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "...");

		Location signLocation = dropLocation.clone();

		signLocation.setX(signLocation.getX() + 1);

		Block signBlock = signLocation.getBlock();
		signBlock.setType(Material.SIGN);

		Sign sign = (Sign) signBlock.getState();
		sign.setLine(0, "");
		sign.setLine(1, "Supply Drop");
		sign.setLine(2, "-----------");
		sign.setLine(3, "");

		sign.update(true);
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Sign Created");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "...");
	}

	// Step for
	public void setDropInventory(Inventory dropInventory) {
		this.dropInventory = dropInventory;
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Supply Drop Inventory Has been Set");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "...");
	}
	
	public void soundAlarm() {
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]");
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "[Emergency Alert] " + ChatColor.GRAY
				+ "Supply drop has been dropped at " + x + ", " + z + ", " + y);
		plugin.getServer().getConsoleSender().sendMessage(
				ChatColor.GRAY + "Use your compass to find the supply drop. Right-click to target the signal.");
	}

	// Step five
	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Supply drop is not empty");
	}

	public Location getdropLocation() {
		return dropLocation;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public Inventory getDropInventory() {
		return dropInventory;
	}
}
