package mortuusterra.managers.commands;

import org.bukkit.command.Command;

import mortuusterra.Main;

public class ConsoleCommandManager {
	
private final Main plugin;
	
	public ConsoleCommandManager(Main plugin) {
		
		this.plugin = plugin;
		
	}

	public void processCommand(Command cmd, String[] args) {


		
	}

	public void temp() {
		
		plugin.getLogger().info("Class Loaded");
		
	}

}
