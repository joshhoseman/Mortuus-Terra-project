package mortuusterra.objects.supplydrops;


public class OLDSupplyDropObject {
	
	/*
	 * This is a old file, go to
	 * mortuusterra.managers.supplydrops.SupplyDropsManager; 
	 * For the new File
	 */
/*
	Main plugin;

	Random ran = new Random();

	private Chest dropChest;
	private Inventory dropInventory;
	private boolean isEmpty;
	private World world;
	private Location dropLocation;
	
	int x, y, z;

	public SupplyDropObject(Main plugin) {
		this.plugin = plugin;
		
		this.x = ran.nextInt((1000) - 500);
        this.z = ran.nextInt((1000) - 500);
        this.y = world.getHighestBlockYAt(x, z);
		this.dropLocation = new Location(world, x, y, z);
		
		setDropL();
	}

	public Location getDropL() {
		return dropLocation;
	}

	public void setDropL() {

		// here I get the highest block at Y so then the Supply Drop will not be
		// underground, then I make the chest.
		dropLocation.setY(world.getHighestBlockYAt(dropLocation));
		dropLocation.getBlock().setType(Material.CHEST);

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
		dropInventory = (plugin.getConfigManager()).getSupplyDropContents(dropInventory);
		//dropInventory.addItem(arg0)  note to self, make some way to give the different tiers of armor.
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
	*/
}
