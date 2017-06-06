package mortuusterra.managers.armor.tier1;

import java.util.concurrent.ThreadLocalRandom;

import mortuusterra.managers.armor.Armor;
//import mortuusterra.managers.armor.crafting.ArmorManager;

public enum Tier1Armor {
	Leather_Chest("50");

	public Boolean IsCraftable;
	public Boolean IsUpgradable;
	public Boolean IsFoundInLoot;
	public Boolean IsBossDrop;
	public String Tier1Chance;

	public void AddItemsToChest() {
		ThreadLocalRandom random = ThreadLocalRandom.current();

		if ((random.nextDouble() * 100) <= 50) {
			//ArmorManager.AddTier1ToChest();

		}
	}

	private Tier1Armor(String chance) {
		this.Tier1Chance = chance;
		if (Armor.Tier1.GetIsCraftable == true) {
			IsCraftable = true;
		} else {
			IsCraftable = false;
		}
		if (Armor.Tier1.GetIsUpgradable == true) {
			IsUpgradable = true;
		} else {
			IsUpgradable = false;
		}
		if (Armor.Tier1.GetIsFoundInLoot == true) {
			IsFoundInLoot = true;
		} else {
			IsFoundInLoot = false;
		}
		if (Armor.Tier1.GetIsBossDrop == true) {
			IsBossDrop = true;
		} else {
			IsBossDrop = false;
		}
	}
}
