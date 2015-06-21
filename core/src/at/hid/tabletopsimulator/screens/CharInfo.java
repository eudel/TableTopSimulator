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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

/**
 * @author dunkler_engel
 *
 */
public class CharInfo implements Screen {

	private Stage stage;
	private HorizontalGroup hgSkillPoints, hgStr, hgVit, hgAtt, hgDef, hgDex, hgWis, hgInt, hgCha, hgWil, hgAwa, hgLck, hgHp, hgMp, hgAp, hgExp, hgLvl, hgGold;
	private Table table;
	private Skin skin;
	private Label lblHeading, lblName, lblSkillPoints, lblSkillPointsVal, lblStr, lblStrVal, lblVit, lblVitVal, lblAtt, lblAttVal, lblDef, lblDefVal, lblDex, lblDexVal, lblWis, lblWisVal, lblInt, lblIntVal, lblCha, lblChaVal, lblWil, lblWilVal, lblAwa, lblAwaVal, lblLck, lblLckVal, lblHp, lblMp, lblAp, lblExp, lblExpVal, lblLvl, lblLvlVal, lblGold, lblGoldVal;
	private TextButton btnBack, btnSave;
	private ImageButton ibtnStrPlus, ibtnStrMinus, ibtnVitPlus, ibtnVitMinus, ibtnAttPlus, ibtnAttMinus, ibtnDefPlus, ibtnDefMinus, ibtnDexPlus, ibtnDexMinus, ibtnWisPlus, ibtnWisMinus, ibtnIntPlus, ibtnIntMinus, ibtnChaPlus, ibtnChaMinus, ibtnWilPlus, ibtnWilMinus, ibtnAwaPlus, ibtnAwaMinus, ibtnLckPlus, ibtnLckMinus;
	private ProgressBar pbHp, pbMp, pbAp;

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
		TableTopSimulator.debug(this.getClass().toString(), "creating CharInfo screen");
		stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		Gdx.input.setInputProcessor(stage);

		// creating skin
		TableTopSimulator.debug(this.getClass().toString(), "creating skin");
		skin = TableTopSimulator.assets.get("ui/gui.json", Skin.class);

		table = new Table(skin);
		table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		// creating heading
		TableTopSimulator.debug(this.getClass().toString(), "creating heading");
		lblHeading = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblHeading.text"), skin);

