package mortuusterra.listeners.player;


public class OLDPlayerListener{
	/*
	 * This is a old file, go to
	 * mortuusterra.managers.PlayerManager.PlayerManager; 
	 * For the new File
	 */
/*
	private final Main plugin;

	public PlayerListener(Main plugin) {

		this.plugin = plugin;

	}
	@EventHandler(priority = EventPriority.LOW)
	public void playerIsAttackedByPlayer(EntityDamageByEntityEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getEntity().getWorld().getName()) == false) {

			return;

		}

		if (plugin.getConfigManager().newbieProtectionEnabled == false) {

			return;

		}

		if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {

			Player attacker = (Player) event.getDamager();
			Player victim = (Player) event.getEntity();

			PlayerObject victimObject = plugin.getPlayerManager().getPlayerObjectByPlayerName(victim.getName());
			PlayerObject attackerObject = plugin.getPlayerManager().getPlayerObjectByPlayerName(attacker.getName());

			if (victimObject == null) {

				return;

			}

			if (attackerObject == null) {

				return;

			}

			// plugin.getLogger().info("Victim Join Date: " +
			// victimObject.joinDate + " | Server Time: " +
			// victim.getWorld().getFullTime());

			if (victim.getWorld().getFullTime() - victimObject.joinDate < (24000 * 5)) {

				// Player has not yet been around for 5 Minecraft days
				event.setCancelled(true);

				victim.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY
						+ "You are protected from pvp for 5 Minecraft days.");
				attacker.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY
						+ "New players cannot be attacked for 5 Minecrat days.");

			}

			if (attacker.getWorld().getFullTime() - attackerObject.joinDate < (24000 * 5)) {

				// attacker has not yet been around for 5 minecraft days
				// therefore he loses his protection

				victim.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + attacker.getName()
						+ " has lost his/her 5 Day protection by attacking you.");
				attacker.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY
						+ "You have lost your 5 Day protection by attacking another player.");

			}

		} else {

			return;

		}

	}

	@EventHandler(priority = EventPriority.LOW)
	public void playerMoves(PlayerMoveEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;

		}

		lightningStrikeCheck(event);

		playerBioCheck(event);

		radiationCheck(event);

	}

	@SuppressWarnings("deprecation")
	public void radiationCheck(PlayerMoveEvent event) {

		double radiationDamage = plugin.getRadiationManager().calculateRadiationDamage(event.getPlayer());

		if (radiationDamage > 0.0) {

			plugin.getRadiationManager().radiationDamageReceived(event.getPlayer());

			Double radiationSicknessIncreaseChance = Math.random();

			Double radiationDamageTrigger = radiationDamage * .01;

			if (radiationDamageTrigger > radiationSicknessIncreaseChance) {

				event.getPlayer().playEffect(event.getPlayer().getLocation(), Effect.CLICK1, 10);

			}

		}

	}

	public void playerBioCheck(PlayerMoveEvent event) {

		// udpate player health, hunger, and thirst
		PlayerObject playerObject = plugin.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName());

		if (playerObject == null) {

			return;

		}

		playerObject.health = event.getPlayer().getHealth();
		playerObject.hunger = event.getPlayer().getFoodLevel();

		if (plugin.getConfigManager().thirstEnabled == false) {

			return;

		}

		playerObject.calculateThirst(event.getPlayer().getWorld().getFullTime());

	}

	public void lightningStrikeCheck(PlayerMoveEvent event) {

		if (plugin.getRadiationManager().isPlayerInside(event.getPlayer()) == true) {

			return;

		}

		if (plugin.getConfigManager().nuclearLightningEnabled == true) {

			if (Math.random() < (plugin.getConfigManager().nuclearLightningChance / 4)) {

				Location lightningLocation = event.getPlayer().getLocation();
				lightningLocation.setY(lightningLocation.getY() + 30);

				event.getPlayer().getLocation().getWorld().strikeLightning(lightningLocation);

			}

		}

	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void playerDrinksRain(WeatherChangeEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getWorld().getName()) == false) {

			return;

		}

		if (plugin.getConfigManager().thirstEnabled == false) {

			return;

		}

		if (event.getWorld().isThundering() == true) {

			for (Player player : event.getWorld().getPlayers()) {

				if (event.getWorld().getHighestBlockAt(player.getLocation()).getType().equals(Material.AIR)
						|| event.getWorld().getHighestBlockAt(player.getLocation()).getType().equals(Material.GLASS)) {

					plugin.getPlayerManager().getPlayerObjectByPlayerName(player.getName()).thirst = 20;
					player.sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY
							+ "You drink from the rain and replenish your thirst.");

				}

			}

		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOWEST)
	public void playerDrinksWater(PlayerInteractEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;

		}

		if (plugin.getConfigManager().thirstEnabled == false) {

			return;

		}

		if ((event.getAction().toString().equals("RIGHT_CLICK_BLOCK")
				|| event.getAction().toString().equals("RIGHT_CLICK_AIR"))
				&& event.getMaterial().toString().equalsIgnoreCase("POTION") && event.getItem().getDurability() == 0) {

			event.getPlayer().getItemInHand().setType(Material.GLASS_BOTTLE);

			final Player finalPlayer = event.getPlayer();

			@SuppressWarnings("unused")
			int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {

					finalPlayer.sendMessage(
							ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "You replenish your thirst.");

				}
			}, 100);

			plugin.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()).thirst = 20;

		}

	}

	@EventHandler
	public void playerSpawns(PlayerDeathEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getEntity().getWorld().getName()) == false) {

			return;

		}

		Long currentTimestamp = event.getEntity().getWorld().getTime();

		PlayerObject playerObject = plugin.getPlayerManager().getPlayerObjectByPlayerName(event.getEntity().getName());

		if (playerObject != null && (currentTimestamp < playerObject.getLastKitAwarded())) {

			// award kit
			event.getEntity().getInventory().addItem(new ItemStack(Material.COMPASS, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.WOOD_SWORD, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.LEATHER_HELMET, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.LEATHER_LEGGINGS, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.LEATHER_BOOTS, 1));
			event.getEntity().getInventory().addItem(new ItemStack(Material.BREAD, 5));
			event.getEntity().getInventory().addItem(new ItemStack(Material.SEEDS, 5));

		}

	}

	@EventHandler(priority = EventPriority.LOW)
	public void playerLogsIn(PlayerJoinEvent event) {

		if (plugin.getPlayerManager().getPlayerObjectByPlayerName(event.getPlayer().getName()) == null) {

			plugin.getPlayerManager().addPlayer(event.getPlayer().getName(), event.getPlayer().getWorld().getFullTime(),
					20, 20, 20);

		}

	}

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOW)
	public void playerMarksProtection(PlayerInteractEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;

		}

		if (event.getPlayer().isOp() == false
				&& plugin.getPermissionsManager().playerHasPermissions(event.getPlayer(), "mt.admin") == false
				&& plugin.getPermissionsManager().playerHasPermissions(event.getPlayer(), "mt.create.*") == false
				&& plugin.getPermissionsManager().playerHasPermissions(event.getPlayer(),
						"mt.create.protection") == false) {

			return;

		}

		if (event.getPlayer().getItemInHand().toString().contains("STICK")) {

			if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {

				Location mark1 = event.getClickedBlock().getLocation();
				plugin.getProtectionManager().protectionMarksOne.put(event.getPlayer(), mark1);
				event.getPlayer().sendMessage(
						ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Protection Point One Set");

				event.setCancelled(true);

			}

			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

				Location mark2 = event.getClickedBlock().getLocation();
				plugin.getProtectionManager().protectionMarksTwo.put(event.getPlayer(), mark2);
				event.getPlayer().sendMessage(
						ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Protection Point Two Set");

			}

		}

	}

	@EventHandler(priority = EventPriority.LOW)
	public void playerDetonatesNuke(PlayerInteractEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;

		}

		if (plugin.getConfigManager().generateCratersPlayer == false) {

			return;

		}

		if ((event.getAction().toString().equals("RIGHT_CLICK_BLOCK")
				|| event.getAction().toString().equals("RIGHT_CLICK_AIR"))
				&& event.getMaterial().toString().equalsIgnoreCase("POTION")
				&& event.getItem().getDurability() == 16396) {

			if (plugin.getProtectionManager().locationIsProtected(event.getPlayer().getLocation()) == false) {

				if (Math.random() > plugin.getConfigManager().generateCratersPlayerChance) {

					event.getPlayer().sendMessage(
							ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "The nuke did not detonate.");

					event.setCancelled(true);

				} else {

					final World world = event.getPlayer().getWorld();
					final Location location = event.getPlayer().getLocation();
					final Effect effect = Effect.SMOKE;

					event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY
							+ "Clear out! The nuke is about to detonate!");

					@SuppressWarnings("unused")
					int taskId = Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
						public void run() {

							// world.createExplosion(location, 2);
							world.playEffect(location, effect, 20);
							plugin.getCraterManager().addCrater(location);
							plugin.getChunkListener().placeCrater(location);

						}
					}, 90);

				}

			} else {

				plugin.getProtectionManager().informPlayer(event.getPlayer());

			}

		}

	}

	/*
	 * @EventHandler(priority=EventPriority.LOW) public void
	 * playerUsesGeigerCounter(PlayerInteractEvent event) {
	 * 
	 * if
	 * (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().
	 * getName()) == false) {
	 * 
	 * return;
	 * 
	 * } if ((event.getAction().equals(Action.RIGHT_CLICK_AIR) ||
	 * event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) &&
	 * event.getPlayer().getItemInHand().getTypeId() ==
	 * plugin.getConfigManager().geigerCounterItemId) {
	 * 
	 * double radiationDamage =
	 * plugin.getRadiationManager().calculateRadiationDamage(event.getPlayer());
	 * event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " +
	 * ChatColor.GRAY + radiationDamage + " Rads");
	 * 
	 * }
	 * 
	 * }
	 */
/*
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.LOW)
	public void playerSearchesForSupplyDropSignal(PlayerInteractEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;

		}

		if (plugin.getConfigManager().supplyDropsEnabled == true && event.getPlayer().isSneaking() == false
				&& (event.getAction().equals(Action.RIGHT_CLICK_AIR)
						|| event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& event.getPlayer().getItemInHand().getType().equals(Material.COMPASS)) {

			SupplyDropsManager closestSupplyDrop = plugin.getSupplyDropsManager()
					.getNearestSupplyDrop(event.getPlayer().getLocation());

			if (closestSupplyDrop == null) {

				event.getPlayer().sendMessage(ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY
						+ "There are no Supply Drops in the city.");

			} else {

				event.getPlayer().sendMessage(
						ChatColor.DARK_RED + "[Mortuus Terra] " + ChatColor.GRAY + "Targeting nearest Supply Drop.");

				event.getPlayer().setCompassTarget(closestSupplyDrop.getdropLocation());

			}

		}

	}

	@EventHandler(priority = EventPriority.LOW)
	public void playerReceivesRadiationDamage(EntityRadiationDamageEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;

		}

		if (event.isCancelled() == true) {

			return;

		}

		// adjustment
		Double radiationDamageReceived = event.getRadiationDamage();

		Integer tempInt = radiationDamageReceived.intValue();
		radiationDamageReceived = Double.valueOf(tempInt);

		double ran = Math.random();
		if (ran <= (radiationDamageReceived * .0015)) {

			plugin.getPlayerManager().activateGeigerCounter(event.getPlayer());

			if (plugin.getRadiationManager().armorAbsorbsRadiation(event.getPlayer()) == false) {

				event.getPlayer().damage(1);

			}

		}

	}

	@EventHandler(priority = EventPriority.LOW)
	public void playerMovesOutOfBounds(PlayerMoveEvent event) {

		if (plugin.getConfigManager().isWorldEnabled(event.getPlayer().getWorld().getName()) == false) {

			return;

		}

		if (plugin.getConfigManager().worldBorderEnabled == false) {

			return;

		}

		if (plugin.getPlayerManager().playerOutOfBounds(event.getPlayer()) == true) {

			if (plugin.getConfigManager().WorldBorderEvent.equalsIgnoreCase("wrap")) {

				Location to = event.getPlayer().getLocation();
				double size = plugin.getConfigManager().worldBorderSize;
				double halfSize = size / 2;

				if (to.getX() < -1.0 * halfSize) {
					to = to.add(size, 0.0, 0.0);
					to.getWorld().loadChunk((int) to.getX(), (int) to.getZ(), true);
					to.setY(to.getWorld().getHighestBlockAt(to).getY());
					event.getPlayer().teleport(to);
				}

				if (to.getX() > halfSize) {
					to = to.add(-1 * size, 0.0, 0.0);
					to.getWorld().loadChunk((int) to.getX(), (int) to.getZ(), true);
					to.setY(to.getWorld().getHighestBlockAt(to).getY());
					event.getPlayer().teleport(to);
				}

				if (to.getZ() < -1.0 * halfSize) {
					to = to.add(0.0, 0.0, size);
					to.getWorld().loadChunk((int) to.getX(), (int) to.getZ(), true);
					to.setY(to.getWorld().getHighestBlockAt(to).getY());
					event.getPlayer().teleport(to);
				}

				if (to.getZ() > halfSize) {
					to = to.add(0.0, 0.0, -1 * size);
					to.getWorld().loadChunk((int) to.getX(), (int) to.getZ(), true);
					to.setY(to.getWorld().getHighestBlockAt(to).getY());
					event.getPlayer().teleport(to);
				}

			}

			if (plugin.getConfigManager().WorldBorderEvent.equalsIgnoreCase("radiation")) {

				if (Math.random() < 0.01) {

					plugin.getPlayerManager().activateGeigerCounter(event.getPlayer());
					event.getPlayer().damage(1);

				}

			}

			if (plugin.getConfigManager().WorldBorderEvent.equalsIgnoreCase("lightning")) {

				if (Math.random() < 0.02) {

					event.getPlayer().getWorld().strikeLightningEffect(event.getPlayer().getLocation());
					event.getPlayer().getWorld().strikeLightning(event.getPlayer().getLocation());

				}

			}

		}

	}
*/

}
