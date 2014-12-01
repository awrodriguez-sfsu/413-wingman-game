/**
 * 
 */
package enums;

/**
 * @author Anthony Rodriguez
 *
 */
public enum AnimationType {
	BIG_BULLET(1, true, "big_bullet"),
	BULLET(2, true, "bullet"),
	BULLET_LEFT(3, true, "bullet_left"),
	BULLET_RIGHT(4, true, "bullet_right"),
	ENEMY_BULLET1(5, true, "enemy_bullet1"),
	ENEMY_BULLET2(6, true, "enemy_bullet2"),
	ENEMY1(7, true, "enemy1"),
	ENEMY2(8, true, "enemy2"),
	ENEMY3(9, true, "enemy3"),
	ENEMY4(10, true, "enemy4"),
	PLAYER1(11, true, "player1"),
	PLAYER2(12, true, "player2"),
	POWER_UP(13, true, "power_up"),
	LIFE1(14, true, "life1_pickup"),
	LIFE2(15, true, "life2_pickup"),
	ISLAND1(16, false, ""),
	ISLAND2(17, false, ""),
	ISLAND3(18, false, ""),
	BACKGROUND(19, false, ""),
	SMALL_EXPLOSION(20, false, ""),
	LARGE_EXPLOSION(21, false, "");

	private final int type;
	private final boolean solid;
	private final String name;

	AnimationType(int type, boolean solid, String name) {
		this.type = type;
		this.solid = solid;
		this.name = name;
	}

	public int getAnimationType() {
		return type;
	}

	public boolean isSolid() {
		return solid;
	}

	public String getName() {
		return name;
	}
}