		// creating labels
		TableTopSimulator.debug(this.getClass().toString(), "creating labels");
		lblName = new Label(TableTopSimulator.playerprofile.getName(), skin);
		lblSkillPoints = new Label("(" + TableTopSimulator.getLangBundle().format("CharInfo.lblSkillPoints.text") + ": ", skin);
		lblSkillPointsVal = new Label(TableTopSimulator.playerprofile.getSkillPoints() + ")", skin);
		lblStr = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblStr.text") + " ", skin);
		lblStrVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getStr()), skin);
		lblVit = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblVit.text") + " ", skin);
		lblVitVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getVit()), skin);
		lblAtt = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblAtt.text") + " ", skin);
		lblAttVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getAtt()), skin);
		lblDef = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblDef.text") + " ", skin);
		lblDefVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getDef()), skin);
		lblDex = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblDex.text") + " ", skin);
		lblDexVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getDex()), skin);
		lblWis = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblWis.text") + " ", skin);
		lblWisVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getWis()), skin);
		lblInt = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblInt.text") + " ", skin);
		lblIntVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getInt_()), skin);
		lblCha = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblCha.text") + " ", skin);
		lblChaVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getCha()), skin);
		lblWil = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblWil.text") + " ", skin);
		lblWilVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getWil()), skin);
		lblAwa = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblAwa.text") + " ", skin);
		lblAwaVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getAwa()), skin);
		lblLck = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblLck.text") + " ", skin);
		lblLckVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getLck()), skin);
		lblHp = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblHp.text") + " ", skin);
		lblMp = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblMp.text") + " ", skin);
		lblAp = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblAp.text") + " ", skin);
		lblExp = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblExp.text") + " ", skin);
		lblExpVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getExp()), skin);
		lblLvl = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblLvl.text") + " ", skin);
		lblLvlVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getLvl()), skin);
		lblGold = new Label(TableTopSimulator.getLangBundle().format("CharInfo.lblGold.text") + " ", skin);
		lblGoldVal = new Label(Integer.toString(TableTopSimulator.playerprofile.getGold()), skin);

		// creating progress bars
		TableTopSimulator.debug(this.getClass().toString(), "creating prograss bars");
		pbHp = new ProgressBar(0, 100, 1, false, skin, "hp-horizontal");
		pbMp = new ProgressBar(0, 100, 1, false, skin, "mp-horizontal");
		pbAp = new ProgressBar(0, 10, 1, false, skin, "ap-horizontal");
		pbHp.setValue(TableTopSimulator.playerprofile.getHp());
		pbMp.setValue(TableTopSimulator.playerprofile.getMp());
		pbAp.setValue(TableTopSimulator.playerprofile.getAp());

		// creating buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating buttons");
		if (lblStrVal.getText().toString().equals("0")) {
			btnBack = new TextButton(TableTopSimulator.getLangBundle().format("CharInfo.btnBack.text1"), skin);
		} else {
			btnBack = new TextButton(TableTopSimulator.getLangBundle().format("CharInfo.btnBack.text"), skin);
		}
		btnBack.pad(10);
		btnSave = new TextButton(TableTopSimulator.getLangBundle().format("CharInfo.btnSave.text"), skin);
		btnSave.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.debug(this.getClass().toString(), "switching to GameScreen screen");
				((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
				dispose();
			}
		});
		btnSave.pad(10);

		// creating image buttons
		TableTopSimulator.debug(this.getClass().toString(), "creating image buttons");
		ibtnStrPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnStrPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!ibtnStrPlus.isDisabled()) {
					TableTopSimulator.playerprofile.setStr(TableTopSimulator.playerprofile.getStr() + 1);
					lblStrVal.setText(Integer.toString(TableTopSimulator.playerprofile.getStr()));
					TableTopSimulator.playerprofile.setSkillPoints(TableTopSimulator.playerprofile.getSkillPoints() - 1);
					lblSkillPointsVal.setText(TableTopSimulator.playerprofile.getSkillPoints() + ")");
					if (TableTopSimulator.playerprofile.getSkillPoints() == 0) {
						disablePlus();
					}
					if (TableTopSimulator.playerprofile.getStr() == TableTopSimulator.playerprofile.getLvl() * 5) {
						ibtnStrPlus.setDisabled(true);
					}
					ibtnStrMinus.setDisabled(false);
				}
			}
		});

		ibtnStrMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnStrMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (!ibtnStrMinus.isDisabled()) {
					TableTopSimulator.playerprofile.setStr(TableTopSimulator.playerprofile.getStr() - 1);
					lblStrVal.setText(Integer.toString(TableTopSimulator.playerprofile.getStr()));
					TableTopSimulator.playerprofile.setSkillPoints(TableTopSimulator.playerprofile.getSkillPoints() + 1);
					lblSkillPointsVal.setText(TableTopSimulator.playerprofile.getSkillPoints() + ")");
					if (TableTopSimulator.playerprofile.getStr() == 0) {
						ibtnStrMinus.setDisabled(true);
					}
					ibtnStrPlus.setDisabled(false);
				}
			}
		});

		ibtnVitPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnVitPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setVit(TableTopSimulator.playerprofile.getVit() + 1);
				lblVitVal.setText(Integer.toString(TableTopSimulator.playerprofile.getVit()));
			}
		});

		ibtnVitMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnVitMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setVit(TableTopSimulator.playerprofile.getVit() - 1);
				lblVitVal.setText(Integer.toString(TableTopSimulator.playerprofile.getVit()));
			}
		});

		ibtnAttPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnAttPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setAtt(TableTopSimulator.playerprofile.getAtt() + 1);
				lblAttVal.setText(Integer.toString(TableTopSimulator.playerprofile.getAtt()));
			}
		});

		ibtnAttMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnAttMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setAtt(TableTopSimulator.playerprofile.getAtt() - 1);
				lblAttVal.setText(Integer.toString(TableTopSimulator.playerprofile.getAtt()));
			}
		});

		ibtnDefPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnDefPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setDef(TableTopSimulator.playerprofile.getDef() + 1);
				lblDefVal.setText(Integer.toString(TableTopSimulator.playerprofile.getDef()));
			}
		});

		ibtnDefMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnDefMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setDef(TableTopSimulator.playerprofile.getDef() - 1);
				lblDefVal.setText(Integer.toString(TableTopSimulator.playerprofile.getDef()));
			}
		});

		ibtnDexPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnDexPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setDex(TableTopSimulator.playerprofile.getDex() + 1);
				lblDexVal.setText(Integer.toString(TableTopSimulator.playerprofile.getDex()));
			}
		});

		ibtnDexMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnDexMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setDex(TableTopSimulator.playerprofile.getDex() - 1);
				lblDexVal.setText(Integer.toString(TableTopSimulator.playerprofile.getDex()));
			}
		});

		ibtnWisPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnWisPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setWis(TableTopSimulator.playerprofile.getWis() + 1);
				lblWisVal.setText(Integer.toString(TableTopSimulator.playerprofile.getWis()));
			}
		});

		ibtnWisMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnWisMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setWis(TableTopSimulator.playerprofile.getWis() - 1);
				lblWisVal.setText(Integer.toString(TableTopSimulator.playerprofile.getWis()));
			}
		});

		ibtnIntPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnIntPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setInt_(TableTopSimulator.playerprofile.getInt_() + 1);
				lblIntVal.setText(Integer.toString(TableTopSimulator.playerprofile.getInt_()));
			}
		});

		ibtnIntMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnIntMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setInt_(TableTopSimulator.playerprofile.getInt_() - 1);
				lblIntVal.setText(Integer.toString(TableTopSimulator.playerprofile.getInt_()));
			}
		});

		ibtnChaPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnChaPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setCha(TableTopSimulator.playerprofile.getCha() + 1);
				lblChaVal.setText(Integer.toString(TableTopSimulator.playerprofile.getCha()));
			}
		});

		ibtnChaMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnChaMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setCha(TableTopSimulator.playerprofile.getCha() - 1);
				lblChaVal.setText(Integer.toString(TableTopSimulator.playerprofile.getCha()));
			}
		});

		ibtnWilPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnWilPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setWil(TableTopSimulator.playerprofile.getWil() + 1);
				lblWilVal.setText(Integer.toString(TableTopSimulator.playerprofile.getWil()));
			}
		});

		ibtnWilMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnWilMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setWil(TableTopSimulator.playerprofile.getWil() - 1);
				lblWilVal.setText(Integer.toString(TableTopSimulator.playerprofile.getWil()));
			}
		});

		ibtnAwaPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnAwaPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setAwa(TableTopSimulator.playerprofile.getAwa() + 1);
				lblAwaVal.setText(Integer.toString(TableTopSimulator.playerprofile.getAwa()));
			}
		});

		ibtnAwaMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnAwaMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setAwa(TableTopSimulator.playerprofile.getAwa() - 1);
				lblAwaVal.setText(Integer.toString(TableTopSimulator.playerprofile.getAwa()));
			}
		});

		ibtnLckPlus = new ImageButton(skin, "default-horizontal-plus");
		ibtnLckPlus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setLck(TableTopSimulator.playerprofile.getLck() + 1);
				lblLckVal.setText(Integer.toString(TableTopSimulator.playerprofile.getLck()));
			}
		});

		ibtnLckMinus = new ImageButton(skin, "default-horizontal-minus");
		ibtnLckMinus.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				TableTopSimulator.playerprofile.setLck(TableTopSimulator.playerprofile.getLck() - 1);
				lblLckVal.setText(Integer.toString(TableTopSimulator.playerprofile.getLck()));
			}
		});

		// creating horizontal groups
		TableTopSimulator.debug(this.getClass().toString(), "creating horizontal groups");
		hgSkillPoints = new HorizontalGroup();
		hgSkillPoints.addActor(lblName);
		hgSkillPoints.addActor(lblSkillPoints);
		hgSkillPoints.addActor(lblSkillPointsVal);

		hgStr = new HorizontalGroup();
		hgStr.addActor(lblStr);
		hgStr.addActor(ibtnStrMinus);
		hgStr.addActor(lblStrVal);
		hgStr.addActor(ibtnStrPlus);

		hgVit = new HorizontalGroup();
		hgVit.addActor(lblVit);
		hgVit.addActor(ibtnVitMinus);
		hgVit.addActor(lblVitVal);
		hgVit.addActor(ibtnVitPlus);

		hgAtt = new HorizontalGroup();
		hgAtt.addActor(lblAtt);
		hgAtt.addActor(ibtnAttMinus);
		hgAtt.addActor(lblAttVal);
		hgAtt.addActor(ibtnAttPlus);

		hgDef = new HorizontalGroup();
		hgDef.addActor(lblDef);
		hgDef.addActor(ibtnDefMinus);
		hgDef.addActor(lblDefVal);
		hgDef.addActor(ibtnDefPlus);

		hgDex = new HorizontalGroup();
		hgDex.addActor(lblDex);
		hgDex.addActor(ibtnDexMinus);
		hgDex.addActor(lblDexVal);
		hgDex.addActor(ibtnDexPlus);

		hgWis = new HorizontalGroup();
		hgWis.addActor(lblWis);
		hgWis.addActor(ibtnWisMinus);
		hgWis.addActor(lblWisVal);
		hgWis.addActor(ibtnWisPlus);

		hgInt = new HorizontalGroup();
		hgInt.addActor(lblInt);
		hgInt.addActor(ibtnIntMinus);
		hgInt.addActor(lblIntVal);
		hgInt.addActor(ibtnIntPlus);

		hgCha = new HorizontalGroup();
		hgCha.addActor(lblCha);
		hgCha.addActor(ibtnChaMinus);
		hgCha.addActor(lblChaVal);
		hgCha.addActor(ibtnChaPlus);

		hgWil = new HorizontalGroup();
		hgWil.addActor(lblWil);
		hgWil.addActor(ibtnWilMinus);
		hgWil.addActor(lblWilVal);
		hgWil.addActor(ibtnWilPlus);

		hgAwa = new HorizontalGroup();
		hgAwa.addActor(lblAwa);
		hgAwa.addActor(ibtnAwaMinus);
		hgAwa.addActor(lblAwaVal);
		hgAwa.addActor(ibtnAwaPlus);

		hgLck = new HorizontalGroup();
		hgLck.addActor(lblLck);
		hgLck.addActor(ibtnLckMinus);
		hgLck.addActor(lblLckVal);
		hgLck.addActor(ibtnLckPlus);

		hgHp = new HorizontalGroup();
		hgHp.addActor(lblHp);
		hgHp.addActor(pbHp);
		
		hgMp = new HorizontalGroup();
		hgMp.addActor(lblMp);
		hgMp.addActor(pbMp);
		
		hgAp = new HorizontalGroup();
		hgAp.addActor(lblAp);
		hgAp.addActor(pbAp);
		
		hgExp = new HorizontalGroup();
		hgExp.addActor(lblExp);
		hgExp.addActor(lblExpVal);
		
		hgLvl = new HorizontalGroup();
		hgLvl.addActor(lblLvl);
		hgLvl.addActor(lblLvlVal);
		
		hgGold = new HorizontalGroup();
		hgGold.addActor(lblGold);
		hgGold.addActor(lblGoldVal);
		
		// building ui
		TableTopSimulator.debug(this.getClass().toString(), "building ui");
		table.add().width(table.getWidth() / 4);
		table.add().width(table.getWidth() / 4);
		table.add().width(table.getWidth() / 4);
		table.add().width(table.getWidth() / 4).row();
		table.add(lblHeading).colspan(4).spaceBottom(15).row();
		table.add(lblName).colspan(4).spaceBottom(15).row();
		table.add().colspan(4).spaceBottom(15).row();
		table.add();
		table.add(hgSkillPoints).colspan(2).spaceBottom(15);
		table.add().row();
		table.add().spaceBottom(15);
		table.add().spaceBottom(15);
		table.add(hgStr).spaceBottom(15);
		table.add(hgVit).spaceBottom(15).row();
		table.add().spaceBottom(15);
		table.add().spaceBottom(15);
		table.add(hgAtt).spaceBottom(15);
		table.add(hgDef).spaceBottom(15).row();
		table.add().spaceBottom(15);
		table.add().spaceBottom(15);
		table.add(hgDex).spaceBottom(15);
		table.add(hgWis).spaceBottom(15).row();
		table.add().spaceBottom(15);
		table.add().spaceBottom(15);
		table.add(hgInt).spaceBottom(15);
		table.add(hgCha).spaceBottom(15).row();
		table.add().spaceBottom(15);
		table.add().spaceBottom(15);
		table.add(hgWil).spaceBottom(15);
		table.add(hgAwa).spaceBottom(15).row();
		table.add().spaceBottom(15);
		table.add().spaceBottom(15);
		table.add().spaceBottom(15);
		table.add(hgLck).spaceBottom(15).row();
		table.add();
		table.add();
		table.add(hgHp);
		table.add(hgExp).row();
		table.add();
		table.add();
		table.add(hgMp);
		table.add(hgLvl).row();
		table.add().spaceBottom(15);
		table.add().spaceBottom(15);
		table.add(hgAp).spaceBottom(15);
		table.add(hgGold).spaceBottom(15).row();
		table.add();
		table.add(btnBack).spaceBottom(15);
		table.add(btnSave).spaceBottom(15);
		table.add();

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
		TableTopSimulator.debug(this.getClass().toString(), "cleaning up CharInfo screen");
		stage.dispose();
	}

	public void disablePlus() {
		ibtnStrPlus.setDisabled(true);
		ibtnVitPlus.setDisabled(true);
		ibtnAttPlus.setDisabled(true);
		ibtnDefPlus.setDisabled(true);
		ibtnDexPlus.setDisabled(true);
		ibtnWisPlus.setDisabled(true);
		ibtnIntPlus.setDisabled(true);
		ibtnChaPlus.setDisabled(true);
		ibtnWilPlus.setDisabled(true);
		ibtnAwaPlus.setDisabled(true);
		ibtnLckPlus.setDisabled(true);
	}

	public void enablePlus() {
		ibtnStrPlus.setDisabled(false);
		ibtnVitPlus.setDisabled(false);
		ibtnAttPlus.setDisabled(false);
		ibtnDefPlus.setDisabled(false);
		ibtnDexPlus.setDisabled(false);
		ibtnWisPlus.setDisabled(false);
		ibtnIntPlus.setDisabled(false);
		ibtnChaPlus.setDisabled(false);
		ibtnWilPlus.setDisabled(false);
		ibtnAwaPlus.setDisabled(false);
		ibtnLckPlus.setDisabled(false);
	}

	public void disableMinus() {
		ibtnStrMinus.setDisabled(true);
		ibtnVitMinus.setDisabled(true);
		ibtnAttMinus.setDisabled(true);
		ibtnDefMinus.setDisabled(true);
		ibtnDexMinus.setDisabled(true);
		ibtnWisMinus.setDisabled(true);
		ibtnIntMinus.setDisabled(true);
		ibtnChaMinus.setDisabled(true);
		ibtnWilMinus.setDisabled(true);
		ibtnAwaMinus.setDisabled(true);
		ibtnLckMinus.setDisabled(true);
	}

	public void enableMinus() {
		ibtnStrMinus.setDisabled(false);
		ibtnVitMinus.setDisabled(false);
		ibtnAttMinus.setDisabled(false);
		ibtnDefMinus.setDisabled(false);
		ibtnDexMinus.setDisabled(false);
		ibtnWisMinus.setDisabled(false);
		ibtnIntMinus.setDisabled(false);
		ibtnChaMinus.setDisabled(false);
		ibtnWilMinus.setDisabled(false);
		ibtnAwaMinus.setDisabled(false);
		ibtnLckMinus.setDisabled(false);
	}
}
