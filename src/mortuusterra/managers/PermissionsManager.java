package mortuusterra.managers;

import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;

import mortuusterra.Main;

public class PermissionsManager {
	
	private final Main plugin;

	public static Permission permissions;
	
	public PermissionsManager(Main plugin) {
		
		this.plugin = plugin;

		permissions = null;

		initializePermissions();
		
	}

	private void initializePermissions() {
		
		RegisteredServiceProvider<Permission> permissionProvider = plugin.getServer().getServicesManager().getRegistration(Permission.class);
		
		if (permissionProvider != null) {

			permissions = (Permission)permissionProvider.getProvider();
			
		}
		
		if (permissions != null) { 
			
			plugin.getLogger().info("Permissions Initialized");
			
		} else {
			
			plugin.getLogger().warning("Could Not Initialize Permissions");
			
		}
		
	}
	
	public boolean playerHasPermissions(Player player, String permission) {
		
		boolean hasPermissions = false;
		
		if (player.hasPermission(permission)) {
			
			hasPermissions = true;
			
		}
		
		return hasPermissions;		
		
	}

}
