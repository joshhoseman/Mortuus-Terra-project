package mortuusterra.managers.armor.tier9;

import mortuusterra.managers.armor.Armor;

public enum Tier9Armor {
	Diamond_Helmet(),
	Diamond_Chest(),
	Diamond_Legs(),
	Diamond_Feet();
	
	
	public Boolean IsCraftable;
	public Boolean IsUpgradable;
	public Boolean IsFoundInLoot;
	public Boolean IsBossDrop;
	
	private Tier9Armor() {
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
