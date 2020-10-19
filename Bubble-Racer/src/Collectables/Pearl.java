/*Class used for creating bullets
 * Usman Asad
 * Talha Awan
 */
package Collectables;

import com.badlogic.gdx.graphics.Texture;
import de.swagner.paxbritannica.Resources;

public class Pearl implements Collectible {
    public Texture texture = Resources.getInstance().pearl;
    float x, y;

    public Pearl(float startingY, float startingX) {
        y = startingY;
        x = startingX;
    }

    public Texture texture() {
        return texture;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void changeX(float time) {
        x -= 10 * Math.pow(2, time / 45);
    }

    public int value() {
        return 50;
    }

}