package mortuusterra.managers;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import mortuusterra.Main;

public class CraftingManager {
	
private final Main plugin;
	
	public CraftingManager(Main plugin) {
		
		this.plugin = plugin;
		
		initializeRecipes();
		
	}
	
	@SuppressWarnings("deprecation")
	public void initializeRecipes() {
		
		plugin.getLogger().info("Initializing Recipes");
		
		ShapedRecipe nukeRecipe = new ShapedRecipe(new ItemStack(373, 1, (short) 16396));
		nukeRecipe.shape(new String[] { "CBC", "BAB", "CBC" });
		nukeRecipe.setIngredient('A', Material.GLASS_BOTTLE);
		nukeRecipe.setIngredient('B', Material.TNT);
		nukeRecipe.setIngredient('C', Material.SNOW_BLOCK);
		plugin.getServer().addRecipe(nukeRecipe);
		
	}

}
