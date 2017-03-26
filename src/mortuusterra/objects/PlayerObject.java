package mortuusterra.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import mortuusterra.Main;

public class PlayerObject {
private final Main plugin;
	
	public PlayerObject(Main plugin) { 
	
		this.plugin = plugin;
	
	}
	
	public String playerName;
	public Long joinDate;
	public Long lastKitAwarded;
	public Long lastThirstChange;
	
	public double health;
	public Integer thirst;
	public Integer hunger;
	
	public List<PotionEffectType> effects;

	public PlayerObject(Main plugin, String playerName, Long joinDate, Integer health, Integer thirst, Integer hunger) {

		this.plugin = plugin;
		
		this.playerName = playerName;
		this.lastKitAwarded = Long.valueOf(24000);
		this.lastThirstChange = Long.valueOf(0);

		this.joinDate = joinDate;
		
		this.health = health;
		this.thirst = thirst;
		this.hunger = hunger;
		
		effects = new ArrayList<PotionEffectType>();
		
		checkThirstEffects();
		
		checkHungerEffects();
		
	}

	public String getPlayerName() {

		return playerName;
	
	}
	
	public void setLastKitAwarded(Long lastKitAwarded) {
		
		this.lastKitAwarded = lastKitAwarded;
		
	}
	
	public Long getLastKitAwarded() {
		
		return lastKitAwarded;
		
	}
	
	public void updateHealth(Integer healthChange) {
		
		health = health + healthChange;
		
	}
	
	public void updateThirst(Integer thirstChange) {
		
		thirst = thirst + thirstChange;

		if (thirst < 0) {
			
			thirst = 0;
			
		}
		
		checkThirstEffects();
		
	}
	
	public void calculateThirst(Long currentTime) {
		
		Long thirstChangeLong = Long.valueOf(0);
		Integer thirstChange = 0;
		
		if (lastThirstChange == 0) {
						
			lastThirstChange = currentTime;

		} else {

			if (currentTime - lastThirstChange > 9600) {
				
				thirstChangeLong = (currentTime - lastThirstChange) / 7200;
				thirstChange = thirstChangeLong.intValue();
				thirstChange = thirstChange * -1;
				
				lastThirstChange = currentTime;
				
			}

		}
		
		updateThirst(thirstChange);
		
	}
	
	public void checkThirstEffects() {
		
		if (plugin.getPlayerManager().getPlayerObjectByPlayerName(playerName) == null) {
			
			return;
			
		}
		
		for (PotionEffect potionEffect : plugin.getServer().getPlayer(playerName).getActivePotionEffects()) {
			
			if (potionEffect.getType().equals(PotionEffectType.CONFUSION) || potionEffect.getType().equals(PotionEffectType.SLOW) || potionEffect.getType().equals(PotionEffectType.WEAKNESS)) {
			
				plugin.getServer().getPlayer(playerName).removePotionEffect(potionEffect.getType());
			
			}
			
		}
		
		if (thirst >= 16) {
			
			// full thirst, no effects
			
		} else if (thirst >= 9) {
			
			// full thirst, no effects
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10000, 0));
			
		} else if (thirst >= 4) {
			
			// full thirst, no effects
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 0));
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10000, 0));
			
		} else {
			
			// full thirst, no effects
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10000, 0));
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10000, 0));
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 10000, 0));
			
		}

	}
	
	public void updateHunger(Integer newHunger) {
		
		hunger = newHunger;
		
		checkHungerEffects();
		
	}
	
	public void checkHungerEffects() {

		if (plugin.getPlayerManager().getPlayerObjectByPlayerName(playerName) == null) {
			
			return; 
			
		}

		for (PotionEffect potionEffect : plugin.getServer().getPlayer(playerName).getActivePotionEffects()) {
			
			if (potionEffect.getType().equals(PotionEffectType.SLOW_DIGGING) || potionEffect.getType().equals(PotionEffectType.HARM) || potionEffect.getType().equals(PotionEffectType.BLINDNESS)) {
			
				plugin.getServer().getPlayer(playerName).removePotionEffect(potionEffect.getType());
			
			}
			
		}

		if (hunger == 20) {
			
			// full hunger, no effects

		} else if (hunger >= 9) {
			
			// full hunger, no effects
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10000, 0));

		} else if (hunger >= 4) {
			
			// full hunger, no effects
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10000, 0));
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 10000, 0));

		} else {
			
			// full hunger, no effects
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 10000, 0));
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 10000, 0));
			plugin.getServer().getPlayer(playerName).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10000, 0));
			
		}

	}
	
	public String getSerializedPlayerObject() {
	
		String playerObjectString;
		
		playerObjectString = playerName + "~" + joinDate + "~" + health + "~" + thirst + "~" + hunger;
		
		return playerObjectString;
	
	}
	

}
