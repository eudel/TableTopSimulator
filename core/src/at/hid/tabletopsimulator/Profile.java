/**
 * 
 */
package at.hid.tabletopsimulator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

/**
 * @author dunkler_engel
 *
 */
public class Profile {
	
	private String saveDir, saveName;
	private int gamemode;
	boolean cheats;
	public Profile() {	
	}

	/**
	 * gets the save directory
	 * @return the save directory
	 */
	public String getSaveDir() {
		return saveDir;
	}

	/**
	 * sets the save directory
	 * @param saveDir the saveDir to set
	 */
	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	/**
	 * gets the save name
	 * @return the save name
	 */
	public String getSaveName() {
		return saveName;
	}

	/**
	 * sets the save name
	 * @param saveName the saveName to set
	 */
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	

	/**
	 * gets the gamemode
	 * @return the gamemode
	 */
	public int getGamemode() {
		if(gamemode != 0) {
		return gamemode;
		} else {
			return 0;
		}
	}

	/**
	 * sets the gamemode
	 * @param gamemode the gamemode to set
	 */
	public void setGamemode(int gamemode) {
		this.gamemode = gamemode;
	}

	/**
	 * gets the cheats usage
	 * @return the cheats usage
	 */
	public boolean getCheats() {
		if(cheats == true) {
		return cheats;
		} else {
			return false;
		}
	}

	/**
	 * sets the cheats usage
	 * @param cheats cheat usage
	 */
	public void setCheats(boolean cheats) {
		this.cheats = cheats;
	}

	/**
	 * saves the profile file to disk
	 * @param profile the profile to save
	 */
	public void saveProfile(Profile profile) {
		 Json json = new Json();
		 FileHandle fhSettings = Gdx.files.external(".hidlauncher/" + TableTopSimulator.TITLE + "/saves/" + getSaveDir() + "/data/settings.dat");
		 String profileAsText = json.toJson(profile);
		 String profileAsCode = Base64Coder.encodeString(profileAsText);
		 fhSettings.writeString(profileAsCode, false);
	}
	
	/**
	 * loads the profile from disk
	 * @param saveDir name of the save directory
	 * @return the Profile object
	 */
	public Profile loadProfile(String saveDir) {
		Json json = new Json();
		Profile profile = null;
		FileHandle fhSettings = Gdx.files.external(".hidlauncher/" + TableTopSimulator.TITLE + "/saves/" + saveDir + "/data/settings.dat");
		try {
			String profileAsCode = fhSettings.readString();
			String profileAsText = Base64Coder.decodeString(profileAsCode);
			profile = json.fromJson(Profile.class, profileAsText);
		} catch (Exception e) {
			TableTopSimulator.error(this.getClass().toString(), "error reading settings file", e);
		}
		return profile;
	}
	
	/**
	 * loads the saveName from saveDir
	 * @param saveDir name of the save directory
	 * @return the save name
	 */
	public String loadSaveName(String saveDir) {
		Profile profile = loadProfile(saveDir);
		return profile.getSaveName();
	}
}
