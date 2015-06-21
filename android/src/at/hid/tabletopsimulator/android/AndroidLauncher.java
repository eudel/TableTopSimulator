package at.hid.tabletopsimulator.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.shephertz.app42.paas.sdk.android.App42API;

import at.hid.tabletopsimulator.TableTopSimulator;
import at.hid.tabletopsimulator.android.api.AndroidApp42;

public class AndroidLauncher extends AndroidApplication{
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		App42API.appContext = this;
		initialize(new TableTopSimulator(new AndroidApp42()), config);
	}
}
