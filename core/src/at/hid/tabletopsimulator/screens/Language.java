package at.hid.tabletopsimulator.screens;

import java.util.Locale;

import at.hid.tabletopsimulator.TableTopSimulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * @author dunkler_engel
 *
 */
public class Language implements Screen {

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
		TableTopSimulator.debug(this.getClass().toString(), "creating Language screen");
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		Gdx.input.setInputProcessor(stage);

		// creating skin
		TableTopSimulator.debug(this.getClass().toString(), "creating skin");
		skin = TableTopSimulator.assets.get("ui/gui.json", Skin.class);

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// creating heading
		TableTopSimulator.debug(this.getClass().toString(), "creating heading");
		Label lblHeading = new Label(TableTopSimulator.getLangBundle().format("Language.lblHeading.text"), skin);

		// creating list
		TableTopSimulator.debug(this.getClass().toString(), "creating list");
		final List<String> list = new List<String>(skin);
		list.setItems(new String[] { "Deutsch", "English" });
		list.setSelected(Locale.getDefault().getDisplayLanguage());
		list.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.log(this.getClass().toString(), "switching Language to " + list.getSelected());

				if (list.getSelected().equals("Deutsch")) {
					Locale locale_de_de = Locale.GERMANY;
					Locale.setDefault(locale_de_de);
				} else if (list.getSelected().equals("English")) {
					Locale locale_en_uk = Locale.UK;
					Locale.setDefault(locale_en_uk);
				} else {
					Locale locale_en_uk = Locale.UK;
					Locale.setDefault(locale_en_uk);
				}
				Gdx.app.getPreferences(TableTopSimulator.TITLE).putString("lang", Locale.getDefault().toString());
				Gdx.app.getPreferences(TableTopSimulator.TITLE).flush();
				((Game) Gdx.app.getApplicationListener()).setScreen(new Language());
				dispose();
			}
		});

		// creating buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating buttons");
		TextButton btnBack = new TextButton(TableTopSimulator.getLangBundle().format("Language.btnBack.text"), skin);
		btnBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to MainMenu screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
				dispose();
			}
		});
		btnBack.pad(10);

		// building ui
		TableTopSimulator.debug(this.getClass().toString(), "building ui");
		table.add(lblHeading).spaceBottom(15);
		table.row();
		table.add(list).spaceBottom(15);
		table.row();
		table.add(btnBack).spaceBottom(15);
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
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up Language screen");
		stage.dispose();
	}

}
