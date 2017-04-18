package mortuusterra.managers.armor.crafting;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import mortuusterra.Main;

public class ArmorCraftingManager {

	private final Main instance;

	public ArmorCraftingManager(Main instance) {

		this.instance = instance;

		initializeRecipes();
		initializeItemData();

	}
	
	public ArrayList<ItemStack> Tier1 = new ArrayList<ItemStack>();



	private void initializeRecipes() {

	}

	private void initializeItemData() {
		ItemStack Tier1Chest = new ItemStack(Material.LEATHER_CHESTPLATE);
		
		ItemStack Tier2Helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack Tier2Chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack Tier2Legs = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemStack Tier2Boots = new ItemStack(Material.LEATHER_BOOTS);
		
		ItemStack Tier3Helmet = new ItemStack(Material.LEATHER_HELMET);
		ItemStack Tier3Chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack Tier3Legs = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack Tier3Boots = new ItemStack(Material.LEATHER_BOOTS);
		
		ItemStack Tier4Helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack Tier4Chest = new ItemStack(Material.IRON_CHESTPLATE);
		ItemStack Tier4Legs = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack Tier4Boots = new ItemStack(Material.IRON_BOOTS);
		
		ItemStack Tier5Helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack Tier5Chest = new ItemStack(Material.IRON_CHESTPLATE);//4 RedStone
		ItemStack Tier5Legs = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack Tier5Boots = new ItemStack(Material.IRON_BOOTS);
		
		ItemStack Tier6Helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack Tier6Chest = new ItemStack(Material.IRON_CHESTPLATE);//4 RedStone
		ItemStack Tier6Legs = new ItemStack(Material.IRON_LEGGINGS);
		ItemStack Tier6Boots = new ItemStack(Material.IRON_BOOTS);
		
		ItemStack Tier7Helmet = new ItemStack(Material.IRON_HELMET);
		ItemStack Tier7Chest = new ItemStack(Material.IRON_CHESTPLATE);//4 RedStone
		ItemStack Tier7Legs = new ItemStack(Material.IRON_LEGGINGS);//4 RedStone
		ItemStack Tier7Boots = new ItemStack(Material.IRON_BOOTS);
		
		ItemStack Tier8Helmet = new ItemStack(Material.GOLD_HELMET);
		ItemStack Tier8Chest = new ItemStack(Material.GOLD_CHESTPLATE);//4 RedStone
		ItemStack Tier8Legs = new ItemStack(Material.GOLD_LEGGINGS);//4 RedStone
		ItemStack Tier8Boots = new ItemStack(Material.GOLD_BOOTS);
		
		ItemStack Tier9Helmet = new ItemStack(Material.DIAMOND_HELMET);//8 RedStone
		ItemStack Tier9Chest = new ItemStack(Material.DIAMOND_CHESTPLATE);//8 RedStone
		ItemStack Tier9Legs = new ItemStack(Material.DIAMOND_LEGGINGS);//8 RedStone
		ItemStack Tier9Boots = new ItemStack(Material.DIAMOND_BOOTS);//8 RedStone

	}

}
