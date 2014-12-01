package wingman;

import java.applet.AudioClip;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JApplet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import enums.AnimationType;
import animations.Animation;

public class Resources {

	private static Resources instance;

	public static HashMap<AnimationType, Animation> animations = new HashMap<AnimationType, Animation>();

	// Images
	public Image big_bullet, bullet, bullet_left, bullet_right, enemy1_1, enemy1_2, enemy1_3,
			enemy2_1, enemy2_2, enemy2_3, enemy3_1, enemy3_2, enemy3_3, enemy4_1, enemy4_2,
			enemy4_3, enemy_bullet1, enemy_bullet2, explosion1_1, explosion1_2, explosion1_3,
			explosion1_4, explosion1_5, explosion1_6, explosion2_1, explosion2_2, explosion2_3,
			explosion2_4, explosion2_5, explosion2_6, explosion2_7, game_over, health1, health1_2,
			health1_3, health1_4, health1_5, health2_2, health2_3, health2_4, health2_5, health6,
			hud_bottom, hud_tile, island1, island2, island3, life1, life1_pickup, life2,
			life2_pickup, player1_1, player1_2, player1_3, player2_1, player2_2, player2_3,
			power_up, water;

	// Animations
	public Animation player1Animation, player2Animation, enemy1Animation, enemy2Animation,
			enemy3Animation, enemy4Animation, backgroundAnimation, island1Animation,
			island2Animation, island3Animation, life1Animation, life2Animation, powerUpAnimation,
			bulletAnimation, bullet_leftAnimation, bullet_rightAnimation, big_bulletAnimation,
			enemyWeapon1Animation, enemyWeapon2Animation;

	// Image Specifications
	public ImageSpecification game_over_image_spec, hud_health_image_spec,
			hud_health_position1_image_spec, hud_health_position2_image_spec,
			hud_bottom_image_spec, life1_image_spec, life2_image_spec, hud_tile_image_spec;

	// Solid Image Specifications
	public SolidResourceSpecification big_bullet_spec, bullet_spec, bullet_left_spec,
			bullet_right_spec, enemy_bullet1_spec, enemy_bullet2_spec, enemy1_spec, enemy2_spec,
			enemy3_spec, enemy4_spec, player1_spec, player2_spec, life1_pickup_spec,
			life2_pickup_spec, power_up_spec;

	// Audio Clips
	public AudioClip background_sound, game_over_sound, large_explosion_sound,
			small_explosion_sound;

	// Score Names
	public String score1;
	public String score2;
	public String score3;
	public String score4;
	public String score5;

	// Score Points
	public int scoreNumber1;
	public int scoreNumber2;
	public int scoreNumber3;
	public int scoreNumber4;
	public int scoreNumber5;

