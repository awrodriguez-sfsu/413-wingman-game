package wingman;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JApplet;
import javax.swing.JFrame;

import projectiles.Projectile;
import wingman.Resources.ImageSpecification;
import actors.Enemy;
import actors.MainActor;
import background.Island;
import background.ItemDrop;
import enums.AnimationType;

public class GameBase extends JApplet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	private Resources resources = Resources.getInstance();;

	private final static int WIDTH = 800;
	private final static int HEIGHT = 800;

	public static int level = 1;

	private int enemiesKilled = 0;

	private int backgroundMovement = 0;

	private boolean godMode = false;

	private boolean gameOver = false;

	private static Dimension dimension;

	private FontMetrics metrics;

	private BufferedImage bufferedImage;

	private Random generator = new Random();

	private MainActor player1;

	private MainActor player2;

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Island> islands = new ArrayList<Island>();

	public static ArrayList<ItemDrop> drops = new ArrayList<ItemDrop>();

	private AudioClip backgroundSound = resources.background_sound;

	@Override
	public void init() {
		setSize(WIDTH, HEIGHT);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		metrics = getFontMetrics(getFont());

		dimension = getSize();

		player1 = new MainActor(AnimationType.PLAYER1, AnimationType.BULLET, AnimationType.BIG_BULLET, WIDTH / 2, HEIGHT - 128);

		player2 = new MainActor(AnimationType.PLAYER2, AnimationType.BULLET, AnimationType.BIG_BULLET, WIDTH / 3, HEIGHT - 128);
	}

	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();

		backgroundSound.loop();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("destroyed");
	}

	@Override
	public void run() {

		while (true) {
			repaint();

			try {
				Thread.sleep(16);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics graphics) {
		dimension = getSize();
		if (dimension.width < 800) {
			setSize(new Dimension(800, dimension.height));
			dimension = getSize();
		}

		if (dimension.height < 800) {
			setSize(new Dimension(dimension.width, 800));
			dimension = getSize();
		}

		Graphics2D graphics2d = createGraphics2D(dimension.width, dimension.height);
		drawGame(dimension.width, dimension.height, graphics2d);
		graphics2d.dispose();
		graphics.drawImage(bufferedImage, 0, 0, this);
	}

	private Graphics2D createGraphics2D(int width, int height) {

		Graphics2D graphics2d = null;

		if (bufferedImage == null || bufferedImage.getWidth() != width
				|| bufferedImage.getHeight() != height) {
			bufferedImage = (BufferedImage) createImage(width, height);
		}

		graphics2d = bufferedImage.createGraphics();
		graphics2d.setBackground(getBackground());
		graphics2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		graphics2d.clearRect(0, 0, width, height);

		return graphics2d;
	}

	private void drawGame(int width, int height, Graphics2D graphics2d) {

		int maxEnemies = 8;
		int minEnemies = 3;
		int maxIslands = 10;
		int minIslands = 4;

		if (enemiesKilled >= ( 10 * level )) {
			level++;
			enemiesKilled = 0;
		}

		if (player1.isAlive() || player2.isAlive()) {
			drawBackgroundWithTileImage(width, height, graphics2d);

			if (islands.size() < 3) {
				generateIslands(generator.nextInt(( maxIslands - minIslands ) + 1) + minIslands);
			} else {
				for (int i = 0; i < islands.size(); i++) {
					Island is = (Island) islands.get(i);
					if (is.isVisible()) {
						is.update(width, height);
						is.draw(graphics2d, this);
					} else {
						islands.remove(i);
					}
				}
			}

			player1.update(width, height);
			player1.draw(graphics2d, this);

			player2.update(width, height);
			player2.draw(graphics2d, this);

			if (enemies.size() < 3) {
				generateEnemies(generator.nextInt(( maxEnemies - minEnemies ) + level) + minEnemies);
			} else {
				for (int i = 0; i < enemies.size(); i++) {
					Enemy enemy = (Enemy) enemies.get(i);
					if (enemy.isAlive() && enemy.inPlay()) {
						enemy.update(width, height);
						enemy.draw(graphics2d, this);
					} else {
						enemies.remove(i);
					}
				}
			}

			ArrayList<Projectile> player1Shots = player1.getShots();
			for (int i = 0; i < player1Shots.size(); i++) {
				Projectile b = (Projectile) player1Shots.get(i);
				if (b.isVisible()) {
					b.update(width, height);
					b.draw(graphics2d, this);

					for (int j = 0; j < enemies.size(); j++) {
						Enemy enemy = (Enemy) enemies.get(j);

						if (b.isColliding(enemy)) {
							enemy.explode();
							enemiesKilled++;
							player1Shots.remove(b);
							player1.increaseScore(10);
						}
					}

				} else {
					player1Shots.remove(i);
				}
			}

			ArrayList<Projectile> player2Shots = player2.getShots();
			for (int i = 0; i < player2Shots.size(); i++) {
				Projectile b = (Projectile) player2Shots.get(i);
				if (b.isVisible()) {
					b.update(width, height);
					b.draw(graphics2d, this);

					for (int j = 0; j < enemies.size(); j++) {
						Enemy enemy = (Enemy) enemies.get(j);

						if (b.isColliding(enemy)) {
							enemy.explode();
							enemiesKilled++;
							player2Shots.remove(b);
							player2.increaseScore(10);
						}
					}

				} else {
					player2Shots.remove(i);
				}
			}

			for (int i = 0; i < drops.size(); i++) {
				ItemDrop drop = (ItemDrop) drops.get(i);
				if (drop.isVisible()) {
					drop.update(width, height);
					drop.draw(graphics2d, this);

					if (drop.isColliding(player1)) {
						if (drop.getAnimationType() == AnimationType.LIFE1) {
							player1.increaseLives(1);
							drops.remove(drop);
						} else if (drop.getAnimationType() == AnimationType.LIFE2) {
							player2.increaseLives(1);
							drops.remove(drop);
						} else {
							player1.increasePowerUp(1);
							drops.remove(drop);
						}
					}

					if (drop.isColliding(player2)) {
						if (drop.getAnimationType() == AnimationType.LIFE1) {
							player1.increaseLives(1);
							drops.remove(drop);
						} else if (drop.getAnimationType() == AnimationType.LIFE2) {
							player2.increaseLives(1);
							drops.remove(drop);
						} else {
							player2.increasePowerUp(1);
							drops.remove(drop);
						}
					}

				} else {
					drops.remove(drop);
				}
			}

			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = (Enemy) enemies.get(i);

				ArrayList<Projectile> enemyShots = enemy.getShots();
				for (int j = 0; j < enemyShots.size(); j++) {
					Projectile b = (Projectile) enemyShots.get(j);
					if (b.isVisible()) {
						b.update(width, height);
						b.draw(graphics2d, this);

						if (player1.isColliding(b)) {
							enemyShots.remove(j);
							player1.increaseHealth(-1);
						}

						if (player2.isColliding(b)) {
							enemyShots.remove(j);
							player2.increaseHealth(-1);
						}
					}
				}

				if (player1.isColliding(enemy)) {
					enemy.explode();
					enemiesKilled++;
					player1.increaseHealth(-1);
					player1.increaseScore(10);
				}

				if (player2.isColliding(enemy)) {
					enemy.explode();
					enemiesKilled++;
					player2.increaseHealth(-1);
					player2.increaseScore(10);
				}
			}

			if (player1.getHealth() <= 0 && !player1.isExploding() && !godMode) {
				player1.explode();
			}

			if (player2.getHealth() <= 0 && !player2.isExploding() && !godMode) {
				player2.explode();
			}

			drawHUD(width, height, graphics2d);
		} else {
			backgroundSound.stop();
			graphics2d.drawImage(resources.game_over, (int) ( ( width / 2 ) - resources.game_over_image_spec.center_x ), (int) ( ( height / 2 ) - resources.game_over_image_spec.center_y ), this);

			graphics2d.setColor(Color.WHITE);
			graphics2d.drawString("ScoreBoard", (int) ( ( width / 2 ) - 98 ), (int) ( ( height / 2 ) + resources.game_over_image_spec.bottom ));

			if (!gameOver) {
				int player1Score = Integer.parseInt(player1.getScore());
				if (player1Score > resources.scoreNumber1) {
					resources.score5 = resources.score4;
					resources.scoreNumber5 = resources.scoreNumber4;

					resources.score4 = resources.score3;
					resources.scoreNumber4 = resources.scoreNumber3;

					resources.score3 = resources.score2;
					resources.scoreNumber3 = resources.scoreNumber2;

					resources.score2 = resources.score1;
					resources.scoreNumber2 = resources.scoreNumber1;

					resources.score1 = "player1";
					resources.scoreNumber1 = (int) player1Score;
				} else if (player1Score > resources.scoreNumber2) {
					resources.score5 = resources.score4;
					resources.scoreNumber5 = resources.scoreNumber4;

					resources.score4 = resources.score3;
					resources.scoreNumber4 = resources.scoreNumber3;

					resources.score3 = resources.score2;
					resources.scoreNumber3 = resources.scoreNumber2;

					resources.score2 = "player1";
					resources.scoreNumber2 = (int) player1Score;
				} else if (player1Score > resources.scoreNumber3) {
					resources.score5 = resources.score4;
					resources.scoreNumber5 = resources.scoreNumber4;

					resources.score4 = resources.score3;
					resources.scoreNumber4 = resources.scoreNumber3;

					resources.score3 = "player1";
					resources.scoreNumber3 = (int) player1Score;
				} else if (player1Score > resources.scoreNumber4) {
					resources.score5 = resources.score4;
					resources.scoreNumber5 = resources.scoreNumber4;

					resources.score4 = "player1";
					resources.scoreNumber4 = (int) player1Score;
				} else if (player1Score > resources.scoreNumber5) {
					resources.score5 = "player1";
					resources.scoreNumber5 = (int) player1Score;
				}

				int player2Score = Integer.parseInt(player2.getScore());
				if (player2Score > resources.scoreNumber1) {
					resources.score5 = resources.score4;
					resources.scoreNumber5 = resources.scoreNumber4;

					resources.score4 = resources.score3;
					resources.scoreNumber4 = resources.scoreNumber3;

					resources.score3 = resources.score2;
					resources.scoreNumber3 = resources.scoreNumber2;

					resources.score2 = resources.score1;
					resources.scoreNumber2 = resources.scoreNumber1;

					resources.score1 = "player2";
					resources.scoreNumber1 = (int) player2Score;
				} else if (player2Score > resources.scoreNumber2) {
					resources.score5 = resources.score4;
					resources.scoreNumber5 = resources.scoreNumber4;

					resources.score4 = resources.score3;
					resources.scoreNumber4 = resources.scoreNumber3;

					resources.score3 = resources.score2;
					resources.scoreNumber3 = resources.scoreNumber2;

					resources.score2 = "player2";
					resources.scoreNumber2 = (int) player2Score;
				} else if (player2Score > resources.scoreNumber3) {
					resources.score5 = resources.score4;
					resources.scoreNumber5 = resources.scoreNumber4;

					resources.score4 = resources.score3;
					resources.scoreNumber4 = resources.scoreNumber3;

					resources.score3 = "player2";
					resources.scoreNumber3 = (int) player2Score;
				} else if (player2Score > resources.scoreNumber4) {
					resources.score5 = resources.score4;
					resources.scoreNumber5 = resources.scoreNumber4;

					resources.score4 = "player2";
					resources.scoreNumber4 = (int) player2Score;
				} else if (player2Score > resources.scoreNumber5) {
					resources.score5 = "player2";
					resources.scoreNumber5 = (int) player2Score;
				}
				gameOver = true;

				resources.writeTopScores();
			}

			graphics2d.drawString("1: " + resources.score1 + "          " + resources.scoreNumber1, (int) ( ( width / 2 ) - 150 ), (int) ( ( height / 2 )
					+ resources.game_over_image_spec.bottom + 50 ));
			graphics2d.drawString("2: " + resources.score2 + "          " + resources.scoreNumber2, (int) ( ( width / 2 ) - 150 ), (int) ( ( height / 2 )
					+ resources.game_over_image_spec.bottom + 100 ));
			graphics2d.drawString("3: " + resources.score3 + "          " + resources.scoreNumber3, (int) ( ( width / 2 ) - 150 ), (int) ( ( height / 2 )
					+ resources.game_over_image_spec.bottom + 150 ));
			graphics2d.drawString("4: " + resources.score4 + "          " + resources.scoreNumber4, (int) ( ( width / 2 ) - 150 ), (int) ( ( height / 2 )
					+ resources.game_over_image_spec.bottom + 200 ));
			graphics2d.drawString("5: " + resources.score5 + "          " + resources.scoreNumber5, (int) ( ( width / 2 ) - 150 ), (int) ( ( height / 2 )
					+ resources.game_over_image_spec.bottom + 250 ));
		}
	}

	private void drawBackgroundWithTileImage(int width, int height, Graphics2D graphics2d) {
		int tileWidth = resources.backgroundAnimation.getImage().getWidth(this);
		int tileHeight = resources.backgroundAnimation.getImage().getHeight(this);

		int amountX = (int) ( width / tileWidth );
		int amountY = (int) ( ( height - resources.hud_bottom_image_spec.bottom ) / tileHeight );

		for (int i = 0; i <= amountX; i++) {
			for (int j = -1; j <= amountY; j++) {
				graphics2d.drawImage(resources.backgroundAnimation.getImage(), i * tileWidth, j
						* tileHeight + ( backgroundMovement % tileHeight ), tileWidth, tileHeight, this);
			}
		}

		backgroundMovement += 1;
	}

	private void drawHUD(int width, int height, Graphics2D graphics2d) {
		ImageSpecification hud = resources.hud_bottom_image_spec;
		int center_x = (int) hud.center_x;
		int bottom = (int) hud.bottom;

		int healtLeftX = (int) resources.hud_health_position1_image_spec.left;
		int healtLeftY = (int) resources.hud_health_position1_image_spec.top;
		int healtRightX = (int) resources.hud_health_position2_image_spec.left;
		int healtRightY = (int) resources.hud_health_position2_image_spec.top;

		int tileWidth = (int) resources.hud_tile_image_spec.right;
		int tileHeight = (int) resources.hud_tile_image_spec.bottom;

		int amountX = (int) ( width / tileWidth );
		int amountY = ( bottom / tileHeight );

		for (int i = 0; i <= amountX; i++) {
			for (int j = 0; j <= amountY; j++) {
				graphics2d.drawImage(resources.hud_tile, i * tileWidth, ( j + height - bottom ), tileWidth, tileHeight, this);
				graphics2d.drawImage(resources.hud_tile, i * tileWidth, ( 32 * j + height - bottom ), tileWidth, tileHeight, this);
				graphics2d.drawImage(resources.hud_tile, i * tileWidth, ( 64 * j + height - bottom ), tileWidth, tileHeight, this);
			}
		}

		// Level
		graphics2d.setColor(Color.WHITE);
		graphics2d.drawString("Level: " + level, 10, 25);

		// Hud
		graphics2d.drawImage(resources.hud_bottom, ( width / 2 ) - center_x, height - bottom, this);

		// Health bars
		graphics2d.drawImage(player2.getHealthBar(), ( ( width / 2 ) - center_x ) + healtLeftX, ( height - bottom )
				+ healtLeftY, this);
		graphics2d.drawImage(player1.getHealthBar(), ( ( width / 2 ) - center_x ) + healtRightX, ( height - bottom )
				+ healtRightY, this);

		// Scores
		graphics2d.setColor(Color.WHITE);
		graphics2d.drawString(player1.getScore(), (int) ( width - 10
				- ( metrics.stringWidth(player1.getScore()) ) + 2 ), (int) ( height - hud.bottom + 24 ));
		graphics2d.drawString(player2.getScore(), 10, (int) ( height - hud.bottom + 24 ));

		// Player2 Lives
		for (int i = 0; i < player2.getLives(); i++) {
			graphics2d.drawImage(resources.life2, 32 * ( i ), (int) ( height - hud.bottom + 24 ), this);
		}

		// Player1 Lives
		for (int i = 0; i < player1.getLives(); i++) {
			graphics2d.drawImage(resources.life1, (int) ( width - 32 * ( i + 1 ) ), (int) ( height
					- hud.bottom + 24 ), this);
		}
	}

	private void generateEnemies(int enemies) {
		AnimationType enemyImage;
		for (int i = 0; i < enemies; i++) {
			switch (generator.nextInt(4) + 1) {
				case 2:
					enemyImage = AnimationType.ENEMY2;
					break;
				case 3:
					enemyImage = AnimationType.ENEMY3;
					break;
				case 4:
					enemyImage = AnimationType.ENEMY4;
					break;
				default:
					enemyImage = AnimationType.ENEMY1;
					break;
			}

			Enemy enemy = new Enemy(enemyImage, AnimationType.ENEMY_BULLET1, AnimationType.ENEMY_BULLET2, generator, dimension);
			this.enemies.add(enemy);
		}
	}

	private void generateIslands(int islands) {
		AnimationType islandType;
		for (int i = 0; i < islands; i++) {
			switch (generator.nextInt(3) + 1) {
				case 2:
					islandType = AnimationType.ISLAND2;
					break;
				case 3:
					islandType = AnimationType.ISLAND3;
					break;
				default:
					islandType = AnimationType.ISLAND1;
					break;
			}

			Island island = new Island(islandType, generator, dimension);
			this.islands.add(island);
		}
	}

	public static Dimension getDimension() {
		return dimension;
	}

	/**
	 * KeyListener
	 */
	Set<Integer> keysPressedP1 = new HashSet<>();
	Set<Integer> keysPressedP2 = new HashSet<>();

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
		// Player1 controls
			case KeyEvent.VK_UP:
				if (!keysPressedP1.contains(event.getKeyCode())) {
					keysPressedP1.add(event.getKeyCode());
				}

				if (keysPressedP1.contains(KeyEvent.VK_LEFT)) {
					player1.moveUpLeft();
				} else if (keysPressedP1.contains(KeyEvent.VK_RIGHT)) {
					player1.moveUpRight();
				} else {
					player1.moveUp();
				}

				if (keysPressedP1.contains(KeyEvent.VK_SLASH)) {
					player1.firePrimary();
				}

				break;

			case KeyEvent.VK_DOWN:
				if (!keysPressedP1.contains(event.getKeyCode())) {
					keysPressedP1.add(event.getKeyCode());
				}

				if (keysPressedP1.contains(KeyEvent.VK_LEFT)) {
					player1.moveDownLeft();
				} else if (keysPressedP1.contains(KeyEvent.VK_RIGHT)) {
					player1.moveDownRight();
				} else {
					player1.moveDown();
				}

				if (keysPressedP1.contains(KeyEvent.VK_SLASH)) {
					player1.firePrimary();
				}

				break;

			case KeyEvent.VK_LEFT:
				if (!keysPressedP1.contains(event.getKeyCode())) {
					keysPressedP1.add(event.getKeyCode());
				}

				if (keysPressedP1.contains(KeyEvent.VK_UP)) {
					player1.moveUpLeft();
				} else if (keysPressedP1.contains(KeyEvent.VK_DOWN)) {
					player1.moveDownLeft();
				} else {
					player1.moveLeft();
				}

				if (keysPressedP1.contains(KeyEvent.VK_SLASH)) {
					player1.firePrimary();
				}

				break;

			case KeyEvent.VK_RIGHT:
				if (!keysPressedP1.contains(event.getKeyCode())) {
					keysPressedP1.add(event.getKeyCode());
				}

				if (keysPressedP1.contains(KeyEvent.VK_UP)) {
					player1.moveUpRight();
				} else if (keysPressedP1.contains(KeyEvent.VK_DOWN)) {
					player1.moveDownRight();
				} else {
					player1.moveRight();
				}

				if (keysPressedP1.contains(KeyEvent.VK_SLASH)) {
					player1.firePrimary();
				}

				break;

			case KeyEvent.VK_PERIOD:
				if (!keysPressedP1.contains(event.getKeyCode())) {
					keysPressedP1.add(event.getKeyCode());
				}

				if (player1.canFirePrimary()) {
					player1.firePrimary();
				}

				break;

			case KeyEvent.VK_SLASH:
				if (!keysPressedP1.contains(event.getKeyCode())) {
					keysPressedP1.add(event.getKeyCode());
				}

				if (player1.canFireSecondary()) {
					player1.fireSecondary();
				}

				break;

			// Player2 controls
			case KeyEvent.VK_W:
				if (!keysPressedP2.contains(event.getKeyCode())) {
					keysPressedP2.add(event.getKeyCode());
				}

				if (keysPressedP2.contains(KeyEvent.VK_A)) {
					player2.moveUpLeft();
				} else if (keysPressedP2.contains(KeyEvent.VK_D)) {
					player2.moveUpRight();
				} else {
					player2.moveUp();
				}

				if (keysPressedP2.contains(KeyEvent.VK_G)) {
					player2.firePrimary();
				}

				break;

			case KeyEvent.VK_S:
				if (!keysPressedP2.contains(event.getKeyCode())) {
					keysPressedP2.add(event.getKeyCode());
				}

				if (keysPressedP2.contains(KeyEvent.VK_A)) {
					player2.moveDownLeft();
				} else if (keysPressedP2.contains(KeyEvent.VK_D)) {
					player2.moveDownRight();
				} else {
					player2.moveDown();
				}

				if (keysPressedP2.contains(KeyEvent.VK_G)) {
					player2.firePrimary();
				}

				break;

			case KeyEvent.VK_A:
				if (!keysPressedP2.contains(event.getKeyCode())) {
					keysPressedP2.add(event.getKeyCode());
				}

				if (keysPressedP2.contains(KeyEvent.VK_W)) {
					player2.moveUpLeft();
				} else if (keysPressedP2.contains(KeyEvent.VK_S)) {
					player2.moveDownLeft();
				} else {
					player2.moveLeft();
				}

				if (keysPressedP2.contains(KeyEvent.VK_G)) {
					player2.firePrimary();
				}

				break;

			case KeyEvent.VK_D:
				if (!keysPressedP2.contains(event.getKeyCode())) {
					keysPressedP2.add(event.getKeyCode());
				}

				if (keysPressedP2.contains(KeyEvent.VK_W)) {
					player2.moveUpRight();
				} else if (keysPressedP2.contains(KeyEvent.VK_S)) {
					player2.moveDownRight();
				} else {
					player2.moveRight();
				}

				if (keysPressedP2.contains(KeyEvent.VK_G)) {
					player2.firePrimary();
				}

				break;

			case KeyEvent.VK_G:
				if (!keysPressedP2.contains(event.getKeyCode())) {
					keysPressedP2.add(event.getKeyCode());
				}

				if (player2.canFirePrimary()) {
					player2.firePrimary();
				}

				break;

			case KeyEvent.VK_H:
				if (!keysPressedP2.contains(event.getKeyCode())) {
					keysPressedP2.add(event.getKeyCode());
				}

				if (player2.canFireSecondary()) {
					player2.fireSecondary();
				}

				break;

			case KeyEvent.VK_Q:
				player1.increaseLives(1);
				player2.increaseLives(1);
				godMode = true;
				break;

		// case KeyEvent.VK_1:
		// player1.increaseScore(10);
		// player2.increaseScore(10);
		// break;
		//
		// case KeyEvent.VK_2:
		// player1.increaseScore(100);
		// player2.increaseScore(100);
		// break;
		//
		// case KeyEvent.VK_3:
		// player1.increaseScore(1000);
		// player2.increaseScore(1000);
		// break;
		//
		// case KeyEvent.VK_4:
		// player1.increaseScore(10000);
		// player2.increaseScore(10000);
		// break;
		//
		// case KeyEvent.VK_5:
		// player1.increaseScore(10000000);
		// player2.increaseScore(10000000);
		// break;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {

		switch (event.getKeyCode()) {
		// Player1 controls
			case KeyEvent.VK_UP:
				keysPressedP1.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_DOWN:
				keysPressedP1.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_LEFT:
				keysPressedP1.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_RIGHT:
				keysPressedP1.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_PERIOD:
				keysPressedP1.remove(event.getKeyCode());
				player1.setCanFirePrimary(true);
				break;
			case KeyEvent.VK_SLASH:
				keysPressedP1.remove(event.getKeyCode());
				player1.setCanFireSecondary(true);
				break;

			// Player 2 controls
			case KeyEvent.VK_W:
				keysPressedP2.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_S:
				keysPressedP2.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_A:
				keysPressedP2.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_D:
				keysPressedP2.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_G:
				keysPressedP2.remove(event.getKeyCode());
				player2.setCanFirePrimary(true);
				break;
			case KeyEvent.VK_H:
				keysPressedP2.remove(event.getKeyCode());
				player2.setCanFireSecondary(true);
				break;
		}

		if (keysPressedP1.isEmpty()) {
			player1.stop();
		} else if (keysPressedP1.size() == 1) {
			if (keysPressedP1.contains(KeyEvent.VK_PERIOD)) {
				player1.stop();
			}
		}

		if (keysPressedP2.isEmpty()) {
			player2.stop();
		} else if (keysPressedP2.size() == 1) {
			if (keysPressedP2.contains(KeyEvent.VK_G)) {
				player2.stop();
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent event) {

		// TODO Auto-generated method stub
	}

	public static void main(String[] args) {
		// ResourceClassBuilder.main(args);

		final GameBase game = new GameBase();
		game.init();

		JFrame jFrame = new JFrame("Wingman");
		jFrame.addWindowListener(new WindowAdapter() {});
		jFrame.getContentPane().add("Center", game);
		jFrame.pack();
		jFrame.setSize(new Dimension(WIDTH, HEIGHT));
		jFrame.setVisible(true);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.start();
	}
}
