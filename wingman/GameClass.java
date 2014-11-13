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
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JApplet;

import enums.EnemyType;
import enums.IslandType;
import projectiles.WingmanBullet;
import actors.Enemy;
import actors.Wingman;
import background.Island;

/**
 * @author Anthony Rodriguez
 *
 */
public class GameClass extends JApplet implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	private static Dimension dimension;

	private boolean isPlayerAlive = true;

	private int width = 800;
	private int height = 600;
	private int move = 1;

	private Image backgroundImage, island1Image, island2Image, island3Image, wingmanBulletImage;
	private Image[] wingmanImageArray, enemy1Image, enemy2Image, enemy3Image, enemy4Image;

	private BufferedImage bufferedImage;

	private Random generator = new Random();

	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Island> islands = new ArrayList<Island>();

	private Wingman wingman;

	@Override
	public void init() {
		setSize(width, height);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);

		dimension = getSize();

		setUpImages();

		wingman = new Wingman(wingmanImageArray, wingmanBulletImage, width, height);
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
	}

	@Override
	public void destroy() {

		// TODO Auto-generated method stub
	}

	@Override
	public void run() {

		while (true) {
			repaint();

			try {
				Thread.sleep(17);
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

			if (enemies.size() < 3) {
				generateEnemies(generator.nextInt(( maxEnemies - minEnemies ) + 1) + minEnemies);
			} else {
				for (int i = 0; i < enemies.size(); i++) {
					Enemy e = (Enemy) enemies.get(i);
					if (e.isVisible()) {
						e.update(width, height);
						e.draw(graphics2d, this);
					} else {
						enemies.remove(i);
					}
				}
			}

			wingman.update(width, height);
			wingman.draw(graphics2d, this);

			ArrayList<WingmanBullet> projectiles = wingman.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				WingmanBullet b = (WingmanBullet) projectiles.get(i);
				if (b.isVisible()) {
					b.moveUp();
					b.update(width, height);
					b.draw(graphics2d, this);
				} else {
					projectiles.remove(i);
				}
			}
		}
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

	private Image getSprite(String name) {
		URL url = GameClass.class.getResource("Resources/" + name);
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

	private void drawBackgroundWithTileImage(int width, int height, Graphics2D graphics2d) {
		int tileWidth = backgroundImage.getWidth(this);
		int tileHeight = backgroundImage.getHeight(this);

		int amountX = (int) ( width / tileWidth );
		int amountY = (int) ( height / tileWidth );

		for (int i = -1; i <= amountY; i++) {
			for (int j = 0; j <= amountX; j++) {
				graphics2d.drawImage(backgroundImage, j * tileWidth, i * tileHeight + ( move % tileHeight ), tileWidth, tileHeight, this);
			}
		}

		move += 1;
	}

	private void setUpImages() {
		backgroundImage = getSprite("water.png");

		// Wingman Image Animation
		wingmanImageArray = new Image[3];
		wingmanImageArray[0] = getSprite("myplane_1.png");
		wingmanImageArray[1] = getSprite("myplane_2.png");
		wingmanImageArray[2] = getSprite("myplane_3.png");
		wingmanBulletImage = getSprite("bullet_up.png");

		enemy1Image = new Image[3];
		enemy1Image[0] = getSprite("enemy1_1.png");
		enemy1Image[1] = getSprite("enemy1_2.png");
		enemy1Image[2] = getSprite("enemy1_3.png");

		enemy2Image = new Image[3];
		enemy2Image[0] = getSprite("enemy2_1.png");
		enemy2Image[1] = getSprite("enemy2_2.png");
		enemy2Image[2] = getSprite("enemy2_3.png");

		enemy3Image = new Image[3];
		enemy3Image[0] = getSprite("enemy3_1.png");
		enemy3Image[1] = getSprite("enemy3_2.png");
		enemy3Image[2] = getSprite("enemy3_3.png");

		enemy4Image = new Image[3];
		enemy4Image[0] = getSprite("enemy4_1.png");
		enemy4Image[1] = getSprite("enemy4_2.png");
		enemy4Image[2] = getSprite("enemy4_3.png");

		island1Image = getSprite("island1.png");
		island2Image = getSprite("island2.png");
		island3Image = getSprite("island3.png");
	}

	public static Dimension getDimension() {
		return dimension;
	}

	private void generateEnemies(int enemies) {
		EnemyType enemyType = EnemyType.ENEMY1;
		Image[] enemyImageArray = enemy1Image;
		for (int i = 0; i < enemies; i++) {
			switch (generator.nextInt(4) + 1) {
				case 1:
					enemyType = EnemyType.ENEMY1;
					enemyImageArray = enemy1Image;
					break;
				case 2:
					enemyType = EnemyType.ENEMY2;
					enemyImageArray = enemy2Image;
					break;
				case 3:
					enemyType = EnemyType.ENEMY3;
					enemyImageArray = enemy3Image;
					break;
				case 4:
					enemyType = EnemyType.ENEMY4;
					enemyImageArray = enemy4Image;
					break;
			}

			Enemy enemy = new Enemy(enemyImageArray, enemyType, generator, dimension);
			this.enemies.add(enemy);
		}
	}

	private void generateIslands(int islands) {
		IslandType islandType = IslandType.ISLAND1;
		Image islandImage = island1Image;
		for (int i = 0; i < islands; i++) {
			switch (generator.nextInt(3) + 1) {
				case 1:
					islandType = IslandType.ISLAND1;
					islandImage = island1Image;
					break;
				case 2:
					islandType = IslandType.ISLAND2;
					islandImage = island2Image;
					break;
				case 3:
					islandType = IslandType.ISLAND3;
					islandImage = island3Image;
					break;
			}

			Island island = new Island(islandImage, islandType, generator, dimension);
			this.islands.add(island);
		}
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
					wingman.moveDiagUpLeft();
				} else if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
					wingman.moveDiagUpRight();
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
					wingman.moveDiagDownLeft();
				} else if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
					wingman.moveDiagDownRight();
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
					wingman.moveDiagUpLeft();
				} else if (keysPressed.contains(KeyEvent.VK_DOWN)) {
					wingman.moveDiagDownLeft();
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
					wingman.moveDiagUpRight();
				} else if (keysPressed.contains(KeyEvent.VK_DOWN)) {
					wingman.moveDiagDownRight();
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

				wingman.firePrimary();
				break;
			default:
				System.out.println(event.getKeyChar());
				break;
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
				wingman.setCanFire(true);
				break;
			default:
				System.out.println(event.getKeyChar());
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
