/*Class used for coins dropped from monsters
 * Usman Asad
 * Talha Awan
 */
package Collectables;

import com.badlogic.gdx.graphics.Texture;

import de.swagner.paxbritannica.Resources;

public class Coin implements Collectible{
	//initializing positions and the texture
	float x, y;
	public Texture texture = Resources.getInstance().coin;
	
	public Coin(float startingY, float startingX) {
		y = startingY;
		x = startingX;
	}

	public Texture texture() {
		return texture;
	}
//getting the x and y values
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	//changing the x variable as it travels through the screen
	public void changeX(float time){
		x -= 10 * Math.pow(2,time/45);
	}

	//how much the coin is worth
	public int value() {
		return 5;
	}


}
