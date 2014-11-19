package wingman;

import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Resources {

	private static Resources instance;
	public Image big_bullet, boss, bottom, bullet, bullet_left, bullet_right, 
				enemy1_1, enemy1_2, enemy1_3, enemy2_1, enemy2_2, 
				enemy2_3, enemy3_1, enemy3_2, enemy3_3, enemy4_1, 
				enemy4_2, enemy4_3, enemy_bullet1, enemy_bullet2, explosion1_1, 
				explosion1_2, explosion1_3, explosion1_4, explosion1_5, explosion1_6, 
				explosion2_1, explosion2_2, explosion2_3, explosion2_4, explosion2_5, 
				explosion2_6, explosion2_7, gameOver, health, health1, 
				health2, health3, island1, island2, island3, 
				life, player1_1, player1_2, player1_3, power_up, 
				score, water, youLose, youWin;

	public ResourceSpec big_bullet_spec, bullet_spec, bullet_left_spec, bullet_right_spec, enemy_bullet1_spec, enemy_bullet2_spec, 
				enemy1_spec, enemy2_spec, enemy3_spec, enemy4_spec, player1_spec, 
				power_up_spec;

	private Resources() {
		try {
			big_bullet = ImageIO.read(new File("../resources/big_bullet.png"));
			boss = ImageIO.read(new File("../resources/boss.png"));
			bottom = ImageIO.read(new File("../resources/bottom.png"));
			bullet = ImageIO.read(new File("../resources/bullet.png"));
			bullet_left = ImageIO.read(new File("../resources/bullet_left.png"));
			bullet_right = ImageIO.read(new File("../resources/bullet_right.png"));
			enemy1_1 = ImageIO.read(new File("../resources/enemy1_1.png"));
			enemy1_2 = ImageIO.read(new File("../resources/enemy1_2.png"));
			enemy1_3 = ImageIO.read(new File("../resources/enemy1_3.png"));
			enemy2_1 = ImageIO.read(new File("../resources/enemy2_1.png"));
			enemy2_2 = ImageIO.read(new File("../resources/enemy2_2.png"));
			enemy2_3 = ImageIO.read(new File("../resources/enemy2_3.png"));
			enemy3_1 = ImageIO.read(new File("../resources/enemy3_1.png"));
			enemy3_2 = ImageIO.read(new File("../resources/enemy3_2.png"));
			enemy3_3 = ImageIO.read(new File("../resources/enemy3_3.png"));
			enemy4_1 = ImageIO.read(new File("../resources/enemy4_1.png"));
			enemy4_2 = ImageIO.read(new File("../resources/enemy4_2.png"));
			enemy4_3 = ImageIO.read(new File("../resources/enemy4_3.png"));
			enemy_bullet1 = ImageIO.read(new File("../resources/enemy_bullet1.png"));
			enemy_bullet2 = ImageIO.read(new File("../resources/enemy_bullet2.png"));
			explosion1_1 = ImageIO.read(new File("../resources/explosion1_1.png"));
			explosion1_2 = ImageIO.read(new File("../resources/explosion1_2.png"));
			explosion1_3 = ImageIO.read(new File("../resources/explosion1_3.png"));
			explosion1_4 = ImageIO.read(new File("../resources/explosion1_4.png"));
			explosion1_5 = ImageIO.read(new File("../resources/explosion1_5.png"));
			explosion1_6 = ImageIO.read(new File("../resources/explosion1_6.png"));
			explosion2_1 = ImageIO.read(new File("../resources/explosion2_1.png"));
			explosion2_2 = ImageIO.read(new File("../resources/explosion2_2.png"));
			explosion2_3 = ImageIO.read(new File("../resources/explosion2_3.png"));
			explosion2_4 = ImageIO.read(new File("../resources/explosion2_4.png"));
			explosion2_5 = ImageIO.read(new File("../resources/explosion2_5.png"));
			explosion2_6 = ImageIO.read(new File("../resources/explosion2_6.png"));
			explosion2_7 = ImageIO.read(new File("../resources/explosion2_7.png"));
			gameOver = ImageIO.read(new File("../resources/gameOver.png"));
			health = ImageIO.read(new File("../resources/health.png"));
			health1 = ImageIO.read(new File("../resources/health1.png"));
			health2 = ImageIO.read(new File("../resources/health2.png"));
			health3 = ImageIO.read(new File("../resources/health3.png"));
			island1 = ImageIO.read(new File("../resources/island1.png"));
			island2 = ImageIO.read(new File("../resources/island2.png"));
			island3 = ImageIO.read(new File("../resources/island3.png"));
			life = ImageIO.read(new File("../resources/life.png"));
			player1_1 = ImageIO.read(new File("../resources/player1_1.png"));
			player1_2 = ImageIO.read(new File("../resources/player1_2.png"));
			player1_3 = ImageIO.read(new File("../resources/player1_3.png"));
			power_up = ImageIO.read(new File("../resources/power_up.png"));
			score = ImageIO.read(new File("../resources/score.png"));
			water = ImageIO.read(new File("../resources/water.png"));
			youLose = ImageIO.read(new File("../resources/youLose.png"));
			youWin = ImageIO.read(new File("../resources/youWin.png"));

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		big_bullet_spec = new ResourceSpec("big_bullet");
		bullet_spec = new ResourceSpec("bullet");
		bullet_left_spec = new ResourceSpec("bullet_left");
		bullet_right_spec = new ResourceSpec("bullet_right");
		enemy_bullet1_spec = new ResourceSpec("enemy_bullet1");
		enemy_bullet2_spec = new ResourceSpec("enemy_bullet2");
		enemy1_spec = new ResourceSpec("enemy1");
		enemy2_spec = new ResourceSpec("enemy2");
		enemy3_spec = new ResourceSpec("enemy3");
		enemy4_spec = new ResourceSpec("enemy4");
		player1_spec = new ResourceSpec("player1");
		power_up_spec = new ResourceSpec("power_up");
	}

	public static Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}

		return instance;
	}

	public class ResourceSpec {

		//Bounds
		public double center_x, center_y, top, bottom , left, right;
		public int number_of_shapes;

		private JSONParser parser;

		public ArrayList<Shape> shapes = new ArrayList<Shape>();

		public ResourceSpec(String name) {
			parser = new JSONParser();
			try {
				JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("../resources.json"));
				JSONObject objectSpecifications = (JSONObject) jsonObject.get(name);
				JSONObject bounds = (JSONObject) objectSpecifications.get("bounds");
				JSONArray center = (JSONArray) bounds.get("center");

				this.center_x = (double) center.get(0);
				this.center_y = (double) center.get(1);

				this.top = (double) bounds.get("top_edge");
				this.bottom = (double) bounds.get("bottom_edge");
				this.left = (double) bounds.get("left_edge");
				this.right = (double) bounds.get("right_edge");

				JSONObject collision = (JSONObject) objectSpecifications.get("collision");
				this.number_of_shapes = (int) collision.get("number_of_shapes");

				JSONObject JSONshapes = (JSONObject) collision.get("shapes");
				for (int i = 1; i <= number_of_shapes; i++) {
					JSONObject shape = (JSONObject) JSONshapes.get("shape" + i);
					JSONArray params = (JSONArray) shape.get("params");
					shapes.add(new Shape((String) shape.get("type"), params));
				}
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		public class Shape {

			public int x, y, width, height;
			public String type;

			public Shape(String type, JSONArray array) {
				this.type = type;
				this.x = (int) array.get(0);
				this.y = (int) array.get(1);
				this.width = (int) array.get(2);
				this.height = (int) array.get(3);
			}
		}
	}
}