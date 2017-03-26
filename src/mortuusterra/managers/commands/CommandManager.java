package mortuusterra.managers.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import mortuusterra.Main;
import mortuusterra.objects.PlayerObject;

public class CommandManager {
	
private final Main plugin;
	
	public CommandManager(Main plugin) {
		
		this.plugin = plugin;
		
	}

	public void processCommand(Player sender, Command cmd, String[] args) {

		if (plugin.getConfigManager().disableMeCommand == true) {

			if (cmd.getName().equalsIgnoreCase("me")) {

				
				
			}
			
		}

		if (cmd.getName().equalsIgnoreCase("rads")) {

			if (args.length == 0) {
				
				plugin.getInfoManager().displayRadiationLevels(sender);
				
			}
			
		}
		
		if (cmd.getName().equalsIgnoreCase("spawn")) {

			if (plugin.getConfigManager().isWorldEnabled(sender.getWorld().getName()) == true) {
			
				plugin.getPlayerManager().startTimedTeleport(sender, sender.getWorld().getSpawnLocation());

			}
			
		}
		
		if (cmd.getName().equalsIgnoreCase("a")) {
			
			// ops or admins only
			if (sender.isOp() == true || plugin.getPermissionsManager().playerHasPermissions(sender, "mt.admin") == true) {
				
				List<Player> recipients = sender.getWorld().getPlayers();
				
				String message = "";
				
				for (String arg : args) {
					
					message += arg + " ";
					
				}

				for (Player recipient : recipients) {

					if (recipient.isOp() == true || plugin.getPermissionsManager().playerHasPermissions(recipient, "mt.admin") == true) {
						
						recipient.sendMessage(ChatColor.RED + "[Admin] " + ChatColor.WHITE + sender.getName() + ChatColor.GRAY + " " + message);
						
					}
					
				}
				
			}
			
		}

		if (cmd.getName().equalsIgnoreCase("mt")) {

			if (args.length == 0) {
				
				return;
				
			}

			if (args[0].equalsIgnoreCase("test")) {
				
				List<Entity> nearbyEntities = sender.getNearbyEntities(25.0, 25.0, 25.0);
				
				plugin.getLogger().info("Nearby Entities: " + nearbyEntities.size() );
				
				if (nearbyEntities.size() == 1) {
					
					plugin.getLogger().info(ChatColor.DARK_RED + "[MT Factions] " + ChatColor.WHITE + "There are no other Faction members near your Security Terminal.");
					
				}
				
				/**

				for (Entity nearbyEntity : nearbyEntities) {

					if (nearbyEntity instanceof Player) {
					
						Player nearbyPlayer = (Player) nearbyPlayer;
						nearbyFactionPlayer FPlayer  = (nearbyFactionPlayer) nearbyPlayer;
						
						if (nearbyFactionPlayer.hasFaction() == true) {
						
							plugin.getLogger().info(ChatColor.DARK_RED + "[MT Factions] " + ChatColor.WHITE + "A member of " + nearbyFactionPlayer.getTag() + " is near your Security Terminal.");
						}
					
					}

				} **/
				
			}
			
			if (args[0].equalsIgnoreCase("admin")) {
				
				if (args.length == 1) {
					
					return;
					
				}
				
				// ops or admins only
				if (sender.isOp() == false || plugin.getPermissionsManager().playerHasPermissions(sender, "mt.admin") == false) {
					
					return;
					
				}

				if (args.length == 3 && args[1].equalsIgnoreCase("world")) {

					sender.teleport(plugin.getServer().getWorld(args[2]).getSpawnLocation());
					
				}
				
				if (args.length == 5 && args[1].equalsIgnoreCase("set")) {

					if (args[2].equalsIgnoreCase("thirst")) {
						
						PlayerObject playerObject = plugin.getPlayerManager().getPlayerObjectByPlayerName(args[3]);
						
						if (playerObject == null) {
							
							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY +"User not online.");
							
						} else {
							
							playerObject.thirst = Integer.valueOf(args[4]);
							
						}
						
					}

				}
				
				if (args[1].equalsIgnoreCase("tp")) {

					Double x = Double.valueOf(args[2]);
					Double y = Double.valueOf(args[3]);
					Double z = Double.valueOf(args[4]);
					
					sender.teleport(new Location(sender.getWorld(), x, y, z));
					
				}
				
				if (args[1].equalsIgnoreCase("setspawn")) {

					Double x = sender.getLocation().getX();
					Integer xx = x.intValue();

					Double y = sender.getLocation().getY();
					Integer yy = y.intValue();

					Double z = sender.getLocation().getZ();
					Integer zz = z.intValue();

					sender.getWorld().setSpawnLocation(xx, yy, zz);
					
					sender.sendMessage(ChatColor.DARK_RED + "[Mortuust Terra] " +  ChatColor.GRAY + "Spawn Location Set");
					
					
				}
				
				
				if (args[1].equalsIgnoreCase("fix")) {
					
					if (args.length == 2) {
						
						return;
						
					}
					
					if (args[2].equalsIgnoreCase("streetlights")) {
						
						plugin.getMiscManager().fixStreetlights(sender, sender.getLocation());
						
					}
					
					if (args[2].equalsIgnoreCase("drops")) {
						
						plugin.getMiscManager().clearDrops(sender, sender.getWorld());
						
					}					
					
				}
				
			}
			
			if (args[0].equalsIgnoreCase("activate")) {
				
				if (args.length == 1) {
					
					return;
					
				}
				
				if (args[1].equalsIgnoreCase("geck")) {
					
					if (plugin.getGECKManager().getGECKObjectByLocation(sender.getEyeLocation().getBlock().getLocation()) == null) {
						
						if (plugin.getGECKManager().isValidGECK(sender.getEyeLocation().getBlock().getLocation()) == true) {
							
							// add geck to the geck data
							plugin.getGECKManager().addGECK(sender.getEyeLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());

							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " GECK Activated");
							
						} else {
							
							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " Not a valid GECK");
							
						}
												
					} else {
						
						sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " GECK Already Activated");
						
					}
					
				}
				
			}
			
			if (args[0].equalsIgnoreCase("delete")) {

				if (args.length == 1) {
					
					return;
					
				}

			}
			
			if (args[0].equalsIgnoreCase("create")) {
				
				if (args.length == 1) {
					
					return;
					
				}

				if (args.length == 2 && args[1].equalsIgnoreCase("crater") && (plugin.getPermissionsManager().playerHasPermissions(sender, "mt.admin") || sender.isOp() == true)) {

					final World world = sender.getWorld();
					final Location location = sender.getLocation();
		
					sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Clear out! The nuke is about to detonate!");
		
					@SuppressWarnings("unused")
					int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {
		
							world.createExplosion(location, 3);
							plugin.getCraterManager().addCrater(location);
							plugin.getChunkListener().placeCrater(location);
							
						}
					}, 90);
					
				}
					
				if (args.length == 2 && args[1].equalsIgnoreCase("supplydrop") && (plugin.getPermissionsManager().playerHasPermissions(sender, "mt.admin") || sender.isOp() == true)) {
					
					plugin.getWorldListener().deliverSupplyDrop(sender.getWorld());
					
				}
				
				if (args[1].equalsIgnoreCase("protection") && (plugin.getPermissionsManager().playerHasPermissions(sender, "mt.admin") || sender.isOp() == true)) {
					
					if (args.length == 2) {
						
						return;
						
					}
					
					if (plugin.getProtectionManager().getProtectionObjectByName(args[2]) == null) {
						
						if (plugin.getProtectionManager().protectionMarksPresent(sender) == true) {
							
							plugin.getProtectionManager().addProtection(args[2], sender);

							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " Protection Created");							
							
						} else {

							sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " First mark points using the protection tool");
							
						}
						
					} else {
						
						sender.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra]" + ChatColor.GRAY + " A Protection already exists by that name.");
						
					}
					
				}
				
			}
			
			if (args[0].equalsIgnoreCase("list")) {
				
				
				
			}

			if (args[0].equalsIgnoreCase("help")) {
				
				if (args.length == 1) {
					
					plugin.getHelpManager().showHelp(sender);
					
				} else {
					
					if (args[1].equalsIgnoreCase("permissions")) {
						
						plugin.getHelpManager().showHelpPermissions(sender);
						
					}
					
					if (args[1].equalsIgnoreCase("admin")) {
						
						plugin.getHelpManager().showHelpAdmin(sender);
						
					}
					
					if (args[1].equalsIgnoreCase("nukes")) {
						
						plugin.getHelpManager().showHelpNukes(sender);
						
					}
					
					if (args[1].equalsIgnoreCase("activate")) {
						
						plugin.getHelpManager().showHelpActivate(sender);
						
					}
					
				}
				
			}
			
			if (args[0].equalsIgnoreCase("")) {
				
				plugin.getHelpManager().showHelp(sender);
				
			}
			
		}		
		
	}

}
