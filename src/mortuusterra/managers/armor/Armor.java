package mortuusterra.managers.armor;

public enum Armor {
	Tier1(true, false, false, false),
	Tier2(true, false, false, false),
	Tier3(true, false, false, false),
	Tier4(false, true, true, false),
	Tier5(false, true, true, false),
	Tier6(false, true, true, false),
	Tier7(false, true, false, true),
	Tier8(false, true, false, true),
	Tier9(false, true, false, true);

	public Boolean GetIsCraftable = IsCraftable();
	public Boolean GetIsUpgradable = IsUpgradable();
	public Boolean GetIsFoundInLoot = IsFoundInLoot();
	public Boolean GetIsBossDrop = IsBossDrop();

	private Boolean IsCraftable;
	private Boolean IsUpgradable;
	private Boolean IsFoundInLoot;
	private Boolean IsBossDrop;

	private Boolean IsCraftable() {
		return IsCraftable;
	}

	private Boolean IsUpgradable() {
		return IsUpgradable;
	}

	private Boolean IsFoundInLoot() {
		return IsFoundInLoot;
	}

	private Boolean IsBossDrop() {
		return IsBossDrop;
	}

	private Armor(Boolean IsCraftable, Boolean IsUpgradable, Boolean IsFoundInLoot, Boolean IsBossDrop) {

		this.IsCraftable = IsCraftable;
		this.IsUpgradable = IsUpgradable;
		this.IsFoundInLoot = IsFoundInLoot;
		this.IsBossDrop = IsBossDrop;
	}

}
