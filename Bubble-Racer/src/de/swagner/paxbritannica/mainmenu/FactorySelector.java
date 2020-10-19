package de.swagner.paxbritannica.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import de.swagner.paxbritannica.Resources;

public class FactorySelector extends Sprite {

    public boolean picked = false;

    public BoundingBox collision = new BoundingBox();

    public Vector3 collisionMinVector = new Vector3();
    public Vector3 collisionMaxVector = new Vector3();
    float delta;
    private float fade = 0.2f;
    private float fadeButton = 0.0f;
    private float pulse_time = 0;

    public FactorySelector(Vector2 position, int id) {
        super();
        this.setPosition(position.x, position.y);

        switch (id) {
            case 1:
                this.set(Resources.getInstance().factoryP1);
                break;
            case 2:
                this.set(Resources.getInstance().factoryP2);
                break;
            default:
                this.set(Resources.getInstance().factoryP3);
                break;
        }
        setRotation(90);
        this.setPosition(position.x, position.y);
        this.setColor(0, 0, 0, 1);


        float pulse = (1 + MathUtils.cos((pulse_time / 180.f) * 2.f * MathUtils.PI)) / 2.f;
        float color = fade * pulse + 1 * (1 - pulse);
        this.setColor(color, color, color, 1);


    }

    public void reset() {
        picked = false;


        fade = 0.2f;
        fadeButton = 0.0f;

        pulse_time = 0;
        float pulse = (1 + MathUtils.cos((pulse_time / 180.f) * 2.f * MathUtils.PI)) / 2.f;
        float color = fade * pulse + 1 * (1 - pulse);
        this.setColor(color, color, color, 1);


    }

    @Override
    public void draw(Batch batch) {


        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());

        super.draw(batch);

        collisionMinVector.set(this.getVertices()[0], this.getVertices()[1], -10);
        collisionMaxVector.set(this.getVertices()[10], this.getVertices()[11], 10);
        collision.set(collisionMinVector, collisionMaxVector);

        collisionMinVector.set(this.getVertices()[0], this.getVertices()[1], -10);
        collisionMaxVector.set(this.getVertices()[10], this.getVertices()[11], 10);
        collisionMinVector.y += ((this.getVertices()[11] - this.getVertices()[1]) / 2);

        collisionMinVector.set(this.getVertices()[0], this.getVertices()[1], -10);
        collisionMaxVector.set(this.getVertices()[10], this.getVertices()[11], 10);
        collisionMaxVector.y -= ((this.getVertices()[11] - this.getVertices()[1]) / 2);


        pulse_time += Gdx.graphics.getDeltaTime();

//		float pulse = (1 + MathUtils.cos((pulse_time/5.f)*2.f*MathUtils.PI))/2.f;
        if (picked) {
            fade = Math.min(fade + delta, 1);
            this.setColor(fade, fade, fade, 1);

            fadeButton = Math.max(fadeButton - delta, 0);
        }


    }
}
