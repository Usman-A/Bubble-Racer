package de.swagner.paxbritannica.players;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;


import de.swagner.paxbritannica.Resources;
import de.swagner.paxbritannica.Ship;

public class FactoryProduction extends Ship {

	float delta;
	
	public FactoryProduction(int id, Vector2 position) {
		super(id, position);


		switch (id) {
		case 1:
			this.set(Resources.getInstance().factoryP1Small);
			break;
		case 2:
			this.set(Resources.getInstance().factoryP2Small);
			break;
		default:
			this.set(Resources.getInstance().factoryP3Small);
			break;
		}

		this.setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		
	}

	@Override
	public void draw(Batch batch) {
		delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
		move();
		fire();
		super.draw(batch);
		

	}

}
