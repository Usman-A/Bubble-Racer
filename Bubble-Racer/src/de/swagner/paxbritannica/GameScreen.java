/*Class used for creating bullets
 * Usman Asad
 * Talha Awan
 */
package de.swagner.paxbritannica;

import java.io.IOException;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;

import Collectables.Bullet;
import Collectables.Coin;
import Collectables.Collectible;
import Collectables.Pearl;
import Monsters.Enemy;
import Monsters.JellyFish;
import Monsters.Octopus;
import Monsters.Shark;

import java.util.ArrayList;
import java.util.Random;

import de.swagner.paxbritannica.background.BackgroundFXRenderer;
//import de.swagner.paxbritannica.factory.PlayerProduction;
import de.swagner.paxbritannica.mainmenu.MainMenu;
import de.swagner.paxbritannica.players.FactoryProduction;
import de.swagner.paxbritannica.Resources;

public class GameScreen extends DefaultScreen implements InputProcessor {

	double startTime = 0;
	BackgroundFXRenderer backgroundFX = new BackgroundFXRenderer();

	private float fade = 1.0f;
	Sprite blackFade;

	Sprite p1;
	Sprite p2;
	Sprite p3;

	Random r = new Random();

	public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public static ArrayList<Collectible> collectibles = new ArrayList<Collectible>();
	int counter = 0;

	SpriteBatch fadeBatch;
	SpriteBatch gameBatch;

	FactoryProduction player;

	// ShapeRenderer shapeRenderer = new ShapeRenderer();
	OrthographicCamera cam;

	private boolean gameOver = false;
	private float gameOverTimer = 5;

	int numPlayers = 0;

	Ray collisionRay;


	public float time=0;
	public float score=0;
	


	BitmapFont hpFont;
	BitmapFont scoreFont;
	
	public static final Color RED = new Color(0xff0000ff);
	static final Color WHITE = new Color(Color.WHITE);
	
	private Array<Vector2> POSITIONS = new Array<Vector2>();

	private Vector2 CENTER = new Vector2(300, 180);

	private int width = 800;
	private int height = 480;

	public boolean collision(float x1, float y1, float x2, float y2, float radius) {
		double distance = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

		if (distance <= radius)
			return true;
		else
			return false;
	}

	public GameScreen(Game game, Array<Integer> playerList) {
		super(game);
		Gdx.input.setCatchBackKey(true);
		Gdx.input.setInputProcessor(this);

		cam = new OrthographicCamera(width, height);

		cam.position.x = 400;
		cam.position.y = 240;
		cam.update();

		numPlayers = playerList.size;

		hpFont = new BitmapFont(Gdx.files.internal("data/fonts/HP.fnt"));
		scoreFont = new BitmapFont(Gdx.files.internal("data/fonts/Score.fnt"));
		// camera = new OrthographicCamera(800, 480);
		// camera.translate(400, 240, 0);

		// determines starting positions factories
		POSITIONS.add(new Vector2(-100, 180)); // changed from 150 to 55

		// Fade
		blackFade = Resources.getInstance().blackFade;
		fadeBatch = new SpriteBatch();
		fadeBatch.getProjectionMatrix().setToOrtho2D(0, 0, 2, 2);

		gameBatch = new SpriteBatch();
		
		
		gameBatch.getProjectionMatrix().set(cam.combined);

		// creates players and cpu using init positions and direction
		// Array<Vector2> positons = generatePositions(numPlayers + 1);
		// keeps track of current factory id (cannot just use i because players and cpu
		// are different)

		for (int i = 0; i < playerList.size; ++i) {
			player = new FactoryProduction(playerList.get(i), POSITIONS.get(i));
			GameInstance.getInstance().factorys.add(player);

		}

		// player= new FactoryProduction(playerList.get(0), POSITIONS.get(0));

		// factor is an array of ships, a player object is created
		// and is added to the array of ships
		// player extends factory productions

		Gdx.gl.glDisable(GL20.GL_CULL_FACE);
		Gdx.gl.glDisable(GL20.GL_DEPTH_TEST);
	}

