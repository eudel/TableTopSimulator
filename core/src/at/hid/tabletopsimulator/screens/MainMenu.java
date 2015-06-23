/**
 * 
 */
package at.hid.tabletopsimulator.screens;

import java.util.Random;

import at.hid.tabletopsimulator.TableTopSimulator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * @author dunkler_engel
 *
 */
public class MainMenu implements Screen {

	private Stage stage;
	private Skin skin;
	private Table table;
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		table.invalidateHierarchy();
		table.setFillParent(true);
	}

	@Override
	public void show() {
		TableTopSimulator.debug(this.getClass().toString(), "creating MainMenu screen");
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		Gdx.input.setInputProcessor(stage);

		// creating skin
		TableTopSimulator.debug(this.getClass().toString(), "creating skin");
		TableTopSimulator.assets.update();
		skin = TableTopSimulator.assets.get("ui/gui.json", Skin.class);

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// creating heading
		TableTopSimulator.debug(this.getClass().toString(), "creating heading");
		Label lblHeading = new Label(TableTopSimulator.TITLE, skin);
		lblHeading.setFontScale(2);

		FileHandle fileSplash = Gdx.files.external(".hidlauncher/" + TableTopSimulator.TITLE + "/splash.txt");
		if (!fileSplash.exists()) {
			fileSplash.writeString("Splash\n", true);
			fileSplash.writeString("working splash message\n", true);
			fileSplash.writeString("Java rocks!!\n", true);
			fileSplash.writeString("supports external splash file\n", true);
			fileSplash.writeString("feel free to add your own", true);
		}
		String[] splash = null;
		try {
			String splashFileExt = fileSplash.readString("UTF-8");
			splash = splashFileExt.split("\n");
		} catch (Exception e) {
			TableTopSimulator.error(this.getClass().toString(), "ERROR reading splash file", e);
		}

		// creating splash text
		TableTopSimulator.debug(this.getClass().toString(), "creating splash text");
		Label lblSplash = new Label(splash[new Random().nextInt(splash.length)], skin);

		// creating buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating buttons");
		TextButton btnPlay = new TextButton(TableTopSimulator.getLangBundle().format("MainMenu.btnPlay.text"), skin);
		btnPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to CreateGame screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new LoadChar());
				dispose();
			}
		});

		btnPlay.pad(10);

		TextButton btnLanguage = new TextButton("", skin, "language");
		btnLanguage.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to Language screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new Language());
				dispose();
			}
		});

		btnLanguage.pad(10);

		TextButton btnOptions = new TextButton(TableTopSimulator.getLangBundle().format("MainMenu.btnOptions.text"), skin);
		btnOptions.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to Options screen");
				 ((Game) Gdx.app.getApplicationListener()).setScreen(new Options());
				 dispose();
			}
		});

		btnOptions.pad(10);

		TextButton btnExit = new TextButton(TableTopSimulator.getLangBundle().format("MainMenu.btnExit.text"), skin);
		btnExit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "exiting game");
				Gdx.app.exit();
			}
		});

		btnExit.pad(10);
		
		// building horizontal groups
		TableTopSimulator.debug(this.getClass().toString(), "building horizontal groups");
		HorizontalGroup hg1 = new HorizontalGroup();
		hg1.addActor(btnLanguage);
		hg1.addActor(btnOptions);
		hg1.addActor(btnExit);

		// building ui
		TableTopSimulator.debug(this.getClass().toString(), "building ui");
		table.add(lblHeading).spaceBottom(100).row();
		table.add(lblSplash).spaceBottom(15).row();
		table.add(btnPlay).spaceBottom(15).row();
		stage.setKeyboardFocus(btnPlay);
		table.add(hg1).spaceBottom(15);
		if (TableTopSimulator.DEBUG) {
			table.debug(); // draw debug lines
		}
		stage.addActor(table);

	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up main menu");
//		stage.dispose();
	}

}
