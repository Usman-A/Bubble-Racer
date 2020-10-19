/*Class used for creating bullets
 * Usman Asad
 * Talha Awan
 */

package Collectables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.swagner.paxbritannica.Resources;

//class for bullets fired from the ship
public class Bullet {

    // initial x positon, where the bullet spawns
    public float x;
    public float y;

    // check if bullet is live
    public boolean active = true;
    public Texture texture = Resources.getInstance().bullet;
    // initializing texture
    SpriteBatch batch;

    // initializing the bullet
    public Bullet(float startingY) {
        y = startingY + 45;
        x = 40;
    }

    //draws the bullet
    public void Draw(Batch batch) {
        batch.draw(texture, x, y);
        x += 10;
        if (x > 900) {
            active = false;
        }
    }

}
