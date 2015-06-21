package at.hid.tabletopsimulator.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Sprite {

	/**
	 * the movement velocity
	 */
	private Vector2 velocity = new Vector2();
	private float speed = 60 * 2, gravity = 60 * 1.8f;
	private int tileWidth = 16, tileHeight = 16;

	private TiledMapTileLayer collisionLayer;

	public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
		super(sprite);
		this.collisionLayer = collisionLayer;
	}

	public void draw(SpriteBatch spriteBatch) {
		update(Gdx.graphics.getDeltaTime());
		super.draw(spriteBatch);
	}

	private void update(float delta) {
		// apply gravity
		velocity.y -= gravity * delta;

		// clamp velocity
		if (velocity.y > speed)
			velocity.y = speed;
		else if (velocity.y < -speed)
			velocity.y = -speed;

		// save old position
		float oldX = getX(), oldy = getY();
		boolean collisionX = false, collisionY = false;

		// move on x
		setX(getX() + velocity.x * delta);
		if (velocity.x < 0) {
			// top left
			collisionX = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("collision");

			// middle left
			collisionX = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight() / 2) / tileHeight)).getTile().getProperties().containsKey("collision");

			// bottom left
			collisionX = collisionLayer.getCell((int) (getX() / tileWidth), (int) ((getY()) / tileHeight)).getTile().getProperties().containsKey("collision");

		} else if (velocity.x > 0) {

		}

		// move on y
		if (velocity.y < 0) {
			setY(getY() + velocity.y * delta);
		} else if (velocity.y > 0) {
			setY(getY() - velocity.y * delta);
		}

	}

	public void moveXpos(float steps) {
		float newX = getX() + (steps * tileWidth);
		boolean collisionX = false;

		try {
			collisionX = collisionLayer.getCell((int) ((newX + tileWidth) / tileWidth), (int) (getY() / tileHeight)).getTile().getProperties().containsKey("collision");
		} catch (Exception e) {

		}
		if (!collisionX) {
			setX(newX);
		}
	}
	public void moveXneg(float steps) {
		float newX = getX() - (steps * tileWidth);
		boolean collisionX = false;
		
		try {
			collisionX = collisionLayer.getCell((int) (newX / tileWidth), (int) (getY() / tileHeight)).getTile().getProperties().containsKey("collision");
		} catch (Exception e) {
			
		}
		if (!collisionX) {
			setX(newX);
		}
	}

	public void moveYpos(float steps) {
		float newY = getY() + (steps * tileHeight);
		boolean collisionY = false;

		try {
			collisionY = collisionLayer.getCell((int) (getX() / tileWidth), (int) (newY / tileHeight)).getTile().getProperties().containsKey("collision");
		} catch (Exception e) {

		}
		if (!collisionY) {
			setY(newY);
		}
	}
	public void moveYneg(float steps) {
		float newY = getY() - (steps * tileHeight);
		boolean collisionY = false;
		
		try {
			collisionY = collisionLayer.getCell((int) (getX() / tileWidth), (int) (newY / tileHeight)).getTile().getProperties().containsKey("collision");
		} catch (Exception e) {
			
		}
		if (!collisionY) {
			setY(newY);
		}
	}
}
