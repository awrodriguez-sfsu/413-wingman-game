package wingman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JApplet;
import javax.swing.JFrame;

import projectiles.Projectile;
import actors.Enemy;
import actors.MainActor;
import animations.Animation;
import background.Island;
import enums.AnimationType;

public class GameBase extends JApplet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	private final int WIDTH = 800;
	private final int HEIGHT = 800;

	private int backgroundMovement = 0;

	private static Dimension dimension;

	private BufferedImage bufferedImage;

	private Random generator = new Random();

	public static HashMap<AnimationType, Animation> animations = new HashMap<AnimationType, Animation>();

	private Animation mainCharacter, enemy1, enemy2, enemy3, enemy4;

	private Animation background, island1, island2, island3;

	private Animation bullet, big_bullet, enemyWeapon1, enemyWeapon2;

	private MainActor player1;

	private MainActor player2;

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Island> islands = new ArrayList<Island>();

	@Override
	public void init() {
		setSize(WIDTH, HEIGHT);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);

		dimension = getSize();

		setupImages();

		player1 = new MainActor(AnimationType.PLAYER1, AnimationType.BULLET, AnimationType.BIG_BULLET, WIDTH / 2, HEIGHT - 128);

		player2 = new MainActor(AnimationType.PLAYER1, AnimationType.BULLET, AnimationType.BIG_BULLET, WIDTH / 3, HEIGHT - 128);
	}

	private void setupImages() {
		mainCharacter = new Animation();
		mainCharacter.addFrame(Resources.getInstance().player1_1, 100);
		mainCharacter.addFrame(Resources.getInstance().player1_2, 100);
		mainCharacter.addFrame(Resources.getInstance().player1_3, 100);

		enemy1 = new Animation();
		enemy1.addFrame(Resources.getInstance().enemy1_1, 100);
		enemy1.addFrame(Resources.getInstance().enemy1_2, 100);
		enemy1.addFrame(Resources.getInstance().enemy1_3, 100);

		enemy2 = new Animation();
		enemy2.addFrame(Resources.getInstance().enemy2_1, 100);
		enemy2.addFrame(Resources.getInstance().enemy2_2, 100);
		enemy2.addFrame(Resources.getInstance().enemy2_3, 100);

		enemy3 = new Animation();
		enemy3.addFrame(Resources.getInstance().enemy3_1, 100);
		enemy3.addFrame(Resources.getInstance().enemy3_2, 100);
		enemy3.addFrame(Resources.getInstance().enemy3_3, 100);

		enemy4 = new Animation();
		enemy4.addFrame(Resources.getInstance().enemy4_1, 100);
		enemy4.addFrame(Resources.getInstance().enemy4_2, 100);
		enemy4.addFrame(Resources.getInstance().enemy4_3, 100);

		background = new Animation();
		background.addFrame(Resources.getInstance().water, 1);

		island1 = new Animation();
		island1.addFrame(Resources.getInstance().island1, 1);

		island2 = new Animation();
		island2.addFrame(Resources.getInstance().island2, 1);

		island3 = new Animation();
		island3.addFrame(Resources.getInstance().island3, 1);

		bullet = new Animation();
		bullet.addFrame(Resources.getInstance().bullet, 1);

		big_bullet = new Animation();
		big_bullet.addFrame(Resources.getInstance().big_bullet, 1);

		enemyWeapon1 = new Animation();
		enemyWeapon1.addFrame(Resources.getInstance().enemy_bullet1, 1);

		enemyWeapon2 = new Animation();
		enemyWeapon2.addFrame(Resources.getInstance().enemy_bullet2, 1);

		animations.put(AnimationType.PLAYER1, mainCharacter);
		animations.put(AnimationType.ENEMY1, enemy1);
		animations.put(AnimationType.ENEMY2, enemy2);
		animations.put(AnimationType.ENEMY3, enemy3);
		animations.put(AnimationType.ENEMY4, enemy4);
		animations.put(AnimationType.BACKGROUND, background);
		animations.put(AnimationType.ISLAND1, island1);
		animations.put(AnimationType.ISLAND2, island2);
		animations.put(AnimationType.ISLAND3, island3);
		animations.put(AnimationType.BULLET, bullet);
		animations.put(AnimationType.BIG_BULLET, big_bullet);
		animations.put(AnimationType.ENEMY_BULLET1, enemyWeapon1);
		animations.put(AnimationType.ENEMY_BULLET2, enemyWeapon2);
	}

	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
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

		if (bufferedImage == null || bufferedImage.getWidth() != width || bufferedImage.getHeight() != height) {
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
				generateEnemies(generator.nextInt(( maxEnemies - minEnemies ) + 1) + minEnemies);
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
							player1Shots.remove(b);
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
							player2Shots.remove(b);
						}
					}

				} else {
					player2Shots.remove(i);
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
							if (player1.getHealth() == 1) {
								player1.explode();
							} else {
								player1.setHealth(player1.getHealth() - 1);
							}
						}

						if (player2.isColliding(b)) {
							enemyShots.remove(j);
							if (player2.getHealth() == 1) {
								player2.explode();
							} else {
								player2.setHealth(player2.getHealth() - 1);
							}
						}
					}
				}

				if (player1.isColliding(enemy)) {
					enemy.explode();
					if (player1.getHealth() == 1) {
						player1.explode();
					} else {
						player1.setHealth(player1.getHealth() - 1);
					}
				}

				if (player2.isColliding(enemy)) {
					enemy.explode();
					if (player2.getHealth() == 1) {
						player2.explode();
					} else {
						player2.setHealth(player2.getHealth() - 1);
					}
				}
			}
			drawHUD(width, height, graphics2d);
		} else {
			graphics2d.drawImage(Resources.getInstance().game_over, (int) ( ( width / 2 ) - Resources.getInstance().game_over_image_spec.center_x ), (int) ( ( height / 2 ) - Resources.getInstance().game_over_image_spec.center_y ), this);
		}
	}

	private void drawBackgroundWithTileImage(int width, int height, Graphics2D graphics2d) {
		int tileWidth = background.getImage().getWidth(this);
		int tileHeight = background.getImage().getHeight(this);

		int amountX = (int) ( width / tileWidth );
		int amountY = (int) ( ( height - Resources.getInstance().hud_bottom_image_spec.bottom ) / tileHeight );

		for (int i = 0; i <= amountX; i++) {
			for (int j = -1; j <= amountY; j++) {
				graphics2d.drawImage(background.getImage(), i * tileWidth, j * tileHeight + ( backgroundMovement % tileHeight ), tileWidth, tileHeight, this);
			}
		}

		backgroundMovement += 1;
	}

	private void drawHUD(int width, int height, Graphics2D graphics2d) {
		int tileWidth = (int) Resources.getInstance().hud_tile_image_spec.right;
		int tileHeight = (int) Resources.getInstance().hud_tile_image_spec.bottom;

		int amountX = (int) ( width / tileWidth );
		int amountY = (int) ( ( Resources.getInstance().hud_bottom_image_spec.bottom ) / tileHeight );

		for (int i = 0; i <= amountX; i++) {
			for (int j = 0; j <= amountY; j++) {
				graphics2d.drawImage(Resources.getInstance().hud_tile, i * tileWidth, ( j + height - 76 ), tileWidth, tileHeight, this);
				graphics2d.drawImage(Resources.getInstance().hud_tile, i * tileWidth, ( 32 * j + height - 76 ), tileWidth, tileHeight, this);
				graphics2d.drawImage(Resources.getInstance().hud_tile, i * tileWidth, ( 64 * j + height - 76 ), tileWidth, tileHeight, this);
			}
		}

		graphics2d.drawImage(Resources.getInstance().hud_bottom, (int) ( ( width / 2 ) - Resources.getInstance().hud_bottom_image_spec.center_x ), (int) ( ( height ) - Resources.getInstance().hud_bottom_image_spec.bottom ), this);
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
				player1.explode();
				player2.explode();
				break;
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
		jFrame.setSize(new Dimension(800, 800));
		jFrame.setVisible(true);
		game.start();
	}
}
