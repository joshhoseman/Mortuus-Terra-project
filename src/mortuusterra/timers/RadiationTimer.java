package mortuusterra.timers;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import mortuusterra.Main;

public class RadiationTimer implements Runnable {

	Main plugin;

	public RadiationTimer(Main plugin) {
		this.plugin = plugin;

	}

	public void run() {
		for (Player p : plugin.getServer().getOnlinePlayers()) {
			if (p.hasPotionEffect(PotionEffectType.POISON))
				continue; // if they already have it don't re-apply it.
			Biome biome = p.getLocation().getBlock().getBiome();
			if (biome == Biome.DESERT || biome == Biome.SWAMPLAND) {
				 p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1200, 10));
			}
		}

	}

}
