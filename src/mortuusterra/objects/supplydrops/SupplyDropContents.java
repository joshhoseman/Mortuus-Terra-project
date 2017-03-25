package mortuusterra.objects.supplydrops;

public class SupplyDropContents {

	public Integer itemId;
	public Integer itemQuantity;
	public Double itemChance;

	public SupplyDropContents(int itemId, int itemQuantity, double itemChance) {
		
		this.itemId = itemId;
		this.itemQuantity = itemQuantity;
		this.itemChance = itemChance;
	}

}
