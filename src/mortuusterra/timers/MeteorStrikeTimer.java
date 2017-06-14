package mortuusterra.timers;

import mortuusterra.Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MeteorStrikeTimer implements BukkitRunnable {
	
	Main plugin;

	public MeteorStrikeTimer(Main plugin) {
		this.plugin = plugin;
		run();
	}

	public void run() {
		// TODO
		
	}
	
	public FallingBlock createMeteor(Location base, int radius) {
		World world = base.getWorld();
		double xLocation = (Math.random() * (radius * 2)) - radius;
		double yLocation = base.getY() + 255;
		double zLocation = (Math.random() * (radius * 2)) - radius;
		Location local = base.clone().add(xLocation, yLocation, zLocation);

		FallingBlock meteor = (FallingBlock) world.spawnFallingBlock(local, Material.ENDER_STONE, (byte) 0);
		
		double xVector = (Math.random() * 0.8) - 0.4;
		double yVector = -3 + (Math.random() * 0.4);
		double zVector = (Math.random() * 0.8) - 0.4;
		Vector vector = new Vector(xVector, yVector, zVector);
		
		meteor.setVelocity(vector);
		meteor.setCustomName("METEOR");
		meteor.setDropItem(false);
		meteor.setHurtEntities(true);
		return meteor;
	}

}
