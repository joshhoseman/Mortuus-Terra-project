package mortuusterra.managers.armor.tier3;

import mortuusterra.managers.armor.Armor;

public enum Tier3Armor {
	Enchanted_Leather_Helmet(),
	Iron_Chest(),
	Iron_Legs(),
	Enchanted_Leather_feet();
	
	
	public Boolean IsCraftable;
	public Boolean IsUpgradable;
	public Boolean IsFoundInLoot;
	public Boolean IsBossDrop;
	
	private Tier3Armor() {
		if (Armor.Tier1.GetIsCraftable = true) {
			IsCraftable = true;
		} else {
			IsCraftable = false;
		}
		if (Armor.Tier1.GetIsUpgradable = true) {
			IsUpgradable = true;
		} else {
			IsUpgradable = false;
		}
		if (Armor.Tier1.GetIsFoundInLoot = true) {
			IsFoundInLoot = true;
		} else {
			IsFoundInLoot = false;
		}
		if (Armor.Tier1.GetIsBossDrop = true) {
			IsBossDrop = true;
		} else {
			IsBossDrop = false;
		}
		
	}

}