	// Constructor
	private Resources() {
		try {
			// Get images
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
			life1 = ImageIO.read(new File("../resources/life1.png"));
			life1_pickup = ImageIO.read(new File("../resources/life1_pickup.png"));
			life2 = ImageIO.read(new File("../resources/life2.png"));
			life2_pickup = ImageIO.read(new File("../resources/life2_pickup.png"));
			player1_1 = ImageIO.read(new File("../resources/player1_1.png"));
			player1_2 = ImageIO.read(new File("../resources/player1_2.png"));
			player1_3 = ImageIO.read(new File("../resources/player1_3.png"));
			player2_1 = ImageIO.read(new File("../resources/player2_1.png"));
			player2_2 = ImageIO.read(new File("../resources/player2_2.png"));
			player2_3 = ImageIO.read(new File("../resources/player2_3.png"));
			power_up = ImageIO.read(new File("../resources/power_up.png"));
			water = ImageIO.read(new File("../resources/water.png"));

		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		// Get sounds
		try {
			background_sound = JApplet.newAudioClip(( new File("../resources/background_sound.wav").toURI().toURL() ));
			game_over_sound = JApplet.newAudioClip(( new File("../resources/game_over_sound.wav").toURI().toURL() ));
			large_explosion_sound = JApplet.newAudioClip(( new File("../resources/large_explosion_sound.wav").toURI().toURL() ));
			small_explosion_sound = JApplet.newAudioClip(( new File("../resources/small_explosion_sound.wav").toURI().toURL() ));

		} catch (MalformedURLException exception) {
			exception.printStackTrace();
		}

		// Get solid image specifications
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
		player2_spec = new SolidResourceSpecification("player2");
		life1_pickup_spec = new SolidResourceSpecification("life1_pickup");
		life2_pickup_spec = new SolidResourceSpecification("life2_pickup");
		power_up_spec = new SolidResourceSpecification("power_up");

		// Get image specifications
		game_over_image_spec = new ImageSpecification("game_over");
		hud_health_image_spec = new ImageSpecification("hud_health");
		hud_health_position1_image_spec = new ImageSpecification("hud_health_position1");
		hud_health_position2_image_spec = new ImageSpecification("hud_health_position2");
		hud_bottom_image_spec = new ImageSpecification("hud_bottom");
		life1_image_spec = new ImageSpecification("life1");
		life2_image_spec = new ImageSpecification("life2");
		hud_tile_image_spec = new ImageSpecification("hud_tile");

		// Setup animations
		player1Animation = new Animation();
		player1Animation.addFrame(player1_1, 100);
		player1Animation.addFrame(player1_2, 100);
		player1Animation.addFrame(player1_3, 100);

		player2Animation = new Animation();
		player2Animation.addFrame(player2_1, 100);
		player2Animation.addFrame(player2_2, 100);
		player2Animation.addFrame(player2_3, 100);

		enemy1Animation = new Animation();
		enemy1Animation.addFrame(enemy1_1, 100);
		enemy1Animation.addFrame(enemy1_2, 100);
		enemy1Animation.addFrame(enemy1_3, 100);

		enemy2Animation = new Animation();
		enemy2Animation.addFrame(enemy2_1, 100);
		enemy2Animation.addFrame(enemy2_2, 100);
		enemy2Animation.addFrame(enemy2_3, 100);

		enemy3Animation = new Animation();
		enemy3Animation.addFrame(enemy3_1, 100);
		enemy3Animation.addFrame(enemy3_2, 100);
		enemy3Animation.addFrame(enemy3_3, 100);

		enemy4Animation = new Animation();
		enemy4Animation.addFrame(enemy4_1, 100);
		enemy4Animation.addFrame(enemy4_2, 100);
		enemy4Animation.addFrame(enemy4_3, 100);

		backgroundAnimation = new Animation();
		backgroundAnimation.addFrame(water, 1);

		island1Animation = new Animation();
		island1Animation.addFrame(island1, 1);

		island2Animation = new Animation();
		island2Animation.addFrame(island2, 1);

		island3Animation = new Animation();
		island3Animation.addFrame(island3, 1);

		life1Animation = new Animation();
		life1Animation.addFrame(life1_pickup, 1);

		life2Animation = new Animation();
		life2Animation.addFrame(life2_pickup, 1);

		powerUpAnimation = new Animation();
		powerUpAnimation.addFrame(power_up, 1);

		bulletAnimation = new Animation();
		bulletAnimation.addFrame(bullet, 1);

		bullet_leftAnimation = new Animation();
		bullet_leftAnimation.addFrame(bullet_left, 1);

		bullet_rightAnimation = new Animation();
		bullet_rightAnimation.addFrame(bullet_right, 1);

		big_bulletAnimation = new Animation();
		big_bulletAnimation.addFrame(big_bullet, 1);

		enemyWeapon1Animation = new Animation();
		enemyWeapon1Animation.addFrame(enemy_bullet1, 1);

		enemyWeapon2Animation = new Animation();
		enemyWeapon2Animation.addFrame(enemy_bullet2, 1);

		animations.put(AnimationType.PLAYER1, player1Animation);
		animations.put(AnimationType.PLAYER2, player2Animation);
		animations.put(AnimationType.ENEMY1, enemy1Animation);
		animations.put(AnimationType.ENEMY2, enemy2Animation);
		animations.put(AnimationType.ENEMY3, enemy3Animation);
		animations.put(AnimationType.ENEMY4, enemy4Animation);
		animations.put(AnimationType.BACKGROUND, backgroundAnimation);
		animations.put(AnimationType.ISLAND1, island1Animation);
		animations.put(AnimationType.ISLAND2, island2Animation);
		animations.put(AnimationType.ISLAND3, island3Animation);
		animations.put(AnimationType.LIFE1, life1Animation);
		animations.put(AnimationType.LIFE2, life2Animation);
		animations.put(AnimationType.POWER_UP, powerUpAnimation);
		animations.put(AnimationType.BULLET, bulletAnimation);
		animations.put(AnimationType.BULLET_LEFT, bullet_leftAnimation);
		animations.put(AnimationType.BULLET_RIGHT, bullet_rightAnimation);
		animations.put(AnimationType.BIG_BULLET, big_bulletAnimation);
		animations.put(AnimationType.ENEMY_BULLET1, enemyWeapon1Animation);
		animations.put(AnimationType.ENEMY_BULLET2, enemyWeapon2Animation);

		getTopScores();
	}

	public static Resources getInstance() {
		if (instance == null) {
			instance = new Resources();
		}

		return instance;
	}

	private void getTopScores() {
		try (BufferedReader br = new BufferedReader(new FileReader("../top_scores.txt"))) {
			String line;

			line = br.readLine();
			score1 = line;

			line = br.readLine();
			score2 = line;

			line = br.readLine();
			score3 = line;

			line = br.readLine();
			score4 = line;

			line = br.readLine();
			score5 = line;

			line = br.readLine();
			scoreNumber1 = (int) Integer.parseInt(line);

			line = br.readLine();
			scoreNumber2 = (int) Integer.parseInt(line);

			line = br.readLine();
			scoreNumber3 = (int) Integer.parseInt(line);

			line = br.readLine();
			scoreNumber4 = (int) Integer.parseInt(line);

			line = br.readLine();
			scoreNumber5 = (int) Integer.parseInt(line);

		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public void writeTopScores() {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("../top_scores.txt"), "utf-8"));
			writer.write(score1 + "\n");
			writer.write(score2 + "\n");
			writer.write(score3 + "\n");
			writer.write(score4 + "\n");
			writer.write(score5 + "\n");
			writer.write("" + scoreNumber1);
			writer.write("\n");
			writer.write("" + scoreNumber2);
			writer.write("\n");
			writer.write("" + scoreNumber3);
			writer.write("\n");
			writer.write("" + scoreNumber4);
			writer.write("\n");
			writer.write("" + scoreNumber5);
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}
	}

	public SolidResourceSpecification getSolidSpec(String name) {

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
			case "player2":
				return player2_spec;
			case "life1_pickup":
				return life1_pickup_spec;
			case "life2_pickup":
				return life2_pickup_spec;
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