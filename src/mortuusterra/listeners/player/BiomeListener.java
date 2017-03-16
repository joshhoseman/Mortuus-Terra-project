package mortuusterra.listeners.player;

import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BiomeListener implements Listener {

	Player player;

	PotionEffectType rad1 = PotionEffectType.POISON;

	@EventHandler
	public void BiomCheck(PlayerMoveEvent e) {
		Biome biome = e.getFrom().getBlock().getBiome();

		if (biome == Biome.DESERT || biome == Biome.MUTATED_DESERT || biome == Biome.SWAMPLAND
				|| biome == Biome.MUTATED_SWAMPLAND) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1, 1));
		}
	}

	// public static Object getBiomCheck() {
	// return BiomCheck();

	// }
}
