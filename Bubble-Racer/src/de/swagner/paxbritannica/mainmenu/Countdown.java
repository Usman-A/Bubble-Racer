package de.swagner.paxbritannica.mainmenu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import de.swagner.paxbritannica.Resources;

public class Countdown extends Sprite {

    public boolean finished = false;
    float delta;
    private boolean showed = true;
    private float fade = 0.0f;
    private int cnt = 3;
    private Vector2 position = new Vector2();

    public Countdown(Vector2 position) {
        this.position = position;

        changeTexture(3);
    }

    public void reset() {
        changeTexture(3);
        finished = false;
        showed = true;
        fade = 0.0f;
        cnt = 3;
        this.setRotation(0);
        this.setScale(1f);
        this.setPosition(position.x, position.y);
        this.setColor(0, 0, 0, 0);
    }

    private void changeTexture(int id) {

        switch (id) {
		/*
		case 4:
			this.set(Resources.getInstance().cnt5);  
			break;
			*/
            case 2:
                this.set(Resources.getInstance().cnt2);
                break;
            case 3:
                this.set(Resources.getInstance().cnt3);
                break;
            default:
                this.set(Resources.getInstance().cnt1);
                break;
        }
        this.setRotation(0);
        this.setScale(1f);
        this.setPosition(position.x, position.y);
        this.setColor(0, 0, 0, 1);
    }

    @Override
    public void draw(Batch batch) {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());

        super.draw(batch);

        if (cnt < 1) {
            finished = true;
            this.setColor(1, 1, 1, 0);
            return;
        }

        if (showed) {
            fade = Math.min(fade + delta * 2.f, 1);
        } else {
            fade = Math.max(fade - delta * 2.f, 0);
        }
        this.setColor(1, 1, 1, fade);

        if (fade == 1) {
            showed = !showed;
        }
        if (fade == 0) {
            showed = !showed;
            --cnt;
            changeTexture(cnt);
        }
    }
}
