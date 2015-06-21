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
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * @author dunkler_engel
 *
 */
public class RenameChar implements Screen {

	private Stage stage;
	private Skin skin;
	private Table table;
	private Label lblCheckName;

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
		TableTopSimulator.debug(this.getClass().toString(), "creating RenameChar screen");
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		Gdx.input.setInputProcessor(stage);

		// creating skin
		TableTopSimulator.debug(this.getClass().toString(), "creating skin");
		skin = TableTopSimulator.assets.get("ui/gui.json", Skin.class);

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// creating heading
		TableTopSimulator.debug(this.getClass().toString(), "creating heading");
		Label lblHeading = new Label(TableTopSimulator.getLangBundle().format("RenameChar.lblHeading.text"), skin);
		lblCheckName = new Label("", skin);
		

		// creating textfield
		TableTopSimulator.debug(this.getClass().toString(), "creating textfield");
		final TextField txtNewGameName = new TextField(TableTopSimulator.playerprofile.getName(), skin);
		txtNewGameName.setSelection(0, txtNewGameName.getText().length());

		// creating buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating buttons");
		TextButton btnRename = new TextButton(TableTopSimulator.getLangBundle().format("RenameChar.btnRename.text"), skin);
		btnRename.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (checkName(txtNewGameName.getText())) {
					String oldName = TableTopSimulator.playerprofile.getName();
					TableTopSimulator.playerprofile.setName(txtNewGameName.getText());
					TableTopSimulator.playerprofile.saveProfile(oldName);

					TableTopSimulator.debug(this.getClass().toString(), "switching to LoadChar screen");
					((Game) Gdx.app.getApplicationListener()).setScreen(new LoadChar());
					dispose();
				}
			}
		});
		btnRename.pad(10);

		TextButton btnBack = new TextButton(TableTopSimulator.getLangBundle().format("RenameChar.btnBack.text"), skin);
		btnBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to LoadChar screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new LoadChar());
				dispose();
			}
		});
		btnBack.pad(10);
		
		HorizontalGroup hg1 = new HorizontalGroup();
		hg1.addActor(btnRename);
		hg1.addActor(btnBack);

		// building ui
		TableTopSimulator.debug(this.getClass().toString(), "building ui");
		table.add(lblHeading).spaceBottom(100).row();
		table.add(txtNewGameName).width(500).spaceBottom(15).row();
		stage.setKeyboardFocus(txtNewGameName);
		table.add(lblCheckName).spaceBottom(15).row();
		table.add(hg1);
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
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up CreateGame screen");
		stage.dispose();
	}

	public boolean checkName(String name) {
		try {
			TableTopSimulator.app42.storageServiceFindDocumentByKeyValue(TableTopSimulator.TITLE, "heroes", "name", name);
		} catch (Exception e) {
			lblCheckName.setText(TableTopSimulator.getLangBundle().format("CreateNewChar.lblCheckName.text", skin));
			return true;
		}
		lblCheckName.setText(TableTopSimulator.getLangBundle().format("CreateNewChar.lblCheckName.text1", skin));
		return false;
	}
}
