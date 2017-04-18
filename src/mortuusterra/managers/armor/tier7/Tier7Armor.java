package mortuusterra.managers.armor.tier7;

import mortuusterra.managers.armor.Armor;

public enum Tier7Armor {
	Enchanted_Iron_Helmet(),
	Enchanted_Iron_Chest(),
	Enchanted_Iron_Legs(),
	Enchanted_Iron_Feet();
	
	
	public Boolean IsCraftable;
	public Boolean IsUpgradable;
	public Boolean IsFoundInLoot;
	public Boolean IsBossDrop;
	
	private Tier7Armor() {
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
