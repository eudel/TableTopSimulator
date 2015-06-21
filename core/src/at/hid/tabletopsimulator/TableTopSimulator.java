package at.hid.tabletopsimulator;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.json.JSONObject;

import at.hid.tabletopsimulator.api.App42;
import at.hid.tabletopsimulator.screens.Splash;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.I18NBundle;

public class TableTopSimulator extends Game {
	public static final String TITLE = "TableTopSimulator", VERSION = "0.0.1-alpha";
	public static boolean DEBUG;
	public static final Profile profile = new Profile();
	public static final PlayerProfile playerprofile = new PlayerProfile();
	public static final AssetManager assets = new AssetManager();
	public static final Random random = new Random();
	public static I18NBundle langBundle;
	public static App42 app42 = null;

	public TableTopSimulator(App42 app42) {
		TableTopSimulator.app42 = app42;
	}

	/**
	 * creates the language bundle
	 */
	public static void createLangBundle(String lang) {
		FileHandle fhLang = Gdx.files.internal("lang/Language");
		Locale locale = new Locale(lang);
		langBundle = I18NBundle.createBundle(fhLang, locale);
	}

	/**
	 * @return the langBundle
	 */
	public static I18NBundle getLangBundle() {
		return langBundle;
	}

	public static void log(String tag, String message) {
		FileHandle fhLog = Gdx.files.external(".hidlauncher/" + TITLE + "/logs/latest.log");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss: ");
		Gdx.app.log(tag, message);
		fhLog.writeString(sdf.format(date) + tag + ": " + message + "\n", true, "UTF-8");
	}

	public static void debug(String tag, String message) {
		FileHandle fhLog = Gdx.files.external(".hidlauncher/" + TITLE + "/logs/latest.log");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss: ");
		Gdx.app.debug(tag, message);
		if (TableTopSimulator.DEBUG) {
			fhLog.writeString(sdf.format(date) + tag + ": Debug: " + message + "\n", true, "UTF-8");
		}
	}

	public static void error(String tag, String message, Throwable t) {
		FileHandle fhLog = Gdx.files.external(".hidlauncher/" + TITLE + "/logs/latest.log");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss: ");
		Gdx.app.error(tag, message, t);
		fhLog.writeString(sdf.format(date) + tag + ": ERROR: " + message + "\n", true, "UTF-8");
		fhLog.writeString(t + "\n", true, "UTF-8");
	}

	public void logrotate() {
		FileHandle fhLog = Gdx.files.external(".hidlauncher/" + TITLE + "/logs/latest.log");
		fhLog.parent().mkdirs();
		if (fhLog.exists()) {
			byte[] buffer = new byte[1024];
			try {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
				FileOutputStream fos = new FileOutputStream(Gdx.files.external(".hidlauncher/" + TITLE + "/logs/" + sdf.format(date) + ".zip").file());
				ZipOutputStream zos = new ZipOutputStream(fos);
				ZipEntry ze = new ZipEntry("latest.log");
				zos.putNextEntry(ze);
				FileInputStream in = new FileInputStream(Gdx.files.external(".hidlauncher/" + TITLE + "/logs/latest.log").file());

				int len;
				while ((len = in.read(buffer)) > 0) {
					zos.write(buffer, 0, len);
				}

				in.close();
				zos.closeEntry();
				zos.close();
				fhLog.delete();
			} catch (Exception e) {
				error(this.getClass().getName(), "error rotating log file", e);
			}
		}
	}

	@Override
	public void create() {

		app42.initialize("674d23290fe918b3487a5751f2dd181cde0f29a74747d357230f5a03a64e92b8", "cad8aef024fe2ab6d0d2ecb6258b0d0a49d6185fde89abca519aac1226c8c5c6");
		app42.buildUserService();
		app42.buildStorageService();

		String username = "";
		if (Gdx.files.external(".hidlauncher/launcher_profiles.json").exists()) {
			try {
				JSONObject json = new JSONObject(Gdx.files.external(".hidlauncher/launcher_profiles.json").readString("UTF-8"));
				username = json.getString("selectedUser");
			} catch (Exception e) {
				TableTopSimulator.error(this.getClass().toString(), "error reading launcher_profiles", e);
			}
		}
		String sid = Gdx.app.getPreferences("HIDLauncher").getString("sessionIdTts");

		if (username != "") {
			if (inetConnection()) {
				app42.getUser(username);
			}
			app42.setSessionId(sid);
		}

		DEBUG = Gdx.app.getPreferences(TITLE).getBoolean("debug");
		logrotate();

		if (DEBUG) {
			Gdx.app.setLogLevel(Application.LOG_DEBUG); // show debug logs 
		} else {
			Gdx.app.setLogLevel(Application.LOG_INFO);
		}

		if (Gdx.app.getPreferences(TITLE).contains("lang")) { // load saved language preferences
			String[] data = Gdx.app.getPreferences(TITLE).getString("lang").split("_");
			Locale.setDefault(new Locale(data[0], data[1]));
		} else {
			Gdx.app.getPreferences(TITLE).putString("lang", Locale.getDefault().toString());
			Gdx.app.getPreferences(TITLE).putBoolean("debug", false);
			Gdx.app.getPreferences(TITLE).putBoolean("fullscreen", false);
			Gdx.app.getPreferences(TITLE).putBoolean("vsync", true);
			Gdx.app.getPreferences(TITLE).flush();
		}

		createLangBundle(Locale.getDefault().toString());

		Texture.setAssetManager(assets);
		assets.load("img/splash.jpg", Texture.class);
		assets.finishLoading();

		setScreen(new Splash());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public boolean inetConnection() {
		try {
			final URLConnection connection = new URL("http://api.shephertz.com/").openConnection();
			connection.connect();
			log(this.getClass().toString(), "Internet connection available.");
			return true;
		} catch (final Exception e) {
			return false;
		}
	}
}
