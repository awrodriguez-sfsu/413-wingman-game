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
	POWER_UP(12, true, "power_up"),
	ISLAND1(13, false, ""),
	ISLAND2(14, false, ""),
	ISLAND3(15, false, ""),
	BACKGROUND(16, false, ""),
	SMALL_EXPLOSION(17, false, ""),
	LARGE_EXPLOSION(18, false, "");

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
