package enums;

/**
 * @author Anthony Rodriguez
 *
 */
public enum GameObjectType {
	MAIN_CHARACTER(1),
	ENEMY(2),
	PROJECTILE(3),
	POWERUP(4),
	BACKGROUND(5);

	private final int gameObjectType;

	GameObjectType(int gameObjectType) {
		this.gameObjectType = gameObjectType;
	}

	public int getGameObjectType() {
		return gameObjectType;
	}
}
