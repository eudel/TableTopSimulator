/**
 * 
 */
package at.hid.tabletopsimulator.screens;

import java.util.ArrayList;

import at.hid.tabletopsimulator.TableTopSimulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * @author dunkler_engel
 *
 */
public class About implements Screen {

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
		TableTopSimulator.debug(this.getClass().toString(), "creating About screen");
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		Gdx.input.setInputProcessor(stage);

		// creating skin
		TableTopSimulator.debug(this.getClass().toString(), "creating skin");
		skin = TableTopSimulator.assets.get("ui/gui.json", Skin.class);

		table = new Table(skin); 
//		table.setFillParent(true);
//		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		// creating heading
		TableTopSimulator.debug(this.getClass().toString(), "creating heading");
		Label lblHeading = new Label(TableTopSimulator.getLangBundle().format("About.lblHeading.text"), skin);
		
		final Label lblContent = new Label("http://libgdx.badlogicgames.com/\r\n" +
						"\r\n" +
						"libGDX is licensed under the Apache 2 License,\r\n" +
						"meaning you can use it free of charge, without strings attached in commercial and non-commercial projects.\r\n" +
						"We love to get (non-mandatory) credit in case you release a game or app using libgdx!", skin, "content");
		
		// creating list
		
		final List<String> listAbout = new List<String>(skin, "content");
		ArrayList<String> newItems = new ArrayList<String>();
		newItems.add("libgdx");
		newItems.add("libgdx-utils");
		newItems.add("flare gameart");
		String[] data = new String[newItems.size()];
		listAbout.setItems(newItems.toArray(data));
		listAbout.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				 if (listAbout.getSelected().equals("libgdx")) {
						lblContent.clear();
						lblContent.setText("http://libgdx.badlogicgames.com/\r\n" +
								"\r\n" +
								"libGDX is licensed under the Apache 2 License,\r\n" +
								"meaning you can use it free of charge, without strings attached in commercial and non-commercial projects.\r\n" +
								"We love to get (non-mandatory) credit in case you release a game or app using libgdx!");
					} else if (listAbout.getSelected().equals("libgdx-utils")) {
					lblContent.clear();
					lblContent.setText("https://bitbucket.org/dermetfan/libgdx-utils/wiki/Home\r\n" +
							"\r\n" +
							"/* Copyright (c) 2014 PixelScientists\r\n" + 
							"*\r\n" + 
							"* The MIT License (MIT)\r\n" + 
							"*\r\n" + 
							"* Permission is hereby granted, free of charge, to any person obtaining a copy of\r\n" + 
							"* this software and associated documentation files (the \"Software\"), to deal in\r\n" + 
							"* the Software without restriction, including without limitation the rights to\r\n" + 
							"* use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of\r\n" + 
							"* the Software, and to permit persons to whom the Software is furnished to do so,\r\n" + 
							"* subject to the following conditions:\r\n" + 
							"*\r\n" + 
							"* The above copyright notice and this permission notice shall be included in all\r\n" + 
							"* copies or substantial portions of the Software.\r\n" + 
							"*\r\n" + 
							"* THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\r\n" + 
							"* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS\r\n" + 
							"* FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR\r\n" + 
							"* COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER\r\n" + 
							"* IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN\r\n" + 
							"* CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.\r\n" + 
							"*/");
				} else if (listAbout.getSelected().equals("flare gameart")) {
					lblContent.clear();
					lblContent.setText("https://github.com/clintbellanger/flare-game\r\n" +
							"\r\n" +
							"Flare (the game) is Copyright ©2010-2013 Clint Bellanger. Contributors retain copyrights to their original contributions.\r\n" + 
							"\r\n" + 
							"The Flare Engine is released under GPL version 3 or later.\r\n" + 
							"\r\n" + 
							"All of Flare's art and data files are released under CC-BY-SA 3.0. Later versions are permitted.\r\n" + 
							"\r\n" + 
							"The Liberation Sans fonts version 2 are released under the SIL Open Font License, Version 1.1.\r\n" + 
							"\r\n" + 
							"The GNU Unifont font is released under GPL v2, with the exception that embedding the font in a document does not in itself bind that document to the terms of the GPL.");
				}
			}
		});

		// creating buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating buttons");
		TextButton btnBack = new TextButton(TableTopSimulator.getLangBundle().format("About.btnBack.text"), skin);
		btnBack.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to Options screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new Options());
				dispose();
			}
		});
		btnBack.pad(10);

		ScrollPane spAboutList = new ScrollPane(null, skin);
		ScrollPane spAboutContent = new ScrollPane(null, skin);
		
		SplitPane splitAbout = new SplitPane(spAboutList, spAboutContent, false, skin);
		splitAbout.setSplitAmount(0.2f);
		
		spAboutList.setWidget(listAbout);
		spAboutContent.setWidget(lblContent);
		
		// building ui
		TableTopSimulator.debug(this.getClass().toString(), "building ui");
		table.add(lblHeading).spaceBottom(100).row();
		table.add(splitAbout).spaceBottom(15).width(1200).row();
		table.add(btnBack).spaceBottom(15).row();
		if (TableTopSimulator.DEBUG) {
			table.debug(); // draw debug lines 
			splitAbout.debug(); // draw debug lines 
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
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up About screen");
		stage.dispose();
	}

}
