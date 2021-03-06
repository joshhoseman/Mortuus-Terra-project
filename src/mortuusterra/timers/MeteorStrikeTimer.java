package mortuusterra.timers;

import mortuusterra.Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.entity.FallingBlock;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class MeteorStrikeTimer extends BukkitRunnable {
	
	public final static String meteorName = "METEOR";
	private Main plugin;
	private long A = 10;

	public MeteorStrikeTimer(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test text 1");
		run();
	}

	public void run() {
		int randomPlayer = (int) Math.random() * Bukkit.getOnlinePlayers().size() - 1;
		if (randomPlayer >= 0) {
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test text 2");
			Object[] players = Bukkit.getOnlinePlayers().toArray();
			
			Player player = (Player) players[randomPlayer];
			Location local = player.getLocation();
		
			new BukkitRunnable() {
			
				int times = 0;
			
				@Override
				public void run() {
					plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test text 3");
					times++;
					for (int i = 0; i < 25; i++) {
						plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test tex 5");
						createMeteor(local, 10);
					}
					if (times > 9) {
						plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test text 6");
						this.cancel();
					}
				}	
			}.runTaskTimer(plugin, A, A);
			
			plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test text 7");
			
			int x = ((int) local.getX());
			int z = ((int) local.getZ());
			String msg = ChatColor.translateAlternateColorCodes('&', "&4Meteor shower at: X=&6" + x + "&4, Z=&6" + z);
			
			for (Player onlineplayer : Bukkit.getOnlinePlayers()) {
				plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test tex 8");
				onlineplayer.sendMessage(msg);	
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public FallingBlock createMeteor(Location base, int radius) {
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Test tex 8");
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
		meteor.setCustomName(meteorName);
		meteor.setDropItem(false);
		meteor.setHurtEntities(true);
		return meteor;
	}

}
