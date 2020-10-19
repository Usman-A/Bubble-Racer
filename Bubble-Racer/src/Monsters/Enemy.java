package Monsters;

import com.badlogic.gdx.graphics.Texture;

public interface Enemy {

	Texture texture();

	int initialHealth();
	
	int health();

	int damage();

	float getX();

	float getY();

	void changeX(float time);
	
	public void changeHealth();

}
