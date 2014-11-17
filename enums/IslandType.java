package enums;

public enum IslandType {
	ISLAND1(1),
	ISLAND2(2),
	ISLAND3(3);

	private final int islandType;

	IslandType(int islandType) {
		this.islandType = islandType;
	}

	public int getIslandType() {
		return islandType;
	}
}
