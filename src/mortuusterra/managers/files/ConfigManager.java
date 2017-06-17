package mortuusterra.managers.files;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import mortuusterra.Main;

public class ConfigManager {

	Main plugin;

	public ConfigManager(Main plugin) {
		this.plugin = plugin;

		config = plugin.getConfig();

		configFile = new File(plugin.getDataFolder() + File.separator + "config.yml");

		initializeConfig();

		loadConfig();

		saveConfig();

	}

	// config files
	private File configFile;
	private FileConfiguration config;

	// world
	public String[] worldsEnabled;
	public boolean worldBorderEnabled;
	public int worldBorderSize;
	public String WorldBorderEvent;

	// additional features
	public boolean thirstEnabled;
	public boolean newbieProtectionEnabled;

	// chat
	public boolean chatFeaturesEnabled;
	public Integer clearChatRange;
	public Integer garbledChatRange;
	public boolean disableMeCommand;

	// spawn
	public String spawnType;
	public int spawnProtectionRange;

	// fallout shelters
	public boolean generateFalloutSheltersEnabled;
	public double generateFalloutSheltersChance;

	// craters
	public boolean generateCratersEnabled;
	public double generateCratersChance;
	public boolean generateCratersAdmin;
	public boolean generateCratersPlayer;
	public double generateCratersPlayerChance;

	// nuclear weather
	public boolean nuclearLightningEnabled;
	public double nuclearLightningChance;

	// radiation
	public boolean radiationEnabled;
	public double radiationMax;
	public double radiationDamageBase;
	public double radiationDamageIncreaseFromWater;
	public double radiationDamageIncreaseFromStorms;

	// supply drops
	public boolean supplyDropsEnabled;
	public double supplyDropsChance;

	// survival kits
	public boolean survivalKitsEnabled;
	public Integer survivalKitsHoursBetween;

	public void initializeConfig() {

		boolean configFileExists = configFile.exists();

		if (configFileExists == true) {

			try {

				config.options().copyDefaults(true);
				config.load(this.configFile);

			} catch (Exception e) {

				e.printStackTrace();

			}

		} else {

			plugin.getLogger().info("Creating Default Config File");

			config.options().copyDefaults(true);

		}

	}

	public void loadConfig() {

		// worlds
		worldsEnabled = config.getString("settings.worlds.enabled").split(",");
		worldBorderEnabled = config.getBoolean("settings.worlds.border.enabled");
		worldBorderSize = config.getInt("settings.worlds.border.size");
		WorldBorderEvent = config.getString("settings.worlds.border.event");

		// additional features
		thirstEnabled = config.getBoolean("settings.additional-features.thirst.enabled");
		newbieProtectionEnabled = config.getBoolean("settings.additional-features.newbie-protection.enabled");

		// spawn
		spawnType = config.getString("settings.worlds.spawn.type");
		spawnProtectionRange = config.getInt("settings.worlds.spawn.protection-range");

		// chat
		chatFeaturesEnabled = config.getBoolean("settings.chat.chat-features-enabled");
		clearChatRange = config.getInt("settings.chat.clear-chat-range");
		garbledChatRange = config.getInt("settings.chat.garbled-chat-range");
		disableMeCommand = config.getBoolean("settings.chat.disable-me-command");

		// fallout shelters
		generateFalloutSheltersEnabled = config.getBoolean("settings.auto-generation.fallout-shelters.enabled");
		generateFalloutSheltersChance = config.getDouble("settings.auto-generation.fallout-shelters.chance");

		// craters
		generateCratersEnabled = config.getBoolean("settings.auto-generation.craters.enabled");
		generateCratersChance = config.getDouble("settings.auto-generation.craters.chance");
		generateCratersAdmin = config.getBoolean("settings.auto-generation.craters.nukes.admin.enabled");
		generateCratersPlayer = config.getBoolean("settings.auto-generation.craters.nukes.player.enabled");
		generateCratersPlayerChance = config.getDouble("settings.auto-generation.craters.nukes.player.chance");

		// nuclear weather
		nuclearLightningEnabled = config.getBoolean("settings.radiation.nuclear-weather.lightning.enabled");
		nuclearLightningChance = config.getDouble("settings.radiation.nuclear-weather.lightning.strike-chance");

		// radiation
		radiationEnabled = config.getBoolean("settings.radiation.enabled");
		radiationMax = config.getDouble("settings.radiation.maximum");
		radiationDamageBase = config.getDouble("settings.radiation.damage.damage-chance.base");
		radiationDamageIncreaseFromWater = config
				.getDouble("settings.radiation.damage.damage-chance.increase-from-water");
		radiationDamageIncreaseFromStorms = config
				.getDouble("settings.radiation.damage.damage-chance.increase-from-storms");

		// supply drops
		supplyDropsEnabled = config.getBoolean("settings.supply-drops.enabled");
		supplyDropsChance = config.getDouble("settings.supply-drops.supply-drop-chance");

		// survival kits
		survivalKitsEnabled = config.getBoolean("settings.survival-kits.enabled");
		survivalKitsHoursBetween = config.getInt("settings.survival-kits.hours-between-kits");

	}

	public void saveConfig() {

		try {

			config.save(configFile);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public boolean isWorldEnabled(String worldName) {

		boolean isEnabled = false;

		for (String world : worldsEnabled) {

			if (world.equalsIgnoreCase(worldName)) {

				isEnabled = true;

			}

		}

		return isEnabled;

	}
}
