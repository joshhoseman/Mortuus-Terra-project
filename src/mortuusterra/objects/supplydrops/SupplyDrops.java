package mortuusterra.objects.supplydrops;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.inventory.Inventory;

import mortuusterra.Main;

public class SupplyDrops {

	Main plugin;

	public SupplyDrops(Main plugin, Chest dropChest, Location dropLocation, Inventory dropInventory) {
		this.plugin = plugin;

		this.plugin = plugin;

		this.dropChest = dropChest;
		this.dropLocation = dropLocation;
		this.dropInventory = dropInventory;

		if (dropInventory != null) {

			fillDropChest();

			placeDropSign();

		}

	}

	private Chest dropChest;
	private Location dropLocation;
	private Inventory dropInventory;
	private boolean isEmpty;

	public void fillDropChest() {
		dropInventory = (plugin.getConfigManager()).getSupplyDropContents(dropInventory);
		this.isEmpty = false;
	}

	@SuppressWarnings("deprecation")
	public void placeDropSign() {

		Location signLocation = dropLocation.clone();

		signLocation.setX(signLocation.getX() + 1);

		Block signBlock = signLocation.getBlock();
		signBlock.setTypeId(63);

		Sign sign = (Sign) signBlock.getState();
		sign.setLine(0, "");
		sign.setLine(1, "Supply Drop");
		sign.setLine(2, "-----------");
		sign.setLine(3, "");

		sign.update(true);
	}

	public Chest getDropChest() {
		return dropChest;
	}

	public void setDropChest(Chest dropChest) {
		this.dropChest = dropChest;
	}

	public Inventory getDropInventory() {
		return dropInventory;
	}

	public void setDropInventory(Inventory dropInventory) {
		this.dropInventory = dropInventory;
	}

	public Location getDropLocation() {
		return dropLocation;
	}

	public void setDropLocation(Location dropLocation) {
		this.dropLocation = dropLocation;

	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setIsEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

}
