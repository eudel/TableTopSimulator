package at.hid.tabletopsimulator.screens;

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

public class Levels implements Screen {

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
		TableTopSimulator.debug(this.getClass().toString(), "creating Levels screen");
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		Gdx.input.setInputProcessor(stage);

		// creating skin
		TableTopSimulator.debug(this.getClass().toString(), "creating skin");
		skin = TableTopSimulator.assets.get("ui/gui.json", Skin.class);

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// creating heading
		TableTopSimulator.debug(this.getClass().toString(), "creating heading");
		Label lblHeading = new Label(TableTopSimulator.getLangBundle().format("Levels.lblHeading.text"), skin);

		// creating buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating buttons");
		TextButton btnBack = new TextButton(TableTopSimulator.getLangBundle().format("Levels.btnBack.text"), skin);
		btnBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to MainMenu screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new MainMenu());
				dispose();
			}
		});
		btnBack.pad(10);
		
		// creating list
		TableTopSimulator.debug(this.getClass().toString(), "creating savedGames list");
		List<String> list = new List<String>(skin);
		
//		FileHandle[] files = fhSaves.list();
//		ArrayList<String> newDatas = new ArrayList<String>();
//		for (int i = 0; i < files.length; i++) {
//			try {
//				String[] settings = files[i].readString("UTF-8").split("\n");
//				String saveName = settings[0];
//				int saveGamemode = Integer.parseInt(settings[1]);
//				int saveCheats = Integer.parseInt(settings[2]);
//
//				String saveGamemodeStr = "", saveCheatsStr = "";
//				if (saveGamemode == 0) {
//					saveGamemodeStr = TableTopSimulator.getLangBundle().format("CreateNewGame.btnGamemode.text");
//				} else if (saveGamemode == 1) {
//					saveGamemodeStr = TableTopSimulator.getLangBundle().format("CreateNewGame.btnGamemode.text1");
//				}
//				if (saveCheats == 1) {
//					saveCheatsStr = ", Cheats";
//				}
//				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YY - HH:mm");
//
//				newDatas.add(saveName + "\n" + files[i] + " (" + sdf.format(fhSaves.lastModified()) + ")\n" + saveGamemodeStr + saveCheatsStr);
//			} catch (Exception e) {
//				
//				TableTopSimulator.error(this.getClass().toString(), "ERROR reading saved games", e);
//			}
//		}
//		String[] data = new String[newDatas.size()];
//		newDatas.toArray(data);
//		list.setItems(data);

		// building ui
		TableTopSimulator.debug(this.getClass().toString(), "building ui");
		table.add(lblHeading);
		table.getCell(lblHeading).spaceBottom(100);
		table.row();
		table.add(list);
		table.row();
		table.add(btnBack);
		table.getCell(btnBack).spaceBottom(15);
		table.row();
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
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up Levels screen");
		stage.dispose();
	}

}
