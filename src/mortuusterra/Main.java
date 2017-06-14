package mortuusterra;

import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import mortuusterra.listeners.ChatListener;
import mortuusterra.listeners.EntityListener;
import mortuusterra.listeners.ProtectionListener;
import mortuusterra.listeners.SpawnListener;
//import mortuusterra.listeners.player.PlayerListener;
import mortuusterra.listeners.radiation.GECKListener;
import mortuusterra.listeners.world.ChunkListener;
import mortuusterra.listeners.world.WorldListener;
import mortuusterra.managers.CraftingManager;
import mortuusterra.managers.HelpManager;
import mortuusterra.managers.InfoManager;
import mortuusterra.managers.MiscManager;
import mortuusterra.managers.PermissionsManager;
import mortuusterra.managers.PlayerManager;
import mortuusterra.managers.ProtectionManager;
import mortuusterra.managers.TutorialManager;
import mortuusterra.managers.commands.CommandManager;
import mortuusterra.managers.commands.ConsoleCommandManager;
import mortuusterra.managers.files.ConfigManager;
import mortuusterra.managers.files.FileManager;
import mortuusterra.managers.ratiation.CraterManager;
import mortuusterra.managers.ratiation.FalloutShelterManager;
import mortuusterra.managers.ratiation.GECKManager;
import mortuusterra.managers.ratiation.RadiationManager;
import mortuusterra.managers.supplydrops.SupplyDropsManager;
import mortuusterra.timers.RadiationTimer;
import mortuusterra.timers.SupplyDropTimer;
import mortuusterra.timers.MeteorStrikeTimer;

public class Main extends JavaPlugin {
	@SuppressWarnings("unused")
	private PluginManager plugin = getServer().getPluginManager();

	private Logger logger = Logger.getLogger("Minecraft");

	private ChunkListener chunkListener;
	private ProtectionListener protectionListener;
	private SpawnListener spawnListener;
	private ChatListener chatListener;
	// private PlayerListener playerListener;
	private WorldListener worldListener;
	private GECKListener geckListener;
	private EntityListener entityListener;

	private CommandManager commandManager;
	private ConsoleCommandManager consoleCommandManager;
	private InfoManager infoManager;

	private FileManager fileManager;

	private ConfigManager configManager;
	private HelpManager helpManager;
	private TutorialManager tutorialManager;
	private PermissionsManager permissionsManager;
	private CraftingManager craftingManager;
	// private WorldManager worldManager;
	private MiscManager miscManager;
	private ProtectionManager protectionManager;
	private PlayerManager playerManager;
	private RadiationManager radiationManager;
	private SupplyDropsManager supplyDropsManager;

	private CraterManager craterManager;
	private FalloutShelterManager falloutShelterManager;
	private GECKManager geckManager;
	
	private RadiationTimer radiationTimer;
	private SupplyDropTimer supplyDropTimer;
	private MeteorStrikeTimer meteorStrikeTimer;

	public void onEnable() {
		logger.info("|---------|");
		initiateManagers();
		registerListeners();

		new RadiationTimer(this).runTaskTimer(this, 0L, 20L);
		new SupplyDropTimer(this).runTaskTimer(this, 0L, 20L);
		new MeteorStrikeTimer(this).runTaskTimer(this, 0L, 1200L);
		// saveDefaultConfig();

		mkdir();
		getFileManager().loadFiles();

		logger.info("MortuusTerra has been enabled");

		logger.info("|---------|");
	}

	public void onDisable() {
		logger.info("|----------|");

		getFileManager().saveFiles();

		radiatoinTimer.cancel();
		supplyDropTimer.cancel();
		meteorStrikeTimer.cancel();

		logger.info("|----------|");
	}

	private void initiateManagers() {
		configManager = new ConfigManager(this);
		commandManager = new CommandManager(this);
		consoleCommandManager = new ConsoleCommandManager(this);
		infoManager = new InfoManager(this);

		fileManager = new FileManager(this);

		configManager = new ConfigManager(this);
		helpManager = new HelpManager(this);
		tutorialManager = new TutorialManager();
		permissionsManager = new PermissionsManager(this);
		craftingManager = new CraftingManager(this);
		// worldManager = new WorldManager(instance);
		miscManager = new MiscManager(this);
		protectionManager = new ProtectionManager(this);
		playerManager = new PlayerManager(this);
		radiationManager = new RadiationManager(this);
		supplyDropsManager = new SupplyDropsManager(this);

		craterManager = new CraterManager(this);
		falloutShelterManager = new FalloutShelterManager(this);
		geckManager = new GECKManager(this);
	}

	private void registerListeners() {
		chunkListener = new ChunkListener(this);
		protectionListener = new ProtectionListener(this);
		spawnListener = new SpawnListener(this);
		chatListener = new ChatListener(this);
		// playerListener = new PlayerListener(this);
		worldListener = new WorldListener(this);
		geckListener = new GECKListener(this);
		entityListener = new EntityListener(this);

		// event listeners
		getServer().getPluginManager().registerEvents(this.protectionListener, this);
		getServer().getPluginManager().registerEvents(this.spawnListener, this);
		getServer().getPluginManager().registerEvents(this.chunkListener, this);
		getServer().getPluginManager().registerEvents(this.chatListener, this);
		// getServer().getPluginManager().registerEvents(this.playerListener,
		// this);
		getServer().getPluginManager().registerEvents(this.worldListener, this);
		getServer().getPluginManager().registerEvents(this.geckListener, this);
		getServer().getPluginManager().registerEvents(this.entityListener, this);

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

	public CommandManager getCommandManager() {

		return commandManager;

	}

	public ConsoleCommandManager getConsoleCommandManager() {

		return consoleCommandManager;

	}

	public InfoManager getInfoManager() {

		return infoManager;

	}

	public FileManager getFileManager() {

		return fileManager;

	}

	public SupplyDropsManager getSupplyDropsManager() {

		return supplyDropsManager;

	}

	public HelpManager getHelpManager() {

		return helpManager;

	}

	public TutorialManager getTutorialManager() {

		return tutorialManager;

	}

	public PermissionsManager getPermissionsManager() {

		return permissionsManager;

	}

	/**
	 * public WorldManager getWorldManager() {
	 * 
	 * return worldManager;
	 * 
	 * }
	 **/
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

	/*
	 * public PlayerListener getPlayerListener() {
	 * 
	 * return playerListener;
	 * 
	 * }
	 */
	public WorldListener getWorldListener() {

		return worldListener;

	}
	
	public EntityListener getEntityListener() {
		
		return entityListener;
		
	}

	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (sender instanceof Player) {

			// player commands
			getCommandManager().processCommand((Player) sender, cmd, args);

		} else {

			// console commands
			getConsoleCommandManager().processCommand(cmd, args);

		}

		return true;

	}
}
