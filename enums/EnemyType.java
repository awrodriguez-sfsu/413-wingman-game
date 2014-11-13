package enums;

public enum EnemyType {
	ENEMY1(1), ENEMY2(2), ENEMY3(3), ENEMY4(4);

	private final int enemyType;

	EnemyType(int enemyType) {
		this.enemyType = enemyType;
	}

	public int getEnemyType() {
		return enemyType;
	}
}
