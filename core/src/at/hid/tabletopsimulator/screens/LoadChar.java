package at.hid.tabletopsimulator.screens;

import java.util.ArrayList;

import at.hid.tabletopsimulator.TableTopSimulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class LoadChar implements Screen {

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
		TableTopSimulator.debug(this.getClass().toString(), "creating LoadChar screen");
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		Gdx.input.setInputProcessor(stage);

		// creating skin
		TableTopSimulator.debug(this.getClass().toString(), "creating skin");
		skin = TableTopSimulator.assets.get("ui/gui.json", Skin.class);

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// creating heading
		TableTopSimulator.debug(this.getClass().toString(), "creating heading");
		Label lblHeading = new Label(TableTopSimulator.getLangBundle().format("LoadChar.lblHeading.text"), skin);

		// creating lists
		TableTopSimulator.debug(this.getClass().toString(), "creating lists");
		TableTopSimulator.debug(this.getClass().toString(), "creating savedGames list");
		final List<String> listSavedGames = new List<String>(skin);

		// creating buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating buttons");
		final TextButton btnPlay = new TextButton(TableTopSimulator.getLangBundle().format("LoadChar.btnPlay.text"), skin);
		btnPlay.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!btnPlay.isDisabled()) {
					TableTopSimulator.playerprofile.loadProfile(listSavedGames.getSelected());

					((Game) Gdx.app.getApplicationListener()).setScreen(new CharInfo());
					dispose();
				}
			}
		});
		btnPlay.setDisabled(true);
		btnPlay.pad(10);

		TextButton btnNew = new TextButton(TableTopSimulator.getLangBundle().format("LoadChar.btnNew.text"), skin);
		btnNew.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to CreateNewChar screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new CreateNewChar());
				dispose();
			}
		});
		btnNew.pad(10);

		final TextButton btnRename = new TextButton(TableTopSimulator.getLangBundle().format("LoadChar.btnRename.text"), skin);
		btnRename.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!btnRename.isDisabled()) {
					TableTopSimulator.debug(this.getClass().toString(), "switching to RenameChar screen");
					TableTopSimulator.playerprofile.loadProfile(listSavedGames.getSelected());

					((Game) Gdx.app.getApplicationListener()).setScreen(new RenameChar());
					dispose();
				}
			}
		});
		btnRename.setDisabled(true);
		btnRename.pad(10);

		final TextButton btnDelete = new TextButton(TableTopSimulator.getLangBundle().format("LoadChar.btnDelete.text"), skin);
		btnDelete.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!btnDelete.isDisabled()) {
					TableTopSimulator.app42.storageServiceDeleteDocumentsByKeyValue(TableTopSimulator.TITLE, "heroes", "name", listSavedGames.getSelected());
					
					((Game) Gdx.app.getApplicationListener()).setScreen(new LoadChar());
					dispose();
				}
			}
		});
		btnDelete.setDisabled(true);
		btnDelete.pad(10);

		TextButton btnBack = new TextButton(TableTopSimulator.getLangBundle().format("LoadChar.btnBack.text"), skin);
		btnBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to MainMenu screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
				dispose();
			}
		});
		btnBack.pad(10);
		
		ScrollPane spList = new ScrollPane(listSavedGames);
		ArrayList<String> saveNames = null;
		try {
			TableTopSimulator.app42.storageServiceFindAllDocuments(TableTopSimulator.TITLE, "heroes");
		} catch (Exception e) {
			TableTopSimulator.debug(this.getClass().toString(), "error reading saved chars (no saved chars?)");
		}
		TableTopSimulator.app42.storageGetJsonDocList();
		saveNames = TableTopSimulator.app42.storageGetSaveNames();
		String[] data = new String[saveNames.size()];
		saveNames.toArray(data);
		listSavedGames.setItems(data);

		listSavedGames.setSelectedIndex(-1);
		listSavedGames.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				btnPlay.setDisabled(false);
				btnRename.setDisabled(false);
				btnDelete.setDisabled(false);
			}
		});

		
		HorizontalGroup hg1 = new HorizontalGroup();
		hg1.addActor(btnPlay);
		hg1.addActor(btnNew);

		HorizontalGroup hg2 = new HorizontalGroup();
		hg2.addActor(btnRename);
		hg2.addActor(btnDelete);
		hg2.addActor(btnBack);
		
		// building ui
		TableTopSimulator.debug(this.getClass().toString(), "building ui");
		table.add(lblHeading).spaceBottom(100).row();
		table.add(spList).spaceBottom(15).row();
		table.add(hg1).spaceBottom(15).row();
		table.add(hg2).spaceBottom(15).row();
		if (TableTopSimulator.DEBUG) {
			table.debug(); // draw debug lines
			spList.debug();
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
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up LoadChar screen");
		stage.dispose();
	}

}
