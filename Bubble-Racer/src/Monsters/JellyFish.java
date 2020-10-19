package Monsters;

import com.badlogic.gdx.graphics.Texture;

import de.swagner.paxbritannica.Resources;

public class JellyFish implements Enemy {
	int health = 600;
	int initialHealth = health;
	float x, y;
	public Texture texture = Resources.getInstance().jellyFish;

	public JellyFish(float startingY) {
		y = startingY;
		x = 900;
	}

	public Texture texture() {
		return this.texture;
	}


	public int initialHealth() {
		return initialHealth;
	}
	
	public int health() {
		return health;
	}

	public int damage() {
		return 50;
	}

	public void changeX(float time){
		x -= 3 * Math.pow(2,time/45);
	}

	public void changeHealth() {
		health-=300;
	}
	
	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

}
