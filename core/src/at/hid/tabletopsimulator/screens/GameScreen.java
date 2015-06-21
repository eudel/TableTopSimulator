package at.hid.tabletopsimulator.screens;

import at.hid.tabletopsimulator.TableTopSimulator;
import at.hid.tabletopsimulator.entities.Player;
import at.hid.tabletopsimulator.inventory.Inventory;
import at.hid.tabletopsimulator.inventory.InventoryActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;

public class GameScreen implements Screen {

	private InventoryActor inventoryActor;

	public static Stage stage;
	private TiledMap map;
//	private OrthogonalTiledMapRenderer renderer;
	private IsometricTiledMapRenderer renderer;
	private OrthographicCamera camera;
	private Player player;
	private float step = 0.125f;
	private final Matrix4 matrix = new Matrix4();

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.I)) {
			if (inventoryActor.isVisible()) {
				inventoryActor.setVisible(false);
			} else {
				inventoryActor.setVisible(true);
			}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.moveYpos(step);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.moveYneg(step);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.moveXneg(step);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.moveXpos(step);
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			camera.position.set(camera.position.x, camera.position.y - 16, camera.position.z);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			camera.position.set(camera.position.x, camera.position.y + 16, camera.position.z);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			camera.position.set(camera.position.x - 16, camera.position.y, camera.position.z);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			camera.position.set(camera.position.x + 16, camera.position.y, camera.position.z);
		}
		
		camera.update();
		renderer.setView(camera);
		renderer.render();

		renderer.getBatch().begin();
		player.draw(renderer.getBatch());
		renderer.getBatch().end();

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

		stage.getViewport().update(width, height, true);
		camera.viewportHeight = height;
		camera.viewportWidth = width;
		camera.update();
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Skin skin = TableTopSimulator.assets.get("ui/inventory.json", Skin.class);

//		map = new TmxMapLoader().load("maps/ortho_home.tmx");
		map = new TmxMapLoader().load("maps/home.tmx");

//		renderer = new OrthogonalTiledMapRenderer(map);
		renderer = new IsometricTiledMapRenderer(map);

		camera = new OrthographicCamera();

		player = new Player(new Sprite(new Texture("sprites/hero.png")), (TiledMapTileLayer) map.getLayers().get("collision"));
		float x = (Float) map.getLayers().get("event").getObjects().get("central_city-home").getProperties().get("x");
		float y = (Float) map.getLayers().get("event").getObjects().get("central_city-home").getProperties().get("y");

		player.setX(x);
		player.setY(y);

		camera.position.set(0, 0, 0);
		renderer.setView(camera);

		DragAndDrop dragAndDrop = new DragAndDrop();
		inventoryActor = new InventoryActor(new Inventory(30), dragAndDrop, skin);
		stage.addActor(inventoryActor);
	}

	@Override
	public void resume() {
	}

	@Override
	public void pause() {

	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		dispose();
	}

	@Override
	public void dispose() {
		map.dispose();
		renderer.dispose();
		player.getTexture().dispose();
		stage.dispose();
	}

}
