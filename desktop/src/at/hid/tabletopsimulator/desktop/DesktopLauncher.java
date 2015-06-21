package at.hid.tabletopsimulator.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import at.hid.tabletopsimulator.TableTopSimulator;
import at.hid.tabletopsimulator.desktop.api.DesktopApp42;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = TableTopSimulator.TITLE + " " + TableTopSimulator.VERSION;
		config.vSyncEnabled = false;
		config.width = 1900;
		config.height = 1000;
		config.x = 0;
		config.y = 0;
		new LwjglApplication(new TableTopSimulator(new DesktopApp42()), config);
	}
}
