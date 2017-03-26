package mortuusterra;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.listeners.world.WorldListener;
import mortuusterra.managers.ConfigManager;
import mortuusterra.managers.FileManager;
import mortuusterra.managers.SupplyDropManager;
import mortuusterra.timers.RadiationTimer;
import mortuusterra.timers.SupplyDropTimer;

public class Main extends JavaPlugin {
	@SuppressWarnings("unused")
	private PluginManager plugin = getServer().getPluginManager();

	private Logger logger = Logger.getLogger("Minecraft");

	// private ChunkListener chunkListener;
	// private ProtectionListener protectionListener;
	// private SpawnListener spawnListener;
	// private ChatListener chatListener;
	// private PlayerListener playerListener;
	private WorldListener worldListener;
	// private GECKListener geckListener;

	// private CommandManager commandManager;
	// private ConsoleCommandManager consoleCommandManager;
	// private InfoManager infoManager;

	private FileManager fileManager;

	private ConfigManager configManager;
	// private HelpManager helpManager;
	// private TutorialManager tutorialManager;
	// private PermissionsManager permissionsManager;
	// private CraftingManager craftingManager;
	// private WorldManager worldManager;
	// private MiscManager miscManager;
	// private ProtectionManager protectionManager;
	// private PlayerManager playerManager;
	// private RadiationManager radiationManager;
	private SupplyDropManager supplyDropManager;

	// private CraterManager craterManager;
	// private FalloutShelterManager falloutShelterManager;
	// private GECKManager geckManager;

	public void onEnable() {
		logger.info("|---------|");
		initiateManagers();
		registerListeners();

		getServer().getScheduler().scheduleSyncRepeatingTask(this, new RadiationTimer(this), 0L, 20L);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new SupplyDropTimer(this), 0L, 24000L);

		// saveDefaultConfig()

		mkdir();
		getFileManager().loadFiles();

		logger.info("MortuusTerra has been enabled");

		logger.info("|---------|");

	}

	public void onDisable() {
		logger.info("|----------|");

		getFileManager().saveFiles();

		getServer().getScheduler().cancelTasks(this);

		logger.info("|----------|");
	}

	private void initiateManagers() {
		configManager = new ConfigManager(this);
		// commandManager = new CommandManager(instance);
		// consoleCommandManager = new ConsoleCommandManager(instance);
		// infoManager = new InfoManager(instance);

		fileManager = new FileManager(this);

		configManager = new ConfigManager(this);
		// helpManager = new HelpManager(instance);
		// tutorialManager = new TutorialManager();
		// permissionsManager = new PermissionsManager(instance);
		// craftingManager = new CraftingManager(instance);
		// worldManager = new WorldManager(instance);
		// miscManager = new MiscManager(instance);
		// protectionManager = new ProtectionManager(instance);
		// playerManager = new PlayerManager(instance);
		// radiationManager = new RadiationManager(instance);
		supplyDropManager = new SupplyDropManager(this);

		// craterManager = new CraterManager(instance);
		// falloutShelterManager = new FalloutShelterManager(instance);
		// geckManager = new GECKManager(instance);

	}

	private void registerListeners() {
		// chunkListener = new ChunkListener(instance);
		// protectionListener = new ProtectionListener(instance);
		// spawnListener = new SpawnListener(instance);
		// chatListener = new ChatListener(instance);
		// playerListener = new PlayerListener(instance);
		worldListener = new WorldListener(this);
		// geckListener = new GECKListener(instance);

		// event listeners
		// getServer().getPluginManager().registerEvents(this.protectionListener,
		// this);
		// getServer().getPluginManager().registerEvents(this.spawnListener,
		// this);
		// getServer().getPluginManager().registerEvents(this.chunkListener,
		// this);
		// getServer().getPluginManager().registerEvents(this.chatListener,
		// this);
		// getServer().getPluginManager().registerEvents(this.playerListener,
		// this);
		getServer().getPluginManager().registerEvents(this.worldListener, this);
		// getServer().getPluginManager().registerEvents(this.geckListener,
		// this);

	}

	private void mkdir() {

		try {
			getDataFolder().mkdir();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public ConfigManager getConfigManager() {
		return configManager;
	}

	/** public CommandManager getCommandManager() {

		return commandManager;

	}

	public ConsoleCommandManager getConsoleCommandManager() {

		return consoleCommandManager;

	}

	public InfoManager getInfoManager() {

		return infoManager;

	} **/

	public FileManager getFileManager() {

		return fileManager;

	}
	public SupplyDropManager getSupplyDropManager() {

		return supplyDropManager;

	}
/**
	public HelpManager getHelpManager() {

		return helpManager;

	}

	public TutorialManager getTutorialManager() {

		return tutorialManager;

	}

	public PermissionsManager getPermissionsManager() {

		return permissionsManager;

	}

	public WorldManager getWorldManager() {

		return worldManager;

	}

	public CraftingManager getCraftingManager() {

		return craftingManager;

	}

	public MiscManager getMiscManager() {

		return miscManager;

	}

	public ProtectionManager getProtectionManager() {

		return protectionManager;

	}

	public PlayerManager getPlayerManager() {

		return playerManager;

	}

	public RadiationManager getRadiationManager() {

		return radiationManager;

	}
	public CraterManager getCraterManager() {

		return craterManager;

	}

	public FalloutShelterManager getFalloutShelterManager() {

		return falloutShelterManager;

	}

	public GECKManager getGECKManager() {

		return geckManager;

	}

	public ChunkListener getChunkListener() {

		return chunkListener;

	}

	public PlayerListener getPlayerListener() {

		return playerListener;

	}
**/
	public WorldListener getWorldListener() {

		return worldListener;

	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (sender instanceof Player) {

			// player commands
			//getCommandManager().processCommand((Player) sender, cmd, args);

		} else {

			// console commands
			//getConsoleCommandManager().processCommand(cmd, args);

		}

		return true;

	}
}
