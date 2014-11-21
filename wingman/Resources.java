package wingman;

import java.applet.AudioClip;
import java.awt.Image;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JApplet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Resources {

	private static Resources instance;
	public Image big_bullet, bullet, bullet_left, bullet_right, enemy1_1, enemy1_2, enemy1_3, enemy2_1, enemy2_2, enemy2_3, enemy3_1, enemy3_2, enemy3_3, enemy4_1, enemy4_2, enemy4_3, enemy_bullet1, enemy_bullet2, explosion1_1, explosion1_2, explosion1_3, explosion1_4, explosion1_5, explosion1_6, explosion2_1, explosion2_2, explosion2_3, explosion2_4, explosion2_5, explosion2_6, explosion2_7, game_over, health1, health1_2, health1_3, health1_4, health1_5, health2_2, health2_3, health2_4, health2_5, health6, hud_bottom, hud_tile, island1, island2, island3, life, player1_1, player1_2, player1_3, power_up, water;

	public ImageSpecification game_over_image_spec, hud_health_image_spec, hud_health_position1_image_spec, hud_health_position2_image_spec, hud_bottom_image_spec, life_image_spec, hud_tile_image_spec;

	public static SolidResourceSpecification big_bullet_spec, bullet_spec, bullet_left_spec, bullet_right_spec, enemy_bullet1_spec, enemy_bullet2_spec, enemy1_spec, enemy2_spec, enemy3_spec, enemy4_spec, player1_spec, power_up_spec;

	public AudioClip background_sound, large_explosion_sound, small_explosion_sound;

	private Resources() {
		try {
			big_bullet = ImageIO.read(new File("../resources/big_bullet.png"));
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
			game_over = ImageIO.read(new File("../resources/game_over.png"));
			health1 = ImageIO.read(new File("../resources/health1.png"));
			health1_2 = ImageIO.read(new File("../resources/health1_2.png"));
			health1_3 = ImageIO.read(new File("../resources/health1_3.png"));
			health1_4 = ImageIO.read(new File("../resources/health1_4.png"));
			health1_5 = ImageIO.read(new File("../resources/health1_5.png"));
			health2_2 = ImageIO.read(new File("../resources/health2_2.png"));
			health2_3 = ImageIO.read(new File("../resources/health2_3.png"));
			health2_4 = ImageIO.read(new File("../resources/health2_4.png"));
			health2_5 = ImageIO.read(new File("../resources/health2_5.png"));
			health6 = ImageIO.read(new File("../resources/health6.png"));
			hud_bottom = ImageIO.read(new File("../resources/hud_bottom.png"));
			hud_tile = ImageIO.read(new File("../resources/hud_tile.png"));
			island1 = ImageIO.read(new File("../resources/island1.png"));
			island2 = ImageIO.read(new File("../resources/island2.png"));
			island3 = ImageIO.read(new File("../resources/island3.png"));
			life = ImageIO.read(new File("../resources/life.png"));
			player1_1 = ImageIO.read(new File("../resources/player1_1.png"));
			player1_2 = ImageIO.read(new File("../resources/player1_2.png"));
			player1_3 = ImageIO.read(new File("../resources/player1_3.png"));
			power_up = ImageIO.read(new File("../resources/power_up.png"));
			water = ImageIO.read(new File("../resources/water.png"));

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		try {
			background_sound = JApplet.newAudioClip(( new File("../resources/background_sound.wav").toURI().toURL() ));
			large_explosion_sound = JApplet.newAudioClip(( new File("../resources/large_explosion_sound.wav").toURI().toURL() ));
			small_explosion_sound = JApplet.newAudioClip(( new File("../resources/small_explosion_sound.wav").toURI().toURL() ));

		} catch (MalformedURLException exception) {
			exception.printStackTrace();
		}

		big_bullet_spec = new SolidResourceSpecification("big_bullet");
		bullet_spec = new SolidResourceSpecification("bullet");
		bullet_left_spec = new SolidResourceSpecification("bullet_left");
		bullet_right_spec = new SolidResourceSpecification("bullet_right");
		enemy_bullet1_spec = new SolidResourceSpecification("enemy_bullet1");
		enemy_bullet2_spec = new SolidResourceSpecification("enemy_bullet2");
		enemy1_spec = new SolidResourceSpecification("enemy1");
		enemy2_spec = new SolidResourceSpecification("enemy2");
		enemy3_spec = new SolidResourceSpecification("enemy3");
		enemy4_spec = new SolidResourceSpecification("enemy4");
		player1_spec = new SolidResourceSpecification("player1");
		power_up_spec = new SolidResourceSpecification("power_up");

		game_over_image_spec = new ImageSpecification("game_over");
		hud_health_image_spec = new ImageSpecification("hud_health");
		hud_health_position1_image_spec = new ImageSpecification("hud_health_position1");
		hud_health_position2_image_spec = new ImageSpecification("hud_health_position2");
		hud_bottom_image_spec = new ImageSpecification("hud_bottom");
		life_image_spec = new ImageSpecification("life");
		hud_tile_image_spec = new ImageSpecification("hud_tile");
	}

	public static Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}

		return instance;
	}

	public static SolidResourceSpecification getSolidSpec(String name) {

		if (instance == null) {
			instance = new Resources();
		}

		switch (name) {
			case "big_bullet":
				return big_bullet_spec;
			case "bullet":
				return bullet_spec;
			case "bullet_left":
				return bullet_left_spec;
			case "bullet_right":
				return bullet_right_spec;
			case "enemy_bullet1":
				return enemy_bullet1_spec;
			case "enemy_bullet2":
				return enemy_bullet2_spec;
			case "enemy1":
				return enemy1_spec;
			case "enemy2":
				return enemy2_spec;
			case "enemy3":
				return enemy3_spec;
			case "enemy4":
				return enemy4_spec;
			case "player1":
				return player1_spec;
			case "power_up":
				return power_up_spec;
			default:
				return null;
		}
	}

	public class SolidResourceSpecification {

		// Bounds
		public double center_x, center_y, top, bottom, left, right, front;
		public long number_of_shapes;
		public String name;

		private JSONParser parser;

		public ArrayList<Shape> shapes = new ArrayList<Shape>();

		public SolidResourceSpecification(String name) {
			parser = new JSONParser();
			try {
				JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("../resources.json"));
				JSONObject objectSpecifications = (JSONObject) jsonObject.get(name);
				JSONObject bounds = (JSONObject) objectSpecifications.get("bounds");
				JSONArray center = (JSONArray) bounds.get("center");

				this.center_x = (double) Double.parseDouble(center.get(0).toString());
				this.center_y = (double) Double.parseDouble(center.get(1).toString());

				this.top = (double) Double.parseDouble(bounds.get("top_edge").toString());
				this.bottom = (double) Double.parseDouble(bounds.get("bottom_edge").toString());
				this.left = (double) Double.parseDouble(bounds.get("left_edge").toString());
				this.right = (double) Double.parseDouble(bounds.get("right_edge").toString());
				this.front = (double) Double.parseDouble(bounds.get("front_edge").toString());

				JSONObject collision = (JSONObject) objectSpecifications.get("collision");
				this.number_of_shapes = (long) Long.parseLong(collision.get("number_of_shapes").toString());

				JSONObject JSONshapes = (JSONObject) collision.get("shapes");
				for (int i = 1; i <= number_of_shapes; i++) {
					JSONObject shape = (JSONObject) JSONshapes.get("shape" + i);
					JSONArray params = (JSONArray) shape.get("params");
					shapes.add(new Shape((String) shape.get("type"), params));
				}

				this.name = name;

			} catch (Exception exception) {
				exception.printStackTrace();
			}

			parser = null;
		}

		public class Shape {

			public long x, y, width, height;
			public String type;

			public Shape(String type, JSONArray array) {
				this.type = type;
				this.x = (long) Long.parseLong(array.get(0).toString());
				this.y = (long) Long.parseLong(array.get(1).toString());
				this.width = (long) Long.parseLong(array.get(2).toString());
				this.height = (long) Long.parseLong(array.get(3).toString());
			}
		}
	}

	public class ImageSpecification {

		public double center_x, center_y, top, bottom, left, right;
		public String name;

		private JSONParser parser;

		public ImageSpecification(String name) {
			parser = new JSONParser();
			try {
				JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("../resources.json"));
				JSONObject objectSpecifications = (JSONObject) jsonObject.get(name);
				JSONObject bounds = (JSONObject) objectSpecifications.get("bounds");
				JSONArray center = (JSONArray) bounds.get("center");
				this.center_x = (double) Double.parseDouble(center.get(0).toString());
				this.center_y = (double) Double.parseDouble(center.get(1).toString());
				this.top = (double) Double.parseDouble(bounds.get("top_edge").toString());
				this.bottom = (double) Double.parseDouble(bounds.get("bottom_edge").toString());
				this.left = (double) Double.parseDouble(bounds.get("left_edge").toString());
				this.right = (double) Double.parseDouble(bounds.get("right_edge").toString());
				this.name = name;

			} catch (Exception exception) {
				exception.printStackTrace();
			}

			parser = null;
		}

	}
}