	Vector3 tmp = new Vector3();

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		if (width == 480 && height == 320) {
			cam = new OrthographicCamera(700, 466);
			this.width = 700;
			this.height = 466;
		} else if (width == 320 && height == 240) {
			cam = new OrthographicCamera(700, 525);
			this.width = 700;
			this.height = 525;
		} else if (width == 400 && height == 240) {
			cam = new OrthographicCamera(800, 480);
			this.width = 800;
			this.height = 480;
		} else if (width == 432 && height == 240) {
			cam = new OrthographicCamera(700, 389);
			this.width = 700;
			this.height = 389;
		} else if (width == 960 && height == 640) {
			cam = new OrthographicCamera(800, 533);
			this.width = 800;
			this.height = 533;
		} else if (width == 1366 && height == 768) {
			cam = new OrthographicCamera(1280, 720);
			this.width = 1280;
			this.height = 720;
		} else if (width == 1366 && height == 720) {
			cam = new OrthographicCamera(1280, 675);
			this.width = 1280;
			this.height = 675;
		} else if (width == 1536 && height == 1152) {
			cam = new OrthographicCamera(1366, 1024);
			this.width = 1366;
			this.height = 1024;
		} else if (width == 1920 && height == 1152) {
			cam = new OrthographicCamera(1366, 854);
			this.width = 1366;
			this.height = 854;
		} else if (width == 1920 && height == 1200) {
			cam = new OrthographicCamera(1366, 800);
			this.width = 1280;
			this.height = 800;
		} else if (width > 1280) {
			cam = new OrthographicCamera(1280, 768);
			this.width = 1280;
			this.height = 768;
		} else if (width < 800) {
			cam = new OrthographicCamera(800, 480);
			this.width = 800;
			this.height = 480;
		} else {
			cam = new OrthographicCamera(width, height);
		}
		cam.position.x = 400;
		cam.position.y = 240;
		cam.update();
		backgroundFX.resize(width, height);
		gameBatch.getProjectionMatrix().set(cam.combined);

	}

	public Array<Vector2> generatePositions(int n) {
		Array<Vector2> positions = new Array<Vector2>();
		for (int i = 1; i <= n; ++i) {
			positions.add(new Vector2(MathUtils.cos(i / n), MathUtils.sin(i / n)).scl(200));
		}
		return positions;
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		counter++;
		delta = Math.min(0.06f, delta);
		time+= delta;
		// System.out.println(delta);

		backgroundFX.render();
		if (counter >= 200) {
			counter = 0;

			
			// adds random line of enemies every 200 ticks

			for (int i = 0; i < 5; i++) {
				int rand = r.nextInt(3);

				switch (rand) {
				case 1:
					JellyFish jellyFish = new JellyFish(-5 + i * 100);
					enemies.add(jellyFish);
					break;
				case 2:
					Octopus octopus = new Octopus(-5 + i * 100);
					enemies.add(octopus);
					break;
				default:
					Shark shark = new Shark(-5 + i * 100);
					enemies.add(shark);

					break;
				}

			}

		}

		gameBatch.begin();

		// handles movement for all enemies
		for (int i = 0; i < enemies.size(); i++) {

			for (int j = 0; j < bullets.size(); j++) {

				if (collision(enemies.get(i).getX(), enemies.get(i).getY(), bullets.get(j).x, bullets.get(j).y, 60)) {
					bullets.get(j).active = false;
					enemies.get(i).changeHealth();
				}
			}

			enemies.get(i).changeX(time);
			
			if (enemies.get(i).health()<=enemies.get(i).initialHealth()/2)
			{
				//tints the enemy if the hp is half or less
				gameBatch.setColor(RED);
			}
			//draws the damaged enemy
			gameBatch.draw(enemies.get(i).texture(), enemies.get(i).getX(), enemies.get(i).getY());
			
			//clears the damage
			gameBatch.setColor(WHITE);



			if (collision(player.getX(), player.getY(), enemies.get(i).getX(),enemies.get(i).getY(),120) &&player.hitPoints > 0)
			{
				player.hitPoints-= enemies.get(i).damage();
				enemies.remove(i);			
			}
			
			
			//checks if enemy dies
			else if (enemies.get(i).health() <= 0) {
				int rand = r.nextInt(10);
				switch (rand) {
				case 1:
					Pearl pearl = new Pearl(enemies.get(i).getY() + 35, enemies.get(i).getX() + 35);
					collectibles.add(pearl);
					break;
				default:
					Coin coin = new Coin(enemies.get(i).getY() + 35, enemies.get(i).getX() + 35);
					collectibles.add(coin);
					break;
				}

				enemies.remove(i);
			}
		}

		//draws collectibles
		for (int i = 0; i < collectibles.size(); i++) {
			collectibles.get(i).changeX(time);
			gameBatch.draw(collectibles.get(i).texture(), collectibles.get(i).getX(), collectibles.get(i).getY());
			
			if (player.hitPoints > 0)
			{
			if (collision(player.getX(), player.getY(), collectibles.get(i).getX(), collectibles.get(i).getY(), 70) )
			{
				score+= collectibles.get(i).value();
				collectibles.remove(i);
			}
				
			else if (collectibles.get(i).getX()<-150) {
				collectibles.remove(i);
			}
				
		}
		}
		// Bubbles
		GameInstance.getInstance().bubbleParticles.draw(gameBatch);
		GameInstance.getInstance().bigBubbleParticles.draw(gameBatch);


		// Factories
		for (Ship ship : GameInstance.getInstance().factorys) {
			
			if (player.hitPoints>0) {
				ship.draw(gameBatch);

			} 
			
			else {
				GameInstance.getInstance().factorys.removeValue(ship, true);
				if (player.hitPoints <= 0)
				//	player.factoryDestruct();
					gameOver = true;
					

					
			}
		}

		// Explosions
		GameInstance.getInstance().sparkParticles.draw(gameBatch);
		GameInstance.getInstance().explosionParticles.draw(gameBatch);
		
		if (player.hitPoints>0) {
			scoreFont.draw(gameBatch,"Score: " + score, 175,500);
		}
		else {
			scoreFont.draw(gameBatch,"Score: " + score, 175,250);
		}
		hpFont.draw(gameBatch,"HP: " + player.hitPoints, -100,5);
		gameBatch.end();

		if (!gameOver && fade > 0 && fade < 100) {
			fade = Math.max(fade - delta / 2.f, 0);
			fadeBatch.begin();
			blackFade.setColor(blackFade.getColor().r, blackFade.getColor().g, blackFade.getColor().b, fade);
			blackFade.draw(fadeBatch);
			fadeBatch.end();
		}

		if (gameOver) {
			gameOverTimer -= delta;
		}
		if (gameOver && gameOverTimer <= 0) {
			fade = Math.min(fade + delta / 2.f, 1);
			fadeBatch.begin();
			blackFade.setColor(blackFade.getColor().r, blackFade.getColor().g, blackFade.getColor().b, fade);
			blackFade.draw(fadeBatch);
			fadeBatch.end();
			if (fade >= 1)
				game.setScreen(new MainMenu(game));
		}

	}

	@Override
	public void hide() {

	}

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.BACK) {
			gameOver = true;
			gameOverTimer = 0;
		}

		if (keycode == Input.Keys.ESCAPE) {
			gameOver = true;
			gameOverTimer = 0;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

}
