package wingman;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Resources {

	private static Resources instance;
	public Image bigBullet, boss, bottom, bullet, bulletLeft, bulletLeft_up, bulletRight, bulletRight_up, bullet_up, defiant_128, defiant_128_1, defiant_128_2, defiant_128_3, defiant_128_4, defiant_128_5, defiant_32, defiant_32_1, defiant_32_2, defiant_32_3, defiant_32_4, defiant_32_5, defiant_64, defiant_64_1, defiant_64_2, defiant_64_3, defiant_64_4, defiant_64_5, enemy1_1, enemy1_2, enemy1_3, enemy1_strip3, enemy2_1, enemy2_2, enemy2_3, enemy2_strip3, enemy3_1, enemy3_2, enemy3_3, enemy3_strip3, enemy4_1, enemy4_2, enemy4_3, enemy4_strip3, enemybullet1, enemybullet2, explosion1_1, explosion1_2, explosion1_3, explosion1_4, explosion1_5, explosion1_6, explosion1_strip6, explosion2_1, explosion2_2, explosion2_3, explosion2_4, explosion2_5, explosion2_6, explosion2_7, explosion2_strip7,
			galaxy_128, galaxy_128_1, galaxy_128_2, galaxy_128_3, galaxy_128_4, galaxy_128_5, galaxy_64, galaxy_64_1, galaxy_64_2, galaxy_64_3, galaxy_64_4, galaxy_64_5, gameOver, health, health1, health2, health3, island1, island2, island3, life, myplane_1, myplane_2, myplane_3, myplane_strip3, powerup, score, sovereign_128, sovereign_128_1, sovereign_128_2, sovereign_128_3, sovereign_128_4, sovereign_128_5, sovereign_64, sovereign_64_1, sovereign_64_2, sovereign_64_3, sovereign_64_4, sovereign_64_5, water, youLose, youWin;

	private Resources() {
		try {
			bigBullet = ImageIO.read(new File("../resources/bigBullet.png"));
			boss = ImageIO.read(new File("../resources/boss.png"));
			bottom = ImageIO.read(new File("../resources/bottom.png"));
			bullet = ImageIO.read(new File("../resources/bullet.png"));
			bulletLeft = ImageIO.read(new File("../resources/bulletLeft.png"));
			bulletLeft_up = ImageIO.read(new File("../resources/bulletLeft_up.png"));
			bulletRight = ImageIO.read(new File("../resources/bulletRight.png"));
			bulletRight_up = ImageIO.read(new File("../resources/bulletRight_up.png"));
			bullet_up = ImageIO.read(new File("../resources/bullet_up.png"));
			defiant_128 = ImageIO.read(new File("../resources/defiant_128.png"));
			defiant_128_1 = ImageIO.read(new File("../resources/defiant_128_1.png"));
			defiant_128_2 = ImageIO.read(new File("../resources/defiant_128_2.png"));
			defiant_128_3 = ImageIO.read(new File("../resources/defiant_128_3.png"));
			defiant_128_4 = ImageIO.read(new File("../resources/defiant_128_4.png"));
			defiant_128_5 = ImageIO.read(new File("../resources/defiant_128_5.png"));
			defiant_32 = ImageIO.read(new File("../resources/defiant_32.png"));
			defiant_32_1 = ImageIO.read(new File("../resources/defiant_32_1.png"));
			defiant_32_2 = ImageIO.read(new File("../resources/defiant_32_2.png"));
			defiant_32_3 = ImageIO.read(new File("../resources/defiant_32_3.png"));
			defiant_32_4 = ImageIO.read(new File("../resources/defiant_32_4.png"));
			defiant_32_5 = ImageIO.read(new File("../resources/defiant_32_5.png"));
			defiant_64 = ImageIO.read(new File("../resources/defiant_64.png"));
			defiant_64_1 = ImageIO.read(new File("../resources/defiant_64_1.png"));
			defiant_64_2 = ImageIO.read(new File("../resources/defiant_64_2.png"));
			defiant_64_3 = ImageIO.read(new File("../resources/defiant_64_3.png"));
			defiant_64_4 = ImageIO.read(new File("../resources/defiant_64_4.png"));
			defiant_64_5 = ImageIO.read(new File("../resources/defiant_64_5.png"));
			enemy1_1 = ImageIO.read(new File("../resources/enemy1_1.png"));
			enemy1_2 = ImageIO.read(new File("../resources/enemy1_2.png"));
			enemy1_3 = ImageIO.read(new File("../resources/enemy1_3.png"));
			enemy1_strip3 = ImageIO.read(new File("../resources/enemy1_strip3.png"));
			enemy2_1 = ImageIO.read(new File("../resources/enemy2_1.png"));
			enemy2_2 = ImageIO.read(new File("../resources/enemy2_2.png"));
			enemy2_3 = ImageIO.read(new File("../resources/enemy2_3.png"));
			enemy2_strip3 = ImageIO.read(new File("../resources/enemy2_strip3.png"));
			enemy3_1 = ImageIO.read(new File("../resources/enemy3_1.png"));
			enemy3_2 = ImageIO.read(new File("../resources/enemy3_2.png"));
			enemy3_3 = ImageIO.read(new File("../resources/enemy3_3.png"));
			enemy3_strip3 = ImageIO.read(new File("../resources/enemy3_strip3.png"));
			enemy4_1 = ImageIO.read(new File("../resources/enemy4_1.png"));
			enemy4_2 = ImageIO.read(new File("../resources/enemy4_2.png"));
			enemy4_3 = ImageIO.read(new File("../resources/enemy4_3.png"));
			enemy4_strip3 = ImageIO.read(new File("../resources/enemy4_strip3.png"));
			enemybullet1 = ImageIO.read(new File("../resources/enemybullet1.png"));
			enemybullet2 = ImageIO.read(new File("../resources/enemybullet2.png"));
			explosion1_1 = ImageIO.read(new File("../resources/explosion1_1.png"));
			explosion1_2 = ImageIO.read(new File("../resources/explosion1_2.png"));
			explosion1_3 = ImageIO.read(new File("../resources/explosion1_3.png"));
			explosion1_4 = ImageIO.read(new File("../resources/explosion1_4.png"));
			explosion1_5 = ImageIO.read(new File("../resources/explosion1_5.png"));
			explosion1_6 = ImageIO.read(new File("../resources/explosion1_6.png"));
			explosion1_strip6 = ImageIO.read(new File("../resources/explosion1_strip6.png"));
			explosion2_1 = ImageIO.read(new File("../resources/explosion2_1.png"));
			explosion2_2 = ImageIO.read(new File("../resources/explosion2_2.png"));
			explosion2_3 = ImageIO.read(new File("../resources/explosion2_3.png"));
			explosion2_4 = ImageIO.read(new File("../resources/explosion2_4.png"));
			explosion2_5 = ImageIO.read(new File("../resources/explosion2_5.png"));
			explosion2_6 = ImageIO.read(new File("../resources/explosion2_6.png"));
			explosion2_7 = ImageIO.read(new File("../resources/explosion2_7.png"));
			explosion2_strip7 = ImageIO.read(new File("../resources/explosion2_strip7.png"));
			galaxy_128 = ImageIO.read(new File("../resources/galaxy_128.png"));
			galaxy_128_1 = ImageIO.read(new File("../resources/galaxy_128_1.png"));
			galaxy_128_2 = ImageIO.read(new File("../resources/galaxy_128_2.png"));
			galaxy_128_3 = ImageIO.read(new File("../resources/galaxy_128_3.png"));
			galaxy_128_4 = ImageIO.read(new File("../resources/galaxy_128_4.png"));
			galaxy_128_5 = ImageIO.read(new File("../resources/galaxy_128_5.png"));
			galaxy_64 = ImageIO.read(new File("../resources/galaxy_64.png"));
			galaxy_64_1 = ImageIO.read(new File("../resources/galaxy_64_1.png"));
			galaxy_64_2 = ImageIO.read(new File("../resources/galaxy_64_2.png"));
			galaxy_64_3 = ImageIO.read(new File("../resources/galaxy_64_3.png"));
			galaxy_64_4 = ImageIO.read(new File("../resources/galaxy_64_4.png"));
			galaxy_64_5 = ImageIO.read(new File("../resources/galaxy_64_5.png"));
			gameOver = ImageIO.read(new File("../resources/gameOver.png"));
			health = ImageIO.read(new File("../resources/health.png"));
			health1 = ImageIO.read(new File("../resources/health1.png"));
			health2 = ImageIO.read(new File("../resources/health2.png"));
			health3 = ImageIO.read(new File("../resources/health3.png"));
			island1 = ImageIO.read(new File("../resources/island1.png"));
			island2 = ImageIO.read(new File("../resources/island2.png"));
			island3 = ImageIO.read(new File("../resources/island3.png"));
			life = ImageIO.read(new File("../resources/life.png"));
			myplane_1 = ImageIO.read(new File("../resources/myplane_1.png"));
			myplane_2 = ImageIO.read(new File("../resources/myplane_2.png"));
			myplane_3 = ImageIO.read(new File("../resources/myplane_3.png"));
			myplane_strip3 = ImageIO.read(new File("../resources/myplane_strip3.png"));
			powerup = ImageIO.read(new File("../resources/powerup.png"));
			score = ImageIO.read(new File("../resources/score.png"));
			sovereign_128 = ImageIO.read(new File("../resources/sovereign_128.png"));
			sovereign_128_1 = ImageIO.read(new File("../resources/sovereign_128_1.png"));
			sovereign_128_2 = ImageIO.read(new File("../resources/sovereign_128_2.png"));
			sovereign_128_3 = ImageIO.read(new File("../resources/sovereign_128_3.png"));
			sovereign_128_4 = ImageIO.read(new File("../resources/sovereign_128_4.png"));
			sovereign_128_5 = ImageIO.read(new File("../resources/sovereign_128_5.png"));
			sovereign_64 = ImageIO.read(new File("../resources/sovereign_64.png"));
			sovereign_64_1 = ImageIO.read(new File("../resources/sovereign_64_1.png"));
			sovereign_64_2 = ImageIO.read(new File("../resources/sovereign_64_2.png"));
			sovereign_64_3 = ImageIO.read(new File("../resources/sovereign_64_3.png"));
			sovereign_64_4 = ImageIO.read(new File("../resources/sovereign_64_4.png"));
			sovereign_64_5 = ImageIO.read(new File("../resources/sovereign_64_5.png"));
			water = ImageIO.read(new File("../resources/water.png"));
			youLose = ImageIO.read(new File("../resources/youLose.png"));
			youWin = ImageIO.read(new File("../resources/youWin.png"));

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