package de.swagner.paxbritannica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import de.swagner.paxbritannica.particlesystem.BigBubbleParticleEmitter;
import de.swagner.paxbritannica.particlesystem.ExplosionParticleEmitter;
import de.swagner.paxbritannica.particlesystem.BubbleParticleEmitter;
import de.swagner.paxbritannica.particlesystem.SparkParticleEmitter;
import de.swagner.paxbritannica.players.FactoryProduction;

public class GameInstance {
	
	public boolean debugMode = false;


	public Array<Ship> factorys = new Array<Ship>();


	public BubbleParticleEmitter bubbleParticles = new BubbleParticleEmitter();
	public BigBubbleParticleEmitter bigBubbleParticles = new BigBubbleParticleEmitter();

	public SparkParticleEmitter sparkParticles = new SparkParticleEmitter();
	public ExplosionParticleEmitter explosionParticles = new ExplosionParticleEmitter();
	public int difficultyConfig = 0;
	public int factoryHealthConfig = 0;
	public int antiAliasConfig = 0;	

	public static GameInstance instance;

	public static GameInstance getInstance() {
		if (instance == null) {
			instance = new GameInstance();
		}
		return instance;
	}
	
	public void resetGame() {

		factorys.clear();

		
		bubbleParticles.dispose();
		bigBubbleParticles.dispose();
		sparkParticles.dispose();
		explosionParticles.dispose();

		bubbleParticles = new BubbleParticleEmitter();
		bigBubbleParticles = new BigBubbleParticleEmitter();

		sparkParticles = new SparkParticleEmitter();
		explosionParticles = new ExplosionParticleEmitter();
		
		Preferences prefs = Gdx.app.getPreferences("paxbritannica");
		GameInstance.getInstance().difficultyConfig  = prefs.getInteger("difficulty",0);
		GameInstance.getInstance().factoryHealthConfig  = prefs.getInteger("factoryHealth",0);
		GameInstance.getInstance().antiAliasConfig  = prefs.getInteger("antiAliasConfig",1);
	}


	public void laser_hit(Vector2 pos, Vector2 vel) {
		sparkParticles.addLaserExplosion(pos, vel);
	}

	public void explode(Ship ship) {
		explode(ship, ship.collisionCenter);
	}

	public void explode(Ship ship, Vector2 pos) {

		if (ship instanceof FactoryProduction) {
			explosionParticles.addBigExplosion(pos);
		} else {
			explosionParticles.addSmallExplosion(pos);
		}
	}

}