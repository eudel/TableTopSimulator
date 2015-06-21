/**
 * 
 */
package at.hid.tabletopsimulator.screens;

import at.hid.tabletopsimulator.TableTopSimulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
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
public class Options implements Screen {

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
		table.setFillParent(true);;
	}

	@Override
	public void show() {
		TableTopSimulator.debug(this.getClass().toString(), "creating Options screen");
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		Gdx.input.setInputProcessor(stage);

		// creating skin
		TableTopSimulator.debug(this.getClass().toString(), "creating skin");
		skin = TableTopSimulator.assets.get("ui/gui.json", Skin.class);

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// creating heading
		TableTopSimulator.debug(this.getClass().toString(), "creating heading");
		Label lblHeading = new Label(TableTopSimulator.getLangBundle().format("Options.lblHeading.text"), skin);

		// creating checkboxes
		TableTopSimulator.debug(this.getClass().toString(), "creating checkboxes");
		final CheckBox cbVsync = new CheckBox(TableTopSimulator.getLangBundle().format("Options.cbVsync.text"), skin);
		cbVsync.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (cbVsync.isChecked()) {
					Gdx.app.getPreferences(TableTopSimulator.TITLE).putBoolean("vsync", true);
					TableTopSimulator.DEBUG = true;
				} else {
					Gdx.app.getPreferences(TableTopSimulator.TITLE).putBoolean("vsync", false);
					TableTopSimulator.DEBUG = false;
				}
				Gdx.app.getPreferences(TableTopSimulator.TITLE).flush();
			}
		});
		if (Gdx.app.getPreferences(TableTopSimulator.TITLE).getBoolean("vsync")) {
			cbVsync.setChecked(true);
		}
		
		final CheckBox cbDebug = new CheckBox(TableTopSimulator.getLangBundle().format("Options.cbDebug.text"), skin);
		cbDebug.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (cbDebug.isChecked()) {
					Gdx.app.getPreferences(TableTopSimulator.TITLE).putBoolean("debug", true);
					TableTopSimulator.DEBUG = true;
				} else {
					Gdx.app.getPreferences(TableTopSimulator.TITLE).putBoolean("debug", false);
					TableTopSimulator.DEBUG = false;
				}
				Gdx.app.getPreferences(TableTopSimulator.TITLE).flush();
			}
		});
		if (Gdx.app.getPreferences(TableTopSimulator.TITLE).getBoolean("debug")) {
			cbDebug.setChecked(true);
		}

		// creating buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating buttons");
		TextButton btnBack = new TextButton(TableTopSimulator.getLangBundle().format("Options.btnBack.text"), skin);
		btnBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to MainMenu screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
				dispose();
			}
		});
		btnBack.pad(10);

		TextButton btnAbout = new TextButton(TableTopSimulator.getLangBundle().format("Options.btnAbout.text"), skin);
		btnAbout.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to About screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new About());
				dispose();
			}
		});
		btnAbout.pad(10);

		// building horizontal groups
		TableTopSimulator.debug(this.getClass().toString(), "building horizontal groups");
		HorizontalGroup hg1 = new HorizontalGroup();
		hg1.addActor(btnAbout);
		hg1.addActor(btnBack);

		// building ui
		TableTopSimulator.debug(this.getClass().toString(), "building ui");
		table.add(lblHeading).spaceBottom(100).row();
		table.add(cbVsync).spaceBottom(15).row();
		table.add(cbDebug).spaceBottom(15).row();
		table.add(hg1).spaceBottom(15).row();
		if (TableTopSimulator.DEBUG) {
			table.debug(); // draw debug lines 
		}
		stage.addActor(table);
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
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up Options screen");
		stage.dispose();
	}

}
