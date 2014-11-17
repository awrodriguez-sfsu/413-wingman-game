package wingman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JApplet;

import projectiles.PrimaryWeapon;
import projectiles.SecondaryWeapon;
import actors.Enemy;
import actors.MainActor;
import animations.Animation;
import background.Island;
import enums.AnimationType;
import enums.EnemyType;

public class GameBase extends JApplet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	private final int WIDTH = 800;
	private final int HEIGHT = 800;

	private int backgroundMovement = 1;

	public static boolean isPlayerAlive = true;

	private static Dimension dimension;

	private BufferedImage bufferedImage;

	private Random generator = new Random();

	public static HashMap<AnimationType, Animation> animations = new HashMap<AnimationType, Animation>();

	private Animation mainCharacter, enemy1, enemy2, enemy3, enemy4;

	private Animation smallExplosion, largeExplosion;

	private Animation background, island1, island2, island3;

	private Animation mainCharacterPrimaryWeapon, mainCharacterSecondaryWeapon, enemyWeapon1, enemyWeapon2;

	private MainActor wingman;

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

		wingman = new MainActor(AnimationType.MAIN_CHARACTER, AnimationType.MAIN_CHARACTER_PRIMARY_WEAPON, AnimationType.MAIN_CHARACTER_SECONDARY_WEAPON, WIDTH / 2, HEIGHT - 128);
	}

	private void setupImages() {
		mainCharacter = new Animation();
		mainCharacter.addFrame(getSprite("myplane_1.png"), 100);
		mainCharacter.addFrame(getSprite("myplane_2.png"), 100);
		mainCharacter.addFrame(getSprite("myplane_3.png"), 100);

		enemy1 = new Animation();
		enemy1.addFrame(getSprite("enemy1_1.png"), 100);
		enemy1.addFrame(getSprite("enemy1_2.png"), 100);
		enemy1.addFrame(getSprite("enemy1_3.png"), 100);

		enemy2 = new Animation();
		enemy2.addFrame(getSprite("enemy2_1.png"), 100);
		enemy2.addFrame(getSprite("enemy2_2.png"), 100);
		enemy2.addFrame(getSprite("enemy2_3.png"), 100);

		enemy3 = new Animation();
		enemy3.addFrame(getSprite("enemy3_1.png"), 100);
		enemy3.addFrame(getSprite("enemy3_2.png"), 100);
		enemy3.addFrame(getSprite("enemy3_3.png"), 100);

		enemy4 = new Animation();
		enemy4.addFrame(getSprite("enemy4_1.png"), 100);
		enemy4.addFrame(getSprite("enemy4_2.png"), 100);
		enemy4.addFrame(getSprite("enemy4_3.png"), 100);

		smallExplosion = new Animation(true);
		smallExplosion.addFrame(Resources.getInstance().explosion1_1, 250);
		smallExplosion.addFrame(Resources.getInstance().explosion1_2, 250);
		smallExplosion.addFrame(Resources.getInstance().explosion1_3, 250);
		smallExplosion.addFrame(Resources.getInstance().explosion1_4, 250);
		smallExplosion.addFrame(Resources.getInstance().explosion1_5, 250);
		smallExplosion.addFrame(Resources.getInstance().explosion1_6, 250);

		largeExplosion = new Animation(true);
		largeExplosion.addFrame(getSprite("explosion2_1.png"), 250);
		largeExplosion.addFrame(getSprite("explosion2_2.png"), 250);
		largeExplosion.addFrame(getSprite("explosion2_3.png"), 250);
		largeExplosion.addFrame(getSprite("explosion2_4.png"), 250);
		largeExplosion.addFrame(getSprite("explosion2_5.png"), 250);
		largeExplosion.addFrame(getSprite("explosion2_6.png"), 250);
		largeExplosion.addFrame(getSprite("explosion2_7.png"), 250);

		background = new Animation();
		background.addFrame(getSprite("water.png"), 1);

		island1 = new Animation();
		island1.addFrame(getSprite("island1.png"), 1);

		island2 = new Animation();
		island2.addFrame(getSprite("island2.png"), 1);

		island3 = new Animation();
		island3.addFrame(getSprite("island3.png"), 1);

		mainCharacterPrimaryWeapon = new Animation();
		mainCharacterPrimaryWeapon.addFrame(getSprite("bullet_up.png"), 1);

		mainCharacterSecondaryWeapon = new Animation();
		mainCharacterSecondaryWeapon.addFrame(getSprite("bigBullet.png"), 1);

		enemyWeapon1 = new Animation();
		enemyWeapon1.addFrame(getSprite("enemybullet1.png"), 200);
		enemyWeapon1.addFrame(getSprite("enemybullet2.png"), 500);

		enemyWeapon2 = new Animation();
		enemyWeapon2.addFrame(getSprite("bigBullet.png"), 1);

		animations.put(AnimationType.MAIN_CHARACTER, mainCharacter);
		animations.put(AnimationType.ENEMY1, enemy1);
		animations.put(AnimationType.ENEMY2, enemy2);
		animations.put(AnimationType.ENEMY3, enemy3);
		animations.put(AnimationType.ENEMY4, enemy4);
		animations.put(AnimationType.SMALL_EXPLOSION, smallExplosion);
		animations.put(AnimationType.LARGE_EXPLOSION, largeExplosion);
		animations.put(AnimationType.BACKGROUND, background);
		animations.put(AnimationType.ISLAND1, island1);
		animations.put(AnimationType.ISLAND2, island2);
		animations.put(AnimationType.ISLAND3, island3);
		animations.put(AnimationType.MAIN_CHARACTER_PRIMARY_WEAPON, mainCharacterPrimaryWeapon);
		animations.put(AnimationType.MAIN_CHARACTER_SECONDARY_WEAPON, mainCharacterSecondaryWeapon);
		animations.put(AnimationType.ENEMY_WEAPON1, enemyWeapon1);
		animations.put(AnimationType.ENEMY_WEAPON2, enemyWeapon2);
	}

	private Image getSprite(String name) {
		URL url = GameBase.class.getResource("Resources/" + name);
		Image image = getToolkit().getImage(url);

		try {
			MediaTracker tracker = new MediaTracker(this);
			tracker.addImage(image, 0);
			tracker.waitForID(0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return image;
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

		while (isPlayerAlive) {
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

		if (isPlayerAlive) {
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

			wingman.update(width, height);
			wingman.draw(graphics2d, this);
			wingman.drawCollisionRect(graphics2d, this);

			if (enemies.size() < 3) {
				generateEnemies(generator.nextInt(( maxEnemies - minEnemies ) + 1) + minEnemies);
			} else {
				for (int i = 0; i < enemies.size(); i++) {
					Enemy enemy = (Enemy) enemies.get(i);
					if (enemy.isAlive() && enemy.isVisible()) {
						enemy.update(width, height);
						enemy.draw(graphics2d, this);
						enemy.drawCollisionRect(graphics2d, this);
					} else {
						enemies.remove(i);
					}
				}
			}

			ArrayList<PrimaryWeapon> mainCharacterPrimaryShots = wingman.getPrimaryWeaponShots();
			for (int i = 0; i < mainCharacterPrimaryShots.size(); i++) {
				PrimaryWeapon b = (PrimaryWeapon) mainCharacterPrimaryShots.get(i);
				if (b.isVisible()) {
					b.moveUp();
					b.update(width, height);
					b.draw(graphics2d, this);
				} else {
					mainCharacterPrimaryShots.remove(i);
				}
			}

			ArrayList<SecondaryWeapon> mainCharacterSecondaryShots = wingman.getSecondaryWeaponShots();
			for (int i = 0; i < mainCharacterSecondaryShots.size(); i++) {
				SecondaryWeapon s = (SecondaryWeapon) mainCharacterSecondaryShots.get(i);
				if (s.isVisible()) {
					s.moveUp();
					s.update(width, height);
					s.draw(graphics2d, this);
				} else {
					mainCharacterSecondaryShots.remove(i);
				}
			}

			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = (Enemy) enemies.get(i);

				if (wingman.isColliding(enemy)) {
					enemy.explode();
				}
			}
		} else {
			System.out.println("End Game");
		}
	}

	private void drawBackgroundWithTileImage(int width, int height, Graphics2D graphics2d) {
		int tileWidth = background.getImage().getWidth(this);
		int tileHeight = background.getImage().getHeight(this);

		int amountX = (int) ( width / tileWidth );
		int amountY = (int) ( height / tileWidth );

		for (int i = -1; i <= amountY; i++) {
			for (int j = 0; j <= amountX; j++) {
				graphics2d.drawImage(background.getImage(), j * tileWidth, i * tileHeight + ( backgroundMovement % tileHeight ), tileWidth, tileHeight, this);
			}
		}

		backgroundMovement += 1;
	}

	private void generateEnemies(int enemies) {
		EnemyType enemyType;
		AnimationType enemyImage;
		for (int i = 0; i < enemies; i++) {
			switch (generator.nextInt(4) + 1) {
				case 2:
					enemyType = EnemyType.ENEMY2;
					enemyImage = AnimationType.ENEMY2;
					break;
				case 3:
					enemyType = EnemyType.ENEMY3;
					enemyImage = AnimationType.ENEMY3;
					break;
				case 4:
					enemyType = EnemyType.ENEMY4;
					enemyImage = AnimationType.ENEMY4;
					break;
				default:
					enemyType = EnemyType.ENEMY1;
					enemyImage = AnimationType.ENEMY1;
					break;
			}

			Enemy enemy = new Enemy(enemyImage, AnimationType.ENEMY_WEAPON1, AnimationType.ENEMY_WEAPON2, AnimationType.SMALL_EXPLOSION, enemyType, generator, dimension);
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
	Set<Integer> keysPressed = new HashSet<>();

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				if (!keysPressed.contains(event.getKeyCode())) {
					keysPressed.add(event.getKeyCode());
				}

				if (keysPressed.contains(KeyEvent.VK_LEFT)) {
					wingman.moveUpLeft();
				} else if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
					wingman.moveUpRight();
				} else {
					wingman.moveUp();
				}

				if (keysPressed.contains(KeyEvent.VK_SPACE)) {
					wingman.firePrimary();
				}

				break;

			case KeyEvent.VK_DOWN:
				if (!keysPressed.contains(event.getKeyCode())) {
					keysPressed.add(event.getKeyCode());
				}

				if (keysPressed.contains(KeyEvent.VK_LEFT)) {
					wingman.moveDownLeft();
				} else if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
					wingman.moveDownRight();
				} else {
					wingman.moveDown();
				}

				if (keysPressed.contains(KeyEvent.VK_SPACE)) {
					wingman.firePrimary();
				}

				break;

			case KeyEvent.VK_LEFT:
				if (!keysPressed.contains(event.getKeyCode())) {
					keysPressed.add(event.getKeyCode());
				}

				if (keysPressed.contains(KeyEvent.VK_UP)) {
					wingman.moveUpLeft();
				} else if (keysPressed.contains(KeyEvent.VK_DOWN)) {
					wingman.moveDownLeft();
				} else {
					wingman.moveLeft();
				}

				if (keysPressed.contains(KeyEvent.VK_SPACE)) {
					wingman.firePrimary();
				}

				break;

			case KeyEvent.VK_RIGHT:
				if (!keysPressed.contains(event.getKeyCode())) {
					keysPressed.add(event.getKeyCode());
				}

				if (keysPressed.contains(KeyEvent.VK_UP)) {
					wingman.moveUpRight();
				} else if (keysPressed.contains(KeyEvent.VK_DOWN)) {
					wingman.moveDownRight();
				} else {
					wingman.moveRight();
				}

				if (keysPressed.contains(KeyEvent.VK_SPACE)) {
					wingman.firePrimary();
				}

				break;

			case KeyEvent.VK_SPACE:
				if (!keysPressed.contains(event.getKeyCode())) {
					keysPressed.add(event.getKeyCode());
				}

				if (wingman.canFirePrimary()) {
					wingman.firePrimary();
				}
				break;

			case KeyEvent.VK_B:
				if (!keysPressed.contains(event.getKeyCode())) {
					keysPressed.add(event.getKeyCode());
				}

				if (wingman.canFireSecondary()) {
					wingman.fireSecondary();
				}
				break;

			case KeyEvent.VK_Q:
				wingman.explode();
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {

		switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				keysPressed.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_DOWN:
				keysPressed.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_LEFT:
				keysPressed.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_RIGHT:
				keysPressed.remove(event.getKeyCode());
				break;
			case KeyEvent.VK_SPACE:
				keysPressed.remove(event.getKeyCode());
				wingman.setCanFirePrimary(true);
				break;
			case KeyEvent.VK_B:
				keysPressed.remove(event.getKeyCode());
				wingman.setCanFireSecondary(true);
				break;
		}

		if (keysPressed.isEmpty()) {
			wingman.stop();
		} else if (keysPressed.size() == 1) {
			if (keysPressed.contains(KeyEvent.VK_SPACE)) {
				wingman.stop();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent event) {

		// TODO Auto-generated method stub
	}
}
