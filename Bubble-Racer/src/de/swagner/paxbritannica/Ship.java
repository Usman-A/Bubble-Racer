package de.swagner.paxbritannica;

import Collectables.Bullet;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import de.swagner.paxbritannica.players.FactoryProduction;

import java.util.ArrayList;

public class Ship extends Sprite {

    /*
     * Scratch space for computing a target's direction. This is safe (as a static)
     * because its only used in goTowardsOrAway which is only called on the render
     * thread (via update).
     */
    public float aliveTime = 0.0f;
    public Vector2 position = new Vector2();
    public Vector2 collisionCenter = new Vector2();
    public Array<Vector2> collisionPoints = new Array<Vector2>();
    public boolean alive = true;
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public int id = 0;
    protected float amount = 1.0f;
    protected float hitPoints = 600f;
    protected float maxHitPoints = 600f;
    int counter = 0;
    private float delta = 0.0f;
    private float deathCounter = 50f;
    private float nextExplosion = 10f;
    private float opacity = 5.0f;

    public Ship(int id, Vector2 position) {
        super();

        this.id = id;
        this.hitPoints = hitPoints / this.id;

        this.position.set(position);

        collisionPoints.clear();
        collisionPoints.add(new Vector2());
        collisionPoints.add(new Vector2());
        collisionPoints.add(new Vector2());
        collisionPoints.add(new Vector2());

        this.setOrigin(this.getWidth() / 2.f, this.getHeight() / 2.f);
    }

    @Override
    public void draw(Batch batch) {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());

        aliveTime += delta;
        collisionPoints.get(0).set(this.getVertices()[0], this.getVertices()[1]);
        collisionPoints.get(1).set(this.getVertices()[5], this.getVertices()[6]);
        collisionPoints.get(2).set(this.getVertices()[10], this.getVertices()[11]);
        collisionPoints.get(3).set(this.getVertices()[15], this.getVertices()[16]);

        collisionCenter.set(collisionPoints.get(0)).add(collisionPoints.get(2)).scl(0.5f);

        position.add(0 * delta, 0 * delta);

        this.setPosition(position.x, position.y);
        //rest of the bullets get worked on here
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).Draw(batch);
            if (!bullets.get(i).active) {
                bullets.remove(i);
            }
        }
        GameScreen.bullets = this.bullets;
        super.draw(batch);
    }

    public void fire() {
        counter++;

        //if statement is for all fresh bullets add counter
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && counter >= 10) {
            Bullet bullet = new Bullet(position.y);
            bullets.add(bullet);
            counter = 0;
        }
    }

    // for ship turning and projectile
    public void move() {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        // edit later: if statement should not be inside call to method
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && position.y < 395)
            position.add(0 * delta, 400 * delta);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && position.y > -30)
            position.add(0 * delta, -400 * delta);


    }

    public Vector2 randomPointOnShip() {
        return new Vector2(collisionCenter.x + MathUtils.random(-this.getWidth() / 2, this.getWidth() / 2),
                collisionCenter.y + MathUtils.random(-this.getHeight() / 2, this.getHeight() / 2));
    }

    public void destruct() {
        if (this instanceof FactoryProduction) {
            factoryDestruct();
        } else {
            GameInstance.getInstance().explode(this);
            alive = false;
        }
    }

    public void factoryDestruct() {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());

        if (deathCounter > 0) {
            this.setColor(1, 1, 1, Math.min(1, opacity));
            opacity -= 1 * delta;
            if (Math.floor(deathCounter) % nextExplosion == 0) {
                GameInstance.getInstance().explode(this, randomPointOnShip());
                nextExplosion = MathUtils.random(2, 6);
            }
            deathCounter -= 10 * delta;
        } else {
            for (int i = 1; i <= 10; ++i) {
                GameInstance.getInstance().explode(this, randomPointOnShip());
            }
            alive = false;
        }
    }

}
