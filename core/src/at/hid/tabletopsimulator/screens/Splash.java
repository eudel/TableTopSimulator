/**
 * 
 */
package at.hid.tabletopsimulator.screens;

import at.hid.tabletopsimulator.TableTopSimulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * @author dunkler_engel
 *
 */
public class Splash implements Screen {

	private SpriteBatch batch;
	private Sprite splash;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		splash.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		splash.setSize(width, height);
	}

	@Override
	public void show() {
		// apply preferences
		TableTopSimulator.debug(this.getClass().toString(), "apply preferences");
		Gdx.graphics.setVSync(Gdx.app.getPreferences(TableTopSimulator.TITLE).getBoolean("vsync", false));

		TableTopSimulator.debug(this.getClass().toString(), "creating Splash screen");

		TableTopSimulator.assets.load("ui/gui.json", Skin.class);
		TableTopSimulator.assets.load("ui/inventory.json", Skin.class);
		TableTopSimulator.assets.load("icons/icons.atlas", TextureAtlas.class);

		batch = new SpriteBatch();

		Texture splashTexture = TableTopSimulator.assets.get("img/splash.jpg", Texture.class);
		splash = new Sprite(splashTexture);
		splash.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		TableTopSimulator.assets.finishLoading();
		TableTopSimulator.debug(this.getClass().toString(), "switching to MainMenu Screen");
		((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
		splash.getTexture().dispose();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up Splash screen");
		batch.dispose();
		splash.getTexture().dispose();
	}

}
