package wingman;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Resources {

	private static Resources instance;
	public Image sovereign_128_3, youWin, explosion2_3, galaxy_128_3, island1, powerup, defiant_64_4, defiant_128, sovereign_64, enemy2_3, defiant_32_5, bulletLeft_up, enemy3_2, sovereign_128, enemy1_1, enemy1_2, explosion2_4, defiant_64_2, myplane_3, galaxy_64_5, defiant_32, explosion2_6, defiant_128_4, island3, enemy1_3, island2, explosion2_2, defiant_32_1, defiant_128_3, galaxy_128_5, defiant_64_1, enemy4_3, health1, galaxy_64, galaxy_64_3, bulletRight, explosion2_7, boss, enemy4_1, enemy4_2, defiant_32_3, defiant_32_2, enemybullet1, explosion1_strip6, sovereign_128_5, explosion2_1, sovereign_128_2, galaxy_64_4, myplane_strip3, enemy3_1, galaxy_64_1, gameOver, health3, enemy2_strip3, explosion1_3, bottom, explosion1_6, defiant_64_5, bullet, enemy1_strip3, explosion1_2, explosion1_1,
			bigBullet, sovereign_64_3, galaxy_128, sovereign_128_1, explosion2_strip7, galaxy_128_1, myplane_2, defiant_128_1, water, enemy4_strip3, enemy3_3, sovereign_64_5, life, defiant_128_5, explosion2_5, bulletRight_up, galaxy_128_4, defiant_128_2, enemybullet2, sovereign_128_4, galaxy_128_2, sovereign_64_2, sovereign_64_4, sovereign_64_1, bullet_up, enemy3_strip3, health2, explosion1_4, youLose, defiant_64, myplane_1, bulletLeft, score, explosion1_5, enemy2_2, health, galaxy_64_2, defiant_64_3, defiant_32_4, enemy2_1;

	private Resources() {
		try {
			sovereign_128_3 = ImageIO.read(new File("../Resources/sovereign_128_3.png"));
			youWin = ImageIO.read(new File("../Resources/youWin.png"));
			explosion2_3 = ImageIO.read(new File("../Resources/explosion2_3.png"));
			galaxy_128_3 = ImageIO.read(new File("../Resources/galaxy_128_3.png"));
			island1 = ImageIO.read(new File("../Resources/island1.png"));
			powerup = ImageIO.read(new File("../Resources/powerup.png"));
			defiant_64_4 = ImageIO.read(new File("../Resources/defiant_64_4.png"));
			defiant_128 = ImageIO.read(new File("../Resources/defiant_128.png"));
			sovereign_64 = ImageIO.read(new File("../Resources/sovereign_64.png"));
			enemy2_3 = ImageIO.read(new File("../Resources/enemy2_3.png"));
			defiant_32_5 = ImageIO.read(new File("../Resources/defiant_32_5.png"));
			bulletLeft_up = ImageIO.read(new File("../Resources/bulletLeft_up.png"));
			enemy3_2 = ImageIO.read(new File("../Resources/enemy3_2.png"));
			sovereign_128 = ImageIO.read(new File("../Resources/sovereign_128.png"));
			enemy1_1 = ImageIO.read(new File("../Resources/enemy1_1.png"));
			enemy1_2 = ImageIO.read(new File("../Resources/enemy1_2.png"));
			explosion2_4 = ImageIO.read(new File("../Resources/explosion2_4.png"));
			defiant_64_2 = ImageIO.read(new File("../Resources/defiant_64_2.png"));
			myplane_3 = ImageIO.read(new File("../Resources/myplane_3.png"));
			galaxy_64_5 = ImageIO.read(new File("../Resources/galaxy_64_5.png"));
			defiant_32 = ImageIO.read(new File("../Resources/defiant_32.png"));
			explosion2_6 = ImageIO.read(new File("../Resources/explosion2_6.png"));
			defiant_128_4 = ImageIO.read(new File("../Resources/defiant_128_4.png"));
			island3 = ImageIO.read(new File("../Resources/island3.png"));
			enemy1_3 = ImageIO.read(new File("../Resources/enemy1_3.png"));
			island2 = ImageIO.read(new File("../Resources/island2.png"));
			explosion2_2 = ImageIO.read(new File("../Resources/explosion2_2.png"));
			defiant_32_1 = ImageIO.read(new File("../Resources/defiant_32_1.png"));
			defiant_128_3 = ImageIO.read(new File("../Resources/defiant_128_3.png"));
			galaxy_128_5 = ImageIO.read(new File("../Resources/galaxy_128_5.png"));
			defiant_64_1 = ImageIO.read(new File("../Resources/defiant_64_1.png"));
			enemy4_3 = ImageIO.read(new File("../Resources/enemy4_3.png"));
			health1 = ImageIO.read(new File("../Resources/health1.png"));
			galaxy_64 = ImageIO.read(new File("../Resources/galaxy_64.png"));
			galaxy_64_3 = ImageIO.read(new File("../Resources/galaxy_64_3.png"));
			bulletRight = ImageIO.read(new File("../Resources/bulletRight.png"));
			explosion2_7 = ImageIO.read(new File("../Resources/explosion2_7.png"));
			boss = ImageIO.read(new File("../Resources/boss.png"));
			enemy4_1 = ImageIO.read(new File("../Resources/enemy4_1.png"));
			enemy4_2 = ImageIO.read(new File("../Resources/enemy4_2.png"));
			defiant_32_3 = ImageIO.read(new File("../Resources/defiant_32_3.png"));
			defiant_32_2 = ImageIO.read(new File("../Resources/defiant_32_2.png"));
			enemybullet1 = ImageIO.read(new File("../Resources/enemybullet1.png"));
			explosion1_strip6 = ImageIO.read(new File("../Resources/explosion1_strip6.png"));
			sovereign_128_5 = ImageIO.read(new File("../Resources/sovereign_128_5.png"));
			explosion2_1 = ImageIO.read(new File("../Resources/explosion2_1.png"));
			sovereign_128_2 = ImageIO.read(new File("../Resources/sovereign_128_2.png"));
			galaxy_64_4 = ImageIO.read(new File("../Resources/galaxy_64_4.png"));
			myplane_strip3 = ImageIO.read(new File("../Resources/myplane_strip3.png"));
			enemy3_1 = ImageIO.read(new File("../Resources/enemy3_1.png"));
			galaxy_64_1 = ImageIO.read(new File("../Resources/galaxy_64_1.png"));
			gameOver = ImageIO.read(new File("../Resources/gameOver.png"));
			health3 = ImageIO.read(new File("../Resources/health3.png"));
			enemy2_strip3 = ImageIO.read(new File("../Resources/enemy2_strip3.png"));
			explosion1_3 = ImageIO.read(new File("../Resources/explosion1_3.png"));
			bottom = ImageIO.read(new File("../Resources/bottom.png"));
			explosion1_6 = ImageIO.read(new File("../Resources/explosion1_6.png"));
			defiant_64_5 = ImageIO.read(new File("../Resources/defiant_64_5.png"));
			bullet = ImageIO.read(new File("../Resources/bullet.png"));
			enemy1_strip3 = ImageIO.read(new File("../Resources/enemy1_strip3.png"));
			explosion1_2 = ImageIO.read(new File("../Resources/explosion1_2.png"));
			explosion1_1 = ImageIO.read(new File("../Resources/explosion1_1.png"));
			bigBullet = ImageIO.read(new File("../Resources/bigBullet.png"));
			sovereign_64_3 = ImageIO.read(new File("../Resources/sovereign_64_3.png"));
			galaxy_128 = ImageIO.read(new File("../Resources/galaxy_128.png"));
			sovereign_128_1 = ImageIO.read(new File("../Resources/sovereign_128_1.png"));
			explosion2_strip7 = ImageIO.read(new File("../Resources/explosion2_strip7.png"));
			galaxy_128_1 = ImageIO.read(new File("../Resources/galaxy_128_1.png"));
			myplane_2 = ImageIO.read(new File("../Resources/myplane_2.png"));
			defiant_128_1 = ImageIO.read(new File("../Resources/defiant_128_1.png"));
			water = ImageIO.read(new File("../Resources/water.png"));
			enemy4_strip3 = ImageIO.read(new File("../Resources/enemy4_strip3.png"));
			enemy3_3 = ImageIO.read(new File("../Resources/enemy3_3.png"));
			sovereign_64_5 = ImageIO.read(new File("../Resources/sovereign_64_5.png"));
			life = ImageIO.read(new File("../Resources/life.png"));
			defiant_128_5 = ImageIO.read(new File("../Resources/defiant_128_5.png"));
			explosion2_5 = ImageIO.read(new File("../Resources/explosion2_5.png"));
			bulletRight_up = ImageIO.read(new File("../Resources/bulletRight_up.png"));
			galaxy_128_4 = ImageIO.read(new File("../Resources/galaxy_128_4.png"));
			defiant_128_2 = ImageIO.read(new File("../Resources/defiant_128_2.png"));
			enemybullet2 = ImageIO.read(new File("../Resources/enemybullet2.png"));
			sovereign_128_4 = ImageIO.read(new File("../Resources/sovereign_128_4.png"));
			galaxy_128_2 = ImageIO.read(new File("../Resources/galaxy_128_2.png"));
			sovereign_64_2 = ImageIO.read(new File("../Resources/sovereign_64_2.png"));
			sovereign_64_4 = ImageIO.read(new File("../Resources/sovereign_64_4.png"));
			sovereign_64_1 = ImageIO.read(new File("../Resources/sovereign_64_1.png"));
			bullet_up = ImageIO.read(new File("../Resources/bullet_up.png"));
			enemy3_strip3 = ImageIO.read(new File("../Resources/enemy3_strip3.png"));
			health2 = ImageIO.read(new File("../Resources/health2.png"));
			explosion1_4 = ImageIO.read(new File("../Resources/explosion1_4.png"));
			youLose = ImageIO.read(new File("../Resources/youLose.png"));
			defiant_64 = ImageIO.read(new File("../Resources/defiant_64.png"));
			myplane_1 = ImageIO.read(new File("../Resources/myplane_1.png"));
			bulletLeft = ImageIO.read(new File("../Resources/bulletLeft.png"));
			score = ImageIO.read(new File("../Resources/score.png"));
			explosion1_5 = ImageIO.read(new File("../Resources/explosion1_5.png"));
			enemy2_2 = ImageIO.read(new File("../Resources/enemy2_2.png"));
			health = ImageIO.read(new File("../Resources/health.png"));
			galaxy_64_2 = ImageIO.read(new File("../Resources/galaxy_64_2.png"));
			defiant_64_3 = ImageIO.read(new File("../Resources/defiant_64_3.png"));
			defiant_32_4 = ImageIO.read(new File("../Resources/defiant_32_4.png"));
			enemy2_1 = ImageIO.read(new File("../Resources/enemy2_1.png"));

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	public static Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}

		return instance;
	}
}