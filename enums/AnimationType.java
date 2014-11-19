/**
 * 
 */
package enums;

/**
 * @author Anthony Rodriguez
 *
 */
public enum AnimationType {
	PLAYER1(1),
	ENEMY1(2),
	ENEMY2(3),
	ENEMY3(4),
	ENEMY4(5),
	ISLAND1(6),
	ISLAND2(7),
	ISLAND3(8),
	BACKGROUND(9),
	SMALL_EXPLOSION(10),
	LARGE_EXPLOSION(11),
	BIG_BULLET(12),
	BULLET(13),
	BULLET_LEFT(14),
	BULLET_RIGHT(15),
	ENEMY_WEAPON1(16),
	ENEMY_WEAPON2(17);

	private final int type;

	AnimationType(int type) {
		this.type = type;
	}

	public int getAnimationType() {
		return type;
	}
}
