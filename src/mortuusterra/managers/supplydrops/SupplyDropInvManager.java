package mortuusterra.managers.supplydrops;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import mortuusterra.Main;

public class SupplyDropInvManager extends SupplyDropsManager {

	public SupplyDropInvManager(Main plugin) {
		super(plugin);
	}

	private String name;
	private Double chance;
	private Double ran = new Random().nextDouble();

	public HashMap<String, Double> items = new HashMap<String, Double>();

	public void setupSupplyDropInv() {
		for (String s : plugin.getConfig().getConfigurationSection("settings.supply-drops.items").getKeys(false)) {
			this.name = plugin.getConfig().getString("settings.supply-drops.items." + s + ".item-id");
			this.chance = plugin.getConfig().getDouble("settings.supply-drops.items." + s + ".chance");
			if (chance >= ran) {

			}
			items.put(name, chance);

			Inv = new ItemStack(Material.valueOf(name));
		}
		plugin.getServer().getConsoleSender().sendMessage("The Supply Drop has been filled with loot!");
		plugin.getServer().getConsoleSender().sendMessage("Go get it befor some one else grabs the good loot!");
	}
}
