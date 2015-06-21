package at.hid.tabletopsimulator.screens;

import org.json.JSONObject;

import at.hid.tabletopsimulator.TableTopSimulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class CreateNewChar implements Screen {

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
		TableTopSimulator.debug(this.getClass().toString(), "creating CreateNewChar screen");
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		Gdx.input.setInputProcessor(stage);

		// creating skin
		TableTopSimulator.debug(this.getClass().toString(), "creating skin");
		skin = TableTopSimulator.assets.get("ui/gui.json", Skin.class);

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// creating heading
		TableTopSimulator.debug(this.getClass().toString(), "creating heading");
		Label lblHeading = new Label(TableTopSimulator.getLangBundle().format("CreateNewChar.lblHeading.text"), skin);

		// creating textfield
		TableTopSimulator.debug(this.getClass().toString(), "creating textfield");
		final TextField txtCharName = new TextField(TableTopSimulator.getLangBundle().format("CreateNewChar.txtCharName.text"), skin);

		// creating labels
		TableTopSimulator.debug(this.getClass().toString(), "creating labels");
		Label lblCharName = new Label(TableTopSimulator.getLangBundle().format("CreateNewChar.lblCharName.text"), skin);
		lblCheckName = new Label("", skin, "content");
		
		final CheckBox cbMale = new CheckBox("Male", skin);
		CheckBox cbFemale = new CheckBox("Female", skin);
		
		ButtonGroup<CheckBox> bgSex = new ButtonGroup<CheckBox>();
		bgSex.add(cbMale);
		bgSex.add(cbFemale);
		bgSex.setMinCheckCount(1);
		bgSex.setMaxCheckCount(1);
		bgSex.setUncheckLast(true);

		// creating buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating buttons");
		TextButton btnCheckName = new TextButton(TableTopSimulator.getLangBundle().format("CreateNewChar.btnCheckName.text"), skin);
		btnCheckName.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				checkName(txtCharName.getText());
			}
		});
		btnCheckName.pad(10);

		TextButton btnCreateNewChar = new TextButton(TableTopSimulator.getLangBundle().format("CreateNewChar.btnCreateNewChar.text"), skin);
		btnCreateNewChar.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "creating new game");
				if (checkName(txtCharName.getText())) {
					JSONObject json = new JSONObject();
					try {
						json.put("name", txtCharName.getText());
						if (cbMale.isChecked()) {
							json.put("sex", "m");
						} else {
							json.put("sex", "f");
						}
						json.put("skillPoints", 10);
						json.put("str", 0);
						json.put("vit", 0);
						json.put("att", 0);
						json.put("def", 0);
						json.put("dex", 0);
						json.put("wis", 0);
						json.put("int", 0);
						json.put("cha", 0);
						json.put("wil", 0);
						json.put("awa", 0);
						json.put("lck", 0);
						json.put("hp", 100);
						json.put("mp", 50);
						json.put("ap", 7);
						json.put("exp", 0);
						json.put("lvl", 1);
						json.put("gold", 100);
						
					} catch (Exception e) {
						TableTopSimulator.error(this.getClass().toString(), "error creating new char json", e);
					}
					TableTopSimulator.playerprofile.loadProfileFromJsonobject(json);
					TableTopSimulator.app42.storageServiceInsertJSONDocument(TableTopSimulator.TITLE, "heroes", json);

					TableTopSimulator.log(this.getClass().toString(), "starting game");
					((Game) Gdx.app.getApplicationListener()).setScreen(new CharInfo());
					dispose();
				}
			}
		});
		btnCreateNewChar.pad(10);

		TextButton btnBack = new TextButton(TableTopSimulator.getLangBundle().format("CreateNewChar.btnBack.text"), skin);
		btnBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to MainMenu screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new LoadChar());
				dispose();
			}
		});
		btnBack.pad(10);

		HorizontalGroup hgCheckName = new HorizontalGroup();
		hgCheckName.addActor(btnCheckName);
		hgCheckName.addActor(lblCheckName);
		
		HorizontalGroup hgSex = new HorizontalGroup();
		hgSex.addActor(cbMale);
		hgSex.addActor(cbFemale);

		HorizontalGroup hgCreateBack = new HorizontalGroup();
		hgCreateBack.addActor(btnCreateNewChar);
		hgCreateBack.addActor(btnBack);

		// building ui
		TableTopSimulator.debug(this.getClass().toString(), "building ui");
		table.add(lblHeading).spaceBottom(100).row();
		table.add(lblCharName).spaceBottom(15).row();
		table.add(txtCharName).width(500).spaceBottom(15).row();
		stage.setKeyboardFocus(txtCharName);
		txtCharName.setCursorPosition(txtCharName.getText().length());
		table.add(hgCheckName).spaceBottom(15).row();
		table.add(hgSex).spaceBottom(15).row();
		table.add(hgCreateBack);
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
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up CreateNewChar screen");
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
