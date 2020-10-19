/*Interface used for creating collectables
 * Usman Asad
 * Talha Awan
 */
package Collectables;

import com.badlogic.gdx.graphics.Texture;

public interface Collectible {

    //names of common methods used in the collectable classes
    Texture texture();

    float getX();

    float getY();

    void changeX(float time);

    int value();

}
