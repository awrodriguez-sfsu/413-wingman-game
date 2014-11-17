/**
 * 
 */
package enums;

/**
 * @author Anthony Rodriguez
 *
 */
public enum AnimationType {
	MAIN_CHARACTER(1),
	ENEMY1(2),
	ENEMY2(3),
	ENEMY3(4),
	ENEMY4(5),
	ISLAND1(6),
	ISLAND2(7),
	ISLAND3(8),
	BACKGROUND(9),
	LARGE_EXPLOSION(10),
	SMALL_EXPLOSION(11),
	MAIN_CHARACTER_PRIMARY_WEAPON(12),
	MAIN_CHARACTER_SECONDARY_WEAPON(13),
	ENEMY_WEAPON1(14),
	ENEMY_WEAPON2(15),
	ENEMY_WEAPON3(16),
	ENEMY_WEAPON4(17);

	private final int type;

	AnimationType(int type) {
		this.type = type;
	}

	public int getAnimationType() {
		return type;
	}
}
